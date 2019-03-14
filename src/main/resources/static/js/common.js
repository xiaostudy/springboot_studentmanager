
/*提交表单时把密码加密后提交*/
$(document).ready(function() {
    //ajax submit.
    $("#form").submit(function()  {
        var md5_password = getMd5($("#password").val());
        if(md5_password == "N/A") {
            alert("服务器出错！");
            return false;
        }
        $("#password").val(md5_password);
    });
});

/*把密码通过后台加密*/
function getMd5(str) {
    var md5 = "N/A";
    $.ajax({
        url: "/md5",
        type: "get",
        data: {"password": str},
        dataType: "text",
        async: false,
        success: function (data) {
            md5 = data;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // 状态码
            console.log(XMLHttpRequest.status);
            // 状态
            console.log(XMLHttpRequest.readyState);
            // 错误信息
            console.log(textStatus);
            console.log(XMLHttpRequest.responseText);
        }
    })
    return md5;
}