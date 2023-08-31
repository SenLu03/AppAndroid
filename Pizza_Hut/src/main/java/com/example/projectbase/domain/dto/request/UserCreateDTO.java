package com.example.projectbase.domain.dto.request;
import com.example.projectbase.constant.ErrorMessage;
import lombok.Setter;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
public class UserCreateDTO {

    @NotBlank(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private String fullName;

    @NotBlank(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private String username;

    @Valid
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@])[0-9a-zA-Z@]{8,16}$", message = ErrorMessage.INVALID_FORMAT_PASSWORD)
    private String password;

    @Valid
    @Pattern(regexp = "\\d{10}", message = ErrorMessage.ERROR_FORMAT_PHONE)
    private String phoneNumber;

    @Valid
    @Pattern(regexp = "^[\\w.%+-]+@gmail\\.com$", message = ErrorMessage.ERROR_FORMAT_EMAIL)
    private String email;

    private String gender;

    @NotBlank(message = "address is not empty and null")
    private String address;

    private String birthday;

}
