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
import java.util.Random;
import java.util.Scanner;

public class Diffie_Helman {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter a prime number: ");
        int prime= scanner.nextInt();
        System.out.println("Enter a primitive root of "+ prime);
        int primRoot= scanner.nextInt();
        Random rand = new Random();

        int  prA = rand.nextInt(prime) + 1;
        int  prB = rand.nextInt(prime) + 1;
        System.out.println("Private key of A: "+prA);
        System.out.println("Private key of B: "+prB);
        int pubA= (int) (Math.pow(primRoot,prA)%prime);
        int pubB= (int) (Math.pow(primRoot,prB)%prime);
        System.out.println("       A                     B");
        System.out.println("(G)ᴾᴿᴬ mod p"+"              "+"(G)ᴾᴿᴮ"+" mod p"
        +"\n "+primRoot+"^"+prA+" mod"+prime+"             "+primRoot+"^"+prB+" mod"+prime+"    Public keys"
        +"\n      A="+pubA+"                  B="+pubB
        +"\n       \\                    /"
        +"\n        \\                  /"
        +"\n         \\                /"
        +"\n          \\              /"
        +"\n           \\            /"
        +"\n            \\          /"
        +"\n             \\        /"
        +"\n              \\      /"
        +"\n               \\    /"
        +"\n                \\  /"
        +"\n                 \\/"
        +"\n                 /\\"
        +"\n                /  \\"
        +"\n               /    \\"
        +"\n              /      \\"
        +"\n             /        \\"
        +"\n            /          \\"
        +"\n           /            \\"
        +"\n          /              \\"
        +"\n         /                \\"
        +"\n        /                  \\"
        +"\n       /                    \\"
        +"\n      /                      \\");
        int sharedSecretA= (int) (Math.pow(pubB,prA)%prime);
        int sharedSecretB= (int) (Math.pow(pubA,prB)%prime);
        System.out.println("(B)ᴾᴿᴬ mod p"+"              "+"(A)ᴾᴿᴮ"+" mod p");
        System.out.println(" "+pubB+"^"+prA+" mod"+prime+"              "+pubA+"^"+prB+" mod"+prime+"   Shared secret");
        System.out.println("     "+sharedSecretA+"                        "+sharedSecretB);

    }
}
