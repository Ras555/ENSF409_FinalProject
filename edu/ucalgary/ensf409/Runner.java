package edu.ucalgary.ensf409;

/**
 * @author Ryan Sommerville, Renno Eric, Noel Thomas
 * href="mailto:noel.thomas@ucalgary.ca">noel.thomas@ucalgary.ca</a>
 * @version	1.0
 * @since	1.0
 */

import java.util.*;

/**
The Runner class prompts the user
for a database username and password,
and uses it to create a link to the
database and make a FurnitureList object.
It then prompts the user for an order of
furniture and uses that to calculate the
cheapest way to get that furniture, print
it to the terminal, print it to a file, and
update the database.
*/
public class Runner {
    public static void main(String args[]){

        //Still needs to handle invalid inputs
        Scanner sc= new Scanner(System.in);    // Using System.in (std input stream)

        System.out.print("Enter database username: ");
        String dbuser = sc.nextLine();

        System.out.print("Enter database password: ");
        String dbpass = sc.nextLine();

                // Create a database connection and initializes connection
                DatabaseIO db = new DatabaseIO(dbuser, dbpass);
                db.createLink();


        System.out.print("Enter furniture category: ");
        String category = sc.nextLine();

        System.out.print("Enter furniture type: ");
        String type = sc.nextLine();

        System.out.print("Enter requested number of items: ");
        int quantity = sc.nextInt();

        System.out.print("Hit enter to commence operation\n");
        String x = sc.nextLine();

        FurnitureList furnitureInfo = db.getData();

        FurnitureOrder furnitureOrder = new FurnitureOrder(category, type, quantity, furnitureInfo);

        try{
            furnitureOrder.calculateAndPrintCheapestOrder();
            // ***** UPDATE DATABASE *****
            db.updateDatabase(furnitureOrder.getCheapestOrder(),
             furnitureOrder.getCategory());
            db.close();
        }
        catch(FurnitureTypeNotFoundException e){
            System.out.println("Operation failed." + e);
			System.exit(1);
        }		
		
		String order = type + " " + category + " " + quantity;
		
        OrderFormIO o = new OrderFormIO(order, quantity, furnitureOrder.getOrderPrice());
        o.fillDataMembersWithUser();

    try {
			o.writeFormattedFile(furnitureOrder.getCheapestOrder());
		}
		catch(Exception e)
		{
			System.out.println("IOException occurred");
			System.exit(1);
		}
		System.out.print("An orderform with name " + "orderForm.txt" + " has been printed successfully");
        sc.close();
    }


}
