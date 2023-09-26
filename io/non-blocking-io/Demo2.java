import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Demo2 {
    public static void main(String[] args) throws IOException  {
        Path path = Paths.get("something.txt");

        try(FileChannel fc = FileChannel.open(path, StandardOpenOption.READ)){
            
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //int read = -1;
            while (fc.read(buffer) != -1){
                // System.out.println(new String(buffer.array(), 0, read));
                System.out.printf("capacity=%s, pos=%s, limit=%s \n",
                    buffer.capacity(), buffer.position(), buffer.limit());
                buffer.flip();
                System.out.printf("capacity=%s, pos=%s, limit=%s \n",
                    buffer.capacity(), buffer.position(), buffer.limit());
                System.out.println(Charset.defaultCharset().decode(buffer));
                // System.out.printf("capacity=%s, pos=%s, limit=%s \n",
                //     buffer.capacity(), buffer.position(), buffer.limit());
                buffer.clear();
                // System.out.printf("capacity=%s, pos=%s, limit=%s \n",
                //     buffer.capacity(), buffer.position(), buffer.limit());
            }
        }

    }
}
