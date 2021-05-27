<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>${title!""}-${zzcjxy!""}</title>
    <link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
    <link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
    <link media="all" href="/home/css/release_product.css" type="text/css" rel="stylesheet">
</head>

<body>

    <#include "../common/top-header.ftl" />

    <div class="container">
        <div class="main center">
            <img class="release-icon-main" src="/home/imgs/release-icon.png" alt="">
            <div class="wave-fluid"></div>
            <div class="release-title">发布商品出售</div>
            <form action="publish" id="publish-form" enctype="multipart/form-data" method="post">
                <div class="form-wr">
                    <div class="form-must-wr">

                        <div id="show-img" class="form-item l goods-title" style="height: 100px;display: none">
                            <div class="form-key">
                                <span>已上传图片</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <img id="upload-img" src="/home/imgs/release-icon.png" width="100px" height="100px"/>
                                </div>
                            </div>
                        </div>

                        <input id="photo" type="hidden" name="photo" value="" class="required" tips="请上传图片">
                        <div class="form-item l goods-title">
                            <div class="form-key">
                                <span>选择商品图片</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <input type="file" id="select-file" name="select-file" />
                                </div>
                            </div>
                        </div>

                        <div class="form-item l goods-title">
                            <div class="form-key">
                                <span>商品名称</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <input id="name" name="name" placeholder="最多18个字" maxlength="18" value="" type="text" class="required" tips="请填写商品名称"></div>
                            </div>
                        </div>

                        <div class="form-item xl goods-desc">
                            <div class="form-key">
                                <span>商品详情</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <textarea name="content" id="desc" placeholder="建议填写物品用途、新旧程度、原价等信息，至少15个字" minlength="15" class="required" tips="请填写商品详情描述"></textarea>
                                </div>
                            </div>
                        </div>


                        <div class="form-item l goods-price">
                            <div class="form-key">
                                <span>购入价格</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <input class="price required" id="buyPrice" name="buyPrice" value="" type="number" tips="请填写购入价格"></div>
                            </div>
                        </div>
                        <div class="form-item l goods-price">
                            <div class="form-key">
                                <span>售出价格</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <input class="price required" id="sellPrice" name="sellPrice" value="" type="number" tips="请上填写出售价格"></div>
                            </div>
                        </div>



                        <!--选择分类信息 -->
                        <div class="form-item l goods-cat">
                            <div class="form-key">
                                <span>分类</span>
                            </div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <select id="cid" style="width: 40%;height: 28px;color: rgb(68, 68, 68);font-size: 1.17em;line-height: 28px;vertical-align: middle;background-color: transparent;" >
                                        <option>---请选择大类----</option>
                                        <#if goodsCategories??>
                                            <#list goodsCategories as goodsCategory>
                                                <#if !goodsCategory.parent??>
                                                    <option value="${goodsCategory.id}">${goodsCategory.name}</option>
                                                </#if>
                                            </#list>
                                        </#if>
                                    </select>
                                    <select id="cid2" name="goodsCategory" style="width: 40%;height: 28px;color: rgb(68, 68, 68);font-size: 1.17em;line-height: 28px;vertical-align: middle;background-color: transparent;" class="required" tips="请选择分类">
                                        <option value="-1">----请选择小类----</option>
                                        <#if goodsCategories??>
                                            <#list goodsCategories as goodsCategory>
                                                <#if goodsCategory.parent??>
                                                    <option style="display:none" value="${goodsCategory.id}" pid="${goodsCategory.parent.id}">${goodsCategory.name}</option>
                                                </#if>
                                            </#list>
                                        </#if>
                                    </select>

                                    <!-- <INPUT TYPE="button" VALUE="点我" ONCLICK="getSelectValue();"> -->
                                </div>
                            </div>
                        </div>
                    </div>

                    <input id="submit-btn"  class="form-submit" type="button" value="上传" />

                </div>
            </form>
        </div>
    </div>

    <#include "../common/right-menu.ftl" />
    <#include "../common/bottom-footer.ftl" />

    <script src="/home/js/jquery-3.1.1.min.js"></script>
    <script src="/home/js/common.js"></script>
    <script src="/home/js/add.js"></script>


    <script type="text/javascript">

        $(document).ready(function(){
            $("#cid").change(function(){
                var cid = $(this).val()
                $("#cid2 option[value='-1']").prop("selected",true);
                $("#cid2 option[value!='-1']").css({'display':'none'});
                $("#cid2 option").each(function (i,e) {
                    if ($(e).attr('pid')==cid){
                        $(e).css({'display':'block'})
                    }
                })
            });

            $("#submit-btn").click(function () {
                var flag = true
                $(".required").each(function (i,e) {
                    if ($(e).val()==null||$(e).val()==''){
                        alert($(e).attr('tips'))
                        flag = false
                        return false;
                    }
                });
                if (flag){
                    if ($("#desc").val().length<15){
                        alert("详情描述必须大于15个字符！")
                        return false;
                    }
                    if (parseFloat($("#buyPrice").val())=='NaN'||parseFloat($("#sellPrice").val())=='NaN'){
                        alert("价格必须为数字！")
                        return false;
                    }
                    if ($("#cid2").val()==''||$("#cid2").val()=='-1'){
                        alert("请选择物品分类")
                        return false;
                    }
                    var data = $("#publish-form").serialize();
                    ajaxRequest('publish','post',data, function () {
                        window.location.href='index';
                    })
                }
            });

            $("#select-file").change(function () {
                uploadPhoto("upload-img","photo");
            });

        });


        function uploadPhoto(showImg,Input) {
            var formData = new FormData
            formData.append('photo',document.getElementById("select-file").files[0])
            $.ajax({
                url:'/home/upload/upload_photo',
                contentType:false,
                processData:false,
                data:formData,
                type:'post',
                success:function (data) {
                    if (data.code==0){
                        $("#"+showImg).attr('src','/photo/view?filename='+data.data);
                        $("#show-img").show();
                        $("#"+Input).val(data.data);
                    }else{
                        //data = JSON.parse(data)
                        alert(data.msg);
                    }
                },
                error:function () {
                    alert("网络错误！")
                }
            });
        }




    </script>



</body>
</html>