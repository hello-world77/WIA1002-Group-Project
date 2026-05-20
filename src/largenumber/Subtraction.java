package largenumber;

public class Subtraction implements Arithmetic {
    private final Addition addition = new Addition();

    @Override
    public String calculate(LargeNumber left, LargeNumber right) {
        return subtract(left, right).toString();
    }

    LargeNumber subtract(LargeNumber left, LargeNumber right) {
        // a - b is handled as a + (-b), so sign logic stays in Addition.
        return addition.add(left, right.negate());
    }
}
