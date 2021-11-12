/**
 * @author Ryan Sommerville, Renno Eric, Noel Thomas
 * href="renno.eric1@ucalgary.ca">renno.eric1@ucalgary.ca</a>
 * @version	1.1
 * @since	1.0
 */

package edu.ucalgary.ensf409;
import java.sql.*;
//javac -cp .:lib/mysql-connector-java-8.0.23.jar:. edu/ucalgary/ensf409/DatabaseIO.java 

/**
 * This class is creates the link between the program and the sql 
 * database. It is responsible for extracting the furniture data for 
 * purchases and for updating the database itself.
 */
public class DatabaseIO{
    
    private final String DBURL;
    private final String USERNAME;
    private final String PASSWORD;
    private Connection connect;
    private ResultSet results;

    /**
     * This constructor uses the usr and passwd for establishing
     * a link with the sql database 
     * @param usr  username for database access
     * @param passwd  password for database access
     */
    public DatabaseIO( String usr, String passwd){
        DBURL = "jdbc:mysql://localhost/INVENTORY";  // from what I know this is guaranteed
        USERNAME = usr;  // could make this 'scm' wanna test stuff though
        PASSWORD = passwd;  // could be 'ensf409' ^^^
        
    } // thinking of adding a default constructor with default usr/pass


    /**
     * Method which initializes the connection with the database
     */
    public void createLink(){
        try {
            connect = DriverManager.getConnection(DBURL,USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
			System.exit(1);
        }
    }
	
	/**
	This method creates an instance of
	a FurnitureList object from the database
	connection and returns it.
	*/
	public FurnitureList getData()
	{
		FurnitureList data = new FurnitureList();
		
		Manufacturer [] manufacturers = this.createManufacturers();
		Chair [] chairs = this.createChairs(manufacturers);
		Desk [] desks = this.createDesks(manufacturers);
		Filing [] filings = this.createFilings(manufacturers);
		Lamp [] lamps = this.createLamps(manufacturers);
		
		data.setChairs(chairs);
		data.setManufacturers(manufacturers);
		data.setDesks(desks);
		data.setLamps(lamps);
		data.setFilings(filings);
		
		return data;
	}
	

    /**
     * Function which reads in Chair values from the database
     * @return array of chair objects
     */
    public Chair[] createChairs(Manufacturer [] manufacturers){

        String command = "SELECT * FROM CHAIR;";
        int rowCount = getRowNum("CHAIR");
        Chair[] obj = new Chair[rowCount];
        try {
            Statement task = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            results = task.executeQuery(command);
            for (int i = 0; i < rowCount; i++){
                results.absolute(i + 1); // moves the cursor to row i
				String id = results.getString("ID");
				int price = results.getInt("Price");
				String manuID = results.getString("ManuID");
				String type = results.getString("Type");
				boolean legs;
				boolean arms;
				boolean seat;
				boolean cushion;
				
				Manufacturer manufacturer = null;

				for(int j = 0; j < manufacturers.length; j++)
				{
					if(manufacturers[j].getManuID().equals(manuID))
					{
						manufacturer = manufacturers[j];
						break;
					}
				}
				
                if (results.getString("Legs").equals("N")){
                    legs = false;
                }
                else{
                    legs = true;
                }
                
                if (results.getString("Arms").equals("N")){
                    arms = false;
                }
                else{
                    arms = true;
                }
                if (results.getString("Seat").equals("N")){
                    seat = false;
                }
                else{
                    seat = true;
                }
                if (results.getString("Cushion").equals("N")){
                    cushion = false;
                }
                else{
                    cushion = true;
                }
				
				obj[i] = new Chair(id, type, price, manufacturer, legs, arms, seat, cushion);
            }
            task.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * Method which reads in Desk data from the Database.
     * @return
     */
    public Desk[] createDesks(Manufacturer [] manufacturers){
        String command = "SELECT * FROM DESK;";
        int rowCount = getRowNum("DESK");
        Desk[] obj = new Desk[rowCount];
        try {
            Statement task = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            results = task.executeQuery(command);
            for (int i = 0; i < rowCount; i++){
                results.absolute(i + 1); // moves the cursor to row i
				String id = results.getString("ID");
				int price = results.getInt("Price");
				String manuID = results.getString("ManuID");
				String type = results.getString("Type");
				boolean legs;
				boolean top;
				boolean drawer;
				
				Manufacturer manufacturer = null;

				for(int j = 0; j < manufacturers.length; j++)
				{
					if(manufacturers[j].getManuID().equals(manuID))
					{
						manufacturer = manufacturers[j];
						break;
					}
				}
				
                if (results.getString("Legs").equals("N")){
					legs = false;
				}
                else{
                    legs = true;
                }
                if (results.getString("Top").equals("N")){
                    top = false;
                }
                else{
                    top = true;
                }
                if (results.getString("Drawer").equals("N")){
                    drawer = false;
                }
                else {
                    drawer = true;
                }
				obj[i] = new Desk(id, type, price, manufacturer, legs, top, drawer);
            }
            task.close();
        }
        catch ( Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * Method which reads in Lamp data from the Database
     * @return an array of Lamp objects
     */
    public Lamp[] createLamps(Manufacturer [] manufacturers){
        String command = "SELECT * FROM LAMP;";
        int rowCount = getRowNum("LAMP");
        Lamp[] obj = new Lamp[rowCount];
        try {
            Statement task = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            results = task.executeQuery(command);
            for (int i = 0; i < rowCount; i++){
                results.absolute(i + 1); // moves the cursor to row i
				String id = results.getString("ID");
				int price = results.getInt("Price");
				String manuID = results.getString("ManuID");
				String type = results.getString("Type");
				boolean base;
				boolean bulb;

				Manufacturer manufacturer = null;

				for(int j = 0; j < manufacturers.length; j++)
				{
					if(manufacturers[j].getManuID().equals(manuID))
					{
						manufacturer = manufacturers[j];
						break;
					}
				}
                if (results.getString("Base").equals("N")){
                    base = false;
                }
                else{
                    base = true;
                }
                if (results.getString("Bulb").equals("N")){
                    bulb = false;
                }
                else{
                    bulb = true;
                }
				obj[i] = new Lamp(id, type, price, manufacturer, base, bulb);
            }
            task.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * Method which reads in Filings data from the Database.
     * @return an array of Filing objects
     */
    public Filing[] createFilings(Manufacturer [] manufacturers){
        String command = "SELECT * FROM FILING;";
        int rowCount = getRowNum("FILING");
        Filing[] obj = new Filing[rowCount];
        try {
            Statement task = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            results = task.executeQuery(command);
            for (int i = 0; i < rowCount; i++){
                results.absolute(i + 1); // moves the cursor to row i
				String id = results.getString("ID");
				int price = results.getInt("Price");
				String manuID = results.getString("ManuID");
				String type = results.getString("Type");
				boolean rails;
				boolean drawers;
				boolean cabinet;

				Manufacturer manufacturer = null;

				for(int j = 0; j < manufacturers.length; j++)
				{
					if(manufacturers[j].getManuID().equals(manuID))
					{
						manufacturer = manufacturers[j];
						break;
					}
				}
                if (results.getString("Rails").equals("N")){
                    rails = false;
                }
                else{
                    rails = true;
                }
                if (results.getString("Drawers").equals("N")){
                    drawers = false;
                }
                else{
                    drawers = true;
                }
                if (results.getString("Cabinet").equals("N")){
                    cabinet = false;
                }
                else{
                    cabinet = true;
                }
				obj[i] = new Filing(id, type, price, manufacturer, rails, drawers, cabinet);
            }
            task.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;

    } 

    /**
     * Method which reads in Manufacturer data from the Database
     * @return an array of Manufacturer objects
     */
    public Manufacturer[] createManufacturers(){
        String command = "SELECT * FROM MANUFACTURER;";
        int rowCount = getRowNum("MANUFACTURER");
        Manufacturer[] obj = new Manufacturer[rowCount];
        try {
            Statement task = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            results = task.executeQuery(command);
            for (int i = 0; i < rowCount; i++){
                results.absolute(i + 1); // moves the cursor to row i
				String manuID = results.getString("ManuID");
				String name = results.getString("Name");
				String phone = results.getString("Phone");
				String province = results.getString("Province");
                obj[i] = new Manufacturer(manuID, name, phone, province);
            }
            task.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * Method whichs remove specified data from the Database
     * @param table location of the data to delete 
     * @param ID unique identifier for the Furniture object
     */
    public void removeFurniture(String table, String ID){
        String statement
         = String.format("DELETE FROM %s WHERE ID = '%s'",
         table,ID);
        try {
            PreparedStatement task = connect.prepareStatement(statement);
            task.executeLargeUpdate();
            task.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which finds the number of rows for a given table
     * @param table name of the table within the Database
     * @return number of rows within the specified table as an int
     */
    public int getRowNum( String table){

        int rowCount = 0;
        String command = "SELECT COUNT(*) as count FROM " + table + ";";
        try {
            Statement task = connect.createStatement();
            results = task.executeQuery(command);
            if( results.next()){
                rowCount = results.getInt("count");
            }
            task.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }
	
	/**
	Method that takes an array of Furniture and a
	string for their category as inputs and removes
	all of that furniture from that table.
	*/
    public void updateDatabase( Furniture[] data, String category){
        for (int i = 0; i < data.length; i++){
            removeFurniture(category, data[i].getID());
        }
    }
	
	/**
	Method to close database stream.
	*/
    public void close() {
        try {
            results.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}