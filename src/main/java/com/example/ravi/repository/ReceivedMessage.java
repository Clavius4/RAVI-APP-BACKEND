package com.example.ravi.repository;

import java.util.List;


public class ReceivedMessage {


	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * source_addr .
     *
     */
    private String source_addr;

    /**
     * encoding .
     *
     */
    private Integer encoding;

    /**
     * message .
     *
     */
    private String message;

	private int appId = 1893;

    /**
     * recipients .
     *
     */
    private List<Recipient> recipients;

    public ReceivedMessage() {
        this.appId = appId;
    }


    public String getSource_addr() {
		return source_addr;
	}

	public void setSource_addr(String source_addr) {
		this.source_addr = source_addr;
	}

	public Integer getEncoding() {
		return encoding;
	}

	public void setEncoding(Integer encoding) {
		this.encoding = encoding;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Recipient> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<Recipient> recipients) {
		this.recipients = recipients;
	}

}
