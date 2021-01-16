package src.lesson1;

import org.w3c.dom.ls.LSOutput;

import java.net.InetSocketAddress;

public class test02 {
    public static void main(String[] args) {
        InetSocketAddress address1 = new InetSocketAddress("127.0.0.01",8080);
        InetSocketAddress address2 = new InetSocketAddress("localhost",8080);

        System.out.println(address1);
        System.out.println(address2);

        System.out.println(address2.getAddress());
        System.out.println(address2.getHostName());
        System.out.println(address2.getPort());
    }
}
