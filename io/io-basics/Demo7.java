import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

public class Demo7 {
    public static void main(String[] args) throws Exception{
        File file = new File("fastfood.psd");
        FileInputStream fis = new FileInputStream(file);

        try{
            byte[] signatureBuffer = new byte[4];
            fis.read(signatureBuffer);
            System.out.println(new String(signatureBuffer));

            // byte[] skipBytes = new byte[10];
            // fis.read(skipBytes);
            fis.skip(10);

            byte[] heightInBytes = new byte[4];
            fis.read(heightInBytes);

            System.out.println(Arrays.toString(heightInBytes));
            int byte1 = (heightInBytes[0] & 255) << 24;
            int byte2 = (heightInBytes[1] & 255) << 16;
            int byte3 = (heightInBytes[2] & 255) << 8;
            int byte4 = (heightInBytes[3] & 255);
            int height = byte1 | byte2 | byte3 | byte4;
            System.out.println("height=" + height);

            byte[] widthInBytes = new byte[4];
            fis.read(widthInBytes);

            System.out.println(Arrays.toString(widthInBytes));
            byte1 = (widthInBytes[0] & 255) << 24;
            byte2 = (widthInBytes[1] & 255) << 16;
            byte3 = (widthInBytes[2] & 255) << 8;
            byte4 = (widthInBytes[3] & 255);
            int width = byte1 | byte2 | byte3 | byte4;
            System.out.println("width=" + width);
            // width = 400
            // height = 300

        }finally{
            fis.close();
        }
    }
}
