package Buffer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class bufferReader {
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("E:\\testFile.txt");
        BufferedReader br = new BufferedReader(reader);

        String s = null;
        while((s = br.readLine()) != null){
            System.out.println(s);
        }

        br.close();

    }
}
