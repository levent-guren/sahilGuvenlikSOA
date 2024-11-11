package tr.gov.sg.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> girisHatasi() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

}
