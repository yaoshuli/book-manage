
//table 加载对象用于条件查询！
var $table;

const url = "http://127.0.0.1:9091";

//请求后台地址 定义为全局的方便调用！
const queryUrl =url+ '/purchase/findPurchase';
/**
 * js加载时执行函数
 */
$().ready(function () {

    //数据加载初始化
    $table= InitMainTable();

    //点击查询事件
    $("#readers_btn_query").click(function () {
        conditionalSearch();
    });



    //工具条点击事件
    toolBarClick();
});

/**
 * 条件检索操作函数
 */
function conditionalSearch(){
    $table.bootstrapTable("refresh");
}

/**
 * 工具栏操作函数
 */
function toolBarClick(){


}

/**
 * 表格初始化加载函数
 * @returns {*}
 * @constructor
 */
function InitMainTable () {
    const titles= [{
        checkbox: true
    }, {
        field: 'purchaseName',
        title: '采购计划名称'
    },{
        field: 'purchaseDescription',
        title: '采购描述'
    },{
        field: 'purchaseNumber',
        title: '采购数量'
    },{
        field: 'purchaseMoney',
        title: '采购金额'
    },{
        field: 'purchase_state',
        title: '采购状态',
        formatter:function (value,row,index) {
            if(value=="EXECUTING")
                return "采购中";
            else if(value=="UNCHECKED")
                return "待核对";
            else
                return "已核对";
        }
    },{
        field: 'principalName',
        title: '负责人'
    },{
        field: 'principalPhone',
        title: '负责人电话'
    },{
        field: 'gmtCreate',
        title: '创建时间'
    }, {
        field:'id',
        title: '操作',
        width: 120,
        align: 'center',
        valign: 'middle',
        formatter:actionFormatter
    }, ];



    //调用公共js中的加载table数据方法
    //参数1：后台请求地址
    //参数2：请求类型
    //参数3：表格titles及对照columns
    //参数4：条件查询表单
    return  $.fn.loadingListTable(queryUrl,'GET',titles,"formSearch");
};

/**
 * 操作栏初始化函数
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function actionFormatter(value, row, index) {
    const id = value;
    var result = "";
    result += "<a href='javascript:;' class='btn btn-xs btn-info' onclick=\"+('" + id + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>&nbsp;&nbsp;&nbsp;&nbsp;";
    result += "<a href='javascript:;' class='btn btn-xs btn-danger' onclick=\"removeReaders('" + id + "')\" title='删除'><span class='glyphicon glyphicon-trash'></span></a>";

    return result;
}

/**
 * 删除读者信息，根据id
 * @param id
 */
function removeReaders(id) {
    //调用封装好的删除提示！
    $.fn.notification("您确定要删除这条信息吗？","删除后将无法恢复，请谨慎操作！",function (isConfirm) {

        //确认删除
        if(isConfirm){

            //请求后台开始删除操作！
            $.ajax({
                url: url+ '/readers/removeReaders',
                type:"POST",
                data:{id:id},
                dataType:"json",
                success: function(data){
                    //删除成功
                    if(data.code==200){
                        //提示
                        $.fn.success(data.message);
                        //刷新表格
                        conditionalSearch();
                        return;
                    }
                    //删除失败
                    $.fn.error(data.message);
                },
                error:function (XMLHttpRequest) {
                    //请求出现异常！
                    $.fn.error("请求出现异常！");
                }
            });
        }
    });
}

