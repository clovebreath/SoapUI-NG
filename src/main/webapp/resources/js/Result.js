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
                          <td>${list[i].testId}</td><td>${list[i].testPlanId}</td><td>${list[i].caseId}</td><td>${list[i].testDate}</td>
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
                          <td>${list[i].testId}</td><td>${list[i].testPlanId}</td><td>${list[i].caseId}</td><td>${list[i].testDate}</td>
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
//格式化XML
function syntaxHighlightXml() {
    let xmlDePreSelector="#desired-response-xml";
    let xmlAcPreSelector="#actual-response-xml";
    xmlTreeViewer.init();
    let xmlNode1 = xmlTreeViewer.parseXML($(xmlDePreSelector).text());
    let retNode1 = xmlTreeViewer.getXMLViewerNode(xmlNode1.xml);
    $(xmlDePreSelector).html("").append(retNode1);
    xmlTreeViewer.init();
    let xmlNode2 = xmlTreeViewer.parseXML($(xmlAcPreSelector).text());
    let retNode2 = xmlTreeViewer.getXMLViewerNode(xmlNode2.xml);
    $(xmlAcPreSelector).html("").append(retNode2);
}
//格式化JSON
function syntaxHighlightJson(json) {
    try {
        if(typeof json === 'string'){
            json=JSON.parse(json);
        }
    } catch(e) {

    }
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
//格式化xml
String.prototype.removeLineEnd = function() {
    return this.replace(/(<.+?\s+?)(?:\n\s*?(.+?=".*?"))/g, '$1 $2')
};
function formatXml(text) {
    //去掉多余的空格
    text = '\n' + text.replace(/(<\w+)(\s.*?>)/g, function($0, name, props) {
        return name + ' ' + props.replace(/\s+(\w+=)/g, " $1");
    }).replace(/>\s*?</g, ">\n<");

    //把注释编码
    text = text.replace(/\n/g, '\r').replace(/<!--(.+?)-->/g,
        function($0, text) {
            var ret = '<!--' + escape(text) + '-->';
            //alert(ret);
            return ret;
        }).replace(/\r/g, '\n');

    //调整格式
    var rgx = /\n(<(([^\?]).+?)(?:\s|\s*?>|\s*?(\/)>)(?:.*?(?:(?:(\/)>)|(?:<(\/)\2>)))?)/mg;
    var nodeStack = [];
    var output = text.replace(rgx, function($0, all, name, isBegin,
                                            isCloseFull1, isCloseFull2, isFull1, isFull2) {
        var isClosed = (isCloseFull1 == '/') || (isCloseFull2 == '/')
            || (isFull1 == '/') || (isFull2 == '/');
        //alert([all,isClosed].join('='));
        var prefix = '';
        if (isBegin == '!') {
            prefix = getPrefix(nodeStack.length);
        } else {
            if (isBegin != '/') {
                prefix = getPrefix(nodeStack.length);
                if (!isClosed) {
                    nodeStack.push(name);
                }
            } else {
                nodeStack.pop();
                prefix = getPrefix(nodeStack.length);
            }

        }
        var ret = '\n' + prefix + all;
        return ret;
    });

    var prefixSpace = -1;
    var outputText = output.substring(1);
    //alert(outputText);

    //把注释还原并解码，调格式
    outputText = outputText.replace(/\n/g, '\r').replace(
        /(\s*)<!--(.+?)-->/g,
        function($0, prefix, text) {
            //alert(['[',prefix,']=',prefix.length].join(''));
            if (prefix.charAt(0) == '\r')
                prefix = prefix.substring(1);
            text = unescape(text).replace(/\r/g, '\n');
            var ret = '\n' + prefix + '<!--'
                + text.replace(/^\s*/mg, prefix) + '-->';
            //alert(ret);
            return ret;
        });

    return outputText.replace(/\s+$/g, '').replace(/\r/g, '\r\n');

}

function getPrefix(prefixIndex) {
    let span = '    ';
    let output = [];
    for (let i = 0; i < prefixIndex; ++i) {
        output.push(span);
    }

    return output.join('');
}
