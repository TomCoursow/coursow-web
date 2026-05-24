package de.coursow.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFound(NoHandlerFoundException e, HttpServletRequest request, HttpServletResponse response, Model model) {
        return handleNotFound(e, request, response, model);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFound(NoResourceFoundException e, HttpServletRequest request, HttpServletResponse response, Model model) {
        return handleNotFound(e, request, response, model);
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request, HttpServletResponse response, Model model) {
        return handleServerError(e, request, response, model);
    }

    private String handleNotFound(Exception e, HttpServletRequest request, HttpServletResponse response, Model model) {
        int status = HttpStatus.NOT_FOUND.value();
        String errorMessage = "Seite nicht gefunden.";
        String targetFragment = "error";
        model.addAttribute("status", status);
        model.addAttribute("errorMessage", errorMessage);
        response.setStatus(status);
        logResult(Level.WARN, request, targetFragment, status, errorMessage);
        return targetFragment;
    }

    private String handleServerError(Exception e, HttpServletRequest request, HttpServletResponse response, Model model) {
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String errorMessage = "Ein unerwarteter Fehler ist aufgetreten.";
        String targetFragment = "error";
        model.addAttribute("status", status);
        model.addAttribute("errorMessage", errorMessage);
        response.setStatus(status);
        log.error(errorMessage, e);
        logResult(Level.WARN, request, targetFragment, status, errorMessage);
        return targetFragment;
    }

    private static void logResult(Level logLevel, HttpServletRequest request, String targetFragment, int status, String errorMessage) {
        log.atLevel(logLevel).log("EXCEPTION: Using {}.html page for: {} -> {} ({})",
                targetFragment, request.getRequestURI(), status, errorMessage);
    }

}
