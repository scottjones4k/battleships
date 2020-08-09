import battleships.Board;
import battleships.InputParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputParserTests {
    @Test
    public void ParserAddsOneShip() throws IOException {
        // Given
        List<String> input = new ArrayList<>();
        input.add("10");
        input.add("(5, 8, N)");

        // When
        Board board  = InputParser.parse(input);

        // Then
        Writer writer = new StringWriter();
        board.print(writer);
        assertEquals("(5, 8, N)\r\n", writer.toString());
    }

    @Test
    public void ParserAddsMultipleShips() throws IOException {
        // Given
        List<String> input = new ArrayList<>();
        input.add("10");
        input.add("(5, 8, N) (8, 10, E)");

        // When
        Board board  = InputParser.parse(input);

        // Then
        Writer writer = new StringWriter();
        board.print(writer);
        assertEquals("(5, 8, N)\r\n(8, 10, E)\r\n", writer.toString());
    }

    @Test
    public void ParserAddsOneAction() throws IOException {
        // Given
        List<String> input = new ArrayList<>();
        input.add("10");
        input.add("(5, 8, N)");
        input.add("(5, 8)");

        // When
        Board board  = InputParser.parse(input);
        board.performActions();

        // Then
        Writer writer = new StringWriter();
        board.print(writer);
        assertEquals("(5, 8, N) SUNK\r\n", writer.toString());
    }

    @Test
    public void ParserAddsMultipleActions() throws IOException {
        // Given
        List<String> input = new ArrayList<>();
        input.add("10");
        input.add("(5, 8, N)");
        input.add("(5, 8) MM");
        input.add("(5, 10)");

        // When
        Board board  = InputParser.parse(input);
        board.performActions();

        // Then
        Writer writer = new StringWriter();
        board.print(writer);
        assertEquals("(5, 10, N) SUNK\r\n", writer.toString());
    }
}
