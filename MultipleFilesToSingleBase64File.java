import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class MultipleFilesToSingleBase64File {
    public static void fileToBase64(String filePath, FileWriter fileWriter) throws IOException {
        // Read the image/GIF file into a byte array
        File file = new File(filePath);
        FileInputStream fileStream = new FileInputStream(file);
        byte[] fileBytes = new byte[(int) file.length()];
        fileStream.read(fileBytes);
        fileStream.close();

        // Encode the byte array into a Base64 string
        String encodedString = Base64.getEncoder().encodeToString(fileBytes);

        // Write the Base64 string to the text file with a delimiter
        fileWriter.write(encodedString + "\n---END OF FILE---\n");
        System.out.println("File " + filePath + " has been converted to Base64 and added to the text file.");
    }

    public static void main(String[] args) {
        try {
            // Example file paths (can include both images and GIFs)
            String[] filePaths = { "files/image1.jpg", "files/image2.gif", "files/image3.png" };

            // Open a FileWriter for the output text file
            FileWriter fileWriter = new FileWriter("output.txt");

            // Loop through all files and convert them to Base64
            for (String filePath : filePaths) {
                fileToBase64(filePath, fileWriter);
            }

            fileWriter.close();
            System.out.println("All files have been encoded and saved to output.txt.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
