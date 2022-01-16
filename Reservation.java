//Nico Robson
//501046490


/*
 * A simple class to model an electronic airline flight reservation
 * 
 * This class has been done for you
 */
public class Reservation
{
	String flightNum;
	String flightInfo;
	boolean firstClass;
	String name;
	String passportNum;
	
	
	public Reservation(String flightNum, String info, String name, String passportNum)
	{
		this.flightNum = flightNum;
		this.flightInfo = info;
		this.firstClass = false;																//I've added some new fields to increase funtionality with Passenger.
		this.name = name;																		//Hence the new instance variables and the getters and setters below.
		this.passportNum = passportNum;
	}

	public Reservation(String flightNum, String info)
	{
		this.flightNum = flightNum;
		this.firstClass = false;
		this.flightInfo = info;
	}
	
	public boolean isFirstClass()
	{
		return firstClass;
	}

	public void setFirstClass()
	{
		this.firstClass = true;
	}

	public String getFlightNum()
	{
		return flightNum;
	}
	
	public String getFlightInfo()
	{
		return flightInfo;
	}

	public void setFlightInfo(String flightInfo)
	{
		this.flightInfo = flightInfo;
	}

	public void print()
	{
		System.out.println(flightInfo);
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName() 
	{																						//new getters and setters
		return name;
	}

	public void setPassportNum(String passportNum)
	{
		this.passportNum = passportNum;
	}

	public String getPassportNum() 
	{
		return passportNum;
	}
}
