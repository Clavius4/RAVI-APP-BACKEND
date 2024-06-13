package com.example.ravi.models;

/**
 * 
 * @author admin
 *
 */
public class MessageDetailResponse {

	private Integer id;
	private String request_id;
	private String message;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
