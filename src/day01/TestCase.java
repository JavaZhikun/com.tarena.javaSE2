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
		String pm25 = "����:50,���˴�:30,��ͷ��:40,"+
				"������:200,����:230,����:180,���˴�:100";
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
		 * �Զ����ʽ�������Ҫʵ��map����
		 * 1.ʹ��key���е�����keys����ȫ��key�ļ��ϣ�<String>��
		 * 		key�ķ���
		 */
		
		Set<String> keys = map.keySet();
		for(String key : keys){
			int val = map.get(key);
			System.out.println(key+":"+ val);
		}
		
		System.out.println("-----------------");
		//1.ʹ��key-value�ԣ�Entry�����е���
		//Set��ÿ��Ԫ����Entry��Entry�а���key��value
		//<String,Integer>�ֱ���key  value�ķ���
		Set<Entry<String,Integer>> entries = map.entrySet();
		for(Entry<String,Integer> entry : entries){
			String key = entry.getKey();
			int val =  entry.getValue();
			System.out.println(key +":" + val);
		}
	}
	
	/**
	 * ·���ָ���
	 */
	//@Test
	public void testFile(){
		//File.separator ֵ��������仯
		//windows: \  Linux|Unix : /
		String filename = "demo" + File.separator + "test.txt";
		System.out.println(filename);
		String file ="demo/test.txt";
		System.out.println(file);
	}
	
	/**
	 * File ����Դ����ļ�������Ŀ¼
	 * File ����3�����ع�����
	 *    new File("�ļ��С�Ŀ¼")
	 *    new File("��Ŀ¼"�����ļ��С�Ŀ¼��)
	 *    new File(File ��Ŀ¼�� ���ļ��С�Ŀ¼��)
	 * ����File���͵Ķ���
	 */
	
	//@Test
	public void testFile2(){
		File dir = new File("C:/windows");
		File file = new File("D:/error.log");
		//                  ��Ŀ¼            ��Ŀ¼
		File dir2 = new File("C:","windows");
		File file2 = new File(dir,"error.log");
		System.out.println(file);
		System.out.println(file2);
		
	}
	
	/**
	 * ����ļ����ļ����Ƿ��Ѿ�����
	 */
	//@Test
	public void testExists(){
		//new File() ֻ�Ǵ����ڴ����
	    //�����������ʹ���ж�Ӧ���ļ����ļ���
		//������ֱ���ڴ����ϴ������ļ���
		File file = new File("c:/b.txt");
		System.out.println(file.isFile());//false
		System.out.println(file.length());//0
		
		//���file����ָ��Ĵ�����ʹ�ô���
		//�ļ����ļ���
		System.out.println(file.exists());//false
		//�ڴ����ϲ����� c:/b.txt ���ļ����ļ���
		//�ڴ�����file���󣬵�������û������ļ��л��ļ�
	}
	
	/**
	 * ���������ļ�
	 * @throws IOException 
	 */
	//@Test
	public void testCreateNewFile() throws IOException{
		File file = new File("C:/", "demo.txt");
		System.out.println(file.exists());//false
		//�ڴ����ϴ����ļ����ɹ�����true
		boolean ok = file.createNewFile();
		System.out.println(ok);//true
		
		//��Ȩ�����������ִ���쳣
		//·������Ҳ���׳��쳣
		File file1 = new File("E:/", "demo.txt");
		ok = file1.createNewFile();
		System.out.println(ok);//true
	}
	
	/**
	 * ɾ��delete
	 * C��/
	 *  *   |-- demo1     //���ļ���
	 *  	|-- demo.txt  //�ļ�
	 *  	|-- demo2     //�ļ��У������ļ����ļ���
	 *             |-- demo.txt 
	 *  ɾ��demo01���Է���true
	 *  ɾ��demo.txt ���Է���true
	 *  ɾ��demo02 ����false
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
	 * ���һ���ļ������Ƿ����ļ���
	 * C��/
	 *    |--   demo   //�ļ���
	 *    |--   demo.txt   //�ļ�
	 */
	//@Test
	public void testIsDir(){
		File f1 = new File("c:/","demo02");
		File f2 = new File("c:/","demo.txt");
		
		//isDirectory()  ��ǰfile�����Ƿ����ļ���
		System.out.println(f1.isDirectory());
		System.out.println(f2.isDirectory());
	}
	
	/**
	 * �����ļ���
	 */
	//@Test
	public void testMkdir(){
		//�����ϻ�û���ļ���ʱ����ܴ���
		File f = new File("C:/TOM");
		System.out.println(f.exists());//false
		boolean ok = f.mkdir();
		System.out.println(ok);//true
	}
	
	/**
	 * ����ϵ��·��
	 * C:/
	 *  |-- data
	 *       |--  income
	 *               |--  fee
	 * "d:/data/income/fee"
	 * ����һ��mkdirs�Ϳ�������ϵ��·��            
	 */
	
	//@Test
	public void testMkdirs(){
		String path ="C:/data/income/fee";
		File file = new File(path);
		boolean b = file.mkdirs();
		System.out.println(b);
	}
	
	/**
	 * ���·������
	 *   ���Ӹ�Ŀ¼��д�ľ���·����Ϊ����·��
	 *   �ӵ�ǰĿ¼��ʼ��д�������·��
	 *   path = "data/in/fee"
	 *   file = new File(path)
	 *   file.mkdirs()
	 *   Eclipse�����·���ǵ�ǰ��Ŀ�ļ��п�ʼ��
	 *   D��/workspace
	 *        |--JSD
	 *        
	 *        ˢ���ļ��в��ܿ���
	 */
	//@Test
	public void testPath(){
		String path = "data/in/fee";
		File file = new File(path);
		boolean b = file.mkdirs();
		System.out.println(b);
	}
	
	/**
	 * ���ļ��е�Ŀ¼
	 */
	
	//@Test
	public void testListFiles(){
		//����һ��File������������Ӧһ��Ŀ¼
		File dir = new File("C:/windows");
		File[] files = dir.listFiles();
		//files ���ļ����ļ���
		for(File file: files){
			//�ļ���ʹ��[�ļ���]�ļ�ֱ�����
			if(file.isDirectory()){
				System.out.println("[" + file.getName() +"]");
			}else{
				System.out.println(file.getName());
			}
		}
	}
	
	/**
	 * ����Ŀ¼����
	 * �磺ֻ��ͼƬ����
	 *   ���ı��ļ�
	 *   ��java�ʼ�
	 *   
	 *   Java�ṩ����listFiles����������
	 *     ����ԭ���г�Ŀ¼��ÿ���ļ�����ÿ���ļ�������������飬
	 *           ���������ͨ�����ļ�����������Ϊ���Ľ��
	 *           
	 *    ������������.java�ļ�
	 *    ���ʹ�ã�
	 *       1.����һ����������������������
	 *       2.����file����ָ��һ���ļ���
	 *       3.��file������ִ��
	 *         File[] files = file.listFiles(������)
	 *       4.���ؽ��������������������Ľ��
	 *   
	 */
	//@Test
	public void testFileFilter(){
		
		//����������java.io.FileFilter
		//Filter������
		FileFilter filter = new FileFilter(){
			//��дFilter�ṩ�Ĺ��˹���
			//accept������
			//�������ļ�����ʱ��listFiles�������ļ����е�ÿ���ļ�����
			//pathname����������ֵ��true��ʾ���ܵ�ǰ���pathname
			//��Ӧ���ļ�
			//pathname = abc.txt  abc.log
			public boolean accept(File pathname){
				return pathname.getName().endsWith(".txt");
			}
		};
		File file =new File("c:/");
		//listFiles������C��/ʹ�ù�������Ŀ¼
		//ֻ�������������Ľ��
		
		File[] files = file.listFiles(filter);
		for(File f : files){
			System.out.println(f.getName());
		}
	}
	
	//@Test
	public void testFileFilter2(){
		File file = new File("C:/");
		//�ڹ�����λ����ֱ��д�����ڲ���
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
	 * ʹ��RandomAccessFile дһ���ļ�
	 * @throws Exception 
	 */
	//@Test
	public void testWriteFile() throws Exception{
		RandomAccessFile raf = new RandomAccessFile("demo.txt","rw");
		//write����������int���ݵĵ�8λ��1��Byte��д���ļ��С�
		raf.write(0x00000041);
		raf.write(0x42);
		raf.close();
	}
	
	//��ȡһ���ļ�
	
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
	 * ��������byte���������д�뵽�ļ���
	 * @throws Exception 
	 */
	//@Test
	public void testWriteBytes() throws Exception{
		RandomAccessFile raf = new RandomAccessFile("test.txt","rw");
		byte[] buf ={0x41,0x42,0x43,(byte)0xe4,(byte)0xb8,(byte)0xad};  //e�����λΪ1��������byte�ķ�Χ
		//��buf��λ��0��ʼ������д��6��byte
		raf.write(buf,0,6);
		raf.close();
		//������
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
		//���ַ��洢��ɢ�б���
		for(int i = 0; i < str.length(); i++){
			char word = str.charAt(i);
			Integer value = map.get(word);
			//���޴�word������Ϊ1���������ڰ����ֻ����ϼ�1
			map.put(word, value==null? 1: ++value);
		}
		
		//�������
		Set<Character> keys = map.keySet();
		for(Character key :keys){
			int value = map.get(key);
			System.out.print(key + ":" + value + " ");
		}
	}
	
	//������״�ṹ��ӡC������data�ļ������������
	//@Test
	public void testFile3(){
		File file = new File("C:/data");
		File[] files = file.listFiles();
		
		for(File f: files){
			System.out.println(f.getName());
		}
	}
	
	
	//ʵ���ļ��ĸ��ƹ���
	@Test
	public void testFileCopy() throws Exception{
		RandomAccessFile raf = new RandomAccessFile("first.txt","rw");
		byte[] b = {0x41,0x42,0x43,0x44};
		raf.write(b);
		raf.close();
		
		//���и���
		RandomAccessFile raf2 = new RandomAccessFile("two.txt","rw");
		 raf = new RandomAccessFile("first.txt","r");
		
		while((raf.read(b))!=-1){
			raf2.write(b);
		}
		raf2.close();
	}
	
	/**
     * ����ʹ���ֽ�������ʽ�����ļ�
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
        System.out.println("�������");
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
    	System.out.println("�������");
    	rafRead.close();
    	rafWrite.close();
    }

}
