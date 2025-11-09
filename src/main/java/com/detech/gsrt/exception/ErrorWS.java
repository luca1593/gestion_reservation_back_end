package com.detech.gsrt.exception;

import com.detech.gsrt.config.Views;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorWS implements ErrorResponse {
    @JsonView(Views.Public.class)
    private int code;
    @JsonView(Views.Public.class)
    private String message;
    @JsonView(Views.Public.class)
    private List<String> erreurs;
    @JsonView(Views.Public.class)
    private HttpStatusCode statusCode;

    @Override
    public HttpStatusCode getStatusCode () {
        return statusCode;
    }

    @Override
    public ProblemDetail getBody () {
        return ProblemDetail.forStatus(statusCode);
    }
}
