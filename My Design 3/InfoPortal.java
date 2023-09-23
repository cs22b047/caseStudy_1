import java.util.*;

abstract class InfoPortal extends Payment implements VehicleDetails {
    //Returns the payment Status of the Vehicle
    public boolean paymentStatus(Vehicle Vh) {
        return Vh.paymentStatus(Vh);
    }
    //This method ensures that the user has paid the required amount else it will recurse itself
    protected static void transaction(long amount){
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

    //Method OverLoading
    //This method is called when the user want to pay at the time of exit 
    protected static void Payment(long amount,String VehicleType){
        transaction(amount);
    }

    //This method is called when the user want to pay in advance 
    protected static long Payment(Vehicle Vh,int i){
        Payment Pt=new Payment();
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
