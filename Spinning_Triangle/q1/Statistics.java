package q1;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * <p>
 * (Please use text file for testing => input.txt) In this class, we will be
 * reading the user's input using file redirection. Then we will put all the
 * input values into an integer array named Input. by using a for loop, we will
 * loop through all user inputs from the text file and sum them up. then we will
 * use the sum and divided by number of inputs to find the mean. and using the
 * mean, we will be able to find the standard deviation.
 * </p>
 *
 * @author Antonio CaoShen
 * @version 1.0
 */

public class Statistics {

    /** declaring integer array named input. */
    private static int[] input;

    /**
     * This is the main method (entry point) that gets called by the JVM.
     * 
     * @param args
     *            command line arguments.
     */

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        final int fifty = 50;
        int i = 0;
        int sum = 0;
        int count = 0;
        double deviation = 0.0;
        double sd = 0.0;
        input = new int[fifty];

        DecimalFormat fmt = new DecimalFormat("#.##");

        while (scan.hasNext()) {
            if (i <= fifty) {
                input[i] = scan.nextInt();
                sum += input[i];
                i++;
                count++;
            }
        }

        double mean = (double) sum / count;

        for (int n = 0; n < count; n++) {
            deviation += Math.pow((input[n] - mean), 2);
        }

        sd = Math.sqrt(deviation / (count - 1));
        System.out.println("standard deviation is: " + fmt.format(sd));

        scan.close();

    }

}
