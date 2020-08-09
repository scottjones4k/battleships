import battleships.Board;
import battleships.Ship;
import battleships.actions.Action;
import battleships.enums.Direction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {
    @Test
    public void SetsBoardSizeCorrectly() {
        // Given
        String input = "10";

        // When
        Board board = Board.parse(input);

        // Then
        assertEquals(10,board.getSizeOfBoard());
    }

    @Test
    public void PerformActionLeavesExpectedState() {
        // Given
        Board board = new Board(10);
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(0,0, Direction.N));
        List<Action> actions = new ArrayList<>();
        actions.add(Action.parse("(0, 0)"));
        board.setShips(ships);
        board.setActions(actions);

        // When
        board.performActions();

        // Then
        assertFalse(ships.get(0).isNotSunk());
    }

    @Test
    public void PerformActionListLeavesExpectedState() {
        // Given
        Board board = new Board(10);
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(0,0, Direction.N));
        List<Action> actions = new ArrayList<>();
        actions.add(Action.parse("(0, 0) MM"));
        actions.add(Action.parse("(0, 2)"));
        board.setShips(ships);
        board.setActions(actions);

        // When
        board.performActions();

        // Then
        assertFalse(ships.get(0).isNotSunk());
    }

    @Test
    public void PerformMoveActionNoShipThrowsException() {
        // Given
        Board board = new Board(10);
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(0,0, Direction.N));
        List<Action> actions = new ArrayList<>();
        actions.add(Action.parse("(1, 1) MM"));
        board.setShips(ships);
        board.setActions(actions);

        // When
        Exception exception = assertThrows(IllegalStateException.class, board::performActions);

        // Then
        assertEquals("No ship found to move from this location", exception.getMessage());
    }

    @Test
    public void PerformMoveActionConflictingLocationThrowsException() {
        // Given
        Board board = new Board(10);
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(0,0, Direction.N));
        ships.add(new Ship(0,1, Direction.N));
        List<Action> actions = new ArrayList<>();
        actions.add(Action.parse("(0, 0) M"));
        board.setShips(ships);
        board.setActions(actions);

        // When
        Exception exception = assertThrows(IllegalStateException.class, board::performActions);

        // Then
        assertEquals("Ship cannot be moved to this location", exception.getMessage());
    }

    @Test
    public void PerformShootActionDoesNotErrorWhenMissing() {
        // Given
        Board board = new Board(10);
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(0,0, Direction.N));
        List<Action> actions = new ArrayList<>();
        actions.add(Action.parse("(1, 1)"));
        board.setShips(ships);
        board.setActions(actions);

        // When
        board.performActions();

        // Then
        assertTrue(ships.get(0).isNotSunk());
    }

    @Test
    public void PrintWritesExpected() throws IOException {
        // Given
        Board board = new Board(10);
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(0,0, Direction.N));
        ships.add(new Ship(1,1, Direction.N));
        List<Action> actions = new ArrayList<>();
        board.setShips(ships);
        board.setActions(actions);
        Writer writer = new StringWriter();

        // When
        board.print(writer);

        // Then
        assertEquals("(0, 0, N)\r\n(1, 1, N)\r\n", writer.toString());
    }
}
