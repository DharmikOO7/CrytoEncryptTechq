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
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Rail_Fence {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		
		System.out.print("Enter plain text: ");
		String plainText=sc.nextLine().toUpperCase().replaceAll(" ","");
		System.out.print("Enter key: ");
		byte key=sc.nextByte();
		String cipherText = encrypt(plainText,key);
		System.out.println("Cipher text: "+cipherText);
		String decryptedText=decrypt(cipherText,key);
		System.out.println("Decrypted text: "+decryptedText);
		sc.close();
	}

	private static String encrypt(String plainText, byte key) {
		List<ArrayList<Character>> fences=new ArrayList<>();
		for(byte i=0;i<key;i++) {
			ArrayList<Character> fence=new ArrayList<>();
			fences.add(fence);
		}
		{
			byte i=0;
			byte bit=1;
			for(Character c:plainText.toCharArray()) {
				fences.get(i%(key)).add(c);
				if(i==key-1)
					bit=-1;
				else if(i==0)
					bit=1;
				i+=bit;
			}
		}
		String cipherText = "";
		for (Iterator<ArrayList<Character>> iterator = fences.iterator(); iterator.hasNext();) {
			for (Iterator<Character> iterator2 = iterator.next().iterator(); iterator2.hasNext();) {
				cipherText+=iterator2.next();
			}
		}
		return cipherText;
	}
	
	private static String decrypt(String cipherText, byte key) {
		StringBuilder encryptedText = new StringBuilder(cipherText);
		Character[][] matrix=new Character[key][cipherText.length()];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j]='.';
			}
		}
		int extremityRowAdder=2*key-3;
		for(int i=0;i<matrix[0].length;) {
			char charToAdd=encryptedText.charAt(0);
			matrix[0][i]=charToAdd;
			i+=(extremityRowAdder+1);
			encryptedText.deleteCharAt(0);
		}
		int middleRowAdder=extremityRowAdder-1;
		System.out.println(middleRowAdder);
		for(int i=1;i<matrix.length-1;i++) {
			int adder=extremityRowAdder-i*2;
			for(int j=i;j<matrix[i].length;) {
				char charToAdd=encryptedText.charAt(0);
				matrix[i][j]=charToAdd;
				encryptedText.deleteCharAt(0);
				j+=(adder+1);
				adder=middleRowAdder-adder;
			}
		}
		for (int i = matrix.length-1; i < matrix[0].length;) {
			char charToAdd=encryptedText.charAt(0);
			matrix[matrix.length-1][i]=charToAdd;
			encryptedText.deleteCharAt(0);
			i+=(extremityRowAdder+1);
		}
		String decryptedText="";
		for (Character i[]:matrix) {
			for (Character j:i) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
		int i=0,j=0;
		byte addr=1;
		for(int l=0;l<cipherText.length();l++) {
			if((i+1)%key==0)
				addr=-1;
			else if(i%key==0)
				addr=1;
			decryptedText+=matrix[i][j++];
			i+=addr;
		}
		return decryptedText;
	}

}
