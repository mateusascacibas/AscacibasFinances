package com.finances.AscacibasFinances.comuns;

public class ResponseMessage {

	private final boolean success;
	private final String message;

	public ResponseMessage(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

}
