package study.ms.year2015;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;

/**
 * Split a large text file.
 * @author xiuzhu
 * 151225
 */
public class TextFileSplitter {
	
	/**
	 * 
	 * @param file  File to be split.
	 * @param num	Count of target files.
	 * @throws Exception
	 */
	public static void split(File file, int num) throws Exception{
		Scanner sc = new Scanner(file);
		long lineCount = 0;
		int eachLineCount = 0;
		int index = 1;
				
		String baseFolder = file.getParent();
		String fileName = file.getName();
		String postFix = "";
		if(fileName.contains(".")){
			fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
			postFix = file.getName().substring(file.getName().lastIndexOf("."));
		}
		String newFileDir = baseFolder + File.separator + fileName + "_" + index + "_" + System.currentTimeMillis() + postFix;
		FileWriter fw = new FileWriter(new File(newFileDir));
		
		System.out.println(new Date() + ": start to calculate line count.");
		//get line count
		while(sc.hasNextLine()){
			lineCount ++;
			sc.nextLine();
		}
		System.out.println(new Date() + ": Total line count: " + lineCount + ", split file count: " + num + ", each file will contain about " + lineCount/num + " lines");
		sc = new Scanner(file);
		eachLineCount = (int) (lineCount/num);
		
		//start to write file
		String content = "";
		System.out.println(new Date() + ": start to write file " + newFileDir);
		for (int i = 1; sc.hasNextLine(); content = sc.nextLine(), i++) {
			if(i == eachLineCount){
				fw.flush();
				fw.close();
				newFileDir = baseFolder + File.separator + fileName + "_" + ++index + "_" + System.currentTimeMillis() + postFix;
				System.out.println(new Date() + ": start to write file " + newFileDir);
				fw = new FileWriter(new File(newFileDir));
				i = 1;
			}
			fw.write(content + "\r\n");
		}
		fw.write(content + "\r\n");
		fw.flush();
		fw.close();
		System.out.println(new Date() + ": done!");
	}
	
	public static void main(String[] args) {
		if(args.length != 2){
			System.out.println("Specify file name and target file split count.\r\n Usage: java TextFileSplitter <fileIpath> <file_split_count>. e.g. java TextFileSplitter D:\\data\\apache.log 10");
			return;
		}
		try{
			String filePath = args[0];
			int num = Integer.parseInt(args[1]);
			File f = new File(filePath);
			if(!f.exists()){
				System.out.println("Can not find file: " + filePath);
				return;
			}else{
				if(!f.isFile()){
					System.out.println("This is not a file, may be a directory? FilePath:" + filePath);
					return;
				}
				split(f, num);
			}
		}catch(Exception e){ System.out.println("Exception: " + e.getMessage());}
		
	}

}
