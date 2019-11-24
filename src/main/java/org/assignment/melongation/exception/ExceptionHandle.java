package org.assignment.melongation.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理
 */
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(MelongationException.class)
    @ResponseBody
    public String handle(MelongationException e) {
        return e.getMessage();
    }
}
