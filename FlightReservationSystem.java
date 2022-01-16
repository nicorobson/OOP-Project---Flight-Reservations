//Nico Robson
//501046490

import java.util.ArrayList;
import java.util.Scanner;

// Flight System for one single day at YYZ (Print this in title) Departing flights!!


public class FlightReservationSystem
{
	public static void main(String[] args)
	{
		// Create a FlightManager object
		FlightManager manager = new FlightManager();

		// List of reservations that have been made
		ArrayList<Reservation> myReservations = new ArrayList<Reservation>();	// my flight reservations

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		while (scanner.hasNextLine())
		{
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) continue;

			// The command line is a scanner that scans the inputLine string
			// For example: list AC201
			Scanner commandLine = new Scanner(inputLine);
			
			// The action string is the command to be performed (e.g. list, cancel etc)
			String action = commandLine.next();

			if (action == null || action.equals("")) continue;

			if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			// List all flights
			else if (action.equalsIgnoreCase("LIST"))
			{
				manager.printAllFlights(); 
			}
			// Reserve a flight based on Flight number string (example input: res AC220)
			else if (action.equalsIgnoreCase("RES"))
			{
				// Get the flight number string from the commndLine scanner (use hasNext() to check if there is a
				// flight number string entered
				String flightNum = null;			//Creating a new variable to store the flight number given from the scanner.
				if (commandLine.hasNext()) {
					flightNum = commandLine.next();
				}
				// call reserveSeatOnFlight() method in manager passing in the flight number string
				// A reference to a Reservation object is returned. Check to make sure it is not == null
				// If it is null, then call manager.getErrorMessage() and print the message
				// If it is not null, add the reservation to the myReservations array list and print the reservation (see class Reservation)
				
				Reservation tempRes = manager.reserveSeatOnFlight(flightNum, "Economy Seat");	//assigning a Reservation variable the value of the returned object
				if (tempRes == null) {															//using reserveSeatOnFlight()
					String errorMessage = manager.getErrorMessage();
					System.out.println(errorMessage);				//if the Reservation is null, print the error message
				} else {
					myReservations.add(tempRes);					//if not, add the reservation to the list and print it out.
					tempRes.print();
				}
			}
		  // Reserve a first class seat on a flight based on Flight number string (example input: res AC220)
			else if (action.equalsIgnoreCase("RESFCL"))
			{
				// Same as above except call 
				// manager.reserveSeatOnFlight() and pass in the flight number string as well as the string constant:
				// LongHaulFlight.firstClass (see class LongHaulFlight)
				// Do the LongHaulFlight class and this command after all the basic functionality is working for regular flights
				String flightNum = null;
				if (commandLine.hasNext()) {
					flightNum = commandLine.next();
				}											//doing the exact same command code as RES, except it uses seat type "First Class Seat"
				Reservation tempRes = manager.reserveSeatOnFlight(flightNum, "First Class Seat");
				if (tempRes == null) {
					String errorMessage = manager.getErrorMessage();
					System.out.println(errorMessage);
				} else {
					myReservations.add(tempRes);
					tempRes.print();
				}

			}
			// Query the flight manager to see if seats are still available for a specific flight (example input: seats AC220)
		  // This one is done for you as a guide for other commands
			else if (action.equalsIgnoreCase("SEATS"))
			{
				String flightNum = null;

				if (commandLine.hasNext())
				{
					flightNum = commandLine.next();

					if (manager.seatsAvailable(flightNum))
					{
						System.out.println("Seats are available");
					}
					else
					{
						System.out.println(manager.getErrorMessage());
					}
				}
			}
			// Cancel an existing reservation (example input: cancel AC220) 
			else if (action.equalsIgnoreCase("CANCEL"))
			{
        // get the flight number string from commandLine scanner (check if there is input first)
				// Use the flight number to find the Reservation object in the myReservations array list
				// If the reservation is found,  
				// 		call cancelReservation() method in the flight manager
				//    remove the reservation from myReservations
				// If the reservation is not found, print a message (see video)
				Reservation theRes = null;
				String flightNum = null;			//creating variables so we can store the result values in them.
				if (commandLine.hasNext()) {
					flightNum = commandLine.next();
				}
				for (int i = 0; i < myReservations.size(); i++) {		//looping through the list of reservations, then setting a temporary
					Reservation tempRes = myReservations.get(i);		//Reservation variable to the value we are getting from the ArrayList being looped through.
					if (tempRes.getFlightNum().equals(flightNum)) {		//If the flightNum from the reservation = the scanner's flightNum,
						theRes = tempRes;								//assign that reservation to the variable up top (theRes).
						break;
					}
				}
				myReservations.remove(theRes);						//remove the reservation from the list and call
				manager.cancelReservation(theRes);					//the cancelReservation method passing theRes.
			}
				
      // Print all the reservations in array list myReservations
			else if (action.equalsIgnoreCase("MYRES"))
			{
				for (int i = 0; i < myReservations.size(); i++) {	//looping through reservation list and printing all the reservations.
					myReservations.get(i).print();
				}
			}
			// Print the list of aircraft (see class Manager)
			else if (action.equalsIgnoreCase("CRAFT"))
		  {
			  manager.printAllAircraft();
			}
			// These commands can be left until we study Java interfaces
			// Feel free to implement the code in class Manager if you already understand interface Comparable
			// and interface Comparator
			else if (action.equalsIgnoreCase("SORTCRAFT"))
		  {
		  	manager.sortAircraft();
		  }
		  else if (action.equalsIgnoreCase("SORTBYDEP"))
		  {
			  manager.sortByDeparture();
			  
		  }
		  else if (action.equalsIgnoreCase("SORTBYDUR"))
		  {
			  manager.sortByDuration();
		  }
		  else if (action.equalsIgnoreCase("RESPSNGR"))
		  {
			//the method for this Passenger command is complete but not implemented
		  }
		  else if (action.equalsIgnoreCase("CNCLPSNGR"))
		  {
			//the method for this Passenger command was incomplete
		  }
		  else if (action.equalsIgnoreCase("PSNGRS"))
		  {
			  String flightNum = null;
			  Flight theFlight = null;
			  if (commandLine.hasNext()) {
				  flightNum = commandLine.next();								
			  }
			  ArrayList<Flight> theFlights = new ArrayList<Flight>();
			  theFlights.addAll(manager.flights);								//adding the flights to the empty Flight ArrayList.

			  for (int i = 0; i < theFlights.size(); i++) {
				  if (theFlights.get(i).getFlightNum().equals(flightNum)) {
					  theFlight = theFlights.get(i);							//finding the correct flight based on its flightNum.
				  }
			  }
			  manager.printPassengers(theFlight);						//calling the printPassengers() method from Passenger.java to print all the passengers on the flight.
		  }
			System.out.print("\n>");
		}
	}

	
}

