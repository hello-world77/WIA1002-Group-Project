package largenumber;

class DigitList {
    private DigitNode head;
    private DigitNode tail;
    private int size;

    DigitList() {
    }

    static DigitList zero() {
        DigitList list = new DigitList();
        list.append(0);
        return list;
    }

    void append(int digit) {
        DigitNode node = new DigitNode(digit);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    void prepend(int digit) {
        DigitNode node = new DigitNode(digit);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    DigitNode getHead() {
        return head;
    }

    DigitNode getTail() {
        return tail;
    }

    int size() {
        return size;
    }

    boolean isZero() {
        return size == 0 || (size == 1 && head.digit == 0);
    }

    DigitList copy() {
        DigitList copy = new DigitList();
        for (DigitNode current = head; current != null; current = current.next) {
            copy.append(current.digit);
        }
        copy.trimLeadingZeros();
        return copy;
    }

    void trimLeadingZeros() {
        // Keep one zero node so every DigitList still represents a valid number.
        while (size > 1 && head.digit == 0) {
            removeFirst();
        }

        if (size == 0) {
            append(0);
        }
    }

    String toDigitString() {
        trimLeadingZeros();

        StringBuilder builder = new StringBuilder();
        for (DigitNode current = head; current != null; current = current.next) {
            builder.append(current.digit);
        }
        return builder.toString();
    }

    private void removeFirst() {
        if (head == null) {
            return;
        }

        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
    }
}
