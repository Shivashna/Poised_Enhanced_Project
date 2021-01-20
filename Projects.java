/**
 * An object of this class contain all the details pertaining to a specific project
 * <p>
 * @author Shivashna Rooplall
 *
 */
public class Projects {
	
	/**
	 * @see Projects
	 */
	
	/** Attributes*/
	/**
	 * integer value for project number
	 */
	private int projectNumber ;
	/**
	 * String value for project name
	 */
	private String projectName ;
	/**
	 * String value for project type
	 */
	private String projectType ;
	/**
	 * String value for the project physical address 
	 */
	private String physicalAddress ;
	/**
	 * String value for ERF number
	 */
	private String erfNumber ;
	/**
	 * double value for project total fee
	 */
	private double totalFee ;
	/**
	 * double value for total amount paid for project
	 */
	private double totalAmountPaid ;
	/**
	 * String value for project deadline
	 */
	private String deadline ;
	/**
	 * boolean value for project finalization
	 */
	private boolean finalised ;
	/**
	 * String value for completion date
	 */
	private String completionDate ;
	
	
	// Methods
	
	/** Constructor
	 * 
	 * @param projectNumber stores the project number
	 * @param projectName stores the project name
	 * @param projectType stores the project type
	 * @param physicalAddress stores the project physical address
	 * @param erfNumber stores the project ERF number
	 * @param totalFee stores the total fee for the project
	 * @param totalAmountPaid stores the total amount paid for the project
	 * @param deadline stores the project deadline
	 * @param finalised stores a true/false value to acknowledge whether a project has been finalized or not
	 */
	public Projects(int projectNumber, String projectName, String projectType, String physicalAddress, String erfNumber, double totalFee, double totalAmountPaid, String deadline, boolean finalised, String completionDate) {
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.projectType = projectType;
		this.physicalAddress = physicalAddress;
		this.erfNumber = erfNumber;
		this.totalFee = totalFee;
		this.totalAmountPaid = totalAmountPaid;
		this.deadline = deadline;
		this.finalised = finalised;
		this.completionDate = completionDate;
	}
	
	
	/** Setter
	 * Simple method
	 * This method will be used to set a new project number
	 * @param projectNumber stores the project number
	 */
	public void setNewProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}
	
	
	/** Setter
	 * Simple method
	 * This method will be used to set a new project name
	 * @param projectName stores the project name
	 */
	public void setNewProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
	/** Setter
	 * Simple method
	 * This method will be used to set a new project type
	 * @param projectType stores the project type
	 */
	public void setNewProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	
	/** Setter
	 * Simple method
	 * This method will be used to set the new physical address
	 * @param physicalAddress stores the project physical address
	 */
	public void setNewPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	
	
	/** Setter
	 * Simple method
	 * This method will be used to set a new ERF number
	 * @param erfNumber stores the project ERF number
	 */
	public void setNewERFNumber(String erfNumber) {
		this.erfNumber = erfNumber;
	}
	
	
	/** Setter
	 * Simple method
	 * This method will be used to set a new totalFee
	 * @param totalFee stores the total fee for the project
	 */
	public void setNewTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	
	
	/** Setter
	 * Simple method
	 * This method will be used to set a new completion date
	 * @param completionDate stores the completion date for the project
	 */
	public void setNewCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	
	
	/** Setter
	 * Simple method
	 * This method will be used to set a new boolean finalised value
	 * @param finalised stores true/false value indicating whether a project has been finalized or not
	 */
	public void setNewFinalised(boolean finalised) {
		this.finalised = finalised;
	}
	
	
	/** Setter
	 * Simple method
	 * This will be used to set a new deadline for the project
	 * @param newDeadline stores the new project deadline
	 */
	public void setNewDeadline (String newDeadline) {
		this.deadline = newDeadline;
	}
	
	
	/** Setter
	 * Simple method
	 * This will be used to update the total amount paid by the client
	 * @param amountPaidNow stores the additional amount paid by the client
	 */
	public void setNewTotalAmountPaid(double amountPaidNow) {
		this.totalAmountPaid += amountPaidNow;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'projectNumber'
	 */
	public int getProjectNumber() {
		return projectNumber;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'projectName'
	 */ 
	public String getProjectName() {
		return projectName;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'projectType'
	 */
	public String getProjectType() {
		return projectType;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'physicalAddress'
	 */
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'erfNumber'
	 */
	public String getERFNumber() {
		return erfNumber;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'totalFee'
	 */ 
	public double getTotalFee() {
		return totalFee;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'totalAmountPaid'
	 */ 
	public double getTotalAmountPaid() {
		return totalAmountPaid;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'deadline'
	 */
	public String getDeadline() {
		return deadline;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'finalised'
	 */ 
	public boolean getFinalised() {
		return finalised;
	}
	
	
	/** Getter
	 * Simple method
	 * @return This will return the value stored in the attribute 'completionDate'
	 */
	public String getCompletionDate() {
		return completionDate;
	}
	
	
	/** toString
	 * @return This will display values stored in the fields of an object in an easy-to-read manner 
	 */ 
	public String toString() {
		String output = "\nProject number: " + projectNumber;
		output += "\nProject Name: " + projectName;
		output += "\nProject type: " + projectType;
		output += "\nPhysical address for the project: " + physicalAddress;
		output += "\nERF number: " + erfNumber;
		output += "\nTotal fee: R" + totalFee;
		output += "\nTotal amount paid: R" + totalAmountPaid;
		output += "\nDeadline for project: " + deadline;
		output += "\nProject finalised : " + finalised;
		   
		return output;
	}
}
