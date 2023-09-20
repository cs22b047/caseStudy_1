class Floor{
    boolean slotOccupied=false;
    String VehicleDetail;
    /*Floor(int level){
        this.level=level;
        //System.out.println("Floor "+level);
        // Creating an array of slot objects
        /*Slot[] Slot=new Slot[20];
        // We are representing the slot id as <Floor_slot>
        for(int i=0;i<20;i++){
            Slot[i]=new Slot(this.level+"_"+i,false);
        }*/

    static boolean slotOccupied(Floor Fr){
        if(Fr.slotOccupied==true){
            return true;
        }
        return false;
    }
}