package FileStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileoutputStream {
    public static void main(String[] args) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream("E:\\testFile.txt",true);
            byte[] bytes = {97, 98, 99, 100};
            fos.write(bytes); // 1. write all elements in byte[], write abcd
            fos.write(bytes, 0, 2); // 2. write some elements in byte[], write ab

            fos.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null)
                try{
                    fos.close();
                } catch (IOException e){
                    e.printStackTrace();
            }
        }


    }
}
