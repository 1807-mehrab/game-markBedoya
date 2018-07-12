import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class Ghost {
    //fields 
    private Room currentRoom;

    //constructor
    public Ghost() {
        
    }
    public Ghost(RoomLayout rl) {
        Random rand = new Random();
        int x = rand.nextInt(5);
        int y = rand.nextInt(5);

        this.currentRoom = rl.getRoom(x, y);
    }

    //methods 
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void move() {
        ArrayList<String> possibleMoves = new ArrayList<String>();

        possibleMoves = currentRoom.getListofExits();

        Random randomNumberBuffer = new Random();
        int randomMove = randomNumberBuffer.nextInt(possibleMoves.size());
        //System.out.println(possibleMoves.size());

        currentRoom = currentRoom.nextRoom(possibleMoves.get(randomMove));
        //System.out.println(possibleMoves.get(randomMove));
        //System.out.println("currrnt possible moves: " + possibleMoves);
        //System.out.println("Ghost moved to (" + currentRoom.getX() + "," + currentRoom.getY() + ")");
        
    }

    public String isClosetoPlayer(Room playerRoom) {
        String direction = null;

        if (playerRoom.getX()-currentRoom.getX() == 0 && playerRoom.getY()-currentRoom.getY() == 1) {
            //System.out.println("You hear a loud spooky noise to the north.");
            direction = "north";
        } else if (playerRoom.getX()-currentRoom.getX() == -1 && playerRoom.getY()-currentRoom.getY() == 0) {
            //System.out.println("You hear a loud spooky noise to the east.");
            direction = "east";
        } else if (playerRoom.getX()-currentRoom.getX() == 0 && playerRoom.getY()-currentRoom.getY() == -1) {
            //System.out.println("You hear a loud spooky noise to the south.");
            direction = "south";
        } else if (playerRoom.getX()-currentRoom.getX() == 1 && playerRoom.getY()-currentRoom.getY() == 0) {
            //System.out.println("You hear a loud spooky noise to the west.");
            direction = "west";
        }

        return direction;
    }

    public ArrayList<String> isCloseEnoughToKillPlayer(Room playerRoom) {
        ArrayList<String> direction = new ArrayList<>();

        if (playerRoom.getX()-currentRoom.getX() == 0 && playerRoom.getY()-currentRoom.getY() == 2) {
            //System.out.println("You hear a distant noise to the north.");
            direction.add("north");
        } else if (playerRoom.getX()-currentRoom.getX() == -1 && playerRoom.getY()-currentRoom.getY() == 1) {
            //System.out.println("You hear a distant noise to the north east");
            direction.add("north");
            direction.add("east");
        } else if (playerRoom.getX()-currentRoom.getX() == -2 && playerRoom.getY()-currentRoom.getY() == 0) {
            //System.out.println("You hear a distant noise to the east.");
            direction.add("east");
        } else if (playerRoom.getX()-currentRoom.getX() == -1 && playerRoom.getY()-currentRoom.getY() == -1) {
            //System.out.println("You hear a distant noise to the south east");
            direction.add("south");
            direction.add("east");
        } else if (playerRoom.getX()-currentRoom.getX() == 0 && playerRoom.getY()-currentRoom.getY() == -2) {
            //System.out.println("You hear a distant noise to the south.");
            direction.add("south");
        } else if (playerRoom.getX()-currentRoom.getX() == 1 && playerRoom.getY()-currentRoom.getY() == -1) {
            //System.out.println("You hear a distant noise to the south west.");
            direction.add("south");
            direction.add ("west");
        } else if (playerRoom.getX()-currentRoom.getX() == 2 && playerRoom.getY()-currentRoom.getY() == 0) {
            //System.out.println("You hear a distant noise to the west.");
            direction.add("west");
        } else if (playerRoom.getX()-currentRoom.getX() == 1 && playerRoom.getY()-currentRoom.getY() == 1) {
            //System.out.println("You hear a distant noise to the north west.");
            direction.add("north");
            direction.add("west");
        }

        return direction;
    }

    public void printCordinates() {
        System.out.println("current Ghost position (" + currentRoom.getX() + "," + currentRoom.getY() + ")");
    }
    
}