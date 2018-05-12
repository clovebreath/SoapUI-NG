function queryData(){
    $.ajax({
        url:"/data/query",
        dataType:"json",
        data:{
            "dataId":$("#data-id").val(),
            "userId":$("#data-user-id").val(),
            "userElemCode":$("#data-user-elem-code").val(),
            "currPage":1
        },
        success:function (data) {
            $("#data-query-table tbody").html("");
            let list=data.list;
            let total=data.total;
            for (let i = 0; i < list.length; i++) { //<th>数据ID</th><th>抄表日期</th><th>用户ID</th><th>电能表编号</th><th>电能表数据</th><th>操作</th>
                let tr = `<tr id="data-${list[i].dataId}"><td>${list[i].dataId}</td><td>${list[i].collectTime}</td><td>${list[i].userId}</td>
                          <td>${list[i].elemId}</td><td>${list[i].elemData}</td>
                          <td><a href="#modal-container-modify-data" data-toggle="modal" onclick="modifyData(${list[i].dataId})">修改</a><strong> | </strong>
                          <a onclick="deleteData(${list[i].dataId})">删除</a></td></tr>`;
                $("#data-query-table tbody").append(tr);
            }
            initDataPagination(total);
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function queryDataByPage(){
    $.ajax({
        url:"/data/query",
        dataType:"json",
        data:{
            "dataId":$("#data-id").val(),
            "userId":$("#data-user-id").val(),
            "userElemCode":$("#data-user-elem-code").val(),
            "currPage":$("#data-pagination .active a").text()
        },
        success:function (data) {
            $("#data-query-table tbody").html("");
            let list=data.list;
            for (let i = 0; i < list.length; i++) { //<th>数据ID</th><th>抄表日期</th><th>用户ID</th><th>电能表编号</th><th>电能表数据</th><th>操作</th>
                let tr = `<tr id="data-${list[i].dataId}"><td>${list[i].dataId}</td><td>${list[i].collectTime}</td><td>${list[i].userId}</td>
                          <td>${list[i].elemId}</td><td>${list[i].elemData}</td>
                          <td><a href="#modal-container-modify-data" data-toggle="modal" onclick="modifyData(${list[i].dataId})">修改</a><strong> | </strong>
                          <a onclick="deleteData(${list[i].dataId})">删除</a></td></tr>`;
                $("#data-query-table tbody").append(tr);
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function initDataPagination(total) {
    let $pagination = $('#data-pagination');
    $pagination.twbsPagination('destroy');
    if(total>0){
        let defaultOpts = {
            totalPages: Math.ceil(total/10),
            first:"<<",
            prev:"<",
            next:">",
            last:">>",
            onPageClick:queryDataByPage
        };
        $pagination.twbsPagination(defaultOpts);
    }
}

function insertData() {
    $.ajax({
        url:"/data/insert",
        dataType:"json",
        data:{
            "userId":$("#new-data-user-id").val(),
            "collectTime":$("#new-data-collect-time").val(),
            "elemId":$("#new-data-elem-id").val(),
            "elemData":$("#new-data-elem-data").val()
        },
        success:function (data) {
            if(1==data){
                $("#insert-data-modal-close").trigger("click");
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
function clearEleData(){
    document.getElementById("new-data-form").reset();
    document.getElementById("modify-data-form").reset();
}
function updateData(){
    $.ajax({
        url:"/data/update",
        dataType:"json",
        data:{
            "dataId":$("#modify-data-id").val(),
            "userId":$("#modify-data-user-id").val(),
            "collectTime":$("#modify-data-collect-time").val(),
            "elemId":$("#modify-data-elem-id").val(),
            "elemData":$("#modify-data-elem-data").val()
        },
        success:function (data) {
            if(1==data){
                $("#modify-data-modal-close").trigger("click");
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
function modifyData(id) {//<th>数据ID</th><th>抄表日期</th><th>用户ID</th><th>电能表编号</th><th>电能表数据</th><th>操作</th>
    let tempRow = $("#data-" + id);
    $("#modify-data-id").val(id);
    $("#modify-data-user-id").val(tempRow.find("td")[2].innerText);
    $("#modify-data-collect-time").val(tempRow.find("td")[1].innerText);
    $("#modify-data-elem-id").val(tempRow.find("td")[3].innerText);
    $("#modify-data-elem-data").val(tempRow.find("td")[4].innerText);
}

function deleteData(id){
    $.ajax({
        url:"/data/delete",
        dataType:"json",
        data:{
            "dataId":id
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