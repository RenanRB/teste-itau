package com.app.consumer.domain.service;

import com.app.consumer.application.input.UserController;
import com.app.consumer.application.output.SendInformations;
import com.app.consumer.domain.model.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class UserService implements UserController {

    private String sendTo;
	private SendInformations sendInformations;
	
	@Override
	public void sendUserConfirmation(User user) {
		log.info("A new email will be sent to {}", sendTo);
		sendInformations.sendEmail(sendTo, "New Register Spring boot", "There is a new register to validate: " + user.toString());
	}

}
