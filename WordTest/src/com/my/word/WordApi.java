package com.my.word;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;

import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

public class WordApi {
	static final ReentrantLock lock = new ReentrantLock();

	public static void replace(String file,String filesaveas, Map<String,Object> source) {
		StudyJacob s = new StudyJacob();
		try {
			lock.lock();
			ComThread.InitSTA();
			s.openDocument(file);
			Iterator<String> it= source.keySet().iterator();
			while(it.hasNext()){
				String key=it.next();
				replaceInLine(s, key, source.get(key));
			}
			s.save(filesaveas);
		} finally {
			s.closeDocument();
			s.close();
			ComThread.Release();
			lock.unlock();
		}
	}

	private static void replaceInLine(StudyJacob s, String text, Object obj) {
		if (obj instanceof String) {
			replaceText(s, "#"+text+"#", obj);
		} else if (obj instanceof List) {
			replaceList(s, text, obj);
		}
	}

	private static void replaceText(StudyJacob s, String text, Object obj) {
		if(s.replaceText(text, obj.toString())){
			s.moveStart();
		}
	}

	private static void replaceList(StudyJacob s, String text, Object obj) {
		if (s.findRegx("$\\{*\\}")) {
			String stext=s.getText().toString();
			if(!hashDatascource(stext,text)){
				return ;
			}
			List datasource = (List) obj;
			Map<String, Object> map = StringRegx
					.findStr(s.getText().toString());
			int tid = Integer.parseInt(map.get("table").toString());// table id
			Dispatch d = s.getTable(tid);
			List<String> filedlist = (List<String>) map.get("col");// filed
			for (int r = 1; r <= datasource.size(); r++) {
				Object o = datasource.get(r - 1);
				s.addRow(tid);
				for (int j = 0; j < filedlist.size(); j++) {
					try {
						s.setCellString(tid, r + 1, j + 1,
								ObjectUtil.getFiledString(o, filedlist.get(j)));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			s.moveStart();
			replaceText(s, stext, "");
		}
	}
	private static boolean hashDatascource(String source,String text){
		java.util.regex.Pattern p=java.util.regex.Pattern.compile("\\$\\{.*datasource:"+text+".*\\}");
		Matcher m=p.matcher(source);
		return m.matches();
	}
}
