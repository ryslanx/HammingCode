package correcter;

import java.io.*;

public class Encode {

    public static void encode() throws IOException {
        byte[] sourceBytes;

        try(InputStream inputStream = new FileInputStream("C:\\Users\\Ruslan\\EduProjects\\Error Correcting Encoder-Decoder\\Error Correcting Encoder-Decoder\\task\\send.txt")) {
            sourceBytes = inputStream.readAllBytes();
        }

        StringBuilder source = new StringBuilder();
        StringBuilder result = new StringBuilder();

        for (byte sourceByte : sourceBytes) {  // save bytes array as string byte by byte
            String b = Integer.toBinaryString(sourceByte);
            source.append(b.length() < 8 ? "0".repeat(8 - b.length()) : "").append(b);
        }
        System.out.println(source);

//        if (source.length() % 3 != 0) {  // add zeroes at the end of string if number of bits not a multiple of 3
//            source.append("0".repeat(3 - source.length() % 3));
//        }

        for (int i = 0; i < source.length(); i += 4) { // encode result string of bits
            int d3 = Integer.parseInt(String.valueOf(source.charAt(i)));
            int d5 = Integer.parseInt(String.valueOf(source.charAt(i+1)));
            int d6 = Integer.parseInt(String.valueOf(source.charAt(i+2)));
            int d7 = Integer.parseInt(String.valueOf(source.charAt(i+3)));
            int p1 = (d3 + d5 + d7) % 2 == 0 ? 0 : 1;
            int p2 = (d3 + d6 + d7) % 2 == 0 ? 0 : 1;
            int p4 = (d5 + d6 + d7) % 2 == 0 ? 0 : 1;
            result.append(Integer.toString(p1))
                    .append(Integer.toString(p2))
                    .append(Integer.toString(d3))
                    .append(Integer.toString(p4))
                    .append(Integer.toString(d5))
                    .append(Integer.toString(d6))
                    .append(Integer.toString(d7)).append("0 ");
        }
        System.out.println(result.toString());
        String[] byteStrings = result.toString().split("\\s"); // split string by bytes
        byte[] bytesResult = new byte[byteStrings.length];

        for (int i = 0; i < byteStrings.length; i++) {
            bytesResult[i] = (byte) Integer.parseInt(byteStrings[i], 2);
        }

        try (FileOutputStream fos = new FileOutputStream("encoded.txt")) { // write encoded bytes
            fos.write(bytesResult);
        }
    }
}