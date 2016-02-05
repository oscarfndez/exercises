package exercises;

public class InvertedSpiralSequencePlayer {

    public static void main(String args[]) {

        int i = Integer.valueOf(args[0]);

        int oddSum = 0;
        int currentOdd = 1;
        while (oddSum < i) {
            oddSum += currentOdd;
            currentOdd += 2;
        }
        System.out.println ("oddSum: " + oddSum);
        System.out.println ("currentOdd: " + currentOdd);
        System.out.println ("result: " + (oddSum - (i - (oddSum - currentOdd) - 2) + 1));
    }
}
