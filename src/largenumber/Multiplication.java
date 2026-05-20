package largenumber;

public class Multiplication implements Arithmetic {
    @Override
    public String calculate(LargeNumber left, LargeNumber right) {
        return multiply(left, right).toString();
    }

    LargeNumber multiply(LargeNumber left, LargeNumber right) {
        DigitList leftDigits = left.digitsCopy();
        DigitList rightDigits = right.digitsCopy();

        if (leftDigits.isZero() || rightDigits.isZero()) {
            return LargeNumber.zero();
        }

        DigitList result = DigitList.zero();
        int shift = 0;

        // Move from right to left so each multiplier digit produces one shifted partial product.
        for (DigitNode current = rightDigits.getTail(); current != null; current = current.prev) {
            DigitList partial = LargeNumber.multiplyAbsByDigit(leftDigits, current.digit);
            LargeNumber.appendShiftZeros(partial, shift);
            result = LargeNumber.addAbs(result, partial);
            shift++;
        }

        return new LargeNumber(left.isNegative() != right.isNegative(), result);
    }
}
