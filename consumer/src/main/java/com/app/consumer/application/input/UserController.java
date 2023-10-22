package com.app.consumer.application.input;

import com.app.consumer.domain.model.User;

public interface UserController {
	void sendUserConfirmation(User user);
}
