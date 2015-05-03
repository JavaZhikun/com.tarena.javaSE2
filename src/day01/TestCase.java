package day01;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class TestCase
{
	//@Test
	public void testPM25(){
		String pm25 = "海淀:50,亚运村:30,门头沟:40,"+
				"崇文门:200,西单:230,海淀:180,亚运村:100";
		String[] str = pm25.split("[:,]");
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(int i = 0 ; i < str.length; i+=2){
			String location = str[i];
			int value = Integer.parseInt(str[i+1]);
			Integer v = map.get(location);
			map.put(location, v==null?value: Math.max(value, v));
		}
		System.out.println(map);
		/**
		 * 自定义格式输出，需要实用map迭代
		 * 1.使用key进行迭代，keys就是全部key的集合，<String>是
		 * 		key的泛型
		 */
		
		Set<String> keys = map.keySet();
		for(String key : keys){
			int val = map.get(key);
			System.out.println(key+":"+ val);
		}
		
		System.out.println("-----------------");
		//1.使用key-value对（Entry）进行迭代
		//Set的每个元素是Entry，Entry中包含key和value
		//<String,Integer>分别是key  value的泛型
		Set<Entry<String,Integer>> entries = map.entrySet();
		for(Entry<String,Integer> entry : entries){
			String key = entry.getKey();
			int val =  entry.getValue();
			System.out.println(key +":" + val);
		}
	}
	
	/**
	 * 路径分隔符
	 */
	//@Test
	public void testFile(){
		//File.separator 值会随操作变化
		//windows: \  Linux|Unix : /
		String filename = "demo" + File.separator + "test.txt";
		System.out.println(filename);
		String file ="demo/test.txt";
		System.out.println(file);
	}
	
	/**
	 * File 类可以代表文件，代表目录
	 * File 类有3个重载构造器
	 *    new File("文件夹、目录")
	 *    new File("父目录"，“文件夹、目录”)
	 *    new File(File 父目录， “文件夹、目录”)
	 * 创建File类型的对象
	 */
	
	//@Test
	public void testFile2(){
		File dir = new File("C:/windows");
		File file = new File("D:/error.log");
		//                  父目录            子目录
		File dir2 = new File("C:","windows");
		File file2 = new File(dir,"error.log");
		System.out.println(file);
		System.out.println(file2);
		
	}
	
	/**
	 * 检查文件、文件夹是否已经存在
	 */
	//@Test
	public void testExists(){
		//new File() 只是创建内存对象
	    //不代表磁盘上使用有对应的文件、文件夹
		//更不是直接在磁盘上创建了文件！
		File file = new File("c:/b.txt");
		System.out.println(file.isFile());//false
		System.out.println(file.length());//0
		
		//检查file对象指向的磁盘上使用存在
		//文件，文件夹
		System.out.println(file.exists());//false
		//在磁盘上不存在 c:/b.txt 的文件、文件夹
		//内存中有file对象，但磁盘上没有这个文件夹或文件
	}
	
	/**
	 * 创建新闻文件
	 * @throws IOException 
	 */
	//@Test
	public void testCreateNewFile() throws IOException{
		File file = new File("C:/", "demo.txt");
		System.out.println(file.exists());//false
		//在磁盘上创建文件，成功返回true
		boolean ok = file.createNewFile();
		System.out.println(ok);//true
		
		//无权限情况，方法执行异常
		//路径错误，也会抛出异常
		File file1 = new File("E:/", "demo.txt");
		ok = file1.createNewFile();
		System.out.println(ok);//true
	}
	
	/**
	 * 删除delete
	 * C：/
	 *  *   |-- demo1     //空文件夹
	 *  	|-- demo.txt  //文件
	 *  	|-- demo2     //文件夹，是有文件的文件夹
	 *             |-- demo.txt 
	 *  删除demo01可以返回true
	 *  删除demo.txt 可以返回true
	 *  删除demo02 返回false
	 */
	//@Test
	public void testDelete(){
		File demo01 = new File("C:/","demo01");
		File demotxt = new File("C:/","demo.txt");
		File demo02 = new File("C:/","demo02");
		boolean ok = demo01.delete();
		System.out.println(ok);//true
		ok = demotxt.delete();
		System.out.println(ok);//true
		ok = demo02.delete();
		System.out.println(ok);//false
	}
	
	/**
	 * 检查一个文件对象是否是文件夹
	 * C：/
	 *    |--   demo   //文件夹
	 *    |--   demo.txt   //文件
	 */
	//@Test
	public void testIsDir(){
		File f1 = new File("c:/","demo02");
		File f2 = new File("c:/","demo.txt");
		
		//isDirectory()  当前file对象是否是文件夹
		System.out.println(f1.isDirectory());
		System.out.println(f2.isDirectory());
	}
	
	/**
	 * 创建文件夹
	 */
	//@Test
	public void testMkdir(){
		//磁盘上还没有文件夹时候才能创建
		File f = new File("C:/TOM");
		System.out.println(f.exists());//false
		boolean ok = f.mkdir();
		System.out.println(ok);//true
	}
	
	/**
	 * 创建系列路径
	 * C:/
	 *  |-- data
	 *       |--  income
	 *               |--  fee
	 * "d:/data/income/fee"
	 * 调用一次mkdirs就可以连续系列路径            
	 */
	
	//@Test
	public void testMkdirs(){
		String path ="C:/data/income/fee";
		File file = new File(path);
		boolean b = file.mkdirs();
		System.out.println(b);
	}
	
	/**
	 * 相对路径问题
	 *   将从根目录书写的绝对路径称为绝对路径
	 *   从当前目录开始书写的是相对路径
	 *   path = "data/in/fee"
	 *   file = new File(path)
	 *   file.mkdirs()
	 *   Eclipse中相对路径是当前项目文件夹开始的
	 *   D：/workspace
	 *        |--JSD
	 *        
	 *        刷新文件夹才能看见
	 */
	//@Test
	public void testPath(){
		String path = "data/in/fee";
		File file = new File(path);
		boolean b = file.mkdirs();
		System.out.println(b);
	}
	
	/**
	 * 列文件夹的目录
	 */
	
	//@Test
	public void testListFiles(){
		//创建一个File对象，这个对象对应一个目录
		File dir = new File("C:/windows");
		File[] files = dir.listFiles();
		//files 有文件或文件夹
		for(File file: files){
			//文件夹使用[文件夹]文件直接输出
			if(file.isDirectory()){
				System.out.println("[" + file.getName() +"]");
			}else{
				System.out.println(file.getName());
			}
		}
	}
	
	/**
	 * 过滤目录内容
	 * 如：只列图片内容
	 *   列文本文件
	 *   列java问价
	 *   
	 *   Java提供重载listFiles（过滤器）
	 *     工作原理：列出目录的每个文件，将每个文件交给过滤器检查，
	 *           过滤器检查通过的文件保留下来最为最后的结果
	 *           
	 *    过滤器：保留.java文件
	 *    如何使用：
	 *       1.创建一个过滤器，给定过滤条件
	 *       2.创建file对象，指向一个文件夹
	 *       3.在file对象上执行
	 *         File[] files = file.listFiles(过滤器)
	 *       4.返回结果就是满足过滤器条件的结果
	 *   
	 */
	//@Test
	public void testFileFilter(){
		
		//创建过滤器java.io.FileFilter
		//Filter过滤器
		FileFilter filter = new FileFilter(){
			//重写Filter提供的过滤规则
			//accept：接受
			//过滤器文件工作时候，listFiles方法将文件夹中的每个文件传给
			//pathname，方法返回值是true表示接受当前这个pathname
			//对应的文件
			//pathname = abc.txt  abc.log
			public boolean accept(File pathname){
				return pathname.getName().endsWith(".txt");
			}
		};
		File file =new File("c:/");
		//listFiles方法在C：/使用过滤器列目录
		//只保留满足条件的结果
		
		File[] files = file.listFiles(filter);
		for(File f : files){
			System.out.println(f.getName());
		}
	}
	
	//@Test
	public void testFileFilter2(){
		File file = new File("C:/");
		//在过滤器位置上直接写匿名内部类
		File[] files = file.listFiles(new FileFilter(){
			public boolean accept(File f){
				return f.getName().endsWith(".txt");
			}
		});
		
		for(File f: files){
			System.out.println(f.getName());
		}
	}
	
	/**
	 * 使用RandomAccessFile 写一个文件
	 * @throws Exception 
	 */
	//@Test
	public void testWriteFile() throws Exception{
		RandomAccessFile raf = new RandomAccessFile("demo.txt","rw");
		//write方法将参数int数据的低8位（1个Byte）写入文件中。
		raf.write(0x00000041);
		raf.write(0x42);
		raf.close();
	}
	
	//读取一个文件
	
	//@Test
	public void testReadFile() throws Exception{
		RandomAccessFile raf = new RandomAccessFile("demo.txt","r");
		int b = raf.read();
		System.out.println(Integer.toHexString(b));
		b = raf.read();
		System.out.println(Integer.toHexString(b));
		b = raf.read();
		System.out.println(Integer.toHexString(b));
		raf.close();
		
	}
	
	//@Test
	public void testReadFile2() throws Exception{
		RandomAccessFile raf = new RandomAccessFile("demo.txt","r");
		int b;
		while( (b = raf.read())!= -1){
			System.out.println(Integer.toHexString(b));
		}
		raf.close();
			
	}
	
	/**
	 * 将成批的byte数组的数据写入到文件中
	 * @throws Exception 
	 */
	//@Test
	public void testWriteBytes() throws Exception{
		RandomAccessFile raf = new RandomAccessFile("test.txt","rw");
		byte[] buf ={0x41,0x42,0x43,(byte)0xe4,(byte)0xb8,(byte)0xad};  //e的最高位为1，超过了byte的范围
		//将buf中位置0开始，连续写出6个byte
		raf.write(buf,0,6);
		raf.close();
		//读出来
		raf = new RandomAccessFile("test.txt","r");
		int b;
		while((b = raf.read())!= -1){
			System.out.println((char)(b));
		}
		raf.close();
	}
	
	//@Test
	public void testCalculate(){
		String str ="good good study, day day up.";
		str = str.replaceAll("[^a-zA-Z]","");
		
		Map<Character, Integer> map = new LinkedHashMap<Character,Integer>();
		//将字符存储到散列表中
		for(int i = 0; i < str.length(); i++){
			char word = str.charAt(i);
			Integer value = map.get(word);
			//若无此word，则置为1，否则在于按数字基础上加1
			map.put(word, value==null? 1: ++value);
		}
		
		//将其输出
		Set<Character> keys = map.keySet();
		for(Character key :keys){
			int value = map.get(key);
			System.out.print(key + ":" + value + " ");
		}
	}
	
	//按照树状结构打印C盘里面data文件夹里面的数据
	//@Test
	public void testFile3(){
		File file = new File("C:/data");
		File[] files = file.listFiles();
		
		for(File f: files){
			System.out.println(f.getName());
		}
	}
	
	
	//实现文件的复制功能
	@Test
	public void testFileCopy() throws Exception{
		RandomAccessFile raf = new RandomAccessFile("first.txt","rw");
		byte[] b = {0x41,0x42,0x43,0x44};
		raf.write(b);
		raf.close();
		
		//进行复制
		RandomAccessFile raf2 = new RandomAccessFile("two.txt","rw");
		 raf = new RandomAccessFile("first.txt","r");
		
		while((raf.read(b))!=-1){
			raf2.write(b);
		}
		raf2.close();
	}
	
	/**
     * 测试使用字节数组形式复制文件
     */
    //@Test
    public void testCopyFile()throws Exception{
        RandomAccessFile rafRead = new RandomAccessFile("raf.dat", "r");
        RandomAccessFile rafWrite = new RandomAccessFile("raf_copy.dat", "rw");
        
        int len = -1;
        byte[] buf = new byte[1024];
        while((len = rafRead.read(buf)) != -1){
            rafWrite.write(buf,0,len);
        }
        System.out.println("复制完毕");
        rafRead.close();
        rafWrite.close();
    }
    
    @Test
    public void testCopyFile2() throws Exception{
    	RandomAccessFile rafRead = new RandomAccessFile("raf.dat","r");
    	RandomAccessFile rafWrite = new RandomAccessFile("raf_copy.dat","rw");
    	
    	int len = -1;
    	byte[] buf = new byte[1024];
    	while((len = rafRead.read(buf))!=-1){
    		rafWrite.write(buf,0,len);
    	}
    	System.out.println("复制完毕");
    	rafRead.close();
    	rafWrite.close();
    }

}
