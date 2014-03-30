package com.martin.packetchat;

public class Protocol {
	public Action getAction(String msg) {
		try {
			if (msg.substring(0, 10).contains("LOGIN")) {
				return Action.LOGIN;
			}
			if (msg.substring(0, 10).contains("LOGOUT")) {
				return Action.LOGOUT;
			}
			if (msg.substring(0, 10).contains("MESSAGE")) {
				return Action.MESSAGE;
			}
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return Action.UNDEFINED;
	}

	@SuppressWarnings("incomplete-switch")
	public String encodeMessage(Action action, String msg) {
		String response = null;
		switch (action) {
		case LOGIN:
			response = fillResponse("LOGIN", msg);
			break;
		case LOGOUT:
			response = fillResponse("LOGOUT", msg);
			break;
		case MESSAGE:
			response = fillResponse("MESSAGE", msg);
			break;
		}
		return response;
	}

	private String fillResponse(String action, String msg) {
		String filler = "";
		for (int i = 0; i < 10 - action.length(); i++) {
			filler += " ";
		}
		return action + filler + msg;
	}
	
	public String getContent(String msg) {
		return msg.substring(10);
	}
}
