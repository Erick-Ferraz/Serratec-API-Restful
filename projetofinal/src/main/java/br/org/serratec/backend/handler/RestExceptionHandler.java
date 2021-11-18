package br.org.serratec.backend.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.org.serratec.backend.exception.CpfException;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.exception.ErrorMessage;
import br.org.serratec.backend.exception.UsernameException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CpfException.class)
	public ResponseEntity<Object> handleCpfException (CpfException cex) {
		
		ErrorMessage erro = new ErrorMessage("CPF Exception", "400", cex.getMessage());
		
		return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmailException.class)
	public ResponseEntity<Object> handleEmailException (EmailException ex) {
		
		ErrorMessage erro = new ErrorMessage("Email Exception", "400", ex.getMessage());
		
		return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UsernameException.class)
	public ResponseEntity<Object> handleUsernameException (UsernameException ex) {
		
		ErrorMessage erro = new ErrorMessage("Username Exception", "400", ex.getMessage());
		
		return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
	}

}
