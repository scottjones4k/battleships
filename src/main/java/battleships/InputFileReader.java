package battleships;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFileReader {
    public static List<String> GetLines(String filePath) throws IOException {
        List<String> records = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null)
        {
            // Add line with trailing whitespace and comments removed
            records.add(line.split("//")[0].trim());
        }
        reader.close();
        return records;
    }
}
