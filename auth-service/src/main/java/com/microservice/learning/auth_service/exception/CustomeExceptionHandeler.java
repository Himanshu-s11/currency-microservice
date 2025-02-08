package com.microservice.learning.auth_service.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@RestControllerAdvice //
public class CustomeExceptionHandeler {

         ProblemDetail errorDetail=null;
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e) {

        if(e instanceof BadCredentialsException){
            errorDetail=ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(401),"Invalid Credentials");
            errorDetail.setProperty("access_denied_reason","Authentication failed");
        }

        if(e instanceof AccessDeniedException){
            errorDetail=ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403),"Invalid Credentials");
            errorDetail.setProperty("access_denied_reason","Not authorized to access the resource");
        }
// use exception handel resolver
        if(e instanceof SignatureException){
            errorDetail=ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403),"Invalid Credentials");
            errorDetail.setProperty("access_denied_reason","Invalid token");
        }

        if(e instanceof ExpiredJwtException){
            errorDetail=ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403),"Invalid Credentials");
            errorDetail.setProperty("access_denied_reason","JWT token already expired");
        }

        return errorDetail;
    }
}
