package edu.ucalgary.ensf409;

public class FurnitureTypeNotFoundException extends Exception
{
	/**
	 *
	 */
	private static final long serialVersionUID = 8375205460099100877L;

	public FurnitureTypeNotFoundException(String type)
	{
		super("The furniture type " + type + " is not recognized.");
	}
}