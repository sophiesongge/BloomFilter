package test;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.UUID;

import org.junit.Test;

import BloomFilter.BloomFilter;
import BloomFilter.CountSlidingBF;

public class CountSlidingBFTest {
	
	static Random r = new Random();
	
	@Test
	public void testCountSlidingBFIntIntDoubleInt() {
		System.out.println("A test for csbf(slidingWindowSize, numberOfGeneration, c, k)");
		
		for(int i=0; i<10000; i++){
			double c = r.nextInt(20)+1;
			int slidingWindowSize = r.nextInt(10000)+1;
			int numberOfGeneration = r.nextInt(20)+1;
			int k = r.nextInt(20)+1;
			
			CountSlidingBF csbf = new CountSlidingBF(slidingWindowSize, numberOfGeneration, c, k);
			
			assertEquals(csbf.BloomFilter.getK(), k);
			assertEquals(csbf.BloomFilter.getExpectedBitsPerElement(), c, 0);
			assertEquals(csbf.BloomFilter.size(), c*slidingWindowSize, 0);
			
		}
	}

	@Test
	public void testCountSlidingBFIntIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountSlidingBFIntIntDouble() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddByteArray() {
		System.out.println("A test for method add()");
		CountSlidingBF csbf = new CountSlidingBF(120, 30, 0.001);
        for (int i = 0; i < 120; i++) {
            String str = UUID.randomUUID().toString();
            csbf.add(str);
            assert(csbf.contains(str));
        }
	}

	@Test
	public void testAddString() {
		System.out.println("A test for method add()");
		CountSlidingBF csbf = new CountSlidingBF(120, 30, 0.001);
        for (int i = 0; i < 120; i++) {
            String str = UUID.randomUUID().toString();
            csbf.add(str);
            assert(csbf.contains(str));
        }
	}

	@Test
	public void testAddE() {
		System.out.println("A test for method add()");
		CountSlidingBF csbf = new CountSlidingBF(120, 30, 0.001);
        for (int i = 0; i < 120; i++) {
            String str = UUID.randomUUID().toString();
            csbf.add(str);
            assert(csbf.contains(str));
        }
	}

	@Test
	public void testContainsByteArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsE() {
		System.out.println("A test for method contains()");
		CountSlidingBF csbf = new CountSlidingBF(120, 30, 0.001);

        for (int i = 0; i < 120; i++) {
            csbf.add(Integer.toBinaryString(i));
            assert(csbf.contains(Integer.toBinaryString(i)));
        }
        assertFalse(csbf.contains(UUID.randomUUID().toString()));
	}

	@Test
	public void testContainsString() {
		fail("Not yet implemented");
	}

}
