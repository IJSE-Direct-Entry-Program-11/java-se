import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteDemo2 {

    public static void main(String[] args) {
        File fileRef = new File("/home/ranjith-suranga/Desktop/my-folder");
        if (fileRef.isDirectory()){
            File[] fileList = fileRef.listFiles();
            for (File file : fileList) {
                file.delete();
                System.out.println(file + " - deleted");
            }
            fileRef.delete();
            System.out.println("Folder deleted");
        }

        Path pathRef = Paths.get("/home/ranjith-suranga/Desktop/my-folder-2");
        if (Files.isDirectory(pathRef)){
            try {
                DirectoryStream<Path> pathList = Files.newDirectoryStream(pathRef);
                for (Path path : pathList) {
                    Files.delete(path);
                    System.out.println(path + " - deleted");
                }
                Files.delete(pathRef);
                System.out.println("Folder deleted!");
            } catch (IOException e) {
                System.out.println("Failed to delete the folder");
                e.printStackTrace();
            }
        }
    }
}
