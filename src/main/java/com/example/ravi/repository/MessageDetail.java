package com.example.ravi.repository;

/**
 * ReceivedMessage .
 * 
 * @author admin
 *
 */
//@Getter
//@Setter
public class MessageDetail {

    /**
     * request_id .
     * 
     */
    private String request_id;
    
    /**
     * recipient_id .
     * 
     */
    private String recipient_id;
    
    /**
     * dest_addr .
     * 
     */
    private String dest_addr;
    
    /**
     * recipients .
     * 
     */
    private String status;

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getRecipient_id() {
		return recipient_id;
	}

	public void setRecipient_id(String recipient_id) {
		this.recipient_id = recipient_id;
	}

	public String getDest_addr() {
		return dest_addr;
	}

	public void setDest_addr(String dest_addr) {
		this.dest_addr = dest_addr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
