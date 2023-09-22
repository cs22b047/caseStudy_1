import java.time.Duration;
import java.util.*;

/*
Every 1 min is considered as 1 hour 
 */

interface PaymentMethods{
    void creditMethod();        //This Method is called when the user wants to pay through Credit Card
    void cashMethod();          //This Method is called when the user wants to pay through Cash
    boolean paymentStatus(Vehicle Vh);      //This method returns the Payment Status of the Vehicle
}

public class Payment implements PaymentMethods{
    // If Paid is 0 : Not Paid 
    //Vehicle Vh=new Vehicle();

    //This Method generates the Parking Amount for the Vehicle
    public long amount(long l){
        if(l<=1){
            return 20;
        }else if(l>1 && l<=3){
            return 30;
        }
        
        return 50+(l-3)*5;
    }
    
    //This Method is called when the user wants to pay through Credit Card
    public void creditMethod(){
        System.out.println("You have Paid through Credit Card");
    }
    
    //This Method is called when the user wants to pay through Cash
    public void cashMethod() {
        System.out.println("You have Paid through Cash");
        
    }
    
    //This method returns the Payment Status of the Vehicle
    public boolean paymentStatus(Vehicle Vh){
        if(Vh.Paid==0){return false;}
        return true;
    }
    
    //This Method updates the Payment Status of the vehicle
    public void updatePaymentStatus(long i,Vehicle Vh){
        Vh.Paid=i;
    }
}
