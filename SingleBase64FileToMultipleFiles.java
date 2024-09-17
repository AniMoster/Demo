import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;

public class SingleBase64FileToMultipleFiles {
    public static void base64ToFile(String base64String, String outputFilePath) throws IOException {
        // Decode the Base64 string back into binary data
        byte[] fileData = Base64.getDecoder().decode(base64String);

        // Write the binary data to a file
        FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
        fileOutputStream.write(fileData);
        fileOutputStream.close();

        System.out.println("File saved as " + outputFilePath);
    }

    public static void main(String[] args) {
        try {
            // Read the entire text file into a string
            FileReader fileReader = new FileReader(new File("output.txt"));
            StringBuilder stringBuilder = new StringBuilder();
            int ch;
            while ((ch = fileReader.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            fileReader.close();

            // Split the Base64 string by the delimiter "---END OF FILE---"
            String[] base64Strings = stringBuilder.toString().split("---END OF FILE---");

            // Loop through each Base64 string and save it as a file (could be image or GIF)
            for (int i = 0; i < base64Strings.length; i++) {
                String base64String = base64Strings[i].trim();
                if (!base64String.isEmpty()) {
                    // Restore both images and GIFs
                    base64ToFile(base64String, "files/restored_file" + (i + 1) + (i % 2 == 0 ? ".jpg" : ".gif"));
                }
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
