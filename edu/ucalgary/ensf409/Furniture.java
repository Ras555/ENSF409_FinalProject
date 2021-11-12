package edu.ucalgary.ensf409;

/**
 * @author Ryan Sommerville, Renno Eric, Noel Thomas
 * @version	1.0
 * @since	1.0
 */
 
// class Furniture:
// ----------------
// - ID: String
// - type: String
// - price: int
// - ManuID: String
//
// //getters and setters may be needed

/**
Abstract data class that holds 
five data members and the corresponding
getter and setter methods and constructors.
*/
abstract class Furniture{
  private String ID;
  private String type;
  private int price;
  private String manuID; //Not sure if necessary
  private Manufacturer manufacturerObject;
	
	/**
	Constructor that takes two strings,
	an int and a Manufacturer object.
	*/
  public Furniture(String id, String type, int price, Manufacturer manu)
  {
	  this.ID = id;
	  this.type = type;
	  this.price = price;
	  this.manufacturerObject = manu;
	  this.manuID = manu.getManuID();
  }

	/**
	Constructor that takes no parameters.
	*/
  public Furniture(){
	  this.ID = null;
	  this.type = null;
	  this.price = -3000;
    this.manuID = null;
	  this.manufacturerObject = null;
  }
  
  /**
  Getter method for the string id.
  */
  public String getID(){
    return ID;
  }

  /**
  Getter method for the string type.
  */
  public String getType(){
    return type;
  }

  /**
  Getter method for the int price.
  */
  public int getPrice(){
    return price;
  }

  /**
  Getter method for the string manuID.
  */
  public String getManuID(){
    return manuID;
  }

  /**
  Getter method for the Manufacturer manufacturerObject.
  */
  public Manufacturer getManufacturer(){
    return manufacturerObject;
  }

  /**
  Setter method for the int price.
  */
  public void setPrice( int price){
    this.price = price;
  }

  /**
  Setter method for the string type.
  */
  public  void setType( String type){
    this.type = type;
  }

  /**
  Setter method for the string id.
  */
  public void setID( String id){
    ID = id;
  }

  /**
  Setter method for the Manufacturer object manufacturerObject.
  */
  public void setManufacturer(Manufacturer obj){
    this.manufacturerObject = obj;
	this.manuID = obj.getManuID();
  }


}

// class Chair extends Furniture:
// ------------------------------
//
// - legs: boolean
// - arms: boolean
// - seat: boolean
// - cushion: boolean
//
// //getters and setters may be needed
/**
Data class representing a chair that extends
the abstract Furniture class. Contains four
booleans that represent the presence or lack
thereof of certain parts.
*/
class Chair extends Furniture{
  //In usable condition vars.
  private boolean legs;
  private boolean arms;
  private boolean seat;
  private boolean cushion;

  /**
  Constructor that takes two strings, an int,
  and a Manufacturer object.
  */
  public Chair(String id, String type, int price, Manufacturer manu)
  {
	  super(id, type, price, manu);
  }
  
  /**
  Constructor that takes two strings, an int,
  four booleans and a Manufacturer object.
  */
  public Chair(String id, String type, int price, Manufacturer manu, boolean legs, boolean arms, boolean seat, boolean cushion)
  {
    super(id, type, price, manu);
	  this.legs = legs;
	  this.arms = arms;
	  this.seat = seat;
	  this.cushion = cushion;
  }

  /**
  Constructor that takes no arguments.
  */
  public Chair(){
	  super();
  }
  
  /**
  Getter method for the boolean legs.
  */
  public boolean getLegs(){
    return legs;
  }

  /**
  Getter method for the boolean arms.
  */
  public boolean getArms(){
    return arms;
  }

  /**
  Getter method for the boolean seat.
  */
  public boolean getSeat(){
    return seat;
  }

  /**
  Getter method for the boolean cushion.
  */
  public boolean getCushion(){
    return cushion;
  }

  /**
  Setter method for the boolean legs.
  */
  public void setLegs( boolean res){
    legs = res;
  }

  /**
  Setter method for the boolean arms.
  */
  public void setArms( boolean res){
    arms = res;
  }

  /**
  Setter method for the boolean seat.
  */
  public void setSeat( boolean res){
    seat = res;
  }

  /**
  Setter method for the boolean cushion.
  */
  public void setCushion( boolean res){
    cushion = res;
  }
}

// class Desk extends Furniture:
// -----------------------------
//
// - legs: boolean
// - top: boolean
// - drawer: boolean
//
//
// //getters and setters may be needed
/**
Data class representing a desk that extends
the abstract Furniture class. Contains three
booleans that represent the presence or lack
thereof of certain parts.
*/
class Desk extends Furniture{
  private boolean legs;
  private boolean top;
  private boolean drawer;
  
  /**
  Constructor that takes two strings, an int,
  and a Manufacturer object.
  */
   public Desk(String id, String type, int price, Manufacturer manu)
  {
	  super(id, type, price, manu);
  }
  
  /**
  Constructor that takes two strings, an int,
  three booleans and a Manufacturer object.
  */
  public Desk(String id, String type, int price, Manufacturer manu, boolean legs, boolean top, boolean drawer)
  {
    super(id, type, price, manu);
	  this.legs = legs;
	  this.top = top;
	  this.drawer = drawer;
	  
  }

  /**
  Constructor that takes no arguments.
  */
  public Desk(){
	  super();
  }
  
  /**
  Getter method for the boolean legs.
  */
  public boolean getLegs(){
    return legs;
  }

  /**
  Getter method for the boolean top.
  */
  public boolean getTop(){
    return top;
  }

  /**
  Getter method for the boolean drawer.
  */
  public boolean getDrawer(){
    return drawer;
  }

  /**
  Setter method for the boolean legs.
  */
  public void setLegs( boolean res){
    legs = res;
  }

  /**
  Setter method for the boolean top.
  */
  public void setTop( boolean res){
    top = res;
  }

  /**
  Setter method for the boolean drawer.
  */
  public void setDrawer( boolean res){
    drawer = res;
  }
}

// class Lamp extends Furniture:
// -----------------------------
//
// - base: boolean
// - bulb: boolean
//
/**
Data class representing a lamp that extends
the abstract Furniture class. Contains two
booleans that represent the presence or lack
thereof of certain parts.
*/
class Lamp extends Furniture{
  private boolean base;
  private boolean bulb;

  /**
  Constructor that takes two strings, an int,
  and a Manufacturer object.
  */
  public Lamp(String id, String type, int price, Manufacturer manu)
  {
	  super(id, type, price, manu);
  }
  
  /**
  Constructor that takes two strings, an int,
  two booleans and a Manufacturer object.
  */
  public Lamp(String id, String type, int price, Manufacturer manu, boolean base, boolean bulb)
  {
    super(id, type, price, manu);
	  this.base = base;
	  this.bulb = bulb;
	  
  }
  
  /**
  Constructor that takes no arguments.
  */
  public Lamp(){
	  super();
  }

  /**
  Getter method for the boolean base.
  */
  public boolean getBase(){
    return base;
  }

  /**
  Getter method for the boolean bulb.
  */
  public boolean getBulb(){
    return bulb;
  }
  
  /**
  Setter method for the boolean base.
  */
  public void setBase( boolean res){
    base = res;
  }

  /**
  Setter method for the boolean bulb.
  */
  public void setBulb( boolean res){
    bulb = res;
  }
}
// //getters and setters may be needed
//
// class Filing extends Furniture:
// -------------------------------
//
// - rails: boolean
// - drawers: boolean
// - cabinet: boolean
/**
Data class representing a filing that extends
the abstract Furniture class. Contains three
booleans that represent the presence or lack
thereof of certain parts.
*/
class Filing extends Furniture{
  private boolean rails;
  private boolean drawers;
  private boolean cabinet;

  /**
  Constructor that takes two strings, an int,
  and a Manufacturer object.
  */
  public Filing(String id, String type, int price, Manufacturer manu)
  {
	  super(id, type, price, manu);
  }
  
  /**
  Constructor that takes two strings, an int,
  three booleans and a Manufacturer object.
  */
  public Filing(String id, String type, int price, Manufacturer manu, boolean rails, boolean drawers, boolean cabinet)
  {
    super(id, type, price, manu);
	  this.rails = rails;
	  this.drawers = drawers;
	  this.cabinet = cabinet;
	  
  }

  /**
  Constructor that takes no arguments.
  */
  public Filing(){
	  super();
  }

  /**
  Getter method for the boolean rails.
  */
  public boolean getRails(){
    return rails;
  }

  /**
  Getter method for the boolean drawers.
  */
  public boolean getDrawers(){
    return drawers;
  }

  /**
  Getter method for the boolean cabinet.
  */
  public boolean getCabinet(){
    return cabinet;
  }

  /**
  Setter method for the boolean rails.
  */
  public void setRails( boolean res){
    rails = res;
  }

  /**
  Setter method for the boolean drawers.
  */
  public void setDrawers( boolean res){
    drawers = res;
  }

  /**
  Setter method for the boolean cabinet.
  */
  public void setCabinet( boolean res){
    cabinet = res;
  }
}
