<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, goodsCategory-scalable=no" />
    <title>物品管理 - ${title!""}</title>
    <#include "../common/header.ftl"/>
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">

        <!--左侧导航-->
        <aside class="lyear-layout-sidebar">
            <!-- logo -->
            <div id="logo" class="sidebar-header">
                <a href="index.html"><img src="/admin/images/logo-sidebar.png" title="${siteName!""}" alt="${siteName!""}" /></a>
            </div>
            <div class="lyear-layout-sidebar-scroll">
                <#--左侧导航栏-->
                <#include "../common/left-menu.ftl" >
            </div>
        </aside>
        <!--End 左侧导航-->

        <#--头部信息-->
        <#include "../common/header-menu.ftl" >

        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-toolbar clearfix">
                                <form class="pull-right search-bar" method="get" action="list" role="form">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <#--<input type="hidden" name="search_field" id="search-field" value="title">-->
                                            <button class="btn btn-default dropdown-toggle" id="search-btn" data-toggle="dropdown" type="button" aria-haspopup="true" aria-expanded="false">
                                                <#if goodsCategorysName??>分类名称<#elseif sno??>学生学号<#elseif name??>物品名称<#else>搜索条件</#if> <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li> <a tabindex="-1" href="javascript:void(0)" data-field="name">物品名称</a> </li>
                                                <li> <a tabindex="-1" href="javascript:void(0)" data-field="student.sno">学生学号</a> </li>
                                                <li> <a tabindex="-1" href="javascript:void(0)" data-field="goodsCategory.name">分类名称</a> </li>
                                            </ul>
                                        </div>
                                        <input type="text" class="form-control" id="search-value" value="${name!sno!goodsCategorysName!""}" name="<#if goodsCategorysName??>goodsCategory.name<#elseif sno??>student.sno<#elseif name??>name</#if>" placeholder="请输入">
                                        <span class="input-group-btn">
                                            <button class="btn btn-primary" type="submit">搜索</button>
                                        </span>
                                    </div>
                                </form>
                                <#include "../common/third-menu.ftl" >
                            </div>
                            <div class="card-body">

                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th>
                                                <label class="lyear-checkbox checkbox-primary">
                                                    <input type="checkbox" id="check-all"><span></span>
                                                </label>
                                            </th>
                                            <th>图片</th>
                                            <th>名称</th>
                                            <th>学生学号</th>
                                            <th>分类</th>
                                            <th>售价</th>
                                            <th>浏览量</th>
                                            <th>状态</th>
                                            <th>是否擦亮</th>
                                            <th>是否推荐</th>
                                            <th>添加时间</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if pageBean.content?size gt 0>
                                            <#list pageBean.content as goods>

                                                <tr align="center">
                                                    <td style="vertical-align: middle">
                                                        <label class="lyear-checkbox checkbox-primary">
                                                            <input type="checkbox" name="ids[]" value="${goods.id}"><span></span>
                                                        </label>
                                                    </td>
                                                    <td style="vertical-align: middle">
                                                        <a href="/home/goods/detail?id=${goods.id}" target="_blank">
                                                            <img style="width: 60px;height: 60px" src="/photo/view?filename=${goods.photo!""}"/>
                                                        </a>
                                                    </td>
                                                    <td style="vertical-align: middle">
                                                        <a href="/home/goods/detail?id=${goods.id}" target="_blank">${goods.name}</a>
                                                    </td>
                                                    <td style="vertical-align: middle">${goods.student.sno}</td>
                                                    <td style="vertical-align: middle">${goods.goodsCategory.name}</td>
                                                    <td style="vertical-align: middle">${goods.sellPrice}</td>
                                                    <td style="vertical-align: middle">${goods.viewNumber}</td>
                                                    <td style="vertical-align: middle">
                                                    <#if goods.status=1>
                                                        <font color="green">上架</font>
                                                    <#elseif goods.status=2>
                                                        <font color="aqua">下架</font>
                                                    <#else>
                                                        <font color="red">已售出</font>
                                                    </#if>
                                                    </td>
                                                    <td style="vertical-align: middle">
                                                    <#if goods.flag=1>
                                                        <font color="green">已擦亮</font>
                                                    <#else>
                                                        <font color="red">未擦亮</font>
                                                    </#if>
                                                    </td>
                                                    <td style="vertical-align: middle">
                                                        <#if goods.recommend=1>
                                                            <font color="green">已推荐</font>
                                                        <#else>
                                                            <font color="red">未推荐</font>
                                                        </#if>
                                                    </td>
                                                    <td style="vertical-align: middle">${goods.createTime}</td>
                                                </tr>

                                            </#list>
                                        <#else>
                                            <tr align="center"><td colspan="10">这里啥都没有哦！</td></tr>
                                        </#if>
                                        </tbody>
                                    </table>
                                </div>

                                <#if pageBean.total gt 0 >
                                    <ul class="pagination">
                                        <#if pageBean.currentPage==1>
                                            <li class="disabled"><span>«</span></li>
                                        <#else>
                                            <li><a href="list?<#if goodsCategorysName??>goodsCategory.name=${goodsCategorysName}<#elseif sno??>student.sno=${sno}<#elseif name??>name=${name}</#if>&currentPage=1">«</a></li>
                                        </#if>
                                        <#list pageBean.currentShowPage as showPage>
                                            <#if pageBean.currentPage==showPage>
                                                <li class="active"><span>${showPage}</span></li>
                                            <#else>
                                                <li><a href="list?<#if goodsCategorysName??>goodsCategory.name=${goodsCategorysName}<#elseif sno??>student.sno=${sno}<#elseif name??>name=${name}</#if>&currentPage=${showPage}">${showPage}</a></li>
                                            </#if>
                                        </#list>
                                        <#if pageBean.currentPage==pageBean.totalPage>
                                            <li class="disabled"><span>»</span></li>
                                        <#else>
                                            <li><a href="list?<#if goodsCategorysName??>goodsCategory.name=${goodsCategorysName}<#elseif sno??>student.sno=${sno}<#elseif name??>name=${name}</#if>&currentPage=${pageBean.totalPage}">»</a></li>
                                        </#if>
                                        <li><span>共${pageBean.totalPage}页，${pageBean.total}条数据</span></li>
                                    </ul>
                                </#if>

                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </main>
        <!--End 页面主要内容-->
    </div>
</div>
<#include "../common/footer.ftl"/>
<script type="text/javascript">

    $(document).ready(function() {
        $(".dropdown-menu li a").click(function () {
            $("#search-btn").html($(this).text()+' <span class="caret"></span>');
            $("#search-value").attr('name',$(this).attr('data-field'));
        });
    });

    function up(url) {
        if ($("input[type='checkbox']:checked").length==0){
            showErrorMsg("请选择一条数据进行编辑！");
            return;
        }
        if ($("input[type='checkbox']:checked").length!=1){
            showErrorMsg("只能选择一条数据进行编辑！");
            return;
        }
        var id = $("input[type='checkbox']:checked").val()
        ajaxRequest(url, 'post',{id:id, status:1},function () {
            showSuccessMsg("修改成功",function () {
                window.location.reload()
            });
        })
    }

    function down(url) {
        if ($("input[type='checkbox']:checked").length==0){
            showErrorMsg("请选择一条数据进行编辑！");
            return;
        }
        if ($("input[type='checkbox']:checked").length!=1){
            showErrorMsg("只能选择一条数据进行编辑！");
            return;
        }
        var id = $("input[type='checkbox']:checked").val()
        ajaxRequest(url, 'post',{id:id, status:2},function () {
            showSuccessMsg("修改成功",function () {
                window.location.reload()
            });
        })
    }

    function del(url) {
        if ($("input[type='checkbox']:checked").length==0){
            showErrorMsg("请选择一条数据进行删除！");
            return;
        }
        if ($("input[type='checkbox']:checked").length!=1){
            showErrorMsg("只能选择一条数据进行删除！");
            return;
        }
        $.confirm({
            title: '确认删除？',
            content: '删除后数据不可恢复，请慎重！',
            buttons: {
                confirm: {
                    text: '确认',
                    action: function(){
                        var id = $("input[type='checkbox']:checked").val()
                        deleteReq(id,url);
                    }
                },
                cancel: {
                    text: '关闭',
                    action: function(){

                    }
                },

            }
        });
    }

    function deleteReq(id,url) {
        $.ajax({
            url: url,
            type: 'post',
            data: {id:id},
            dataType: 'json',
            async: true,
            success:function (data) {
                if(data.code==0){
                    showSuccessMsg("物品删除成功!",function () {
                        $("input[type='checkbox']:checked").parents("tr").remove();
                    })
                }else{
                    showErrorMsg(data.msg);
                }
            },
            error:function (data) {
                alert("网络错误！")
            }

        });
    }

    function recommend(url) {
        if ($("input[type='checkbox']:checked").length==0){
            showErrorMsg("请选择一条数据进行编辑！");
            return;
        }
        if ($("input[type='checkbox']:checked").length!=1){
            showErrorMsg("只能选择一条数据进行编辑！");
            return;
        }
        var id = $("input[type='checkbox']:checked").val()
        ajaxRequest(url, 'post',{id:id, recommend:1},function () {
            showSuccessMsg("修改成功",function () {
                window.location.reload()
            });
        })
    }

    function unrecommend(url) {
        if ($("input[type='checkbox']:checked").length==0){
            showErrorMsg("请选择一条数据进行编辑！");
            return;
        }
        if ($("input[type='checkbox']:checked").length!=1){
            showErrorMsg("只能选择一条数据进行编辑！");
            return;
        }
        var id = $("input[type='checkbox']:checked").val()
        ajaxRequest(url, 'post',{id:id, recommend:0},function () {
            showSuccessMsg("修改成功",function () {
                window.location.reload()
            });
        })
    }

</script>
</body>
</html>