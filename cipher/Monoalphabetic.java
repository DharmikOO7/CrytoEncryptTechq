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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Monoalphabetic {

	public static void main(String[] args) {
		String alphabet="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		List<String> key=Arrays.asList(alphabet.split(","));
		List<String> DecryptionKey=new ArrayList<>(key);
		Collections.shuffle(key);
		System.out.println(key);
		System.out.println(DecryptionKey);
		String PlainText, CipherText="";
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter plain text: ");
		PlainText=sc.nextLine().toUpperCase().replaceAll(" ","");
		for(int i=0;i<PlainText.length();i++) {
			CipherText += key.get( (int)(PlainText.charAt(i)) - 65 );
		}
		System.out.println(CipherText);
		String DecryptedText="";
		for(int i =0;i<CipherText.length();i++) {
			DecryptedText+=DecryptionKey.get(key.indexOf(Character.toString(CipherText.charAt(i))));
		}
		System.out.println("Decrypted text: "+DecryptedText);
		sc.close();
	}

}
