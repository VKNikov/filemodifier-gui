package filemodifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileModifier {

    public static void modifyFiles(String folderPath, String fileName, String regexPattern, String replacementText) {
        try {
            List<Path> filePaths = Files.walk(Paths.get(folderPath))
                    .filter(Files::isRegularFile)
                    .filter(x -> x.getFileName().toString().equals(fileName))
                    .collect(Collectors.toList());
            if (filePaths.isEmpty()) {
                System.out.println(String.format("No files with name %s found.", fileName));
            } else {
                getSearchPatternAndReplace(filePaths, regexPattern, replacementText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getSearchPatternAndReplace(List<Path> filePaths, String regexPattern, String replacementText) {
        for (Path file : filePaths) {
            findAndChangeString(file, regexPattern, replacementText);
        }
    }


    private static void findAndChangeString(Path file, String pattern, String replacementText) {
        try (Stream<String> lines = Files.lines(file)) {
            List<String> modifiedLines = lines.map(x -> x.replaceAll(pattern, replacementText))
                    .collect(Collectors.toList());
            Files.write(file, modifiedLines);
        } catch (IOException e) {
            System.out.println("Something went wrong while reading and writing to the file. Please try again.");
            e.printStackTrace();
        }
    }
}
