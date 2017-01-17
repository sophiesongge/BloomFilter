/**
 * @author gsong
 */
package RDF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import RDF.RDFTriple;

public class Read {
	String filePath;
	
	/**
	 * Constructor
	 */
	public Read() {
		
	}
	
	/**
	 * Constructor
	 * @param filePath
	 */
	public Read(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * A method for reading a file
	 * @param filePath
	 * @return
	 */
	public ArrayList<RDFTriple> Reader(String filePath) {
		File file = new File(filePath);
		BufferedReader reader = null;
		ArrayList<RDFTriple> readList = new ArrayList<RDFTriple>();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempsString = null;
			
			while((tempsString = reader.readLine())!=null) {
				String parts[] = tempsString.split(" +");
				String Subject = parts[0];				
				String Predicate = parts[1];
				String Object = parts[2];
				
				RDFTriple rdf = new RDFTriple(Subject, Predicate, Object);
				rdf.setSubject(Subject);
				rdf.setPredicate(Predicate);
				rdf.setObject(Object);
				
				readList.add(rdf);
			}
			
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} finally {
			if(reader != null){
				try{
					reader.close();
				}catch(IOException e1){
					//Do nothing
				}
			}
		}
		return readList;
	}

}