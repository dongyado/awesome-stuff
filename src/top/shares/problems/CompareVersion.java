package top.shares.problems;

/**
 * compare version
 * 
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", 
 * 	it is the fifth second-level revision of the second first-level revision.
 * Here is an example of version numbers ordering:
 * 0.1 < 1.1 < 1.2 < 13.37
 * 
 * 
 * @author dongyado<dongyado@gmail.com>
 * */

public class CompareVersion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(compareVersion("01", "1"));

	}

	public static int compareVersion(String  v1, String v2)
	{
		String[] parts1 = v1.split("\\.");
		String[] parts2 = v2.split("\\.");
		
		int minLength = parts1.length < parts2.length ? parts1.length : parts2.length;
		int p1,p2;
		
		for (int i = 0; i < minLength; i++)
		{
			p1 = Integer.parseInt(parts1[i]);
			p2 = Integer.parseInt(parts2[i]);
			if(p1 < p2) return -1;
			if(p1 > p2) return 1;
		}
		
		if (parts1.length < parts2.length) return -1;
		else if(parts1.length > parts2.length) return 1;
		
		return 0;
	}
}
