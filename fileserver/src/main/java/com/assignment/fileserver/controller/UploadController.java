package com.assignment.fileserver.controller;


import com.assignment.fileserver.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;


    /**
     * 当用户选择图片后，就上传图片到服务器，服务器返回url，表单里面只用提交url即可
     *
     * @param file
     * @return 图片的url, 可以直接访问
     */
    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file) {

        String url = uploadService.uploadImage(file);
        if (StringUtils.isEmpty(url)) {
            //400
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(url);
    }

}
