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

public class HillCipher {
    public static void main(String[] args){
        try(Scanner sc=new Scanner(System.in)){
	        
	        System.out.println("Enter keyword: ");
	        String keyword=sc.nextLine().toUpperCase();
	        
	        System.out.println("Enter plain text: ");
	        StringBuilder plainText=new StringBuilder(sc.nextLine().toUpperCase());
	        
	        String CipherText=encrypt(plainText,keyword);
	        System.out.println(CipherText.toString());
    	}
    }

    private static int[][] genKeyMatrix(String keyword) {
        int keywordLength=keyword.length();
        System.out.println("Keyword length: "+keywordLength);
        int keyMatrixDimensions;
        for(keyMatrixDimensions=2;;keyMatrixDimensions++){
            if( keyMatrixDimensions * keyMatrixDimensions >= keywordLength ){
                break;
            }
        }

        System.out.println("KeyMatrixDimensions: "+keyMatrixDimensions);
        int[][] keyMatrix=new int[keyMatrixDimensions][keyMatrixDimensions];
        int i=0,j=0;
        for (int k = 0; k < keywordLength; k++) {
            keyMatrix[i][j++]=keyword.charAt(k)-65;
            if (j%keyMatrixDimensions==0) {
                i++;
                j=0;
            }
        }
        int alphaindex=0;
        if(j!=0){
            while (j<keyMatrixDimensions) {            
                keyMatrix[i][j++]=alphaindex++;
            }
            i++;
        }
        for(;i<keyMatrixDimensions;i++){
            for(j=0;j<keyMatrixDimensions;j++){
                keyMatrix[i][j]=alphaindex++;
            }
        }
        System.out.println("Key matrix: ");
        for (int k = 0; k < keyMatrixDimensions; k++) {
            for (int l = 0; l < keyMatrixDimensions; l++) {
                System.out.print(keyMatrix[k][l]+" ");
            }
            System.out.println();
        }
        return keyMatrix;
    }

    private static String encrypt(StringBuilder plainText, String keyword) {
        int[][] keyMatrix=genKeyMatrix(keyword);
        int blockSize=keyMatrix.length;
        System.out.println("Blocksize: "+blockSize);
        plainText=formatPlainText(plainText,blockSize);
        StringBuilder CipherText = new StringBuilder(plainText.length());
        for(int i=0;i<plainText.length();){
            int[] block=new int[blockSize];
            for(int j=0;j<blockSize;j++){
                block[j]=plainText.charAt(i++)-65;
            }
            int[] encryptedBlock=new int[blockSize];
            for(int l=0;l<blockSize;l++){
                    for(int k=0;k<blockSize;k++){
                        encryptedBlock[l]+=keyMatrix[l][k]*block[k];
                    }
                    encryptedBlock[l]%=26;
            }
            for(int j=0;j<blockSize;j++){
                CipherText.append((char)(encryptedBlock[j]+65));
            }      
        }
        return CipherText.toString();
    }

    private static StringBuilder formatPlainText(StringBuilder plainText,int blockSize) {
        int extraChars=plainText.length()%blockSize;
        if(extraChars!=0){
            for(;extraChars>0;extraChars--){
                plainText.append('X');
            }
        }
        System.out.println(plainText);
        return plainText;
    }
}
