import java.util.*;
public class HotelBooking{

    int floor;
    boolean AC;
    int occupancy;
    public HotelBooking(int floor, int occupancy, boolean AC) {
        this.floor = floor;
        this.AC = AC;
        this.occupancy = occupancy;
    }
    static ArrayList<Integer> taken= new ArrayList<Integer>();

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        for(;;){
            System.out.println("Ready to Continue Booking? true/false");
            boolean f=in.nextBoolean();
            if(f==false)
                break;
            System.out.println("No of People? Max Capacity 3");
            byte occupancy = in.nextByte();
            System.out.println("AC/ Non-Ac");
            boolean AC = in.nextBoolean();
            System.out.println("Which Floor? Provide other value than 1 or 2 for no floor preference");
            byte floor = in.nextByte();

            HotelBooking b = new HotelBooking(floor, occupancy, AC);

            int room_no=(b.gen_room_no())[0];
            int floor_no=((b.gen_room_no())[1]>2)?2:1;
            if (room_no!=1)
            {
                taken.add(room_no);
                String ac = (AC == true) ? "Yes" : "No";
                System.out.println("Wishing a very happy stay! Room Number: " + room_no + " floor: " + floor_no + " AC?: " + ac + " Occupancy: " + occupancy + " Estimated Cost per day :Rs." + b.price());
            }
            else
                System.out.println("Not Available! Retry.");
        }
    }
    private int[] gen_room_no()
    {
    /* Occupance = 1 or 2 or ....., AC =1 or NOn-AC = 0
    101 - Single occupancy, AC
    102 - Double occupancy, AC
    103 - Double occupancy
    201 - Single occupancy, AC
    202 - Single occupancy
    203 - Double occupancy, AC
    204 - Triple occupancy, AC
     */
        int room_id = -1;
        int cad = (AC == true) ? 1 : 0;
        int rooms[] = {101, 102, 103, 201, 202, 203, 204};
        int max[] = {1, 2, 2, 1, 1, 2, 3};
        int cnd[] = {1, 1, 0, 1, 0, 1, 1};
        int start = (floor == 2) ? 3 : 0;
        int end = (floor == 1) ? 3 : 7;
        int pos = -1;
        boolean f = false;
        for (int i = start; i < end; i++) {
            if (max[i] == occupancy && cnd [i] == cad) {
                if (taken.contains(rooms[i]))
                    continue;
                room_id = rooms[i];
                pos = i;

            }
        }
        int[] room = {room_id, pos};
        return room;
    }
    private int price()
    {
        int sum=(occupancy==1)?2000:((occupancy==2)?3000:4000);
        sum=sum+((AC==true)?1000:0);
        return sum;
    }
}