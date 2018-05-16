function queryCase(){
    $.ajax({
        url:"/testCase/query",
        dataType:"json",
        data:{
            "caseName":$("#case-name").val(),
            "caseId":$("#case-id").val(),
            "caseLibId":$("#case-lib-id").val(),
            "caseParaType":$("#case-para-type").val(),
            "currPage":1
        },
        success:function (data) {
            $("#case-query-table tbody").html("");
            let list=data.list;
            let total=data.total;
            for (let i = 0; i < list.length; i++) { //<th>ID</th><th>名称</th><th>所属用例库编号</th><th>参数类型</th><th>入参</th><th>期望返回值</th><th>备注</th>
                let tr = `<tr id="case-${list[i].caseId}"><td>${list[i].caseId}</td><td>${list[i].caseName}</td><td>${list[i].caseLibId}</td>
                          <td>${list[i].caseParaType}</td><td>${list[i].parameter}</td><td>${list[i].desiredResponse}</td><td>${list[i].caseInfo}</td>
                          <td><a href="#modal-container-modify-case" data-toggle="modal" onclick="modifyCase(${list[i].caseId})" class="btn">修改</a>
                          <a onclick="deleteCase(${list[i].caseId})" class="btn">删除</a></td></tr>`;
                $("#case-query-table tbody").append(tr);
            }
            initCasePagination(total);
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function queryCaseByPage(){
    $.ajax({
        url:"/testCase/query",
        dataType:"json",
        data:{
            "caseName":$("#case-name").val(),
            "caseId":$("#case-id").val(),
            "caseLibId":$("#case-lib-id").val(),
            "caseParaType":$("#case-para-type").val(),
            "currPage":$("#case-pagination .active a").text()
        },
        success:function (data) {
            $("#case-query-table tbody").html("");
            let list=data.list;
            for (let i = 0; i < list.length; i++) { //<th>ID</th><th>名称</th><th>所属用例库编号</th><th>参数类型</th><th>入参</th><th>期望返回值</th><th>备注</th>
                let tr = `<tr id="case-${list[i].caseId}"><td>${list[i].caseId}</td><td>${list[i].caseName}</td><td>${list[i].caseLibId}</td>
                          <td>${list[i].caseParaType}</td><td>${list[i].parameter}</td><td>${list[i].desiredResponse}</td><td>${list[i].caseInfo}</td>
                          <td><a href="#modal-container-modify-case" data-toggle="modal" onclick="modifyCase(${list[i].caseId})" class="btn">修改</a>
                          <a onclick="deleteCase(${list[i].caseId})" class="btn">删除</a></td></tr>`;
                $("#case-query-table tbody").append(tr);
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}

function initCasePagination(total) {
    let $pagination = $('#case-pagination');
    $pagination.twbsPagination('destroy');
    if(total>0) {
        let defaultOpts = {
            totalPages: Math.ceil(total / 10),
            first: "<<",
            prev: "<",
            next: ">",
            last: ">>",
            onPageClick: queryCaseByPage
        };
        $pagination.twbsPagination(defaultOpts);
    }
}
function insertCase() {
    $.ajax({
        url:"/testCase/insert",
        dataType:"json",
        data:{
            "caseName":$("#new-case-name").val(),
            "caseLibId":$("#new-case-lib-id").val(),
            "caseParaType":$("#new-case-para-type").val(),
            "parameter":$("#new-parameter").val(),
            "desiredResponse":$("#new-desired-response").val(),
            "caseInfo":$("#new-case-info").val()
        },
        success:function (data) {
            if(1==data){
                $("#insert-case-modal-close").trigger("click");
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
function clearCaseData(){
    document.getElementById("new-case-form").reset();
    document.getElementById("modify-case-form").reset();
}
function updateCase(){
    $.ajax({
        url:"/testCase/update",
        dataType:"json",
        data:{
            "caseId":$("#modify-case-id").val(),
            "caseName":$("#modify-case-name").val(),
            "caseLibId":$("#modify-case-lib-id").val(),
            "caseParaType":$("#modify-case-para-type").val(),
            "parameter":$("#modify-parameter").val(),
            "desiredResponse":$("#modify-desired-response").val(),
            "caseInfo":$("#modify-case-info").val()
        },
        success:function (data) {
            if(1==data){
                $("#modify-case-modal-close").trigger("click");
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
function modifyCase(id) {//<th>ID</th><th>名称</th><th>所属用例库编号</th><th>参数类型</th><th>入参</th><th>期望返回值</th><th>备注</th>
    let tempRow=$("#case-"+id);
    $("#modify-case-id").val(id);
    $("#modify-case-name").val(tempRow.find("td")[1].innerText);
    $("#modify-case-lib-id").val(tempRow.find("td")[2].innerText);
    $("#modify-case-para-type").val(tempRow.find("td")[3].innerText);
    $("#modify-case-parameter").val(tempRow.find("td")[4].innerText);
    $("#modify-desired-response").val(tempRow.find("td")[5].innerText);
    $("#modify-case-info").val(tempRow.find("td")[6].innerText);
}

function deleteCase(id){
    $.ajax({
        url:"/testCase/delete",
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