package exercises;

public class GameContext {

    public Position position;
    public String remainingNotes;

    public GameContext(Position position, String remainingNotes) {

        this.remainingNotes = remainingNotes;
        this.position = position;
    }

}