
//table 加载对象用于条件查询！
var $table;

const url = "http://127.0.0.1:9091";

//请求后台地址 定义为全局的方便调用！
const queryUrl =url+ '/readers/findReadersBlackList';
/**
 * js加载时执行函数
 */
$().ready(function () {

    //数据加载初始化
    $table= InitMainTable();

    $("#readers_btn_query").click(function () {
        conditionalSearch();
    })

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
            field: 'readersName',
            title: '读者姓名'
        },{
            field: 'readersPhone',
            title: '联系方式'
        },{
            field: 'idCard',
            title: '身份证号'
        },{
            field: 'blacklistDescription',
            title: '拉黑原因'
        },
        {
            field: 'isContact',
            title: '是否知情'
        },{
            field: 'readersState',
            title: '状态',
            formatter:function (value,row,index) {
                if(value == "AVAILABLE")
                    return "正常";
                else if(value== "BLACKLIST")
                    return "黑名单";
            }
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
    result += "<a href='javascript:;' class='btn btn-xs red' onclick=\"moveReaders('" + id + "')\" title='移出黑名单'><span class='glyphicon glyphicon-share-alt'></span></a>";
    return result;
}

/**
 * 将读者从黑名单中移出,根据id
 * @param id
 */
function moveReaders(id) {
    //调用封装好的移出提示！
    $.fn.notification("您确定要移出该读者么？","移出后读者将恢复正常，请谨慎操作！",
        function (isConfirm) {
            //确认移出
            if(isConfirm){

                //请求后台开始移出操作！
                $.ajax({
                    url: url+ '/readers/moveBlackList',
                    type:"POST",
                    data:{id:id},
                    dataType:"json",
                    success: function(data){
                        //移出成功
                        if(data.code==200){
                            //提示
                            $.fn.success(data.message);
                            //刷新表格
                            conditionalSearch();
                            return;
                        }
                        //移出失败
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
