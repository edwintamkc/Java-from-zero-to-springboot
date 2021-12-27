# 1. IO的分類

>按照byte作為單位嘅stream好常用，因為可以讀唔同類型嘅文件，例如txt, png, mp3等
>
>而按char作為單位嘅stream通常只用作讀取純txt文件，連word都讀唔到
>
>​	**從disk去memory叫做Read，亦叫做 input**
>
>​	**從memory去disk叫做Write，亦叫做output**
>

### `IO分類`

- inputStream (byte)
  - fileInputStream
  - bufferedInputStream

- outputStream (byte)
  - fileOutputStream
  - bufferedOutputStream

- reader (char)
  - fileReader
  - bufferedReader
- writer (char)
  - fileWriter
  - bufferedWriter



## 1.1 注意事項

所有stream都有implement closeable interface，所以用完**記得call close()**

所有output stream都有implement flushable interface，用完**記得call flush()**去清空pipe入面嘅資料



# 2. File class

> I/O 通常都與File class息息相關，所以講左file先

`construct`

- File(String filepathname)
- File(String parent, String child)
- File(File parent, String child)

```java
// construct
// 將一個已經存在嘅txt 整成一個file objcet
// 1.
File file1 = new File("D:/abc/1.txt");

// 2.
File file2 = new File("D:/abc/", "1.txt");

// 3.						  
File file3 = new File("D:/abc/");
File file4 = new File(file3, "1.txt");

// create
// 1. create txt
File file5 = new File("D:/2.txt");  // 首先都係整個file object先
file5.createNewFile();

// 2. create directory
File file6 = new File("D:/a");
file6.mkdir();

// 3. 多層文件夾
File file7 = new File("D:/a/b/c");
file7.mkdirs();
```



`File class method`

- 創建功能
  - createNewFile()

  - mkdir()

    呢個function return 一個boolean，如果無呢個file，咁佢就會整一個，return true；如果本身已經有呢個file，咁就會return false

  - mkdirs()

- 判斷功能

  - isDirectory()

    判斷是否為文件夾

  - isFile()

    判斷是否為文件

  - exists()

- 獲取功能

  - getAbsolutePath()

  - getPath()

    get relative path (based on current project)

  - getName()

  - list()

    獲取指定位置下所有file (dir) 嘅名，return String array

  - listFiles()

    獲取指定位置下所有file (dir)嘅 File object，return File array



# 3. Reader and Writer

## 3.1 Reader

> read() 一次讀一個char array，return讀取到嘅char number，讀唔到return -1

```java
public class ReaderDemo{
    pulibc static void main(String[] args) throw IOException{
        // 1. create object
        Reader reader = new FileReader("lib/1.txt");
        
        // 2. read
        char[] chs = new char[1024];
        int len = 0;
        while((len = reader.read(chs)) != -1){
            String s = new String(chs,0,len);
            System.out.print(s);
        }
        
        // 3. close
        reader.close();
    }
}
```



## 3.2 Writer

 ```java
public class WriterDemo{
    pulibc static void main(String[] args) throw IOException{
        // 1. create object
        Writer writer = new FileWriter("lib/1.txt");
        
        // 2. writer
        // 寫一個char
        writer.write('a');
        // 寫char array
        char[] chs = {'a','b','c'};
        writer.write(chs);
        // 寫String
        writer.write("aasdfasdfasdfa@!");
        
        // 3. close
        writer.close();
    }
}
 ```



## 3.3 讀寫 (copy)

> 每次讀寫一個char

```java
public class CopyDemo1{
    pulibc static void main(String[] args) throw IOException{
        // 1. create object
        FileReader fr = new FileReader("lib/1.txt");
        FileWriter fw = new FileWriter("lib/2.txt");
        
        // 2. len
        int len = 0;
            
        // 3. read and write
        while((len = fr.read()) != -1){
            fw.write(len);
        }
        
        // 4. close
        fr.close();
        fw.close();
    }
}
```

> 每次讀寫一個char array

```java
public class CopyDemo1{
    pulibc static void main(String[] args) throw IOException{
        // 1. create object
        FileReader fr = new FileReader("lib/1.txt");
        FileWriter fw = new FileWriter("lib/2.txt");
        
        // 2. define char array and len
        char[] chs = new char[2048];
        int len = 0;
            
        // 3. read and write
        while((len = fr.read(chs)) != -1){
            fw.write(chs,0,len);
        }
        
        // 4. close
        fr.close();
        fw.close();
    }
}
```



## 3.4 buffer reader and writer

> 用buffer做緩衝，再做read/writer 會比直接read/writer快
>
> 原因：
>
> 直接read/write係讀一個寫一個，效率低 (相當於滴水咁一下一下咁滴)
>
> 而用buffer，就係先將output嘅內容擺曬入一個buffer array度 (memory)，之後再一次過output到disk (相當於首先用個水桶裝水，再一次過倒曬啲水入去指定嘅地方)
>
> `留意bufferedReader/bufferedWriter 自帶char array做input/output，所以佢永遠都係一次讀寫一個char array，而唔係讀寫一個char；default size = 8192 chars`



`一次讀寫一個char array (8192 chars)`

```java
public class BufferDemo{
    pulibc static void main(String[] args) throw IOException{
        // 1. create object
        // 用Buffer裝Reader/Writer
        BufferedReader br = new BufferedReader(new FileReader("lib/1.txt"));
        BufferedWriter bw = nwe BufferedWriter(new FileWriter("lib/2.txt"));
        
        // 2. len
        int len = 0;
        
        // 3. 讀寫
        while((len = br.read()) != -1){
            bw.write(len);
        }
        
        // 4. close
        br.close();
        bw.close();
    }
}
```

`一次讀寫一行`

```java
public class BufferDemo{
    pulibc static void main(String[] args) throw IOException{
        // 1. create object
        // 用Buffer裝Reader/Writer
        BufferedReader br = new BufferedReader(new FileReader("lib/1.txt"));
        BufferedWriter bw = nwe BufferedWriter(new FileWriter("lib/2.txt"));
        
        // 2. define string 接收readLine()嘅嘢
        String str;
        
        // 3. 讀寫
        // readLine()每次讀一行，return一個String，讀唔到就return null
        while((str = br.readLine()) != null){
            bw.write(str);
            bw.newLine();    // 記得換行，默認唔會換行
        }
        
        // 4. close
        br.close();
        bw.close();
    }
}
```





# 4. InputStream and OutputStream

> inputStream/ outputStream同Reader Writer嘅寫法差唔多
>
> 主要就係 char / char array 變左做 byte / byte array

## 4.1 讀寫 (copy)

```java
public class StreamCopy{
    pulibc static void main(String[] args) throw IOException{
        // 1. create object
        FileInputStream fis = new FileInputStream("lib/1.txt");
        FileOutputStream fos = new FileOutputStream("lib/2.txt");
        
        // 2. read
        byte[] bys = new byte[1024];
        int len = 0;
        while((len = reader.read(bys)) != -1){
            fos.write(bys,0,len);
        }
        
        // 3. close
        fis.close();
        fos.close();
    }
}
```



## 4.2 buffer inputStream and outputStream

> stream buffer都係有默認嘅buffer byte array，8192 bytes

``一次讀寫一個byte array (8192 bytes)`

```java
public class BufferDemo{
    pulibc static void main(String[] args) throw IOException{
        // 1. create object
        // 用Buffer裝Reader/Writer
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("lib/1.jpg"));
        BufferedOutputStream bos = nwe BufferedOutputStream(new FileOutputStream("lib/2.jpg"));
        
        // 2. len
        int len = 0;
        
        // 3. 讀寫
        while((len = bis.read()) != -1){
            bos.write(len);
        }
        
        // 4. close
        bis.close();
        bos.close();
    }
}
```



