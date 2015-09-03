/**
 * Count based Sliding Window Bloom Filter
 * In this Bloom Filter we want to treat the latest n elements, and we update the Bloom Filter with the information of generation
 * @author gsong
 */
package BloomFilter;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class CountSlidingBF extends BloomFilter {

	private static final long serialVersionUID = 1L;

	private static Object m;
	
	public int generationSize; //the maximum number of elements a generation will contain
	public int numberOfGeneration; //the number of generation in a Sliding Window
	public int slidingWindowSize; //the maximum number of elements a sliding window will contain
	public int count; //the number of elements we have already treated in a Sliding Window
	public Map Generation; //the Map to store the information of generation, with key:hash value (bitset index); value:generation
	
	
	//public int generation; //the generation of the current element, calculated by generation = Math.ciel(currentElementNumber\numberElementInG)
	
	
	public CountSlidingBF(double c, int m, int k){
		super(c, m, k);
		this.slidingWindowSize = m;
		this.Generation = new HashMap();
	}
	
	public CountSlidingBF(int bitSetSize, int maxElementNumber){
		super(bitSetSize, maxElementNumber);
		this.slidingWindowSize = maxElementNumber;
		this.Generation = new HashMap();
	}
	
	public CountSlidingBF(double falsePositive, int maxElementNumber) {
		super(falsePositive, maxElementNumber);
		this.Generation = new HashMap();
	// TODO Auto-generated constructor stub
	}
	
	public CountSlidingBF(int bitSetSize, int maxElementNumber,
	int elementNumber, BitSet filteredData) {
		super(bitSetSize, maxElementNumber, elementNumber, filteredData);
		// TODO Auto-generated constructor stub
		this.Generation = new HashMap();
	}
	
	
	
	
	

	
	
}