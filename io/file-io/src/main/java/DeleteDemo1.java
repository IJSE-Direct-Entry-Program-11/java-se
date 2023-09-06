import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteDemo1 {

    public static void main(String[] args) {
        File fileRef = new File("/home/ranjith-suranga/Desktop/dep-11");
        boolean deleted = fileRef.delete();
        System.out.println(deleted ? "Folder deleted": "Folder does not exist");

        File fileRef2 = new File("/home/ranjith-suranga/Desktop/abc.txt");
        deleted = fileRef2.delete();
        System.out.println(deleted ? "File deleted": "File does not exist");

        File fileRef3 = new File("/home/ranjith-suranga/Desktop/my-folder");
        deleted = fileRef3.delete();
        System.out.println(deleted ? "Folder deleted": "Folder can't be deleted");

        Path pathRef = Paths.get("/home/ranjith-suranga/Desktop/my-folder");
        try {
            Files.delete(pathRef);
            System.out.println("Folder deleted");
        }catch (DirectoryNotEmptyException e){
            System.out.println("Folder can't be deleted when it contains files");
        } catch (IOException e) {
            System.out.println("Exception ekak aawo...!");
            e.printStackTrace();
        }
    }
}
