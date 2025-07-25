package com.streamix.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("data")
    private T data;

    @JsonProperty("message")
    private String message;

    @JsonProperty("error")
    private ErrorDetails error;

    @JsonProperty("pagination")
    private PaginationInfo pagination;

    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    @JsonProperty("timestamp")
    private Instant timestamp;

    @JsonProperty("traceId")
    private String traceId;

    @JsonProperty("version")
    private String version = "v1";

    private ApiResponse() {
        this.timestamp = Instant.now();
    }

    private ApiResponse(boolean success, T data, String message) {
        this();
        this.success = success;
        this.data = data;
        this.message = message;
    }


    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, "Success");
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, message);
    }


    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(true, null, "Success");
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, null, message);
    }

    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> response = new ApiResponse<>(false, null, null);
        response.error = new ErrorDetails("GENERAL_ERROR", message);
        return response;
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        ApiResponse<T> response = new ApiResponse<>(false, null, null);
        response.error = new ErrorDetails(code, message);
        return response;
    }

    public static <T> ApiResponse<T> validationError(String message, List<ValidationError> validationErrors) {
        ApiResponse<T> response = new ApiResponse<>(false, null, null);
        response.error = new ErrorDetails("VALIDATION_ERROR", message, validationErrors);
        return response;
    }

    public static <T> ApiResponse<T> error(String message, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(false, null, null);
        response.error = new ErrorDetails(status.name(), message);
        return response;
    }

    public static <T> ApiResponse<List<T>> paginated(List<T> data, PaginationInfo pagination) {
        ApiResponse<List<T>> response = success(data);
        response.pagination = pagination;
        return response;
    }

    public static <T> ApiResponse<List<T>> paginated(List<T> data, PaginationInfo pagination, String message) {
        ApiResponse<List<T>> response = success(data, message);
        response.pagination = pagination;
        return response;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private final ApiResponse<T> response;

        private Builder() {
            this.response = new ApiResponse<>();
        }

        public Builder<T> success(boolean success) {
            response.success = success;
            return this;
        }

        public Builder<T> data(T data) {
            response.data = data;
            return this;
        }

        public Builder<T> message(String message) {
            response.message = message;
            return this;
        }

        public Builder<T> error(ErrorDetails error) {
            response.error = error;
            response.success = false;
            return this;
        }

        public Builder<T> pagination(PaginationInfo pagination) {
            response.pagination = pagination;
            return this;
        }

        public Builder<T> metadata(Map<String, Object> metadata) {
            response.metadata = metadata;
            return this;
        }

        public Builder<T> traceId(String traceId) {
            response.traceId = traceId;
            return this;
        }

        public Builder<T> version(String version) {
            response.version = version;
            return this;
        }

        public ApiResponse<T> build() {
            return response;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorDetails {
        @JsonProperty("code")
        private String code;

        @JsonProperty("message")
        private String message;

        @JsonProperty("details")
        private String details;

        @JsonProperty("validationErrors")
        private List<ValidationError> validationErrors;

        public ErrorDetails() {
        }

        public ErrorDetails(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public ErrorDetails(String code, String message, List<ValidationError> validationErrors) {
            this.code = code;
            this.message = message;
            this.validationErrors = validationErrors;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public List<ValidationError> getValidationErrors() {
            return validationErrors;
        }

        public void setValidationErrors(List<ValidationError> validationErrors) {
            this.validationErrors = validationErrors;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ValidationError {
        @JsonProperty("field")
        private String field;

        @JsonProperty("message")
        private String message;

        @JsonProperty("rejectedValue")
        private Object rejectedValue;

        public ValidationError() {
        }

        public ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public ValidationError(String field, String message, Object rejectedValue) {
            this.field = field;
            this.message = message;
            this.rejectedValue = rejectedValue;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getRejectedValue() {
            return rejectedValue;
        }

        public void setRejectedValue(Object rejectedValue) {
            this.rejectedValue = rejectedValue;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PaginationInfo {
        @JsonProperty("page")
        private int page;

        @JsonProperty("size")
        private int size;

        @JsonProperty("totalElements")
        private long totalElements;

        @JsonProperty("totalPages")
        private int totalPages;

        @JsonProperty("first")
        private boolean first;

        @JsonProperty("last")
        private boolean last;

        @JsonProperty("hasNext")
        private boolean hasNext;

        @JsonProperty("hasPrevious")
        private boolean hasPrevious;

        public PaginationInfo() {
        }

        public PaginationInfo(int page, int size, long totalElements, int totalPages) {
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.first = page == 0;
            this.last = page == totalPages - 1;
            this.hasNext = page < totalPages - 1;
            this.hasPrevious = page > 0;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(long totalElements) {
            this.totalElements = totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public boolean isHasPrevious() {
            return hasPrevious;
        }

        public void setHasPrevious(boolean hasPrevious) {
            this.hasPrevious = hasPrevious;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorDetails getError() {
        return error;
    }

    public void setError(ErrorDetails error) {
        this.error = error;
    }

    public PaginationInfo getPagination() {
        return pagination;
    }

    public void setPagination(PaginationInfo pagination) {
        this.pagination = pagination;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
