package edu.ucalgary.ensf409;

/**
 * @author Ryan Sommerville, Renno Eric, Noel Thomas
 * @version	1.0
 * @since	1.0
 */

// class Manufacturer:
// -------------------
//
// - manuID: String
// - name: String
// - phone: String
// - province: String
//
// //getters and setters may be needed

class Manufacturer{
  private String manuID;
  private String name;
  private String phone;
  private String province;
	
	/**
	Constructor that takes four strings.
	*/
  public Manufacturer(String manuID, String name, String phone, String province)
  {
	  this.manuID = manuID;
	  this.name = name;
	  this.phone = phone;
	  this.province = province;
  }
  
  /**
  Constructor that takes no parameters.
  */
  public Manufacturer() {
  }

	/**
	Getter method for the string manuID.
	*/
  public String getManuID(){
    return manuID;
  }

	/**
	Getter method for the string name.
	*/
  public String getName(){
    return name;
  }

	/**
	Getter method for the string phone.
	*/
  public String getPhone(){
    return phone;
  }

	/**
	Getter method for the string province.
	*/
  public String getProvince(){
    return province;
  }

	/**
	Setter method for the string manuID.
	*/
  public void setManuID( String id){
    manuID = id;
  }

	/**
	Setter method for the string name.
	*/
  public void setName( String name){
    this.name = name;
  }

	/**
	Setter method for the string phone.
	*/
  public void setPhone( String num){
    phone = num;
  }

	/**
	Setter method for the string province.
	*/
  public void setProvince( String prov){
    province = prov;
  }
}
