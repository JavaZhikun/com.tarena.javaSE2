package day01;

import java.io.File;

/**
 * ������״�ṹ��ӡ�����ļ��е�����
 */
public class TestFileInTree
{
	
	public static int index = 0;
	
	public static void print(File file){
		//������ļ����߿��ļ�����ֹͣ
		if(file.isFile()||file.listFiles()==null){
			return ;
		}
		
		File[] files = file.listFiles();
		for(File f : files){
			//�����ļ���ֱ�Ӵ��
			if(f.isFile()){
				//λ�ú���
				index++;
				print(index);
				System.out.println(f.getName());
				index--;
				//��ӡ��֮���ƶ�����
			}
			
			//���ļ��У����ж��Ƿ����滹���ļ�����Ϊ�գ�ֱ�Ӵ�ӡ
		    if(!f.isFile()&&f.listFiles()==null){
		    	index++;
		    	print(index);
		    	System.out.println(f.getName());
		    	index--;
		    }
		    
		    if(!f.isFile()&&f.listFiles()!=null){//���滹���ļ��Ļ�
		    	index++;
		    	print(index);
		    	System.out.println(f.getName());
		    	//��ӡ���������
		    	print(f);
		    	index--;
		    }
			
		}
	}
	
	//��ӡindex���Ʊ��
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
