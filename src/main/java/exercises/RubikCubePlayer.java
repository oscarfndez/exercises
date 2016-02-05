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

    enum Direction {

        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    class Side {

        public int code;
        public Adjacency left;
        public Adjacency right;
        public Adjacency up;
        public Adjacency down;
        public Adjacency opposite;

        Side(int code, Adjacency left, Adjacency right, Adjacency up, Adjacency down, Adjacency opposite) {

            this.code = code;
            this.left = left;
            this.right = right;
            this.up = up;
            this.down = down;
            this.opposite = opposite;
        }
    }

    class Movement {

        public int line;
        public Direction direction;
    }

    enum Rotation {
        D0,
        D90,
        D180,
        D270,
        D360
    }

     class Adjacency {

        public Side side;
        public Rotation rotation;

         Adjacency(Side sideCode, Rotation rotation) {
            this.side = side;
            this.rotation = rotation;
        }
    }

    public static void main(String args[]) {

        RubikCubePlayer rubikCubePlayer = new RubikCubePlayer();
        Color[][][] rubikCube = rubikCubePlayer.randomizeStartingPoint();
        rubikCubePlayer.printRubikCube(rubikCube);
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

    public void rotate(Side side, Color[][][] rubikCube, Movement rotation) {

        for (Side s : Side.values()) {
            computeAffectation(side, s, rubikCube, rotation);
        }
    }

    private void computeAffectation(Side originator, Side affectedSide, Color[][][] rubikCube, Movement rotation) {

        switch (rotation.direction) {
            case UP:
            case DOWN:
            case RIGHT:
            case LEFT:
        }
    }
}
