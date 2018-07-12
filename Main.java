import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.*;


public class Main {
    //fields
    private Parser parser;
    private Room currentRoom;
    private RoomLayout roomLayout;  
    private ArrayList<Ghost> ghost;

    public static void main(String[] args){
        Main game = new Main();
        game.play();
    }

    //constructor
    public Main(){
        roomLayout = new RoomLayout();
        ghost = new ArrayList<Ghost>();
        for (int i=0; i<4;i++) {
            ghost.add(new Ghost(roomLayout));
        }
        setCurrentRoom();
        parser = new Parser();
    }

    //methods
    private void setCurrentRoom(){
        Random rand1 = new Random();

        int x;
        int y;
        
        // this makes sure the player ends up in a different room than the key.
        do {
            x = rand1.nextInt(4); //random number 0-4
            y = rand1.nextInt(4); //random number 0-4
            currentRoom = roomLayout.getRoom(x, y);
            //System.out.println("Reset Current Room.");
        } while (roomLayout.getRoom(x, y).getHasKey() || isMonsterInRoom(currentRoom) );
    }

    public void play() {
        //initialize display
        System.out.println();
        System.out.println("Welcome to...");
        System.out.println("                 The Haunted House!");
        System.out.println("                Text Adeventure Game");
        System.out.println("________________________________________________________");
        System.out.println("You find yourself locked inside a haunted house.");
        System.out.println("Its dark and you can't see anything.");
        System.out.println("Begin moving around this dark spooky house to");
        System.out.println("find the hidden key to get out and win!");
        System.out.println("Try not to repeat your steps to cover more ground.");
        System.out.println("Beware... after all, this is a haunted house......");
        System.out.println("--------------------------------------------------------");
        //System.out.println("These are your command options");
        //parser.showValidCommands();
        System.out.println("Type 'help' if you need it.");
        System.out.println("--------------------------------------------------------");
        System.out.println("As you begin to take your first step.. you listen into the darkness...");

        printGhostNoises();

        boolean playerHasQuit = false;
        boolean playerHasWon = false;
        boolean playerHasDied = false;

        //currentRoom.printCordinates();
        //for (Ghost g : ghost) {
            //g.printCordinates();
        //}

        //print where key is
        //roomLayout.printRoomWithKey();

        while (!playerHasQuit && !playerHasWon && !playerHasDied) {
        
            Command command = parser.getCommand();
            playerHasQuit = processCommand(command);
            printGhostNoises();

            if (currentRoom.getHasKey() == true) {
                playerHasWon = true;
            }
            if(isMonsterInRoom(currentRoom)) {
                playerHasDied = true;
            }
             
        }
        if (playerHasWon == true) {
            System.out.println("-------------------------------------------------");
            System.out.println("         !!  YOU FOUND THE KEY  !!");
            System.out.println("--------------------------------------------------");
            System.out.println("You hurry back to what feels like the front door..");
            System.out.println("... IT UNLOCKS! ... and you run out ");
            System.out.println("YOU WIN!");
            System.out.println("the end.");
        }
        if (playerHasDied == true) {
            System.out.println("--------------------------------------------------");
            System.out.println("               !!  A GHOST  !!");
            System.out.println("          !!!  YOU SCREAM IN FEAR  !!!");
            System.out.println("           !!  AHHHHHHHHHHhhhhhhh  !!");
            System.out.println("--------------------------------------------------");
            System.out.println("but... before you have a chance to move...");
            System.out.println("...you die");
            System.out.println("YOU LOSE!");
            System.out.println("the end.");
        }
        System.out.println("Thanks for playing.");
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        
        if(command.isUnknown()) {
            System.out.println("Invailded input!");
            return false;
        }

        String firstWord = command.getFirstWord();
        if(firstWord.equals("go")) {
            goRoom(command);
        } else if (firstWord.equals("help")) {
            System.out.println("These are your command options");
            parser.showValidCommands();
        } else if (firstWord.equals("quit")) {
            wantToQuit = true;
        } else if (firstWord.equals("exits")){
            System.out.println(currentRoom.getRoomExitsString());
        } else {
            System.out.println("Invailded input!");
            return false;
        }

        return wantToQuit;
    }

    
    private void goRoom(Command command) {
        //first command is go but need to check second command
        //check if invalid direction
        //else go to next room
        if (!command.hasSecondWord()) {
            //If no second word
            System.out.println("Go Where?");
            return;
        }
        
        String direction = command.getSecondWord();

        //Try to leave current room
        Room nextRoom = currentRoom.nextRoom(direction);

        if(nextRoom == null) {
            System.out.println("Can't go there!");
        } else {
            
            for (Ghost g : ghost) { 
                if (g.isClosetoPlayer(currentRoom) == null) {
                    g.move();
                    //System.out.print("Ghost moved. ");
                    //System.out.print(g.isClosetoPlayer(currentRoom));
                    //System.out.print(" ");
                    //System.out.print(g.isClosetoPlayer(currentRoom) == null);
                } //else {
                    //System.out.print("Ghost did not move. ");
                    //System.out.print(g.isClosetoPlayer(currentRoom));
                    //System.out.print(" ");
                    //System.out.printl(g.isClosetoPlayer(currentRoom) == null);
                //}
                
                //g.printCordinates(); 		
            }
            //currentRoom.printCordinates();

            this.currentRoom = nextRoom;

            //currentRoom.printCordinates();

            //roomLayout.printRoomWithKey();
            //print ghost descriptions 
        }
    }

    public boolean isMonsterInRoom(Room currentRoom) {
        boolean isMonsterinRoom = false;
        for (Ghost g : ghost) {
            if (g.getCurrentRoom() == currentRoom) 
                isMonsterinRoom = true;
        }

        return isMonsterinRoom;
    }

    public void printGhostNoises() {
        ArrayList<String> dir = new ArrayList<>();
        //Set<String> farDirections = new HashSet<String>();
        Set<String> closeDirections = new HashSet<String>();
        for (Ghost g : ghost) {
            
            if (g.isCloseEnoughToKillPlayer(currentRoom).size() != 0) {
                dir = g.isCloseEnoughToKillPlayer(currentRoom);
                closeDirections.add(dir.get(0));
                if (g.isCloseEnoughToKillPlayer(currentRoom).size() > 1) { 
                    closeDirections.add(dir.get(1));
                }
            }
            
            if (g.isClosetoPlayer(currentRoom)  != null) {
                closeDirections.add(g.isClosetoPlayer(currentRoom));
            }
        
        }
        //if (farDirections.size() != 0) {
            //System.out.println("You hear a distant noise to the...");
            //System.out.println(farDirections);
        //} 
        if (closeDirections.size() != 0) {
            //System.out.println("You hear a LOUD SPOOKY NOISE to the..");
            System.out.println("You hear a spooky noise to the..");
            System.out.println(closeDirections);
        }
        //if (farDirections.size() == 0 && closeDirections.size() == 0) {
        if (closeDirections.size() == 0) {
            System.out.println("...You hear nothing......   .....spooky...");
        }
        System.out.println();
        //farDirections.clear();
        closeDirections.clear();

    }
}