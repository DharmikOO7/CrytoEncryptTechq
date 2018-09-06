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

public class PolyAlpha {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter plaintext: ");
		String plainText=sc.nextLine().toUpperCase();
		System.out.print("Enter keyword: ");
		String keyword=sc.nextLine().toUpperCase();
		String key="";
		for(int i=0;i<plainText.length()/keyword.length();i++) {
			key+=keyword;
		}
		for(int i=0;i<plainText.length()%keyword.length();i++) {
			key+=keyword.charAt(i);
		}
		System.out.println("Key: "+key);
		String cipherText="";
		for(int i=0;i<plainText.length();i++) {
			cipherText+=(char)( ( plainText.charAt(i)-65 + key.charAt(i)-65 )%26 + 65);
		}
		String decryptedText="";
		for(int i=0;i<cipherText.length();i++) {
			int c=(cipherText.charAt(i)-65);
			int k=(key.charAt(i)-65);
			decryptedText+=(char)((26+c-k)%26+65);
		}
		System.out.println(cipherText);
		System.out.println(decryptedText);
		sc.close();
	}
}
