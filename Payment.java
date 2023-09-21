import java.time.Duration;
import java.util.*;

/*
Every 1 min is considered as 1 hour 
 */

interface PaymentMethods{
    void creditMethod();
    void cashMethod();
    boolean paymentStatus(Vehicle Vh); 
}

public class Payment implements PaymentMethods{
    // If Paid is 0 : Not Paid 
    //Vehicle Vh=new Vehicle();
    public long amount(long l){
        if(l<=1){
            return 20;
        }else if(l>1 && l<=3){
            return 30;
        }
        
        return 50+(l-3)*5;
    }
    public void creditMethod(){
        System.out.println("You have Paid through Credit Card");
    }
    public void cashMethod() {
        System.out.println("You have Paid through Cash");
        
    }
    public boolean paymentStatus(Vehicle Vh){
        if(Vh.Paid==0){return false;}
        return true;
    }
    public void updatePaymentStatus(long i,Vehicle Vh){
        Vh.Paid=i;
    }
}
