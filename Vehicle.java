interface VehicleDetails{
    String vehicleDetails="";
    boolean paymentStatus();     
    String vehichleType="";
}

abstract class Vehicle extends VehicleDetails{
    // Making the entry time stamp as private. This can be accessed using the getter and setter method defined
    private String entryTimeStamp="";  
    //getter Method
    public String getentryTimeStamp() {
        return entryTimeStamp;
    }
    // setter Method
    public void setentryTimeStamp(String entryTimeStamp) {
        this.entryTimeStamp=entryTimeStamp;
    }
}

