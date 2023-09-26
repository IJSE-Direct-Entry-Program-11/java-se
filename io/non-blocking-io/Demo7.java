import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Demo7 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get("async.txt");
        if (!Files.exists(path)) Files.createFile(path);
        try(var channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE)){
            String content = "This is what I want to write in this file";
            ByteBuffer bytes = Charset.defaultCharset().encode(content);

            channel.write(bytes, 0, null, 
                new CompletionHandler<Integer, Object>() {

                    @Override
                    public void completed(Integer result, Object attachment) {
                        System.out.println("File Saved");
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        System.out.println("Failed to save the file");
                        exc.printStackTrace();
                    }
                    
                });

            System.out.println("-----------------------------------");
            System.out.println("I am not blocked here, I can execute some code lines here");
            Thread.sleep(500);
        }
    }
}
