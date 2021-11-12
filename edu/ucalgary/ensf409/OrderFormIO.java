
package edu.ucalgary.ensf409;

/**
 * @author Ryan Sommerville, Renno Eric, Noel Thomas
 * href="mailto:noel.thomas@ucalgary.ca">noel.thomas@ucalgary.ca</a>
 * @version	1.0
 * @since	1.0
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The orderFormIO class is used to build an orderform.txt file in the working directory.
 */

class OrderFormIO{
  //Fields used to build output file
  private String fname;
  private String contact;
  private String date;
  private String originalRequest;
  private String numOrdered;
  private String totPrice;

  /**
	Class constructor that accepts three
	string arguments for a factory name, contact, date,
  orginal request, and integer arguments for number of items ordered, and  total price.
	*/
  public OrderFormIO(String originalRequest, int numOrdered, int totPrice){
    this.originalRequest = originalRequest;
    this.numOrdered = String.valueOf(numOrdered);
    this.totPrice = "$" + String.valueOf(totPrice);
  }

  /**
  Class constructor that has noel
  parameters and initializes all variables
  to null.
  */
  public OrderFormIO(){
    this.fname = null;
    this.contact = null;
    this.date = null;
    this.originalRequest = null;
    this.numOrdered = null;
    this.totPrice = null;
  }

  /**
  Getter function for the String fname.
  */
//getter functions for each variable
  public String getFname(){
    return this.fname;
  }
  
  /**
  Getter function for the String contact.
  */
  public String getContact(){
    return this.contact;
  }

  /**
  Getter function for the String date.
  */
  public String getDate(){
    return this.date;
  }

  /**
  Getter function for the String originalRequest.
  */
  public String getOriginalRequest(){
    return this.originalRequest;
  }

  /**
  Getter function for the String numOrdered.
  */
  public String getNumOrdered(){
    return this.numOrdered;
  }

  /**
  Getter function for the String totPrice.
  */
  public String getTotPrice(){
    return this.totPrice;
  }

  /**
  Method that fills the strings
  fname, contact, and date with
  user inputs taken from the
  command line.
  */
  public void fillDataMembersWithUser(){
    Scanner sc= new Scanner(System.in);    // Using System.in (std input stream)

    System.out.print("Enter the faculty name: ");  
    fname = sc.nextLine();

    System.out.print("Enter a phone number: ");  
    contact = sc.nextLine();

    System.out.print("Enter the date in MM-DD-YYYY format: ");  
    date = sc.nextLine();

    System.out.print("Hit enter to continue");  
    String x = sc.nextLine();

    sc.close();

    return;
  }

/**
	Method that formats each data member into an
	orderform and returns a string of that orderform.
	*/
public String orderFormToString(Furniture [] order){
    //Create a StringBuilder object
    StringBuilder s = new StringBuilder();

    //Use append method to format string with values given by class' data members
    s.append("Faculty Name: " + this.fname + "\n");
    s.append("Contact: " + this.contact + "\n");
    s.append("Date: " + this.date + "\n\n");
    s.append("Original: " + this.originalRequest + "\n\n");
    s.append("Items Ordered \n");
	for(Furniture f : order)
	{
		s.append("ID: " + f.getID() + "\n");
	}
	
    s.append("\nTotal Price: " + this.totPrice);


    return s.toString();
}

/**
	Method that uses a formatted string to create an output 
  file named "orderForm.txt" in the working directory.
	*/
public void writeFormattedFile(Furniture [] order) throws IOException{
  //Create a buffered writer
  BufferedWriter bw = new BufferedWriter(new FileWriter("orderForm.txt"));
  
  //Creates a string using the orderFormToString method
  String s = orderFormToString(order);

  //Writes the string to "orderForm.txt"
  bw.write(s);

  //Method called to flush and terminate the life of the bufferedwriter
  bw.close();
}

}
