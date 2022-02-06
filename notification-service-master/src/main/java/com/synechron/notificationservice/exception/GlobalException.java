package com.synechron.notificationservice.exception;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> handleInvalidCustomerId(Exception exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(404, exception.getMessage()));
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Error> handleInvalidMethodArgument(MethodArgumentNotValidException ex){
		  List<String> messages=ex.getAllErrors().stream().map(DefaultMessageSourceResolvable :: getDefaultMessage).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(400,messages));
	}

}

@Setter
@Getter
@NoArgsConstructor
class Error {
    private int code;
    private List<String> messages = new ArrayList<>();


    public Error(int code, String message){
        this.code = code;
        this.messages.add(message);
    }

    public Error(int code, List<String> listOfErrors){
        this.code = code;
        this.messages = listOfErrors;
    }


}
