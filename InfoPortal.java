import java.util.*;
interface CustomerInfoPortal{
    String VacantSpace="";
}

public class InfoPortal {
    Vehicle Vh=new Vehicle();
    Payment Pt=new Payment();
    
    public void transaction(long amount){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please type the amount shown above again for recomfirmation :");
        int r=sc.nextInt();
        if(amount==r){
            System.out.println("You have paid : Rs."+r);
            return ;
        }else{
            System.out.println("Transaction failed");
            transaction(amount);
        }
    }

    public void normalPayment(long amount,String VehicleType){
        transaction(amount);
    }

    public boolean advancePayment(int i,String VehicleType){
        Scanner sc=new Scanner(System.in);
        System.out.println("\tYou have opted for Advamce Payment \n\tPlease enter the estimated amount of time (in hours) you will be parking : ");    
        int t=sc.nextInt();
        System.out.println("You have to pay : "+Pt.amount(t));
        transaction(Pt.amount(t));
        return true;
    }

}
