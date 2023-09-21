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

    public long advancePayment(Vehicle Vh,int i){
        Scanner sc=new Scanner(System.in);
        System.out.println("\tYou have opted for Advamce Payment \n\tPlease enter the estimated amount of time (in hours) you will be parking : ");    
        int t=sc.nextInt();
        long Amt=Pt.amount(t);
        if(Vh.Special.toLowerCase().equals("vip")){
            Amt+=500;
        }
        if(Vh.vehicleType.equals("E")){
            System.out.println("If you want to charge your car ,you will need to pay extra Rs.500/- Enter yes or no");
            String s=sc.next();
            if(s.toLowerCase().equals("yes")){Amt+=500;}
        }else if(Vh.vehicleType.equals("T")){
            System.out.println("Since Your Vehicle type is Truck you have to pay an extra of Rs.600/-");
            Amt+=600;
        }
        System.out.println("What is the method of payment : Credit card or cash");
        String s=sc.next();
        System.out.println("You have to pay : "+Amt);
        transaction(Amt);
        return Amt;
    }

}
