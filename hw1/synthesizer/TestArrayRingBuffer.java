package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer<Double>(10);
        arb.enqueue(33.1); // 33.1 null null  null
        arb.enqueue(44.8); // 33.1 44.8 null  null
        arb.enqueue(62.3); // 33.1 44.8 62.3  null
        arb.enqueue(-3.4); // 33.1 44.8 62.3 -3.4
        assertEquals(arb.dequeue(), 33.1);     // 44.8 62.3 -3.4  null (returns 33.1)
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
