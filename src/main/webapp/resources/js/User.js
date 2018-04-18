function queryUser(){
    $.ajax({
        url:"/user/query",
        dataType:"json",
        data:{
            "userName":$("#user-name").val(),
            "userId":$("#user-id").val(),
            "userElemCode":$("#user-elem-code").val(),
            "userState":$("#user-state").val()
        },
        success:function (data) {
            $("#user-query-table tbody").html("");
            for (let i = 0; i < data.length; i++) { //<th>ID</th><th>名称</th><th>状态</th><th>类型</th><th>地址</th><th>电能表编号</th><th>电能表台区编号</th><th>操作</th>
                let tr = `<tr id="user-${data[i].userId}"><td>${data[i].userId}</td><td>${data[i].userName}</td><td>${data[i].userState}</td>
                          <td>${data[i].userType}</td><td>${data[i].userAddress}</td><td>${data[i].userElemCode}</td><td>${data[i].userAreaCode}</td>
                          <td><a href="#modal-container-modify-user" data-toggle="modal" onclick="modifyUser(${data[i].userId})">修改</a><strong> | </strong>
                          <a onclick="deleteUser(${data[i].userId})">删除</a></td></tr>`;
                $("#user-query-table tbody").append(tr);
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function insertUser() {
    $.ajax({
        url:"/user/insert",
        dataType:"json",
        data:{
            "userName":$("#new-user-name").val(),
            "userType":$("#new-user-type").val(),
            "userAddress":$("#new-user-address").val(),
            "userElemCode":$("#new-user-elem-code").val(),
            "userAreaCode":$("#new-user-area-code").val(),
            "userState":$("#new-user-state").val()
        },
        success:function (data) {
            if(1==data){
                $("#insert-user-modal-close").trigger("click");
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
function clearUserData(){
    document.getElementById("new-user-form").reset();
    document.getElementById("modify-user-form").reset();
}
function updateUser(){
    $.ajax({
        url:"/user/update",
        dataType:"json",
        data:{
            "userId":$("#modify-user-id").val(),
            "userName":$("#modify-user-name").val(),
            "userType":$("#modify-user-type").val(),
            "userAddress":$("#modify-user-address").val(),
            "userElemCode":$("#modify-user-elem-code").val(),
            "userAreaCode":$("#modify-user-area-code").val(),
            "userState":$("#modify-user-state").val()
        },
        success:function (data) {
            if(1==data){
                $("#modify-user-modal-close").trigger("click");
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
function modifyUser(id) {//<th>ID</th><th>名称</th><th>状态</th><th>类型</th><th>地址</th><th>电能表编号</th><th>电能表台区编号</th><th>操作</th>
    let tempRow=$("#user-"+id);
    $("#modify-user-id").val(id);
    $("#modify-user-name").val(tempRow.find("td")[1].innerText);
    $("#modify-user-state").val(tempRow.find("td")[2].innerText);
    $("#modify-user-type").val(tempRow.find("td")[3].innerText);
    $("#modify-user-address").val(tempRow.find("td")[4].innerText);
    $("#modify-user-elem-code").val(tempRow.find("td")[5].innerText);
    $("#modify-user-area-code").val(tempRow.find("td")[6].innerText);
}

function deleteUser(id){
    $.ajax({
        url:"/user/delete",
        dataType:"json",
        data:{
            "userId":id
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