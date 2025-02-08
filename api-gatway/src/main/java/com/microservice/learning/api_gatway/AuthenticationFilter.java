package com.microservice.learning.api_gatway;

import com.microservice.learning.api_gatway.util.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JWTService jwtService;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            // If the request is not secured then pass the request
            if(routeValidator.isSecured().test(exchange.getRequest())){
               //check header contains token or not
                 if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                     throw new RuntimeException("Missing Authorization Header");
                 }
                 //Get the token if present
                 String authHeader= exchange.getRequest().getHeaders()
                         .getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);
                 if(authHeader!=null && authHeader.startsWith("Bearer")){
                     authHeader= authHeader.substring(7);
                 }try {
                    //REST call to auth service
                   // restTemplate.getForObject("http://auth-service//auth/validate" + authHeader, String.class);
                 //if token is valid then only pass the request
                  jwtService.validateToken(authHeader);
                  //Extract role
                    String role= jwtService.extractRole(authHeader);
                    // Role-based access control: Allow access based on the role
                    if (exchange.getRequest().getPath().toString().contains("/currency-exchange") && !role.equals("ADMIN")) {
                        throw new RuntimeException("Access Denied: Admin role required");
                    } else if (exchange.getRequest().getPath().toString().contains("/currency-conversion-feign") && !role.equals("USER")) {
                        throw new RuntimeException("Access Denied: User role required");
                    }
                }catch(Exception e){
                    throw new RuntimeException("Not Authorized");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
        // Put configuration properties here

    }
}
