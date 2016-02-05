package exercises;

public class PianoPlayer {


    public static void main(String[] args) {

        String src = "12221";
        GameContext context = new GameContext(Position.NORTH, src);
        boolean bool = Piano.compute(context);
        System.out.println(bool);
    }

}
