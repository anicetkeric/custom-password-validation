package com.passay.sample.custompasswordvalidation.model;

import com.passay.sample.custompasswordvalidation.annotation.PasswordValueMatch;
import com.passay.sample.custompasswordvalidation.annotation.ValidPassword;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <h2>UserData</h2>
 *
 * @author aek
 */
@PasswordValueMatch.List({
        @PasswordValueMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Passwords do not match!"
        )
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserData {


    @NonNull
    @NotBlank(message = "username is mandatory")
    private String username;

    @NotNull
    @NotEmpty
    @Email
    private String email;


    @ValidPassword
    @NonNull
    @NotBlank(message = "New password is mandatory")
    private String password;


    @ValidPassword
    @NonNull
    @NotBlank(message = "Confirm Password is mandatory")
    private String confirmPassword;
}


