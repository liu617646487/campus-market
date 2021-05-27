<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>后台管理系统主页 - ${siteName!""}</title>
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
                            <div class="card-body">

                                <div class="edit-avatar">
                                    <#if Session["user"].headPic??>
                                        <#if Session["user"].headPic?length gt 0>
                                            <img src="/photo/view?filename=${Session["user"].headPic!""}" class="img-avatar" id="show-img" alt="..."/>
                                        <#else>
                                            <img src="/admin/images/head-default.jpg" class="img-avatar" id="show-img" alt="..."/>
                                        </#if>
                                    </#if>
                                    <div class="avatar-divider"></div>
                                    <div class="edit-avatar-content">
                                        <input type="file" id="select-file" style="display: none" onchange="upload('show-img','headPic')">
                                        <button class="btn btn-default" id="edit-pic-btn">修改头像</button>
                                        <p class="m-0">选择一张你喜欢的图片，裁剪后会自动生成264x264大小，上传图片大小不能超过2M。</p>
                                    </div>
                                </div>
                                <hr>
                                <form method="post" action="update_userinfo" id="login-user-edit-form" class="site-form">
                                    <input type="hidden" name="headPic" id="headPic" value="${Session["user"].headPic!""}">
                                    <div class="form-group">
                                        <label for="username">用户名</label>
                                        <input type="text" class="form-control" name="username" id="username" value="${Session["user"].username}" disabled="disabled" />
                                    </div>
                                    <div class="form-group">
                                        <label for="nickname">手机号码</label>
                                        <input type="text" class="form-control" name="mobile" id="mobile" placeholder="输入您的手机号" value="${Session["user"].mobile!""}">
                                    </div>
                                    <div class="form-group">
                                        <label for="email">邮箱</label>
                                        <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="请输入正确的邮箱地址" value="${Session["user"].email!""}">
                                        <small id="emailHelp" class="form-text text-muted">请保证您填写的邮箱地址是正确的。</small>
                                    </div>
                                    <button type="button" id="edit-form-submit-btn" class="btn btn-primary ajax-post">保存</button>
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

    $(document).ready(function() {

        $("#edit-pic-btn").click(function () {
            $("#select-file").click()
        });

        //提交按钮监听事件
        $("#edit-form-submit-btn").click(function () {
            if (!checkForm("login-user-edit-form")){
                return;
            }
            var data=$("#login-user-edit-form").serialize();

            $.ajax({
                url: 'update_userinfo',
                type: 'post',
                data: data,
                dataType: 'json',
                async: true,
                success:function (data) {
                    if(data.code==0){
                        //表示用户编辑用户成功，跳转到用户列表页面
                        showSuccessMsg("信息修改成功!",function () {
                        });
                    }else{
                        showErrorMsg(data.msg);
                    }
                },
                error:function (data) {
                    alert("网络错误！")
                }

            });
        });

    });

</script>
</body>
</html>