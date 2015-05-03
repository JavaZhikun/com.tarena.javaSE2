package day01;

import java.io.File;

/**
 * 按照树状结构打印出来文件夹的内容
 */
public class TestFileInTree
{
	
	public static int index = 0;
	
	public static void print(File file){
		//如果是文件或者空文件夹则停止
		if(file.isFile()||file.listFiles()==null){
			return ;
		}
		
		File[] files = file.listFiles();
		for(File f : files){
			//若是文件，直接打出
			if(f.isFile()){
				//位置后移
				index++;
				print(index);
				System.out.println(f.getName());
				index--;
				//打印完之后，移动回来
			}
			
			//是文件夹，先判断是否里面还有文件，若为空，直接打印
		    if(!f.isFile()&&f.listFiles()==null){
		    	index++;
		    	print(index);
		    	System.out.println(f.getName());
		    	index--;
		    }
		    
		    if(!f.isFile()&&f.listFiles()!=null){//里面还有文件的话
		    	index++;
		    	print(index);
		    	System.out.println(f.getName());
		    	//打印里面的内容
		    	print(f);
		    	index--;
		    }
			
		}
	}
	
	//打印index个制表符
	public static void print(int index){
		for(int j = 0;j < index;j++){
			System.out.print("|---");
		}
	}
	
	public static void main(String[] args)
	{
		File file = new File("C:/data");
		System.out.println(file.getName());
		print(file);
	}

}
