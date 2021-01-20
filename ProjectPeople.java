/**
 * An object of this class contain all the details of a client, contractor and architect pertaining to a specific project
 * <p>
 * @author Shivashna Rooplall
 *
 */
public class ProjectPeople {
	/** Attributes*/
	/** 
	 * String value for name of client, contractor or architect
	 */
	private String name ;
	/**
	 * String value for telephone number of client, contractor or architect
	 */
	private String telephoneNumber ;
	/**
	 * String value for email address of client, contractor or architect
	 */
	private String emailAddress ;
	/**
	 * String value for physical address of client, contractor or architect
	 */
	private String peoplePhysicalAddress ;
	
	
	// Methods
	
	/** Constructor
	 * 
	 * @param name value for name of client, contractor or architect
	 * @param telephoneNumber value for telephone number of client, contractor or architect
	 * @param emailAddress value for email address of client, contractor or architect
	 * @param peoplePhysicalAddress value for physical address of client, contractor or architect
	 */
	public ProjectPeople(String name, String telephoneNumber, String emailAddress,String peoplePhysicalAddress) {
		this.name = name;
		this.telephoneNumber = telephoneNumber;
		this.emailAddress = emailAddress;
		this.peoplePhysicalAddress = peoplePhysicalAddress;
	}
	
	
	/**
	 * Setter
	 * Simple method
	 * This method will be used to set a new name
	 * @param name value for name of client, contractor or architect
	 */
	public void setNewName(String name) {
		this.name = name;
	}
	
	
	/** Setter
	 * Simple method
	 * This will be used to set a new telephone number
	 * @param newTelephoneNumber value for telephone number of client, contractor or architect
	 */
	public void setNewTelephoneNumber(String newTelephoneNumber) {
		this.telephoneNumber = newTelephoneNumber;
	}
	
	
	/** Setter
	 * Simple method
	 * This will be used to change the physical address
	 * @param peoplePhysicalAddress value for physical address of client, contractor or architect
	 */
	public void setNewPeoplePhysicalAddress(String peoplePhysicalAddress) {
		this.peoplePhysicalAddress = peoplePhysicalAddress;
	}
	
	
	/** Setter
	 * Simple method
	 * This will be used to set a new email address
	 * @param newEmailAddress value for email address of client, contractor or architect
	 */
	public void setNewEmailAddress(String newEmailAddress) {
		this.emailAddress = newEmailAddress;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'name'
	 */
	public String getName() {
		return name;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'telephoneNumber'
	 */ 
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'emailAddress'
	 */
	// 
	public String getEmailAddress() {
		return emailAddress;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'physicalAddress'
	 */
	public String getPeoplePhysicalAddress() {
		return peoplePhysicalAddress;
	}
	
	
	/** toString method
	 * @return This will display values stored in the fields of an object in an easy-to-read manner
	 */
	// This will display values stored in the fields of an object in an easy-to-read manner
	public String toString() {
	    String output = "\nName: " + name;
	    output += "\nTelephone number: " + telephoneNumber;
	    output += "\nEmail address: " + emailAddress;
	    output += "\nPhysical address: " + peoplePhysicalAddress;
	    
	    return output;
	}

}
