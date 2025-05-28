package com.meuprojeto.gestaoEscolar.infra;

import com.meuprojeto.gestaoEscolar.exceptions.AlunoNotFoundExecption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(AlunoNotFoundExecption.class)
    private ResponseEntity<RestErrorMessage> alunoNotFoundHandler(AlunoNotFoundExecption execption){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND,execption.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

}
