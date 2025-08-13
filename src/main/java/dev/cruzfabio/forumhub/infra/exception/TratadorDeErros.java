package dev.cruzfabio.forumhub.infra.exception;

import dev.cruzfabio.forumhub.domain.ValidacaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarValidação(ValidacaoException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroDeValidacao::new).toList());
    }

    public record DadosErroDeValidacao(String campo, String mensagem) {
        public DadosErroDeValidacao(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
