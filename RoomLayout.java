import java.util.Random;

public class RoomLayout {
    //fields 
    private Room[][] roomLayout;

    //constructor
    public RoomLayout() {
        roomLayout = new Room[5][5];
        //create all the rooms for the game in a grid.
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                roomLayout[i][j] = new Room();
                roomLayout[i][j].setX(j);
                roomLayout[i][j].setY(i);
            }
        }
        // set the rooms neighbor hashmap
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                if (i>0) { //has north room
                    roomLayout[i][j].setRoomExit("north", roomLayout[i-1][j]);
                }
                if (i<4) { //has south
                    roomLayout[i][j].setRoomExit("south", roomLayout[i+1][j]);
                }
                if (j>0) { //has west
                    roomLayout[i][j].setRoomExit("west", roomLayout[i][j-1]);
                }
                if (j<4) { //has east
                    roomLayout[i][j].setRoomExit("east", roomLayout[i][j+1]);
                }
                    
            }
        }

        //put key in a random room
        Random rand = new Random();
        int intBuffer1 = rand.nextInt(5);
        int intBuffer2 = rand.nextInt(5);

        roomLayout[intBuffer1][intBuffer2].setHasKey(true);
        //***********DEBUG************
        //System.out.println("key is at: (" + intBuffer1 + "," + intBuffer2 + ")");
        /* Test case for setting all rooms to have keys*/
        /*
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                roomLayout[i][j].setHasKey(true);
            }
        }
        roomLayout[0][0].setHasKey(false);
        */
        //***********DEBUG************
    }

    public Room getRoom(int x, int y) {
        return roomLayout[x][y];
    }

    public void printRoomWithKey() {
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                if (roomLayout[i][j].getHasKey())
                    System.out.println("Key position (" + j + "," + i + ")");
            }
        }
    } 
}