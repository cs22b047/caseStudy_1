import java.time.Duration;
import java.util.*;

/*
Every 1 min is considered as 1 hour 
 */

interface PaymentMethods{
    void creditMethod();
    void cashMethod();
    boolean paymentStatus(); 
}

public class Payment implements PaymentMethods{
    // If Paid is 0 : Not Paid  and 1 : Paid
    private int Paid=0;
    public long amount(long l){
        if(l<=1){
            return 20;
        }else if(l>1 && l<=3){
            return 30;
        }
        
        return 20+(l-3)*5;
    }
    public void creditMethod(){

    }
    public void cashMethod() {
        
    }
    public boolean paymentStatus(){
        if(Paid==1){return true;}
        return false;
    }
}
