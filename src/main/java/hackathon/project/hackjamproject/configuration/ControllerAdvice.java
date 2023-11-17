package hackathon.project.hackjamproject.configuration;

import hackathon.project.hackjamproject.domain.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodValidationException(
		MethodArgumentNotValidException exception
	) {
		Map<String, String> errorsMap = exception
			.getFieldErrors()
			.stream()
			.collect(
				Collectors.toMap(
					FieldError::getField,
					Objects.requireNonNull(
						DefaultMessageSourceResolvable::getDefaultMessage
					)
				)
			);

		return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> handleConstraintViolationException(
		ConstraintViolationException violationException
	) {
		Map<String, String> errorsMap = violationException
			.getConstraintViolations()
			.stream()
			.collect(
				Collectors.toMap(
					constraintViolation ->
						constraintViolation.getRootBeanClass().getName(),
					ConstraintViolation::getMessage
				)
			);

		return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleErrorResponse(
		RuntimeException exception
	) {
		ErrorResponse errorResponse = ErrorResponse
			.builder()
			.message(exception.getMessage())
			.throwableName(exception.getClass().getName())
			.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.build();

		return new ResponseEntity<>(
			errorResponse,
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
}
