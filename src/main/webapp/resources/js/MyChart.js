/**
 * 获取饼状图的option项
 * @param data
 * @param myTittle
 * @param mySubTittle
 * @param myCategory
 * @returns {{title: {text: *, subtext: *, x: string}, tooltip: {trigger: string, formatter: string}, legend: {orient: string, x: string, data: Array}, toolbox: {show: boolean, x: string, y: string, feature: {mark: {show: boolean}, dataView: {show: boolean, readOnly: boolean}, magicType: {show: boolean, type: string[], option: {funnel: {x: string, width: string, funnelAlign: string, max: number}}}, restore: {show: boolean}, saveAsImage: {show: boolean}}}, calculable: boolean, series: *[]}}
 */
function getPieOption(data,myTittle,mySubTittle,myCategory) {
    let legendData=[];
    let seriesData=[];
    for (let i = 0; i < data.length; i++) {
        legendData.push(data[i].type);
        seriesData.push({"value":data[i].number,"name":data[i].type});
    }
    let option={
        title : {
            text: myTittle,
            subtext: mySubTittle,
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:legendData
        },
        toolbox: {
            show : true,
            x:'right',
            y:'top',
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: true},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: 1548
                        }
                    }
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:myCategory,
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:seriesData
            }
        ]
    };
    return option;
}

/**
 * 获取柱状图的option项
 * @param data
 * @param myTittle
 * @param mySubTittle
 * @returns {{title: {text: *, subtext: *}, tooltip: {trigger: string}, toolbox: {show: boolean, feature: {mark: {show: boolean}, dataView: {show: boolean, readOnly: boolean}, magicType: {show: boolean, type: string[]}, restore: {show: boolean}, saveAsImage: {show: boolean}}}, calculable: boolean, xAxis: *[], yAxis: *[], series: *[]}}
 */
function getBarOption(data,myTittle,mySubTittle){
    let xData=[];
    let yData=[];
    for (let i = 0; i < data.length; i++) {
        xData.push(data[i].type);
        yData.push(data[i].number);
    }
    let option = {
        title : {
            text: myTittle,
            subtext: mySubTittle
        },
        tooltip : {
            trigger: 'axis'
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: true},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : xData
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'统计结果',
                type:'bar',
                data:yData
            }
        ]
    };
    return option;
}

function countAllData(){
    //初始化echarts对象
    let chartCountAll = echarts.init(document.getElementById('chart-count-all'));
    //读取数据前显示读取提醒
    chartCountAll.showLoading({
        text: '正在努力的读取数据中...'
    });
    //获取数据
    $.ajax({
        url:"/common/countAll",
        dataType:"json",
        success:function (data) {
            //隐藏读取中提示
            chartCountAll.hideLoading();
            //设置option，显示图表
            chartCountAll.setOption(getBarOption(data,"数据总览",""));
            window.onresize=chartCountAll.resize;
        },
        error:function () {
            chartCountAll.showLoading({
                text: '读取数据失败。'    //loading话术
            });
        }
    });
}

function apiCategoryByType() {
    let chartApiType = echarts.init(document.getElementById('chart-api-type'));
    chartApiType.showLoading({
        text: '正在努力的读取数据中...'    //loading话术
    });
    $.ajax({
        url:"/api/categoryByType",
        dataType:"json",
        success:function (data) {
            chartApiType.hideLoading();
            chartApiType.setOption(getPieOption(data,"接口信息","根据类型统计","接口类型"));
            window.onresize=chartApiType.resize;
        },
        error:function () {
            chartApiType.showLoading({
                text: '读取数据失败。'    //loading话术
            });
        }
    });
}
function apiCategoryByAccessMode() {
    let chartApiAccessMode = echarts.init(document.getElementById('chart-api-access-mode'));
    chartApiAccessMode.showLoading({
        text: '正在努力的读取数据中...'    //loading话术
    });
    $.ajax({
        url:"/api/categoryByAccessMode",
        dataType:"json",
        success:function (data) {
            chartApiAccessMode.hideLoading();
            chartApiAccessMode.setOption(getPieOption(data,"接口信息","根据访问方式统计","接口访问方式"));
            window.onresize=chartApiAccessMode.resize;
        },
        error:function () {
            chartApiAccessMode.showLoading({
                text: '读取数据失败。'    //loading话术
            });
        }
    });
}
function caseCategoryByLibId(){
    let chartCaseLib = echarts.init(document.getElementById('chart-case-lib'));
    chartCaseLib.showLoading({
        text: '正在努力的读取数据中...'   //loading话术
    });
    $.ajax({
        url:"/testCase/categoryByLibId",
        dataType:"json",
        success:function (data) {
            chartCaseLib.hideLoading();
            chartCaseLib.setOption(getPieOption(data,"用例信息","根据所属用例库统计","用例所属用例库"));
            window.onresize=chartCaseLib.resize;
        },
        error:function () {
            chartCaseLib.showLoading({
                text: '读取数据失败。'    //loading话术
            });
        }
    });
}
function caseCategoryByParaType(){
    let chartCaseParaType= echarts.init(document.getElementById('chart-case-para-type'));
    chartCaseParaType.showLoading({
        text: '正在努力的读取数据中...'    //loading话术
    });
    $.ajax({
        url:"/testCase/categoryByParaType",
        dataType:"json",
        success:function (data) {
            chartCaseParaType.hideLoading();
            chartCaseParaType.setOption(getPieOption(data,"用例信息","根据参数类型统计","参数类型"));
            window.onresize=chartCaseParaType.resize;
        },
        error:function () {
            chartCaseParaType.showLoading({
                text: '读取数据失败。'    //loading话术
            });
        }
    });
}
function libCategoryByApplyApiId(){
    let chartLibApply = echarts.init(document.getElementById('chart-lib-apply'));
    chartLibApply.showLoading({
        text: '正在努力的读取数据中...'   //loading话术
    });
    $.ajax({
        url:"/testCaseLib/categoryByApplyApiId",
        dataType:"json",
        success:function (data) {
            chartLibApply.hideLoading();
            chartLibApply.setOption(getPieOption(data,"用例库信息","根据适用接口统计","适用接口"));
            window.onresize=chartLibApply.resize;
        },
        error:function () {
            chartLibApply.showLoading({
                text: '读取数据失败。'    //loading话术
            });
        }
    });
}
function resultCategoryByTestId(){
    let chartResultTestId= echarts.init(document.getElementById('chart-result-test-id'));
    chartResultTestId.showLoading({
        text: '正在努力的读取数据中...'   //loading话术
    });
    $.ajax({
        url:"/result/categoryByTestId",
        dataType:"json",
        success:function (data) {
            chartResultTestId.hideLoading();
            chartResultTestId.setOption(getPieOption(data,"测试结果信息","根据测试编号统计","测试编号"));
            window.onresize=chartResultTestId.resize;
        },
        error:function () {
            chartResultTestId.showLoading({
                text: '读取数据失败。'    //loading话术
            });
        }
    });
}
function resultCategoryByCaseId(){
    let chartResultCaseId= echarts.init(document.getElementById('chart-result-case-id'));
    chartResultCaseId.showLoading({
        text: '正在努力的读取数据中...'    //loading话术
    });
    $.ajax({
        url:"/result/categoryByCaseId",
        dataType:"json",
        success:function (data) {
            chartResultCaseId.hideLoading();
            chartResultCaseId.setOption(getPieOption(data,"测试结果信息","根据用例编号统计","用例编号"));
            window.onresize=chartResultCaseId.resize;
        },
        error:function () {
            chartResultCaseId.showLoading({
                text: '读取数据失败。'    //loading话术
            });
        }
    });
}
function resultCategoryByAssertion(){
    let chartResultAssertion= echarts.init(document.getElementById('chart-result-assertion'));
    chartResultAssertion.showLoading({
        text: '正在努力的读取数据中...'    //loading话术
    });
    $.ajax({
        url:"/result/categoryByAssertion",
        dataType:"json",
        success:function (data) {
            chartResultAssertion.hideLoading();
            chartResultAssertion.setOption(getPieOption(data,"测试结果信息","根据断言统计","断言"));
            window.onresize=chartResultAssertion.resize;
        },
        error:function () {
            chartResultAssertion.showLoading({
                text: '读取数据失败。'    //loading话术
            });
        }
    });
}
function dataCategoryByUserId(){
    let chartDataUserId= echarts.init(document.getElementById('chart-data-user-id'));
    chartDataUserId.showLoading({
        text: '正在努力的读取数据中...'    //loading话术
    });
    $.ajax({
        url:"/data/categoryByUserId",
        dataType:"json",
        success:function (data) {
            chartDataUserId.hideLoading();
            chartDataUserId.setOption(getPieOption(data,"采集数据信息","根据用户编号统计","用户编号"));
            window.onresize=chartDataUserId.resize;
        },
        error:function () {
            chartDataUserId.showLoading({
                text: '读取数据失败。'    //loading话术
            });
        }
    });
}
function userCategoryByState(){
    let chartUserState= echarts.init(document.getElementById('chart-user-state'));
    chartUserState.showLoading({
        text: '正在努力的读取数据中...'   //loading话术
    });
    $.ajax({
        url:"/user/categoryByUserState",
        dataType:"json",
        success:function (data) {
            chartUserState.hideLoading();
            chartUserState.setOption(getPieOption(data,"用户档案","根据用户状态统计","用户状态"));
            window.onresize=chartUserState.resize;
        },
        error:function () {
            chartUserState.showLoading({
                text: '读取数据失败。'    //loading话术
            });
        }
    });
}
function userCategoryByType(){
    let chartUserType= echarts.init(document.getElementById('chart-user-type'));
    chartUserType.showLoading({
        text: '正在努力的读取数据中...'   //loading话术
    });
    $.ajax({
        url:"/user/categoryByUserType",
        dataType:"json",
        success:function (data) {
            chartUserType.hideLoading();
            chartUserType.setOption(getPieOption(data,"用户档案","根据用户类型统计","用户类型"));
            window.onresize=chartUserType.resize;
        },
        error:function () {
            chartUserType.showLoading({
                text: '读取数据失败。'    //loading话术
            });
        }
    });
}
function userCategoryByAreaCode(){
    let chartUserAreaCode= echarts.init(document.getElementById('chart-user-area-code'));
    chartUserAreaCode.showLoading({
        text: '正在努力的读取数据中...'  //loading话术
    });
    $.ajax({
        url:"/user/categoryByUserAreaCode",
        dataType:"json",
        success:function (data) {
            chartUserAreaCode.hideLoading();
            chartUserAreaCode.setOption(getPieOption(data,"用户档案","根据台区编号统计","用户台区编号"));
            window.onresize=chartUserAreaCode.resize;
        },
        error:function () {
            chartUserAreaCode.showLoading({
                text: '读取数据失败。'    //loading话术
            });
        }
    });
}

function chartInit(){
    countAllData();
    $("#modal-echart-api").click(function () {
        setTimeout(function () {
            apiCategoryByAccessMode();
            apiCategoryByType();
        },250);
    });
    $("#modal-case-echart").click(function () {
        setTimeout(function () {
            caseCategoryByLibId();
            caseCategoryByParaType();
        },250);
    });
    $("#modal-data-echart").click(function () {
        setTimeout(function () {
            dataCategoryByUserId();
        },250);
    });
    $("#modal-lib-echart").click(function () {
        setTimeout(function () {
            libCategoryByApplyApiId();
        },250);
    });
    $("#modal-result-echart").click(function () {
        setTimeout(function () {
            resultCategoryByAssertion();
            resultCategoryByCaseId();
            resultCategoryByTestId();
        },250);
    });
    $("#modal-user-echart").click(function () {
        setTimeout(function () {
            userCategoryByAreaCode();
            userCategoryByState();
            userCategoryByType();
        },250);
    });
}
chartInit();

