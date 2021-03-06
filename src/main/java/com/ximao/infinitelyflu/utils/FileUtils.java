package com.ximao.infinitelyflu.utils;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;


/**
 * 文件工具类
 * @author ximao
 * @date 2021/7/18
 */
public class FileUtils {

    // 备选：
    private static final String UPLOAD_DIR= "/Users/apple/Documents/JavaProjects/InfinitelyFlu-server/src/main/webapp/upload";
    private static final String DOWNLOAD_DIR = "/Users/apple/Documents/JavaProjects/InfinitelyFlu-server/src/main/webapp/download/";

    // 默认上传文件路径
//    private static final String UPLOAD_DIR= "/Users/victorcolone/JavaProjects/InfinitelyFlu-server/src/main/webapp/upload";
    // 默认下载文件路径,
//    private static final String DOWNLOAD_DIR = "/Users/victorcolone/JavaProjects/InfinitelyFlu-server/src/main/webapp/download";

    /**
     * 上传文件
     * @param multipartFile 上传文件
     * @return 路径，结果：数据库中后缀为if，实际upload路径下文件后缀为xml
     */
    public static String upload(MultipartFile multipartFile) {

        // 返回文件的类型,在此处中并没有用到，只是列出getContentType是返回文件的类型
        String type = multipartFile.getContentType();
        // 拿到文件的原始名称，即例如a.xml
        String originalFilename = multipartFile.getOriginalFilename();
        //判断是否为空
        if (originalFilename==null) {
            return null;
        }
        // 因为上传文件只有一个upload目录，所有的文件都会放在这个文件下，会堆积大量的文件，所以把文件进行分开在upload不同的目录中，利用hash算法就行计算分配目录。例如upload/4/5;或者upload/5/5;这种
        // 拿到文件原名称的hashcode
        int hashCode = originalFilename.hashCode();
        // 拿到upload下第一个目录的名称upload/n
        int dir1 = hashCode & 0xf;
        // 拿到upload下的n目录下的目录名称upload/n/n
        int dir2 = (hashCode & 0xf0) >> 4;
        // 拼接路径，path是传过来的文件保存路径，即upload的真实路径,
        String dir = UPLOAD_DIR + File.separator + dir1 + File.separator + dir2;
        // 把路径丢到File文件中
        File file = new File(dir);
        // 如果d:\1\2这个文件夹不存在，才创建
        if (!file.exists()){
            file.mkdirs();
        }
        // 生成新的UUID.randomUUID().toString()：为了防止文件名重复，尾缀为if
        String randomName = UUID.randomUUID().toString().replace("-","");
        String uploadFileName = randomName + ".xml";
        String downloadFileName = randomName + ".xml";
        InputStream is = null;
        OutputStream os = null;
        try {
            is = multipartFile.getInputStream();
            // 写入文件时候依旧用xml后缀
            os = new FileOutputStream(dir + File.separator + uploadFileName);
            // 对文件进行复制
            FileCopyUtils.copy(is, os);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        xml2Binary(dir, uploadFileName, downloadFileName);
        // 返回文件的路径，以便保存到数据库中，写入数据库时候用if后缀
        return dir1 + File.separator + dir2 + File.separator + downloadFileName;
    }

    /**
     * xml转换为二进制文件
     * @param uploadFileName     .xml文件，已经位于upload文件夹下
     * @param downloadFileName   .if文件，生成后位于download文件夹下
     */
    private static void xml2Binary(String dir, String uploadFileName, String downloadFileName) {
        
    }

    /**
     * 生成特定json
     * @param fileName 文件名
     * @param response 返回流
     */
    public static void generateJson(String fileName, HttpServletResponse response) {
        String path = UPLOAD_DIR + File.separator + fileName;
        JSONObject jsonObject;
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(path));
            SAXReader saxReader = new SAXReader();
            Document document = (Document) saxReader.read(in);
            String xmlString = document.asXML();
            System.out.println("yutao=======" + xmlString);
            jsonObject = NewXML.toJSONObject(xmlString, true);
            System.out.println("yutao=======" + jsonObject.toString());
            out = response.getWriter();
            out.write(jsonObject.toString());
            // 强制将缓冲区中的数据发送出去,不必等到缓冲区满
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 下载文件
     * @param fileName 文件名
     * @param response 返回流
     */
    public static void download(String fileName, HttpServletResponse response) {

        String path = DOWNLOAD_DIR + File.separator + fileName;
        try {
            // 获取输入流
            InputStream bis = new BufferedInputStream(new FileInputStream(path));
            // 转码，免得文件名中文乱码
            fileName = URLEncoder.encode("main.xml","UTF-8");
            // 设置文件下载头
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            int len = 0;
            while((len = bis.read()) != -1){
                out.write(len);
                out.flush();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     * @param fileName 文件名
     */
    public static void delete(String fileName) {
        // yutao todo 删除文件无效
        String downloadPath = DOWNLOAD_DIR + File.separator + fileName;
        String uploadPath = UPLOAD_DIR + File.separator + fileName;
        try {
            File downloadFile = new File(downloadPath);
            downloadFile.delete();
            File uploadFile = new File(uploadPath);
            uploadFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新文件
     * @param fileName 文件名
     */
    public static void update(String fileName, MultipartFile multipartFile) {
        String uploadPath = UPLOAD_DIR + File.separator + fileName;
        File uploadFile = new File(uploadPath);
        // 先清空文件内容
        try {
            if(!uploadFile.exists()) {
                uploadFile.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(uploadFile);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        OutputStream os = null;
        try {
            is = multipartFile.getInputStream();
            // 写入文件时候依旧用xml后缀
            os = new FileOutputStream(uploadPath);
            // 对文件进行复制
            FileCopyUtils.copy(is, os);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
