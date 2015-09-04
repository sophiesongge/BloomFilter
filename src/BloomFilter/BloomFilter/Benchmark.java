/**
 * A Benchmark to evaluate the performance of BloomFilter
 * @author gsong
 */
package BloomFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Benchmark{
	static int elementNo = 36; // The number of elements to evaluate
	
	public static void printStatus(long start, long end){
		double diff = (end-start)/1000.0;
		System.out.println("The processing time is: "+diff+"s, "+"The speed is: "+(elementNo/diff)+" elements/second");
	}
	
	public static void main(String[] args){
		final Random r = new Random();
		
		//Generate elements
		List<String> elementList = new ArrayList<String>(elementNo);
		for(int i=0; i<elementNo; i++){
			byte[] b = new byte[200];
			r.nextBytes(b);
			elementList.add(new String(b));
		}
		
		List<String> newElement = new ArrayList<String>(elementNo);
		for(int i=0; i<elementNo; i++){
			byte[] b = new byte[200];
			r.nextBytes(b);
			newElement.add(new String(b));
		}
		
		//A new Count Based Sliding Window Bloom Filter with false positive 0.001, sliding window size 12, number of generation 3
		CountSlidingBF csbf = new CountSlidingBF(12, 3, 0.001);
		System.out.println("Testing "+elementNo+" elements with false positive 0.001, sliding window size 12 and 3 generations");
		System.out.println("With k="+csbf.BloomFilter.getK()+" hash functions.");
		System.out.println("The Bloom Filter Size is: "+csbf.BloomFilter.bitSetSize);
		for(int i=0; i<csbf.BloomFilter.size(); i++){
			System.out.print(csbf.BloomFilter.getBit(i)+" ");
		}
		System.out.print("\n");
		
		//Test for method add() in CountSlidingBF.java
		System.out.println("Evaluation for method add() in CountSlidingBF: ");
		long start_addcsbf = System.currentTimeMillis();
		for(int i=0; i<elementNo; i++){
			csbf.add(elementList.get(i));
		}
		long end_addcsbf = System.currentTimeMillis();
		printStatus(start_addcsbf, end_addcsbf);
		for(int i=0; i<elementNo; i++){
			System.out.print(csbf.BloomFilter.getBit(i)+" ");
		}
		System.out.print("\n");
		
		//A new Bloom Filter with false positive 0.001, and maximum number of elements elementNo
		BloomFilter<String> bf = new BloomFilter<String>(0.001, elementNo);
		System.out.println("Testing "+elementNo+" elements.");
		System.out.println("With k="+bf.getK()+" hash functions.");
		/*for(int i=0; i<bf.size(); i++){
		System.out.print(bf.getBit(i)+" ");
		}
		System.out.print("\n");*/
		
		
		//Test for the method add() in BloomFilter.java
		System.out.print("Evaluation for method add() in BloomFilter: ");
		long start_add = System.currentTimeMillis();
		for(int i=0; i<elementNo; i++){
			bf.add(elementList.get(i));
		}
		long end_add = System.currentTimeMillis();
		printStatus(start_add, end_add);
		/*for(int i=0; i<bf.size(); i++){
			System.out.print(bf.getBit(i)+" ");
		}
		System.out.print("\n");*/
		
		//Test for the method contains()
		System.out.print("Evaluation for method contains() with existing elements: ");
		long start_contains = System.currentTimeMillis();
		for(int i=0; i<elementNo; i++){
			bf.contains(elementList.get(i));
		}
		long end_contains = System.currentTimeMillis();
		printStatus(start_contains, end_contains);
		
		System.out.print("Evaluation for method contains() with non-existing elements: ");
		long start_ncontains = System.currentTimeMillis();
		for(int i=0; i<elementNo; i++){
			bf.contains(newElement.get(i));
		}
		long end_ncontains = System.currentTimeMillis();
		printStatus(start_ncontains, end_ncontains);
		
		/*//Test for the method containsAll()
		System.out.println("Evaluation for method containsAll() with existing elements: ");
		long start_containsAll = System.currentTimeMillis();
		for(int i=0; i<elementNo; i++){
			bf.contains(elementList.get(i));
		}
		long end_containsAll = System.currentTimeMillis();
		printStatus(start_containsAll, end_containsAll);

        System.out.println("Evaluation for method containsAll() with non-existing elements: ");
        long start_ncontainsAll = System.currentTimeMillis();
        for(int i=0; i<elementNo; i++){
        	bf.contains(newElement.get(i));
        }
        long end_ncontainsAll = System.currentTimeMillis();
        printStatus(start_ncontainsAll, end_ncontainsAll);*/
		
		
		
		

	}	
	
}