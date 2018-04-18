function queryResult(){
    $.ajax({
        url:"/result/query",
        dataType:"json",
        data:{
            "testId":$("#result-test-id").val(),
            "caseId":$("#result-case-id").val(),
            "assertion":$("#result-assertion").val()
        },
        success:function (data) {
            $("#result-query-table tbody").html("");
            for (let i = 0; i < data.length; i++) { //<th>测试编号</th><th>用例编号</th><th>测试时间</th><th>期望输出</th><th>实际输出</th><th>断言</th><th>操作</th>
                let tr = `<tr id="result-${data[i].testId}-${data[i].caseId}">
                          <td>${data[i].testId}</td><td>${data[i].caseId}</td><td>${data[i].testDate}</td>
                          <td>${data[i].desiredResponse}</td><td>${data[i].actualResponse}</td><td>${data[i].assertion}</td>
                          <td><a onclick="deleteResult(${data[i].dataId},${data[i].caseId})">删除</a></td></tr>`;
                $("#data-query-table tbody").append(tr);
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function insertResult() {
    $.ajax({
        url:"/result/insert",
        dataType:"json",
        data:{
            "testId":$("#new-result-test-id").val(),
            "caseId":$("#new-result-case-id").val(),
            "testDate":$("#new-result-test-date").val(),
            "desiredResponse":$("#new-result-desired-response").val(),
            "actualResponse":$("#new-result-actual-response").val(),
            "assertion":$("#new-result-assertion").val()
        },
        success:function (data) {
            if(1==data){
                $("#insert-result-modal-close").trigger("click");
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
function clearResultData(){
    document.getElementById("new-result-form").reset();
}

function deleteData(dataId,caseId){
    $.ajax({
        url:"/result/delete",
        dataType:"json",
        data:{
            "dataId":dataId,
            "caseId":caseId
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