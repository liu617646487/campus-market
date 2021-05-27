package com.ljc.market.controller.home;


import com.ljc.market.bean.CodeMsg;
import com.ljc.market.bean.Result;
import com.ljc.market.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;


/**
 * 系统图片上传公共控制类
 */
@Controller
@RequestMapping("home/upload")
public class HomeUploadController {

    Logger log = LoggerFactory.getLogger(HomeUploadController.class);

    @Value("${ljc.upload.photo.suffix}")
    private String uploadPhotoSuffix;

    @Value("${ljc.upload.photo.maxSize}")
    private Long uploadPhotoMaxSize;

    @Value("${ljc.upload.photo.path}")
    private String uploadPhotoPath;

    /**
     * 图片统一上传方法
     * @param photo
     * @return
     */
    @PostMapping("/upload_photo")
    @ResponseBody
    public Result<String> uploadPhoto(@RequestParam(name = "photo",required = true) MultipartFile photo){

        //获取上传文件名
        String originalFilename = photo.getOriginalFilename();
        log.info("进入图片上传方法，上传图片名："+originalFilename);
        //获取文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        log.info("进入图片上传方法，文件后缀名："+suffix);
        //判断文件类型是不是图片
        if (!uploadPhotoSuffix.contains(suffix.toLowerCase())){
            return Result.error(CodeMsg.UPLOAD_PHOTO_SUFFIX_ERROR);
        }
        //判断图片大小
        log.info("图片大小："+photo.getSize());
        if (photo.getSize()/1024>uploadPhotoMaxSize){
            CodeMsg codeMsg = CodeMsg.UPLOAD_PHOTO_ERROR;
            codeMsg.setMsg("图片大小不能超过"+uploadPhotoMaxSize/1024+"M");
            return Result.error(codeMsg);
        }
        //准备保存文件
        File filePath = new File(uploadPhotoPath);
        if (!filePath.exists()){
            //如果没有这个文件夹则自己创建
            filePath.mkdirs();
        }
        String path = uploadPhotoPath+"/"+ StringUtil.getFormatterDate(new Date(),"yyyyMMdd");
        filePath = new File(path);
        if (!filePath.exists()){
            //判断天的日期文件夹是否存在，不存在则创建
            filePath.mkdir();
        }
        String fileName = StringUtil.getFormatterDate(new Date(),"yyyyMMdd")+"/"+System.currentTimeMillis()+suffix;
        try {
            photo.transferTo(new File(uploadPhotoPath+"/"+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("图片上传成功，保存位置"+uploadPhotoPath+"/"+fileName);
        return Result.success(fileName);
    }

}
