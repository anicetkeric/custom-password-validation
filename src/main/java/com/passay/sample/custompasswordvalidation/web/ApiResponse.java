package com.passay.sample.custompasswordvalidation.web;

import lombok.*;

/**
 * <h2>BaseResponse</h2>
 *
 * @author aek
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiResponse {

    private Object data;
    private String message;
    private boolean error = true;

    public ApiResponse(Object data, String message){
        this.data = data;
        this.message = message;
    }
}
