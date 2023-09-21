import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

interface ParkingSlots{
    public void clearSlot(Slot[][] Sl,int i,int j);     //Clear Slot method will clear the slot if it contains any vehicle
    public void filledSlot(Slot[][] Sl);                //FilledSlot will print all the slots filled int the ParkingLot
}  

public class ParkingLot implements ParkingSlots{

    //Clear Slot method will clear the slot if it contains any vehicle
    public void clearSlot(Slot[][] Sl,int i,int j){
        Sl[i][j].slotOccupied=false;
    }

    //FilledSlot will print all the slots filled int the ParkingLot
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
        Vehicle[] Vh=new Vehicle[400];
        Scanner sc=new Scanner(System.in);   
        int i=0;
        String VehicleNumber;
        System.out.println("If you want to park a new Vehicle Type \"Park\"");
        String s=sc.next();

        //Program runs till user quits the program
        while(true){
            if(s.toLowerCase().equals("park")){
                i++;
                LocalTime ObjT = LocalTime.now();
                Vh[i]=new Vehicle();

                //Vehicle Details input
                System.out.println("Enter the Number Plate of the Vehicle : ");
                Vh[i].vehicleDetails=sc.next();

                //Vehicle Type Input
                System.out.println("Enter the Type of the Vehicle :\tEnter \n\tElectric Car\tE\n\tNormal Car\tN\n\tTruck\t\tT\n\tMotorCycle\tM");
                Vh[i].vehicleType=sc.next();

                ///Special Vehicles
                if(!Vh[i].vehicleType.equals("T")){
                    System.out.println("Do you want to use any of our services :\n\t1)VIP\n\t2)Handicapped\nIf no type \"no\" ");
                    s=sc.next();
                    Vh[i].Special=s;
                }

                //Advance Payment
                System.out.println("If You want to pay in advance type \"Advance\" else if you want to pay at exit type \"at Exit\"");
                String Advance=sc.next();
                if(Advance.toLowerCase().equals("advance")){
                    long Amt=Ip.advancePayment(Vh[i],i);
                    if(Amt!=0){
                        Pt.updatePaymentStatus(Amt,Vh[i]);
                    }
                }

                //Park Vehicle
                Av.parkVehicle(Sl, Vh[i], i);
                System.out.println("Your Vehicle is parked on floor "+Vh[i].floor+" at slot "+Vh[i].slot);

                //Vehicle entry TimeStamp
                Vh[i].setEntryTimeStamp(ObjT);

                System.out.println("\t\tVehicle Number : "+Vh[i].vehicleDetails+ "\t\tEntry Time : " +ObjT);

            }


            if(s.toLowerCase().equals("exit")){
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
