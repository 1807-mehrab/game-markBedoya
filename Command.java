

public class Command {
    //fields
    private String firstWord;
    private String secondWord;

    //constructor
    public Command(String firstWord, String secondWord) {
        this.firstWord = firstWord;
        this.secondWord = secondWord;
    }

    //methods
        //String getFirstWord()
        //String getSecondWord()
        //boolean isUnknown()
        //boolean hasSecondWord()
    public String getFirstWord() {
        return firstWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public boolean isUnknown() {
        return (firstWord == null);
    }

    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}