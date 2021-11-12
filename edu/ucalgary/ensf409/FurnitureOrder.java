/**
 * @author Ryan Sommerville, Renno Eric, Noel Thomas
 * href="mailto:ryan.sommerville@ucalgary.ca">ryan.sommerville@ucalgary.ca</a>
 * @version	1.0
 * @since	1.0
 */


package edu.ucalgary.ensf409;

import java.util.ArrayList;

/**
* The FurnitureOrder class excepts
* a furniture category, type, and
* quantity upon initialization and
* can use this information to calculateAndPrintCheapestOrder
* the cheapest order for that furniture using
* the info in an instance of the
* FurnitureList class.
*/

public class FurnitureOrder
{

	private String category;
	private String type;
	private int quantity;
	private FurnitureList furnitureInfo;
	private ArrayList <Furniture> cheapestOrder = new ArrayList<Furniture>();
	private int orderPrice = 0;
  
	/**
	Class constructor that accepts three
	string arguments for a furniture category,
	type, and quantity; as well as a reference
	to a FurnitureList class and a DatabaseIO
	class.
	*/
	public FurnitureOrder(String category, String type, int quantity, FurnitureList furnitureInfo) throws IllegalArgumentException
	{
		if(quantity <= 0)
		{
			throw new IllegalArgumentException();
		}
		this.category = category;
		this.type = type;
		this.quantity = quantity;
		this.furnitureInfo = furnitureInfo;
	}

   
	//Getter functions
  
  
	/**
	Getter function for the string category.
	*/
	public String getCategory()
	{
		return category;
	}
  
  
	/**
	Getter function for the string type.
	*/
	public String getType()
	{
		return type;
	}

  
	/**
	Getter function for the int quantity.
	*/
	public int getQuantity()
	{
		return quantity;
	}

  
	/**
	Getter function for the FurnitureList variable.
	*/
	public FurnitureList getFurnitureInfo()
	{
		return furnitureInfo;
	}
	
  
	/**
	Getter function that returns an array from the
	ArrayList cheapestOrder.
	*/
  
public Furniture [] getCheapestOrder()
	{
		return cheapestOrder.toArray(new Furniture [0]);
	}
	
	public int getOrderPrice()
	{
		return orderPrice;
	}
  
	//Setter functions
	/**
	Setter function for the string category.
	*/
	public void setCategory(String category)
	{
		this.category = category;
	}

	/**
	Setter function for the string type.
	*/
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	Setter function for the int quantity.
	*/
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	/**
	Setter function for the FurnitureList furnitureInfo.
	*/
	public void setFurnitureInfo(FurnitureList info)
	{
		furnitureInfo = info;
	}


  /**
	Uses the class variables to
	figure out the cheapest way to
	complete the order, prints the
	order to the screen, removes the
	needed furniture from the database,
	and creates a text file orderform.
	*/
	public void calculateAndPrintCheapestOrder() throws FurnitureTypeNotFoundException
	{
		cheapestOrder = new ArrayList<Furniture>(10);
		//Create an arraylist of all furniture of the correct category and type.
		if(category.equals("Chair"))
		{
			Chair [] posFurniture = furnitureInfo.getChairs(type);

			try{
				this.calculateOrder(posFurniture);
			}
			catch(OrderCannotBeCompletedException e)
			{
				System.out.println("Due to lacking required furniture parts, the order could not be completed.");
				this.printRecommendedManufacturers(posFurniture);
				return;
			}
		}
		else if(category.equals("Desk"))
		{
			Desk [] posFurniture = furnitureInfo.getDesks(type);
			try{
				this.calculateOrder(posFurniture);
			}
			catch(OrderCannotBeCompletedException e)
			{
				System.out.println("Due to lacking reqiured furniture parts, the order could not be completed.");
				this.printRecommendedManufacturers(posFurniture);
				return;
			}
		}
		else if(category.equals("Filing"))
		{
			Filing [] posFurniture = furnitureInfo.getFilings(type);
			
			try{
				this.calculateOrder(posFurniture);
			}
			catch(OrderCannotBeCompletedException e)
			{
				System.out.println("Due to lacking reqiured furniture parts, the order could not be completed.");
				this.printRecommendedManufacturers(posFurniture);
				return;
			}
		}
		else if(category.equals("Lamp"))
		{
			Lamp [] posFurniture = furnitureInfo.getLamps(type);
			
			try{
				this.calculateOrder(posFurniture);
			}
			catch(OrderCannotBeCompletedException e)
			{
				System.out.println("Due to lacking reqiured furniture parts, the order could not be completed.");
				this.printRecommendedManufacturers(posFurniture);
				return;
			}
		}
		else {
			throw new FurnitureTypeNotFoundException(category);
		}
		orderPrice = this.calculateOrderPrice();
		this.printOrder();
	}
	
	/**
	Method that calculates the cheapest
	order for an order of chairs.
	*/
	private void calculateOrder(Chair [] posFurniture) throws OrderCannotBeCompletedException
	{
		//Create an int array storing the number of possible parts for each part.
		//The size of the array and the part represented by each index differs for each furniture category.
		int [] posParts = findPossibleParts(posFurniture);
		
		//If there are not enough of any of the types of parts, then
		// throw a OrderCannotBeCompletedException.
		for(int i = 0; i < posParts.length; i++)
		{
			if(posParts[i] < quantity)
			{
				throw new OrderCannotBeCompletedException();
			}
		}
		
		int [] numParts = new int[posParts.length];
		
		boolean fulfilled = false;//Becomes true when all needed parts are found.
		
		//While the number of required parts has not been met...
		while(!fulfilled)
		{
			int bought = 0;//Represents the number of furniture bought during this cycle of the loop.
			
			//If there is any parts with just the right amount, buy all furniture with that part.
			for(int i = 0; i < posParts.length; i++)
			{
				if((posParts[i] == (quantity - numParts[i])) && (numParts[i] != quantity))
				{
					bought = this.buyAllFurnitureWithPart(posFurniture, i);
					break;
				}
			}
			//If no furniture was bought before, then find the cheapest piece of furniture and buy it.
			if(bought == 0)
			{
				Furniture cheapest = this.findCheapestFurniture(posFurniture, numParts);
				cheapestOrder.add(cheapest);
				for(int i = 0; i < posFurniture.length; i++)
				{
					if(posFurniture[i] == cheapest)
					{
						posFurniture[i] = null;
						break;
					}
				}
				bought = 1;
			}
			
			
			//Update numParts and posParts
			for(int i = 1; i <= bought; i++)
			{
				numParts = this.addParts((Chair)cheapestOrder.get(cheapestOrder.size() - i), numParts);
			}
			posParts = findPossibleParts(posFurniture);
			
			//Figure out whether all parts have been found
			
			fulfilled = true; //Assume all parts have been found.
			for(int i = 0; i < numParts.length; i++)
			{
				if(numParts[i] < quantity)
				{
					fulfilled = false; //Make fulfilled false if it is found that not all parts are found.
					break;
				}
			}
		}
	}
	
	/**
	Method that calculates the cheapest
	order for an order of desks.
	*/
	private void calculateOrder(Desk [] posFurniture) throws OrderCannotBeCompletedException
	{
		//Create an int array storing the number of possible parts for each part.
		//The size of the array and the part represented by each index differs for each furniture category.
		int [] posParts = findPossibleParts(posFurniture);
		
		//If there are not enough of any of the types of parts, then
		// throw a OrderCannotBeCompletedException.
		for(int i = 0; i < posParts.length; i++)
		{
			if(posParts[i] < quantity)
			{
				throw new OrderCannotBeCompletedException();
			}
		}
		
		int [] numParts = new int[posParts.length];
		
		boolean fulfilled = false;//Becomes true when all needed parts are found.
		
		//While the number of required parts has not been met...
		while(!fulfilled)
		{
			int bought = 0;//Represents the number of furniture bought during this cycle of the loop.
			
			//If there is any parts with just the right amount, buy all furniture with that part.
			for(int i = 0; i < posParts.length; i++)
			{
				if((posParts[i] == (quantity - numParts[i])) && (numParts[i] != quantity))
				{
					bought = this.buyAllFurnitureWithPart(posFurniture, i);
					break;
				}
			}
			//If no furniture was bought before, then find the cheapest piece of furniture and buy it.
			if(bought == 0)
			{
				Furniture cheapest = this.findCheapestFurniture(posFurniture, numParts);
				cheapestOrder.add(cheapest);
				for(int i = 0; i < posFurniture.length; i++)
				{
					if(posFurniture[i] == cheapest)
					{
						posFurniture[i] = null;
						break;
					}
				}
				bought = 1;
			}
			
			//Update numParts and posParts
			for(int i = 1; i <= bought; i++)
			{
				numParts = this.addParts((Desk)cheapestOrder.get(cheapestOrder.size() - i), numParts);
			}
			posParts = findPossibleParts(posFurniture);
			
			//Figure out whether all parts have been found
			
			fulfilled = true; //Assume all parts have been found.
			for(int i = 0; i < numParts.length; i++)
			{
				if(numParts[i] < quantity)
				{
					fulfilled = false; //Make fulfilled false if it is found that not all parts are found.
					break;
				}
			}
		}
	}
	
	/**
	Method that calculates the cheapest
	order for an order of lamps.
	*/
	private void calculateOrder(Lamp [] posFurniture) throws OrderCannotBeCompletedException
	{
		//Create an int array storing the number of possible parts for each part.
		//The size of the array and the part represented by each index differs for each furniture category.
		int [] posParts = findPossibleParts(posFurniture);
		
		//If there are not enough of any of the types of parts, then
		// throw a OrderCannotBeCompletedException.
		for(int i = 0; i < posParts.length; i++)
		{
			if(posParts[i] < quantity)
			{
				throw new OrderCannotBeCompletedException();
			}
		}
		
		int [] numParts = new int[posParts.length];
		
		boolean fulfilled = false;//Becomes true when all needed parts are found.
		
		//While the number of required parts has not been met...
		while(!fulfilled)
		{
			int bought = 0;//Represents the number of furniture bought during this cycle of the loop.
			
			//If there is any parts with just the right amount, buy all furniture with that part.
			for(int i = 0; i < posParts.length; i++)
			{
				if((posParts[i] == (quantity - numParts[i])) && (numParts[i] != quantity))
				{
					bought = this.buyAllFurnitureWithPart(posFurniture, i);
					break;
				}
			}
			//If no furniture was bought before, then find the cheapest piece of furniture and buy it.
			if(bought == 0)
			{
				Furniture cheapest = this.findCheapestFurniture(posFurniture, numParts);
				cheapestOrder.add(cheapest);
				for(int i = 0; i < posFurniture.length; i++)
				{
					if(posFurniture[i] == cheapest)
					{
						posFurniture[i] = null;
						break;
					}
				}
				bought = 1;
			}
			
			//Update numParts and posParts
			for(int i = 1; i <= bought; i++)
			{
				numParts = this.addParts((Lamp)cheapestOrder.get(cheapestOrder.size() - i), numParts);
			}
			posParts = findPossibleParts(posFurniture);
			
			//Figure out whether all parts have been found
			
			fulfilled = true; //Assume all parts have been found.
			for(int i = 0; i < numParts.length; i++)
			{
				if(numParts[i] < quantity)
				{
					fulfilled = false; //Make fulfilled false if it is found that not all parts are found.
					break;
				}
			}
		}
	}
	
	/**
	Method that calculates the cheapest
	order for an order of filings.
	*/
	private void calculateOrder(Filing [] posFurniture) throws OrderCannotBeCompletedException
	{
		//Create an int array storing the number of possible parts for each part.
		//The size of the array and the part represented by each index differs for each furniture category.
		int [] posParts = findPossibleParts(posFurniture);
		
		//If there are not enough of any of the types of parts, then
		// throw a OrderCannotBeCompletedException.
		for(int i = 0; i < posParts.length; i++)
		{
			if(posParts[i] < quantity)
			{
				throw new OrderCannotBeCompletedException();
			}
		}
		
		int [] numParts = new int[posParts.length];
		
		boolean fulfilled = false;//Becomes true when all needed parts are found.
		
		//While the number of required parts has not been met...
		while(!fulfilled)
		{
			int bought = 0;//Represents the number of furniture bought during this cycle of the loop.
			
			//If there is any parts with just the right amount, buy all furniture with that part.
			for(int i = 0; i < posParts.length; i++)
			{
				if((posParts[i] == (quantity - numParts[i])) && (numParts[i] != quantity))
				{
					bought = this.buyAllFurnitureWithPart(posFurniture, i);
					break;
				}
			}
			//If no furniture was bought before, then find the cheapest piece of furniture and buy it.
			if(bought == 0)
			{
				Furniture cheapest = this.findCheapestFurniture(posFurniture, numParts);
				cheapestOrder.add(cheapest);
				for(int i = 0; i < posFurniture.length; i++)
				{
					if(posFurniture[i] == cheapest)
					{
						posFurniture[i] = null;
						break;
					}
				}
				bought = 1;
			}
			
			//Update numParts and posParts
			for(int i = 1; i <= bought; i++)
			{
				numParts = this.addParts((Filing)cheapestOrder.get(cheapestOrder.size() - i), numParts);
			}
			posParts = findPossibleParts(posFurniture);
			
			//Figure out whether all parts have been found
			
			fulfilled = true; //Assume all parts have been found.
			for(int i = 0; i < numParts.length; i++)
			{
				if(numParts[i] < quantity)
				{
					fulfilled = false; //Make fulfilled false if it is found that not all parts are found.
					break;
				}
			}
		}
	}
	
	//addParts methods for each furniture category
	/**
	Method that add the parts of a Chair
	to an integer array.
	*/
	private int [] addParts(Chair furniture, int [] parts)
	{
		parts[0] += furniture.getLegs()? 1 : 0;
		parts[1] += furniture.getArms()? 1 : 0;
		parts[2] += furniture.getSeat()? 1 : 0;
		parts[3] += furniture.getCushion()? 1 : 0;
		return parts;
	}
	
	/**
	Method that add the parts of a Desk
	to an integer array.
	*/
	private int [] addParts(Desk furniture, int [] parts)
	{
		parts[0] += furniture.getLegs()? 1 : 0;
		parts[1] += furniture.getTop()? 1 : 0;
		parts[2] += furniture.getDrawer()? 1 : 0;
		return parts;
	}
	
	/**
	Method that add the parts of a Lamp
	to an integer array.
	*/
	private int [] addParts(Lamp furniture, int [] parts)
	{
		parts[0] += furniture.getBase()? 1 : 0;
		parts[1] += furniture.getBulb()? 1 : 0;
		return parts;
	}
	
	/**
	Method that add the parts of a Filing
	to an integer array.
	*/
	private int [] addParts(Filing furniture, int [] parts)
	{
		parts[0] += furniture.getRails()? 1 : 0;
		parts[1] += furniture.getDrawers()? 1 : 0;
		parts[2] += furniture.getCabinet()? 1 : 0;
		return parts;
	}
	
	//findPossibleParts method that works differently depending on the furniture category.
	/**
	Method that finds the total number of each
	type of part in an array of Chair objects
	and returns the result as an int array.
	*/
	private int [] findPossibleParts(Chair [] posFurniture)
	{
		int [] posParts;
		posParts = new int[4];
		/*
		* Index			Part
		* 0				legs
		* 1				arms
		* 2				seat
		* 3				cushion
		*/
		for(int i = 0; i < posFurniture.length; i++)
		{
			if(posFurniture[i] != null)
			{
				posParts[0] += posFurniture[i].getLegs()? 1 : 0;
				posParts[1] += posFurniture[i].getArms()? 1 : 0;
				posParts[2] += posFurniture[i].getSeat()? 1 : 0;
				posParts[3] += posFurniture[i].getCushion()? 1 : 0;
			}
		}
		return posParts;
	}
	
	/**
	Method that finds the total number of each
	type of part in an array of Desk objects
	and returns the result as an int array.
	*/
	private int [] findPossibleParts(Desk [] posFurniture)
	{
		int [] posParts;
		posParts = new int[3];
		/*
		* Index			Part
		* 0				legs
		* 1				top
		* 2				drawer
		*/
		for(int i = 0; i < posFurniture.length; i++)
		{
			if(posFurniture[i] != null)
			{
				posParts[0] += posFurniture[i].getLegs()? 1 : 0;
				posParts[1] += posFurniture[i].getTop()? 1 : 0;
				posParts[2] += posFurniture[i].getDrawer()? 1 : 0;
			}
		}
		return posParts;
	}
	
	/**
	Method that finds the total number of each
	type of part in an array of Filing objects
	and returns the result as an int array.
	*/
	private int [] findPossibleParts(Filing [] posFurniture)
	{
		int [] posParts;
		posParts = new int[3];
		/*
		* Index			Part
		* 0				rails
		* 1				drawers
		* 2				cabinet
		*/
		for(int i = 0; i < posFurniture.length; i++)
		{
			if(posFurniture[i] != null)
			{
				posParts[0] += posFurniture[i].getRails()? 1 : 0;
				posParts[1] += posFurniture[i].getDrawers()? 1 : 0;
				posParts[2] += posFurniture[i].getCabinet()? 1 : 0;
			}
		}
		return posParts;
	}
	
	/**
	Method that finds the total number of each
	type of part in an array of Lamp objects
	and returns the result as an int array.
	*/
	private int [] findPossibleParts(Lamp [] posFurniture)
	{
		int [] posParts;
		posParts = new int[2];
		/*
		* Index			Part
		* 0				base
		* 1				bulb
		*/
		for(int i = 0; i < posFurniture.length; i++)
		{
			if(posFurniture[i] != null)
			{
				posParts[0] += posFurniture[i].getBase()? 1 : 0;
				posParts[1] += posFurniture[i].getBulb()? 1 : 0;
			}
		}
		return posParts;
	}
	

	//buyAllFurnitureWithPart method that works differently depending on furniture category.
	/**
	Method that adds all Chair objects that 
	has a particular part in an array of
	chairs to the cheapestOrder. Returns
	the amount of furniture added.
	*/
	private int buyAllFurnitureWithPart(Chair [] posFurniture, int part)
	{
		int bought = 0;
		for(int i = 0; i < posFurniture.length; i++)
		{
			if(posFurniture[i] != null)
			{
				if((part == 0 && posFurniture[i].getLegs()) || (part == 1 && posFurniture[i].getArms())
					|| (part == 2 && posFurniture[i].getSeat()) || (part == 3 && posFurniture[i].getCushion()))
				{
					bought++;
					cheapestOrder.add(posFurniture[i]);
					posFurniture[i] = null;
					i--;
				}
			}
		}
		return bought;
	}
	
	/**
	Method that adds all Desk objects that 
	has a particular part in an array of
	Desk objects to the cheapestOrder. Returns
	the amount of furniture added.
	*/
	private int  buyAllFurnitureWithPart(Desk [] posFurniture, int part)
	{
		int bought = 0;
			for(int i = 0; i < posFurniture.length; i++)
			{
				if(posFurniture[i] != null)
				{
					if((part == 0 && posFurniture[i].getLegs()) || (part == 1 && posFurniture[i].getTop())
						|| (part == 2 && posFurniture[i].getDrawer()))
					{
						bought++;
						cheapestOrder.add(posFurniture[i]);
						posFurniture[i] = null;
						i--;
					}
				}
			}
			return bought;
	}
	
	/**
	Method that adds all Lamp objects that 
	has a particular part in an array of
	Lamp objects to the cheapestOrder. Returns
	the amount of furniture added.
	*/
	private int buyAllFurnitureWithPart(Lamp [] posFurniture, int part)
	{
		int bought = 0;
		for(int i = 0; i < posFurniture.length; i++)
		{
			if(posFurniture[i] != null)
			{
				if((part == 0 && posFurniture[i].getBase()) || (part == 1 && posFurniture[i].getBulb()))
				{
					bought++;
					cheapestOrder.add(posFurniture[i]);
					posFurniture[i] = null;
					i--;
				}
			}
		}
		return bought;
	}
	
	/**
	Method that adds all Filing objects that 
	has a particular part in an array of
	Filing objects to the cheapestOrder. Returns
	the amount of furniture added.
	*/
	private int buyAllFurnitureWithPart(Filing [] posFurniture, int part)
	{
		int bought = 0;
		for(int i = 0; i < posFurniture.length; i++)
		{
			if(posFurniture[i] != null)
			{
				if((part == 0 && posFurniture[i].getRails()) || (part == 1 && posFurniture[i].getDrawers())
					|| (part == 2 && posFurniture[i].getCabinet()))
				{
					bought++;
					cheapestOrder.add(posFurniture[i]);
					posFurniture[i] = null;
					i--;
				}
			}
		}
		return bought;
	}
	
	

	
	//findCheapestFurniture method that works differently for each furniture category
	/**
	A method that calculates and
	returns the cheapest Chair per
	needed parts in an array of Chairs.
	*/
	private Chair findCheapestFurniture(Chair [] posFurniture, int [] numParts)
	{
		int cheapestIndex = -1;
		int cheapestPrice = -1;
		int i;
		for(i = 0; i < posFurniture.length; i++)
		{
			if(posFurniture[i] != null)
			{
				//This integer represents the amount of parts that the current furniture has and are needed.
				int neededParts = ((numParts[0] < quantity)? 1: 0) * (posFurniture[i].getLegs()? 1: 0)
					+ ((numParts[1] < quantity)? 1: 0) * (posFurniture[i].getArms()? 1: 0)
					+ ((numParts[2] < quantity)? 1: 0) * (posFurniture[i].getSeat()? 1: 0)
					+ ((numParts[3] < quantity)? 1: 0) * (posFurniture[i].getCushion()? 1: 0);
				
				if(neededParts != 0)
				{
					int price = posFurniture[i].getPrice()/neededParts;
					
					if(cheapestPrice < 0 || price < cheapestPrice)
					{
						cheapestPrice = price;
						cheapestIndex = i;
					}
				}
			}
		}
		return posFurniture[cheapestIndex];
	}
	
	/**
	A method that calculates and
	returns the cheapest Desk per
	needed parts in an array of Desks.
	*/
	private Desk findCheapestFurniture(Desk [] posFurniture, int [] numParts)
	{
		int cheapestIndex = -1;
		int cheapestPrice = -1;
		
		int i;
		for(i = 0; i < posFurniture.length; i++)
		{
			if(posFurniture[i] != null)
			{
				//This integer represents the amount of parts that the current furniture has and are needed.
				int neededParts = ((numParts[0] < quantity)? 1: 0) * (posFurniture[i].getLegs()? 1: 0)
					+ ((numParts[1] < quantity)? 1: 0) * (posFurniture[i].getTop()? 1: 0)
					+ ((numParts[2] < quantity)? 1: 0) * (posFurniture[i].getDrawer()? 1: 0);
				
				if(neededParts != 0)
				{
					int price = posFurniture[i].getPrice()/neededParts;
					
					if(cheapestPrice < 0 || price < cheapestPrice)
					{
						cheapestPrice = price;
						cheapestIndex = i;
					}
				}
			}
		}
		return posFurniture[cheapestIndex];
	}
	
	/**
	A method that calculates and
	returns the cheapest Lamp per
	needed parts in an array of Lamps.
	*/
	private Lamp findCheapestFurniture(Lamp [] posFurniture, int [] numParts)
	{
		int cheapestIndex = -1;
		int cheapestPrice = -1;
		
		int i;
		for(i = 0; i < posFurniture.length; i++)
		{
			if(posFurniture[i] != null)
			{
				//This integer represents the amount of parts that the current furniture has and are needed.
				int neededParts = ((numParts[0] < quantity)? 1: 0) * (posFurniture[i].getBase()? 1: 0)
					+ ((numParts[1] < quantity)? 1: 0) * (posFurniture[i].getBulb()? 1: 0);
				
				if(neededParts != 0)
				{
					int price = posFurniture[i].getPrice()/neededParts;
					
					if(cheapestPrice < 0 || price < cheapestPrice)
					{
						cheapestPrice = price;
						cheapestIndex = i;
					}
				}
			}
		}
		return posFurniture[cheapestIndex];
	}
	
	/**
	A method that calculates and
	returns the cheapest Filing per
	needed parts in an array of Filings.
	*/
	private Filing findCheapestFurniture(Filing [] posFurniture, int [] numParts)
	{
		int cheapestIndex = -1;
		int cheapestPrice = -1;
		
		int i;
		for(i = 0; i < posFurniture.length; i++)
		{
			if(posFurniture[i] != null)
			{
				//This integer represents the amount of parts that the current furniture has and are needed.
				int neededParts = ((numParts[0] < quantity)? 1: 0) * (posFurniture[i].getRails()? 1: 0)
					+ ((numParts[1] < quantity)? 1: 0) * (posFurniture[i].getDrawers()? 1: 0)
					+ ((numParts[2] < quantity)? 1: 0) * (posFurniture[i].getCabinet()? 1: 0);
				
				if(neededParts != 0)
				{
					int price = posFurniture[i].getPrice()/neededParts;
					
					if(cheapestPrice < 0 || price < cheapestPrice)
					{
						cheapestPrice = price;
						cheapestIndex = i;
					}
				}
			}
		}
		return posFurniture[cheapestIndex];
	}
	

	
	//Function to print manufacturers if order cannot be fulfilled.
	/**
	Method to print all manufacturers that are
	in an array of Furniture to the terminal.
	*/
	private void printRecommendedManufacturers(Furniture [] posFurniture)
	{
		//Find all recommended manufacturers
    Manufacturer [] recManus = new Manufacturer[furnitureInfo.getManufacturersArray().length];
		int index = 0;
		for(int i = 0; i < posFurniture.length; i++)
		{
			Manufacturer recManu = posFurniture[i].getManufacturer();
			
			boolean newManu = true;
			for(int j = 0; j < recManus.length; j++)
			{
				if(recManus[j] == recManu)
				{
					newManu = false;
				}
			}
			
			if(newManu)
			{
				recManus[index] = recManu;
				index++;
			}
		}
				
		//Print recommended manufacturers
		if(recManus.length != 0)
		{
			System.out.println("Recommended manufacturers to buy this furniture: ");
			for(int i = 0; i < index; i++)
			{
				System.out.println("\t " + recManus[i].getName());
			}
		}
	}
	
	
	
	//Function to print completed order to the terminal.
	/**
	Method to print the cheapest order to the terminal.
	*/
	private void printOrder()
	{
		System.out.println("Request: " + type + " " + category + ", " + quantity);
		System.out.print("Order: Puchase");
		int i;
		for(i = 0; i < cheapestOrder.size() - 1; i++)
		{
			System.out.print(" " + cheapestOrder.get(i).getID() + ",");
		}
		System.out.print(" and " + cheapestOrder.get(i).getID());
		
		System.out.println(" for $" + orderPrice + ".");

		
	}
	
	/**
	Method to calculate the total
	price of the cheapestOrder field
	and return it.
	*/
	private int calculateOrderPrice()
	{
		int price = 0;
		for(Furniture f : cheapestOrder)
		{
			price += f.getPrice();
		}
		return price;
	}
}
