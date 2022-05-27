import static org.junit.Assert.*;

import org.junit.Test;

import javax.annotation.Generated;

public class TestArrayDequeGold {
    private static final int ARRAY_LENGTH = 500;

    @Test
    public void GeneratedDeque() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        for (int i = 0; i < ARRAY_LENGTH; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                ads.addLast(i);
            } else {
                sad1.addFirst(i);
                ads.addFirst(i);
            }
        }
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                assertEquals("equal", ads.removeFirst(), ads.removeFirst());
            } else {
                assertEquals("equal", ads.removeLast(), ads.removeLast());
            }
        }
    }
}
}
