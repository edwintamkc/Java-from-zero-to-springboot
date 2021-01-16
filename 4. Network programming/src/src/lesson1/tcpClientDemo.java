package src.lesson1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class tcpClientDemo {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream os = null;

        try {
            // 1. 設定server嘅IP， port number
            InetAddress serverIP = InetAddress.getByName("127.0.0.1"); // create InetAddress object
            int port = 9999; // 因為server果邊set左9999，呢度連翻去9999

            // 2. create socket
            socket = new Socket(serverIP, port);

            // 3. 用IO stream sd msg
            os = socket.getOutputStream(); // create OutputStream object
            os.write("hello from client~".getBytes()); // 傳嘅係byte[]，唔係String，呢個method return byte[]

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
