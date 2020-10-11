package correcter;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Write a mode: ");
        switch (new Scanner(System.in).next()) {
            case "encode":
                Encode.encode();
                break;
            case "send":
                Send.sendWithErrors();
                break;
            case "decode":
                Decode.decode();
                break;
            default:break;
        }
    }
}