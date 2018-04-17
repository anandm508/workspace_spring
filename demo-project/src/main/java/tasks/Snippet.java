package tasks;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Snippet {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(URLDecoder.decode("agent%$", "UTF-8"));
	}

}
