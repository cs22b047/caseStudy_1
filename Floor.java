class Floor{
    int level;
    Floor(int level){
        this.level=level;
        System.out.println("Floor "+level);
        Slot[] slotArray=new Slot[20];
        for(int i=0;i<20;i++){
            slotArray[i]=new Slot(i);
        }
    }
}