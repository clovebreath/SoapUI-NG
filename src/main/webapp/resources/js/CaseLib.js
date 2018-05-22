function queryLib(){
    $.ajax({
        url:"/testCaseLib/query",
        dataType:"json",
        data:{
            "libId":$("#lib-id").val(),
            "libName":$("#lib-name").val(),
            "applyApiId":$("#apply-api-id").val(),
            "currPage":1
        },
        success:function (data) {
            $("#lib-query-table tbody").html("");
            let list=data.list;
            let total=data.total;
            for (let i = 0; i < list.length; i++) {
                let tr = `<tr id="lib-${list[i].libId}"><td>${list[i].libId}</td><td>${list[i].libName}</td><td>${list[i].applyApiId}</td><td>${list[i].libInfo}</td>
                          <td><a href="#modal-container-modify-lib" data-toggle="modal" onclick="modifyLib(${list[i].libId})" class="btn">修改</a>
                          <a onclick="deleteLib(${list[i].libId})" class="btn">删除</a></td></tr>`;
                $("#lib-query-table tbody").append(tr);
            }
            initLibPagination(total);
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function queryLibByPage(){
    $.ajax({
        url:"/testCaseLib/query",
        dataType:"json",
        data:{
            "libId":$("#lib-id").val(),
            "libName":$("#lib-name").val(),
            "applyApiId":$("#apply-api-id").val(),
            "currPage":$("#lib-pagination .active a").text()
        },
        success:function (data) {
            $("#lib-query-table tbody").html("");
            let list=data.list;
            for (let i = 0; i < list.length; i++) {
                let tr = `<tr id="lib-${list[i].libId}"><td>${list[i].libId}</td><td>${list[i].libName}</td><td>${list[i].applyApiId}</td><td>${list[i].libInfo}</td>
                          <td><a href="#modal-container-modify-lib" data-toggle="modal" onclick="modifyLib(${list[i].libId})" class="btn">修改</a>
                          <a onclick="deleteLib(${list[i].libId})" class="btn">删除</a></td></tr>`;
                $("#lib-query-table tbody").append(tr);
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}

function initLibPagination(total) {
    let $pagination = $('#lib-pagination');
    $pagination.twbsPagination('destroy');
    if(total>0) {
        let defaultOpts = {
            totalPages: Math.ceil(total / 10),
            first: "<<",
            prev: "<",
            next: ">",
            last: ">>",
            onPageClick: queryLibByPage
        };
        $pagination.twbsPagination(defaultOpts);
    }
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
                queryLibByPage();
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
                queryLibByPage();
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
                queryLibByPage();
            }else{
                alert("删除失败！");
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}