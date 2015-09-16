/**
 * This is a test for 1-variable join, with the following query:
 * 
 * SELECT ?S ?O1 ?O2
 * WHERE
 * {
 * ?S "Work" ?O1
 * ?S "Diplome" ?O2
 * ?S "Paper" "kNN"
 * }
 * 
 * @author gsong
 */
package RDF;

import java.util.ArrayList;

import BloomFilter.BloomFilter;
import RDF.Read;
import RDF.RDFTriple;

public class multiVariableJoin{
	public static void main(String[] args){
		Read r1 = new Read("/Users/gsong/Documents/workspace/BloomFilter/src/data/p11.txt");
		Read r2 = new Read("/Users/gsong/Documents/workspace/BloomFilter/src/data/p12.txt");
		Read r3 = new Read("/Users/gsong/Documents/workspace/BloomFilter/src/data/p13.txt");
		ArrayList p1 = Read.Reader(r1.filePath);
		ArrayList p2 = Read.Reader(r2.filePath);
		ArrayList p3 = Read.Reader(r3.filePath);
		BloomFilter bfp1 = new BloomFilter(0.001, 12);
		BloomFilter bfp2 = new BloomFilter(0.001, 12);
		
		for(int i=0; i<p1.size(); i++){
			bfp1.add(((RDFTriple) p1.get(i)).getSubject());
		}
		
		for(int j=0; j<p2.size(); j++){
			bfp2.add(((RDFTriple) p2.get(j)).getSubject());
		}
		
		ArrayList result = new ArrayList();
		ArrayList intermediate = new ArrayList();
		
		for(int m=0; m<p3.size(); m++){
			if(bfp1.contains(((RDFTriple)p3.get(m)).getSubject()) && bfp2.contains(((RDFTriple)p3.get(m)).getSubject())){
				//System.out.println(p3.get(m));
				intermediate.add(p3.get(m));
			}
		}
		
		for(int i=0; i<p1.size(); i++){
			for(int j=0; j<intermediate.size(); j++){
				if(((RDFTriple)p1.get(i)).getSubject().equals(((RDFTriple)intermediate.get(j)).getSubject())){
					result.add(p1.get(i));
				}
			}
		}
		
		for(int i=0; i<p2.size(); i++){
			for(int j=0; j<intermediate.size(); j++){
				if(((RDFTriple)p2.get(i)).getSubject().equals(((RDFTriple)intermediate.get(j)).getSubject())){
					result.add(p2.get(i));
				}
			}
		}
		
		for(int n=0; n<result.size(); n++){
			System.out.println("?S: "+((RDFTriple)result.get(n)).getSubject()+", "+" And its ?O: "+((RDFTriple)result.get(n)).getObject());
		}
	}
}