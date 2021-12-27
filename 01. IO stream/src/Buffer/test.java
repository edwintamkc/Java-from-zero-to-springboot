package Buffer;

import java.io.*;

public class test {
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("E:\\testFile.txt");
        InputStreamReader reader = new InputStreamReader(in); //轉換
        BufferedReader br = new BufferedReader(reader);

        String s = null;
        while((s = br.readLine()) != null){
            System.out.println(s);
        }

        br.close();
    }
}
