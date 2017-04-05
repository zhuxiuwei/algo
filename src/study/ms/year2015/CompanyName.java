package study.ms.year2015;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CompanyName {

	public static void main(String[] args) throws Exception {
		int num = 50;
		int maxLength = 2;
		
		Scanner sc = new Scanner(new File("d:\\brand.txt"));
		List<Character> cs = new ArrayList<Character>();
		while(sc.hasNextLine()){
			String s = sc.nextLine().trim();
			for (int i = 0; i < s.length(); i++) {
				cs.add(s.charAt(i));
			}
		}
		
		Random r = new Random();
		for (int length = 2; length <= maxLength; length++) {
			for (int i = 0; i < num; i++) {
				String temp = "";
				for (int j = 0; j < length; j++) {
					temp += cs.get(r.nextInt(cs.size())); 
				}
				System.out.println(temp);
			}
		}
	}

}
