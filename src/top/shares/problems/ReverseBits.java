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

		System.out.println( reverseBits( 2 ));
	
	}
	
	public static int reverseBits(int n) {
        long num = (long)n;
		long mask = 0X80000000L;
		long f = 0L; 
		long fmask = 1L;
		long flag = 0;
		
		for ( int i = 0; i < 32; i++)
		{
			fmask = 1;
			flag = num & mask;
			if (flag  > 0)
			{
				fmask <<= i;
				f |= fmask;
			}
			mask >>= 1;
		}
		return (int)f;
    }
	
	public static long getUnsignedInt (int num)
	{     
		//将int数据转换为0~4294967295 (0xFFFFFFFF即DWORD)。
        return num & 0x0FFFFFFFF ;
    }
}
