package com.test;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 判斷接收嘅係 普通form 定係有file upload嘅form
        if(!ServletFileUpload.isMultipartContent(req)){
            return;  // 如果係普通form，return
        }

        try{
            // 2. 處理file upload所保存嘅位置，建議放係WEB-INF下，因為user無辦法入到
            String uploadPath = this.getServletContext().getRealPath("/WEB-INF/upload");
            File uploadFile = new File(uploadPath);
            if(!uploadFile.exists()){  // 如果呢個directory唔存在，整一個先
                uploadFile.mkdir();
            }

            // 3. 如果文件超出限制大小，放入一個臨時文件夾中，過幾日自動刪除
            String tmpPath = this.getServletContext().getRealPath("WEB-INF/temp");
            File tmpFile = new File(tmpPath);
            if(!tmpFile.exists()){
                tmpFile.mkdir();
            }

            // 4. 處理file upload
            // 需要用到stream，我地可以用HttpServletRequest.getInputStream嚟搞，但係好麻煩
            // 所以用commons-fileupload (jar file) 入面嘅FileItem.getInputStream嚟搞，佢又依賴於commons-io呢個jar file，所以呢兩個都要import
            // ----------------------------------------------------------------------------------------
            // 我地需要用ServletFileUpload object去處理file upload，但係呢個object需要一個叫DiskFileItemFactory嘅object
            // 所以要整一個DiskFileItemFactory object先，同時呢個object可以限制file upload size，超出size嘅file放入臨時文件夾

            // 4.1 創建DiskFileItemFactory object，用作處理file upload path / 大小限制
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024*1024); // 1M 大小限制； 可以唔寫，因為佢都有default size
            factory.setRepository(tmpFile); // 超出則放入tmpFile

            // 4.2 獲取ServletFileUpload object
            ServletFileUpload upload = new ServletFileUpload(factory); //需要傳入一個factory (DiskFileItemFactory object)
            // listener，監聽file upload 進度
            upload.setProgressListener(new ProgressListener() {
                @Override
                public void update(long pBytesRead, long pContentLength, int pItems) {
                    // pBytesRead = 已經upload嘅bytes  pContentLength = 總共幾多bytes
                    System.out.println("Total:" + pContentLength + "\t Received: " + pBytesRead);
                }
            });
            // 處理亂碼問題
            upload.setHeaderEncoding("UTF-8");
            // 設置單個文件嘅maximum size
            upload.setFileSizeMax(1024*1024*10);
            // 設置maximum upload size (所有文件size總和)
            upload.setSizeMax(1024*1024*10);

            // 5. 處理upload嘅file
            // 5.1 將request (一個form入面嘅每一項) 封裝成一個FileItem object，再變為list，需要傳入HttpServletRequest object
            List<FileItem> fileItems = upload.parseRequest(req);
            // form 入面會有好多input，我地只需要file upload嘅input
            for (FileItem fileItem : fileItems) {
                if(fileItem.isFormField()){  // 普通input field
                    // 簡單display就ok
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    System.out.println(name+":"+value);
                }else{  // file upload
                    // 1. 處理文件
                    String uploadFileName = fileItem.getName();
                    // 如果個名not valid，唔再做落去
                    if(uploadFileName.trim().equals("") || uploadFileName==null){
                        continue;
                    }
                    // 獲取文件上傳名
                    String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                    // 獲取文件後綴
                    String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
                    // 呢度可以處理文件，例如判斷文件後綴等等，但係skip左

                    // 為upload嘅file 整一個唯一嘅名 (用uuid 實現)
                    String uuidPath = UUID.randomUUID().toString();

                    // 2. 存放地址
                    // 創建地址
                    String realPath = uploadPath + "/" + uuidPath;
                    // 每個file 整一個directory去放
                    File file = new File(realPath);
                    if(!file.exists()){
                        file.mkdir();
                    }

                    // 3. 文件傳輸
                    // 獲取file input/output stream
                    InputStream inputStream = fileItem.getInputStream();
                    FileOutputStream fos = new FileOutputStream(realPath + "/" + fileName);
                    // read and write
                    byte[] buffer = new byte[1024*1024];
                    int len = 0;
                    while((len = inputStream.read(buffer)) != -1){
                        fos.write(buffer,0,len);
                    }

                    inputStream.close();
                    fos.close();


                }
            }


        } catch (FileUploadException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
