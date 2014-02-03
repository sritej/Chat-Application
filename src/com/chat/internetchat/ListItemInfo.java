package com.chat.internetchat;

public class ListItemInfo {
	private String KEY_SONG = "Parent_node"; // parent node
	private String KEY_ID = "id";
	private String KEY_NAME = "name";
	private String KEY_LATEST_MSG = "latest_msg";
	private String KEY_TIME = "time";
	private String KEY_THUMB_URL = "thumb_url";
	private int flag;

	
	public ListItemInfo(String KEY_SONG, String KEY_ID, String KEY_NAME, String KEY_LATEST_MSG,
			String KEY_TIME, String KEY_THUMB_URL, int flag) {
		this.KEY_SONG = KEY_SONG;
		this.KEY_ID = KEY_ID;
		this.KEY_NAME = KEY_NAME;
		this.KEY_LATEST_MSG = KEY_LATEST_MSG;
		this.KEY_TIME = KEY_TIME;
		this.KEY_THUMB_URL = KEY_THUMB_URL;
		this.flag = flag;
	}

	public String getSong() {
		return this.KEY_SONG;
	}

	public String getID() {
		return this.KEY_ID;
	}

	public String getName() {
		return this.KEY_NAME;
	}

	public String getLatestMessage() {
		return this.KEY_LATEST_MSG;
	}
	
	public String getTime() {
		return this.KEY_TIME;
	}

	public String getThumbURL() {
		return this.KEY_THUMB_URL;
	}
	
	public int getFlag() {
		return this.flag;
	}
}
