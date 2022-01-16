//Nico Robson
//501046490


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;


public class FlightManager
{
  // Contains list of Flights departing from Toronto in a single day
	ArrayList<Flight> flights = new ArrayList<Flight>();
  
  String[] cities = 	{"Dallas", "New York", "London", "Paris", "Tokyo"};
  final int DALLAS = 0;  final int NEWYORK = 1;  final int LONDON = 2;  final int PARIS = 3; final int TOKYO = 4;
  
  // flight times in hours
  int[] flightTimes = { 3, // Dallas
  											1, // New York
  											7, // London
  											8, // Paris
  											16// Tokyo
  										};
  
  // Contains list of available airplane types and their seat capacity
  ArrayList<Aircraft> airplanes = new ArrayList<Aircraft>();  
  
  String errorMsg = null; // if a method finds an error (e.g. flight number not found) set this string. See video!
  
  Random random = new Random(); // random number generator - google "Java class Random". Use this in generateFlightNumber
  
  
  public FlightManager()
  {
  	// DO NOT ALTER THIS CODE - THE TA'S WILL USE IT TO TEST YOUR PROGRAM
  	// IN ASSIGNMENT 2 YOU WILL BE LOADING THIS INFORMATION FROM A FILE
  
  	// Create some aircraft types with max seat capacities
  	airplanes.add(new Aircraft(85, "Boeing 737"));
  	airplanes.add(new Aircraft(180,"Airbus 320"));
  	airplanes.add(new Aircraft(37, "Dash-8 100"));
  	airplanes.add(new Aircraft(12, "Bombardier 5000"));
  	airplanes.add(new Aircraft(592, 14, "Boeing 747"));
  	
  	// Populate the list of flights with some random test flights
  	String flightNum = generateFlightNumber("United Airlines");
  	Flight flight = new Flight(flightNum, "United Airlines", "Dallas", "1400", flightTimes[DALLAS], airplanes.get(0));
  	flights.add(flight);
  	flight.setStatus(Flight.Status.DELAYED);
  	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "London", "2300", flightTimes[LONDON], airplanes.get(1));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "Paris", "2200", flightTimes[PARIS], airplanes.get(1));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Porter Airlines");
   	flight = new Flight(flightNum, "Porter Airlines", "New York", "1200", flightTimes[NEWYORK], airplanes.get(2));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("United Airlines");
   	flight = new Flight(flightNum, "United Airlines", "New York", "0900", flightTimes[NEWYORK], airplanes.get(3));
   	flights.add(flight);
   	flight.setStatus(Flight.Status.INFLIGHT);
   	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "New York", "0600", flightTimes[NEWYORK], airplanes.get(2));
   	flights.add(flight);
   	flight.setStatus(Flight.Status.INFLIGHT);
   	
   	
   	flightNum = generateFlightNumber("United Airlines");
   	flight = new Flight(flightNum, "United Airlines", "Paris", "2330", flightTimes[PARIS], airplanes.get(0));
   	flights.add(flight);
   	
    /*
     * Add this code back in when you are ready to tackle class LongHaulFlight and have implemented its methods
     */
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new LongHaulFlight(flightNum, "Air Canada", "Tokyo", "2200", flightTimes[TOKYO], airplanes.get(4));
   	flights.add(flight);
  }
  
  /*
   * This private helper method generates and returns a flight number string from the airline name parameter
   * For example, if parameter string airline is "Air Canada" the flight number should be "ACxxx" where xxx is 
   * a random 3 digit number between 101 and 300 (Hint: use class Random - see variable random at top of class)
   * you can assume every airline name is always 2 words. 
   * 
   */
  private String generateFlightNumber(String airline)
  {
    // Your code here
    String flightNum = "";                            //assigning an empty String to flightNum which we build and will return.
  	String airlineArr[] = airline.split(" ");         //As the arline names are always 2 words, we can split at the space in between.
    String firstWord = airlineArr[0];                 //therefore the array contains two words, and we assign them each to a String.
    String secondWord = airlineArr[1];
    flightNum += firstWord.charAt(0);                 //Adding the first letter of the first word to the flightNum string.
    flightNum += secondWord.charAt(0);                //Adding the first letter of the second word to the flightNum string.
                                                      //we now have for example, Air Canada >>>> AC.
    int threeDigits = (int) (Math.random()*(301-100)) + 101;    //getting a random three digit number between 101 and 300
    flightNum += String.valueOf(threeDigits);                   //adding the String value of these digits to the end of the flightNum
    return flightNum;
  }

  // Prints all flights in flights array list (see class Flight toString() method) 
  // This one is done for you!
  public void printAllFlights()
  {
  	for (int i = 0; i < flights.size(); i++)
  	{
  		System.out.println(flights.get(i).toString());
  	}
  }
  
  // Given a flight number (e.g. "UA220"), check to see if there are economy seats available
  // if so return true, if not return false
  public boolean seatsAvailable(String flightNum)
  {
    // First check for a valid flight number
    // if it is not found, set errorMsg String and return false
    // To determine if the given flightNum is valid, search the flights array list and find 
    // the  Flight object with matching flightNum string
    // Once a Flight object is found, check if seats are available (see class Flight) 
    // if flight is full, set errorMsg to an appropriate message (see video) and return false
    // otherwise return true
    boolean result = true;                //the boolean value we will return with this method
    boolean flightNumIsValid = false;     //creating a boolean variable to indicate if the flightNum was found. (flag)

    for (int i = 0; i < flights.size(); i++) {
      Flight tempFlight = flights.get(i);                 //looping through the list of flights and seeing if one of the flights has
      if (tempFlight.getFlightNum().equals(flightNum)) {  //the same flightNum string
        flightNumIsValid = true;                          //if they do, set the flag to true;
      }
    }
    if (flightNumIsValid == true) {                         //if the flightNum was found,
      for (int i = 0; i < flights.size(); i++) {
        Flight tempFlight = flights.get(i);
        if (tempFlight.getFlightNum().equals(flightNum)) {      //getting the flight with the matching flightNum and assigning it to tempFlight.
          boolean hasSeats = tempFlight.seatsAvailable();       //this boolean value calls the seatsAvailable() method using the tempFlight.
          if (hasSeats == true) {                               //if the boolean value after calling that method is true, there are seats available.
            result = true;
          } else {                                              //if not, there are no seats available and we set an appropriate error message.
            errorMsg = "Flight " + flightNum + " is full";
            result = false;
          }
        } 
      } 
    } else {                                                //if the flightNum was never matched to any flight in the list, we return false and
      errorMsg = "Flight " + flightNum + " Not Found";      //set an appropriate error message.
      result = false;
    } 
    return result;
  }
  
  
  // Given a flight number string flightNum and a seat type, reserve a seat on a flight
  // If successful, return a Reservation object
  // NOTE: seat types are not used for basic Flight objects (seats are all "Economy Seats")
  // class LongHaulFlight defines two seat types
  // I  suggest you first write this method *without* considering class LongHaulFlight 
  // once that works (test it!!), add the long haul flight code
  public Reservation reserveSeatOnFlight(String flightNum, String seatType)
  {
  	// Check for valid flight number by searching through flights array list
  	// If matching flight is not found, set instance variable errorMsg (see at top) and return null 
  	boolean flightNumIsValid = false;
    Reservation result = null;
    for (int i = 0; i < flights.size(); i++) {
      if (flights.get(i).getFlightNum().equals(flightNum)) {        //searching for the flightNum using the flights list and, if found, set the flightNumIsValid to true.
        flightNumIsValid = true;
      }
      
    }
    if (flightNumIsValid == false) {                              //if not found, return null and set an appropriate error message.
      errorMsg = "Flight " + flightNum + " Not Found";
      result = null;
    }
  	// If flight found
  	//    
  	//		****beginning of long haul flight code - you may want to skip initially
  	//		check if seat type is first class and check if this is a long haul flight (Hint: use instanceof operator)
  	//    if above is true
  	//			call reserveSeat method in class LongHaulFlight
  	//			if long haul flight first class is not full
  	//				create Reservation object, set firstClass boolean variable to true in Reservation object
  	//				return reference to Reservation object
  	//			else long haul first class is full
  	//				set errorMsg and return null
  	//		***end of long haul flight code
  	//
  	//		else must be economy seat 
  	//			reserve seat on flight (see class Flight reserveSeat() and overridden reserveSeat() in class LongHaulFlight)
  	//      if flight not full
  	//				create Reservation object and return reference to Reservation object 
  	//			else set ErrorMesg (flight full) and return null
    
  	if (flightNumIsValid == true) {                               //start of code if the flightNum is found.

      Flight flightToUse = new Flight();                          //creating a new instance variable of Flight
      for (int i = 0; i < flights.size(); i++) {
        Flight tempFlight = flights.get(i);
        if (tempFlight.getFlightNum().equals(flightNum)) {        //finding the correct flight that we should use by searching for the correct flightNum.
          flightToUse = tempFlight;                               //assigning the correct flight to the instance variable.
        }
      }
      if (seatType == "First Class Seat" && flightToUse instanceof LongHaulFlight) {        //if the seatType is first class and it's a long haul...
        LongHaulFlight newFlight = (LongHaulFlight) flightToUse;                            //cast the correct flight to a new LongHaulFlight variable.                                                    
        Aircraft theAircraft = newFlight.aircraft;                                          //setting a new Aircraft variable to this particular flight's aircraft.
        if (theAircraft.getNumFirstClassSeats() > newFlight.numFirstClassPassengers) {      //if first class seats are > first class passengers,
          newFlight.reserveSeat(seatType);                                                   //reserve a seat on the long haul flight using reserveSeat() and create
          Reservation object = new Reservation(flightNum, newFlight.toString());            //a Reservation object passing the flightNum and all the flight info using the
          object.setFirstClass();           //setting first class to true;                   //Flight.toString() method.
          result = object;                                                                  
        } else {
            errorMsg = "Flight " + flightNum + " is full";                                  //if the flight is full, return null and set the error message to full.
            result = null;
        }

      } else if (seatType == "Economy Seat" && flightToUse instanceof LongHaulFlight){              //if it's a long haul but an economy seat.....
        LongHaulFlight newFlight = (LongHaulFlight) flightToUse;                                    //essentially repeat without setting the first class variable to true.
        int numEconPassengers = newFlight.getPassengerCount() - newFlight.numFirstClassPassengers;
        Aircraft theAircraft = newFlight.aircraft;
        if (theAircraft.getNumSeats() > numEconPassengers) {
          newFlight.reserveSeat(seatType);
          Reservation object = new Reservation(flightNum, newFlight.toString());
          result = object;
        } else {
          errorMsg = "Flight " + flightNum + " is full";                                //if the flight is full, return null and set the error message to full.
          result = null;
        }
      } else {                                                                          //else, it's not a long haul.
          if (flightToUse.seatsAvailable() == true) {                                   //if seats are available (checking using the seatsAvailable method),
          flightToUse.reserveSeat();                                                    //reserve a seat and create a new Reservation object passing the flightNum
          Reservation object = new Reservation(flightNum, flightToUse.toString());      //and all the flight info using the Flight.toString() method.
          result = object;                          
        } else {
          errorMsg = "Flight " + flightNum + " is full";                                //if the flight is full, return null and set the error message to full.
          result = null;
          
        }
      }
    
    }
    return result;
  }  
  
  
  public String getErrorMessage()
  {
  	return errorMsg;
  }
  
  /*
   * Given a Reservation object, cancel the seat on the flight
   */
  public boolean cancelReservation(Reservation res)
  {
  	// Get the flight number string from res
  	// Search flights to find the Flight object - if not found, set errorMsg variable and return false
  	// if found, cancel the seat on the flight (see class Flight)
    boolean flightNumIsValid = false;
    boolean result = true;
  	String resFlightNum = res.getFlightNum();                   //calling getFlightNum() from Reservation class to get res's fightNum.
    Flight theFlight = null;                                    //creating an instance Flight variable.
    for (int i = 0; i < flights.size(); i++) {
      if (flights.get(i).getFlightNum().equals(resFlightNum)) {
        theFlight = flights.get(i);                                   //if the flightNum matches, set theFlight to this index's flight.
        flightNumIsValid = true;                                      //set flightNumIsValid to true;                                
      } 
      if (flightNumIsValid == true) {                                   //if flightNum is found
        theFlight.cancelSeat();                                         //cancel the seat on the flight using cancelSeat() method
      } else {
        errorMsg = "Flight " + res.getFlightNum() + " Not Found";       //else, set the error message to not found and return false.
        result = false;
      }                           
        
      
    }
  	// Once you have the above basic functionality working, try to get it working for canceling a first class reservation
  	// If this is a first class reservation (see class Reservation) and the flight is a LongHaulFlight (Hint use instanceof)
  	// then cancel the first class seat on the LongHaulFlight (Hint: you will need to cast)   
  	
    if (res.isFirstClass() == true && theFlight instanceof LongHaulFlight) {    //if the reservation is first class and the flight is a long haul,
      LongHaulFlight theFlight_LHF = (LongHaulFlight) theFlight;                //cast to a long haul flight
      theFlight_LHF.cancelSeat(theFlight_LHF.seatType);                         //and call cancelSeat() but this time we pass in the seatType variable.
    }
    return result;
  }
  
  // Sort the array list of flights by increasing departure time 
  // Essentially one line of code but you will be making use of a Comparator object below
  public void sortByDeparture()
  {
	  Collections.sort(flights, new DepartureTimeComparator());         //sorting flights using the departure time comparator
  }
  // Write a simple inner class that implements the Comparator interface (NOTE: not *Comparable*)
  // This means you will be able to compare two Flight objects by departure time
  private class DepartureTimeComparator implements Comparator<Flight>
  {
  	public int compare(Flight a, Flight b) {
      int result = 0;
      if (a.getDepartureTime().compareTo(b.getDepartureTime()) > 0) {           //comparing departure times for two flights and returning 1, -1, or 0 to sort them.
        result = 1;
      } else if (a.getDepartureTime().compareTo(b.getDepartureTime()) < 0) {
        result = -1;
      } else {
        result = 0;
      }
      return result;
    }
  }
  //Sort the array list of flights by increasing flight duration  
  // Essentially one line of code but you will be making use of a Comparator object below
  public void sortByDuration()
  {
	  Collections.sort(flights, new DurationComparator());        //sorting the flights using the duration comparator
  }
  //Write a simple inner class that implements the Comparator interface (NOTE: not *Comparable*)
 // This means you will be able to compare two Flight objects by duration
  private class DurationComparator implements Comparator<Flight>
  {
  	public int compare(Flight a, Flight b) {
      return a.getFlightDuration() - b.getFlightDuration();   //simply subtracting flight a's duration by b's duration to compare them.
    }
  }
  // Prints all aircraft in airplanes array list. 
  // See class Aircraft for a print() method
  public void printAllAircraft()
  {
  	for (int i = 0; i < airplanes.size(); i++) {        //looping through the airplanes list and assigning each index's element to an Aircraft instance.
      Aircraft aircraftToPrint = airplanes.get(i);      
      aircraftToPrint.print();                          //now we can call the Aircraft class's print method, printing out each airplane
    }
  }
  
  // Sort the array list of Aircraft objects 
  // This is one line of code. Make sure class Aircraft implements the Comparable interface
  public void sortAircraft()
  {
  	Collections.sort(airplanes);      //sorting the airplanes based on the compareTo() method implemented in class Aircraft.
  }
  
  public void printPassengers(Flight flight) {                        //one of my added commands for Passenger class.
    ArrayList<Passenger> thePassengers = new ArrayList<Passenger>();
    thePassengers.addAll(flight.passengerList);                       //adds all of the flight's passengers to a Passenger ArrayList and prints them all.
    for (int i = 0; i < thePassengers.size(); i++) {
      System.out.print(thePassengers.get(i) + "\t");
    }
  }

  public Reservation resPassenger(String flightNum, String name, String passportNum, Flight flight, String flightInfo, Passenger passenger) {
    boolean flightNumIsValid = false;
    boolean passengerAlreadyOnFlight = false;           //itializing some boolean variables to use, and a reservation in which we will return.
    Reservation result = null;

    for (int i = 0; i < flights.size(); i++) {
      Flight tempFlight = flights.get(i);
      if (tempFlight.getFlightNum().equals(flightNum)) {      //looping through flights list and if the flightNum is found on an index's flight, 
        flightNumIsValid = true;                              //set flightNumIsValid to true 
        break;
      }
    }                                                                                           
    if (flightNumIsValid == false) {                      //if flightNum is not found, return null and set appropriate error message.
      errorMsg = "Flight " + flightNum + " Not Found";
      result = null;
    }
    
    for (int i = 0; i < flight.passengerList.size(); i++) {
      if (flight.passengerList.get(i).getName().equals(name) && flight.passengerList.get(i).getPassportNum().equals(passportNum)) {
        passengerAlreadyOnFlight = true;          //if the passport number and name already exists in the passenger list, 
      }                                           //set passengerAlreadyOnFlight to true.
    }
    if (flightNumIsValid == true && passengerAlreadyOnFlight == false) {                  //if flightNum is found and the passenger is new,
      if (flight.seatsAvailable() == true) {                                                   //if seats are available on the flight,
        Reservation newRes = new Reservation(flightNum, flightInfo, name, passportNum);        //we make a new reservation object to set to our method's returning value.
        flight.passengerList.add(passenger);                                                   //also adding the passenger to the list of passengers
        result = newRes;
      } else {                                                                              //else, the flight is full and return null.
        errorMsg = "Flight " + flightNum + " is full";
        result = null;
      }
    }
    return result;
  }
}
