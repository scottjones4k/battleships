import battleships.Coordinates;
import battleships.enums.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinatesTests {
    @Test
    public void MoveNIncrementsY()
    {
        // Given
        Coordinates coordinates = new Coordinates(0,0);

        // When
        coordinates.move(Direction.N);

        // Then
        assertEquals(0,coordinates.getX());
        assertEquals(1,coordinates.getY());
    }

    @Test
    public void MoveEIncrementsX()
    {
        // Given
        Coordinates coordinates = new Coordinates(0,0);

        // When
        coordinates.move(Direction.E);

        // Then
        assertEquals(1,coordinates.getX());
        assertEquals(0,coordinates.getY());
    }

    @Test
    public void MoveSDecrementsY()
    {
        // Given
        Coordinates coordinates = new Coordinates(0,0);

        // When
        coordinates.move(Direction.S);

        // Then
        assertEquals(0,coordinates.getX());
        assertEquals(-1,coordinates.getY());
    }

    @Test
    public void MoveWDecrementsX()
    {
        // Given
        Coordinates coordinates = new Coordinates(0,0);

        // When
        coordinates.move(Direction.W);

        // Then
        assertEquals(-1,coordinates.getX());
        assertEquals(0,coordinates.getY());
    }

    @Test
    public void EqualsNonCoordinatesFalse()
    {
        // Given
        Coordinates coordinates = new Coordinates(0,0);
        int other = 5;

        // When
        boolean actual = coordinates.equals(other);

        // Then
        assertFalse(actual);
    }

    @Test
    public void EqualsSameCoordinatesObjectTrue()
    {
        // Given
        Coordinates coordinates = new Coordinates(0,0);

        // When
        boolean actual = coordinates.equals(coordinates);

        // Then
        assertTrue(actual);
    }

    @Test
    public void EqualsSameCoordinatesTrue()
    {
        // Given
        Coordinates coordinates = new Coordinates(1,1); // Non-zero to avoid default int
        Coordinates otherCoordinates = new Coordinates(1,1);

        // When
        boolean actual = coordinates.equals(otherCoordinates);

        // Then
        assertTrue(actual);
    }

    @Test
    public void EqualsDifferentXCoordinatesFalse()
    {
        // Given
        Coordinates coordinates = new Coordinates(1,1); // Non-zero to avoid default int
        Coordinates otherCoordinates = new Coordinates(2,1);

        // When
        boolean actual = coordinates.equals(otherCoordinates);

        // Then
        assertFalse(actual);
    }

    @Test
    public void EqualsDifferentYCoordinatesFalse()
    {
        // Given
        Coordinates coordinates = new Coordinates(1,1); // Non-zero to avoid default int
        Coordinates otherCoordinates = new Coordinates(1,2);

        // When
        boolean actual = coordinates.equals(otherCoordinates);

        // Then
        assertFalse(actual);
    }
}
