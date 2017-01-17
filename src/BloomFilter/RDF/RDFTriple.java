/**
 * @author gsong
 */
package RDF;

public class RDFTriple{
	private String Subject;
	private String Predicate;
	private String Object;
	
	/**
	 * Constructor
	 * @param subject
	 * @param predicate
	 * @param object
	 */
	public RDFTriple(String subject, String predicate, String object){
		this.setSubject(subject);
		this.setPredicate(predicate);
		this.setObject(object);
	}
	
	/**
	 * Get Subject
	 * @return
	 */
	public String getSubject() {
		return Subject;
	}
	
	/**
	 * Set Subject
	 * @param subject
	 */
	public void setSubject(String subject) {
		Subject = subject;
	}
	
	/**
	 * Get Predicate
	 * @return
	 */
	public String getPredicate() {
		return Predicate;
	}
	
	/**
	 * Set Predicate
	 * @param predicate
	 */
	public void setPredicate(String predicate) {
		Predicate = predicate;
	}
	
	/**
	 * Get Object
	 * @return
	 */
	public String getObject() {
		return Object;
	}
	
	/**
	 * Set Object
	 * @param object
	 */
	public void setObject(String object) {
		Object = object;
	}
	
	/**
	 * Override toString
	 */
	public String toString(){
		return "<"+Subject+", "+Predicate+", "+Object+">";
	}
	
	
}