import java.time.Duration;
import java.time.LocalTime;
import java.util.*;


public class ParkingLot{
   
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Payment Pt=new Payment();
        Vehicle[] Vh=new Vehicle[100];
        int count=0,i=count++;
        System.out.println("If you want to enter a new Vehicle Type \"Enter\"");
        String s=sc.next();
        String VehicleNumber;
        if(s.equals("Enter")){
        LocalTime ObjT = LocalTime.now();
            Vh[i]=new Vehicle();
            System.out.println("Enter the Number Plate of the Vehicle : ");
            Vh[i].vehicleDetails=sc.next();
            Vh[i].setEntryTimeStamp(ObjT);
            System.out.println(ObjT);
        }
        System.out.println("If you want to exit Type \"Exit\"");
        s=sc.next();
        if(s.equals("Exit")){
            System.out.println("Enter your Car Number : ");
            VehicleNumber=sc.next();
            for(int j=0;j<100;j++){
                if(Vh[j].vehicleDetails.equals(VehicleNumber)){
                    i=j;
                    break;
                }
            }
            LocalTime ObjT = LocalTime.now();
            Vh[i].setExitTimeStamp(ObjT);
            System.out.println(Vh[i].getEntryTimeStamp());
            System.out.println(Vh[i].getExitTimeStamp());
            Duration timeElapsed = Duration.between(Vh[i].getEntryTimeStamp(), Vh[i].getExitTimeStamp());
            System.out.println(timeElapsed.toMillis()/1000);
            if(Vh[i].paymentStatus()){
                System.out.println("You have Paid");
            }else{
                System.out.println("Amount to be paid : "+Pt.amount((timeElapsed.toMillis()/60000)));
            }
        }
        //Floor[] floorArray = new Floor[5];
        //for(int i=0;i<5;i++){
        //    floorArray[i]=new Floor(i);
        //}
    }

}