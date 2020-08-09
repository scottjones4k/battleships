package battleships;

import battleships.actions.Action;
import battleships.actions.MoveAction;
import battleships.actions.ShootAction;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class Board {
    private int sizeOfBoard;
    private List<Ship> ships;
    private List<Action> actions;

    public Board(int sizeOfBoard) {
        this.sizeOfBoard = sizeOfBoard;
    }

    public static Board parse(String inputLine) {
        return new Board(Integer.parseInt(inputLine));
    }

    public int getSizeOfBoard() {
        return sizeOfBoard;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public void setActions(List<Action> actionList) {
        this.actions = actionList;
    }

    public void performActions() {
        actions.forEach(action -> performAction(action));
    }

    private void performAction(Action action) {
        Ship ship = findShipAtCoordinates(action.getShipLocation());
        if (action instanceof ShootAction) {
            if (ship != null) {
                ship.shoot();
            }
        } else if (action instanceof MoveAction) {
            if (ship == null) {
                throw new IllegalStateException("No ship found to move from this location");
            }
            ship.move((MoveAction)action);
            if (!ship.locationValid(sizeOfBoard, this.ships)) {
                throw new IllegalStateException("Ship cannot be moved to this location");
            }
        }
    }

    private Ship findShipAtCoordinates(Coordinates coordinates) {
        for (Ship ship : this.ships) {
            if (ship.isAt(coordinates) && ship.isNotSunk()) {
                return ship;
            }
        }
        return null;
    }

    public void print(Writer writer) throws IOException {
        for (Ship ship : ships) {
            ship.print(writer);
        }
    }
}
