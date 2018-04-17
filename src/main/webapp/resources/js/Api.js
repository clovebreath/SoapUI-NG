function queryApi() {
    $.ajax({
        url:"/api/query",
        dataType:"json",
        data:{
            "apiId":$("#api-id").val(),
            "apiName":$("#api-name").val(),
            "apiType":$("#api-type").val()
        },
        success:function (data) {
            $("#api-query-table tbody").html("");
            for (let i = 0; i < data.length; i++) {
                let tr = `<tr id="api-${data[i].apiId}"><td>${data[i].apiId}</td><td>${data[i].apiName}</td><td>${data[i].apiType}</td><td>${data[i].apiLink}</td>
                          <td>${data[i].accessMode}</td><td>${data[i].apiInfo}</td><td>
                          <a href="#modal-container-modify-api" data-toggle="modal" onclick="modifyApi(${data[i].apiId})">修改</a><strong> | </strong>
                          <a onclick="deleteApi(${data[i].apiId})">删除</a></td></tr>`;
                $("#api-query-table tbody").append(tr);
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function insertApi() {
    $.ajax({
        url:"/api/insert",
        dataType:"json",
        data:{
            "apiName":$("#new-api-name").val(),
            "apiType":$("#new-api-type").val(),
            "apiLink":$("#new-api-link").val(),
            "accessMode":$("#new-api-access-mode").val(),
            "apiInfo":$("#new-api-info").val()
        },
        success:function (data) {
            if(1==data){
                $("#insert-api-modal-close").trigger("click");
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
function clearApiData() {
    document.getElementById("api-new-form").reset();
    document.getElementById("api-modify-form").reset();

}
function modifyApi(apiId) {
    let tempRow=$("#api-"+apiId);
    $("#modify-api-id").val(apiId);
    $("#modify-api-name").val(tempRow.find("td")[1].innerText);
    $("#modify-api-type").val(tempRow.find("td")[2].innerText);
    $("#modify-api-link").val(tempRow.find("td")[3].innerText);
    $("#modify-api-access-mode").val(tempRow.find("td")[4].innerText);
    $("#modify-api-info").val(tempRow.find("td")[5].innerText);
}
function updateApi(){
    $.ajax({
        url:"/api/update",
        dataType:"json",
        data:{
            "apiId":$("#modify-api-id").val(),
            "apiName":$("#modify-api-name").val(),
            "apiType":$("#modify-api-type").val(),
            "apiLink":$("#modify-api-link").val(),
            "accessMode":$("#modify-api-access-mode").val(),
            "apiInfo":$("#modify-api-info").val()
        },
        success:function (data) {
            if(1==data){
                $("#modify-api-modal-close").trigger("click");
                alert("更新成功！");
            }else{
                alert("更新失败！");
            }
        },
        error: function(data){
            window.location="/error";
        }
    });
}
function deleteApi(apiId) {
    $.ajax({
        url:"/api/delete",
        dataType:"json",
        data:{
            "id":apiId
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