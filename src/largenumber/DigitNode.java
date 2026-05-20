package largenumber;

class DigitNode {
    int digit;
    DigitNode prev;
    DigitNode next;

    DigitNode(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Digit must be between 0 and 9.");
        }
        this.digit = digit;
    }
}
