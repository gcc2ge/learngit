package com.my.word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TT {
	public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pydw", "中");
		map.put("tzrs", "过");
		map.put("ztdw", "人");
		map.put("cfgj", "名");
		map.put("zwts", "接");
		map.put("jingfei", "放");
		map.put("lxtel", "军");
		map.put("lxr", "死");
		List<User> list=new ArrayList<User>();
		list.add(new User());
		list.add(new User());
		list.add(new User());
		list.add(new User());
		list.add(new User());
		list.add(new User());
		list.add(new User());
		map.put("tt", list);
		WordApi.replace("c:/1.doc","c:/2.doc", map);
	}
}
