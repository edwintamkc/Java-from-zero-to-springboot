package src.lesson1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class tcpServerDemo {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null; // 接收信息用，轉化

        try {
            // 1. 創建一個
            serverSocket = new ServerSocket(9999);

            while(true){
                // 2. 等client 連接,監聽
                socket = serverSocket.accept();
                // 3. 讀client信息
                is = socket.getInputStream();

                baos = new ByteArrayOutputStream(); // 唔直接用InputStream讀，用ByteArrayOutputStream 包裝一下
                byte[] buffer = new byte[1024];
                int len;
                while((len=is.read(buffer)) != -1){
                    baos.write(buffer,0,len);
                }
                System.out.println(baos.toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
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
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
