package com.my.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Map;

import com.my.demo.ScreenInfo;

public class SerializeInfo {
	public static Map<String, ScreenInfo> load(){
		URL url=SerializeInfo.class.getResource("info.out");
	    File file = new File(url.getPath());
	    Map<String, ScreenInfo>  list=null;
		 try {
			ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
		    Object newobject= oin.readObject(); // 没有强制转换到Person类型
		    list=(Map<String, ScreenInfo>) newobject;
		    oin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public static void save(Map<String, ScreenInfo> map){
		URL url=SerializeInfo.class.getResource("info.out");
	    File file = new File(url.getPath());
//
		    ObjectOutputStream oout;
			try {
				oout = new ObjectOutputStream(new FileOutputStream(file));
				oout.writeObject(map);
				oout.flush();
				oout.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args){
		SerializeInfo.save(null);
	}
}
