package com.example.ravi.repository;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ResponseMessage {

	private String request_id;

	private Integer code;

	private String message;

	private Boolean successful;

	private Integer valid;

	private Integer invalid;
	
	private Integer duplicates;


}
