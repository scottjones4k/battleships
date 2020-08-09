package battleships;

import battleships.actions.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    static final Pattern ShipPattern = Pattern.compile("\\(\\d+, \\d+, \\w+\\)");

    public static Board parse(List<String> inputLines) {
        // Start with board size
        Board board = Board.parse(inputLines.get(0));

        // Pull in ships
        Matcher matcher = ShipPattern.matcher(inputLines.get(1));
        List<Ship> ships = new ArrayList<>();
        while (matcher.find()) {
            ships.add(Ship.parse(matcher.group()));
        }

        // Pull in actions
        List<String> actionDescriptorList = inputLines.subList(2, inputLines.size());
        List<Action> actionList = new ArrayList<>();
        actionDescriptorList.forEach(inputLine -> actionList.add(Action.parse((inputLine))));

        // Setup board
        board.setShips(ships);
        board.setActions(actionList);

        return board;
    }
}
