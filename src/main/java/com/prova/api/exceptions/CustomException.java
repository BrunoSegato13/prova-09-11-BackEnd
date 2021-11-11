package com.prova.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomException {

    private Integer status;
    private String message;

}
