import java.io.File;

public class IOFileWalker {
    public static void main(String[] args) {
        File targetDir = new File("target");
        deleteFolder(targetDir);
    }

    public static void deleteFolder(File directory) {     
        File[] fileList = directory.listFiles();

        for (File file : fileList) {
            System.out.println((file.isDirectory() ? "Directory" : "File") + " - " +
                                file.getAbsolutePath());
            if (file.isDirectory()) deleteFolder(file);
            file.delete();
        }
    }
}