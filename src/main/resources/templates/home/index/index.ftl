<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>${title!""}-${zzcjxy!""}</title>
    <link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
    <link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">

</head>

<body>

<#include "../common/top-header.ftl" />
<#include "../common/left-menu.ftl" />

<div class="container">
    <div class="main center">
        <#--<div class="wrap-site mt20">
            <div class="recom-title"></div>
            <ul class="recom-list group">
                <li><a href="http://www.2shoujie.com/publishRule" target="_top">商品发布规则</a></li>
                <li><a href="http://www.2shoujie.com/article/4.html" target="_top">购买二手iPhone注意事项</a></li>
            </ul>
        </div>-->

        <div class="label-wr center clearfix">
            <div id="nav-labels">
                <button id="new_pro" class="labels" onclick="" ></button>
            </div>

        </div>

        <div class="item-list">
            <ul class="items clearfix">

                <#if pageBean.content??>
                    <#list pageBean.content as goods>
                        <li class="item">
                            <a href="../goods/detail?id=${goods.id}" class="img" target="_top"><img src="/photo/view?filename=${goods.photo}" alt="${goods.name}"></a>
                            <div class="info">
                                <div class="price">${goods.sellPrice}</div>
                                <div class="name">
                                    <a href="../goods/detail?id=${goods.id}" target="_top">${goods.name}</a>
                                </div>
                                <div class="department"><span>${goods.content!""}</span></div>
                                <div class="place"><span>${goods.student.school!""}</span></div>
                                <#if goods.recommend==1>
                                    <div class="school"><span>推荐</span></div>
                                </#if>
                            </div>
                        </li>
                    </#list>
                </#if>

            </ul>
        </div>
        <#if pageBean.total gt 0>
            <!-- 分页 开始 -->
            <div class="pages">
                <#if pageBean.currentPage == 1>
                    <a class="page-arrow arrow-left" href="javascript:void(0)">首页</a>
                <#else>
                    <a class="page-arrow arrow-left" href="/home/index/index?name=${name!""}&currentPage=1">首页</a>
                </#if>
                <#list pageBean.currentShowPage as showPage>
                    <#if pageBean.currentPage==showPage>
                        <a class="page-num cur" href="javascript:void(0)">${showPage}</a>
                    <#else>
                        <a class="page-num" href="/home/index/index?name=${name!""}&currentPage=${showPage}">${showPage}</a>
                    </#if>
                </#list>
                <#if pageBean.currentPage==pageBean.totalPage>
                    <a class="page-arrow arrow-right disabled" href="javascript:void(0)">尾页</a>
                <#else>
                    <a class="page-arrow arrow-right" href="/home/index/index?name=${name!""}&currentPage=${pageBean.totalPage}">尾页</a>
                </#if>
                <li><span>共${pageBean.totalPage}页，${pageBean.total}条数据</span></li>
            </div>
            <!-- 分页 结束 -->
        </#if>
    </div>
</div>
<div class="return-to-top"><a href="#"></a></div><!--返回顶部-->

<#include "../common/right-menu.ftl" />
<#include "../common/bottom-footer.ftl" />


<script src="/home/js/jquery-3.1.1.min.js"></script>
<script src="/home/js/common.js"></script>
<script src="/home/js/index.js"></script>


</body>
</html>