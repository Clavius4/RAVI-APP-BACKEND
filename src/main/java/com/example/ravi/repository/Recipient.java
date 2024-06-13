package com.example.ravi.repository;

/**
 * Recipient .
 *
 * @author admin
 *
 */
//@Getter
//@Setter
public class Recipient {

	/**
	 * recipient_id .
	 *
	 */
	private Integer recipient_id;

	/**
	 * dest_addr.
	 */
	private String dest_addr;

	public Recipient(Integer recipient_id, String dest_addr) {
		this.recipient_id = recipient_id;
		this.dest_addr = dest_addr;
	}


	public Integer getRecipient_id() {
		return recipient_id;
	}

	public void setRecipient_id(Integer recipient_id) {
		this.recipient_id = recipient_id;
	}

	public String getDest_addr() {
		return dest_addr;
	}

	public void setDest_addr(String dest_addr) {
		this.dest_addr = dest_addr;
	}



}
