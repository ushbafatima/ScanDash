import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Arduino {
    public static String scanProduct() {
    	System.out.println("Hi From Arduino Scan Product");
        String filePath = "test.txt"; // specify your file path here

        // Initial line count
        int initialLineCount = countLines(filePath);
        int newLineCount = initialLineCount;

        // Loop until the line count increases
        while (true) {
            newLineCount = countLines(filePath);
            if (newLineCount > initialLineCount) {
                break;
            }
        }

        // Get and print the last Card UID
        String lastCardUID = getCardUID(filePath);
        return lastCardUID;
        
    }

    private static int countLines(String filePath) {
        int lines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static String getCardUID(String filePath) {
        String lastCardUID = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Card UID: ")) {
                    lastCardUID = line.substring(10).trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastCardUID;
    }
}
