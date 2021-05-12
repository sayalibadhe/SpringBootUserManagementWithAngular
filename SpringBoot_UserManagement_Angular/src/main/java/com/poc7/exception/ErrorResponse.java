package com.poc7.exception;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.poc7.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ErrorResponse {
	private Date timestamp;
    private String message;
    private String details;

    

}
