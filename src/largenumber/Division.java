package largenumber;

public class Division implements Arithmetic {
    private final int decimalPlaces;

    public Division(int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("Decimal places cannot be negative.");
        }
        this.decimalPlaces = decimalPlaces;
    }

    @Override
    public String calculate(LargeNumber left, LargeNumber right) {
        return divide(left, right);
    }

    private String divide(LargeNumber left, LargeNumber right) {
        DigitList dividend = left.digitsCopy();
        DigitList divisor = right.digitsCopy();

        if (divisor.isZero()) {
            throw new ArithmeticException("division by zero");
        }
        if (dividend.isZero()) {
            return "0";
        }

        DigitList quotient = new DigitList();
        DigitList remainder = DigitList.zero();

        // Integer part: bring down one dividend digit at a time, like manual long division.
        for (DigitNode current = dividend.getHead(); current != null; current = current.next) {
            LargeNumber.appendDigitForDivision(remainder, current.digit);
            int quotientDigit = LargeNumber.findQuotientDigit(remainder, divisor);
            quotient.append(quotientDigit);
            if (quotientDigit > 0) {
                DigitList product = LargeNumber.multiplyAbsByDigit(divisor, quotientDigit);
                remainder = LargeNumber.subtractAbs(remainder, product);
            }
            remainder.trimLeadingZeros();
        }

        quotient.trimLeadingZeros();
        String quotientText = quotient.toDigitString();

        StringBuilder fraction = new StringBuilder();
        // Decimal part: keep bringing down zeroes until the remainder ends or precision is reached.
        for (int i = 0; i < decimalPlaces && !remainder.isZero(); i++) {
            LargeNumber.appendDigitForDivision(remainder, 0);
            int quotientDigit = LargeNumber.findQuotientDigit(remainder, divisor);
            fraction.append(quotientDigit);
            if (quotientDigit > 0) {
                DigitList product = LargeNumber.multiplyAbsByDigit(divisor, quotientDigit);
                remainder = LargeNumber.subtractAbs(remainder, product);
            }
            remainder.trimLeadingZeros();
        }

        LargeNumber.trimTrailingZeros(fraction);
        StringBuilder result = new StringBuilder();
        // Avoid printing "-0" when the rounded/truncated result has no non-zero digit.
        if (left.isNegative() != right.isNegative() && (!quotient.isZero() || fraction.length() > 0)) {
            result.append('-');
        }
        result.append(quotientText);
        if (fraction.length() > 0) {
            result.append('.').append(fraction);
        }

        return result.toString();
    }
}
