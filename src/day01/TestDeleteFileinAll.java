package day01;

import java.io.File;


//ɾ���ļ��е���������
public class TestDeleteFileinAll
{
	public static void deleteFileinAll(File file ){
		//������ļ����߿��ļ��У���ɾ��֮
		if(file.isFile()||file.listFiles()==null){
			file.delete();
		}
		//���ǿ��ļ��У���ɾ�����ݣ���ɾ��֮
	    if(!file.isFile() && file.listFiles()!= null){
	    	File[] files = file.listFiles();
	    	for(File f: files){
	    		deleteFileinAll(f);
	    	}
	    	//ȫ��ɾ��֮����ɾ���ļ��б���
	    	
	    }
	    file.delete();
	}
	
	public static void main(String[] args)
	{
		File file = new File("c:/demo02");
		deleteFileinAll(file);
	}

}
