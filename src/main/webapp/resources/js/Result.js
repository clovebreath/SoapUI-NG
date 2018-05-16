function queryResult(){
    $.ajax({
        url:"/result/query",
        dataType:"json",
        data:{
            "testId":$("#result-test-id").val(),
            "testPlanId":$("#result-test-plan-id").val(),
            "caseId":$("#result-case-id").val(),
            "assertion":$("#result-assertion").val(),
            "currPage":1
        },
        success:function (data) {
            $("#result-query-table tbody").html("");
            let list=data.list;
            let total=data.total;
            for (let i = 0; i < list.length; i++) { //<th>测试编号</th><th>测试计划编号</th><th>用例编号</th><th>测试时间</th><th>期望输出</th><th>实际输出</th><th>断言</th><th>操作</th>
                let tr = `<tr id="result-${list[i].testId}">
                          <td>${list[i].testId}</td><td>${list[i].testPlanId}</td><td>${list[i].caseId}</td><td>${list[i].testDate}</td>
                          <td>${list[i].desiredResponse}</td><td>${list[i].actualResponse}</td><td>${list[i].assertion}</td>
                          <td><a onclick="seeDetail(${list[i].testId})" class="btn" href="#modal-container-response-compare" data-toggle="modal">输出比对</a>
                              <a onclick="deleteResult(${list[i].testId})" class="btn">删除</a></td></tr>`;
                $("#result-query-table tbody").append(tr);
            }
            initResultPagination(total);
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function queryResultByPage(){
    $.ajax({
        url:"/result/query",
        dataType:"json",
        data:{
            "testId":$("#result-test-id").val(),
            "testPlanId":$("#result-test-plan-id").val(),
            "caseId":$("#result-case-id").val(),
            "assertion":$("#result-assertion").val(),
            "currPage":$("#result-pagination .active a").text()
        },
        success:function (data) {
            $("#result-query-table tbody").html("");
            let list=data.list;
            for (let i = 0; i < list.length; i++) { //<th>测试编号</th><th>测试计划编号</th><th>用例编号</th><th>测试时间</th><th>期望输出</th><th>实际输出</th><th>断言</th><th>操作</th>
                let tr = `<tr id="result-${list[i].testId}">
                          <td>${list[i].testId}</td><td>${list[i].testPlanId}</td><td>${list[i].caseId}</td><td>${list[i].testDate}</td>
                          <td>${list[i].desiredResponse}</td><td>${list[i].actualResponse}</td><td>${list[i].assertion}</td>
                          <td><a onclick="seeDetail(${list[i].testId})" class="btn" href="#modal-container-response-compare" data-toggle="modal">输出比对</a>
                              <a onclick="deleteResult(${list[i].testId})" class="btn">删除</a></td></tr>`;
                $("#result-query-table tbody").append(tr);
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function initResultPagination(total) {
    let $pagination = $('#result-pagination');
    $pagination.twbsPagination('destroy');
    if(total>0){
        let defaultOpts = {
            totalPages: Math.ceil(total/10),
            first:"<<",
            prev:"<",
            next:">",
            last:">>",
            onPageClick:queryResultByPage
        };
        $pagination.twbsPagination(defaultOpts);
    }
}
function insertResult() {
    $.ajax({
        url:"/result/insert",
        dataType:"json",
        data:{
            "testPlanId":$("#new-result-test-plan-id").val(),
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

function deleteResult(testId){
    $.ajax({
        url:"/result/delete",
        dataType:"json",
        data:{
            "testId":testId
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

function seeDetail(testId){
    let desiredResponse = $("result-"+testId+" td").eq(4).text();
    let acturalResponse = $("result-"+testId+" td").eq(5).text();
    $("#actual-response").text(syntaxHighlight(acturalResponse));
    $("#desired-response").text(syntaxHighlight(desiredResponse));
}

function syntaxHighlight(json) {
    if (typeof json !== 'string') {
        json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
        let cls = 'number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'key';
            } else {
                cls = 'string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'boolean';
        } else if (/null/.test(match)) {
            cls = 'null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}