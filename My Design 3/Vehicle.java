import java.time.LocalTime;

interface VehicleDetails{
    boolean paymentStatus(Vehicle Vh);  //Returns the payment Status of the Vehicle
}

class Vehicle extends InfoPortal implements VehicleDetails{   
    int floor=0,slot=0;         //Contains the information of the floor and slot at which the vehicle is parked
    long Paid=0;                //Contains the inforamtion of how much amount is paid for the vehicle
    String vehicleType="";      //Contains the vehicle Type
    String vehicleDetails="";   //Contains the details of the Vehicle like NUmber Plate
    String Special="";          //Contains information whether the vehicle need services like vip or handicapped
    Payment Pt=new Payment();   //Creating Payment Class to access the Payment Methods

    /* Making the entry/exit time stamp as private so that they can't be accessed from outside 
    *    since the Parking amount depends on their difference.
    *   This can be accessed using the getter and setter method defined*/
    private LocalTime entryTimeStamp;  
    private LocalTime exitTimeStamp;  

    //getter Method for the Entry and Exit TimeStamp
    public LocalTime getEntryTimeStamp() {
        return entryTimeStamp;
    }
    public LocalTime getExitTimeStamp() {
        return exitTimeStamp;
    }
    
    // setter Methodfor the Entry and Exit TimeStamp
    public void setEntryTimeStamp(LocalTime objT) {
        this.entryTimeStamp=objT;
    }
    public void setExitTimeStamp(LocalTime exitTimeStamp) {
        this.exitTimeStamp = exitTimeStamp;
    }
    
    //Returns the payment Status of the Vehicle
    public boolean paymentStatus(Vehicle Vh) {
        if(Pt.paymentStatus(Vh)){return true;}
        return false;
    }

}