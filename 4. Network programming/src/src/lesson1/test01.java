package src.lesson1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class test01 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address1 = InetAddress.getByName("127.0.0.1");
        System.out.println(address1);
        InetAddress address2 = InetAddress.getByName("www.google.com");
        System.out.println(address2);
    }
}
