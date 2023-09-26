import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Demo4 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("something-2.txt");
        if (!Files.exists(path)) Files.createFile(path);
        try(var fc = FileChannel.open(path, StandardOpenOption.WRITE)){
            String content = "I want to write this in something-2.txt file via nio";
            ByteBuffer bytes = Charset.defaultCharset().encode(content);
            fc.write(bytes);
        }
    }
}
