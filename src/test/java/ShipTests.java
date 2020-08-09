import battleships.Coordinates;
import battleships.Ship;
import battleships.actions.MoveAction;
import battleships.enums.Direction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTests {
    @Test
    public void ParseParsesCorrectly()
    {
        // Given
        String input = "(10, 20, E)";

        // When
        Ship ship = Ship.parse(input);

        // Then
        assertTrue(ship.isAt(new Coordinates(10,20)));
        assertTrue(ship.isNotSunk());
    }

    @Test
    public void ShootSinksShip()
    {
        // Given
        Ship ship = new Ship(10,20, Direction.E);

        // When
        ship.shoot();

        // Then
        assertFalse(ship.isNotSunk());
    }

    @Test
    public void LocationInvalidSmallBoard()
    {
        // Given
        Ship ship = new Ship(10,20, Direction.E);

        // When
        boolean actual = ship.locationValid(10, new ArrayList<>());

        // Then
        assertFalse(actual);
    }

    @Test
    public void LocationValidBigBoard()
    {
        // Given
        Ship ship = new Ship(10,20, Direction.E);

        // When
        boolean actual = ship.locationValid(20, new ArrayList<>());

        // Then
        assertTrue(actual);
    }

    @Test
    public void LocationInvalidPositionClash()
    {
        // Given
        Ship ship = new Ship(10,20, Direction.E);
        Ship ship2 = new Ship(10,20, Direction.E);
        List<Ship> shipList = new ArrayList<>();
        shipList.add(ship);
        shipList.add(ship2);

        // When
        boolean actual = ship.locationValid(20, shipList);

        // Then
        assertFalse(actual);
    }

    @Test
    public void LocationValidPositionClashSunk()
    {
        // Given
        Ship ship = new Ship(10,20, Direction.E);
        Ship ship2 = new Ship(10,20, Direction.E);
        ship2.shoot();
        List<Ship> shipList = new ArrayList<>();
        shipList.add(ship);
        shipList.add(ship2);

        // When
        boolean actual = ship.locationValid(20, shipList);

        // Then
        assertTrue(actual);
    }

    @Test
    public void ApplyMovementLeftRotates() {
        // Given
        Ship ship = new Ship(10,20, Direction.E);

        // When
        ship.move((MoveAction)MoveAction.parse("(10, 20) L"));

        // Then
        assertEquals(Direction.N, ship.getDirection());
    }

    @Test
    public void ApplyMovementRightRotates() {
        // Given
        Ship ship = new Ship(10,20, Direction.E);

        // When
        ship.move((MoveAction)MoveAction.parse("(10, 20) R"));

        // Then
        assertEquals(Direction.S, ship.getDirection());
    }

    @Test
    public void ApplyMovementMoveMoves() {
        // Given
        Ship ship = new Ship(10,20, Direction.E);

        // When
        ship.move((MoveAction)MoveAction.parse("(10, 20) M"));

        // Then
        assertEquals(11, ship.getLocation().getX());
        assertEquals(20, ship.getLocation().getY());
    }

    @Test
    public void ApplyMovementListAppliesAllInOrder() {
        // Given
        Ship ship = new Ship(10,20, Direction.E);

        // When
        ship.move((MoveAction)MoveAction.parse("(10, 20) MLM"));

        // Then
        assertEquals(11, ship.getLocation().getX());
        assertEquals(21, ship.getLocation().getY());
    }

    @Test
    public void PrintWritesExpected() throws IOException {
        // Given
        Ship ship = new Ship(10,20, Direction.E);
        Writer writer = new StringWriter();

        // When
        ship.print(writer);

        // Then
        assertEquals("(10, 20, E)\r\n", writer.toString());
    }

    @Test
    public void PrintSunkWritesExpected() throws IOException {
        // Given
        Ship ship = new Ship(15,10, Direction.S);
        ship.shoot();
        Writer writer = new StringWriter();

        // When
        ship.print(writer);

        // Then
        assertEquals("(15, 10, S) SUNK\r\n", writer.toString());
    }
}
