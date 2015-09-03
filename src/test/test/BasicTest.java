/**
 * This is a test for the basic Bloom Filter: BloomFilter.java
 * @author gsong
 */
package test;

import java.util.Random;

import BloomFilter.BloomFilter;

public class BasicTest{
	static Random r = new Random();
	
	public void testConstructorCMK() throws Exception{
		System.out.print("A test for constructor BloomFilter(c, m, k): ");
		
		for(int i=0; i<10000; i++){
			double c = r.nextInt(20)+1;
			int m = r.nextInt(10000)+1;
			int k = r.nextInt(20)+1;
			BloomFilter bf = new BloomFilter(c, m, k);
			
			
		}
		
	}
}