<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>物品分类管理|${title!""} - ${siteName!""}</title>
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
                            <div class="card-header"><h4>添加物品分类</h4></div>
                            <div class="card-body">
                                <form id="goodscategory-add-form" action="add" method="post" class="row">

                                    <div class="input-group m-b-10">
                                        <span class="input-group-addon">ICON上传</span>
                                        <input type="file" id="select-file" style="display: none" onchange="upload('','icon')">
                                        <input type="text" class="form-control required" id="icon" name="icon" value="" placeholder="请选择菜单icon" tips="请选择菜单icon" readonly/>
                                        <span class="input-group-btn">
                                            <button class="btn btn-primary" id="add-pic-btn" type="button">点击上传</button>
                                        </span>
                                    </div>

                                    <div class="input-group m-b-10">
                                        <span class="input-group-addon">分类名称</span>
                                        <input type="text" class="form-control required" id="name" name="name" value="" placeholder="请输入物品分类名" tips="请填写物品分类名！"/>
                                    </div>

                                    <div class="input-group m-b-10">
                                        <span class="input-group-addon" id="basic-addon1">所属分类</span>
                                        <select name="parent" class="form-control" id="parent">
                                            <#if goodsCategories??>
                                                <option value="">请选择上级分类</option>
                                                <#list goodsCategories as goodsCategory>
                                                    <option value="${goodsCategory.id}">${goodsCategory.name}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                    <div class="input-group m-b-10">
                                        <span class="input-group-addon">分类排序</span>
                                        <input type="number" class="form-control" id="sort" name="sort" value="0" />
                                    </div>
                                    <div class="form-group col-md-12">
                                        <button type="button" class="btn btn-primary ajax-post" id="add-form-submit-btn">确 定</button>
                                        <button type="button" class="btn btn-default" onclick="javascript:history.back(-1);return false;">返 回</button>
                                    </div>
                                </form>
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

        //提交按钮监听事件
        $("#add-form-submit-btn").click(function () {
            if (!checkForm("user-add-form")){
                return;
            }
            var data=$("#goodscategory-add-form").serialize();
            $.ajax({
                url: 'add',
                type: 'post',
                data: data,
                dataType: 'json',
                async: true,
                success:function (data) {
                    if(data.code==0){
                        //表示物品分类添加成功，跳转到物品分类列表页面
                        showSuccessMsg("物品分类添加成功!",function () {
                            window.location.href='list';
                        })
                    }else{
                        showErrorMsg(data.msg);
                    }
                },
                error:function (data) {
                    alert("网络错误！")
                }

            });
        });


        $("#add-pic-btn").click(function () {
            $("#select-file").click()
        });

    });


</script>
</body>
</html>