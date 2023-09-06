import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateDirDemo {

    public static void main(String[] args) {
        File fileRef = new File("/home/ranjith-suranga/Desktop/dep-11");
        boolean created = fileRef.mkdir();
        System.out.println(created ? "Folder created": "Folder already exists");

        Path pathRef = Paths.get("/home/ranjith-suranga/Desktop/born-to-code");
        try {
            Files.createDirectory(pathRef);
            System.out.println("Folder created");
        }catch (FileAlreadyExistsException e){
            System.out.println("Folder already exists");
        } catch (IOException e) {
            System.out.println("IO Aaawa...!");
            e.printStackTrace();
        }
    }
}
