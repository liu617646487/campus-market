package com.ljc.market.util;

import com.ljc.market.annotation.ValidateEntity;
import com.ljc.market.bean.CodeMsg;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.lang.reflect.Field;

/**
 * 验证实体工具类
 */
public class ValidateEntityUtil {

    public static CodeMsg validate(Object object){
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field:declaredFields){
            ValidateEntity annotation = field.getAnnotation(ValidateEntity.class);
            if (annotation!=null){
                //表示该字段是必填字段
                if (annotation.required()){
                    //setAccessible作用就是让我们在用反射时访问私有变量
                    field.setAccessible(true);
                    try {
                        Object o = field.get(object);
                        if(o==null||o==""){
                            CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
                            codeMsg.setMsg(annotation.errorRequiredMsg());
                            return codeMsg;
                        }
                        //说明这个变量不是null
                        //首先判断是不是String类型
                        if(o instanceof String){
                            //若是字符串类型则检查其长度
                            if(annotation.requiredLength()){
                                if(o.toString().length()<annotation.minLength()){
                                    CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
                                    codeMsg.setMsg(annotation.errorMinLengthMsg());
                                    return codeMsg;
                                }
                                if(o.toString().length()>annotation.maxLength()){
                                    CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
                                    codeMsg.setMsg(annotation.errorMaxLengthMsg());
                                    return codeMsg;
                                }
                            }
                        }
                        //其次判断是否为数字
                        if (isNumberObject(o)){
                            //判断是否检查最小值
                            if (annotation.requiredMinValue()){
                                if(Double.valueOf(o.toString())<annotation.minValue()){
                                    CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
                                    codeMsg.setMsg(annotation.errorMinValueMsg());
                                    return codeMsg;
                                }
                            }
                            //判断是否检查最打值
                            if (annotation.requiredMaxValue()){
                                if(Double.valueOf(o.toString())>annotation.maxValue()){
                                    CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
                                    codeMsg.setMsg(annotation.errorMaxValueMsg());
                                    return codeMsg;
                                }
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return CodeMsg.SUCCESS;
    }

    /**
     * 检查对象是否是数字类型
     */
    public static boolean isNumberObject(Object object){
        if (object instanceof Integer) return true;
        if (object instanceof Long) return true;
        if (object instanceof Float) return true;
        if (object instanceof Double) return true;
        return false;
    }
}
