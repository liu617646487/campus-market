<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>数据库备份管理 - ${siteName!""}</title>
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
                                            <th>备份文件</th>
                                            <th>存放路径</th>
                                            <th>备份时间</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if pageBean.content?size gt 0>
                                            <#list pageBean.content as databaesbak>

                                                <tr align="center">
                                                    <td style="vertical-align: middle">
                                                        <label class="lyear-checkbox checkbox-primary">
                                                            <input type="checkbox" name="ids[]" value="${databaesbak.id}"><span></span>
                                                        </label>
                                                    </td>

                                                    <td style="vertical-align: middle">${databaesbak.filename}</td>
                                                    <td style="vertical-align: middle">${databaesbak.filepath}</td>
                                                    <td style="vertical-align: middle">${databaesbak.createTime}</td>

                                                </tr>

                                            </#list>
                                        <#else>
                                            <tr align="center"><td colspan="9">这里啥都没有哦！</td></tr>
                                        </#if>
                                        </tbody>
                                    </table>
                                </div>

                                <#if pageBean.total gt 0 >
                                    <ul class="pagination">
                                        <#if pageBean.currentPage==1>
                                            <li class="disabled"><span>«</span></li>
                                        <#else>
                                            <li><a href="list?currentPage=1">«</a></li>
                                        </#if>
                                        <#list pageBean.currentShowPage as showPage>
                                            <#if pageBean.currentPage==showPage>
                                                <li class="active"><span>${showPage}</span></li>
                                            <#else>
                                                <li><a href="list?currentPage=${showPage}">${showPage}</a></li>
                                            </#if>
                                        </#list>
                                        <#if pageBean.currentPage==pageBean.totalPage>
                                            <li class="disabled"><span>»</span></li>
                                        <#else>
                                            <li><a href="list?currentPage=${pageBean.totalPage}">»</a></li>
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


    function add(url) {
        ajaxRequest(url,'post',{},function (rst) {
            showSuccessMsg("数据库备份成功！",function () {
                window.location.reload();
            });
        });
    }

    function del(url) {
        if ($("input[type='checkbox']:checked").length==0){
            showErrorMsg("请选择一条数据进行删除！");
            return;
        }
        var ids = '';
        $("input[type='checkbox']:checked").each(function (i,e) {
            if ($(e).attr('id')!='check-all'){
                ids += $(e).val()+','
            }
        });
        if (ids!=''){
            ids = ids.substring(0,ids.length-1);
        }
        $.confirm({
            title: '确认删除？',
            content: '删除后数据不可恢复，请慎重！',
            buttons: {
                confirm: {
                    text: '确认',
                    action: function(){
                        ajaxRequest(url,'post',{ids:ids},function (rst) {
                            showSuccessMsg("数据库备份删除成功！",function () {
                                window.location.reload();
                            });
                        });
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

    //还原操作
    function restore(url) {
        if ($("input[type='checkbox']:checked").length==0){
            showErrorMsg("请选择一条数据进行还原！");
            return;
        }
        if ($("input[type='checkbox']:checked").length!=1){
            showErrorMsg("只能选择一条数据进行还原！");
            return;
        }
        var id = $("input[type='checkbox']:checked").val();
        ajaxRequest(url,'post',{id:id},function (rst) {
            showSuccessMsg("数据库还原成功！",function () {
                window.location.reload();
            });
        });
    }



</script>
</body>
</html>