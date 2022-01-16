//Nico Robson
//501046490


/*
 * 
 * This class models an aircraft type with a model name, a maximum number of economy seats, and a max number of forst class seats 
 * 
 * Add code such that class Aircraft implements the Comparable interface
 * Compare two Aircraft objects by first comparing the number of economy seats. If the number is equal, then compare the
 * number of first class seats 
 */
public class Aircraft implements Comparable<Aircraft> 
{
  int numEconomySeats;
  int numFirstClassSeats;
  
  String model;
  
  public Aircraft(int seats, String model)
  {
  	this.numEconomySeats = seats;
  	this.numFirstClassSeats = 0;
  	this.model = model;
  }

  public Aircraft(int economy, int firstClass, String model)
  {
  	this.numEconomySeats = economy;
  	this.numFirstClassSeats = firstClass;
  	this.model = model;
  }
  
	public int getNumSeats()
	{
		return numEconomySeats;
	}
	
	public int getTotalSeats()
	{
		return numEconomySeats + numFirstClassSeats;
	}
	
	public int getNumFirstClassSeats()
	{
		return numFirstClassSeats;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}
	
	public void print()
	{
		System.out.println("Model: " + model + "\t Economy Seats: " + numEconomySeats + "\t First Class Seats: " + numFirstClassSeats);
	}

	/*
	 * Write a compareTo method that is part of the Comparable interface
	 */
	public int compareTo(Aircraft other) {
		int result = 0;													//creating an int variable to return.
		if (this.numEconomySeats > other.numEconomySeats) {				//if a > b return 1
			result = 1;
		} else if (this.numEconomySeats < other.numEconomySeats) {		//if a < b return -1
			result = -1;
		} else if (this.numEconomySeats == other.numEconomySeats) {			//if the number of economy seats are =, then compare the number of first class seats
			if (this.numFirstClassSeats > other.numFirstClassSeats) {		//if a has more first class seats, return 1
				result = 1;
			} else if (this.numFirstClassSeats < other.numFirstClassSeats) {	//if b has more first class seats, return -1
				result = -1;
			} else {															//else everything is =, therefore return 0.
				result = 0;
			}
		}
		return result;			//returning the result variable with either a 0, -1, 1.
	}
	
}
  
