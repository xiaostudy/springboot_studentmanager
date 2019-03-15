//编辑年级
function gradeEdit(thiz) {
    if(isLogin() == false) {
        alert("请先登录！");
        $("#login").submit();
    }
    var gradeNumber = thiz.parentNode.parentNode.firstElementChild;
    var gradeName = thiz.parentNode.parentNode.firstElementChild.nextElementSibling;

    var gradeNumber_input = document.createElement("input");
    var gradeName_input = document.createElement("input");

    gradeNumber_input.setAttribute("value", gradeNumber.innerHTML);
    gradeName_input.setAttribute("value", gradeName.innerHTML);
    gradeNumber_input.setAttribute("class", "btn-group-sm bg-info");
    gradeName_input.setAttribute("class", "btn-group-sm bg-info");
    gradeNumber_input.setAttribute("style", "width: 100px");
    gradeName_input.setAttribute("style", "width: 100px");

    gradeNumber.innerHTML = "";
    gradeName.innerHTML = "";
    gradeNumber.appendChild(gradeNumber_input);
    gradeName.appendChild(gradeName_input);

    thiz.setAttribute("style", "display: none");
    thiz.nextElementSibling.setAttribute("style", "display: inline");
}

//保存编辑年级
function saveGradeEdit(thiz) {
    if(isLogin() == false) {
        alert("请先登录！");
        $("#login").submit();
    }
    var gradeNumber = thiz.parentNode.parentNode.firstElementChild;
    var gradeName = thiz.parentNode.parentNode.firstElementChild.nextElementSibling;
    var gradeNumber_input = gradeNumber.firstElementChild;
    var gradeName_input = gradeName.firstElementChild;
    var gradeNumber_value = gradeNumber_input.value;
    var gradeName_value = gradeName_input.value;
    var newGradeNumber = document.getElementById("thiz" + thiz);
    var newGradeName = document.getElementById("gradeName" + thiz);
    var json = {"newGradeNumber": gradeNumber_value, "newGradeName": gradeName_value, "gradeId": thiz.name};

    var data = ajax("updateAjax", "get", json, "json", false);

    if(data.status == "error") {
        gradeNumber.innerHTML = data.oldGradeNumber;
        gradeName.innerHTML = data.oldGradeName;
        alert(data.message);
        if(data.message == "请先登录！") {
            $("#login").submit();
        }
    } else if(data.status == "ok") {
        gradeNumber.innerHTML = gradeNumber_value;
        gradeName.innerHTML = gradeName_value;
        alert(data.message);
    }

    thiz.setAttribute("style", "display: none");
    thiz.previousElementSibling.setAttribute("style", "display: inline");
}

//删除年级
function deleteGrade(thiz) {
    if(isLogin() == false) {
        alert("请先登录！");
        $("#login").submit();
    }
    var b = del();//在common.js中
    if(b) {
        var json = {"gradeId": thiz.name};

        var data = ajax("deleteAjax", "get", json, "json", false);

        if(data.status == "error") {
            alert(data.message);
            if(data.message == "请先登录！") {
                $("#login").submit();
            }
        } else if(data.status == "ok") {
            $(thiz.parentNode.parentNode).empty();
            alert(data.message);
        }
    }
}

//添加年级时添加输入信息
function addGrade(thiz) {
    if(isLogin() == false) {
        alert("请先登录！");
        $("#login").submit();
    }
    var table = thiz.parentNode.parentNode;
    var tr = document.createElement("tr");
    table.appendChild(tr);

    //table.insertRow(-1);//向table最后添加tr
    //var last = table.lastChild;
    tr.setAttribute("id","add");
    var td_id = document.createElement("td");
    var td_name = document.createElement("td");
    tr.appendChild(td_id);
    tr.appendChild(td_name);
    var gradeNumber_input = document.createElement("input");
    var gradeName_input = document.createElement("input");
    gradeNumber_input.setAttribute("type", "text");
    gradeName_input.setAttribute("type", "text");
    gradeNumber_input.setAttribute("id", "newGradeNumber");
    gradeName_input.setAttribute("id", "newGradeName");
    td_id.appendChild(gradeNumber_input);
    td_name.appendChild(gradeName_input);

    gradeNumber_input.setAttribute("class", "btn-group-sm bg-info");
    gradeName_input.setAttribute("class", "btn-group-sm bg-info");
    gradeNumber_input.setAttribute("style", "width: 100px");
    gradeName_input.setAttribute("style", "width: 100px");
    thiz.setAttribute("style", "display: none");
    thiz.nextElementSibling.setAttribute("style", "display: inline");
}

//添加年级，从后台获取数据判断
function inAddGrade(thiz) {
    if(isLogin() == false) {
        alert("请先登录！");
        $("#login").submit();
    }
    var newGradeNumber = thiz.parentNode.parentNode.lastElementChild.firstElementChild;
    var newGradeName = thiz.parentNode.parentNode.lastElementChild.firstElementChild.nextElementSibling;
    var newGradeNumber_input = newGradeNumber.firstElementChild;
    var newGradeName_input = newGradeName.firstElementChild;

    var gradeNumber_value = newGradeNumber_input.value;
    var gradeName_value = newGradeName_input.value;

    var json = {"newGradeNumber": gradeNumber_value, "newGradeName": gradeName_value};

    var data = ajax("insertAjax", "get", json, "json", false);

    if(data.status == "error") {
        $(thiz.parentNode.lastElementChild).empty();

        alert(data.message);
        if(data.message == "请先登录！") {
            $("#login").submit();
        }
    } else if(data.status == "ok") {
        newGradeNumber.innerHTML = gradeNumber_value;
        newGradeName.innerHTML = gradeName_value;

        var tr = thiz.parentNode.parentNode.lastElementChild;
        // tr.setAttribute("id", "deleteGrade" + gradeNumber.value);//为了方便删除

        var td = document.createElement("td");
        tr.appendChild(td);

        var a_edit = document.createElement("a");
        var a_saveEdit = document.createElement("a");
        var a_del = document.createElement("a");
        td.appendChild(a_edit);
        td.appendChild(a_saveEdit);
        td.appendChild(a_del);

        a_edit.innerText = "编辑";
        a_edit.setAttribute("class", "btn btn-sm btn-primary");
        a_edit.setAttribute("onclick", "gradeEdit(this)");

        a_saveEdit.innerText = "保存";
        a_saveEdit.setAttribute("innerText", "保存");
        a_saveEdit.setAttribute("class", "btn btn-sm btn-success");
        a_saveEdit.setAttribute("name", data.gradeId);
        a_saveEdit.setAttribute("onclick", "saveGradeEdit(this)");
        a_saveEdit.setAttribute("style", "display: none");

        a_del.innerText = "删除";
        a_del.setAttribute("class", "btn btn-sm btn-danger");
        a_del.setAttribute("name", data.gradeId);
        a_del.setAttribute("onclick", "deleteGrade(this)");
        alert(data.message);
    }
    thiz.setAttribute("style", "display: none");
    thiz.previousElementSibling.setAttribute("style", "display: inline");
}