/*
 Parking Spots : 
    Total 10 Floors
    Floor 0 means Ground Floor
    Floor 0 and 1 only for Trucks
    Floor 2 onwards to 10 for Cars and Bikes 
    
    Total 40 Slots per Floor
    In ground floor :
        Slot 0 to 20 for Trucks
        Slot 20 to 40 for Handicapped
    In Other Floors : 
        Slot 0 to 15 for Normal Vehicles
        Slot 15 t 20 for Electric Vehicles
        Slot 20 to 40 for MotorCycles
 */



import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

interface Arranging{
    public void park(Slot[][] Sl,Vehicle Vh,int pos,int floorSi,int floorEi,int slotSi,int slotEi);
}

public class ArrangeVehicles implements Arranging {
    InfoPortal Ip=new InfoPortal();
    Payment Pt=new Payment();

    public String returnVehicleType(Vehicle Vh){
        String s="";
        if(Vh.vehicleType=="N"){s="Normal Car";}
        else if(Vh.vehicleType=="E"){s="Electric Car";}
        else if(Vh.vehicleType=="T"){s="Truck";}
        else if(Vh.vehicleType=="M"){s="MotorCycle";}
        return s;
    }

    public int findVehicle(Vehicle[] Vh,String VehicleNumber){
        for(int i=1;i<100;i++){
            if(Vh[i].vehicleDetails.equals(VehicleNumber)){
                return i;
            }
        }
        /*for(int i=0;i<10;i++){
            for(int j=0;j<40;j++){
                if(Sl[i][j].VehicleDetail.equals(VehicleNumber)){
                    return Sl[i][j].VehiclePos;
                }
            }
        }*/
        return 0;
    }

    /*public void parkNormal(Slot[][] Sl,Vehicle Vh,int pos){
        for(int i=2;i<10;i++){
            for(int j=0;j<15;j++){
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
        System.out.println("Parking Lot is full for normal Vehicles");
    }*/
    
    public void park(Slot[][] Sl,Vehicle Vh,int pos,int floorSi,int floorEi,int slotSi,int slotEi){
    ArrangeVehicles Av=new ArrangeVehicles();
        for(int i=floorSi;i<floorEi;i++){
            for(int j=slotSi;j<slotEi;j++){
                if(!Sl[i][j].slotOccupied){
                    Sl[i][j].slotOccupied=true;
                    System.out.println(i+" "+j);
                    Vh.floor=i;
                    Vh.slot=j;
                    Sl[i][j].VehiclePos=pos;
                    Sl[i][j].VehicleType=Vh.vehicleType;
                    Sl[i][j].VehicleDetail=Vh.vehicleDetails;
                    return;
                }
            }
        }
        System.out.println("Parking Lot is full for "+Av.returnVehicleType(Vh)+" Vehicles");
    }

    /*public void parkElectric(Slot[][] Sl,Vehicle Vh,int pos){
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
        System.out.println("Parking Lot for Electric Vehicles is full.");
    }*/

    public void parkVehicle(Slot[][] Sl,Vehicle Vh,int pos){
    ArrangeVehicles Av=new ArrangeVehicles();
        if(Vh.vehicleType.equals("E")){
            Av.park(Sl, Vh,pos, 2, 10, 15, 20);
        }
        else if(Vh.vehicleType.equals("N")){
            Av.park(Sl, Vh, pos, 2, 10, 0, 15);
        }
        else if(Vh.vehicleType.equals("T")){
            Av.park(Sl, Vh, pos, 0, 1, 0, 20);;
        }
        else if(Vh.vehicleType.equals("M")){
            Av.park(Sl, Vh, pos, 2, 10, 20, 40);
        }
    }

    public void exitVehicle(Slot[][] Sl,Vehicle Vh){
        LocalTime ObjT = LocalTime.now();
        Vh.setExitTimeStamp(ObjT);
        System.out.print("Entry Time : "+Vh.getEntryTimeStamp());
        System.out.println("\tExit Time : "+Vh.getExitTimeStamp());
        Duration timeElapsed = Duration.between(Vh.getEntryTimeStamp(), Vh.getExitTimeStamp());
        System.out.println("Time parked : " +timeElapsed.toMillis()/60000+"hrs");

        if(Vh.paymentStatus(Vh)){
            System.out.println("You have Paid in Advance for the Vehicle with Number Plate : "+Vh.vehicleDetails);
        }else{
            long amount=Pt.amount((timeElapsed.toMillis()/60000));
            System.out.println("Amount to be paid : "+amount+"Rs");
            Ip.transaction(amount);
            //Ip.normalPayment(Pt.amount((timeElapsed.toMillis()/60000)), null);
        }
        System.out.println("You can exit from Exit_"+Vh.floor);
    }
}
