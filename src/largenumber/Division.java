package largenumber;
public class Division implements Arithmetic {
    private int decimalPlaces;
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
        DigitNode current = dividend.getHead();
        while (current != null) {
            LargeNumber.appendDigitForDivision(remainder, current.digit);
            int qDigit = LargeNumber.findQuotientDigit(remainder, divisor);
            quotient.append(qDigit);
            if (qDigit > 0) {
                DigitList product = LargeNumber.multiplyAbsByDigit(divisor, qDigit);
                remainder = LargeNumber.subtractAbs(remainder, product);
            }
            remainder.trimLeadingZeros();
            current = current.next;
        }
        quotient.trimLeadingZeros();
        String quotientText = quotient.toDigitString();
        StringBuilder fraction = new StringBuilder();
        int i = 0;
        while (i < decimalPlaces && !remainder.isZero()) {
            LargeNumber.appendDigitForDivision(remainder, 0);
            int qDigit = LargeNumber.findQuotientDigit(remainder, divisor);
            fraction.append(qDigit);
            if (qDigit > 0) {
                DigitList product = LargeNumber.multiplyAbsByDigit(divisor, qDigit);
                remainder = LargeNumber.subtractAbs(remainder, product);
            }
            remainder.trimLeadingZeros();
            i++;
        }
        LargeNumber.trimTrailingZeros(fraction);
        StringBuilder result = new StringBuilder();
        boolean isNegative = left.isNegative() != right.isNegative();
        if (isNegative && (!quotient.isZero() || fraction.length() > 0)) {
            result.append('-');
        }
        result.append(quotientText);
        if (fraction.length() > 0) {
            result.append('.');
            result.append(fraction);
        }
        return result.toString();
    }
}
