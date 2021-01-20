# Poised_Enhanced_Project

## What Is The Purpose Of This Program And Who Can Use It?

This program is written in the Java programming language and its aim it to fulfill the requirements of the level 3 task requirements for the HyperionDev Software Engineering Bootcamp that I am currently enrolled in. The purpose of this project management system is to enhance the previous 'Poised' project by incorporating database programming into the system. This program allows the user to create a new project by allowing them to enter all the details for the project itself and the details for all the people involved with the project such as the client, contractor and architect. This program can be used by any construction company or other structural engineering firms and can be modified to meet their requirements. Please note that this program makes use of object-oriented programming.

## Describing The Code

When the user runs the code, a menu with options will be displayed. The user must enter the number of the option they wish to select. The following options are displayed to the user :

1. Create a new project
2. Change the due date of the project
3. Change the total amount paid for the project
4. Update a contractor's contact details
5. Finalise a project
6. View a list of projects that still need to be completed
7. View a list of projects that are passed the due date
8. Search for a project
9. Exit

### Option 1 : Creating A New Project

When the user selects this option, he/she will be prompted to enter the following information :

- Project number (If the project number already exists the user will be prompted to enter another project number)
- Project name (If the user does not enter a project name a default one will be created using the **project type** and **client's surname** )(If the project name already exists, the user will be asked to enter another project name.)
- Project type
- Physical address for the project
- ERF number
- Total fee for the project
- Total amount paid by the client
- Deadline for the project
- The completion date will initially set to null
- Client details :
  - Name
  - Telephone number
  - Email address
  - Physical address
- Contractor details :
  - Name
  - Telephone number
  - Email address
  - Physical address
- Architect details :
  - Name
  - Telephone number
  - Email address
  - Physical address

The project will be created with the above details and added to a list of projects. All the project details will then be added to the respected tables in a database called 'poisedpms_db'. The main menu will then be displayed again.

### Option 2 : Changing The Due Date Of The Project

When this option is selected, a list of all the existing projects will be displayed to the user in an easy-to-read manner. The user will then have to choose a project they want edit the deadline for by either entering the project number or the project name. An appropriate message will be displayed if the project name or number entered does not exist. If the project name or number does exist, the program will prompt the user to enter the new deadline for the project and update the deadline in both the project list and in 'poisepms_db' database. The main menu will then be displayed again.

### Option 3 : Changing The Total Amount Paid For The Project

When this option is selected, a list of all the existing projects will be displayed to the user in an easy-to-read manner. The user will then have to choose a project they want edit the total amount paid for by either entering the project number or the project name. An appropriate message will be displayed if the project name or number entered does not exist. If the project name or number does exist, the program will prompt the user to enter the additional amount paid by the client for the project and update the total amount paid by adding this amount to it. The total amount paid will edited in both the project list and the 'poisepms_db' database. The main menu will then be displayed again.

### Option 4 : Updating The Contractor's Details

When this option is selected, a list of all the existing projects will be displayed to the user in an easy-to-read manner, followed by a menu with the following options:

#### 1. Edit contractor's telephone number :

If this option is chosen, the user will then have to choose a project they want to edit the contractor's telephone number for by either entering the project number or the project name. An appropriate message will be displayed if the project name or number entered does not exist. If the project name or number does exist, the program will prompt the user to enter the new telephone number for the contractor and update the contractor's telephone number in the list as well as in the 'ProjectPeople' table in the 'poisepms_db' database. The main menu will then be displayed again.

#### 2. Edit contractor's email address

If this option is chosen, the user will then have to choose a project they want to edit the contractor's email address for by either entering the project number or the project name. An appropriate message will be displayed if the project name or number entered does not exist. If the project name or number does exist, the program will prompt the user to enter the new email address for the contractor and update the contractor's email address in the list as well as in the 'ProjectPeople' table in the 'poisepms_db' database. The main menu will then be displayed again.

### Option 5 : Finalizing A Project

When this option is selected, a list of all the existing projects will be displayed to the user in an easy-to-read manner. The user will then have to choose a project they want to finalize by either entering the project number or the project name. An appropriate message will be displayed if the project name or number entered does not exist. If the project name or number does exist, the program marks a project as *finalised*. 

The program then checks if the full fees has been paid by the client or not. If the full fees have been paid by the client the program outputs a suitable message to the user stating that the full fees have been paid. 

If there is still an outstanding amount to be paid, the program will generate and output an invoice with the following details :

- Client's name 
- Client's telephone number
- Client's email address
- Client's physical address
- Amount still to be paid

Thereafter the main menu will be displayed again.

### Option 6 : Viewing A List Of Projects That Still Need To Be Completed

When the user selects this option, a list of all the projects that still need to be completed will be displayed in a user-friendly manner. Thereafter, the main menu will be displayed again.

### Option 7 : Viewing A List Of Projects The Are Passed The Due Date

When the user selects this option, a list of all the projects that are passed the due date will be displayed in a user-friendly manner. Thereafter, the main menu will be displayed again.

### Option 8 : Searching For A Project

When this option is selected, the user will be asked to enter a project name or project number they would like to search for. If the project name or project number entered by the user does not exist, a suitable message will be displayed. If the project name or project number does exist, the program will display all the details of that project in a user friendly manor. The main menu will then be displayed again.

### Option 9 : Exiting The Program

The program will run and the main menu will be displayed until this option is selected(user chooses to exit the program).

## How To Get This Program To Work

Firstly, you need to clone this repository with the Task Manager program and related text files to a local repository on your computer, so that you can access and run the program. If you need help, follow the instructions as set out github help webpage:

https://help.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository

In order to run this Poised Java program, you will then need to install the Java Development Kit (JDK) onto your computer's operating system (OS):

https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_HowTo.html#jdk-install - Use this link as a guide to downloading and installing the JDK.

You will then need to install an integrated development environment (IDE) which is a program that enables you to view, write and run Java code. A link for an IDE called 'Eclipse' is provided below:

https://www.ntu.edu.sg/home/ehchua/programming/howto/EclipseJava_HowTo.html - Use this link as a guide to downloading and installing Eclipse.

In order to get the Server working for this database program, you will also need to instal MySQL from the following link:

https://dev.mysql.com/downloads/mysql/
