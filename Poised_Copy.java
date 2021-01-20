import javax.swing.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.sql.*;

/**
 * This program serves as a project management system for a small structural engineering firm called "Poised".
 * The purpose of this program is to keep track of all the projects that Poised is handling.
 * <p>
 * @author Shivashna Rooplall
 * @version 3.0 , 12 December 2020
 */
public class Poised_Copy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			// When the program runs, the main menu will be the first thing to be displayed to the user.
			int option = menuOption();
		
			// Objects with default values are then created
			// This allows the default values in the object fields to be replaced later on with values entered by the user
			
			Projects project = new Projects(0, null, null, null, null, 0.0d, 0.0d, null, false, null);
			ArrayList<Projects> projectList =  new ArrayList<Projects>();//List of all projects
			ArrayList<Projects> incompleteProjectsList =  new ArrayList<Projects>();// List of incomplete projects
			ArrayList<Projects> overdueProjectsList =  new ArrayList<Projects>();// List of overdue projects
			
			ProjectPeople client = new ProjectPeople(null, null, null, null);
			ArrayList<ProjectPeople> clientList = new ArrayList<ProjectPeople>();// List all of clients
			ArrayList<ProjectPeople> incompleteClientList = new ArrayList<ProjectPeople>();// List of incomplete projects client details
			ArrayList<ProjectPeople> overdueClientList = new ArrayList<ProjectPeople>();// List of overdue projects client details
			
			ProjectPeople contractor = new ProjectPeople(null, null, null, null);
			ArrayList<ProjectPeople> contractorList = new ArrayList<ProjectPeople>();// List all of Contractors
			ArrayList<ProjectPeople> incompleteContractorList = new ArrayList<ProjectPeople>();// List of incomplete projects contractor details
			ArrayList<ProjectPeople> overdueContractorList = new ArrayList<ProjectPeople>();// List of overdue projects contractor details
			
			ProjectPeople architect = new ProjectPeople(null, null, null, null);
			ArrayList<ProjectPeople> architectList = new ArrayList<ProjectPeople>();// List all of architects
			ArrayList<ProjectPeople> incompleteArchitectList = new ArrayList<ProjectPeople>();// List of incomplete projects architect details
			ArrayList<ProjectPeople> overdueArchitectList = new ArrayList<ProjectPeople>();// List of overdue projects architect details
			
			
			try {
				// Connect to the poisepms_db database, via the jdbc:mysql: channel on localhost (this PC)
				// Use username "otheruser", password "swordfish".
				Connection connection = DriverManager . getConnection (
						"jdbc:mysql://localhost:3306/poisepms_db?useSSL=false" ,
						"otheruser" ,
						"swordfish"
				);
				
				// Create a direct line to the database for running our queries
				Statement statement = connection . createStatement ();
				ResultSet results ;
				int rowsAffected ;
				
				
				// Populating the project list
				populateProjectList(projectList, statement);
				
				//Populating the client list by reading information from the database poisepms_db
				populateClientList(clientList, statement);
				
				//Populating the contractor list by reading information from the database poisepms_db
				populateContractorList(contractorList, statement);
				
				//Populating the architect list by reading information from the database poisepms_db
				populateArchitectList(architectList, statement);
				
				//Populating the incomplete projects list by reading information from the database poisepms_db
				project = populateIncompleteProjectsList(project, incompleteProjectsList, statement);
				
				//Populating the incomplete client list by reading information from the database poisepms_db
				populateIncompleteClientList(incompleteClientList, statement);
				
				//Populating the incomplete contractor list by reading information from the database poisepms_db
				populateIncompleteContractorList(incompleteContractorList, statement);
				
				//Populating the incomplete architect list by reading information from the database poisepms_db
				results = populateIncompleteArchitectList(incompleteArchitectList, statement);
				
				
				//The program will run and allow the user to select different options until they choose option 8 (to exit the program)
				while (option != 9) {
					
//****************************************************** OPTION 1 ****************************************************************************************************************************************************************************************************************************************************************
					if (option == 1) {
						// Option 1 allows the user to enter details for the project and these details will be used to create the project object, client object, contractor object and architect object. 
						
						// Displaying the list of existing projects
						displayListProjectDetails(projectList,clientList,contractorList,architectList);
						
						try {
							
							// Details for the project object from user input
							
							int projectNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter a project number"));
							
							// Checking if the project number already exists
							results = statement.executeQuery("SELECT ProjectNumber FROM Projects");
							boolean equalProjNum = false;
							while (results.next()) {
								int projectNum_db = results.getInt("ProjectNumber");
								if (projectNum_db == projectNumber) {
									equalProjNum = true;
									while (equalProjNum == true) {
										projectNumber = Integer.parseInt(JOptionPane.showInputDialog("The project number you have entered already exists"));
										if (projectNumber != projectNum_db) {
											equalProjNum = false;
										}
									}
								}
							}
								
							
							String projectName = JOptionPane.showInputDialog("Enter the project name or press 'ok' to create default project name : ");
							
							// Checking if the project name already exists
							results = statement.executeQuery("SELECT ProjectName FROM Projects");
							boolean equalProjName = false;
							while (results.next()) {
								String projectName_db = results.getString("ProjectName");
								if (projectName_db.equalsIgnoreCase(projectName)) {
									equalProjName = true;
									while (equalProjName == true) {
										projectName = JOptionPane.showInputDialog("The project name you have entered already exists. Please enter another project name : ");
										if (projectName != projectName_db) {
											equalProjName = false;
										}
									}
								}
							}
							
							
							String projectType = JOptionPane.showInputDialog("Enter the project type : ");
							String physicalAddress = JOptionPane.showInputDialog("Enter the physical address for the project : ");
							String erfNumber = JOptionPane.showInputDialog("The project number is : " + projectNumber +"\nEnter the ERF number for the project : ");
							double totalFee = Double.parseDouble(JOptionPane.showInputDialog("Enter the total fee for the project : R"));
							double totalAmountPaid = Double.parseDouble(JOptionPane.showInputDialog("Enter the total amount paid by the client for the project : R"));
							String deadline = JOptionPane.showInputDialog("Enter the deadline for the project. (eg.10 September 2020) : ");
							boolean finalised = false;
							String completionDate = null;
				

							// Details for the client object from user input
							String clientName = JOptionPane.showInputDialog("Enter the first name and last name of the client : ");
							String[] clientNameArray = clientName.split(" ",2);
							String clientSurname = clientNameArray[1];
							String clientTelephoneNumber = JOptionPane.showInputDialog("Enter the client's telephone number : ");
							String clientEmailAddress = JOptionPane.showInputDialog("Enter the client's email address : ");
							String clientPhysicalAddress = JOptionPane.showInputDialog("Enter the client's physical address : ");
							//Changing the default values of the fields in the client object
							client = new ProjectPeople(clientName, clientTelephoneNumber, clientEmailAddress, clientPhysicalAddress);
							//Adding to list of clients
							clientList.add(projectNumber-1,client);
							// Adding client information to the database
							rowsAffected = statement.executeUpdate("INSERT INTO projectpeople VALUES(" + projectNumber + ",'Client','" + clientName + "','" + clientTelephoneNumber + "','" + clientEmailAddress + "','" + clientPhysicalAddress + "')" );
				
					
							// Details for the contractor object from user input
							String contractorName = JOptionPane.showInputDialog("Enter the first name and last name of the contractor : ");
							String contractorTelephoneNumber = JOptionPane.showInputDialog("Enter the contractor's telephone number : ");
							String contractorEmailAddress = JOptionPane.showInputDialog("Enter the contractor's email address : ");
							String contractorPhysicalAddress = JOptionPane.showInputDialog("Enter the contractor's physical address : ");
							//Changing the values of the fields in the contractor object
							contractor = new ProjectPeople(contractorName, contractorTelephoneNumber, contractorEmailAddress, contractorPhysicalAddress);
							//Adding to list of contractors
							contractorList.add(projectNumber-1,contractor);
							//Adding Contractor information to the database
							rowsAffected = statement.executeUpdate("INSERT INTO projectpeople VALUES(" + projectNumber + ",'Contractor','" + contractorName + "','" + contractorTelephoneNumber + "','" + contractorEmailAddress + "','" + contractorPhysicalAddress + "')" );
				
					
							// Details for the architect object from user input
							String architectName = JOptionPane.showInputDialog("Enter the first name and last name of the architect : ");
							String architectTelephoneNumber = JOptionPane.showInputDialog("Enter the architect's telephone number : ");
							String architectEmailAddress = JOptionPane.showInputDialog("Enter the architect's email address : ");
							String architectPhysicalAddress = JOptionPane.showInputDialog("Enter the architect's physical address : ");
							//Changing the values of the fields in the architect object
							architect = new ProjectPeople(architectName, architectTelephoneNumber, architectEmailAddress, architectPhysicalAddress);
							//Adding to list of Architects
							architectList.add(projectNumber-1,architect);
							//Adding Architect information to the database
							rowsAffected = statement.executeUpdate("INSERT INTO projectpeople VALUES(" + projectNumber + ",'Architect','" + architectName + "','" + architectTelephoneNumber + "','" + architectEmailAddress + "','" + architectPhysicalAddress + "')" );
				
					
							// Changing the default values of the fields in the project object to the values entered by the user
							
							if (projectName.isEmpty() == true) {
								// This code will execute if the user does not enter a project name
								// If a project name isn't entered, a default project name will be created
								
								String defaultProjectName = projectType + " " + clientSurname;
								//Checking if the default project name already exists
								results = statement.executeQuery("SELECT ProjectName FROM Projects");
								boolean equalDefaultProjName = false;
								while (results.next()) {
									String projectName_db = results.getString("ProjectName");
									if (projectName_db.equalsIgnoreCase(defaultProjectName)) {
										equalDefaultProjName = true;
										while (equalDefaultProjName == true) {
											defaultProjectName = JOptionPane.showInputDialog("The default project name created already exists. Please enter another project name : ");
											if (defaultProjectName != projectName_db) {
												equalDefaultProjName = false;
											}
										}
									}
								}
								
								project = new Projects(projectNumber,defaultProjectName,projectType,physicalAddress,erfNumber,totalFee,totalAmountPaid,deadline,finalised,completionDate);
								//Adding project object to the list
								projectList.add(projectNumber-1,project);
								//Displaying the project details
								System.out.println("The project has been created.\nProject details :");
								System.out.println(project + "\n");
								//Adding the project details to the database
								rowsAffected = statement.executeUpdate("INSERT INTO projects VALUES(" + projectNumber + ",'" +  defaultProjectName + "','" + projectType + "','" + physicalAddress + "','" + erfNumber + "'," + totalFee + "," + totalAmountPaid + ",'" + deadline + "','" + finalised + "','" + completionDate + "')" );
								
					
					
							} else if (projectName.isEmpty() == false) {
								// This code will execute if a project name is entered by the user
								
								project = new Projects(projectNumber,projectName,projectType,physicalAddress,erfNumber,totalFee,totalAmountPaid,deadline,finalised,completionDate);
								//Adding project object to the list
								projectList.add(projectNumber-1,project);
								//Displaying the project details
								System.out.println("\n\nThe project has been created.\nProject details :");
								System.out.println(project + "\n");
								//Adding the project details to the database
								rowsAffected = statement.executeUpdate("INSERT INTO projects VALUES(" + projectNumber + ",'" +  projectName + "','" + projectType + "','" + physicalAddress + "','" + erfNumber + "'," + totalFee + "," + totalAmountPaid + ",'" + deadline + "','" + finalised + "','" + completionDate + "')" );
							
							}
				
							//Displaying the client, contractor and architect details
							System.out.println("Client details :");
							System.out.println(client + "\n");
							System.out.println("Contractor details :");
							System.out.println(contractor + "\n");
							System.out.println("Architect details :");
							System.out.println(architect + "\n");
							// Displaying the main menu again
							option = menuOption();
					
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("Please make sure that you enter the full name (name and surname) of the client.");
							option = menuOption();
						}
						
						
//****************************************************** OPTION 2 ************************************************************************************************************************************************************************************************************************************************
				
				
					} else if (option == 2) {
						// This option allows the user to set a new deadline for the project
						
						// This method displays all the projects details from the respective lists
						displayListProjectDetails(projectList, clientList, contractorList, architectList);
						
						// The must user must choose whether to select a project they want to edit by entering the project number or the project name				
						int selectionChoice = Integer.parseInt(JOptionPane.showInputDialog("Search for the project you would like to edit the deadline for by : \n1\t- Entering the project number \n2\t- Entering the project name \nEnter the number of the option you wish to select : "));
						 
						if (selectionChoice == 1) {
							// This option will execute if the user chooses to select a project by entering the project number
							
							int projectNum_input = Integer.parseInt(JOptionPane.showInputDialog("Enter the project number of the project you would like to edit the deadline for : "));
							
							// Create a counter variable to keep track of how many projects the loop below has iterated through
							int countProjects = 0;
							for (int j = 0; j < projectList.size(); j++) {
								countProjects += 1;
								// Getting all the project details from the list
								int projectNumber_List = projectList.get(j).getProjectNumber();
								
								if(projectNum_input == projectNumber_List) {
									// The deadline before it is changed
									String initialDeadline = projectList.get(j).getDeadline();
									
									// Getting new deadline from user input
									String newDeadline = JOptionPane.showInputDialog("The current deadline for this project is : " + initialDeadline + "\nEnter the new deadline you wish to set for the project (eg.10 September 2020) : ");
									
									// Updating the deadline in the projectList
									projectList.get(j).setNewDeadline(newDeadline);
									
									// Updating the deadline in the database
									rowsAffected = statement.executeUpdate("UPDATE projects SET Deadline = '" + newDeadline + "' WHERE ProjectNumber = " + projectNum_input);
									
									System.out.println("\nThe deadline of the project has been changed to : " + projectList.get(j).getDeadline());
									break;
									
								} else if (projectNum_input != projectNumber_List && countProjects == projectList.size()) {
									System.out.println("\nThe project number you have entered does not exist.");
								}
							}
							
							// Displaying the main menu again
							option = menuOption();
						
						
						} else if (selectionChoice == 2) {
							// This option will execute if the user chooses to select a project by entering the project name
							String projectName_input = JOptionPane.showInputDialog("Enter the project name of the project your would like to edit the deadline for : ");
							
							// Create a counter variable to keep track of how many projects the loop below has iterated through
							int countProjects = 0;
							for (int j = 0; j < projectList.size(); j++) {
								countProjects += 1;
								// Getting the required project details from the list
								String projectName_List = projectList.get(j).getProjectName();
								
								if(projectName_input.equalsIgnoreCase(projectName_List)){
									// The deadline before it is changed
									String initialDeadline = projectList.get(j).getDeadline();
									
									String newDeadline = JOptionPane.showInputDialog("The current deadline for this project is : " + initialDeadline + "\nEnter the new deadline you wish to set for the project (eg. 10 September 2020) : ");
									
									// Updating the deadline
									projectList.get(j).setNewDeadline(newDeadline);
									
									// Updating the deadline in the database
									rowsAffected = statement.executeUpdate("UPDATE projects SET Deadline = '" + newDeadline + "' WHERE ProjectName = '" + projectName_input + "'");
									
									System.out.println("\nThe deadline of the project has been changed to : " + projectList.get(j).getDeadline());
									break;
									
								} else if (projectName_input != projectName_List && countProjects == projectList.size()) {
									System.out.println("\nThe project name you have entered does not exist.");
								}
							}
							
							// Displaying the main menu again
							option = menuOption();
						}
				
			
			
	//************************************************** OPTION 3 ************************************************************************************************************
					} else if (option == 3){
						// Changing the total amount paid by the client
						// This option allows the total amount paid by the client to be updated every time the client pays a partial amount
						
						// This method displays all the projects details from the respective lists
						displayListProjectDetails(projectList, clientList, contractorList, architectList);
						
						// The must user must choose whether to select a project they want to edit by entering the project number or the project name
						int selectionChoice = Integer.parseInt(JOptionPane.showInputDialog("Search for the project you would like to edit the total amount paid by the client for by : \n1\t- Entering the project number \n2\t- Entering the project name \nEnter the number of the option you wish to select : "));
						
						if (selectionChoice == 1) {
							// This option will execute if the user chooses to select a project by entering the project number
							
							int projectNum_input = Integer.parseInt(JOptionPane.showInputDialog("Enter the project number of the project you would like to edit the total amount paid for : "));
							
							// Create a counter variable to keep track of how many projects the loop below has iterated through
							int countProjects = 0;
							
							for (int j = 0; j < projectList.size(); j++) {
								
								countProjects += 1;
								
								// Getting all the project details from the list
								int projectNumber_List = projectList.get(j).getProjectNumber();
								
								if(projectNum_input == projectNumber_List) {
									
									// The total amount paid before it is changed
									double initialAmountPaid = projectList.get(j).getTotalAmountPaid();
									
									//Getting the new total amount paid from the user
									double amountPaidNow = Integer.parseInt(JOptionPane.showInputDialog("The total amount paid by the client for this project thus far is : R" + initialAmountPaid + "\nEnter the additonal amount paid by the client for the project : R"));
									
									// Updating the total amount paid in the project list
									projectList.get(j).setNewTotalAmountPaid(amountPaidNow);
									
									// Updating the deadline in the database
									rowsAffected = statement.executeUpdate("UPDATE projects SET TotalAmountPaid = " + (initialAmountPaid + amountPaidNow) + " WHERE ProjectNumber = " + projectNum_input);
									
									System.out.println("\nThe total amount paid for the project has been updated to : R" + projectList.get(j).getTotalAmountPaid());
									break;
									
								} else if (projectNum_input != projectNumber_List && countProjects == projectList.size()) {
									System.out.println("\nThe project number you have entered does not exist.");
								}
							}
							
							// Displaying the main menu again
							option = menuOption();
						
					
						} else if (selectionChoice == 2) {
							// This option will execute if the user chooses to select a project by entering the project name
							
							String projectName_input = JOptionPane.showInputDialog("Enter the project name of the project you would like to edit the total amount paid for : ");
							
							// Create a counter variable to keep track of how many projects the loop below has iterated through
							int countProjects = 0;
							for (int j = 0; j < projectList.size(); j++) {
								countProjects += 1;
								// Getting the required project details from the list
								String projectName_List = projectList.get(j).getProjectName();
								
								if(projectName_input.equalsIgnoreCase(projectName_List)){
									
									// The total amount paid before it is changed
									double initialAmountPaid = projectList.get(j).getTotalAmountPaid();
									
									//Getting the new total amount paid from the user
									double amountPaidNow = Integer.parseInt(JOptionPane.showInputDialog("The total amount paid by the client for this project thus far is : " + initialAmountPaid + "\nEnter the additonal amount paid by the client for the project : "));
									
									// Updating the total amount paid in the project list
									projectList.get(j).setNewTotalAmountPaid(amountPaidNow);
									
									// Updating the deadline in the database
									rowsAffected = statement.executeUpdate("UPDATE projects SET TotalAmountPaid = " + (initialAmountPaid + amountPaidNow) + " WHERE ProjectName = '" + projectName_input + "'");
									
									System.out.println("\nThe total amount paid for the project has been updated to : R" + projectList.get(j).getTotalAmountPaid());
									break;
									
								} else if (projectName_input != projectName_List && countProjects == projectList.size()) {
									System.out.println("\nThe project name you have entered does not exist.");
								}
							}
							
							// Displaying the main menu again
							option = menuOption();
						}
			
						
			
	//*********************************************** OPTION 4 ******************************************************************
					} else if (option == 4) {
						// This option allows the user to edit the contractor's contact details
						// The user can either edit the contractor's telephone number or email address
						
						// This method displays all the projects details from the respective lists
						displayListProjectDetails(projectList, clientList, contractorList, architectList);
						
					
						int contractorOption = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of the option you wish to select :\n1\t- Edit contractor's telephone number\n2\t- Edit the contractor's email address\nEnter an option number to continue : "));
						
						if (contractorOption == 1) {
							// This option will run if the user chooses to edit a contractors telephone number
								
							int ProjectNum_input = Integer.parseInt(JOptionPane.showInputDialog("Enter the project number of the project you would like to edit the contractor's telephone number for : "));
							
							// Create a counter variable to keep track of how many projects the loop below has iterated through
							int countProjects = 0;
							for (int j = 0; j < projectList.size(); j++) {
								
								countProjects += 1;
								
								int projectNum_List = projectList.get(j).getProjectNumber();
							
								// If the project number exists in the project list, then the contractor for that specific project is stored in the position 'projectNumber-1' = 'j' in the contractor list
								if(ProjectNum_input == projectNum_List) {
										
									// The contractor's telephone number before it is changed
									String initialContractorTelephoneNumber = contractorList.get(j).getTelephoneNumber();
										
									//Getting the new contractor's telephone number from the user
									String newContractorTelephoneNumber = JOptionPane.showInputDialog("The number currently stored for the contractor is : " + initialContractorTelephoneNumber + "\nEnter the new telephone number for the contractor : ");
										
									// Updating the contractor's telephone number in the contractor list
									contractorList.get(j).setNewTelephoneNumber(newContractorTelephoneNumber);
									
									// Updating the deadline in the database
									rowsAffected = statement.executeUpdate("UPDATE projectpeople SET TelephoneNumber = '" + newContractorTelephoneNumber + "' WHERE ProjectNumber = " + ProjectNum_input + " AND PersonType = 'Contractor'");
										
									System.out.println("\nThe new number for the contractor has been updated to : " + contractorList.get(j).getTelephoneNumber());
									break;
									
								} else if (ProjectNum_input != projectNum_List && countProjects == projectList.size()) {
									System.out.println("\nThe project number you have entered does not exist.");
								}
							}
							// Displaying the main menu again
							option = menuOption();
							
							
						} else if (contractorOption == 2) {
							// This option will run if the user chooses to edit a contractors email address
								
								int ProjectNum_input = Integer.parseInt(JOptionPane.showInputDialog("Enter the project number of the project you would like to edit the contractor's email address for : "));
								
								// Create a counter variable to keep track of how many projects the loop below has iterated through
								int countProjects = 0;
								for (int j = 0; j < projectList.size(); j++) {
									
									countProjects += 1;
									
									int projectNum_List = projectList.get(j).getProjectNumber();
							
									// If the project number exists in the project list, then the contractor for that specific project is stored in the position 'projectNumber-1' = 'j' in the contractor list
									if(ProjectNum_input == projectNum_List) {
										
										// The contractor's email address before it is changed
										String initialContractorEmailAddress = contractorList.get(j).getEmailAddress();
										
										//Getting the new contractor's email address from the user
										String newContractorEmailAddress = JOptionPane.showInputDialog("The email address currently stored for the contractor is : " + initialContractorEmailAddress + "\nEnter the new email address for the contractor : ");
										
										// Updating the contractor's email address in the contractor list
										contractorList.get(j).setNewEmailAddress(newContractorEmailAddress);
										
										// Updating the deadline in the database
										rowsAffected = statement.executeUpdate("UPDATE projectpeople SET EmailAddress = '" + newContractorEmailAddress + "' WHERE ProjectNumber = " + ProjectNum_input + " AND PersonType = 'Contractor'");
										
										System.out.println("\nThe new email address for the contractor has been updated to : " + contractorList.get(j).getEmailAddress());
										break;
										
									} else if (ProjectNum_input != projectNum_List && countProjects == projectList.size()) {
										System.out.println("\nThe project number you have entered does not exist.");
									}
								
								}
								// Displaying the main menu again
								option = menuOption();
							
						}
						
			
			
	//********************************************* OPTION 5 *******************************************************************************************************************************************************************************************************************************
					} else if (option == 5) {
						// This option allows the user to finalize a project.
						// When a project is finalized, the program checks if the client has paid the full fees or not
						// If the full fees has been paid the program will output a message saying so
						// Else an invoice will be generated for the client
						// All the information about the project will be written to a text file called "CompletedProject.txt"
						
						// Displaying the list of projects to the user in the console
						displayListProjectDetails(projectList, clientList, contractorList, architectList);
						
						// The must user must choose whether to select a project they want to edit by entering the project number or the project name
						int selectionChoice = Integer.parseInt(JOptionPane.showInputDialog("Search for the project you would like to finalise by : \n1\t- Entering the project number \n2\t- Entering the project name \nEnter the number of the option you wish to select : "));
						
						if (selectionChoice == 1) {
							// This option will execute if the user chooses to select a project by entering the project number
							
							int projectNum_input = Integer.parseInt(JOptionPane.showInputDialog("Enter the project number of the project you want to finalise : "));
							
							// Create a counter variable to keep track of how many projects the loop below has iterated through
							int countProjects = 0;
							for (int j = 0; j < projectList.size(); j++) {
								
								countProjects += 1;
								
								// Getting all the project details from the list
								int projectNumber_List = projectList.get(j).getProjectNumber();
								boolean finalised_List = projectList.get(j).getFinalised();
								
								
								if(projectNum_input == projectNumber_List) {
									
									if (finalised_List == false) {// Checking if the project has already been finalised
										
										// Setting the boolean value finalised to true in the list
										projectList.get(j).setNewFinalised(true);
										
										// Updating the finalised in the database
										rowsAffected = statement.executeUpdate("UPDATE projects SET Finalised = '" + true + "' WHERE ProjectNumber = " + projectNum_input);
								
										//Getting all the project information to write to the text file
										//Project details
										double amountPaid = projectList.get(j).getTotalAmountPaid();
										double totalFee = projectList.get(j).getTotalFee();
									
										if (amountPaid == totalFee) {
											System.out.println("The client has paid the full fees for the project.");
								
								
										} else {
											// Client details
											String clientName = clientList.get(j).getName();
											String clientTelephoneNumber = clientList.get(j).getTelephoneNumber();
											String clientEmailAddress = clientList.get(j).getEmailAddress();
											String clientPhysicalAddress = clientList.get(j).getPeoplePhysicalAddress();
											String completionDate = JOptionPane.showInputDialog("Enter the completion date of the project : ");
											double amountStillToPay = totalFee - amountPaid;
											
											// Updating the completionDate in the database
											rowsAffected = statement.executeUpdate("UPDATE projects SET CompletionDate = '" + completionDate + "' WHERE ProjectNumber = " + projectNum_input);
											
											project.setNewCompletionDate(completionDate);
											projectList.get(j).setNewCompletionDate(completionDate);
											
											System.out.println("\nInvoice Number : " + projectList.get(j).getERFNumber());
											System.out.println("\nClient's name : " + clientName + "\nClient's telephone number : " + clientTelephoneNumber + "\nClient's email address : " + clientEmailAddress + "\nClient's physical address : " + clientPhysicalAddress + "\nAmount still outstanding : R" + amountStillToPay);
											
									
										}
									
									}else if (finalised_List == true) {
										System.out.println("\n\nThis project has already been finalised.");
									}
								
									break;
									
								} else if (projectNum_input != projectNumber_List && countProjects == projectList.size()) {
									System.out.println("\nThe project number you have entered does not exist.");
								}
								
							}
							
							// Displaying the main menu again
							option = menuOption();
						
						
						} else if (selectionChoice == 2) {
							// This option will execute if the user chooses to select a project by entering the project name
							
							String projectName_input = JOptionPane.showInputDialog("Enter the project name of the project you want to finalise : ");
							
							// Create a counter variable to keep track of how many projects the loop below has iterated through
							int countProjects = 0;
							for (int j = 0; j < projectList.size(); j++) {
								
								countProjects += 1;
				
								//Getting all the project information
								String projectName_List = projectList.get(j).getProjectName();
								boolean finalised_List = projectList.get(j).getFinalised();
								
								if(projectName_input.equalsIgnoreCase(projectName_List)){
									
									if (finalised_List == false) {// Checking if the project has already been finalised
										
										// Setting the boolean value finalised to true in the list
										projectList.get(j).setNewFinalised(true);
									
										// Updating the finalised in the database
										rowsAffected = statement.executeUpdate("UPDATE projects SET Finalised = '" + true + "' WHERE ProjectName = '" + projectName_input + "'");
									
									
										//Getting all the project information to write to the text file
										//Project details
										double amountPaid = projectList.get(j).getTotalAmountPaid();
										double totalFee = projectList.get(j).getTotalFee();
									
										if (amountPaid == totalFee) {
											System.out.println("The client has paid the full fees for the project.");
									
										} else {
											// Client details
											String clientName = clientList.get(j).getName();
											String clientTelephoneNumber = clientList.get(j).getTelephoneNumber();
											String clientEmailAddress = clientList.get(j).getEmailAddress();
											String clientPhysicalAddress = clientList.get(j).getPeoplePhysicalAddress();
											String completionDate = JOptionPane.showInputDialog("Enter the completion date of the project : ");
											double amountStillToPay = totalFee - amountPaid;
											
											// Updating the completionDate in the database
											rowsAffected = statement.executeUpdate("UPDATE projects SET CompletionDate = '" + completionDate + "' WHERE ProjectName = '" + projectName_input + "'");
											
											project.setNewCompletionDate(completionDate);
											projectList.get(j).setNewCompletionDate(completionDate);
											
											System.out.println("\nInvoice Number : " + projectList.get(j).getERFNumber());
											System.out.println("\nClient's name : " + clientName + "\nClient's telephone number : " + clientTelephoneNumber + "\nClient's email address : " + clientEmailAddress + "\nClient's physical address : " + clientPhysicalAddress + "\nAmount still outstanding : R" + amountStillToPay);
										
										}
									
									}else if (finalised_List == true) {
										System.out.println("\n\nThis project has already been finalised.");
									}
									
									break;
								
								} else if (projectName_input != projectName_List && countProjects == projectList.size()) {
									System.out.println("\nThe project name you have entered does not exist.");
								}
							}
							
							// Displaying the main menu again
							option = menuOption();
						}
						
						
						
	//*********************************************** OPTION 6 *********************************************************************************************************************************************************************************************************************************************************************************	
					} else if (option == 6) {
						// This option allows the user to view a list of projects that still need to be completed
						
						// Displaying all the incomplete projects in an easy to read manor
						displayIncompleteProjectDetails(incompleteProjectsList, incompleteClientList, incompleteContractorList, incompleteArchitectList);
						option = menuOption();
				
						
					
	//*********************************************** OPTION 7 ********************************************************************************************************************************************************************************************************************************************************************************
					} else if (option == 7) {
						
						// Determining whether a project is overdue or not and adding it the over lists if it is.
						
						for (int countOverdue = 0; countOverdue < incompleteProjectsList.size(); countOverdue ++) {
							
							try {
								// Getting current date
								Date currentDate = new Date();
								
								// Deadline from incomplete projects list
								String deadline_Incomp_List = incompleteProjectsList.get(countOverdue).getDeadline();
								
								// Converting the string date to date format
								DateFormat format = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH);
								Date deadline_Date = format.parse(deadline_Incomp_List);
								
								// If the current date is passed the deadline, the project will be added to the overdue list
								if (deadline_Date.compareTo(currentDate) < 0) {
									overdueProjectsList.add(incompleteProjectsList.get(countOverdue));
									overdueClientList.add(incompleteClientList.get(countOverdue));
									overdueContractorList.add(incompleteContractorList.get(countOverdue));
									overdueArchitectList.add(incompleteArchitectList.get(countOverdue));	
								}
					
							} catch (java.text.ParseException e) {
								e.printStackTrace();
							}
						}
						
						// Displaying the details of all the overdue projects
						displayOverdueProjectDetails(overdueProjectsList, overdueClientList, overdueContractorList, overdueArchitectList);
						option = menuOption();

//************************************************* OPTION 8 ******************************************************************************************************************************************************************************************************************************************************************************						
					} else if (option == 8) {
						//This option allows the user to search for a project by either entering the project number or project name
						
						int selectionChoice = Integer.parseInt(JOptionPane.showInputDialog("Would you like to search for a project by entering :\n1 - The project number \n2 - The project name \nEnter the number of the option you wish to select : "));
						
						if (selectionChoice == 1) {
							int projectNumber_input = Integer.parseInt(JOptionPane.showInputDialog("Enter the project number of the project you want to search for : "));
							
							// Searching for the project in the project list
							// Create a counter variable to keep track of how many projects the loop below has iterated through
							int countProjects = 0;
							for (int j = 0; j < projectList.size(); j++) {
								countProjects += 1;
								// Getting all the project details from the list
								int projectNumber_List = projectList.get(j).getProjectNumber();
								
								if(projectNumber_input == projectNumber_List) {
									
									// Displaying the list in an easy to read manor
									System.out.println("\n\nPROJECT DETAILS : " + projectList.get(j));
									System.out.println("\nCLIENT DETAILS FOR PROJECT " + projectList.get(j).getProjectNumber() + " : " + clientList.get(j));
									System.out.println("\nCONTRACTOR DETAILS FOR PROJECT " + projectList.get(j).getProjectNumber() + " : " + contractorList.get(j));
									System.out.println("\nARCHITECT DETAILS FOR PROJECT " + projectList.get(j).getProjectNumber() + " : " + architectList.get(j));
									break;
								
								} else if (projectNumber_input != projectNumber_List && countProjects == projectList.size()) {
									System.out.println("\nThe project number you have entered does not exist.");
								}
							}
							
							// Displaying the main menu again
							option = menuOption();
						
						} else if (selectionChoice == 2) {
							String projectName_input = JOptionPane.showInputDialog("Enter the project name of the project you want to search for : ");
							
							// Searching for the project in the project list
							// Create a counter variable to keep track of how many projects the loop below has iterated through
							int countProjects = 0;
							for (int j = 0; j < projectList.size(); j++) {
								countProjects += 1;
								// Getting all the project details from the list
								String projectName_List = projectList.get(j).getProjectName();
								
								if(projectName_input.equalsIgnoreCase(projectName_List)) {
									
									// Displaying the list in an easy to read manor
									System.out.println("\n\nPROJECT DETAILS : " + projectList.get(j));
									System.out.println("\nCLIENT DETAILS FOR PROJECT " + projectList.get(j).getProjectNumber() + " : " + clientList.get(j));
									System.out.println("\nCONTRACTOR DETAILS FOR PROJECT " + projectList.get(j).getProjectNumber() + " : " + contractorList.get(j));
									System.out.println("\nARCHITECT DETAILS FOR PROJECT " + projectList.get(j).getProjectNumber() + " : " + architectList.get(j));
									break;
								
								} else if (projectName_input != projectName_List && countProjects == projectList.size()) {
									System.out.println("\nThe project number you have entered does not exist.");
								}
							}
							
							// Displaying the main menu again
							option = menuOption();
						}
						
//************************************************ OPTION 9 *******************************************************************************************************************************************************************************************************************************************************************************			
					} else if (option == 9) {
						// This option allows the user to exit the program by breaking out of the while loop
						System.out.print("Goodbye");
						break;
					
						
						
//**********************************************************************************************************************************************************************************************************************************************************************************************************************************
					} else {
						// This will execute if the user enters anything else except the numbers "1,2,3,4,5,6,7,8" for the main menu option
						System.out.println("The value you have entered isn't recognised! Please enter a number corresponding to an option from the menu.");
						option = menuOption();
					}
					
				}
				System.out.print("Goodbye");
				
				results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		} catch(NumberFormatException e) {// option - main menu catch block
			System.out.println("Error! You have not entered a number from the menu.");
		}

	}



	
	
//*****************************************************************************************************************************************************************************************************************************************************************************************************************************
	// METHODS
	
	
	/**This method populates the projectList with values from the database poisepms_db
	 * 
	 * @param projectList stores all the details of existing projects
	 * @param statement direct line to database to run sql queries
	 * @throws SQLException type of exception
	 */
	public static void populateProjectList(ArrayList<Projects> projectList, Statement statement) throws SQLException {
		Projects project;
		ResultSet results;
		results = statement . executeQuery ( "SELECT * FROM Projects" );
		while (results.next()) {
			int projectNumber_db = results.getInt("ProjectNumber");
			String projectName_db = results.getString("ProjectName");
			String projectType_db = results.getString("ProjectType");
			String projectPhysicalAddress_db = results.getString("ProjectAdress");
			String erfNumber_db = results.getString("ERFNumber");
			double TotalFee_db = results.getDouble("TotalFee");
			double TotalAmountPaid_db = results.getDouble("TotalAmountPaid");
			String Deadline_db = results.getString("Deadline");
			boolean Finalised_db = Boolean.parseBoolean(results.getString("Finalised"));
			String CompletionDate_db = results.getString("CompletionDate");
			// The code below is very important - it prevents the overwriting of information already stored in the list
			project = new Projects(projectNumber_db,projectName_db,projectType_db,projectPhysicalAddress_db,erfNumber_db,TotalFee_db,TotalAmountPaid_db,Deadline_db,Finalised_db,CompletionDate_db);
			
			projectList.add(projectNumber_db-1,project);	
		}
	}
	
	
	/**This method populates the clientList with values from the database poisepms_db
	 * 
	 * @param clientList stores all the details of the clients
	 * @param statement direct line to database to run sql queries
	 * @throws SQLException type of exception
	 */
	public static void populateClientList(ArrayList<ProjectPeople> clientList, Statement statement)throws SQLException {
		ProjectPeople client;
		ResultSet results;
		results = statement.executeQuery("SELECT * FROM projectpeople WHERE PersonType = 'Client'");
		while (results.next()) {
			int projectNumber_db = results.getInt("ProjectNumber");
			String clientName_db = results.getString("PersonName");
			String clientTelephoneNumber_db = results.getString("TelephoneNumber");
			String clientEmailAddress_db = results.getString("EmailAddress");
			String clientPhysicalAddress_db = results.getString("PersonAdress");
			
			// The line below prevents the overwriting of data that is already in the list
			client = new ProjectPeople(clientName_db,clientTelephoneNumber_db,clientEmailAddress_db,clientPhysicalAddress_db);
			
			clientList.add(projectNumber_db-1,client);
		}
	}
	
	
	/** This method populates the contractorList with values from the database poisepms_db
	 * 
	 * @param contractorList stores all the contractor details
	 * @param statement direct line to database to run sql queries
	 * @throws SQLException type of exception
	 */
	public static void populateContractorList(ArrayList<ProjectPeople> contractorList, Statement statement)
			throws SQLException {
		ProjectPeople contractor;
		ResultSet results;
		results = statement.executeQuery("SELECT * FROM projectpeople WHERE PersonType = 'Contractor'");
		while (results.next()) {
			int projectNumber_db = results.getInt("ProjectNumber");
			String contractorName_db = results.getString("PersonName");
			String contractorTelephoneNumber_db = results.getString("TelephoneNumber");
			String contractorEmailAddress_db = results.getString("EmailAddress");
			String contractorPhysicalAddress_db = results.getString("PersonAdress");
			
			// The line below prevents the overwriting of data that is already in the list
			contractor = new ProjectPeople(contractorName_db,contractorTelephoneNumber_db,contractorEmailAddress_db,contractorPhysicalAddress_db);
			
			contractorList.add(projectNumber_db-1,contractor);
			
		}
	}
	
	
	/** This method populates the architectList with values from the database poisepms_db
	 * 
	 * @param architectList stores all the architect details
	 * @param statement direct line to database to run sql queries
	 * @throws SQLException type of exception
	 */
	public static void populateArchitectList(ArrayList<ProjectPeople> architectList, Statement statement)
			throws SQLException {
		ProjectPeople architect;
		ResultSet results;
		results = statement.executeQuery("SELECT * FROM projectpeople WHERE PersonType = 'Architect'");
		while (results.next()) {
			int projectNumber_db = results.getInt("ProjectNumber");
			String architectName_db = results.getString("PersonName");
			String architectTelephoneNumber_db = results.getString("TelephoneNumber");
			String architectEmailAddress_db = results.getString("EmailAddress");
			String architectPhysicalAddress_db = results.getString("PersonAdress");
			
			// The line below prevents the overwriting of data that is already in the list
			architect = new ProjectPeople(architectName_db,architectTelephoneNumber_db,architectEmailAddress_db,architectPhysicalAddress_db);
			
			architectList.add(projectNumber_db-1,architect);
		}
	}
	
	/** This method populates the incompleteProjectsList with values from the database poisepms_db
	 * 
	 * @param project project object
	 * @param incompleteProjectsList stores list of incomplete project details
	 * @param statement direct line to database to run sql queries
	 * @return project project object 
	 * @throws SQLException type of exception for sql statements
	 */
	public static Projects populateIncompleteProjectsList(Projects project, ArrayList<Projects> incompleteProjectsList,
			Statement statement) throws SQLException {
		ResultSet results;
		results = statement.executeQuery("SELECT * FROM projects WHERE Finalised = 'false'");
		while (results.next()) {
			int projectNumber_db = results.getInt("ProjectNumber");
			String projectName_db = results.getString("ProjectName");
			String projectType_db = results.getString("ProjectType");
			String projectPhysicalAddress_db = results.getString("ProjectAdress");
			String erfNumber_db = results.getString("ERFNumber");
			double TotalFee_db = results.getDouble("TotalFee");
			double TotalAmountPaid_db = results.getDouble("TotalAmountPaid");
			String Deadline_db = results.getString("Deadline");
			boolean Finalised_db = Boolean.parseBoolean(results.getString("Finalised"));
			String CompletionDate_db = results.getString("CompletionDate");
			// The code below is very important - it prevents the overwriting of information already stored in the list
			project = new Projects(projectNumber_db,projectName_db,projectType_db,projectPhysicalAddress_db,erfNumber_db,TotalFee_db,TotalAmountPaid_db,Deadline_db,Finalised_db,CompletionDate_db);
			
			incompleteProjectsList.add(project);	
		}
		return project;
	}
	
	
	/** This method populates the incompleteClientList with values from the database poisepms_db
	 * 
	 * @param incompleteClientList stores client details for incomplete projects
	 * @param statement direct line to database to run sql queries
	 * @throws SQLException type of exception for sql statements
	 */
	public static void populateIncompleteClientList(ArrayList<ProjectPeople> incompleteClientList, Statement statement)
			throws SQLException {
		ProjectPeople client;
		ResultSet results;
		results = statement.executeQuery("SELECT * FROM projectpeople WHERE PersonType = 'Client'");
		while (results.next()) {
			int projectNumber_db = results.getInt("ProjectNumber");
			String clientName_db = results.getString("PersonName");
			String clientTelephoneNumber_db = results.getString("TelephoneNumber");
			String clientEmailAddress_db = results.getString("EmailAddress");
			String clientPhysicalAddress_db = results.getString("PersonAdress");
			
			// The line below prevents the overwriting of data that is already in the list
			client = new ProjectPeople(clientName_db,clientTelephoneNumber_db,clientEmailAddress_db,clientPhysicalAddress_db);
			
			incompleteClientList.add(client);
		}
	}
	
	
	/** This method populates the incompleteContractorList with values from the database poisepms_db
	 * 
	 * @param incompleteContractorList stores contractor details for incomplete projects
	 * @param statement direct line to database to run sql queries
	 * @throws SQLException type of exception for sql statements
	 */
	public static void populateIncompleteContractorList(ArrayList<ProjectPeople> incompleteContractorList,
			Statement statement) throws SQLException {
		ProjectPeople contractor;
		ResultSet results;
		results = statement.executeQuery("SELECT * FROM projectpeople WHERE PersonType = 'Contractor'");
		while (results.next()) {
			int projectNumber_db = results.getInt("ProjectNumber");
			String contractorName_db = results.getString("PersonName");
			String contractorTelephoneNumber_db = results.getString("TelephoneNumber");
			String contractorEmailAddress_db = results.getString("EmailAddress");
			String contractorPhysicalAddress_db = results.getString("PersonAdress");
			
			// The line below prevents the overwriting of data that is already in the list
			contractor = new ProjectPeople(contractorName_db,contractorTelephoneNumber_db,contractorEmailAddress_db,contractorPhysicalAddress_db);
			
			incompleteContractorList.add(contractor);
			
		}
	}
	
	
	/**  This method populates the incompleteArchitectList with values from the database poisepms_db
	 * 
	 * @param incompleteArchitectList stores architect details for incomplete projects
	 * @param statement direct line to database to run sql queries
	 * @return results details of clients for incomplete projects
	 * @throws SQLException type of exception for sql statements
	 */
	public static ResultSet populateIncompleteArchitectList(ArrayList<ProjectPeople> incompleteArchitectList,
			Statement statement) throws SQLException {
		ProjectPeople architect;
		ResultSet results;
		results = statement.executeQuery("SELECT * FROM projectpeople WHERE PersonType = 'Architect'");
		while (results.next()) {
			int projectNumber_db = results.getInt("ProjectNumber");
			String architectName_db = results.getString("PersonName");
			String architectTelephoneNumber_db = results.getString("TelephoneNumber");
			String architectEmailAddress_db = results.getString("EmailAddress");
			String architectPhysicalAddress_db = results.getString("PersonAdress");
			
			// The line below prevents the overwriting of data that is already in the list
			architect = new ProjectPeople(architectName_db,architectTelephoneNumber_db,architectEmailAddress_db,architectPhysicalAddress_db);
			
			incompleteArchitectList.add(architect);
		}
		return results;
	}
	
		
	/** This method displays all the project details from the projectList, clientList, contractorList and architectList in an easy to read manor
	* 
	* @param projectList stores all the existing project details
	* @param clientList stores all the client details for the existing projects
	* @param contractorList stores all the contractor details for the existing projects
	* @param architectList stores all the architect details for the existing projects
	*/
	public static void displayListProjectDetails(ArrayList<Projects> projectList, ArrayList<ProjectPeople> clientList,
	ArrayList<ProjectPeople> contractorList, ArrayList<ProjectPeople> architectList) {
		for (int i = 0; i < projectList.size(); i++) {
			// Displaying the list in an easy to read manor
			System.out.println("\n\nPROJECT DETAILS : " + projectList.get(i));
			System.out.println("\nCLIENT DETAILS FOR PROJECT " + projectList.get(i).getProjectNumber() + " : " + clientList.get(i));
			System.out.println("\nCONTRACTOR DETAILS FOR PROJECT " + projectList.get(i).getProjectNumber() + " : " + contractorList.get(i));
			System.out.println("\nARCHITECT DETAILS FOR PROJECT " + projectList.get(i).getProjectNumber() + " : " + architectList.get(i));	
		}
	}
		
		
		
	/** This method displays all the incomplete project details from the incompleteProjectList, incompleteClientList, incompleteContractorList and incompleteArchitectList in an easy to read manor
	* 
	* @param incompleteProjectsList stores all the incomplete project details
	* @param incompleteClientList stores all the client details for the incomplete projects
	* @param incompleteContractorList stores all the contractor details for the incomplete projects
	* @param incompleteArchitectList stores all the architect details for the incomplete projects
	*/
	public static void displayIncompleteProjectDetails(ArrayList<Projects> incompleteProjectsList, ArrayList<ProjectPeople> incompleteClientList,
	ArrayList<ProjectPeople> incompleteContractorList, ArrayList<ProjectPeople> incompleteArchitectList) {
		for (int i = 0; i < incompleteProjectsList.size(); i++) {
			// Displaying the list in an easy to read manor
			System.out.println("\n\nINCOMPLETE PROJECT DETAILS : " + incompleteProjectsList.get(i));
			System.out.println("\nCLIENT DETAILS FOR INCOMPLETE PROJECT " + incompleteProjectsList.get(i).getProjectNumber() + " : " + incompleteClientList.get(i));
			System.out.println("\nCONTRACTOR DETAILS FOR INCOMPLETE PROJECT " + incompleteProjectsList.get(i).getProjectNumber() + " : " + incompleteContractorList.get(i));
			System.out.println("\nARCHITECT DETAILS FOR INCOMPLETE PROJECT " + incompleteProjectsList.get(i).getProjectNumber() + " : " + incompleteArchitectList.get(i));	
		}
	}
			
			
			
	/** This method displays all the overdue project details from the overdueProjectList, overdueClientList, overdueContractorList and overdueArchitectList in an easy to read manor
	* 
	* @param overdueProjectsList stores all the overdue project details
	* @param overdueClientList stores all the client details for the overdue projects
	* @param overdueContractorList stores all the contractor details for the overdue projects
	* @param overdueArchitectList stores all the architect details for the overdue projects
	*/
	public static void displayOverdueProjectDetails(ArrayList<Projects> overdueProjectsList, ArrayList<ProjectPeople> overdueClientList,
	ArrayList<ProjectPeople> overdueContractorList, ArrayList<ProjectPeople> overdueArchitectList) {
		for (int i = 0; i < overdueProjectsList.size(); i++) {
			// Displaying the list in an easy to read manor
			System.out.println("\n\nOVERDUE PROJECT DETAILS : " + overdueProjectsList.get(i));
			System.out.println("\nCLIENT DETAILS FOR OVERDUE PROJECT " + overdueProjectsList.get(i).getProjectNumber() + " : " + overdueClientList.get(i));
			System.out.println("\nCONTRACTOR DETAILS FOR OVERDUE PROJECT " + overdueProjectsList.get(i).getProjectNumber() + " : " + overdueContractorList.get(i));
			System.out.println("\nARCHITECT DETAILS FOR OVERDUE PROJECT " + overdueProjectsList.get(i).getProjectNumber() + " : " + overdueArchitectList.get(i));	
		}
	}
		
		
		
	/** This method is used to display the main menu to the user when it is called
	* 
	* @return option the number of the option the user has entered
	*/
	public static Integer menuOption() {
		int option = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of the option you would like to select :\n1\t- Create a new project\n2\t- Change the due date of a project\n3\t- Change the total amount paid for a project\n4\t- Update a contractor's contact details\n5\t- Finalise a project\n6\t- View a list of projects that still need to be completed\n7\t- View a list of projects that are passed the due date \n8\t- Search for a project\n9\t- Exit\nEnter an option number to continue : "));
		return option;
	}
	

}
