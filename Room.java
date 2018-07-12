import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

public class Room {
    //fields
    private String description;
    private boolean displayedDescription;
    //hashmap links room's exits to there neighbor rooms.
    private HashMap<String, Room> roomExits;
    private boolean hasKey = false;
    private int xPosition;
    private int yPosition;

    //constructor
    public Room ()  {
        this.roomExits = new HashMap<>();
    }

    //methods
    public void setRoomExit(String direction, Room neighbor) {
        //setter hashmap
        //add a "in what direction", "to what room" key to value in the hashmap
        roomExits.put(direction, neighbor);
    }

    public String getDescription() {
        //getter description
        return description;
    }

    public String getRoomExitsString() {
        //getter hashmap
        //get a string discribing the possible exits to your current room
        //loop through hashmap and return the string of exits
        String returnString = "Exits:";
        Set<String> keys = roomExits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room nextRoom(String direction) {
        //return the next room depending on the secondWord input
        return roomExits.get(direction);
    }

    public ArrayList<String> getListofExits() {
        ArrayList<String> possibleMoves = new ArrayList<>();
        Set<String> keys = roomExits.keySet();
        for(String exit : keys) {
            possibleMoves.add(exit);
        }
        return possibleMoves;
    }

    public boolean getHasKey() {
        return this.hasKey;
    }

    public void setHasKey(boolean key) {
        this.hasKey = key;
    }

    public int getX() {
        return this.xPosition;
    }

    public void setX(int x) {
        this.xPosition = x;
    }

    public int getY() {
        return this.yPosition;
    }

    public void setY(int y) {
        this.yPosition = y;
    }

    public void printCordinates() {
        System.out.println("current Room position (" + xPosition + "," + yPosition + ")");
    }

}