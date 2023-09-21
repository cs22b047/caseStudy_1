import java.time.LocalTime;

interface VehicleDetails{
    boolean paymentStatus(Vehicle Vh);
}

class Vehicle implements VehicleDetails{   
    int floor=0,slot=0;
    long Paid=0;  
    String vehicleType="";
    Payment Pt=new Payment();
    String vehicleDetails="";
    String Special="";

    // Making the entry/exit time stamp as private. This can be accessed using the getter and setter method defined
    private LocalTime entryTimeStamp;  
    private LocalTime exitTimeStamp;  

    //getter Method
    public LocalTime getEntryTimeStamp() {
        return entryTimeStamp;
    }
    public LocalTime getExitTimeStamp() {
        return exitTimeStamp;
    }

    // setter Method
    public void setEntryTimeStamp(LocalTime objT) {
        this.entryTimeStamp=objT;
    }
    public void setExitTimeStamp(LocalTime exitTimeStamp) {
        this.exitTimeStamp = exitTimeStamp;
    }
    public boolean paymentStatus(Vehicle Vh) {
        if(Pt.paymentStatus(Vh)){return true;}
        return false;
    }

}

