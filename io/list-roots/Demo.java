import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) throws IOException {
        /* io */
        /* In windows, this will list all the volumes (C:, D:, E:) */
        File[] listRoots = File.listRoots();
        System.out.println(Arrays.toString(listRoots));
        for (File file : listRoots) {
            File[] rootContent = file.listFiles();
            for (File f : rootContent) {
                System.out.println(f);
            }
        }

        System.out.println("===========================================");

        /* nio */
        /* In windows, this will list all the volumes (C:, D:, E:) */
        Iterable<Path> rootDirectories = FileSystems.getDefault().getRootDirectories();
        for (Path path : rootDirectories) {
            DirectoryStream<Path> rootContent = Files.newDirectoryStream(path);
            for (Path p : rootContent) {
                System.out.println(p.toAbsolutePath());
            }
            //rootContent.forEach(p -> System.out.println(p.toAbsolutePath()));
        }
    }
}