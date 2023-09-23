import java.time.Duration;
import java.util.*;

/*
Every 1 min is considered as 1 hour 
 */

interface PaymentMethods{
    void paymentMethod();
    boolean paymentStatus(Vehicle Vh);      //This method returns the Payment Status of the Vehicle
}

public class Payment implements PaymentMethods{
    // If Paid is 0 : Not Paid 

    //This Method updates the Payment Status of the vehicle
    protected static void updatePaymentStatus(long i,Vehicle Vh){
        Vh.Paid=i;
    }


    //This Method generates the Parking Amount for the Vehicle
    protected static long amount(long l){
        if(l<=1){
            return 20;
        }else if(l>1 && l<=3){
            return 30;
        }
        
        return 50+(l-3)*5;
    }
    public void paymentMethod() {
        System.out.println("You have paid through");
        creditMethod();
        cashMethod();
    }
    
    //This Method is called when the user wants to pay through Credit Card
    private void creditMethod(){
        System.out.println("Credit Card");
    }
    
    //This Method is called when the user wants to pay through Cash
    private void cashMethod() {
        System.out.println("Cash");
        
    }
    //This method returns the Payment Status of the Vehicle
    public boolean paymentStatus(Vehicle Vh){
        if(Vh.Paid==0){return false;}
        return true;
    }
    
}
