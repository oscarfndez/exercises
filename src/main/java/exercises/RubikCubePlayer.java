package exercises;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class RubikCubePlayer {

    public static final int NUM_COLORS = 6;
    public static final int ROWS = 3;

    enum Color {

        GREEN("G"),
        BLUE("B"),
        RED("R"),
        WHITE("W"),
        ORANGE("O"),
        YELLOW("Y");

        public String code;

        Color(String code) {
            this.code = code;
        }
    }

    enum MovementType {

        VERTICAL,
        HORIZONTAL,
        ROTATION
    }

    class Movement {
        public int line;
        public MovementType direction;
    }


    public static void main(String args[]) {

        RubikCubePlayer rubikCubePlayer = new RubikCubePlayer();
        Color[][][] rubikCube = rubikCubePlayer.randomizeStartingPoint();

        boolean finished = false;

        while (!finished) {
            rubikCubePlayer.printRubikCube(rubikCube);
            Movement nextMovement = rubikCubePlayer.randomizeMovement();
            rubikCubePlayer.applyMovement(rubikCube, nextMovement);
        }
    }

    private Movement randomizeMovement() {

        Movement movement = new Movement();
        movement.line = ThreadLocalRandom.current().nextInt(ROWS);
        movement.direction = Arrays.asList(MovementType.values()).get(ThreadLocalRandom.current().nextInt(NUM_COLORS));
        return movement;
    }

    private void applyMovement(Color[][][] rubikCube, Movement movement) {

        Color[][][] originalCopy = arrayCopy(rubikCube);

        switch (movement.direction) {
            case VERTICAL:
                applyVerticalMovement(originalCopy, rubikCube, 0, 3, movement.line);
                applyVerticalMovement(originalCopy, rubikCube, 3, 2, movement.line);
                applyVerticalMovement(originalCopy, rubikCube, 2, 1, movement.line);
                applyVerticalMovement(originalCopy, rubikCube, 1, 0, movement.line);

                //Missing the possible rotation
            case HORIZONTAL:
                applyHorizontalMovement(originalCopy, rubikCube, 0, 5, movement.line);
                applyHorizontalMovement(originalCopy, rubikCube, 5, 2, movement.line);
                applyHorizontalMovement(originalCopy, rubikCube, 2, 4, movement.line);
                applyHorizontalMovement(originalCopy, rubikCube, 4, 0, movement.line);

                //Missing the possible rotation
            case ROTATION:
        }
    }

    private void applyVerticalMovement(Color[][][] originalCube, Color[][][] destinationCube, int originalSide, int destinationSide, int line) {

        destinationCube[destinationSide][line][0] = originalCube[originalSide][line][0];
        destinationCube[destinationSide][line][1] = originalCube[originalSide][line][1];
        destinationCube[destinationSide][line][2] = originalCube[originalSide][line][2];
    }

    private void applyHorizontalMovement(Color[][][] originalCube, Color[][][] destinationCube, int originalSide, int destinationSide, int line) {

        destinationCube[destinationSide][0][line] = originalCube[originalSide][0][line];
        destinationCube[destinationSide][1][line] = originalCube[originalSide][1][line];
        destinationCube[destinationSide][2][line] = originalCube[originalSide][2][line];
    }

    public Color[][][] arrayCopy(Color[][][] src) {

        Color[][][] dst = new Color[ROWS][ROWS][ROWS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS; j++) {
                for (int k = 0; k < ROWS; k++) {
                    dst[i][j][k] = src[i][j][k];
                }
            }
        }
        return dst;
    }

    private Color[][][] randomizeStartingPoint() {

        Color[][][] rubikCube = new Color[NUM_COLORS][ROWS][ROWS];

        int[] howManyOfEachColor = new int[NUM_COLORS];
        for (int side = 0; side < NUM_COLORS; side++) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < ROWS; j++) {
                    while (rubikCube[side][i][j] == null) {
                        Color color = Arrays.asList(Color.values()).get(ThreadLocalRandom.current().nextInt(NUM_COLORS));
                        if (howManyOfEachColor[side] < 9) {
                            rubikCube[side][i][j] = color;
                            howManyOfEachColor[side]++;
                        }
                    }
                }
            }
        }
        return rubikCube;
    }

    private void printRubikCube(Color[][][] rubikCube) {
        System.out.println("Solved : " + isCubeSolved(rubikCube));
        for (int side = 0; side < NUM_COLORS; side++) {
            System.out.println("SIDE: " + side);
            for (int i = 0; i < ROWS; i++) {
                String row = "";
                for (int j = 0; j < ROWS; j++) {
                    row = row + rubikCube[side][i][j].code;
                }
                System.out.println(row);
            }
        }
    }

    public boolean isCubeSolved(Color[][][] rubikCube) {

        for (int side=0; side < NUM_COLORS; side++) {
           Color color = rubikCube[side][0][0];
           for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < ROWS; j++) {
                    if (rubikCube[side][i][j] != color) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
