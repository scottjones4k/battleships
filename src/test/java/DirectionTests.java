import battleships.enums.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTests {
    @Test
    public void TransformLeftWorksAsExpected() {
        assertEquals(Direction.W,Direction.N.transformLeft());
        assertEquals(Direction.N,Direction.E.transformLeft());
        assertEquals(Direction.E,Direction.S.transformLeft());
        assertEquals(Direction.S,Direction.W.transformLeft());
    }

    @Test
    public void TransformRightWorksAsExpected() {
        assertEquals(Direction.E,Direction.N.transformRight());
        assertEquals(Direction.S,Direction.E.transformRight());
        assertEquals(Direction.W,Direction.S.transformRight());
        assertEquals(Direction.N,Direction.W.transformRight());
    }
}
