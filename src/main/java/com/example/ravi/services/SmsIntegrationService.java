package com.example.ravi.services;

import com.example.ravi.payloads.responses.Response;
import com.example.ravi.repository.MessageDetail;
import com.example.ravi.repository.MessageDetailResponse;
import com.example.ravi.repository.ReceivedMessage;
import com.example.ravi.repository.ResponseMessage;

import javax.management.ServiceNotFoundException;

public interface SmsIntegrationService {

	public ResponseMessage getResponseFromDownstream(ReceivedMessage request) throws ServiceNotFoundException;
	public MessageDetailResponse saveMessage(MessageDetail request);

    Response<com.example.ravi.models.ResponseMessage> sendMessage(com.example.ravi.models.ReceivedMessage messageRequestDto);

}
