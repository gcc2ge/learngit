package com.my.demo;

import java.io.Serializable;

public class ScreenInfo implements Serializable {
	private String id;///自动生成 方案id
	private String bh;//屏幕编号
	private String pmhtml;//屏幕html
	private String name;//方案名称
	private String content;//html内容
	
	private String createtime;//创建时间
	
	public ScreenInfo(){}
	public ScreenInfo(String id,String pmhtml,String name,String content,String createtime){
		this.id=id;
		this.pmhtml=pmhtml;
		this.name=name;
		this.content=content;
		this.createtime=createtime;
	}
	public String getPmhtml() {
		return pmhtml;
	}
	public void setPmhtml(String pmhtml) {
		this.pmhtml = pmhtml;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
