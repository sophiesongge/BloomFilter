/**
 * 1-V Join:
 * SELECT ?S
 * WHERE
 * {
 * ?S "Work" "INRIA"
 * ?S "Diplome" "Ph.D"
 * ?S "Paper" "kNN"
 * }
 * 
 * 2-V Join:
 * SELECT ?S ?O
 * WHERE
 * {
 * ?S "Work" "INRIA"
 * ?S "Diplome" ?O
 * ?S "Paper" "kNN"
 * }
 * 
 * M-V Join:
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

public class JoinTest{
	Read r1;
	Read r2;
	Read r3;
	ArrayList p1;
	ArrayList p2;
	ArrayList p3;
	BloomFilter bfp1;
	BloomFilter bfp2;
	BloomFilter bfp3;
	
	public JoinTest(String r1, String r2, String r3, double d, int maxElem){
		this.r1 = new Read(r1);
		this.r2 = new Read(r2);
		this.r3 = new Read(r3);
		this.p1 = Read.Reader(this.r1.filePath);
		this.p2 = Read.Reader(this.r2.filePath);
		this.p3 = Read.Reader(this.r3.filePath);
		this.bfp1 = new BloomFilter(d,maxElem);
		this.bfp2 = new BloomFilter(d,maxElem);
		this.bfp3 = new BloomFilter(d,maxElem);
	};
	
	public ArrayList oneVariableJoin(String fix1, String fix2){
		ArrayList result = new ArrayList();
		for(int i=0; i<p1.size(); i++){
			if(((RDFTriple)p1.get(i)).getObject().equals(fix1)){
				bfp1.add(((RDFTriple) p1.get(i)).getSubject());	
			}
		}
		
		for(int j=0; j<p2.size(); j++){
			if(((RDFTriple)p2.get(j)).getObject().equals(fix2)){
				bfp2.add(((RDFTriple) p2.get(j)).getSubject());
			}
		}
		
		for(int m=0; m<p3.size(); m++){
			if(bfp1.contains(((RDFTriple)p3.get(m)).getSubject()) && bfp2.contains(((RDFTriple)p3.get(m)).getSubject())){
				//System.out.println(p3.get(m));
				result.add(p3.get(m));
			}
		}
		
		return result;
	}
	
	
	public ArrayList twoVariableJoin(String fix1, String fix2){
		ArrayList result = new ArrayList();
		for(int i=0; i<p1.size(); i++){
			if(((RDFTriple)p1.get(i)).getObject().equals(fix1)){
				bfp1.add(((RDFTriple) p1.get(i)).getSubject());
			}
		}
		
		for(int j=0; j<p3.size(); j++){
			if(((RDFTriple)p3.get(j)).getObject().equals(fix2)){
				bfp3.add(((RDFTriple) p3.get(j)).getSubject());
			}
		}
		
		for(int m=0; m<p2.size(); m++){
			if(bfp1.contains(((RDFTriple)p2.get(m)).getSubject()) && bfp3.contains(((RDFTriple)p2.get(m)).getSubject())){
				//System.out.println(p3.get(m));
				result.add(p2.get(m));
			}
		}
		return result;
	}
	
	
	public ArrayList multiVariableJoin(){
		ArrayList result = new ArrayList();
		ArrayList result1 = new ArrayList();
		ArrayList result2 = new ArrayList();
		ArrayList intermediate = new ArrayList();
		
		for(int i=0; i<p1.size(); i++){
			bfp1.add(((RDFTriple) p1.get(i)).getSubject());
		}
		
		for(int j=0; j<p2.size(); j++){
			bfp2.add(((RDFTriple) p2.get(j)).getSubject());
		}
		
		for(int m=0; m<p3.size(); m++){
			if(bfp1.contains(((RDFTriple)p3.get(m)).getSubject()) && bfp2.contains(((RDFTriple)p3.get(m)).getSubject())){
				//System.out.println(p3.get(m));
				intermediate.add(p3.get(m));
			}
		}
		
		for(int i=0; i<p1.size(); i++){
			for(int j=0; j<intermediate.size(); j++){
				if(((RDFTriple)p1.get(i)).getSubject().equals(((RDFTriple)intermediate.get(j)).getSubject())){
					result1.add(p1.get(i));
				}
			}
		}
		
		for(int i=0; i<p2.size(); i++){
			for(int j=0; j<intermediate.size(); j++){
				if(((RDFTriple)p2.get(i)).getSubject().equals(((RDFTriple)intermediate.get(j)).getSubject())){
					result2.add(p2.get(i));
				}
			}
		}
		result.add(result1);
		result.add(result2);
		return result;
	}
	
	public static void main(String[] args){
		JoinTest jt = new JoinTest("./src/data/p11.txt", "./src/data/p12.txt", "./src/data/p13.txt", 0.001, 12);
		ArrayList result1 = jt.oneVariableJoin("INRIA", "Ph.D");
		ArrayList result2 = jt.twoVariableJoin("INRIA", "kNN");
		ArrayList result3 = jt.multiVariableJoin();
		
		System.out.println("This is a test for 1-variable join, to find the authors for paper kNN who works in INRIA and who has a Ph.D diplome:");
		for(int i=0; i<result1.size(); i++){
			System.out.println(((RDFTriple)result1.get(i)).getSubject()+" ");
		}
		System.out.println("\n");
		
		
		System.out.println("This is a test for 2-variable join, to find the authors for paper kNN who works in INRIA and their diplome:");
		for(int i=0; i<result2.size(); i++){
			System.out.println("Author: "+((RDFTriple)result2.get(i)).getSubject()+" has dimplome: "+((RDFTriple)result2.get(i)).getObject()+" ");
		}
		System.out.println("\n");
		
		System.out.println("This is a test for multi-variable join, to find the authors for paper kNN, and the place they work, and their diplome: ");
		for(int i=0; i<((ArrayList)result3.get(0)).size(); i++){
			System.out.println("Author: "+((RDFTriple)((ArrayList)result3.get(0)).get(i)).getSubject()+" works in: "+((RDFTriple)((ArrayList)result3.get(0)).get(i)).getObject());
		}
		
		for(int i=0; i<((ArrayList)result3.get(1)).size(); i++){
			System.out.println("Author: "+((RDFTriple)((ArrayList)result3.get(1)).get(i)).getSubject()+" has diplome: "+((RDFTriple)((ArrayList)result3.get(1)).get(i)).getObject());
		}
		
	}
	
}