import java.util.*; 

public class Parser {
    //fields
    private Scanner reader;
    private static final String[] validCommands = {
        "go", "north", "east", "south", "west", "exits", "help", "quit"
    };

    //constructor
    public Parser() {
        this.reader = new Scanner(System.in);
    }

    //methods
        //Command getCommand()
        //boolean isCommand(String)
        //void showValidCommands()
    public Command getCommand() {
        //SCAN PLAYER INPUT FOR A NEW COMMANDS
        //code here parses through the input by blank spaces (tokens)
        //gets the command out of it
        //checks if vaild command
        //return the command

        String inputLine = reader.nextLine();
        String word1 = null;
        String word2 = null;

        //scans for next line
        System.out.print("> ");
        //inputLine.nextLine();

        //tokenize the input line by blankspaces
        //extract word1 and word 2
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) { 
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
                //any other words are ignored
            }
        }
        tokenizer.close();

        //check if command known / valid
        if (isCommand(word1)) {
            if (isCommand(word2)) {
                //System.out.println("1");
                return new Command(word1, word2);
            } else {
                //System.out.println("2");
                return new Command(word1, null);
            }
        } else {
                //System.out.println("3");
                return new Command(null, null);
        }
    }

    public boolean isCommand(String input) {
        //check for valid command
        for(int i=0; i < validCommands.length; i++) {
            if (validCommands[i].equals(input)) {
                return true;
            }
        }
        //if we get here, string was not found in validCommands
        return false;
    }

    public void showValidCommands() {
        //create buffer to loop through valid cammands and print them
        System.out.print("Commands: ");
        for (String commandBuffer : validCommands) {
            System.out.print("'" + commandBuffer + "'" + " ");
        }
        System.out.println();
    }

}