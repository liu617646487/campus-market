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

                                <form method="post" id="edit-pwd-form" action="update_pwd" class="site-form">
                                    <div class="form-group">
                                        <label for="old-password">旧密码</label>
                                        <input type="password" class="form-control required" name="oldpwd" id="old-password" tips="输入账号的原登录密码" placeholder="输入账号的原登录密码">
                                    </div>
                                    <div class="form-group">
                                        <label for="new-password">新密码</label>
                                        <input type="password" class="form-control required" name="newpwd" id="new-password" tips="输入新的密码" placeholder="输入新的密码">
                                    </div>
                                    <div class="form-group">
                                        <label for="confirm-password">确认新密码</label>
                                        <input type="password" class="form-control required" name="confirmpwd" id="confirm-password" tips="请在次输入新密码" placeholder="请在次输入新密码">
                                    </div>
                                    <button type="button" id="submit-btn" class="btn btn-primary">修改密码</button>
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

        //提交按钮监听事件
        $("#submit-btn").click(function () {
            if (!checkForm("edit-pwd-form")){
                return;
            }
            var oldpwd = $("#old-password").val();
            var newpwd = $("#new-password").val();
            var confirmpwd = $("#confirm-password").val();
            if (newpwd!=confirmpwd){
                showErrorMsg("两次输入的密码不一致！")
                return;
            }
            var data = {oldpwd:oldpwd,newpwd:newpwd,confirmpwd:confirmpwd};
            ajaxRequest('update_pwd','post',data,function () {
                showSuccessMsg("密码修改成功",function () {})
            });

        });

    });

</script>
</body>
</html>