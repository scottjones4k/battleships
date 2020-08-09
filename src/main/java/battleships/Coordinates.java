package battleships;

import battleships.enums.Direction;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof Coordinates))
            return false;

        Coordinates coordinates = (Coordinates)obj;

        return this.x == coordinates.x && this.y == coordinates.y;
    }

    public void move(Direction direction) {
        switch (direction) {
            case N -> y++;
            case E -> x++;
            case S -> y--;
            case W -> x--;
        }
    }

    public boolean isValid(int sizeOfBoard) {
        return x >= 0 && x <= sizeOfBoard && y >= 0 && y <= sizeOfBoard;
    }
}
