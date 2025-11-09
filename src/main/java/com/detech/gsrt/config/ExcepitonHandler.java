package com.detech.gsrt.config;

import com.detech.gsrt.exception.ErrorWS;
import com.detech.gsrt.exception.UtilisateurException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcepitonHandler {

    @ExceptionHandler(UtilisateurException.class)
    public ResponseEntity<Object> handleHelloException(UtilisateurException ex){
        ErrorWS errorWS = new ErrorWS();
        errorWS.setCode(500);
        errorWS.setMessage(ex.getMessage());
        return ResponseEntity.status(ex.getError().getCode())
                .body(errorWS);
    }
}
