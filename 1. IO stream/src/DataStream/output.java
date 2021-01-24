package DataStream;

import java.io.*;

public class output {
    public static void main(String[] args) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("E:\\testFile.txt"));

        byte b = 20;
        short s = 35;
        int i = 60;
        float f = 70.5F;
        long l = 13L;

        dos.writeByte(b);
        dos.writeShort(s);
        dos.writeInt(i);
        dos.writeFloat(f);
        dos.writeLong(l);

        dos.flush();
        dos.close();
    }
}
