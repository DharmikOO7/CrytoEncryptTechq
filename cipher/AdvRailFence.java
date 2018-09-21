/*
 * Copyright (c) 2018 Dharmik Panchal(Github userid:DharmikOO7)
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package cipher;

import java.util.Scanner;


public class AdvRailFence {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		
		System.out.print("Enter plain text: ");
		String plainText=sc.nextLine().toUpperCase().replaceAll(" ","");
		System.out.print("Enter key: ");
		String key=sc.nextLine();
		System.out.println(plainText.length());
		int lenKey=key.length();
		int lenPT=plainText.length();
		for(byte i=0;i<lenKey-lenPT%lenKey;i++) {
			plainText+="X";
		}
		System.out.println("Padded PT: "+plainText);
		String CipherText1=encrypt(plainText,key);
		System.out.println("CipherText1: "+CipherText1);
		String CipherText2=encrypt(CipherText1,key);
		System.out.println("CipherText2: "+CipherText2);
		sc.close();
	}

	private static String encrypt(String inputText, String key) {
		int lenKey=key.length();
		String CipherText="";
		int rows=inputText.length()/lenKey;
		for(int i=0;i<lenKey;i++) {
			int order=key.indexOf(Integer.toString(i+1));
			for(int j=0;j<rows;j++) {
				CipherText+=inputText.charAt(order+j*lenKey);
			}
		}
		return CipherText;
	}

}
