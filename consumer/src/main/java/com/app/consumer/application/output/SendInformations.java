package com.app.consumer.application.output;

public interface SendInformations {
	void sendEmail(String sendTo, String subject, String text);
}
