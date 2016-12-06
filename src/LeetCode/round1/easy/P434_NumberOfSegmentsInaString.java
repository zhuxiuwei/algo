package LeetCode.round1.easy;
/**
 * 161206
Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.
Please note that the string does not contain any non-printable characters.
Example:
Input: "Hello, my name is John"
Output: 5
 */
public class P434_NumberOfSegmentsInaString {

	/**
	 * AC: 4ms ,20%.
	 * @param s
	 * @return
	 */
	public int countSegments(String s) {
		int count = 0;
		boolean inNonEmpty = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c != ' '){
				if(!inNonEmpty){
					inNonEmpty = true;
					count ++;
				}
			}else
				inNonEmpty = false;
		}
		return count;
    }
	
	public static void main(String[] args) {
		P434_NumberOfSegmentsInaString p = new P434_NumberOfSegmentsInaString();
		System.out.println(p.countSegments("Hello, my  name is John"));	//5
	}

}
