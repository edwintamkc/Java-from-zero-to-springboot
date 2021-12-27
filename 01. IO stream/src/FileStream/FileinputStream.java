package FileStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileinputStream {
    public static void main(String[] args){
        // 1. create object
        FileInputStream in = null;

        try{ // 2. set its location
            in = new FileInputStream("E:\\testFile.txt");

            //  3. read data
            byte[] bytes = new byte[in.available()];
            int readCount = in.read(bytes);
            System.out.println(new String(bytes));

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            // 4. close
            if(in != null){
                try{
                    in.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
