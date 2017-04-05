package LeetCode.round1.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 170405
 * @author Administrator
 *
 */
public class P535_EncodeAndDecodeTinyURL {

	private final Map<String, String> urls = new HashMap<String, String>();
	
	/**
	 * AC: 6ms, 54,25%
	 * @param longUrl
	 * @return
	 */
	public String encode(String longUrl) {
		String tinyUrl = longUrl.hashCode() + "";
        urls.put(tinyUrl, longUrl);
        return tinyUrl;
    }

    public String decode(String shortUrl) {
        return urls.get(shortUrl);
    }
	
	public static void main(String[] args) {
		P535_EncodeAndDecodeTinyURL codec = new P535_EncodeAndDecodeTinyURL();
		String url = "https://leetcode.com/advvx/design-tinyurl";
		System.out.println(codec.decode(codec.encode(url)));
	}

}
