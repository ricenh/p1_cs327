package p1_cs327;
//import java.util.Random;

/**
 * @author Xunhua Wang. All rights reserved.
 * @date 02/16/2012; revised on 09/27/2018; further refined on 09/20/2019, 09/29/2020, 10/07/2022, 03/13/2023; 09/14/2024
 * 
 */

public class AndersonThomasRSA
{
	public int gcd (int inE, int inZ) {
		// TO BE FINISHED
		// Must implement Euclid's algorithm
		// NO brute-forcing; violation will lead to zero points
		// NO recursion; violation will lead to zero points
		while (inZ != 0) {
			int r = inE % inZ;
			inE = inZ;
			inZ = r;
		}
		return inE;
	}

	public void testGcd () {
		int result1 = gcd (29, 288);
		int result2 = gcd (30, 288);
		int result3 = gcd (149, 288);
		int result4 = gcd (2, 4);

		System.out.println ("GCD (29, 288) = 0x" + Integer.toString(result1, 16));
		System.out.println ("GCD (30, 288) = 0x" + Integer.toString(result2, 16));
		System.out.println ("GCD (149, 288) = 0x" + Integer.toString(result3, 16));
		System.out.println ("GCD (2, 4) = 0x" + Integer.toString(result4, 16));
	}

	//
	// We assume that inE < inZ
	// This implementation follows our slides
	// Return:
	//	-1: no inverse
	//	inverse of inE mod inZ
	//
	public int xgcd (int inE, int inZ) {
		// TO BE FINISHED
		// Must implement the extended Euclidean algorithm
		// NO brute-forcing; violation will lead to zero points
		// NO recursion; violation will lead to zero points
		// Initialize variables
        int oldR = inE, r = inZ; 
        int oldS = 1, s = 0;     
        int oldT = 0, t = 1;     

        while (r != 0) {
            int q = oldR / r;

            int tempR = r;
            r = oldR - q * r;
            oldR = tempR;

            int tempS = s;
            s = oldS - q * s;
            oldS = tempS;

            int tempT = t;
            t = oldT - q * t;
            oldT = tempT;
        }

        if (oldR == 1) {
            return (oldS % inZ + inZ) % inZ;
        } else {
            return 0;
        }
	}

	public void testXgcd () {
		int result1 = xgcd (29, 288);
		int result2 = xgcd (30, 288);
		int result3 = xgcd (149, 288);
		int result4 = xgcd (2, 4);

		System.out.println ("29^-1 mod 288 = 0x" + Integer.toString(result1, 16));
		System.out.println ("30^-1 mod 288 = 0x" + Integer.toString(result2, 16));
		System.out.println ("149^-1 mod 288 = 0x" + Integer.toString(result3, 16));
		System.out.println ("2^-1 mod 4 = 0x" + Integer.toString(result4, 16));
	}

	public int[] keygen (int inP, int inQ, int inE) {
		int n = inP * inQ;
		int z = (inP - 1) * (inQ - 1);
		int d = xgcd (inE, z);
		int[] ret = {inE, n, d};
		return ret;
	}

	//
	// The following method will return an integer array, with [e, N, d] in this order
	public void testKeygen () {
		int[] keypair = keygen (17, 19, 29);

		System.out.println ("e = 0x" + Integer.toString(keypair[0], 16));
		System.out.println ("N = 0x" + Integer.toString(keypair[1], 16));
		System.out.println ("d = 0x" + Integer.toString(keypair[2], 16));
	}
	

	//
	// Calculate c = a^b mod n
	//
	public int modExpOne (int a, int b, int n) 	 {

		int x = 1;
		int w = a; 
		int y = b;
		
		while (y > 0) {
			int t = y%2; 
			y = y/2;
			if (t == 1) { 
				long xLong = x * w; 
				x = (int) (xLong % n);
			}
			
			long wLong = w * w;
			w = (int) (wLong % n);
		}

		return x;
	}


	public int encrypt (int message, int inE, int inN) {
		// TO BE FINISHED
		return 0;
	}

	public int decrypt (int ciphertext, int inD, int inN) {
		// TO BE FINISHED
		return 0;
	}

	public void testRSA () {
		int[] keypair = keygen (17, 19, 29);

		int m1 = 131;
		int c1 = encrypt (m1, keypair[0], keypair[1]);
		System.out.println ("The encryption of (m1=0x" + Integer.toString(m1, 16) + ") is 0x" + Integer.toString(c1, 16));
		int cleartext1 = decrypt (c1, keypair[2], keypair[1]);
		System.out.println ("The decryption of (c=0x" + Integer.toString(c1, 16) + ") is 0x" + Integer.toString(cleartext1, 16));

		int m2 = 254;
		int c2 = encrypt (m2, keypair[0], keypair[1]);
		System.out.println ("The encryption of (m2=0x" + Integer.toString(m2, 16) + ") is 0x" + Integer.toString(c2, 16));
		int cleartext2 = decrypt (c2, keypair[2], keypair[1]);
		System.out.println ("The decryption of (c2=0x" + Integer.toString(c2, 16) + ") is 0x" + Integer.toString(cleartext2, 16));
	}

	public static void main (String[] args) {
		AndersonThomasRSA atrsa = new AndersonThomasRSA ();

		System.out.println ("********** Small RSA Project output begins ********** ");

		atrsa.testGcd ();
		atrsa.testXgcd ();
		atrsa.testKeygen ();
		// atrsa.testRSA ();
	}
}
