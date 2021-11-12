package edu.ucalgary.ensf409;

/**
 * @author Ryan Sommerville, Renno Eric, Noel Thomas
 * @version	1.0
 * @since	1.0
 */

import java.util.ArrayList;
import java.util.Arrays;

public class FurnitureList {

    private ArrayList<Chair> chairList;
    private ArrayList<Desk> deskList;
    private ArrayList<Lamp>  lampList;
    private ArrayList<Filing> filingList;
    private ArrayList<Manufacturer> manuList;


    /**
     * This method takes in a Chair array, transforming it into an ArrayList, 
     * and then placing it into the appropriate datamembers.
     */
    public void setChairs(Chair [] chairs)
	{
		chairList = new ArrayList<>(Arrays.asList(chairs));
	}
	
    /**
     * Takes in a type, and returns only Chair objects of that type
     * @param type
     * @return array
     */
    public Chair[] getChairs(String type){
        ArrayList<Chair> tmpList = new ArrayList<Chair>();

        for (Chair tmp : chairList) {
            if(tmp.getType().equals(type)){
                tmpList.add(tmp);
            }
        }

        Chair[] array = tmpList.toArray(new Chair[tmpList.size()]);

        return array;
    }

    /**
     * Returns all Chair objects as ArrayList
     * @return
     */
    public ArrayList<Chair> getChairs(){
        return chairList;
    }
	
    /**
     * returns Chair array
     * @return
     */
	public Chair [] getChairsArray() {
        Chair[] array = chairList.toArray(new Chair[chairList.size()]);
		return array;
	}
	
	
	/**
     * This method takes in a Desk array, transforming it into an ArrayList, 
     * and then placing it into the appropriate datamembers.
     */
	public void setDesks(Desk [] desks)
	{
		deskList = new ArrayList<>(Arrays.asList(desks));
	}
	
    /**
     * Takes in a type, and returns only Desk objects of that type
     * @param type
     * @return array
     */
    public Desk[] getDesks(String type){
        ArrayList<Desk> tmpList = new ArrayList<Desk>();

        for (Desk tmp : deskList) {
            if(tmp.getType().equals(type)){
                tmpList.add(tmp);
            }
        }

        Desk[] array = tmpList.toArray(new Desk[tmpList.size()]);

        return array;
    }

    /**
     * Returns all Desk objects as ArrayList
     * @return
     */
    public ArrayList<Desk> getDesks(){
        return deskList;
    }
	
    /**
     * returns Desk array
     * @return
     */
	public Desk [] getDesksArray() {
        Desk[] array = deskList.toArray(new Desk[deskList.size()]);
		return array;
	}

    /**
     * This method takes in a Filing array, transforming it into an ArrayList, 
     * and then placing it into the appropriate datamembers.
     */
	public void setFilings(Filing [] filings)
	{
		filingList = new ArrayList<>(Arrays.asList(filings));
	}

    /**
     * Takes in a type, and returns only Filing objects of that type
     * @param type
     * @return array
     */
    public Filing[] getFilings(String type){
        ArrayList<Filing> tmpList = new ArrayList<Filing>();

        for (Filing tmp : filingList) {
            if(tmp.getType().equals(type)){
                tmpList.add(tmp);
            }
        }

        Filing[] array = tmpList.toArray(new Filing[tmpList.size()]);

        return array;
    }
	
	/**
     * This method takes in a Lamp array, transforming it into an ArrayList, 
     * and then placing it into the appropriate datamembers.
     */
	public void setLamps(Lamp [] lamps)
	{
		lampList = new ArrayList<>(Arrays.asList(lamps));
	}

    /**
     * Takes in a type, and returns only Lamp objects of that type
     * @param type
     * @return array
     */
    public Lamp[] getLamps(String type){
        ArrayList<Lamp> tmpList = new ArrayList<Lamp>();

        for (Lamp tmp : lampList) {
            if(tmp.getType().equals(type)){
                tmpList.add(tmp);
            }
        }

        Lamp[] array = tmpList.toArray(new Lamp[tmpList.size()]);

        return array;
    }

    /**
     * Returns all Lamp objects as ArrayList
     * @return
     */
    public ArrayList<Lamp> getLamps(){
        return lampList;
    }
	
    /**
     * returns Lamp array
     * @return
     */
	public Lamp [] getLampsArray() {
        Lamp[] array = lampList.toArray(new Lamp[lampList.size()]);
		return array;
	}

    /**
     * Returns all Filing objects as ArrayList
     * @return
     */
    public ArrayList<Filing> getFilings(){
        return filingList;
    }
	
    /**
     * returns Filing array
     * @return
     */
	public Filing [] getFilingsArray() {
        Filing[] array = filingList.toArray(new Filing[filingList.size()]);
		return array;
	}
	
	/**
     * This method takes in a Manufacturer array, transforming it into an ArrayList, 
     * and then placing it into the appropriate datamembers.
     */
	public void setManufacturers(Manufacturer [] manufacturers)
	{
		manuList = new ArrayList<>(Arrays.asList(manufacturers));
    }

    /**
     * Returns all Manufacturer objects as ArrayList
     * @return
     */
    public ArrayList<Manufacturer> getManufacturers(){
        return manuList;
    }
	
    /**
     * returns Manufacturer array
     * @return
     */
	public Manufacturer [] getManufacturersArray() {
        Manufacturer[] array = manuList.toArray(new Manufacturer[manuList.size()]);
		return array;
	}
}
