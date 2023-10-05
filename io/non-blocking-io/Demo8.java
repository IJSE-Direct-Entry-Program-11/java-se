import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo8 {

    public static void main(String[] args) {
        Path path = Paths.get("something.txt");
        CompletableFuture<String> promise = CompletableFuture.supplyAsync(() -> {
            try {
                var fc = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int pos = 0;
                StringBuilder sb = new StringBuilder();
                while (true){
                    Future<Integer> future = fc.read(buffer, pos);
                    if (future.get() < 1) break;
                    pos += future.get();
                    buffer.flip();
                    sb.append(Charset.defaultCharset().decode(buffer));
                    buffer.clear();
                }
                return sb.toString();
            } catch (IOException | ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, Executors.newSingleThreadExecutor());
        promise.thenAccept(System.out::println)
                .whenComplete((unused, throwable) -> System.out.println("Finish"));
        System.out.println("I can execute some code here");
    }
}
