<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>${title!""}-${zzcjxy!""}</title>
    <link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
    <link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
    <link media="all" href="/home/css/user_center.css" type="text/css" rel="stylesheet">
</head>

<body>

    <#include "../common/top-header.ftl" />
    <#include "../common/left-menu.ftl" />
    <div class="container">
        <div class="main center">
            <!-- 头像设置开始 -->
            <div class="head-img-box hide" id="head-img-box">
                <div class="wrap-head-img">
                    <h3 class="head-title">
                        <span>头像设置</span>
                        <a id="close-head-img" class="close-head-img" href="javascript:;">X</a>
                    </h3>
                    <div class="head-img-area">
                        <div class="wrap-img" id="wrap-img" style="position: relative;">
                            <a href="javascript:;" id="upload-person-img" class="img-attr btn-upload-img " style="position: relative; z-index: 1;"><i>+</i>选择图片</a>
                            <p class="img-attr img-limit ">只支持JPG、PNG、GIF，大小不超过1M</p>
                            <img class="loading hide" src="/home/imgs/loading.gif" alt="" width="28" height="28">
                            <div id="" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;">
                                <input id="select-file" onchange="uploadImg()" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" accept="image/*" capture="camera" type="file">
                            </div>
                        </div>
                    </div>
                    <div class="head-img-footer">
                        <a href="javascript:;" class="btn-ok" id="upload-ok">确定</a>
                        <a href="javascript:;" id="cancel-img-box" class="btn-cancel">取消</a>
                    </div>
                </div>
            </div>
            <!-- 头像设置结束 -->
            <div class="top clearfix" id ="user-top">
                <div id="user_msg">
                    <div class="name" id="user_big_name">
                    </div>
                    <ul class="seller_attr">
                        <li>已发布商品：<span id="procount">${goodsList?size}</span></li>
                        <li>待售出商品：<span id = "upGoodsTotal">0</span></li>
                        <li>已售出商品：<span id = "soldGoodsTotal">0</span></li>
                        <li>已下架商品：<span id = "downGoodsTotal">0</span></li>
                        <li>待求购商品：<span id = "userpoint">${wantedGoodsList?size}</span></li>
                        <li>已举报商品：<span id = "userpoint">${reportGoodsList?size}</span></li>
                    </ul>
                </div>
                <div id="user_photo">
                    <#if Session["zzcjxy_student"].headPic??>
                        <img id="origin_ph" onclick="openUploadPanel()" src="/photo/view?filename=${Session["zzcjxy_student"].headPic}" old-src="/photo/view?filename=${Session["zzcjxy_student"].headPic}" alt="大头像">
                    <#else>
                        <img id="origin_ph" onclick="openUploadPanel()" src="/home/imgs/avatar1.png" old-src="/home/imgs/avatar1.png" alt="大头像">
                    </#if>
                    <img id="change_photo" src="/home/imgs/person_hover.png" alt="更换头像" style="display: none;">
                </div>
            </div>
            <ul id="middle_nav" class="clearfix">
                <li class="on"><a href="#">个人资料</a></li>
            </ul>
            <div class="pop-tip hide">
                <div class="pop-tip-area">
                    <p class="pop-tip-txt">修改成功</p>
                </div>
            </div>
            <div id="my_info">
                <div id="account_info">
                    <h2>账户信息</h2>
                    <ul class="infos" id="userid">
                        <li>账号ID</li>
                        <li class="right_info">${Session["zzcjxy_student"].id}</li>
                    </ul>
                    <ul class="infos" id="userstate">
                        <li>认证状态</li>
                        <li class="right_info"><#if Session["zzcjxy_student"].status==1>正常<#else>冻结</#if></li>
                    </ul>
                    <ul class="infos">
                        <li>学号</li>
                        <li class="right_info">${Session["zzcjxy_student"].sno}</li>
                    </ul>
                </div>
                <div id="base_info">
                    <h2>基本信息 <span id="edit_info">编辑</span><span id="save_info">保存</span></h2>
                    <ul class="infos">
                        <li>昵称</li>
                        <li class="right_info">
                            <span id="nickname_span">${Session["zzcjxy_student"].nickname!""}</span>
                            <input value="${Session["zzcjxy_student"].nickname!""}" id="nickname" type="text">
                        </li>
                    </ul>
                    <ul class="infos">
                        <li>手机</li>
                        <li class="right_info">
                            <span id="mobile_span">${Session["zzcjxy_student"].mobile!""}</span>
                            <input value="${Session["zzcjxy_student"].mobile!""}" id="mobile" type="text">
                        </li>
                    </ul>
                    <ul class="infos">
                        <li>QQ</li>
                        <li class="right_info">
                            <span id="qq_span">${Session["zzcjxy_student"].qq!""}</span>
                            <input value="${Session["zzcjxy_student"].qq!""}" id="qq" type="text">
                        </li>
                    </ul>
                    <ul class="infos">
                        <li>学校</li>
                        <li class="right_info">
                            <span id="school_span">${Session["zzcjxy_student"].school!""}</span>
                            <input value="${Session["zzcjxy_student"].school!""}" id="school" type="text">
                        </li>
                    </ul>
                    <ul class="infos">
                        <li>学院</li>
                        <li class="right_info">
                            <span id="academy_span">${Session["zzcjxy_student"].academy!""}</span>
                            <input value="${Session["zzcjxy_student"].academy!""}" id="academy" type="text">
                        </li>
                    </ul>
                    <ul class="infos">
                        <li>年级</li>
                        <li class="right_info">
                            <span id="grade_span">${Session["zzcjxy_student"].grade!""}</span>
                            <input value="${Session["zzcjxy_student"].grade!""}" id="grade" type="text">
                        </li>
                    </ul>
                </div>
            </div>

            <ul id="middle_nav" class="clearfix">
                <li class="on"><a href="javascript:void(0)">我发布的商品</a></li>
            </ul>
            <div id="my_products">
                <div id="onsale_pro">
                    <#if goodsList??>
                        <#list goodsList as goods>
                            <div class="enshr_each" id="">
                                <div class="enshr_info">
                                    <h2><a href="../goods/detail?id=${goods.id}" title="${goods.name}">${goods.name}</a></h2>
                                    <p style="overflow:hidden;">${goods.content}</p>
                                    <div class="enshr_state">
                                        <span id="prostate">状态：<#if goods.status==1><font style="color:rgb(75, 192, 165);">上架</font><#elseif goods.status==2><font color="red">下架</font><#elseif goods.status==3><font color="#a9a9a9">已售出</font></#if></span>
                                        &nbsp;<span id="prostate">是否推荐：<#if goods.recommend==1><font style="color:rgb(75, 192, 165);">已推荐</font><#elseif goods.recommend==0><font color="red">未推荐</font></#if></span>&nbsp;
                                        &nbsp;<span id="prostate">上架日期：${goods.createTime}</span>
                                        <#if goods.status==1>
                                            <span class="enshrine_it" onclick="sellout(${goods.id});">确认售出</span>
                                        <#elseif goods.status==3>
                                            <span class="enshrine_it">已售出</span>
                                        </#if>
                                        <#if goods.status==1>
                                            <span class="enshrine_it make_edition" onclick="offshelf(${goods.id});">下架</span>
                                        <#elseif goods.status==2>
                                            <span class="enshrine_it make_edition" onclick="onshelf(${goods.id});" style="margin-right: 30px">上架</span>
                                        </#if>
                                        <#if goods.flag==0>
                                            <span class="enshrine_it make_edition" onclick="refresh(${goods.id},1);">擦亮</span>
                                        <#else>
                                            <span class="enshrine_it make_edition" onclick="refresh(${goods.id},0);">取消擦亮</span>
                                        </#if>

                                        <a href="edit_goods?id=${goods.id}" target="_top">
                                            <span class="enshrine_it  make_edition">编辑</span>
                                        </a>
                                    </div>
                                </div>
                                <a href="../goods/detail?id=${goods.id}">
                                    <img class="enshr_ph" src="/photo/view?filename=${goods.photo}" alt="${goods.name}">
                                </a>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>

            <ul id="middle_nav" class="clearfix">
                <li class="on"><a href="javascript:void(0)">我求购的商品</a></li>
            </ul>
            <div id="my_products">
                <div id="onsale_pro">
                    <#if wantedGoodsList??>
                        <#list wantedGoodsList as wantedGoods>
                            <div class="enshr_each" id="">
                                <div class="enshr_info">
                                    <h2><a href="" title="${wantedGoods.name}">${wantedGoods.name}</a></h2>
                                    <p style="overflow:hidden;">${wantedGoods.content}</p>
                                    <div class="enshr_state">
                                        &nbsp;<span id="prostate">发布日期：${wantedGoods.createTime}</span>
                                        <a href="edit_wanted_goods?id=${wantedGoods.id}" target="_top">
                                            <span class="enshrine_it make_edition" style="margin-right: 30px">编辑</span>
                                        </a>
                                        <a href="javascript:void(0);" onclick="deleteWantedGoods(${wantedGoods.id})" target="_top">
                                            <span class="enshrine_it make_edition" style="margin-right: 30px">删除</span>
                                        </a>
                                    </div>
                                </div>
                                <a href="">
                                    <#if wantedGoods.student.headPic??>
                                        <img class="enshr_ph" src="/photo/view?filename=${wantedGoods.student.headPic}" alt="${wantedGoods.student.nickname}">
                                    <#else>
                                        <img class="enshr_ph" src="/home/imgs/avatar1.png" alt="${wantedGoods.student.nickname}">
                                    </#if>
                                </a>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>

            <ul id="middle_nav" class="clearfix">
                <li class="on"><a href="javascript:void(0)">我举报的商品</a></li>
            </ul>
            <div id="my_products">
                <div id="onsale_pro">
                    <#if reportGoodsList??>
                        <#list reportGoodsList as reportGoods>
                            <div class="enshr_each" id="">
                                <div class="enshr_info">
                                    <h2><a href="../goods/detail?id=${reportGoods.goods.id}" title="${reportGoods.goods.name}">${reportGoods.goods.name}</a></h2>
                                    <p style="overflow:hidden;">${reportGoods.goods.content}</p>
                                    <div class="enshr_state">
                                        &nbsp;<span id="prostate">举报日期：${reportGoods.createTime}</span>&nbsp;
                                        &nbsp;<span id="prostate">举报原因：${reportGoods.content}</span>
                                        <a href="javascript:void(0);" onclick="deleteReportGoods(${reportGoods.id})" target="_top">
                                            <span class="enshrine_it make_edition" style="margin-right: 30px">取消举报</span>
                                        </a>
                                    </div>
                                </div>
                                <a href="../goods/detail?id=${reportGoods.goods.id}">
                                    <img class="enshr_ph" src="/photo/view?filename=${reportGoods.goods.photo}" alt="${reportGoods.goods.name}">
                                </a>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>

        </div>
    </div>

<#include "../common/right-menu.ftl" />
<#include "../common/bottom-footer.ftl" />


<script src="/home/js/jquery-3.1.1.min.js"></script>
<script src="/home/js/common.js"></script>
<script src="/home/js/user_center.js"></script>
<script src="/home/js/add.js"></script>

<script type="text/javascript">

    $(function () {
        ajaxRequest('get_stats','post',{},function (rst) {
            $("#soldGoodsTotal").text(rst.data.soldGoodsTotal);
            $("#downGoodsTotal").text(rst.data.downGoodsTotal);
            $("#upGoodsTotal").text(rst.data.upGoodsTotal);
        });
    });

    function deleteWantedGoods(id){
        if (!confirm('删除后不可恢复，确认删除？')){
            return;
        }
        ajaxRequest('delete_wanted_goods','post',{id:id},function () {
            alert("删除成功！")
            window.location.reload();
        });
    }

    function deleteReportGoods(id){
        if (!confirm('撤销后后不可恢复，确认撤销举报？')){
            return;
        }
        ajaxRequest('delete_report_goods','post',{id:id},function () {
            alert("举报撤销成功！")
            window.location.reload();
        });
    }

</script>


</body>
</html>