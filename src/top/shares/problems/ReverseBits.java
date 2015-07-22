package top.shares.problems;

/**
 * reverse bits
 *  
 * Reverse bits of a given 32 bits unsigned integer.
 * 
 * For example, given input 43261596 (represented in binary as 00000010100101000001111010011100),
 * return 964176192 (represented in binary as 00111001011110000010100101000000).
 * 
 * @author dongyado<dongyado@gmail.com>
 * */

public class ReverseBits {

	public static void main(String[] args) 
	{
		System.out.println( reverseBits( 43261596 ));
	}
	
	public static int reverseBits(int n) 
	{
		long mask = 0X80000000L;
		int f = 0; 
		
		/**
		 * use mask shift right to find witch bit is 1, 
		 *  and use 1 shift left to set that bit to 1
		 * 
		 * */
		for ( int i = 0; i < 32; i++)
		{
			if ((n & mask) > 0)
			{
				f |= 1 << i;
			}
			mask >>= 1;
		}
		return f;
    }
}
