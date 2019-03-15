
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

//通过ajax获取数据
function ajax(url, type, data, dataType, async) {
    var json;
    $.ajax({
        url: url,
        type: type,
        data: data,
        dataType: dataType,
        async: async,
        success: function (data) {
            json = data;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // 状态码
            console.log(XMLHttpRequest.status);
            // 状态
            console.log(XMLHttpRequest.readyState);
            // 错误信息
            console.log(textStatus);
            console.log(XMLHttpRequest.responseText);
            json = XMLHttpRequest.responseText;
        }
    })
    return json;
}

//自定义删除确认框
function del() {
    if (confirm("是否确认删除?")) {
        return true;
    } else {
        return false;
    }
}

function isLogin() {
    var username="<%=session.getAttribute('username')%>";
    var password="<%=session.getAttribute('password')%>";
    if(username !== null || username !== undefined || username !== '' || password !== null || password !== undefined || password !== '') {
        return true;
    }
    return false;
}