package study.interview;

public class Base64Test {

	private static String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRS"
			+ "TUVWXYZabcdefghijklmnopqrstuvwxyz";  
	private static String encoding(long num) {  
		
	    if(num < 1)  
	        throw new IllegalArgumentException("num must be greater than 0.");  
	    StringBuilder sb = new StringBuilder();  
	    while(num > 0){
	    	sb.append(ALPHABET.charAt((int) (num % 62)));  
	    	num = num / 62;
	    }  
	    return sb.toString();  
	}  
	
	private static long decoding(String str) {  
	    str = str.trim();  
	    if(str.length() < 1)  
	        throw new IllegalArgumentException("str must not be empty.");  
	  
	    long result = 0;  
	    for (int i = 0; i < str.length(); i++) {  
	        result += ALPHABET.indexOf(str.charAt(i)) * Math.pow(62, i);  
	    }  
	      
	    return result;  
	}  
	
	public static void main(String[] args) {
		
		System.out.println(encoding(12345657));
		System.out.println(decoding(encoding(12345657)));
	}

}
