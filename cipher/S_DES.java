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
import java.util.List;
import java.util.Scanner;

public class S_DES {

	public static void main(String[] args) {
		
		Scanner sc =new Scanner(System.in);
		
		System.out.print("Enter cipher key: ");
		String cipherKey=sc.nextLine();
		
		System.out.println("Enter straight p-box: ");
		List<Byte> straightPBox=new ArrayList<>();
		for(int i=0;i<10;i++) {
			byte pbit=Byte.parseByte(sc.nextLine());
			straightPBox.add(pbit);
		}
		System.out.println("Enter compression box: ");
		List<Byte> compBox=new ArrayList<>();
		for(int i=0;i<8;i++) {
			byte cbit=Byte.parseByte(sc.nextLine());
			compBox.add(cbit);
		}
		StringBuilder key1=new StringBuilder();
		StringBuilder key2=new StringBuilder();
		roundKeyGenerator(key1,key2,cipherKey,straightPBox,compBox,sc);
		
		System.out.println("Key 1: "+key1);
		System.out.println("Key 2: "+key2);
		
		System.out.print("Enter plain text: ");
		String plainText=sc.nextLine();
		
		System.out.print("Enter initial permutation: ");
		String initPerm=sc.nextLine();
		
		StringBuilder pt=new StringBuilder();
		for(byte i=0;i<8;i++) {
			pt.append(plainText.charAt(initPerm.charAt(i)-49)-48);
		}
		System.out.println("After init perm. = "+pt);
		
		System.out.print("Enter expansion box: ");                 
		String expBox=sc.nextLine();
		
		System.out.println("Round 1 begin");
		String round1OP=round(pt.toString(),key1,expBox,sc);
		System.out.println("Round 1 OP: "+round1OP);
		
		System.out.println("Round 2 begin");
		String round2OP=round(round1OP,key2,expBox,sc);
		System.out.println("Round 2 OP: "+round2OP);
		
		System.out.print("Enter final permutation: ");
		String finPerm=sc.nextLine();
		
		StringBuilder cipherText=new StringBuilder();
		for(byte i=0;i<8;i++) {
			cipherText.append(round2OP.charAt(finPerm.charAt(i)-49)-48);
		}
		System.out.println("CipherText: "+cipherText);
		sc.close();
	}

	private static void roundKeyGenerator(StringBuilder key1, StringBuilder key2, String cipherKey,List<Byte> straightPBox, List<Byte> compBox, Scanner sc) {
		StringBuilder cipKeyAftPbox=new StringBuilder();
		for(byte i=0;i<10;i++) {
			cipKeyAftPbox.append(cipherKey.charAt(straightPBox.get(i)-1)-48);
		}
		StringBuilder lKeyBits=new StringBuilder(cipKeyAftPbox.substring(0, 5));
		StringBuilder rKeyBits=new StringBuilder(cipKeyAftPbox.substring(5, 10));
		System.out.println(lKeyBits+" "+rKeyBits);
		{	//left shift one bit
			char l1=lKeyBits.charAt(0);
			lKeyBits.deleteCharAt(0);
			lKeyBits.append(l1);
			char r1=rKeyBits.charAt(0);
			rKeyBits.deleteCharAt(0);
			rKeyBits.append(r1);
		}
		String shifted1Key=lKeyBits.toString()+rKeyBits.toString();
		System.out.println("After shift: "+ lKeyBits+" "+rKeyBits);
		for(byte i=0;i<8;i++) {
			key1.append(shifted1Key.charAt(compBox.get(i)-1)-48);
		}
		{	//left shift two bits
			char l1=lKeyBits.charAt(0);
			char l2=lKeyBits.charAt(1);
			lKeyBits.deleteCharAt(0);
			lKeyBits.deleteCharAt(0);
			lKeyBits.append(l1);
			lKeyBits.append(l2);
			char r1=rKeyBits.charAt(0);
			char r2=rKeyBits.charAt(1);
			rKeyBits.deleteCharAt(0);
			rKeyBits.deleteCharAt(0);
			rKeyBits.append(r1);
			rKeyBits.append(r2);
		}
		String shifted2Key=lKeyBits.toString()+rKeyBits.toString();
		System.out.println(shifted2Key);
		for(byte i=0;i<8;i++) {
			key2.append(shifted2Key.charAt(compBox.get(i)-1)-48);
		}
	}

	private static String round(String roundIP, StringBuilder key, String expBox, Scanner sc) {
		String lbits=roundIP.substring(0,4);
		String rbits=roundIP.substring(4,8);
		System.out.println("left bits: "+lbits);
		System.out.println("right bits: "+rbits);
		System.out.println("Calling fucntion");
		String funcOP=function(rbits,key,expBox,sc);
		System.out.println("Func OP: "+funcOP);
		int lBits=Integer.parseInt(lbits, 2);
		int rBits=Integer.parseInt(funcOP, 2);
		int newRbits=lBits^rBits;
		System.out.println("New L bits: "+rbits);
		System.out.println("NEWRBits:"+String.format("%4s", Integer.toBinaryString(newRbits)).replace(' ', '0'));
		String roundOP=rbits+String.format("%4s", Integer.toBinaryString(newRbits)).replace(' ', '0');
		return roundOP;
	}

	private static String function(String rbits, StringBuilder key, String expBox, Scanner sc) {               

		StringBuilder expIP=new StringBuilder();                     
		for(byte i=0;i<8;i++) {
			expIP.append(rbits.charAt(expBox.charAt(i)-49)-48);
		}
		System.out.println("Expanded IP:"+expIP);
		int xOR = Integer.parseInt(key.toString(), 2) ^ Integer.parseInt(expIP.toString(), 2);
		System.out.println("AFter xORing with key: "+xOR);
		String sBoxIP=String.format("%8s", Integer.toBinaryString(xOR)).replace(' ', '0');
		System.out.println("AFter xORing with key: "+sBoxIP);
		String sBox1IP=sBoxIP.substring(0, 4);
		String sBox2IP=sBoxIP.substring(4, 8);
		//rccr
		int[][] sBox1= {{1,0,3,2},{3,2,1,0},{0,2,1,3},{3,1,3,2}};
		int[][] sBox2= {{0,1,2,3},{2,0,1,3},{3,0,1,0},{2,1,0,3}};
		int sB1r=Integer.parseInt(String.valueOf(sBox1IP.charAt(0)-48)+String.valueOf(sBox1IP.charAt(3)-48), 2);
		int sB1c=Integer.parseInt(String.valueOf(sBox1IP.charAt(1)-48)+String.valueOf(sBox1IP.charAt(2)-48), 2);
		System.out.println("S1 row"+sB1r+"column "+sB1c);
		int sB2r=Integer.parseInt(String.valueOf(sBox2IP.charAt(0)-48)+String.valueOf(sBox2IP.charAt(3)-48), 2);
		int sB2c=Integer.parseInt(String.valueOf(sBox2IP.charAt(1)-48)+String.valueOf(sBox2IP.charAt(2)-48), 2);
		System.out.println("S2 row"+sB2r+"column "+sB2c);
		String sBox1OP=String.format("%2s", Integer.toBinaryString(sBox1[sB1r][sB1c])).replace(' ', '0');
		System.out.println("S1 OP:"+sBox1OP);
		String sBox2OP=String.format("%2s", Integer.toBinaryString(sBox2[sB2r][sB2c])).replace(' ', '0');
		System.out.println("S2 OP:"+sBox2OP);
		String sBoxOP=sBox1OP+sBox2OP;
		String finalPerm="2431";
		StringBuilder funcOP=new StringBuilder();
		for(byte i=0;i<4;i++) {
			funcOP.append(sBoxOP.charAt(finalPerm.charAt(i)-49)-48);
		}
		return funcOP.toString();
	}
}
