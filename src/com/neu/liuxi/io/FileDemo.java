package com.neu.liuxi.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.neu.liuxi.Person;


public class FileDemo {

	public static void main(String[] args) throws IOException {
		//博客地址
		//http://blog.csdn.net/nightcurtis/article/details/51324105
		/*testFileInputStream();
		testFileReader();
		testFileOutputStream();
		testFileWriter();
		testBufferedStream();*/
		//testInputStreamReader();
		//testWriteObject();
		testReadObject();
	}
	private static void testFileReader() throws IOException{
		 FileReader fis=null;
	        try {
	            //创建字节输入流
	            fis=new FileReader("src/com/neu/liuxi/io/txt.txt");
	            //创建一个长度为1024的竹筒
	            char[] b=new char[1024];
	            //用于保存的实际字节数
	            int hasRead=0;
	            //使用循环来重复取水的过程
	            while((hasRead=fis.read(b))>0){
	                //取出竹筒中的水滴（字节），将字节数组转换成字符串进行输出
	                System.out.print(new String(b,0,hasRead));
	            }
	        }catch (IOException e){
	            e.printStackTrace();
	        }finally {
	            fis.close();
	        }
	}
	
	private static void testFileInputStream() throws IOException{
		FileInputStream fis = null;
		try {
			//fis = new FileInputStream("e:\\tomtop\\learn\\src\\com\\neu\\liuxi\\io\\txt.txt");
			//相对路径的根目录是project的根文件夹
			fis = new FileInputStream("src/com/neu/liuxi/io/txt.txt");
			byte[] b = new byte[1024];
			int hasRead=0;
			while((hasRead=fis.read(b))>0){
				System.out.println(new String(b,0,hasRead));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fis.close();
		}
	}
	private static void testFileOutputStream() throws IOException{
		FileInputStream fis =null;
		FileOutputStream fos =null;
		try {
			fis = new FileInputStream("src/com/neu/liuxi/io/txt.txt");
			fos = new FileOutputStream("src/com/neu/liuxi/io/txt-copy1.txt");
			byte[] b = new byte[1024];
			int hasRead=0;
			while((hasRead=fis.read(b))>0){
				fos.write(b, 0, hasRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fis.close();
			fos.close();
		}
	} 
	private static void testFileWriter() throws IOException{
		FileReader fis =null;
		FileWriter fos =null;
		try {
			fis = new FileReader("src/com/neu/liuxi/io/txt.txt");
			fos = new FileWriter("src/com/neu/liuxi/io/txt-copy.txt");
			char[] b=new char[1024];
			int hasRead=0;
			while((hasRead=fis.read(b))>0){
				fos.write(b, 0, hasRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			fis.close();
			fos.close();
		}
	} 
	private static void testBufferedStream() throws IOException{
		 FileInputStream fis=null;
	        FileOutputStream fos=null;
	        BufferedInputStream bis=null;
	        BufferedOutputStream bos=null;
	        try {
	            //创建字节输入流
	        	fis = new FileInputStream("src/com/neu/liuxi/io/txt.txt");
	            //创建字节输出流
				fos = new FileOutputStream("src/com/neu/liuxi/io/txt-copy2.txt");
				//创建字节缓存输入流
	            bis=new BufferedInputStream(fis);
	            //创建字节缓存输出流
	            bos=new BufferedOutputStream(fos);

	            byte[] b=new byte[1024];
	            int hasRead=0;
	            //循环从缓存流中读取数据
	            while((hasRead=bis.read(b))>0){
	                //向缓存流中写入数据，读取多少写入多少
	                bos.write(b,0,hasRead);
	            }
	        }catch (IOException e){
	            e.printStackTrace();
	        }finally {
	            bis.close();
	            bos.close();
	        }
	}
	private static void testInputStreamReader() throws IOException{
		try {
            // 将System.in对象转化为Reader对象
            InputStreamReader reader=new InputStreamReader(System.in);
            //将普通的Reader包装成BufferedReader
            BufferedReader bufferedReader=new BufferedReader(reader);
           String buffer=null;
           while ((buffer=bufferedReader.readLine())!=null){
            // 如果读取到的字符串为“exit”,则程序退出
               if(buffer.equals("exit")){
                   System.exit(1);
               }
               //打印读取的内容
               System.out.print("输入内容："+buffer);
           }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
        }
	}
	private static void testWriteObject() throws IOException{
		OutputStream outputStream=null;
        BufferedOutputStream buf=null;
        ObjectOutputStream obj=null;
        try {
            //序列化文件輸出流
            outputStream=new FileOutputStream("src/com/neu/liuxi/io/myfile.tmp");
            //构建缓冲流
            buf=new BufferedOutputStream(outputStream);
            //构建字符输出的对象流
            obj=new ObjectOutputStream(buf);
            //序列化数据写入
            obj.writeObject(new Person("A", 21));//Person对象
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
        	obj.close();
        }
	}
	 private static void testReadObject() throws IOException {
	        try {
	            InputStream inputStream=new FileInputStream("src/com/neu/liuxi/io/myfile.tmp");
	            //构建缓冲流
	            BufferedInputStream buf=new BufferedInputStream(inputStream);
	            //构建字符输入的对象流
	            ObjectInputStream obj=new ObjectInputStream(buf);
	            Person tempPerson=(Person)obj.readObject();
	            System.out.println("Person对象为："+tempPerson);
	            //关闭流
	            obj.close();
	            buf.close();
	            inputStream.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
}
