//Manage Computers program: maintains an ArrayList of Computer objects, 
//can be either Laptop or Desktop, but never just Computer-type objects themselves

import java.util.ArrayList;
import java.util.Scanner;

public class ManageComputers {

    public static void main(String args[]) {

        //This ArrayList will hold ALL THE COMPUTERS in the system. Note that the type of objects expected in this
        //ArrayList are Computer, NOT Laptop or Desktop, but since those are subclasses of Computer they can be
        //stored in an ArrayLiust<Computer> anyway.
        ArrayList<Computer> computers = new ArrayList<Computer>(); 


        //(System.in) = The "standard" input stream. This stream is already open and ready to supply input data. 
        Scanner s = new Scanner(System.in);
        String menuOption=""; //Create a menu option 

        do { //Start of main program loop

            //Show computer data in ArrayList<Computer>
            showComputers(computers); 

            //Display menu and return menu option selected by the user
            menuOption = getMenuSelection(s);

            //When you create a menu, of course you have to put smth in it. We start putting different option such as: 
            // Add, Delete, Edit, Stop menu by entering "x"
            switch(menuOption) {
                //Add new computer
                case "a": 

                    addComputer(computers,s);

                    break;

                //Delete a computer    
                case "d": 

                    deleteComputer(computers,s);

                    break;

                //Edit a computer    
                case "e": 

                    editComputer(computers, s);

                    break;

            }

        } while ( ! menuOption.equals("x") ); //Stop when "x" is entered

        s.close(); //Close keyboard scanner

    } //End of main


    //-----------------------------

    //After writing all the functions, it's time to display it. 

    //Display menu and get user selection, return it
    private static String getMenuSelection(Scanner s) {
        String menuOption="";

        //This part is related to the " switch(menuOption)" that we created above 
        //Display menu options on-screen
        System.out.println("----------");
        System.out.println("A) Add Computer");
        System.out.println("D) Delete Computer");
        System.out.println("E) Edit Computer");
        System.out.println("X) eXit");
        System.out.println("----------");

        //Get menu selection from keyboard
        System.out.print("Enter menu selection:");
        menuOption = s.nextLine();

        menuOption = menuOption.toLowerCase(); //Make lower case for comparison purposes

        return menuOption;
    } //End of getMenuSelection


    //-----------------------------
    //After display menu and let user pick option in the menu. 
    //For example: user pick "Adding Computer". 
    //The program will ask user to chose between Laptop or Desktop.
    // Then it will ask to enter CPU,RAM,DISK,SCREEN SIZE. And will display a LIST OF COMPUTER FOR YOU  
    //------------------------------

    //Show data for all laptops and desktops stored in ArrayList<Computer> create in main() method
    private static void showComputers(ArrayList<Computer> computers) {
        int computerListNumber=0; //This variable is used to hold the "list number" for each computer, starting at 1.

        System.out.println("=========");

        System.out.println("LIST OF COMPUTERS:-");

        for (Computer c: computers) {  

            computerListNumber++; //Increment list number for each computer

            //Call overridden toString() method for current object to get and display its data
            System.out.println(computerListNumber + ": " + c.toString());
        }

        System.out.println("=========");

    } //End of showComputers

    
    //-----------------------------
    //Add a new Laptop or Desktop computer to the ArrayList<Computer>
    private static void addComputer(ArrayList<Computer> computers, Scanner s) {
        String computerType="";

        Computer tempComputer=null;

        System.out.println("ADDING COMPUTER:-");

        System.out.println("Enter type of computer to add ('L' for Laptop, 'D' for Desktop):");
        computerType=s.nextLine();
        computerType=computerType.toLowerCase(); //Convert to lower case for comparison purposes

        switch(computerType) {

            //Add a laptop
            case "l": 

                //Get CPU, RAM and Disk info
                tempComputer = getComputerData(s); 

                System.out.print("Enter screen size:");
                String screenSize = s.nextLine();

                //Add new Laptop to ArrayList in main() method
                computers.add(new Laptop(tempComputer.getCPU(),tempComputer.getRAM(),tempComputer.getDisk(),screenSize)); 

                break;
            
            //Add a desktop    
            case "d": 

            //Get CPU, RAM and Disk info
                tempComputer = getComputerData(s); 

                System.out.print("Enter GPU:");
                String GPUType = s.nextLine();

                //Add new Desktop to ArrayList in main() method
                computers.add(new Desktop(tempComputer.getCPU(),tempComputer.getRAM(),tempComputer.getDisk(),GPUType)); 

                break;

            //Invalid computer type to add entered
            default:

                System.out.println("Invalid computer type entered!");

        }
    } //End of addComputer

    //-----------------------------
    //Delete a specified computer from the ArrayList
    private static void deleteComputer(ArrayList<Computer> computers, Scanner s) {
        int computerListNumberToDelete=0;

        System.out.println("DELETE COMPUTER:-");

        System.out.print("Enter number of computer to delete:");
        computerListNumberToDelete = Integer.parseInt(s.nextLine());

        //Check if computer list number is valid before deleting computer from list
        if (computerListNumberToDelete>=1 && computerListNumberToDelete<=computers.size()) {
            //Subtract 1 to get ArrayList index from on-screen list number to create correct index in ArrayList to delete
            computers.remove(computerListNumberToDelete-1); 
        }   
        else {
            System.out.println("Invalid computer number entered!");
        }

    } //End of deleteComputer

    //-----------------------------
    //Edit a computer. Since Laptop and Desktop are mutable classses/object get new data values and replace old
    //attribute values in object being edited using object setter methods
    private static void editComputer(ArrayList<Computer> computers, Scanner s) {
        int computerListNumberToEdit=0;
        String computerType="";
        Computer tempComputer=null;

        System.out.println("EDIT COMPUTER:-");

        System.out.print("Enter number of computer to edit:");
        computerListNumberToEdit = Integer.parseInt(s.nextLine());

        //Check that computerListNumberToEdit is valid first
        if (computerListNumberToEdit>=1 && computerListNumberToEdit<=computers.size()) {

            //Determine exact type of computer being edited
            //Subtract 1 to get ArrayList index from on-screen list number
            if (computers.get(computerListNumberToEdit-1) instanceof Laptop) { 
                computerType="laptop";
            }
            //Subtract 1 to get ArrayList index from on-screen list number
            else if (computers.get(computerListNumberToEdit-1) instanceof Desktop) { 
                computerType="desktop";
            }

        
            //Edit computer
            switch(computerType) {

                //Editing a laptop
                case "laptop": 
            
                    System.out.println("Editing a Laptop:");

                    //Get CPU, RAM and Disk info, store in temporary Computer-type object
                    tempComputer = getComputerData(s); 

                    System.out.print("Enter screen size:");
                    String screenSize = s.nextLine();

                    //Get reference to the object in ArrayList<Computer> to edit
                    //Cast Computer to Laptop for setScreenSize call a few lines of code later
                    Laptop laptopToEdit = (Laptop)computers.get(computerListNumberToEdit-1);

                    //Use setter methods to change mutable object state
                    laptopToEdit.setCPU(tempComputer.getCPU());
                    laptopToEdit.setRAM(tempComputer.getRAM());
                    laptopToEdit.setDisk(tempComputer.getDisk());
                    laptopToEdit.setScreenSize(screenSize);

                    break;

                //Editing a desktop, store in temporary Computer-type object
                case "desktop": 

                    System.out.println("Editing a Desktop:");

                    //Get CPU, RAM and Disk info
                    tempComputer = getComputerData(s); 

                    System.out.print("Enter GPU:");
                    String GPUType = s.nextLine();

                    //Get reference to the object in ArrayList<Computer> to edit
                    //Cast Computer to Laptop for setScreenSize call a few lines of code later
                    Desktop desktopToEdit = (Desktop)computers.get(computerListNumberToEdit-1);

                    //Use setter methods to change mutable object state
                    desktopToEdit.setCPU(tempComputer.getCPU());
                    desktopToEdit.setRAM(tempComputer.getRAM());
                    desktopToEdit.setDisk(tempComputer.getDisk());
                    desktopToEdit.setGPUType(GPUType);

                    break;

            }

        }
        else {
            System.out.println("Invalid computer number entered!");
        }


    } //End of editComputer

    //-----------------------------
    //Helper method to get data common to Laptop and Desktop (CPU, RAM and disk) objects. Returns a Computer-type object
    //holding these values as attribues
    private static Computer getComputerData(Scanner s) {

        //Task 1: Whitelist for Desktop 
        String DesktopCPU = "";
        String DesktopRAM = "";
        String DesktopDisk = "";

        // Define valid option for Desktop 
        String[] validDesktopCPUs = {"i5", "i7"}; //Valid desktop cpu
        String[] validDesktopRAMs = {"16", "32"}; // valid desktop ram
        String[] validDesktopDisks = {"512", "1024"}; //valid desktop disk 

        // Get and validate CPU
        DesktopCPU = getValidatedInput(s, "Enter CPU (i5 or i7): ", validDesktopCPUs, "Invalid CPU. Allowed values: i5 or i7.");

        // Get and validate RAM
        DesktopRAM = getValidatedInput(s, "Enter RAM size (16 or 32 GB): ", validDesktopRAMs, "Invalid RAM size. Allowed values: 16 or 32.");

        // Get and validate Disk
        DesktopDisk = getValidatedInput(s, "Enter Disk size (512 or 1024 GB): ", validDesktopDisks, "Invalid Disk size. Allowed values: 512 or 1024.");

        return new Computer(DesktopCPU, DesktopRAM, DesktopDisk);

    } //End of getComputerData

    //Create a Validate input function for desktop
    private static String getValidatedInput(Scanner s, String string, String[] validDesktopDisks, String string2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValidatedInput'");
    }


} //End of ManageComputer class