//Nico Robson
//501046490


public class Passenger {
    String name;
    String passportNum;
    String seatNum;

    public Passenger() {            //default constructor
        this.name = null;
        this.passportNum = null;
        this.seatNum = null;
    }

    public Passenger(String name, String passportNum, String seatNum) {           //main constructor instantiating each field.
        this.name = name;
        this.passportNum = passportNum;
        this.seatNum = seatNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public String getPassportNum() {                                               //getters and setters for each field
        return passportNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public String toString()                                //a toString method to retrieve all the fields in a String
	{
		 return "\t Name: " + name + "\t Passport Number: "  + passportNum + "\t Seat Number: " + seatNum;
		
	}
    
    public boolean equals(Object other) {                           //a method that compares two passengers to see if there are the same.
        Passenger otherPassenger = (Passenger) other;
        boolean theSame = false;
        if (otherPassenger.getName().equals(this.name) && otherPassenger.getPassportNum().equals(this.passportNum)) {       //if they have same name and number,
            theSame = true;                                                                                                 //return true
        }
        return theSame;                                                                                                     //otherwise return false by default.
    }
}
