package battleships;

import battleships.actions.MoveAction;
import battleships.enums.Direction;
import battleships.enums.Movement;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class Ship {
    private Coordinates location;
    private Direction direction;
    private boolean sunk;

    public Ship(int x, int y, Direction direction) {
        this.location = new Coordinates(x,y);
        this.direction = direction;
        sunk = false;
    }

    public static Ship parse(String descriptor) {
        String[] parts = descriptor.substring(1,descriptor.length()-1).split(", ");
        return new Ship(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Direction.valueOf(parts[2]));
    }

    public void print(Writer writer) throws IOException {
        writer.write(String.format("(%d, %d, %s)%s\r\n",
                location.getX(),
                location.getY(),
                direction.toString(),
                sunk ? " SUNK" : ""));
    }

    public Direction getDirection() {
        return direction;
    }

    public Coordinates getLocation() {
        return location;
    }

    public boolean isAt(Coordinates coordinates) {
        return location.equals(coordinates);
    }

    public void shoot() {
        sunk = true;
    }

    public boolean isNotSunk() {
        return !sunk;
    }

    public void move(MoveAction action) {
        action.getMovements().forEach(movement -> applyMovement(movement));
    }

    private void applyMovement(Movement movement) {
        switch (movement) {
            case L -> direction = direction.transformLeft();
            case R -> direction = direction.transformRight();
            case M -> location.move(direction);
        }
    }

    public boolean locationValid(int sizeOfBoard, List<Ship> ships) {
        if (!location.isValid(sizeOfBoard))
            return false;

        for(Ship ship : ships) {
            if (ship != this && ship.isAt(location) && ship.isNotSunk())
                return false;
        }

        return true;
    }
}
