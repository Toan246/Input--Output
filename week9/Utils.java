package week9;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static String readContentFromFile(String path) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void writeContentToFile(String path, String content) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            writer.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendContentToFile(String path, String content) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            writer.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File findFileByName(String folderPath, String fileName) {
        Path folder = Paths.get(folderPath);
        try {
            List<Path> files = Files.list(folder)
                    .filter(p -> p.getFileName().toString().equals(fileName))
                    .collect(Collectors.toList());

            if (!files.isEmpty()) {
                return files.get(0).toFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        String filePath = "C:\\filename.txt";
        String fileContent = readContentFromFile(filePath);
        System.out.println("Content of the file:\n" + fileContent);

        String newContent = "This is the new content.";
        writeContentToFile(filePath, newContent);
        System.out.println("Content written to the file.");

        String additionalContent = "\nAdditional content.";
        appendContentToFile(filePath, additionalContent);
        System.out.println("Additional content appended to the file.");

        String folderPath = "C:\\filename_folder";
        String FileName = "filename.txt";
        File foundFile = findFileByName(folderPath, FileName);
        if (foundFile != null) {
            System.out.println("File found: " + foundFile.getAbsolutePath());
        } else {
            System.out.println("File not found.");
        }
    }
}
