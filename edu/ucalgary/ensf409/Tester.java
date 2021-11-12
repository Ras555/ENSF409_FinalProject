package edu.ucalgary.ensf409;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * @author Ryan Sommerville, Renno Eric, Noel Thomas
 * @version	1.0
 * @since	1.0
 */


//javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/Tester.java

//java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:lib/mysql-connector-java-8.0.23.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.Tester
// run these commands for UNIX terminal

/**
This class tests the classes of the
supply-chain-management project Group 43.
In order to work properly, the usr and
passwd fields should be changed to a working
username and password on the local interface
to access the MySQL database.
Additionally, the computer running the tests
must have the exact same data as the inventory.sql file.
*/
public class Tester {

    private static String usr = "ryan-ENSF409"; // change to your sql username and password
    private static String passwd = "Ryjanar@55";


	//Test for Manufacturer class
	
	/**
	This method tests the Manufacturer class'
	getter and setter for the manuID field 
	and the blank constructor.
	*/
	@Test
	public void testManufacturerSetAndGetManuID()
	{
		Manufacturer test = new Manufacturer();
		String id = "003";

		test.setManuID(id);

		assertEquals(id, test.getManuID());
	}
	
	/**
	This method tests the Manufacturer class'
	getter and setter for the name field
	and the blank constructor.
	*/
	@Test
	public void testManufacturerSetAndGetName()
	{
		Manufacturer test = new Manufacturer();
		String name = "Best Manufacturer Inc.";

		//Setter for name
		test.setName(name);

		//Getter for name
		assertEquals(name, test.getName());
	}
	
	/**
	This method tests the Manufacturer class'
	getter and setter for the phone field
	and the blank constructor.
	*/
	@Test
	public void testManufacturerSetAndGetPhone()
	{
		Manufacturer test = new Manufacturer();
		String phone = "403-875-9023";

		//Setter for contact 
		test.setPhone(phone);

		//Getter for contact
		assertEquals(phone, test.getPhone());
	}
	
	/**
	This method tests the Manufacturer class'
	getter and setter for the province field
	and the blank constructor.
	*/
	@Test
	public void testManufacturerSetAndGetProvince()
	{
		Manufacturer test = new Manufacturer();
		String province = "AB";

		//Setter for location
		test.setProvince(province);

		//Getter for location
		assertEquals(province, test.getProvince());
	}
	
	/**
	This method test the Manufacturer class'
	constructor that takes four strings.
	*/
	@Test
	public void testManufacturersConstructorWithArguments()
	{
		Manufacturer test = new Manufacturer("003", "Best Manufacturer Inc.", "403-875-9023", "AB");
		
		assertEquals("ID: 003, Name: Best Manufacturer Inc., Phone: 403-875-9023, Province: AB",
			"ID: " + test.getManuID() + ", Name: " + test.getName() + ", Phone: " + test.getPhone() + ", Province: " + test.getProvince());
	}




	//Tests for Furniture class
		//Due to Furniture being abstract, it's child classes
		//are used to test it's methods instead.
	
	/**
	This method tests the constructor
	which does not take any inputs of
	the abstract Furniture class and it's
	setter and getter methods for the id field.
	This is done using the child class Chair.
	*/
	@Test
	public void testFurnitureSetAndGetID()
	{
		Furniture test = new Chair();
		String id = "F342";
		
		//Setter for ID
		test.setID(id);

		//Getter for ID
		assertEquals(id, test.getID());
	}
	
	/**
	This method tests the constructor
	which does not take any inputs of
	the abstract Furniture class and it's
	setter and getter methods for the type field.
	This is done using the child class Lamp.
	*/
	@Test
	public void testFurnitureSetAndGetType()
	{
		Furniture test = new Lamp();
		String type = "Mesh";

		//Setter for type
		test.setType(type);

		//Getter for type
		assertEquals(type, test.getType());
	}
	
	/**
	This method tests the constructor
	which does not take any inputs of
	the abstract Furniture class and it's
	setter and getter methods for the price field.
	This is done using the child class Filing.
	*/
	@Test
	public void testFurnitureSetAndGetPrice()
	{
		Furniture test = new Filing();
		int price = 105;

		//Setter for price
		test.setPrice(price);

		//Getter for price
		assertEquals(price, test.getPrice());
	}
	
	/**
	This method tests the constructor
	which does not take any inputs of
	the abstract Furniture class and it's
	setter and getter methods for the manufacturerObject field.
	This is done using the child class Chair.
	*/
	@Test
	public void testFurnitureSetAndGetManufacturer()
	{
		Manufacturer testManu = new Manufacturer();
		testManu.setManuID("003");
		testManu.setName("Greatest Manufacturer Inc.");
		testManu.setPhone("403-845-0923");
		testManu.setProvince("AB");

		Furniture test = new Chair();
		test.setManufacturer(testManu);

		assertSame(testManu, test.getManufacturer());
	}
	
	/**
	This method tests the constructor
	which takes two string, an int, and
	a Manufacturer object.
	This is done using the child class Desk.
	*/
	@Test
	public void testFurnitureConstructerWithInputs()
	{
		Manufacturer testManu = new Manufacturer("236", "Greatest Manufacturer", "403-563-8957", "ON");
		String type = "Folding";
		String id = "D983";
		int price = 120;
		
		Furniture test = new Desk(id, type, price, testManu);
		
		assertEquals("ID: D983, Type: Folding, Price: 120, ManuID: 236",
			"ID: " + test.getID() + ", Type: " + test.getType() + ", Price: " + test.getPrice() + ", ManuID: " + test.getManuID());
	}
	
	
	
	
	//Tests for Chair class
	
	/**
	This method tests the inherited
	constructor without parameters of
	the Chair class and it's getter
	and setter for the boolean legs field.
	*/
	@Test
	public void testChairSetAndGetLegs()
	{
		Chair test = new Chair();
		test.setLegs(true);

		assertTrue(test.getLegs());
	}
	
	/**
	This method tests the inherited
	constructor without parameters of
	the Chair class and it's getter
	and setter for the boolean arms field.
	*/
	@Test
	public void testChairSetAndGetArms()
	{
		Chair test = new Chair();
		test.setArms(true);

		assertTrue(test.getArms());
	}
	
	/**
	This method tests the inherited
	constructor without parameters of
	the Chair class and it's getter
	and setter for the boolean seat field.
	*/
	@Test
	public void testChairSetAndGetSeat()
	{
		Chair test = new Chair();
		test.setSeat(true);

		assertTrue(test.getSeat());
	}
	
	/**
	This method tests the inherited
	constructor without parameters of
	the Chair class and it's getter
	and setter for the boolean cushion field.
	*/
	@Test
	public void testChairSetAndGetCushion()
	{
		Chair test = new Chair();
		test.setCushion(true);

		assertTrue(test.getCushion());
	}
	
	/**
	This method tests the Chair constructor
	inheritted from the Furniture class
	that takes two strings, an int, and
	a Manufacturer class as parameters.
	*/
	@Test
	public void testChairSecondInherittedConstructor()
	{
		Manufacturer testManu = new Manufacturer("236", "Greatest Manufacturer", "403-563-8957", "ON");
		Chair test = new Chair("C345", "Mesh", 60, testManu);
		
		assertEquals("C345Mesh60236", test.getID() + test.getType() + test.getPrice() + test.getManuID());
	}

	/**
	This method tests the constructor for the Chair
	class that accepts two strings, an int, a Manufacturer
	object and four booleans as arguments.
	*/
	@Test
	public void testChairsConstructorWithBooleans()
	{
		Manufacturer testManu = new Manufacturer("236", "Greatest Manufacturer", "403-563-8957", "ON");
		Chair test = new Chair("C345", "Mesh", 60, testManu, true, false, false, true);
		
		assertEquals("C345Mesh60236truefalsefalsetrue", 
			test.getID()+test.getType()+test.getPrice()+test.getManuID()+test.getLegs()+test.getArms()+test.getSeat()+test.getCushion());
	}
	
	
	
	//Test for Desk class
	
	/**
	This method tests the inherited
	constructor without parameters of
	the Desk class and it's getter
	and setter for the boolean legs field.
	*/
	@Test
	public void testDeskSetAndGetLegs()
	{
		Desk test = new Desk();
		test.setLegs(true);

		assertTrue(test.getLegs());
	}
	
	/**
	This method tests the inherited
	constructor without parameters of
	the Desk class and it's getter
	and setter for the boolean top field.
	*/
	@Test
	public void testDeskSetAndGetTop()
	{
		Desk test = new Desk();
		test.setTop(true);

		assertTrue(test.getTop());
	}
	
	/**
	This method tests the inherited
	constructor without parameters of
	the Desk class and it's getter
	and setter for the boolean drawer field.
	*/
	@Test
	public void testDeskSetAndGetDrawer()
	{
		Desk test = new Desk();
		test.setDrawer(true);

		assertTrue(test.getDrawer());
	}
	
	/**
	This method tests the Desk constructor
	inheritted from the Furniture class
	that takes two strings, an int, and
	a Manufacturer class as parameters.
	*/
	@Test
	public void testDesksSecondInherittedConstructor()
	{
		Manufacturer testManu = new Manufacturer("236", "Greatest Manufacturer", "403-563-8957", "ON");
		Desk test = new Desk("C345", "Mesh", 60, testManu);
		
		assertEquals("C345Mesh60236", test.getID() + test.getType() + test.getPrice() + test.getManuID());
	}

	/**
	This method tests the constructor for the Desk
	class that accepts two strings, an int, a Manufacturer
	object and three booleans as arguments.
	*/
	@Test
	public void testDesksConstructorWithBooleans()
	{
		Manufacturer testManu = new Manufacturer("236", "Greatest Manufacturer", "403-563-8957", "ON");
		Desk test = new Desk("C345", "Mesh", 60, testManu, true, false, false);
		
		assertEquals("C345Mesh60236truefalsefalse", 
			test.getID()+test.getType()+test.getPrice()+test.getManuID()+test.getLegs()+test.getTop()+test.getDrawer());
	}




	//Tests for Filing class
	
	/**
	This method tests the inherited
	constructor without parameters of
	the Filing class and it's getter
	and setter for the boolean rails field.
	*/
	@Test
	public void testFilingSetAndGetRails()
	{
		Filing test = new Filing();
		test.setRails(true);

		assertTrue(test.getRails());
	}
	
	/**
	This method tests the inherited
	constructor without parameters of
	the Filing class and it's getter
	and setter for the boolean drawers field.
	*/
	@Test
	public void testFilingSetAndGetDrawers()
	{
		Filing test = new Filing();
		test.setDrawers(true);

		assertTrue(test.getDrawers());
	}
	
	/**
	This method tests the inherited
	constructor without parameters of
	the Filing class and it's getter
	and setter for the boolean cabinet field.
	*/
	@Test
	public void testFilingSetAndGetCabinet()
	{
		Filing test = new Filing();
		test.setCabinet(true);

		assertTrue(test.getCabinet());
	}
	
	/**
	This method tests the Filing constructor
	inheritted from the Furniture class
	that takes two strings, an int, and
	a Manufacturer class as parameters.
	*/
	@Test
	public void testFilingsSecondInherittedConstructor()
	{
		Manufacturer testManu = new Manufacturer("236", "Greatest Manufacturer", "403-563-8957", "ON");
		Filing test = new Filing("C345", "Mesh", 60, testManu);
		
		assertEquals("C345Mesh60236", test.getID() + test.getType() + test.getPrice() + test.getManuID());
	}

	/**
	This method tests the constructor for the Filing
	class that accepts two strings, an int, a Manufacturer
	object and three booleans as arguments.
	*/
	@Test
	public void testFilingsConstructorWithBooleans()
	{
		Manufacturer testManu = new Manufacturer("236", "Greatest Manufacturer", "403-563-8957", "ON");
		Filing test = new Filing("C345", "Mesh", 60, testManu, true, false, false);
		
		assertEquals("C345Mesh60236truefalsefalse", 
			test.getID()+test.getType()+test.getPrice()+test.getManuID()+test.getRails()+test.getDrawers()+test.getCabinet());
	}
	



	//Tests for Lamp class
	
	/**
	This method tests the inherited
	constructor without parameters of
	the Lamp class and it's getter
	and setter for the boolean base field.
	*/
	@Test
	public void testLampSetAndGetBase()
	{
		Lamp test = new Lamp();
		test.setBase(true);

		assertTrue(test.getBase());
	}

	/**
	This method tests the inherited
	constructor without parameters of
	the Lamp class and it's getter
	and setter for the boolean bulb field.
	*/
	@Test
	public void testLampSetAndGetBulb()
	{
		Lamp test = new Lamp();
		test.setBulb(true);

		assertTrue(test.getBulb());
	}
	
	/**
	This method tests the Lamp constructor
	inheritted from the Furniture class
	that takes two strings, an int, and
	a Manufacturer class as parameters.
	*/
	@Test
	public void testLampsSecondInherittedConstructor()
	{
		Manufacturer testManu = new Manufacturer("236", "Greatest Manufacturer", "403-563-8957", "ON");
		Lamp test = new Lamp("C345", "Mesh", 60, testManu);
		
		assertEquals("C345Mesh60236", test.getID() + test.getType() + test.getPrice() + test.getManuID());
	}

	/**
	This method tests the constructor for the Lamp
	class that accepts two strings, an int, a Manufacturer
	object and two booleans as arguments.
	*/
	@Test
	public void testLampsConstructorWithBooleans()
	{
		Manufacturer testManu = new Manufacturer("236", "Greatest Manufacturer", "403-563-8957", "ON");
		Lamp test = new Lamp("C345", "Mesh", 60, testManu, true, false);
		
		assertEquals("C345Mesh60236truefalse", 
			test.getID()+test.getType()+test.getPrice()+test.getManuID()+test.getBase()+test.getBulb());
	}
	
	
	
    //Tests for DatabaseIO class
	
	/**
	This method tests the DatabaseIO
	class constructor.
	*/
	@Test
	public void testDatabaseIOConstructorAndGetterMethods()
	{
		DatabaseIO test = new DatabaseIO("purple", "green");
		assertTrue(test != null);
	}
	
	/**
	Tests that the DatabaseIO method
	createLink runs correctly.
	*/
	@Test
	public void testDatabaseIOCreateLink()
	{
		DatabaseIO test = new DatabaseIO(usr, passwd);
		test.createLink();
	}
	
	/**
	Tests the DatabaseIO class' createManufacturers()
	method. Requires the correct database taken from
	inventory.sql to work properly.
	*/
    @Test
   public void testDatabaseCreateManufacturers(){
    DatabaseIO dbio = new DatabaseIO(usr, passwd);
     dbio.createLink();
     String[] expected = {
                     "001", "002",
                     "003", "004","005"};
     Manufacturer[] obj = dbio.createManufacturers();
	 String [] result = new String [obj.length];
     for( int i=0; i < obj.length; i++){
         result[i] = obj[i].getManuID();
     }
	 assertArrayEquals(expected, result);
    }
	
	/**
	Tests the DatabaseIO class' createChairs()
	method. Requires the correct database taken from
	inventory.sql to work properly.
	*/
    @Test
    public void testDatabaseCreateChairs(){
        System.out.println("Chair list test");
        DatabaseIO dbio = new DatabaseIO(usr, passwd);
        dbio.createLink();
		Manufacturer [] manus = dbio.createManufacturers();

        String[] expected = {"C0914", "C0942", "C1148",
                        "C1320", "C2483",
                        "C3405", "C3819", "C4839",
                        "C5409", "C5784", "C5789",
                        "C6748", "C7268", "C8138", "C9890"};
        Chair[] obj = dbio.createChairs(manus);
		String [] result = new String [obj.length];
        for( int i=0; i < obj.length; i++){
         result[i] = obj[i].getID();
		 }
		 assertArrayEquals(expected, result);
   }

	/**
	Tests the DatabaseIO class' createDesks()
	method. Requires the correct database taken from
	inventory.sql to work properly.
	*/
   @Test
   public void testDatabaseCreateDesks(){
       DatabaseIO dbio = new DatabaseIO(usr, passwd);
        dbio.createLink();
		Manufacturer [] manus = dbio.createManufacturers();

        String[] expected = {
                        "D0890", "D1030",
                        "D1927", "D2341", "D2746",
                        "D3682", "D3820", "D4231",
                        "D4438", "D4475", "D5437",
                        "D7373", "D8675", "D9352", "D9387"};
        Desk[] obj = dbio.createDesks(manus);
		String [] result = new String [obj.length];
        for( int i=0; i < obj.length; i++){
         result[i] = obj[i].getID();
		 }
		 assertArrayEquals(expected, result);
   }

	/**
	Tests the DatabaseIO class' createFilings()
	method. Requires the correct database taken from
	inventory.sql to work properly.
	*/
    @Test
    public void testDatabaseCreateFilings(){
       DatabaseIO dbio = new DatabaseIO(usr, passwd);
        dbio.createLink();
		Manufacturer [] manus = dbio.createManufacturers();

        String[] expected = {
                        "F001", "F002",
                        "F003", "F004", "F005",
                        "F006", "F007", "F008",
                        "F009", "F010", "F011",
                        "F012", "F013", "F014", "F015"};
        Filing[] obj = dbio.createFilings(manus);
		String [] result = new String [obj.length];
        for( int i=0; i < obj.length; i++){
         result[i] = obj[i].getID();
		 }
		 assertArrayEquals(expected, result);
   }
	
	/**
	Tests the DatabaseIO class' createLamps()
	method. Requires the correct database taken from
	inventory.sql to work properly.
	*/
   @Test
   public void testDatabaseCreateLamps(){
    DatabaseIO dbio = new DatabaseIO(usr, passwd);
     dbio.createLink();
	 Manufacturer [] manus = dbio.createManufacturers();

     String[] expected = {
                     "L013", "L053",
                     "L096", "L112", "L132",
                     "L208", "L223", "L342",
                     "L487", "L564", "L649",
                     "L879", "L928", "L980", "L982"};
     Lamp[] obj = dbio.createLamps(manus);
     String [] result = new String [obj.length];
     for( int i=0; i < obj.length; i++){
         result[i] = obj[i].getID();
     }
	 assertArrayEquals(expected, result);
    }



    //Tests for OrderFormIO class
	
	/**
	This method tests the constructor of the 
	OrderFormIO class that takes a string and
	two ints.
	*/
    @Test
    public void orderConstructorAndGettterTest1(){
        OrderFormIO o = new OrderFormIO("1 Mesh Chair", 3, 500);
        String [] orderFormVars = {o.getOriginalRequest(), o.getNumOrdered(), o.getTotPrice()};
        String [] actuals = {"1 Mesh Chair", "3", "$500"};
        assertArrayEquals(orderFormVars, actuals);
    }
	
	/**
	This method tests the orderFormToString()
	method of the OrderFormIO class.
	*/
    @Test
    public void orderFormtoStringTest2(){
		/*
		To run successfully, the inputs:
		Walmart
		587-XXX-XXXX
		April 2nd
		
		Should be input in that order when requested
		while running the tests.
		*/
        OrderFormIO o = new OrderFormIO("1 Mesh Chair", 3, 500);
		o.fillDataMembersWithUser();
        StringBuilder s = new StringBuilder();

        s.append("Faculty Name: " + "Walmart" + "\n");
        s.append("Contact: " + "587-XXX-XXXX" + "\n");
        s.append("Date: " + "April 2nd" + "\n\n");
        s.append("Original: " + "1 Mesh Chair" + "\n\n");
        s.append("Items Ordered \n\n");
        s.append("Total Price: " + "$500");

        String expected = s.toString();
        String actual = o.orderFormToString(new Furniture[0]);

        assertEquals(expected, actual);
    }


	//Tests for FurnitureOrder class
	
	/**
	Tests the constructor and getter
	methods for the FurnitureOrder class.
	*/
	@Test
	public void testOrderConstructorAndGetterMethods()
	{
		DatabaseIO dbio = new DatabaseIO(usr, passwd);
		dbio.createLink();
		FurnitureList testList = dbio.getData();

		FurnitureOrder test = new FurnitureOrder("Chair", "Mesh", 2, testList);

		String result = test.getCategory() + " : " + test.getType() + " : " + String.valueOf(test.getQuantity());
		String expected = "Chair : Mesh : 2";

		assertEquals(expected, result);
	}
	
	/**
	This method tests the calculateAndPrintCheapestOrder()
	method of the FurnitureOrder class when the given orderConstructorAndGettterTest1
	cannot be completed.
	*/
	@Test
	public void testOrderCalculatingCheapestNotPossible() throws FurnitureTypeNotFoundException
	{
		DatabaseIO dbio = new DatabaseIO(usr, passwd);
		dbio.createLink();
		FurnitureList testList = dbio.getData();

		FurnitureOrder test = new FurnitureOrder("Chair", "Mesh", 2, testList);

		test.calculateAndPrintCheapestOrder();

		ArrayList <Furniture> exp = new ArrayList<Furniture>();

		Furniture [] result = test.getCheapestOrder();

		Furniture [] expected = exp.toArray(new Furniture[0]);

		assertArrayEquals(result, expected);
	}
	
	/**
	This method tests the calculateAndPrintCheapestOrder()
	method of the FurnitureOrder class.
	*/
	@Test
	public void testOrderCalculatingCheapest() throws FurnitureTypeNotFoundException
	{
		DatabaseIO dbio = new DatabaseIO(usr, passwd);
		dbio.createLink();
		FurnitureList testList = dbio.getData();

		FurnitureOrder test = new FurnitureOrder("Chair", "Mesh", 1, testList);

		test.calculateAndPrintCheapestOrder();

		Furniture [] result = test.getCheapestOrder();

		String [] resultID = new String [result.length];

		for(int i = 0; i < result.length; i++)
		{
			resultID[i] = result[i].getID();
		}

		String [] expectedID = {"C9890", "C0942"};

		assertArrayEquals(expectedID, resultID);
	}

    //Tests for FurnitureList class
	
	/**
	This method tests the setChairs(Chair [])
	and getChairsArray() methods for the 
	chairlist field in the FurnitureList class.
	*/
    @Test
    public void SetAndGetChairsTest(){
        FurnitureList test = new FurnitureList();
        DatabaseIO dbio = new DatabaseIO(usr, passwd);
		dbio.createLink();

        Manufacturer[] manufacturers = dbio.createManufacturers();

        Chair[] expected = dbio.createChairs(manufacturers);

        test.setChairs(expected);

        Chair[] actual = test.getChairsArray();

        assertArrayEquals(expected, actual);
    }
	
	/**
	This method tests the getChairs(type)
	method from the FurnitureList class.
	*/
    @Test
    public void getChairsOfTypeTest(){
        String type = "Mesh";

		DatabaseIO dbio = new DatabaseIO(usr, passwd);
		dbio.createLink();
        FurnitureList test = dbio.getData();
		
        Chair[] expected = test.getChairsArray();
		
        //Create an ArrayList that recieves all Chair objects of some type
        ArrayList<Chair> tmpList = new ArrayList<Chair>();

        for (Chair tmp : expected) {
            if(tmp.getType().equals(type)){
                tmpList.add(tmp);
            }
        }

        // Redefine expected to hold only Chair objects of certain type
        expected = tmpList.toArray(new Chair[tmpList.size()]);
        // Get Chairs of certain type
        Chair[] actual = test.getChairs(type);
        assertArrayEquals(expected, actual);
    }
	
	/**
	This method tests the getDesks(type)
	method from the FurnitureList class.
	*/
    @Test
    public void getDesksOfTypeTest(){
        String type = "Standing";

        FurnitureList test = new FurnitureList();
        DatabaseIO dbio = new DatabaseIO(usr, passwd);
		dbio.createLink();
        Manufacturer[] manufacturers = dbio.createManufacturers();

        // Create an array that holds all Desks in the database
        Desk[] expected = dbio.createDesks(manufacturers);

        test.setDesks(expected);

        //Create an ArrayList that recieves all Desk objects of some type
        ArrayList<Desk> tmpList = new ArrayList<Desk>();

        for (Desk tmp : expected) {
            if(tmp.getType().equals(type)){
                tmpList.add(tmp);
            }
        }

        // Redefine expected to hold only Desk objects of certain type
        expected = tmpList.toArray(new Desk[tmpList.size()]);

        // Get Desks of certain type
        Desk[] actual = test.getDesks(type);

        assertArrayEquals(expected, actual);
    }
	
	/**
	This method tests the getFilings(type)
	method from the FurnitureList class.
	*/
    @Test
    public void getFilingsOfTypeTest(){
        String type = "Small";

        FurnitureList test = new FurnitureList();
        DatabaseIO dbio = new DatabaseIO(usr, passwd);
		dbio.createLink();
        Manufacturer[] manufacturers = dbio.createManufacturers();

        // Create an array that holds all Filings in the database
        Filing[] expected = dbio.createFilings(manufacturers);

        test.setFilings(expected);

        //Create an ArrayList that recieves all Filing objects of some type
        ArrayList<Filing> tmpList = new ArrayList<Filing>();

        for (Filing tmp : expected) {
            if(tmp.getType().equals(type)){
                tmpList.add(tmp);
            }
        }

        // Redefine expected to hold only Filing objects of certain type
        expected = tmpList.toArray(new Filing[tmpList.size()]);

        // Get Filings of certain type
        Filing[] actual = test.getFilings(type);

        assertArrayEquals(expected, actual);
    }
	
	/**
	This method tests the getLamps(type)
	method from the FurnitureList class.
	*/
    @Test
    public void getLampsOfTypeTest(){
        String type = "Swing Arm";

        FurnitureList test = new FurnitureList();
        DatabaseIO dbio = new DatabaseIO(usr, passwd);
		dbio.createLink();
        Manufacturer[] manufacturers = dbio.createManufacturers();

        // Create an array that holds all Lamps in the database
        Lamp[] expected = dbio.createLamps(manufacturers);

        test.setLamps(expected);

        //Create an ArrayList that recieves all Lamp objects of some type
        ArrayList<Lamp> tmpList = new ArrayList<Lamp>();

        for (Lamp tmp : expected) {
            if(tmp.getType().equals(type)){
                tmpList.add(tmp);
            }
        }

        // Redefine expected to hold only Lamp objects of certain type
        expected = tmpList.toArray(new Lamp[tmpList.size()]);

        // Get Lamps of certain type
        Lamp[] actual = test.getLamps(type);

        assertArrayEquals(expected, actual);
    }
}
