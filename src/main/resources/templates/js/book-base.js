 $.fn.loadingListTable = function(url,type,conlumns,formId){
     //先销毁表格
     $('#readers_table').bootstrapTable('destroy');
    return  $('#readers_table').bootstrapTable({
        url: url,                      //请求后台的URL（*）
        method: type,                      //请求方式（*）
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        //得到查询的参数
        queryParams : function (params) {

            //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
            const temp =  $.fn.serializeJsonObject(formId);
            temp["rows"] = params.limit;
            temp["page"] = (params.offset / params.limit) + 1;
            temp["sort"] = params.sort;
            temp["sortOrder"] =  params.order;
            console.info(temp);
            return temp;
        },
        columns: conlumns,
        //选中行操作
        onDblClickRow: function (row, $element) {
            const id = row.ID;
        },
    });
}


 //自定义函数处理queryParams的批量增加
 $.fn.serializeJsonObject = function (formId) {
     var json = {};
     var form = $("#"+formId).serializeArray();
     $.each(form, function () {
         //如果值为空则直接跳过!
         if(this.value!="" && !this.value != undefined){
             if (json[this.name]) {
                 if (!json[this.name].push) {
                     json[this.name] = [json[this.name]];
                 }
                 json[this.name].push();
             } else {
                 json[this.name] = this.value || '';
             }
         }
     });
     return json;
 }


 //成功后提示框

 $.fn.success= function(message){
     swal({
         title: "成功",
         text: message,
         type: "success"
     });
 }

 $.fn.error= function(message){
     swal({
         title: "出现问题",
         text: message,
         type: "error"
     });
 }

 //warning 提示框
 $.fn.notification = function(title,message,fn){
     swal({
             title: title,
             text:message,
             type: "warning",
             showCancelButton: true,
             confirmButtonColor: "#DD6B55",
             confirmButtonText: "确认",
             cancelButtonColor:"#1ab394",
             cancelButtonText: "取消",
             closeOnConfirm: false,
             closeOnCancel: true
         },
         fn);
 }

