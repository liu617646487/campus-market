package com.ljc.market.bean;

/**
 * 错误码统一处理类，所有的错误码统一定义在在这里
 */


public class CodeMsg {


    private int code;//错误码

    private String msg;//错误信息

    /**
     * 构造函数私有化，即单例模式
     * @param code
     * @param msg
     */
    private CodeMsg(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    /**
     * 通用错误码定义
     */
    //处理成功消息码
    public static CodeMsg SUCCESS = new CodeMsg(0,"success");
    //非法数据错误码
    public static CodeMsg DATA_ERROR = new CodeMsg(-1,"非法数据!");

    public static CodeMsg CAPTCHA_EMPTY = new CodeMsg(-2,"验证码不能为空!");
    public static CodeMsg VALIDATE_ENTITY_ERROR = new CodeMsg(-3,"");
    public static CodeMsg SESSION_EXPIRED = new CodeMsg(-4,"会话已失效，请刷新后重试！");
    public static CodeMsg CAPTCHA_ERROR = new CodeMsg(-5,"验证码错误!");
    public static CodeMsg USER_SESSION_EXPIRED = new CodeMsg(-6,"用户未登录或会话已失效，请重新登录！");
    public static CodeMsg UPLOAD_PHOTO_SUFFIX_ERROR = new CodeMsg(-7,"图片格式不正确！");
    public static CodeMsg UPLOAD_PHOTO_ERROR = new CodeMsg(-8,"");



    /**
     * 后台管理类错误码
     */
    //用户管理类错误
    public static CodeMsg ADMIN_USERNAME_EMPTY = new CodeMsg(-2000,"用户名不能为空!");
    public static CodeMsg ADMIN_PASSWORD_EMPTY = new CodeMsg(-2001,"密码不能为空!");
    public static CodeMsg ADMIN_NO_RIGHT = new CodeMsg(-2002,"您所属的用户角色没有该权限!");
    public static CodeMsg ADMIN_OLDPWD_ERROR = new CodeMsg(-2003,"旧密码错误!");
    public static CodeMsg ADMIN_CONFIRMPWD_ERROR = new CodeMsg(-2004,"确认密码错误!");

    /**
     * 登录类错误码
     */
    public static CodeMsg ADMIN_USERNAME_NO_EXIST = new CodeMsg(-3000,"用户名不存在!");
    public static CodeMsg ADMIN_PASSWORD_ERROR = new CodeMsg(-3001,"密码错误!");
    public static CodeMsg ADMIN_USER_UNABLE = new CodeMsg(-3002,"无法登陆，用户已被冻结，请联系管理员!");
    public static CodeMsg ADMIN_USER_ROLE_UNABLE = new CodeMsg(-3003,"无法登陆，用户所属角色已被冻结，请联系管理员!");
    public static CodeMsg ADMIN_USER_ROLE_AUTHORITIES_EMPTY = new CodeMsg(-3004,"无法登陆，用户所属角色无可用权限，请联系管理员!");



    /**
     * 后台菜单管理类错误码
     */
    public static CodeMsg ADMIN_MENU_ADD_ERROR = new CodeMsg(-4000,"菜单添加失败，请联系管理员!");
    public static CodeMsg ADMIN_MENU_EDIT_ERROR = new CodeMsg(-4001,"菜单编辑失败，请联系管理员!");
    public static CodeMsg ADMIN_MENU_ID_EMPTY = new CodeMsg(-4002,"菜单ID不能为空!");
    public static CodeMsg ADMIN_MENU_ID_ERROR = new CodeMsg(-4003,"菜单ID错误!");
    public static CodeMsg ADMIN_MENU_DELETE_ERROR = new CodeMsg(-4004,"该菜单下有子菜单，不能删除该菜单，请删除子菜单后删除该菜单!");

    /**
     * 后台角色管理类错误码
     */
    public static CodeMsg ADMIN_ROLE_ADD_ERROR = new CodeMsg(-5000,"角色添加失败，请联系管理员!");
    public static CodeMsg ADMIN_ROLE_EDIT_ERROR = new CodeMsg(-5001,"角色编辑失败，请联系管理员!");
    public static CodeMsg ADMIN_ROLE_ID_EMPTY = new CodeMsg(-5002,"角色ID不能为空!");
    public static CodeMsg ADMIN_ROLE_ID_ERROR = new CodeMsg(-5003,"角色ID错误!");
    public static CodeMsg ADMIN_ROLE_DELETE_ERROR = new CodeMsg(-5004,"该角色下有用户，不能删除该角色，请删除角色下的用户后再删除角色!");

    /**
     * 后台用户管理类错误码
     */
    public static CodeMsg ADMIN_USER_ADD_ERROR = new CodeMsg(-6000,"用户添加失败，请联系管理员!");
    public static CodeMsg ADMIN_USER_ROLE_EMPTY = new CodeMsg(-6001,"用户添加失败，请为用户添加角色!");
    public static CodeMsg ADMIN_USER_USERNAME_EXIST = new CodeMsg(-6002,"用户添加失败，该用户名已存在!");
    public static CodeMsg ADMIN_USER_NO_EMPTY = new CodeMsg(-6003,"用户编辑失败，用户不存在!");
    public static CodeMsg ADMIN_USER_EDIT_ERROR = new CodeMsg(-6004,"用户编辑失败，请联系管理员!");
    public static CodeMsg ADMIN_USER_DELETE_ERROR = new CodeMsg(-6005,"用户删除失败，该用户存在关联数据，不可删除!");
    public static CodeMsg ADMIN_USER_ID_ERROR = new CodeMsg(-6006,"用户ID错误!");


    //数据库备份管理类错误码
    public static CodeMsg ADMIN_DATABASE_BACKUP_NO_EXIST= new CodeMsg(-8000,"数据路备份记录不存在!");


    //后台物品和物品分类管理类错误码
    public static CodeMsg ADMIN_GOODSCATEGORY_ADD_ERROR= new CodeMsg(-9000,"分类添加失败，请联系管理员!");
    public static CodeMsg ADMIN_GOODSCATEGORY_EDIT_ERROR= new CodeMsg(-9001,"分类编辑失败，请联系管理员!");
    public static CodeMsg ADMIN_GOODSCATEGORY_DELETE_ERROR= new CodeMsg(-9002,"该分类下存在子分类或物品信息，请删除子分类或物品信息后删除该分类!");
    public static CodeMsg ADMIN_GOODS_NO_EXIST= new CodeMsg(-9003,"物品不存在!");
    public static CodeMsg ADMIN_GOODS_STATUS_NO_CHANGE= new CodeMsg(-9004,"物品状态未改变!");
    public static CodeMsg ADMIN_GOODS_STATUS_ERROR= new CodeMsg(-9005,"物品状态错误!");
    public static CodeMsg ADMIN_GOODS_EDIT_ERROR= new CodeMsg(-9006,"物品状态编辑失败，请联系管理员!");
    public static CodeMsg ADMIN_GOODS_STATUS_NO_EDIT= new CodeMsg(-9007,"已出售的物品状态不可修改!");
    public static CodeMsg ADMIN_GOODS_RECOMMEND_ERROR= new CodeMsg(-9008,"物品推荐状态错误!");
    public static CodeMsg ADMIN_GOODS_RECOMMEND_NO_CHANGE= new CodeMsg(-9009,"物品状态未改变!");
    public static CodeMsg ADMIN_GOODS_DELETE_ERROR= new CodeMsg(-9010,"请先删除评论信息或举报信息后再，删除商品!");

    //后台学生错误码
    public static CodeMsg ADMIN_STUDENT_NO_EXIST= new CodeMsg(-9101,"学生不存在!");
    public static CodeMsg ADMIN_STUDENT_STATUS_NO_CHANGE= new CodeMsg(-9102,"学生状态未改变!");
    public static CodeMsg ADMIN_STUDENT_EDIT_ERROR= new CodeMsg(-9103,"学生状态编辑失败，请联系管理员!");
    public static CodeMsg ADMIN_STUDENT_DELETE_ERROR= new CodeMsg(-9104,"请先删除学会物品或评论信息或举报信息后再删除商品!");




    //前台用户注册类错误码
    public static CodeMsg HOME_STUDENT_REGISTER_SNO_EXIST= new CodeMsg(-10000,"该学号已存在，请勿重复注册!");
    public static CodeMsg HOME_STUDENT_REGISTER_ERROR= new CodeMsg(-10001,"注册失败，请联系管理员!");
    public static CodeMsg HOME_STUDENT_SNO_NO_EXIST= new CodeMsg(-10002,"学号不存在!");
    public static CodeMsg HOME_STUDENT_PASSWORD_ERROR= new CodeMsg(-10003,"密码错误!");
    public static CodeMsg HOME_STUDENT_UNABLE= new CodeMsg(-10004,"该用户已被冻结，请联系管理员!");

    //前台用户中心类错误码
    public static CodeMsg HOME_STUDENT_EDIT_ERROR= new CodeMsg(-20000,"基本信息修改失败，请联系管理员!");

    //前台物品发布类错误码
    public static CodeMsg HOME_STUDENT_PUBLISH_ERROR= new CodeMsg(-30000,"物品发布失败失败，请联系管理员!");
    public static CodeMsg HOME_STUDENT_GOODS_EDIT_ERROR= new CodeMsg(-30001,"物品编辑失败，请联系管理员!");
    public static CodeMsg HOME_STUDENT_GOODS_NO_EXIST= new CodeMsg(-30002,"物品不存在，请联系管理员!");

    //前端用户举报物品
    public static CodeMsg HOME_STUDENT_REPORT_GOODS_ERROR= new CodeMsg(-4000,"举报失败，请联系管理员!");
    public static CodeMsg HOME_STUDENT_REPORTED_GOODS= new CodeMsg(-4001,"您已举报过此商品，请勿重复举报!");
    public static CodeMsg HOME_STUDENT_REPORTED_NO_EXIST= new CodeMsg(-4002,"举报的商品信息不存在!");
    //前端用户评论
    public static CodeMsg HOME_STUDENT_COMMENT_GOODS_ERROR= new CodeMsg(-5000,"评论失败，请联系管理员!");


}
