package bank_system;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileHandler {

    public static String readFileToString(String filePath) throws IOException {
        return FileUtils.readFileToString(new File(filePath), "UTF-8");
    }

    public static List<String> readLines(String filePath) throws IOException {
        return FileUtils.readLines(new File(filePath), "UTF-8");
    }

    public static void writeStringToFile(String filePath, String data) throws IOException {
        FileUtils.writeStringToFile(new File(filePath), data, "UTF-8");
    }
}
