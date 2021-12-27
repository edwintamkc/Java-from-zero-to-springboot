import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class logger {
    public static void log(String msg) throws FileNotFoundException {
        // create printstream ant point to a txt
        PrintStream out = new PrintStream(new FileOutputStream("E:\\testFile.txt",true));
        // change the output place
        System.setOut(out);
        // Get current date
        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm::ss ss");
        String strTime = sdf.format(nowTime);

        System.out.println(strTime + ": " + msg);
    }
}
