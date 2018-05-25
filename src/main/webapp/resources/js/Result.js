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
                // list[i].actualResponse.replace(/</g, "&lt;").replace(/>/g, "&gt;");
                // list[i].desiredResponse.replace(/</g, "&lt;").replace(/>/g, "&gt;");
                let tr = `<tr id="result-${list[i].resultId}">
                          <td><a class="btn" onclick="showTestPie(this)" href="#modal-container-result-echart" data-toggle="modal">${list[i].testId}</a></td>
                          <td>${list[i].testPlanId}</td><td>${list[i].caseId}</td><td>${list[i].testDate}</td>
                          <td><pre></pre></td><td><pre></pre></td><td>${list[i].assertion}</td>
                          <td><a onclick="seeDetail(${list[i].resultId})" class="btn" href="#modal-container-response-compare" data-toggle="modal">输出比对</a>
                              <a onclick="deleteResult(${list[i].resultId})" class="btn">删除</a></td></tr>`;
                $("#result-query-table tbody").append(tr);
                let trDom=$("#result-query-table tbody tr:last-child");
                trDom.find("pre").eq(0).text(list[i].desiredResponse);
                trDom.find("pre").eq(1).text(list[i].actualResponse);
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
                // list[i].actualResponse.replace(/</g, "&lt;").replace(/>/g, "&gt;");
                // list[i].desiredResponse.replace(/</g, "&lt;").replace(/>/g, "&gt;");
                let tr = `<tr id="result-${list[i].resultId}">
                          <td><a class="btn" onclick="showTestPie(this)" href="#modal-container-result-echart" data-toggle="modal">${list[i].testId}</a></td>
                          <td>${list[i].testPlanId}</td><td>${list[i].caseId}</td><td>${list[i].testDate}</td>
                          <td><pre></pre></td><td><pre></pre></td><td>${list[i].assertion}</td>
                          <td><a onclick="seeDetail(${list[i].resultId})" class="btn" href="#modal-container-response-compare" data-toggle="modal">输出比对</a>
                              <a onclick="deleteResult(${list[i].resultId})" class="btn">删除</a></td></tr>`;
                $("#result-query-table tbody").append(tr);
                let trDom=$("#result-query-table tbody tr:last-child");
                trDom.find("pre").eq(0).text(list[i].desiredResponse);
                trDom.find("pre").eq(1).text(list[i].actualResponse);
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
                queryResultByPage();
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

function deleteResult(resultId){
    $.ajax({
        url:"/result/delete",
        dataType:"json",
        data:{
            "resultId":resultId
        },
        success:function (data) {
            if(1==data){
                alert("删除成功！");
                queryResultByPage();
            }else{
                alert("删除失败！");
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}
//查看数据详情
function seeDetail(resultId){
    let desiredResponse = $("#result-"+resultId+" td").eq(4).text();
    let acturalResponse = $("#result-"+resultId+" td").eq(5).text();
    let xmlDePreSelector="#desired-response-xml";
    let jsonDePreSelector="#desired-response-json";
    let xmlAcPreSelector="#actual-response-xml";
    let jsonAcPreSelector="#actual-response-json";
    if('<'===desiredResponse[0]){

        $(xmlDePreSelector).text(formatXml(desiredResponse));

        $(xmlDePreSelector).show();
        $(jsonDePreSelector).hide();
    }else {
        $(jsonDePreSelector).html(syntaxHighlightJson(desiredResponse));
        $(xmlDePreSelector).hide();
        $(jsonDePreSelector).show();
    }
    if('<'===acturalResponse[0]){
        $(xmlAcPreSelector).text(formatXml(acturalResponse));

        $(xmlAcPreSelector).show();
        $(jsonAcPreSelector).hide();
    }else {
        $(jsonAcPreSelector).html(syntaxHighlightJson(acturalResponse));
        $(xmlAcPreSelector).hide();
        $(jsonAcPreSelector).show();
    }

}
function showTestPie(button) {
    let testId=button.text;
    $("#chart-result-case-id").hide();
    $("#chart-result-test-id").hide();
    setTimeout(function () {
        resultCategoryByAssertion(testId);
    },250);
}
