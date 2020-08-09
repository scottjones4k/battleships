package battleships.actions;

import battleships.enums.Movement;

import java.util.ArrayList;
import java.util.List;

public class MoveAction extends Action{
    private final List<Movement> movements;

    protected MoveAction(int shipX, int shipY, String movementString) {
        super(shipX, shipY);
        movements = new ArrayList<>();

        movementString.chars().forEachOrdered(i -> movements.add(
                Movement.valueOf(new String(new char[] { (char)i }))
        ));
    }

    public List<Movement> getMovements() {
        return movements;
    }
}
