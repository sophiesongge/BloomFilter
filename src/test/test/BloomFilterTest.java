/**
 * This is a test for BloomFilter.java
 * @author gsong
 */
package test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;

import BloomFilter.BloomFilter;;

public class BloomFilterTest {
	
	static Random r = new Random();
	
	/**
	 * A test for testing the constructor BloomFilter(c,m,k)
	 * @throws Exception
	 */
	@Test
	public void testConstructorCMK() throws Exception {
		System.out.println("A test for BloomFilter(c, m, k)");
		
		for(int i=0; i<10000; i++){
			double c = r.nextInt(20)+1;
			int m = r.nextInt(10000)+1;
			int k = r.nextInt(20)+1;
			
			BloomFilter bf = new BloomFilter(c,m,k);
			
			assertEquals(bf.getK(), k);
			assertEquals(bf.getExpectedBitsPerElement(), c, 0);
			assertEquals(bf.size(), c*m, 0);
			
		}
	}

	@Test
	public void testBloomFilterIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testBloomFilterDoubleInt() {
		fail("Not yet implemented");
	}
	
	/**
	 * A test for testing the method isEquals()
	 */
	@Test
	public void testIsEquals() {
		System.out.println("A test for method isEquals(): ");
		BloomFilter bf1 = new BloomFilter(1000, 100);
		BloomFilter bf2 = new BloomFilter(1000, 100);
		
		for(int i=0; i<100; i++){
			String str = UUID.randomUUID().toString();
			bf1.add(str);
			bf2.add(str);
		}
		
		assert(bf1.equals(bf2));
		assert(bf2.equals(bf1));
		
		bf1.add("New Element");
		
		bf1.clear();
		bf2.clear();
		
		assert(bf1.equals(bf2));
		assert(bf2.equals(bf1));
		
		for(int i=0; i<100; i++){
			String str = UUID.randomUUID().toString();
			bf1.add(str);
			bf2.add(str);
		}
		
		assertTrue(bf1.equals(bf2));
		assertTrue(bf2.equals(bf1));
	}
    
    /**
     * A test for method createHashes
     * @throws UnsupportedEncodingException 
     */
	@Test
	public void testCreateHashes() throws UnsupportedEncodingException {
		System.out.println("A test for method createHashes()");
		String str = UUID.randomUUID().toString();
		int result1 = BloomFilter.createHash(str);
		int result2 = BloomFilter.createHash(str);
		assertEquals(result2, result1);
		int result3 = BloomFilter.createHash(UUID.randomUUID().toString());
		assertNotSame(result3, result2);
		int result4 = BloomFilter.createHash(str.getBytes("UTF-8"));
		assertEquals(result4, result1);
	}
    
	/**
	 * A test for method createHashByteArray()
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testCreateHashByteArray() throws UnsupportedEncodingException {
		System.out.println("A test for method createHashByteArray(): ");
		String str = UUID.randomUUID().toString();
		byte[] data = str.getBytes("UTF-8");
		int[] result1 = BloomFilter.createHashes(data, 10);
		int[] result2 = BloomFilter.createHashes(data, 10);
		assertEquals(result1.length, 10);
		assertEquals(result2.length, 10);
		assertArrayEquals(result1, result2);
		int[] result3 = BloomFilter.createHashes(data, 5);
		for(int i=0; i<result3.length; i++){
			assertEquals(result3[i], result1[i]);
		}
	}
	
	/**
	 * A test for method createHashStringCharset()
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testCreateHashStringCharset() throws UnsupportedEncodingException {
		System.out.println("A test for method createHashStringCharset(): ");
		String str = UUID.randomUUID().toString();
		byte[] data = str.getBytes("UTF-8");
		int result1 = BloomFilter.createHash(data);
		int result2 = BloomFilter.createHash(str);
		assertEquals(result1, result2);
	}

	@Test
	public void testCreateHashString() {
		fail("Not yet implemented");
	}
	

	/**
     * A test for method hashCode()
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testHashCode() throws UnsupportedEncodingException {
        System.out.println("A test for method hashCode():");

        BloomFilter bf1 = new BloomFilter(1000, 100);
        BloomFilter bf2 = new BloomFilter(1000, 100);

        assertTrue(bf1.hashCode() == bf2.hashCode());

        for (int i = 0; i < 100; i++) {
            String str = UUID.randomUUID().toString();
            bf1.add(str);
            bf2.add(str);
        }

        assertTrue(bf1.hashCode() == bf2.hashCode());

        bf1.clear();
        bf2.clear();

        assertTrue(bf1.hashCode() == bf2.hashCode());

        bf1 = new BloomFilter(100, 10);
        bf2 = new BloomFilter(100, 9);
        assertFalse(bf1.hashCode() == bf2.hashCode());

        bf1 = new BloomFilter(100, 10);
        bf2 = new BloomFilter(99, 9);
        assertFalse(bf1.hashCode() == bf2.hashCode());

        bf1 = new BloomFilter(100, 10);
        bf2 = new BloomFilter(50, 10);
        assertFalse(bf1.hashCode() == bf2.hashCode());
    }


	@Test
	public void testGetFalsePositiveProbabilityDouble() {
		fail("Not yet implemented");
	}
    
    /**
     * A test for mehtod getFalsePositiveProbability()
     */
	@Test
	public void testGetFalsePositiveProbability() {
		System.out.println("A test for method getFalsePositiveProbability(): ");
		BloomFilter bf = new BloomFilter(1000,100);
		double expectResult = 0.00819; // with m/n = 10, k = 7
		double realResult = bf.getFalsePositiveProbability();
		assertEquals(bf.getK(), 7);
		assertEquals(expectResult, realResult, 0.000009);
		
		bf = new BloomFilter(100, 10);
		expectResult = 0.00819; //with m/n = 10, k = 7
		realResult = bf.getFalsePositiveProbability();
		assertEquals(bf.getK(), 7);
		assertEquals(expectResult, realResult, 0.000009);
		
		bf = new BloomFilter(20, 10);
		expectResult = 0.393; //with m/n = 2, k = 1
		realResult = bf.getFalsePositiveProbability();
		assertEquals(expectResult, realResult, 0.0005);
		
		bf = new BloomFilter(110, 10);
		expectResult = 0.00509; //with m/n = 11, k = 8
		realResult = bf.getFalsePositiveProbability();
		assertEquals(8, bf.getK());
		assertEquals(expectResult, realResult, 0.00001);
	}

	@Test
	public void testMaxFalsePositiveProbability() {
		fail("Not yet implemented");
	}
	
	/**
	 * A test for method clear()
	 */
	@Test
	public void testClear() {
		System.out.println("A test for method clear()");
        BloomFilter bf = new BloomFilter(1000, 100);
        for (int i = 0; i < bf.size(); i++)
            bf.setBit(i, true);
        bf.clear();
        for (int i = 0; i < bf.size(); i++)
            assertSame(bf.getBit(i), false);
	}
	
	/**
	 * A test for method add()
	 */
	@Test
	public void testAdd() {
		System.out.println("A test for method add()");
        BloomFilter bf = new BloomFilter(1000, 100);
        for (int i = 0; i < 100; i++) {
            String str = UUID.randomUUID().toString();
            bf.add(str);
            assert(bf.contains(str));
        }
	}

	@Test
	public void testAddByteArray() {
		fail("Not yet implemented");
	}
	
	/**
	 * A test for method testAddAll()
	 */
	@Test
	public void testAddAll() {
		System.out.println("A test for method addAll(): ");
        List<String> v = new ArrayList<String>();
        BloomFilter bf = new BloomFilter(1000, 100);

        for (int i = 0; i < 100; i++)
            v.add(UUID.randomUUID().toString());

        bf.addAll(v);

        for (int i = 0; i < 100; i++)
            assert(bf.contains(v.get(i)));
	}

	@Test
	public void testContainsByteArray() {
		fail("Not yet implemented");
	}
	
	/**
	 * A test for method contains()
	 */
	@Test
	public void testContains() {
		System.out.println("A test for method contains()");
        BloomFilter bf = new BloomFilter(10000, 10);

        for (int i = 0; i < 10; i++) {
            bf.add(Integer.toBinaryString(i));
            assert(bf.contains(Integer.toBinaryString(i)));
        }
        assertFalse(bf.contains(UUID.randomUUID().toString()));
	}
	
	/**
	 * A test for method containsAll()
	 */
	@Test
	public void testContainsAll() {
		 System.out.println("A test for method containsAll(): ");
	        List<String> v = new ArrayList<String>();
	        BloomFilter bf = new BloomFilter(1000, 100);

	        for (int i = 0; i < 100; i++) {
	            v.add(UUID.randomUUID().toString());
	            bf.add(v.get(i));
	        }
	        assert(bf.containsAll(v));
	}

	@Test
	public void testGetK() {
		fail("Not yet implemented");
	}
	
	/**
	 * A test for method getBit()
	 */
	@Test
	public void testGetBit() {
		System.out.println("A test for method getBit(): ");
        BloomFilter bf = new BloomFilter(1000, 100);
        Random r = new Random();

        for (int i = 0; i < 100; i++) {
            boolean b = r.nextBoolean();
            bf.setBit(i, b);
            assertSame(bf.getBit(i), b);
        }
	}
	
	/**
	 * A test for method setBit()
	 */
	@Test
	public void testSetBit() {
		System.out.println("A test for method setBit(): ");

        BloomFilter bf = new BloomFilter(1000, 100);
        Random r = new Random();

        for (int i = 0; i < 100; i++) {
            bf.setBit(i, true);
            assertSame(bf.getBit(i), true);
        }

        for (int i = 0; i < 100; i++) {
            bf.setBit(i, false);
            assertSame(bf.getBit(i), false);
        }
	}
	
	/**
	 * A test for method size()
	 */
	@Test
	public void testSize(){
		System.out.println("A test for method size(): ");
        for (int i = 100; i < 1000; i++) {
            BloomFilter bf = new BloomFilter(i, 10);
            assertEquals(bf.size(), i);
        }
	}

	@Test
	public void testGetBitSet() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMaxNumberOfElements() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetExpectedBitsPerElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBitsPerElement() {
		fail("Not yet implemented");
	}

}
