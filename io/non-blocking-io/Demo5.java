import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.management.RuntimeErrorException;

public class Demo5 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("something.txt");
        try(var channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ)){
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Future<Integer> future = channel.read(buffer, 0);
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    future.get();
                    buffer.flip();
                    return Charset.defaultCharset().decode(buffer).toString();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            // Let's do something else here
            System.out.println("============================================");
            System.out.println("I can execute this code, I am not blocked");            
            completableFuture.thenAccept(System.out::println);
            completableFuture.exceptionally(t -> {t.printStackTrace();return null;});
        }
    }
}
