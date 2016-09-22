package com.example.sax;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class SteamTools {
	
	public	static String readStream(InputStream in) throws Exception{
		//定义一个内存输出流
		ByteArrayOutputStream baops = new ByteArrayOutputStream();
		int length = -1;
		byte[] data_byte = new byte[1024];
		while((length=in.read(data_byte))!=-1){
			baops.write(data_byte,0,length);
		}
		in.close();
		String content = new String(baops.toByteArray(),"UTF-8");	
		return content;
		
	}
	
}
