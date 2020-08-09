package battleships.actions;

import battleships.Coordinates;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Action {
    private static final Pattern ActionPattern = Pattern.compile("\\((\\d+), (\\d+)\\) ?(\\w+)?");
    private Coordinates shipLocation;

    protected Action(int shipX, int shipY) {
        shipLocation = new Coordinates(shipX, shipY);
    }

    public Coordinates getShipLocation() {
        return shipLocation;
    }

    public static Action parse(String inputLine) throws IllegalStateException {
        Matcher matcher = ActionPattern.matcher(inputLine);
        if (!matcher.matches()) {
            throw new IllegalStateException("Input line is not in the correct format");
        }
        int shipX = Integer.parseInt(matcher.group(1));
        int shipY = Integer.parseInt(matcher.group(2));
        // Only has 2 components - Shoot action
        if (matcher.group(3) == null) {
            return new ShootAction(shipX, shipY);
        }

        // Move action
        return new MoveAction(shipX, shipY, matcher.group(3));
    }
}
