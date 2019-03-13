function getMd5(str) {
    var md5;
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