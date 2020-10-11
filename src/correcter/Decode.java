package correcter;

import java.io.*;

public class Decode {
    static void decode() throws IOException {
        byte[] bytesReceived;
        try (InputStream inputStream = new FileInputStream("received.txt")){ // read bytes from received.txt
            bytesReceived = inputStream.readAllBytes();
        }

        StringBuilder resultString = new StringBuilder();

        for (byte byt : bytesReceived) {
            String bt = Integer.toBinaryString(byt);
            String btr = bt.length() < 8 ? "0".repeat(8 - bt.length()) + bt :
                    bt.length() > 8 ? bt.substring(bt.length() - 8) : bt;
            System.out.println(btr);
            int d3 = Integer.parseInt(String.valueOf(btr.charAt(2)));
            int d5 = Integer.parseInt(String.valueOf(btr.charAt(4)));
            int d6 = Integer.parseInt(String.valueOf(btr.charAt(5)));
            int d7 = Integer.parseInt(String.valueOf(btr.charAt(6)));
            int p1 = Integer.parseInt(String.valueOf(btr.charAt(0)));
            int p2 = Integer.parseInt(String.valueOf(btr.charAt(1)));
            int p4 = Integer.parseInt(String.valueOf(btr.charAt(3)));

            int p1val = (d3 + d5 + d7) % 2;
            int p2val = (d3 + d6 + d7) % 2;
            int p4val = (d5 + d6 + d7) % 2;
            if ((p1val != p1 && p2val != p2) || (p2val != p2 && p4val != p4)
            || (p1val != p1 && p4val != p4)) {
                String binaryString = Integer.toString(p4val != p4 ? 1 : 0) + Integer.toString(p2val != p2 ? 1 : 0)
                        + Integer.toString(p1val != p1 ? 1 : 0);
                System.out.println(binaryString);
                int position = Integer.parseInt(binaryString, 2);
                System.out.println(position);
                if (position == 3) {
                    if (d3 == 0) {
                        d3 = 1;
                    } else {
                        d3 = 0;
                    }
                } else if (position == 6) {
                    if (d6 == 0) {
                        d6 = 1;
                    } else {
                        d6 = 0;
                    }
                } else if (position == 5) {
                    if (d5 == 0) {
                        d5 = 1;
                    } else {
                        d5 = 0;
                    }
                } else if (position == 7) {
                    if (d7 == 0) {
                        d7 = 1;
                    } else {
                        d7 = 0;
                    }
                }
            }
            resultString.append(d3).append(d5).append(d6).append(d7);

//            boolean[] curByte = new boolean[8];
//
//            for (int i = 0; i < 8; i++) {
//                curByte[i] = Integer.parseInt(String.valueOf(btr.charAt(i))) == 1;
//            }

//            int a = curByte[0] & curByte[1] ? 1 : 0, b = curByte[2] & curByte[3] ? 1 : 0,
//                    c = curByte[4] & curByte[5] ? 1 : 0, d = curByte[6] & curByte[7] ? 1 : 0;
//            boolean aXor = curByte[0] ^ curByte[1], bXor = curByte[2] ^ curByte[3],
//                    cXor = curByte[4] ^ curByte[5], dXor = curByte[6] ^ curByte[7];
//
//
//            if (dXor) {                                                 // decode bytes
//                resultString.append(a).append(b).append(c);
//            }   else {
//                resultString.append(!aXor ? a : (a ^ b ^ c) == d ? a : a ^ 1).
//                        append(!bXor ? b : (a ^ b ^ c) == d ? b : b ^ 1).
//                        append(!cXor ? c : (a ^ b ^ c) == d ? c : c ^ 1);
//            }
        }
        System.out.println("After decode:  " + resultString.toString());

        byte[] decodedBytes = new byte[resultString.length() / 8];

        for (int i = 0, j = 0; i < decodedBytes.length; i++, j += 8) {
            decodedBytes[i] = (byte) Integer.parseInt(resultString.substring(j, j + 8), 2);
        }

        try (FileOutputStream fos = new FileOutputStream("decoded.txt")) {      // write decoded bytes to decoded.txt
            fos.write(decodedBytes);
        }
    }
}
