package com.j2ee.utils;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

public class FileUploadUtil {

    /**
     * 文件存放的目录
     */
    public static String BASE_PATH = null;



    /**
     * 随机文件名 不包含拓展名
     * @return RandomFileName
     */
    private static String getRandomFileName(){
        return String.valueOf(UUID.randomUUID());
    }

    private static String getPath() throws FileNotFoundException {
        if(BASE_PATH == null){
            BASE_PATH = ResourceUtils.getURL("classpath:").getPath() + "static/upload/";

            File fileExist = new File(BASE_PATH);

            if (!fileExist.exists()) {
                fileExist.mkdirs();
            }
        }
        return BASE_PATH;
    }

    public static String save(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String fileName = getRandomFileName() + fileSuffix;

        String basePath = getPath();

        // 获取文件对象
        File f = new File(basePath, fileName);
        // 完成文件的上传
        file.transferTo(f);

        return "/upload/" + fileName;
    }
}
