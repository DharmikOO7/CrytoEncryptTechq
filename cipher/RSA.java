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

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {
    public static void main(String[] args){

        Scanner scanner=new Scanner(System.in);
        int p,q;
        System.out.print("Enter prime p: ");
        p=scanner.nextInt();
        System.out.print("Enter prime q: ");
        q=scanner.nextInt();
        int n =p*q;
        int phi_n=(p-1)*(q-1);
        System.out.println("n: "+n+", phi(n): "+phi_n);
        Random random=new Random();
        //find e such that 1<e<phi(n) and gcd(e,phi(n))=1
        int e =random.nextInt(phi_n)+1;
        while (n%e==0 && GCD(e,phi_n)!=1)
            e=random.nextInt(phi_n)+1;
        //find private key such that d*e=1 mod phi(n)
        int d=0;
        while (d==0) {
            try {
                d = new BigInteger("" + e).modInverse(new BigInteger("" + phi_n)).intValue();
            } catch (ArithmeticException ex) {
                // id e is not invertible try another e
                e=random.nextInt(phi_n)+1;
                while (n%e==0 && GCD(e,phi_n)!=1)
                    e = random.nextInt(phi_n) + 1;
            }
        }
        System.out.println("public key: <"+e+","+n+">");
        System.out.println("private key: <"+d+">");
        System.out.print("Enter plaintext(an integer): ");
        int m=scanner.nextInt();
        BigInteger nBigInt=new BigInteger(""+n);
        BigInteger cipherText=(new BigInteger(""+m).pow(e)).mod(nBigInt);
        System.out.println("Encrypted message is: "+cipherText);
        BigInteger decryptedText=(cipherText.pow(d)).mod(nBigInt);
        System.out.println("Decrypted message is: "+decryptedText);
        scanner.close();
    }

    private static int GCD(int num1, int num2) {
        if(num2 == 0){
            return num1;
        }
        return GCD(num2, num1%num2);
    }

}
