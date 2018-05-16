const stateDictionary={0:'未执行',1:'执行中',2:'执行完成',3:'执行中断'};
function queryPlan(){
    $.ajax({
        url:"/plan/query",
        dataType:"json",
        data:{
            "planId":$("#plan-plan-id").val(),
            "libId":$("#plan-lib-id").val(),
            "apiId":$("#plan-api-id").val(),
            "planStatus":$("#plan-status").val(),
            "currPage":1
        },
        success:function (data) {
            $("#plan-query-table tbody").html("");
            let list=data.list;
            let total=data.total;
            for (let i = 0; i < list.length; i++) { //<th>测试计划编号</th><th>测试计划名称</th><th>用例库编号</th><th>被测接口编号</th><th>当前状态</th><th>操作</th>
                let tr = `<tr id="plan-${list[i].testPlanId}">
                          <td>${list[i].testPlanId}</td><td>${list[i].planName}</td><td>${list[i].libId}</td><td>${list[i].apiId}</td><td>${stateDictionary[list[i].planStatus]}</td>`;
                if (list[i].planStatus !== 1) {
                    tr+=`<td><a onclick="executePlan(${list[i].testPlanId})" class="btn">执行计划</a>`;
                } else {
                    tr+=`<td><a class="btn" disabled>执行计划</a>`;
                }
                tr+=`<a onclick="modifyPlan(${list[i].testPlanId})" class="btn" href="#modal-container-modify-plan" data-toggle="modal">修改</a>
                     <a onclick="deletePlan(${list[i].testPlanId})" class="btn">删除</a></td></tr>`;
                $("#plan-query-table tbody").append(tr);
            }
            initPlanPagination(total);
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function queryPlanByPage(){
    $.ajax({
        url:"/plan/query",
        dataType:"json",
        data:{
            "planId":$("#plan-plan-id").val(),
            "libId":$("#plan-lib-id").val(),
            "apiId":$("#plan-api-id").val(),
            "planStatus":$("#plan-status").val(),
            "currPage":$("#plan-pagination .active a").text()
        },
        success:function (data) {
            $("#plan-query-table tbody").html("");
            let list=data.list;
            for (let i = 0; i < list.length; i++) { //<th>测试计划编号</th><th>用例库编号</th><th>被测接口编号</th><th>当前状态</th><th>操作</th>
                let tr = `<tr id="plan-${list[i].testPlanId}">
                          <td>${list[i].testPlanId}</td><td>${list[i].planName}</td><td>${list[i].libId}</td><td>${list[i].apiId}</td><td>${stateDictionary[list[i].planStatus]}</td>`;
                if (list[i].planStatus !== 1) {
                    tr+=`<td><a onclick="executePlan(${list[i].testPlanId})" class="btn">执行计划</a>`;
                } else {
                    tr+=`<td><a class="btn" disabled>执行计划</a>`;
                }
                tr+=`<a onclick="modifyPlan(${list[i].testPlanId})" class="btn" href="#modal-container-modify-plan" data-toggle="modal">修改</a>
                     <a onclick="deletePlan(${list[i].testPlanId})" class="btn">删除</a></td></tr>`;
                $("#plan-query-table tbody").append(tr);
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function initPlanPagination(total) {
    let $pagination = $('#plan-pagination');
    $pagination.twbsPagination('destroy');
    if(total>0){
        let defaultOpts = {
            totalPages: Math.ceil(total/10),
            first:"<<",
            prev:"<",
            next:">",
            last:">>",
            onPageClick:queryPlanByPage
        };
        $pagination.twbsPagination(defaultOpts);
    }
}
function insertPlan() {
    $.ajax({
        url:"/plan/insert",
        dataType:"json",
        data:{
            "testPlanId":$("#plan-new-plan-id").val(),
            "libId":$("#plan-new-lib-id").val(),
            "planName":$("#plan-new-plan-name").val(),
            "apiId":$("#plan-new-api-id").val()
        },
        success:function (data) {
            if(1==data){
                $("#insert-plan-modal-close").trigger("click");
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
function updatePlan() {
    $.ajax({
        url:"/plan/update",
        dataType:"json",
        data:{
            "testPlanId":$("#plan-modify-plan-id").val(),
            "libId":$("#plan-modify-lib-id").val(),
            "planName":$("#plan-modify-plan-name").val(),
            "apiId":$("#plan-modify-api-id").val()
        },
        success:function (data) {
            if(1==data){
                $("#modify-plan-modal-close").trigger("click");
                queryPlanByPage();
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
function modifyPlan(planId) {
    let tempRow=$("#plan-"+planId);
    $("#plan-modify-plan-id").val(planId);
    $("#plan-modify-plan-name").val(tempRow.find("td")[1].innerText);
    $("#plan-modify-lib-id").val(tempRow.find("td")[2].innerText);
    $("#plan-modify-api-id").val(tempRow.find("td")[3].innerText);
}

function clearPlanData(){
    document.getElementById("new-plan-form").reset();
    document.getElementById("modify-plan-form").reset();
}

function deletePlan(planId){
    $.ajax({
        url:"/plan/delete",
        dataType:"json",
        data:{
            "planId":planId
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
function executePlan(planId) {
    $.ajax({
        url:"/plan/execute",
        dataType:"json",
        data:{
            "planId":planId
        },
        success:function (data) {
            queryPlanByPage();
        },
        error: function(data){
            window.location="/error";
        }
    });
}