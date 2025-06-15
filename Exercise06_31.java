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