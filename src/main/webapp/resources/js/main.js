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
                let tr = `<tr><td>${data[i].apiId}</td><td>${data[i].apiName}</td><td>${data[i].apiType}</td><td>${data[i].apiLink}</td><td>${data[i].accessMode}</td><td>${data[i].apiInfo}</td></tr>`;
                $("#api-query-table tbody").append(tr);
            }
        },
        error: function(data){
            window.location="/error";
        }
    })
}