package com.itau.system.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String msg){
        super(msg);
        log.error("User not found, " + msg);
    }
}
