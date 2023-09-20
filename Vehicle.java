import java.time.LocalTime;

interface VehicleDetails{
    boolean paymentStatus();     
    String vehichleType="";
}

class Vehicle implements VehicleDetails{
    Payment Pt=new Payment();
    String vehicleDetails="";
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
    public boolean paymentStatus() {
        if(Pt.paymentStatus()){return true;}
        return false;
    }
}

