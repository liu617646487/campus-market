<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>${title!""}-${zzcjxy!""}</title>
    <link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
    <link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
    <link media="all" href="/home/css/product_detail.css" type="text/css" rel="stylesheet">
</head>

<body>

<#include "../common/top-header.ftl" />
<#include "../common/left-menu.ftl" />
<div class="container">
    <div class="main center clearfix">
        <div class="ershou-details">

            <div class="ershou-photos-wr">
                <!-- 收藏功能
               <a class="ershou-favorite" href="javascript:void(0);" style="background-image: url('heart.png');" onclick="favorites();">0</a> -->
                <!-- 轮播大图-->
                <div class="bigger-photo-box">
                    <a class="bigger-photo hide show" rel="img_group" href="#">
                        <img class="bigger" src="/photo/view?filename=${goods.photo}"  alt="${goods.name}">
                    </a>
                </div>
            </div>


            <div class="ershou-info">
                <div class="ershou-hd">
                    <p class="ershou-title">${goods.name}</p>
                    <div class=" discount">
                        <span class="buy-price">原价：${goods.buyPrice}</span>
                        <span class="ershou-price">现价：${goods.sellPrice}</span>
                    </div>
                    <p class="bro-counts">浏览了<span>${goods.viewNumber}</span>次</p>
                </div>
                <ul class="ershou-detail">
                    <li class="ershou-place" id="pid">
                        <div class="name">
                            <span>商品编号</span>
                        </div>
                        <div class="value">
                            <span id="pid">${goods.id}</span>
                        </div>
                    </li>
                    <li class="ershou-time">
                        <div class="name"><span>发布时间</span></div>
                        <div class="value">
                            <span class="real-time" id="creat_time">${goods.createTime}</span>
                        </div>
                    </li>
                    <#--<li class="ershou-tel" id="contact-saler">
                        <div class="name"><span>联系方式</span></div>
                        <div class="value">手机：<span class="user-num"id="user_tel">'+tel+'********</span>
                            &nbsp;&nbsp;QQ:&nbsp;<span class="user-num"id="user_qq">'+qq+'*****</span>
                        </div>
                    </li>-->
                    <li class="ershou-place">
                        <div class="name"><span>交易地点</span></div>
                        <div class="value">${goods.student.school!"该用户未填写学校信息"}</div>
                    </li>
                    <li class="ershou-cert">
                        <div class="name"><span>学院</span></div>
                        <div class="value">
                            <span id="user_college">${goods.student.academy!"该用户未填写学院信息"}</span>
                            <span id="user_grade">${goods.student.grade!"该用户未填写年级信息"}</span>
                        </div>
                    </li>
                    <li class="ershou-seller">
                        <div class="name"><span>卖家</span></div>
                        <div class="value"><span class="value-name" id="userid">${goods.student.nickname!goods.student.sno}</span></div>
                    </li>
                </ul>

                <div id="buy-button" >
                    <a href="javascript:void(0);" style="color:white">联系卖家</a>
                </div>
                <div class="complain">
                    <a href="javascript:void(0);" onclick="report();">•&nbsp;&nbsp;&nbsp;&nbsp;举报&nbsp;&nbsp;&nbsp;&nbsp;•</a>
                </div>
            </div>
        </div>

        <div class="ershou-desc">
            <div class="desc clearfix">
                <a href="" target="_top">
                    <#if goods.student.headPic??>
                        <img id="user_ph" src="/photo/view?filename=${goods.student.headPic}">
                    <#else>
                        <img id="user_ph" src="/home/imgs/avatar1.png" alt="头像">
                    </#if>

                </a>
                <p id="user_cmt">${goods.content}</p>
            </div>
        </div>
    </div>
</div>

<#include "../common/right-menu.ftl" />
<#include "../common/bottom-footer.ftl" />

<script src="/home/js/jquery-3.1.1.min.js"></script>
<script src="/home/js/common.js"></script>
<script src="/home/js/add.js"></script>


<script type="text/javascript">
    $(function () {
        $("#buy-button").click(function(){
            <#if Session["zzcjxy_student"]??>
                alert("请您联系卖家购买商品。"+"\n"+"电话:"+'${goods.student.mobile!""}'+"\n"+"QQ:"+'${goods.student.qq!""}');
            <#else>
                alert("请您先登录");
                window.location.href="../index/login";
            </#if>

        });
    });

</script>



</body>
</html>