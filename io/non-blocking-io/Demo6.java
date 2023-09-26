import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Demo6 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get("something-2.txt");
        try(var channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ)){
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            channel.read(buffer, 0, null, 
                    new CompletionHandler<Integer, Object>() {

                @Override
                public void completed(Integer result, Object attachment) {
                    buffer.flip();
                    System.out.println(Charset.defaultCharset().decode(buffer));
                }

                @Override
                public void failed(Throwable exc, Object attachment) {  
                    exc.printStackTrace();
                }
            });
            
            System.out.println("----------------------");
            Thread.sleep(500);
        }
    }
}
