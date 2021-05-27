<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, goodsCategory-scalable=no" />
    <title>物品分类管理 - ${siteName!""}</title>
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
                                <form class="pull-right search-bar" method="get" action="#!" role="form">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <input type="hidden" name="search_field" id="search-field" value="title">
                                            <button class="btn btn-default dropdown-toggle" id="search-btn" data-toggle="dropdown" type="button" aria-haspopup="true" aria-expanded="false">
                                                物品分类名 <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li> <a tabindex="-1" href="javascript:void(0)" data-field="title">标题</a> </li>
                                                <li> <a tabindex="-1" href="javascript:void(0)" data-field="cat_name">栏目</a> </li>
                                            </ul>
                                        </div>
                                        <input type="text" class="form-control" value="${name!""}" name="name" placeholder="请输入物品分类名">
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
                                            <th>图标</th>
                                            <th>名称</th>
                                            <th>父类</th>
                                            <th>排序</th>
                                            <th>添加时间</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if pageBean.content?size gt 0>
                                            <#list pageBean.content as goodsCategory>

                                                <tr align="center">
                                                    <td style="vertical-align: middle">
                                                        <label class="lyear-checkbox checkbox-primary">
                                                            <input type="checkbox" name="ids[]" value="${goodsCategory.id}"><span></span>
                                                        </label>
                                                    </td>
                                                    <td style="vertical-align: middle">
                                                        <#if goodsCategory.icon??>
                                                            <#if goodsCategory.icon?length gt 0>
                                                                <img style="width: 60px;height: 60px" src="/photo/view?filename=${goodsCategory.icon!""}"/>
                                                            <#else>
                                                                <img style="width: 60px;height: 60px" src="/admin/images/default-category-icon.png"/>
                                                            </#if>
                                                        </#if>
                                                    </td>
                                                    <td style="vertical-align: middle">${goodsCategory.name!""}</td>
                                                    <td style="vertical-align: middle">
                                                        <#if goodsCategory.parent??>
                                                            ${goodsCategory.parent.name}
                                                        </#if>
                                                    </td>
                                                    <td style="vertical-align: middle">${goodsCategory.sort}</td>
                                                    <td style="vertical-align: middle">${goodsCategory.createTime}</td>
                                                </tr>

                                            </#list>
                                        <#else>
                                            <tr align="center"><td colspan="6">这里啥都没有哦！</td></tr>
                                        </#if>
                                        </tbody>
                                    </table>
                                </div>

                                <#if pageBean.total gt 0 >
                                    <ul class="pagination">
                                        <#if pageBean.currentPage==1>
                                            <li class="disabled"><span>«</span></li>
                                        <#else>
                                            <li><a href="list?name=${name!""}&currentPage=1">«</a></li>
                                        </#if>
                                        <#list pageBean.currentShowPage as showPage>
                                            <#if pageBean.currentPage==showPage>
                                                <li class="active"><span>${showPage}</span></li>
                                            <#else>
                                                <li><a href="list?name=${name!""}&currentPage=${showPage}">${showPage}</a></li>
                                            </#if>
                                        </#list>
                                        <#if pageBean.currentPage==pageBean.totalPage>
                                            <li class="disabled"><span>»</span></li>
                                        <#else>
                                            <li><a href="list?name=${name!""}&currentPage=${pageBean.totalPage}">»</a></li>
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

    $(document).ready(function(e) {

    });

    function edit(url) {
        if ($("input[type='checkbox']:checked").length==0){
            showErrorMsg("请选择一条数据进行编辑！");
            return;
        }
        if ($("input[type='checkbox']:checked").length!=1){
            showErrorMsg("只能选择一条数据进行编辑！");
            return;
        }
        var id = $("input[type='checkbox']:checked").val()
        window.location.href=url+"?id="+id;
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
                    showSuccessMsg("物品分类删除成功!",function () {
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





</script>
</body>
</html>