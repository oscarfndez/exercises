package exercises;

public class Piano {

    public static boolean compute(GameContext gameContext) {

        if (gameContext.remainingNotes.length() > 0) {

            int whatToPlay = Character.getNumericValue(gameContext.remainingNotes.charAt(0));
            Position nextPosition = gameContext.position.move(whatToPlay);
            if (nextPosition != null) {
                 return compute(new GameContext(nextPosition, gameContext.remainingNotes.substring(1)));
            } else {
                return false;
            }
        }
        return true;
    }
}
