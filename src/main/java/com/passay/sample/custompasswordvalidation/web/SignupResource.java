package com.passay.sample.custompasswordvalidation.web;

import com.passay.sample.custompasswordvalidation.model.UserData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/api")
public class SignupResource {

    @PostMapping(value = "/registration")
    public ResponseEntity<ApiResponse> registration(@Valid @RequestBody UserData userData) {

        return new ResponseEntity<>(
                new ApiResponse(userData, "success", false), HttpStatus.OK);

    }
}
