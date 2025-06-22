package me.danhthanhf.distant_class.exception.handler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import me.danhthanhf.distant_class.common.constant.ErrorConstants;
import me.danhthanhf.distant_class.common.response.ApiResponse;
import me.danhthanhf.distant_class.exception.type.ResourceConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(ErrorConstants.ERR_INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }


    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceClosedException(ResourceConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage()));
    }

    // argument validation exception
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    //Handle validation exception
    // occur when using @Valid or @Validated with @RequestBody or @ModalAttribute.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<Map<String, String>>>> handleMethodArgument(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = getValidationErrors(ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ApiResponse.error(ErrorConstants.ERR_VALIDATION_FAILED, errors));
    }

    // occur when using @Valid or @Validated with @RequestParam, @PathVariable, or @RequestHeader..
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleConstraintViolationException(ConstraintViolationException ex) {
        var errors = getValidationErrors(ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ApiResponse.error(ErrorConstants.ERR_VALIDATION_FAILED, errors));
    }

    // Handles validation errors, returning them as {"field": "fieldName", "message": "error message"}.
    private List<Map<String, String>> getValidationErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> Map.of(
                                "field", error.getField(),
                                "message", Objects.requireNonNull(error.getDefaultMessage())
                        )
                )
                .toList();
    }

    private List<Map<String, String>> getValidationErrors(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .map(vio -> Map.of(
                                "field", vio.getPropertyPath().toString(),
                                "message", vio.getMessage()
                        )
                ).toList();
    }


}
