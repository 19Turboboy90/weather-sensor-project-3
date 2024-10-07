package ru.zhrinov.MeteorologicalSensor.util.excetions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class ErrorObjectResponse {
    private String message;

    public static void showExceptionCreateObject(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(",");
            }
            throw new ObjectNotCreatedException(errorMsg.toString());
        }
    }
}
