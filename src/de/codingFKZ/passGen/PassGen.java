/* PassGen
 * 05.07.2012
 * de.codingFKZ.passGen
 * PassGen.java
 * by codingFKZ
 */
package de.codingFKZ.passGen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * @author codingFKZ
 *
 */
public class PassGen {
	
	private static char []map = new char[] {
		'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
		'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		'0','1','2','3','4','5','6','7','8','9','!','?','-', '_', '§', '$', '%', '&'
	};
	
	private static PrintWriter out;
	private static int wordLength = 10;
	private static int wordCount = 2500;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("usage:\n\tjava -jar passgen.jar FILE_PATH [ LENGTH_OF_WORDS [>= 5; <= 64] Default:10] [ NUMBER_OF_WORDS Default:2500]");
			System.exit(1);
		}
		
		if (args.length > 1)
			wordLength = Integer.valueOf(args[1]);
		
		if (args.length > 2)
			wordCount = getCount(args[2]);
		
		try {
	    out = new PrintWriter(new FileOutputStream(new File(args[0])));
    } catch (FileNotFoundException e) {
    	System.err.println(args[0] + " not found");
    }
		
		recPassGen(5);
		
		System.out.println("Wordbook generated!\n" + args[0] + "\nBye!");
	}
	
	private static void recPassGen(int c) {
		if (c > wordLength)
			return;
		
		for (int i = 0; i < wordCount; i++) {
			String pass = "";
			for (int j = 0; j < c; j++)
				pass += (map[(int) (Math.random()*map.length)]);
			
			out.println(pass);
			out.flush();
			}
			
		recPassGen(c+1);
	}
	
	private static int getCount(String arg) {
		int c;
		
		try {
			c = Integer.valueOf(arg);
			
		} catch (NumberFormatException e) {
			return 5;
		}
		
		if 			(c < 5) 	return 5;
		else if (c > 64) 	return 64;
		else 							return c;
		
	}

}
