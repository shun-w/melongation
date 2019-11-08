package com.assignment.fileserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UploadService {

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif");
    private static Logger logger = LoggerFactory.getLogger(UploadService.class);


    public String uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        try {


            //校验文件类型
            String contentType = file.getContentType();

            if (!CONTENT_TYPES.contains(contentType)) {

                logger.info("文件类型不合法:{}", originalFilename);
                return null;
            }

            //校验文件内容

            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                logger.info("文件内容不合法:{}", originalFilename);
                return null;

            }

            String fileName = (new Date()).getTime() + originalFilename;

            //保存文件到服务器
            file.transferTo(new File("D:\\melongation\\image\\" + fileName));

            //返回url,用nginx反向代理
            return "http://image.melongation.com/" + fileName;

        } catch (IOException e) {
            logger.info("服务器内部错误，上传文件：" + originalFilename);
            e.printStackTrace();
        }
        return null;


    }
}
