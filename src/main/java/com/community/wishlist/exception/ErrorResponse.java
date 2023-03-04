package com.community.wishlist.exception;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private Date timestamp;
    private String status;
    private String message;
    private String details;

}
