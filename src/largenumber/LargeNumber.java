package largenumber;

public class LargeNumber {
    private final boolean negative;
    private final DigitList digits;

    LargeNumber(boolean negative, DigitList digits) {
        // Normalize every number once so the rest of the code never has to handle "-0".
        this.digits = digits.copy();
        this.digits.trimLeadingZeros();
        this.negative = negative && !this.digits.isZero();
    }

    public static LargeNumber parse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }

        String text = input.trim();
        if (text.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }

        boolean negative = false;
        int start = 0;
        char first = text.charAt(0);
        if (first == '-' || first == '+') {
            negative = first == '-';
            start = 1;
        }

        if (start == text.length()) {
            throw new IllegalArgumentException("Sign must be followed by digits.");
        }

        DigitList parsedDigits = new DigitList();
        for (int i = start; i < text.length(); i++) {
            char character = text.charAt(i);
            if (!Character.isDigit(character)) {
                throw new IllegalArgumentException("Only integer digits are allowed.");
            }
            parsedDigits.append(character - '0');
        }

        return new LargeNumber(negative, parsedDigits);
    }

    @Override
    public String toString() {
        String text = digits.toDigitString();
        if (negative && !digits.isZero()) {
            return "-" + text;
        }
        return text;
    }

    boolean isNegative() {
        return negative;
    }

    DigitList digitsCopy() {
        return digits.copy();
    }

    LargeNumber negate() {
        if (digits.isZero()) {
            return this;
        }
        return new LargeNumber(!negative, digits);
    }

    static LargeNumber zero() {
        return new LargeNumber(false, DigitList.zero());
    }

    static DigitList addAbs(DigitList left, DigitList right) {
        DigitList result = new DigitList();
        DigitNode leftNode = left.getTail();
        DigitNode rightNode = right.getTail();
        int carry = 0;

        // Start at the tail because the least significant digit is processed first.
        while (leftNode != null || rightNode != null || carry > 0) {
            int sum = carry;
            if (leftNode != null) {
                sum += leftNode.digit;
                leftNode = leftNode.prev;
            }
            if (rightNode != null) {
                sum += rightNode.digit;
                rightNode = rightNode.prev;
            }
            result.prepend(sum % 10);
            carry = sum / 10;
        }

        result.trimLeadingZeros();
        return result;
    }

    static DigitList subtractAbs(DigitList larger, DigitList smaller) {
        // This method assumes |larger| >= |smaller|, so the final result is non-negative.
        DigitList result = new DigitList();
        DigitNode largerNode = larger.getTail();
        DigitNode smallerNode = smaller.getTail();
        int borrow = 0;

        while (largerNode != null) {
            int difference = largerNode.digit - borrow;
            if (smallerNode != null) {
                difference -= smallerNode.digit;
                smallerNode = smallerNode.prev;
            }

            if (difference < 0) {
                difference += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            result.prepend(difference);
            largerNode = largerNode.prev;
        }

        result.trimLeadingZeros();
        return result;
    }

    static DigitList multiplyAbsByDigit(DigitList number, int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Digit must be between 0 and 9.");
        }
        if (digit == 0 || number.isZero()) {
            return DigitList.zero();
        }

        DigitList result = new DigitList();
        int carry = 0;

        for (DigitNode current = number.getTail(); current != null; current = current.prev) {
            int product = current.digit * digit + carry;
            result.prepend(product % 10);
            carry = product / 10;
        }

        while (carry > 0) {
            result.prepend(carry % 10);
            carry /= 10;
        }

        result.trimLeadingZeros();
        return result;
    }

    static void appendShiftZeros(DigitList number, int count) {
        if (number.isZero()) {
            return;
        }
        for (int i = 0; i < count; i++) {
            number.append(0);
        }
    }

    static int compareAbs(DigitList left, DigitList right) {
        DigitList normalizedLeft = left.copy();
        DigitList normalizedRight = right.copy();

        if (normalizedLeft.size() != normalizedRight.size()) {
            return normalizedLeft.size() > normalizedRight.size() ? 1 : -1;
        }

        DigitNode leftNode = normalizedLeft.getHead();
        DigitNode rightNode = normalizedRight.getHead();
        while (leftNode != null && rightNode != null) {
            if (leftNode.digit != rightNode.digit) {
                return leftNode.digit > rightNode.digit ? 1 : -1;
            }
            leftNode = leftNode.next;
            rightNode = rightNode.next;
        }
        return 0;
    }

    static void appendDigitForDivision(DigitList number, int digit) {
        // Bringing down a digit in long division means remainder = remainder * 10 + digit.
        number.trimLeadingZeros();
        if (number.isZero()) {
            number.clear();
        }
        number.append(digit);
        number.trimLeadingZeros();
    }

    static int findQuotientDigit(DigitList remainder, DigitList divisor) {
        // The next quotient digit is the largest single digit whose product fits the remainder.
        for (int candidate = 9; candidate >= 1; candidate--) {
            DigitList product = multiplyAbsByDigit(divisor, candidate);
            if (compareAbs(product, remainder) <= 0) {
                return candidate;
            }
        }
        return 0;
    }

    static void trimTrailingZeros(StringBuilder text) {
        while (text.length() > 0 && text.charAt(text.length() - 1) == '0') {
            text.deleteCharAt(text.length() - 1);
        }
    }
}
