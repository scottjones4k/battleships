import battleships.actions.Action;
import battleships.actions.MoveAction;
import battleships.actions.ShootAction;
import battleships.enums.Movement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActionParserTests {
    @Test
    public void ShootActionCorrectlyParsed() {
        // Given
        String input = "(5, 10)";

        // When
        Action action = Action.parse(input);

        // Then
        assertTrue(action instanceof ShootAction);

        assertEquals(5, action.getShipLocation().getX());
        assertEquals(10, action.getShipLocation().getY());
    }

    @Test
    public void MoveActionCorrectlyParsed() {
        // Given
        String input = "(10, 5) MRMLM";
        List<Movement> expected = new ArrayList<>();
        expected.add(Movement.M);
        expected.add(Movement.R);
        expected.add(Movement.M);
        expected.add(Movement.L);
        expected.add(Movement.M);

        // When
        Action action = Action.parse(input);

        // Then
        assertTrue(action instanceof MoveAction);
        assertEquals(10, action.getShipLocation().getX());
        assertEquals(5, action.getShipLocation().getY());

        MoveAction moveAction = (MoveAction)action;

        assertEquals(expected, moveAction.getMovements());
    }
}
