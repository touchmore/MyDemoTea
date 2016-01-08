package com.kb.mydemotea.domain;

public class Student {
	private int id;
	private int newsid;
	private String title;
	private String source;
	private String description;
	private String wap_thumb;
	private String create_time;
	private String nickname;

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(int newsid, String title, String source, String wap_thumb,
			String create_time, String nickname) {
		super();
		this.newsid = newsid;
		this.title = title;
		this.source = source;
		this.wap_thumb = wap_thumb;
		this.create_time = create_time;
		this.nickname = nickname;
	}

	public Student(int id, int newsid, String title, String source,
			String description, String wap_thumb, String create_time,
			String nickname) {
		super();
		this.id = id;
		this.newsid = newsid;
		this.title = title;
		this.source = source;
		this.description = description;
		this.wap_thumb = wap_thumb;
		this.create_time = create_time;
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNewsid() {
		return newsid;
	}

	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWap_thumb() {
		return wap_thumb;
	}

	public void setWap_thumb(String wap_thumb) {
		this.wap_thumb = wap_thumb;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
