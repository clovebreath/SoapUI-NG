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
