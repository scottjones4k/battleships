package battleships.enums;

public enum Direction {
    N,
    E,
    S,
    W;

    public Direction transformLeft() {
        return Direction.values()[(ordinal()+3)%4];
    }

    public Direction transformRight() {
        return Direction.values()[(ordinal()+1)%4];
    }
}
