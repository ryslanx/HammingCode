package correcter;

import java.io.*;
import java.util.Random;

public class Send {
    static void sendWithErrors() throws IOException {
        byte[] bytesToSend;

        try(InputStream inputStream = new FileInputStream("encoded.txt")) { // read bytes from encoded.txt
            bytesToSend = inputStream.readAllBytes();
        }

        Random random = new Random();
        for (int i = 0; i < bytesToSend.length; i++) { // make 1 error in each byte
            bytesToSend[i] ^= 1 << random.nextInt(8);
        }

        try (FileOutputStream fos = new FileOutputStream("received.txt")) { // write bytes with errors to received.txt
            fos.write(bytesToSend);
        }
    }
}
