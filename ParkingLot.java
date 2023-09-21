import java.time.Duration;
import java.time.LocalTime;
import java.util.*;


public class ParkingLot{
    
    public void clearSlot(Slot[][] Sl,int i,int j){
        Sl[i][j].slotOccupied=false;
    }
    public void filledSlot(Slot[][] Sl){
        System.out.println("\n\t\t------------------\tParking Status\t------------------");
        for(int i=0;i<10;i++){
            for(int j=0;j<40;j++){
                if(Sl[i][j].slotOccupied){
                    System.out.println("Vehicle Number : "+Sl[i][j].VehicleDetail+"\tVehicle Type : "+Sl[i][j].VehicleType+"\tFloor : "+i+"\tSlot : "+j);
                }
            }
        }
        System.out.println();
    }
    public static void main(String[] args){
        Slot[][] Sl=new Slot[10][40]; //10 represents floors and 20 represents slots per floor
        for(int i=0;i<10;i++){
            for(int j=0;j<40;j++){
                Sl[i][j]=new Slot();
            }
        }
        ArrangeVehicles Av=new ArrangeVehicles();
        Payment Pt=new Payment();
        InfoPortal Ip=new InfoPortal();
        ParkingLot Pl=new ParkingLot();
        Vehicle[] Vh=new Vehicle[100];
        Scanner sc=new Scanner(System.in);   
        int i=0;
        String VehicleNumber;
        System.out.println("If you want to park a new Vehicle Type \"Park\"");
        String s=sc.next();

        //Program runs till user quits the program
        while(true){
            if(s.equals("Park")){
                i++;
                LocalTime ObjT = LocalTime.now();
                Vh[i]=new Vehicle();

                //Vehicle Details input
                System.out.println("Enter the Number Plate of the Vehicle : ");
                Vh[i].vehicleDetails=sc.next();

                //Vehicle Type Input
                System.out.println("Enter the Type of the Vehicle :\tEnter \nElectric Car\tE\nNormal Car\tN\nTruck\t\tT\nMotorCycle\tM");
                Vh[i].vehicleType=sc.next();

                //Advance Payment
                System.out.println("If You want to pay in advance type \"Advance\" else if you want to pay at exit type \"at Exit\"");
                String Advance=sc.next();
                if(Advance.equals("Advance")){if(Ip.advancePayment(i,Vh[i].vehicleType)){Pt.updatePaymentStatus(1,Vh[i]);};}

                //Park Vehicle
                Av.parkVehicle(Sl, Vh[i], i);

                //Vehicle entry TimeStamp
                Vh[i].setEntryTimeStamp(ObjT);

                System.out.println("Vehicle Number : "+Vh[i].vehicleDetails+ "\t\tEntry Time : " +ObjT);

            }


            if(s.equals("Exit")){
                //Which car to exit
                System.out.println("Enter your Vehicle Number : ");
                VehicleNumber=sc.next();
                //int Pos = Av.findVehicle(Sl, VehicleNumber);
                int Pos = Av.findVehicle(Vh, VehicleNumber);

                //Exit the Vehicle
                Av.exitVehicle(Sl, Vh[Pos]);

                // Clear the Slot
                Pl.clearSlot(Sl, Vh[Pos].floor, Vh[Pos].slot);
            }
            Pl.filledSlot(Sl);
            System.out.println("If you want to park a new Vehicle Type \"Park\" or want to exit a vehicle type \"Exit\"");
            s=sc.next();

        }
    }

}
