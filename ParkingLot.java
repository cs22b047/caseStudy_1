public class ParkingLot {
    public static void main(String[] args) throws Exception {
        Floor[] floorArray = new Floor[5];
        for(int i=0;i<5;i++){
            floorArray[i]=new Floor(i);
            System.out.println("");
        }
    }
}