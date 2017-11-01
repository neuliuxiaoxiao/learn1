package com.neu.liuxi.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIODemo {

	public static void main(String[] args) throws IOException {
		testFileNIO();
	}
	
	public static void testFileNIO() throws IOException{
		//声明源文件和目标文件
		FileInputStream fis = new FileInputStream("src/com/neu/liuxi/io/txt.txt");
		FileOutputStream fos =new FileOutputStream("src/com/neu/liuxi/io/txt-nio.txt");
		//获得传输通道channel
		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();
		//获得容器buffer
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while(true){
			//判断是否读完文件
			int eof = inChannel.read(buffer);
			if(eof==-1)
				break;
			 //重设一下buffer的position=0，limit=position
            buffer.flip();
          //开始写
            outChannel.write(buffer);
            //写完要重置buffer，重设position=0,limit=capacity
            buffer.clear();
		}
		inChannel.close();
        outChannel.close();
        fis.close();
        fos.close();
		
	}
}
