package study.basiccrud.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(HttpServletRequest req, IllegalArgumentException e) {
        log.info("requested '{}'" + req.getRequestURI());
        log.error("illegalArgument request", e);
        return ResponseEntity.notFound().build();
    }
}
