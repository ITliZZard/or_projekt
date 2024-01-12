package fer.project.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Response> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        String message = "Letters and special characters are not allowed in numerical parameters.";
        return new ResponseEntity<>(new Response(400, message, null), responseHeaders, HttpStatus.BAD_REQUEST);
    }

    /*@ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Response> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        String message = "The path you have requested is not implemented.";
        return new ResponseEntity<>(new Response(501, message, null), responseHeaders, HttpStatus.NOT_IMPLEMENTED);
    }*/

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("index");
        // You can add objects to the model if needed
        // modelAndView.addObject("attribute", value);

        return new ResponseEntity<>(modelAndView, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Response> handleMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex) {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        String message = "Invalid request type.";
        return new ResponseEntity<>(new Response(405, message, null), responseHeaders, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Response> handleBadRequestExceptions(
            Exception ex) {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        String message = "Invalid request. Check your parameters.";
        return new ResponseEntity<>(new Response(400, message, null), responseHeaders, HttpStatus.BAD_REQUEST);
    }
}
