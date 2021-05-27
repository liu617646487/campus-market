package com.ljc.market.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 图片统一查看控制器
 */
@Controller
@RequestMapping("photo")
public class PhotoController {

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${ljc.upload.photo.path}")
    private String uploadPhotoPath;

    @RequestMapping("/view")
    public ResponseEntity<?> viewPhoto(@RequestParam(name = "filename",required = true) String filename){
        String filePath = uploadPhotoPath+"/"+filename;//文件完整的绝对路径
        Resource resource = resourceLoader.getResource("file:"+filePath);
        try {
            return ResponseEntity.ok(resource);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

}
