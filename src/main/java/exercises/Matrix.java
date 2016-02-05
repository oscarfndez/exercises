package exercises;

public class Matrix {


    public static void main(String args[][]) {

        int[][] matrix = new int[20][20];


        long provisionalMaxProduct = 0;
        for (int i=0;i<20;i++) {
            for (int j=0;j<20;j++) {

                long resultHorizontal = matrix[i][j] * matrix[i+1][j] * matrix[i+2][j] * matrix[i+3][j];
                long resultVertical   = matrix[i][j] * matrix[i][j] * matrix[i+2][j] * matrix[i+3][j];


            }
        }



    }

}
