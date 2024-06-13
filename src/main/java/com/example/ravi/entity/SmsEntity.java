package com.example.ravi.entity;
import com.example.ravi.constants.SystemConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * SmsEntity .
 * 
 * @author admin
 *
 */
@Entity
@Table(name = "SMSCALLBACK2")
public class SmsEntity {


	/**
     * id .
     */
    @Id
    @SequenceGenerator(name = "pkgen", allocationSize = 1, initialValue = 1,sequenceName = SystemConstants.SMS_SEQUENCE)
    @GeneratedValue(generator = "pkgen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    /**
     * request_id .
     */
    @Column(name = "request_id")
    private String request_id;

    /**
     * recipient_id .
     */
    @Column(name = "recipient_id")
    private String recipient_id;

    /**
     * dest_addr .
     */
    @Column(name = "dest_addr")
    private String dest_addr;


    /**
     * status .
     */
    @Column(name = "Status")
    private String status;


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
