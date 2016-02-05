package exercises;

public enum Position {

    NORTH(1, "WEST", 2, "EAST"),
    EAST(2, "NORTH", 3, "SOUTH"),
    SOUTH(3, "EAST", 4, "WEST"),
    WEST(4, "SOUTH", 1, "NORTH");

    int left;
    String whereIfLeft;
    int right;
    String whereIfRight;

    Position(int left, String whereIfLeft, int right, String whereIfRight) {

        this.left = left;
        this.right = right;
        this.whereIfLeft = whereIfLeft;
        this.whereIfRight = whereIfRight;
    }

    public Position move(int whereToGo) {

        if (this.left == whereToGo) {
            return valueOf(whereIfLeft);
        } else if (this.right == whereToGo) {
            return valueOf(whereIfRight);
        }
        return null;
    }
}