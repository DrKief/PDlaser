package pdl.backend;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * Global centralized exception handler.
 * Translates backend exceptions into standardized RESTful "ProblemDetail" JSON responses.
 */
@RestControllerAdvice
public class ErrorHandler {

  private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

  // --- Custom Domain Exceptions (Sealed class hierarchy) ---

  public abstract static sealed class DomainException
    extends RuntimeException
    permits RecordNotFoundException, BadRequestException, UnsupportedFileException, ForbiddenException, DuplicateImageException 
  {
    protected DomainException(String message) {
      super(message, null, false, false);
    }
  }

  public static final class RecordNotFoundException extends DomainException {
    public RecordNotFoundException(String message) {
      super(message);
    }
  }

  public static final class BadRequestException extends DomainException {
    public BadRequestException(String message) {
      super(message);
    }
  }

  public static final class UnsupportedFileException extends DomainException {
    public UnsupportedFileException(String message) {
      super(message);
    }
  }

  public static final class ForbiddenException extends DomainException {
    public ForbiddenException(String message) {
      super(message);
    }
  }

  public static final class DuplicateImageException extends DomainException {
    public DuplicateImageException(String message) {
      super(message);
    }
  }

  /**
   * Helper method to build a standard ProblemDetail object with an attached Trace ID for logging.
   */
  private ProblemDetail buildProblemDetail(HttpStatus status, String detail) {
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(status, detail);
    String traceId = UUID.randomUUID().toString();
    pd.setProperty("traceId", traceId);
    log.error("API Error [TraceID: {}]: Status {} - {}", traceId, status.value(), detail);
    return pd;
  }

  // --- Exception Handlers ---

  @ExceptionHandler(ErrorHandler.DuplicateImageException.class)
  public ProblemDetail handleDuplicate(ErrorHandler.DuplicateImageException ex) {
    return buildProblemDetail(HttpStatus.CONFLICT, ex.getMessage());
  }

  @ExceptionHandler(ErrorHandler.RecordNotFoundException.class)
  public ProblemDetail handleRecordNotFound(ErrorHandler.RecordNotFoundException ex) {
    return buildProblemDetail(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(ErrorHandler.BadRequestException.class)
  public ProblemDetail handleBadRequest(ErrorHandler.BadRequestException ex) {
    return buildProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ExceptionHandler(ErrorHandler.UnsupportedFileException.class)
  public ProblemDetail handleUnsupportedFile(ErrorHandler.UnsupportedFileException ex) {
    return buildProblemDetail(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ProblemDetail handleMaxSizeException(MaxUploadSizeExceededException ex) {
    return buildProblemDetail(
      HttpStatus.CONTENT_TOO_LARGE,
      "File too large. Maximum upload size exceeded."
    );
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ProblemDetail handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
    return buildProblemDetail(HttpStatus.NOT_FOUND, "Image not found");
  }

  @ExceptionHandler(Exception.class)
  public ProblemDetail handleGenericException(Exception ex) {
    String traceId = UUID.randomUUID().toString();
    log.error("Unhandled server exception [TraceID: {}]", traceId, ex);
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(
      HttpStatus.INTERNAL_SERVER_ERROR,
      "Internal server error"
    );
    pd.setProperty("traceId", traceId);
    return pd;
  }
}