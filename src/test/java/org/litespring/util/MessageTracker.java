package org.litespring.util;

import java.util.ArrayList;
import java.util.List;

public class MessageTracker {
	
	public static List<String> messages = new ArrayList<>();
	
	public static void addMsg(String msg) {
		messages.add(msg);
	}
	
	public static void clearMsgs() {
		messages.clear();
	}
	
	public static List<String> getMsgs() {
		return messages;
	}
}
