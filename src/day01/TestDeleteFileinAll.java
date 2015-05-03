package day01;

import java.io.File;


//删除文件夹的所有内容
public class TestDeleteFileinAll
{
	public static void deleteFileinAll(File file ){
		//如果是文件或者空文件夹，就删除之
		if(file.isFile()||file.listFiles()==null){
			file.delete();
		}
		//不是空文件夹，先删除内容，再删除之
	    if(!file.isFile() && file.listFiles()!= null){
	    	File[] files = file.listFiles();
	    	for(File f: files){
	    		deleteFileinAll(f);
	    	}
	    	//全部删完之后，再删除文件夹本身
	    	
	    }
	    file.delete();
	}
	
	public static void main(String[] args)
	{
		File file = new File("c:/demo02");
		deleteFileinAll(file);
	}

}
