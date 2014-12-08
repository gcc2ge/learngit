package com.my.word;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringRegx {
	public static void main(String[] args){
		String s="${table:1,col:[name,sex,date,dep,beizu],datasource:t_tt,22}";
		findStr(s);
	}
	public static String getRegxString(String regx,String s){
		Pattern p=Pattern.compile(regx);
		Matcher m=p.matcher(s);
		return m.matches()?m.group(1):"";
	}
	public static Map<String,Object> findStr(String s){
//		Pattern p=Pattern.compile("\\$\\{.*?table\\W*:(\\d+).*\\}");
//		p=Pattern.compile("\\$\\{.*?col:\\[(.+?)\\].*\\}");
//		p=Pattern.compile("\\$\\{.*?datasource:(\\w+).*\\}");
		
		String table=getRegxString("\\$\\{.*?table\\W*:(\\d+).*\\}",s);
		String[] name=getRegxString("\\$\\{.*?col:\\[(.+?)\\].*\\}",s).split(",");
		String datasource=getRegxString("\\$\\{.*?datasource:(\\w+).*\\}",s);
		List<String> namelist=Arrays.asList(name);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("table",table);
		map.put("col",namelist);
		map.put("datasource",datasource);
		return map;
	
	}
}
