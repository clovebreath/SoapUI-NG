function queryCase(){
    $.ajax({
        url:"/testCase/query",
        dataType:"json",
        data:{
            "caseName":$("#case-name").val(),
            "caseId":$("#case-id").val(),
            "caseLibId":$("#case-lib-id").val(),
            "caseParaType":$("#case-para-typed").val()
        },
        success:function (data) {
            $("#case-query-table tbody").html("");
            for (let i = 0; i < data.length; i++) { //<th>ID</th><th>名称</th><th>所属用例库编号</th><th>参数类型</th><th>入参</th><th>期望返回值</th><th>备注</th>
                let tr = `<tr id="case-${data[i].caseId}"><td>${data[i].caseId}</td><td>${data[i].caseName}</td><td>${data[i].caseLibId}</td>
                          <td>${data[i].caseParaType}</td><td>${data[i].parameter}</td><td>${data[i].desiredResponse}</td><td>${data[i].caseInfo}</td>
                          <td><a href="#modal-container-modify-case" data-toggle="modal" onclick="modifyCase(${data[i].caseId})">修改</a><strong> | </strong>
                          <a onclick="deleteCase(${data[i].caseId})">删除</a></td></tr>`;
                $("#lib-query-table tbody").append(tr);
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function insertLib() {
    $.ajax({
        url:"/testCaseLib/insert",
        dataType:"json",
        data:{
            "libName":$("#new-lib-name").val(),
            "libInfo":$("#new-lib-info").val(),
            "applyApiId":$("#new-apply-api-id").val()
        },
        success:function (data) {
            if(1==data){
                $("#insert-lib-modal-close").trigger("click");
                alert("新增成功！");
            }else{
                alert("新增失败！");
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function clearLibData(){
    document.getElementById("lib-new-form").reset();
    document.getElementById("lib-modify-form").reset();
}

function updateLib(){
    $.ajax({
        url:"/testCaseLib/update",
        dataType:"json",
        data:{
            "libId":$("#modify-lib-id").val(),
            "libName":$("#modify-lib-name").val(),
            "libInfo":$("#modify-lib-info").val(),
            "applyApiId":$("#modify-apply-api-id").val()
        },
        success:function (data) {
            if(1==data){
                $("#modify-lib-modal-close").trigger("click");
                alert("修改成功！");
            }else{
                alert("修改失败！");
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}

function modifyLib(id) {
    let tempRow=$("#lib-"+id);
    $("#modify-lib-id").val(id);
    $("#modify-lib-name").val(tempRow.find("td")[1].innerText);
    $("#modify-apply-api-id").val(tempRow.find("td")[2].innerText);
    $("#modify-lib-info").val(tempRow.find("td")[3].innerText);
}

function deleteLib(id){
    $.ajax({
        url:"/testCaseLib/delete",
        dataType:"json",
        data:{
            "id":id
        },
        success:function (data) {
            if(1==data){
                alert("删除成功！");
            }else{
                alert("删除失败！");
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}