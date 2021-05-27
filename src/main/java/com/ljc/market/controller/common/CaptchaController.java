package com.ljc.market.controller.common;


import com.ljc.market.util.CaptchaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 系统验证码公共控制类
 */
@Controller
@RequestMapping("captcha")
public class CaptchaController {


    private Logger log = LoggerFactory.getLogger(CaptchaController.class);


    /**
     * 公用验证码生成器
     * @param vcodeLength
     * @param fontSize
     * @param width
     * @param height
     * @param request
     * @param response
     */
    @GetMapping("/generate_captcha")
    public void generateCaptcha(
            @RequestParam(name = "vl",defaultValue = "4") Integer vcodeLength,//验证码长度
            @RequestParam(name = "fs",defaultValue = "21")Integer fontSize,//验证码字体大小
            @RequestParam(name = "w",defaultValue = "98")Integer width,//图片宽度
            @RequestParam(name = "h",defaultValue = "33")Integer height,//图片高度
            @RequestParam(name = "method",defaultValue = "admin_login")String method,
            HttpServletRequest request,
            HttpServletResponse response){
        CaptchaUtil captchaUtil = new CaptchaUtil(vcodeLength,fontSize,width,height);
        String generatorVCode = captchaUtil.generatorVCode();
        //将生成的验证码放入session，以供后面的程序使用
        request.getSession().setAttribute(method,generatorVCode);
        log.info("验证码生成成功,method="+method+",value="+generatorVCode);
        try {
            //生成旋转的图片
            ImageIO.write(captchaUtil.generatorRotateVCodeImage(generatorVCode,true),"gif",response.getOutputStream());
            //ImageIO.write(captchaUtil.generatorVCodeImage(generatorVCode,false),"gif",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
