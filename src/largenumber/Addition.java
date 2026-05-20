package largenumber;

public class Addition implements Arithmetic {
    @Override
    public String calculate(LargeNumber left, LargeNumber right) {
        return add(left, right).toString();
    }

    LargeNumber add(LargeNumber left, LargeNumber right) {
        DigitList leftDigits = left.digitsCopy();
        DigitList rightDigits = right.digitsCopy();

        // Same signs: add absolute values and keep the shared sign.
        if (left.isNegative() == right.isNegative()) {
            return new LargeNumber(left.isNegative(), LargeNumber.addAbs(leftDigits, rightDigits));
        }

        // Different signs: subtract the smaller absolute value from the larger one.
        int comparison = LargeNumber.compareAbs(leftDigits, rightDigits);
        if (comparison == 0) {
            return LargeNumber.zero();
        }
        if (comparison > 0) {
            return new LargeNumber(left.isNegative(), LargeNumber.subtractAbs(leftDigits, rightDigits));
        }
        return new LargeNumber(right.isNegative(), LargeNumber.subtractAbs(rightDigits, leftDigits));
    }
}
