package com.jblog.vo;

public class BlogVo {

	private String id;
	private String name;
	private String blogTitle;
	private String logoFile;
	
	public BlogVo() {
		
	}

	public BlogVo(String id, String blogTitle, String logoFile) {
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", name=" + name +", blogTitle=" + blogTitle + ", logoFile=" + logoFile + "]";
	}
	
}
