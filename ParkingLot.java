import java.time.Duration;
import java.time.LocalTime;
import java.util.*;


public class ParkingLot{
    
    public void clearSlot(Slot[][] Sl,int i,int j){
        Sl[i][j].slotOccupied=false;
    }

    public int findVehicle(Slot[][] Sl,String VehicleName){
        for(int i=2;i<10;i++){
            for(int j=15;j<20;j++){
                if(Sl[i][j].VehicleDetail.equals(VehicleName)){
                    return Sl[i][j].VehiclePos;
                }
            }
        }
        return 0;
    }

    public void filledSlot(Slot[][] Sl){
        System.out.println("--\t--\t--\t--\t--Parking Status--\t--\t--\t--\t--");
        for(int i=0;i<10;i++){
            for(int j=0;j<20;j++){
                if(Sl[i][j].slotOccupied){
                    System.out.println("Vehicle Number : "+Sl[i][j].VehicleDetail+"\tVehicle Type : "+Sl[i][j].VehicleType+"\t\tFloor : "+i+"\tSlot : "+j);
                }
            }
        }
        System.out.println();
    }
    
    public void parkElectric(Slot[][] Sl,Vehicle Vh,int pos){
        for(int i=2;i<10;i++){
            for(int j=15;j<20;j++){
                if(!Sl[i][j].slotOccupied){
                    Sl[i][j].slotOccupied=true;
                    Vh.floor=i;
                    Vh.slot=j;
                    Sl[i][j].VehiclePos=pos;
                    Sl[i][j].VehicleType=Vh.vehicleType;
                    Sl[i][j].VehicleDetail=Vh.vehicleDetails;
                    return;
                }
            }
        }
    }
    public static void main(String[] args){
        Slot[][] Sl=new Slot[10][20]; //10 represents floors and 20 represents slots per floor
        for(int i=0;i<10;i++){
            for(int j=0;j<20;j++){
                Sl[i][j]=new Slot();
            }
        }
        Payment Pt=new Payment();
        InfoPortal Ip=new InfoPortal();
        ParkingLot Pl=new ParkingLot();
        Vehicle[] Vh=new Vehicle[100];
        Scanner sc=new Scanner(System.in);   
        int i=0,floor=0,slotForElectric=0;
        String VehicleNumber;
        System.out.println("If you want to park a new Vehicle Type \"Park\"");
        String s=sc.next();
        while(true){
            if(s.equals("Park")){
                i++;
                LocalTime ObjT = LocalTime.now();
                Vh[i]=new Vehicle();

                //Vehicle Details input
                System.out.println("Enter the Number Plate of the Vehicle : ");
                Vh[i].vehicleDetails=sc.next();

                //Vehicle Type Input
                System.out.println("Enter the Type of the Vehicle : \tElectric\tNormal");
                Vh[i].vehicleType=sc.next();
                if(Vh[i].vehicleType.equals("Electric")){
                    Pl.parkElectric(Sl,Vh[i],i);
                }

                //Vehicle entry TimeStamp
                Vh[i].setEntryTimeStamp(ObjT);

                //Advance Payment
                System.out.println("If You want to pay in advance type \"Advance\" else if you want to pay at exit type \"at Exit\"");
                String Advance=sc.next();
                if(Advance.equals("Advance")){if(Ip.advancePayment(i,Vh[i].vehicleType)){Pt.updatePaymentStatus(1,Vh[i]);};}
                System.out.println("Entry Time : " +ObjT);
            }


            if(s.equals("Exit")){
                System.out.println("Enter your Car Number : ");
                VehicleNumber=sc.next();
                System.out.println(Pl.findVehicle(Sl, VehicleNumber));
                int Pos = Pl.findVehicle(Sl, VehicleNumber);
                LocalTime ObjT = LocalTime.now();
                Vh[Pos].setExitTimeStamp(ObjT);
                System.out.println(Vh[Pos].getEntryTimeStamp());
                System.out.println(Vh[Pos].getExitTimeStamp());
                Duration timeElapsed = Duration.between(Vh[Pos].getEntryTimeStamp(), Vh[Pos].getExitTimeStamp());
                System.out.println(timeElapsed.toMillis()/1000);
                if(Vh[Pos].paymentStatus(Vh[Pos])){
                    System.out.println("You have Paid in Advance\nVehicle with Number Plate : "+Vh[Pos].vehicleDetails+" Exited .");
                }else{
                    System.out.println("Amount to be paid : "+Pt.amount((timeElapsed.toMillis()/60000)));
                }
                Pl.clearSlot(Sl, Vh[Pos].floor, Vh[Pos].slot);
            }
            Pl.filledSlot(Sl);
            System.out.println("If you want to park a new Vehicle Type \"Park\" or want to exit a vehicle type \"Exit\"");
            s=sc.next();

        }
    }

}
