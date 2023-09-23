    /*
 Parking Spots : 
    Total 10 Floors
    Floor 0 means Ground Floor
    Floor 0 only for Trucks and Handicapped
    Floor 1 for only VIPS Cars (normal and electric)
    Floor 2 onwards to 10 for Cars of normal and electric and Bikes 
    
    Total 40 Slots per Floor
    In ground floor :
        Slot 0 to 30 for Trucks
        Slot 30 to 40 for Handicapped
    In Other Floors : 
        Slot 0 to 15 for Normal Vehicles
        Slot 15 t 20 for Electric Vehicles
        Slot 20 to 40 for MotorCycles
 */

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

interface Arranging{
    String returnVehicleType(Vehicle Vh);        //Returns the Type of the Vehicle
    int findVehicle(Vehicle[] Vh,String VehicleNumber);  //Returns the position of the Vehicle
    void parkVehicle(Slot[][] Sl,Vehicle Vh,int pos);//This method will take into account the vehicle type and then call the park method
    void exitVehicle(Slot[][] Sl,Vehicle Vh);//This method will exit the vehicle from the ParkingLot
}

public class ArrangeVehicles extends InfoPortal implements Arranging {

    //Returns the Type of the Vehicle
    public String returnVehicleType(Vehicle Vh){
        String s="";
        if(Vh.vehicleType=="N"){s="Normal Car";}
        else if(Vh.vehicleType=="E"){s="Electric Car";}
        else if(Vh.vehicleType=="T"){s="Truck";}
        else if(Vh.vehicleType=="M"){s="MotorCycle";}
        return s;
    }

    //Returns the position of the Vehicle
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
        return -1;
    }

    //Parks the Vehicle in the slot
    private void park(Slot[][] Sl,Vehicle Vh,int pos,int floorSi,int floorEi,int slotSi,int slotEi){
    ArrangeVehicles Av=new ArrangeVehicles();
        for(int i=floorSi;i<floorEi;i++){
            for(int j=slotSi;j<slotEi;j++){
                if(!Sl[i][j].slotOccupied){
                    Sl[i][j].slotOccupied=true;
                    //System.out.println(i+" "+j);
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

    //This method will take into account the vehicle type and then call the park method
    public void parkVehicle(Slot[][] Sl,Vehicle Vh,int pos){
    //ArrangeVehicles Av=new ArrangeVehicles();
        if(Vh.Special.toLowerCase().equals("vip")){
            if(Vh.vehicleType.equals("E")){
                park(Sl, Vh, pos, 1, 2, 30, 40);
            }else{
                park(Sl, Vh, pos, 1, 2, 0, 30);
            }
        }
        else if(Vh.Special.toLowerCase().equals("handicapped")){
            park(Sl, Vh, pos, 0, 1, 30, 40);
        }
        else if(Vh.vehicleType.equals("E")){
            park(Sl, Vh,pos, 2, 10, 15, 20);
        }
        else if(Vh.vehicleType.equals("N")){
            park(Sl, Vh, pos, 2, 10, 0, 15);
        }
        else if(Vh.vehicleType.equals("T")){
            park(Sl, Vh, pos, 0, 1, 0, 30);;
        }
        else if(Vh.vehicleType.equals("M")){
            park(Sl, Vh, pos, 2, 10, 20, 40);
        }
        
    }

    //This method will exit the vehicle from the ParkingLot
    public void exitVehicle(Slot[][] Sl,Vehicle Vh){
        LocalTime ObjT = LocalTime.now();
        Vh.setExitTimeStamp(ObjT);
        System.out.print("Entry Time : "+Vh.getEntryTimeStamp());
        System.out.println("\tExit Time : "+Vh.getExitTimeStamp());
        Duration timeElapsed = Duration.between(Vh.getEntryTimeStamp(), Vh.getExitTimeStamp());
        System.out.println("Time parked : " +timeElapsed.toMillis()/60000+"hrs");

        if(Vh.paymentStatus(Vh)){
            long amount=amount((timeElapsed.toMillis()/60000));
            if(Vh.Paid<=amount){
                System.out.println("Amount to be Paid : "+amount+"\nYou have paid an advance of : "+Vh.Paid+"\nYou have a short fall of : "+(amount-Vh.Paid));
                transaction(amount-Vh.Paid);
            }
            else{
                System.out.println("You have Paid in Advance for the Vehicle with Number Plate : "+Vh.vehicleDetails);
            }
        }else{
            long amount=amount((timeElapsed.toMillis()/60000));
            if(Vh.Special.toLowerCase().equals("vip")){
                amount+=500;
                //System.out.println("You have to pay : "+amount);
            }else if(Vh.vehicleType.equals("T")){
                System.out.println("Since Your Vehicle type is Truck you have to pay an extra of Rs.600/-");
                amount+=600;
            }
            System.out.println("Amount to be paid : "+amount+"Rs");
            transaction(amount);
            //Ip.Payment(Pt.amount((timeElapsed.toMillis()/60000)), null);
        }
        System.out.println("Thank you for using our Parking Lot \n\t\tYou can exit from Exit "+Vh.floor);
    }
}
