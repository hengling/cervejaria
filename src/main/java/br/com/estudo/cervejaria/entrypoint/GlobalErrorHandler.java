package br.com.estudo.cervejaria.entrypoint;

import br.com.estudo.cervejaria.util.exception.BusinessErrorException;
import br.com.estudo.cervejaria.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(BusinessErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionPayload tratarBusinessErrorException(BusinessErrorException e) {
        return ExceptionPayload.builder()
                .message(Objects.nonNull(e.getMessage()) ? e.getMessage() : "Ocorreu um erro ao executar a operação.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionPayload tratarNotFoundException(NotFoundException e) {
        return ExceptionPayload.builder()
                .message(Objects.nonNull(e.getMessage()) ? e.getMessage() : "Registro não encontrado.")
                .status(HttpStatus.NOT_FOUND.value())
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExceptionPayload {
        private String message;
        private int status;
    }
}
