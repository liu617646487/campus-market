//显示错误信息
function showErrorMsg(msg) {
    $.confirm({
        title: '错误',
        content: msg,
        type: 'red',
        typeAnimated: false,
        buttons: {
            omg: {
                text: '知道了',
                btnClass: 'btn-red',
                action: function(){

                }
            }
        }
    });
}

//显示警告信息
function showWarningMsg(msg) {
    $.confirm({
        title: '警告',
        content: msg,
        type: 'orange',
        typeAnimated: false,
        buttons: {
            omg: {
                text: '知道了',
                btnClass: 'btn-orange',
                action: function(){

                }
            }
        }
    });
}

//显示成功信息
function showSuccessMsg(msg,callback) {
    $.confirm({
        title: '成功',
        content: msg,
        type: 'green',
        buttons: {
            omg: {
                text: '知道了',
                btnClass: 'btn-green',
                action: function(){
                    callback();
                }
            }
        }
    });
}


//表单验证公用方法，只需要传form表单的id
function checkForm(formId) {
    var flag = true
    $("#"+formId).find(".required").each(function (i,e) {
        if ($(e).val()==''){
            showErrorMsg($(e).attr('tips'));
            flag = false;
            return false
        }
    });
    return flag;
}

//图片统一上传方法
function upload(ShowPictureImg,Input) {
    var formData = new FormData;
    formData.append('photo',document.getElementById("select-file").files[0]);
    $.ajax({
        url:'/upload/upload_photo',
        contentType:false,
        processData: false,
        data:formData,
        type:'post',
        success:function (data) {
            if(data.code==0){
                //表示用户编辑用户成功，跳转到用户列表页面
                showSuccessMsg("图片上传成功!",function () {
                    $("#"+ShowPictureImg).attr('src','/photo/view?filename='+data.data);
                    $("#"+Input).val(data.data);
                })
            }else{
                //data = $.parseJSON(data);
                showErrorMsg(data.msg);
            }
        },
        error:function (data) {
            alert("网络错误！")
        }
    });

}


function ajaxRequest(url,requestType,data,callback) {
    $.ajax({
        url: url,
        type: requestType,
        data: data,
        dataType: 'json',
        async: true,
        success:function (rst) {
            if(rst.code==0){
                callback();
            }else{
                showErrorMsg(rst.msg);
            }
        },
        error:function (data) {
            alert("网络错误！")
        }

    });
}