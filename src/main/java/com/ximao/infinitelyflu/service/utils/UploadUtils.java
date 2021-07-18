package com.ximao.infinitelyflu.service.utils;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * 模板上传工具类
 * @author ximao
 * @date 2021/7/18
 */
public class UploadUtils {

    // 默认上传文件路径
    private static final String UPLOAD_DIR= "/Users/apple/Documents/JavaProjects/InfinitelyFlu-server/src/main/webapp/upload";

    private static final String TAG= "UploadUtils";

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
        String dir = UPLOAD_DIR + File.separator+dir1 + File.separator + dir2;
        // 把路径丢到File文件中
        File file = new File(dir);
        // 如果d:\1\2这个文件夹不存在，才创建
        if (!file.exists()){
            file.mkdirs();
        }
        // 生成新的UUID.randomUUID().toString()：为了防止文件名重复
        String newFileName = UUID.randomUUID().toString().replace("-","")+"."+originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        InputStream is = null;
        OutputStream os = null;
        try {
            is = multipartFile.getInputStream();
            os = new FileOutputStream(dir+File.separator+newFileName);
            //对文件进行复制
            FileCopyUtils.copy(is,os);
        } catch (IOException e) {
            System.out.println(TAG + ": " + e);
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
        // 返回文件的路径，以便保存到数据库中
        return dir1+File.separator+dir2+File.separator+newFileName;
    }
}