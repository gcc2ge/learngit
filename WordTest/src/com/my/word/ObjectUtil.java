package com.my.word;

import java.lang.reflect.Field;

public class ObjectUtil {
	public static String getFiledString(Object o,String field){
		try {
			Class clzz=o.getClass();
			Field f=null;
			f=clzz.getDeclaredField(field);
			f.setAccessible(true);
			return f.get(o).toString();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
		return "";
	}
}
