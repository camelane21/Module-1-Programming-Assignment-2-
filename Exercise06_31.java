public class Exercise06_31 {

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