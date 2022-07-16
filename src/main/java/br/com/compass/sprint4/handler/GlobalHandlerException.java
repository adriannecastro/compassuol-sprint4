package br.com.compass.sprint4.handler;

import br.com.compass.sprint4.exceptions.AssociatesNotFoudException;
import br.com.compass.sprint4.exceptions.PoliticPositionInvalidException;
import br.com.compass.sprint4.exceptions.SexInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    private static final String ASSOCIATES_NOT_FOUND = "Associate Not Found";
    private static final String INVALID_POLITIC_POSITION = "Invalid Politic Position. The first letter must be uppercase" +
            "The values cannot be different from Vereador, Prefeito, Deputado Estadual, Deputado Federal, Senador, Governador, Presidente";
    private static final String INVALID_SEX = "Invalid sex";
    private static final String INTERNAL_ERROR = "Internal Server Error";

    @ExceptionHandler(value = {AssociatesNotFoudException.class})
    protected ResponseEntity<ErrorMessage> handlerAssociateNotFound(AssociatesNotFoudException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(ASSOCIATES_NOT_FOUND));
    }

    @ExceptionHandler(value = {PoliticPositionInvalidException.class})
    protected ResponseEntity<ErrorMessage> handlerPoliticPositionInvalid(PoliticPositionInvalidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(INVALID_POLITIC_POSITION));
    }

    @ExceptionHandler(value = {SexInvalidException.class})
    protected ResponseEntity<ErrorMessage> handlerInvalidSex(SexInvalidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(INVALID_SEX));
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorMessage> handlerException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(INTERNAL_ERROR));
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<String> validationList = e.getBindingResult().getFieldErrors().stream().map(fieldError ->
                "Campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage()).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(validationList));
    }
}
