//Nico Robson
//501046490


/* 
 *  Class to model an airline flight. In this simple system, all flights originate from Toronto
 *  
 *  This class models a simple flight that has only economy seats
 */
import java.util.ArrayList;
public class Flight
{
	public enum Status {DELAYED, ONTIME, ARRIVED, INFLIGHT};

	String flightNum;
	String airline;
	String origin, dest;
	String departureTime;
	Status status; // see enum Status above. google this to see how to use it
	int flightDuration;
	Aircraft aircraft;
	protected int passengers; // count of (economy) passengers on this flight - initially 0

	ArrayList<Passenger> passengerList = new ArrayList<Passenger>();    //added a passenger list for to store objects of type Passenger now.
  
	public Flight()
	{
		// write code to initialize instance variables to default values
		this.flightNum = null;
		this.airline = null;
		this.dest = null;
		this.origin = null;
		this.departureTime = null;
		this.flightDuration = 0;
		this.aircraft = null;
		passengers = 0;
		status = null;
	}
	
	public Flight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft)
	{
		this.flightNum = flightNum;
		this.airline = airline;
		this.dest = dest;
		this.origin = "Toronto";
		this.departureTime = departure;
		this.flightDuration = flightDuration;
		this.aircraft = aircraft;
		passengers = 0;
		status = Status.ONTIME;
		
	}
	public String getFlightNum()
	{
		return flightNum;
	}
	public void setFlightNum(String flightNum)
	{
		this.flightNum = flightNum;
	}
	public String getAirline()
	{
		return airline;
	}
	public void setAirline(String airline)
	{
		this.airline = airline;
	}
	public String getOrigin()
	{
		return origin;
	}
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	public String getDest()
	{
		return dest;
	}
	public void setDest(String dest)
	{
		this.dest = dest;
	}
	public String getDepartureTime()
	{
		return departureTime;
	}
	public void setDepartureTime(String departureTime)
	{
		this.departureTime = departureTime;
	}
	
	public Status getStatus()
	{
		return status;
	}
	public void setStatus(Status status)
	{
		this.status = status;
	}
	public int getFlightDuration()
	{
		return flightDuration;
	}
	public void setFlightDuration(int dur)
	{
		this.flightDuration = dur;
	}
	
	public int getPassengers()
	{
		return passengers;
	}
	public void setPassengers(int passengers)
	{
		this.passengers = passengers;
	}
	
	// Check to see if there is room on this flight - compare current passenger count
	// with aircraft max capacity of economy seats
	public boolean seatsAvailable()
	{
		// your code here
		if (passengers < aircraft.getNumSeats()) {		//if the amount of passengers is less than the number of seats, there is room available.
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Cancel a seat - essentially reduce the passenger count by 1. Make sure the count does not
	 * fall below 0 (see instance variable passenger)
	 */
	public void cancelSeat()
	{
		// your code here
		if (passengers < 0) {		//decrementing the passenger count as a seat is cancelled.
			passengers -= 1;
		}
	}
	
	/*
	 * reserve a seat on this flight - essentially increases the passenger count by 1 only if there is room for more
	 * economy passengers on the aircraft used for this flight (see instance variables above)
	 */
	public boolean reserveSeat()
	{
		// your code here
		if (aircraft.getNumSeats() > getPassengers()) {		//if there are more than 0 seats left, increment the passenger count as a seat is reserved.
			passengers += 1;
			return true;
		} else {
			return false;
		}
	}
	
	public String toString()
	{
		 return airline + "\t Flight:  " + flightNum + "\t Dest: " + dest + "\t Departing: " + departureTime + "\t Duration: " + flightDuration + "\t Status: " + status;
		
	}

  
}
