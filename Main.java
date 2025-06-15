import java.util.Scanner; 
public class Main {

    /** Return true if the card number is valid */
    public static boolean isValid(long number) {
        int size = getSize(number);
        if (size < 13 || size > 16) return false;

        // check prefix
        if (!(prefixMatched(number, 4)  // Visa
           || prefixMatched(number, 5)  // MasterCard
           || prefixMatched(number, 37) // American Express
           || prefixMatched(number, 6))) // Discover
            return false;

        // Luhn check
        int sum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
        return sum % 10 == 0;
    }

     /** Get the result from step 2: double every second digit, sum digits */
    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String s = Long.toString(number);
        // start from second-to-last digit
        for (int i = s.length() - 2; i >= 0; i -= 2) {
            int d = Character.getNumericValue(s.charAt(i));
            sum += getDigit(d * 2);
        }
        return sum;
    }

     /** Return this number if single digit, otherwise sum the two digits */
    public static int getDigit(int number) {
        if (number < 10) return number;
        return (number / 10) + (number % 10);
    }

     /** Sum the digits in the odd places from right to left */
    public static int sumOfOddPlace(long number) {
        int sum = 0;
        String s = Long.toString(number);
        // start from last digit
        for (int i = s.length() - 1; i >= 0; i -= 2) {
            sum += Character.getNumericValue(s.charAt(i));
        }
        return sum;
    }

     /** Return true if the number d is a prefix for number */
    public static boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize(d)) == d;
    }

    /** Return the number of digits in d */
    public static int getSize(long d) {
        return Long.toString(d).length();
    }

       /**
     * Return the first k number of digits from number. 
     * If the number of digits in number is less than k, return number.
     */
    public static long getPrefix(long number, int k) {
        String s = Long.toString(number);
        if (s.length() <= k) {
            return number;
        }
        return Long.parseLong(s.substring(0, k));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a credit card number as a long integer: ");
        long cardNumber = in.nextLong();
        in.close();

        if (isValid(cardNumber)) {
            System.out.println(cardNumber + " is valid");
        } else {
            System.out.println(cardNumber + " is invalid");
        }
    }
}
