class Floor{
    int level;
    Floor(int level){
        this.level=level;
        //System.out.println("Floor "+level);
        // Creating an array of slot objects
        Slot[] slotArray=new Slot[20];
        // We are representing the slot id as <Floor_slot>
        for(int i=0;i<20;i++){
            slotArray[i]=new Slot(this.level+"_"+i);
        }
    }
}