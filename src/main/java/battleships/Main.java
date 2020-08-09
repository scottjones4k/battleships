package battleships;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Main {
    public static void main(String args[]) throws IOException
    {
        File directory = new File("input");
        String[] inputs = directory.list();

        for(String inputPath: inputs) {
            String outputPath = String.format("output/%s", inputPath);
            String errorPath = String.format("output/%s.ERROR", inputPath);
            try {
                // Cleanup previous runs
                (new File(outputPath)).delete();
                (new File(errorPath)).delete();

                // Read in input
                List<String> inputLines = InputFileReader.GetLines(String.format("input/%s", inputPath));

                // Parse input
                Board board = InputParser.parse(inputLines);

                // Perform actions;
                board.performActions();

                // Print output
                FileWriter writer = new FileWriter(outputPath);
                board.print(writer);
                writer.close();
            }
            catch (Exception e) {
                // Print exception
                PrintWriter writer = new PrintWriter(new File(errorPath));
                e.printStackTrace(writer);
                writer.close();
            }
        }
    }
}
