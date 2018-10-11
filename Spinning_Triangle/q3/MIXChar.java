package q3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p> In this class, we will be using for loop to add all 
 * value from table 0 - 39 using unicode to an arraylist. 
 * To include the sepcial character we will add the special
 * constants seperately to the arraylist. Then we will use 
 * the arraylist to check if userinput is one of the character
 * on the table. Then we will use the index of the array to 
 * encode the char that the user inputted. Then we will do the 
 * opposite and decode back the long that we decoded in the 
 * beginning. Lastly, we will be printing both the encoded 
 * and decoded value in my main method.
 * </p>
 *
 * @author Antonio Cao Shen
 * @version 1.0
 */
public final class MIXChar {

    /** declaring special case for constant Delta. */
    private static final char DELTA = '\u0394';

    /** declaring special case for constant sigma. */
    private static final char SIGMA = '\u03A3';
    /** declaring special case for constant PI. */
    private static final char PI = '\u03A0';

    /** declaring char for special case unicode. */
    private static char[] spc;

    /** declaring a list to add all table values to array list. */
    private static ArrayList<Character> list;

    /** clearing value as private variable. */
    private int value;

    /**
     * constructor for MIXChar.
     * 
     * @param value
     *            an integer
     */
    private MIXChar(int value) {
        this.value = value;
    }

    /**
     * method to calculate all the unicode 
     * values and store them to an arraylist.
     */
    public static void calUnicode() {
        final int sixtyFive = 65;
        final int seventyThree = 73;
        final int seventyFour = 74;
        final int eightyTwo = 82;
        final int eightyThree = 83;
        final int ninty = 90;
        final int fortyEight = 48;
        final int fiftySeven = 57;

        spc = new char[] { '.', ',', '(', ')',
            '+', '-', '*', '/', '=', '$', '<', '>', '@', ';', ':', '\'' };
        list = new ArrayList<Character>();

        list.add(' ');
        for (int a = sixtyFive; a <= seventyThree; a++) {
            char z = (char) a;
            list.add(z);
        }
        list.add(DELTA);

        for (int b = seventyFour; b <= eightyTwo; b++) {
            char y = (char) b;
            list.add(y);
        }
        list.add(SIGMA);
        list.add(PI);

        for (int c = eightyThree; c <= ninty; c++) {
            char u = (char) c;
            list.add(u);
        }
        for (int d = fortyEight; d <= fiftySeven; d++) {
            char x = (char) (d);
            list.add(x);
        }

        for (int i = 0; i < spc.length; i++) {
            char w = (char) spc[i];
            list.add(w);
        }
    }

    /**
     * method check if is MIXChar.
     * 
     * @param c
     *            a char
     * @return boolean
     */
    static boolean isMIXChar(char c) {
        if (list.contains(c)) {
            return true;
        }
        return false;
    }

    /**
     * method that check char to MIXChar.
     * 
     * @param c
     *            a char
     * @return MIXchar
     */
    static MIXChar toMIXChar(char c) {
        if (!isMIXChar(c)) {
            throw new IllegalArgumentException("not a MIXChar");
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (c == list.get(i)) {
                    MIXChar mix = new MIXChar(i);
                    return mix;
                }
            }

        }
        throw new IllegalArgumentException("not a MIXChar");
    }

    /**
     * method accepts MIXchar and convert to char.
     * 
     * @param x
     *            MIXChar
     * @return x.value
     */
    static char toChar(MIXChar x) {
        return list.get(x.value);
    }

    /**
     * to string method.
     * 
     * @param m
     *            MIXChar array
     * @return a string
     */
    static String toString(MIXChar[] m) {
        String str = "";
        for (int i = 0; i < m.length; i++) {
            str += m[i];
        }
        return str;
    }

    /**
     * method that accepts String and convert to MIXChar.
     * 
     * @param s
     *            String
     * @return x
     */
    static MIXChar[] toMIXChar(String s) {
        MIXChar[] x = new MIXChar[s.length()];
        for (int i = 0; i < s.length(); i++) {
            x[i] = toMIXChar(s.charAt(i));
        }
        return x;
    }

    /**
     * ordinal.
     * 
     * @return value
     */
    int ordinal() {
        return value;
    }

    /**
     * encode method that encode MIXChar array.
     * 
     * @param m
     *            MIXChar array
     * @return Long array.
     */
    static long[] encode(MIXChar[] m) {
        final double eleven = 11.0;
        final int eleven2 = 11;
        final int fiftySix = 56;

        int formula = (int) Math.ceil(m.length / eleven);
        long[] encodeList = new long[formula];
        long total = 0;
        int count = 0;
        int counter = 0;
        long x = 1;

        for (int i = 0; i < m.length; i++) {
            if (counter == eleven2) {

                encodeList[count] = total;
                count++;
                counter = 0;
                total = 0;
                x = 1;
            } else {
                total += (long) m[i].value * x;
                x = x * fiftySix;
                counter++;
                encodeList[count] = total;
            }
        }
        return encodeList;
    }

    /**
     * method that decode the long[] array.
     * 
     * @param l
     *            long array
     * @return MIXChar[]
     */
    static MIXChar[] decode(long[] l) {
        final int base = 56;
        final int eleven = 11;
        MIXChar[] array = new MIXChar[eleven * l.length];
        long total = 0;
        int count = 0;

        for (int i = 0; i < l.length; i++) {
            total = l[i];

            for (int a = 0; a < eleven; a++) {
                long unsign = Long.divideUnsigned(total, base);
                int remainder = (int) Long.remainderUnsigned(total, base);
                total = unsign;
                array[count] = toMIXChar(list.get(remainder));
                count++;
            }
        }
        return array;
    }

    @Override
    public String toString() {
        return "" + list.get(value);
    }

    /**
     * returns value as integer.
     * 
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * <p>
     * This is the main method (entry point) that gets called by the JVM.
     * </p>
     *
     * @param a
     *            command line arguments.
     */
    public static void main(String[] a) {
        calUnicode();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter something: ");
        String input = scan.nextLine();
        MIXChar[] temp = toMIXChar(input.toUpperCase());
        long[] temp1 = encode(temp);
        for (int i = 0; i < temp1.length; i++) {
            System.out.println("Encoded: " + temp1[i]);
        }

        MIXChar[] x = decode(temp1);

        System.out.println("decoded: " + toString(x));

        scan.close();
    }

}
