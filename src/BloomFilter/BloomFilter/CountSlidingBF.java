/**
 * Count based Sliding Window Bloom Filter
 * In this Bloom Filter we want to treat the latest n elements, and we update the Bloom Filter with the information of generation
 * @author gsong
 */
package BloomFilter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import BloomFilter.BloomFilter;

public class CountSlidingBF<E>{
	
	static final Charset charset = Charset.forName("UTF-8");
	
	public int generationSize; //the maximum number of elements a generation will contain (The number of elements need to be update each time)
	public int numberOfGeneration; //the number of generation in a Sliding Window
	public int slidingWindowSize; //the maximum number of elements a sliding window will contain
	public int elementNumber; //the number of elements we have already treated in a Sliding Window
	public int k; //the number of hash functions
	public BloomFilter BloomFilter; //the Bloom Filter used in this Sliding Window
	public Map<Integer, Integer> Generation; //the Map to store the information of generation, with key:hash value (bitset index); value:generation ---- BloomFilter + Generation = Sliding BF
	
	/**
	 * A constructor
	 * @param slidingWindowSize
	 * @param numberOfGeneration
	 * @param c
	 * @param k
	 */
	public CountSlidingBF(int slidingWindowSize, int numberOfGeneration, double c, int k){
		this.generationSize = (int) Math.ceil(slidingWindowSize/numberOfGeneration);
		this.slidingWindowSize = slidingWindowSize;
		this.numberOfGeneration = numberOfGeneration;
		this.k = k;
		this.BloomFilter = new BloomFilter(c, slidingWindowSize, k);
		this.Generation = new HashMap();
	}
	
	/**
	 * A constructor
	 * @param slidingWindowSize
	 * @param numberOfGeneration
	 * @param bitSetSize
	 */
	public CountSlidingBF(int slidingWindowSize, int numberOfGeneration, int bitSetSize){
		this.slidingWindowSize = slidingWindowSize;
		this.numberOfGeneration = numberOfGeneration;
		this.generationSize = (int) Math.ceil(slidingWindowSize/numberOfGeneration);
		this.BloomFilter = new BloomFilter(bitSetSize, slidingWindowSize);
		this.Generation = new HashMap();
	}
	
	/**
	 * A constructor
	 * @param slidingWindowSize
	 * @param numberOfGeneration
	 * @param falsePositive
	 */
	public CountSlidingBF(int slidingWindowSize, int numberOfGeneration, double falsePositive){
		this.slidingWindowSize = slidingWindowSize;
		this.numberOfGeneration = numberOfGeneration;
		this.generationSize = (int) Math.ceil(slidingWindowSize/numberOfGeneration);
		this.BloomFilter = new BloomFilter(falsePositive, slidingWindowSize);
		this.Generation = new HashMap();
	}
	
	public void add(byte[] bytes){
		BloomFilter.add(bytes);
		int[] hashes = BloomFilter.createHashes(bytes, k);
		elementNumber ++;
		int generationNumber = (int) Math.floor(elementNumber / generationSize);
		for(int hash : hashes){
			this.Generation.put(Math.abs(hash % BloomFilter.bitSetSize), generationNumber);
		}
		if(elementNumber > slidingWindowSize){
			ArrayList<Integer> index = new ArrayList<Integer>();
			for(Integer key : Generation.keySet()){
				if(Generation.get(key)==0){
					index.add(key);
				}
			}
			for(int i=0; i<index.size(); i++){
				BloomFilter.setBit((int) index.get(i), false);
			}
			elementNumber = 0;
		}
	}
	
	 public void add(E element) {
	       add(element.toString().getBytes(charset));
	    }
	
}