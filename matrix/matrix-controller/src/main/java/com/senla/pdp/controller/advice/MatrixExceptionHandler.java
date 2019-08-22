package com.senla.pdp.controller.advice;

import com.senla.pdp.api.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.mail.MessagingException;

@ControllerAdvice
public class MatrixExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatrixExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity does not exist")
    @ExceptionHandler(EntityNotFoundException.class)
    public void handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        LOGGER.error(entityNotFoundException.getLocalizedMessage());
    }

    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler({MessagingException.class})
    public void handleMessagingException(MessagingException messagingException){
        LOGGER.error(messagingException.getLocalizedMessage());
    }
}
