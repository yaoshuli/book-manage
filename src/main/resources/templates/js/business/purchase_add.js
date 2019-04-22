
var validatePurchase;
var validateOrderBook;

/**
 * js加载时执行函数
 */
$().ready(function () {

    //修改验证规则
    initValidateConfig();

    //初始化表单验证规则
    validatePurchase=initValidatePurchase();
    validateOrderBook = initValidateOrderBook();

    //当点击提交时，校验表单！
    $("#purchase_add_submit").click(function (event) {
        event.preventDefault();
        formValidate();
    });

    //点击生成时 批量生成书籍信息填写框
    $("#generate").click(function () {

        var number = $("#bookNumber").val();
        console.info(number);
        generateBookInfo(number);
    });

});

/**
 * 校验form表单！
 */
formValidate = function () {

    //判断校验是否通过
    if (validateOrderBook.form()&&validatePurchase.form()  ) {

        //获取采购计划信息
        var purchaseForm= $("#purchaseForm").serialize();
        const bookFormContext =  $("#bookForm").serialize().toString();

        var orderBookArray=new Array();
        const pArray = bookFormContext.split('&');
        const bookInfoLength = $("[name=bookInfo]").length;
        var index= 0;
        for (let i = 0; i < bookInfoLength; i++) {
            const orderBookArrayElement = orderBookArray[i];
            orderBook=new Object();
            orderBook.orderBookName = pArray[index].split("=")[1];
            orderBook.orderBookAuthor = pArray[index+1].split("=")[1];
            orderBook.orderBookNumber = pArray[index+2].split("=")[1];
            orderBook.orderBookType = pArray[index+3].split("=")[1];
            orderBookArray[i] = orderBook;
            index = index+4;
        }

        const str = "&orderBookStr="+JSON.stringify(orderBookArray);

        purchaseForm = purchaseForm+""+str;

        console.info(purchaseForm);
        $.ajax({
            url: "http://127.0.0.1:9091/purchase/addPurchase",
            type:"POST",
            data:purchaseForm,
            dataType:"json",
            success: function(data){
                //成功后提示框
                swal({
                    title: "成功",
                    text: data.message,
                    type: "success"
                });

                $(':input','#purchaseForm').val("");
                $(':input','#bookForm').val("");

            },
            error:function (XMLHttpRequest) {
                console.info(XMLHttpRequest);
            }
        });
    }

}

/**
 * jquery.validate 配置修改
 */
initValidateConfig = function(){

    //配置jquery.validate 使其可以进行同name下所有框都校验
    //不配置默认只校验一个
    if ($.validator) {
        $.validator.prototype.elements = function () {
            var validator = this,
                rulesCache = {};
            return $(this.currentForm)
                .find("input, select, textarea")
                .not(":submit, :reset, :image, [disabled]")
                .not(this.settings.ignore)
                .filter(function () {
                    if (!this.name && validator.settings.debug && window.console) {
                        console.error("%o has no name assigned", this);
                    }
                    rulesCache[this.name] = true;
                    return true;
                });
        }
    }
}


/**
 * 初始订单书籍验证规则！
 * @returns {*|jQuery}
 */
initValidateOrderBook = function(){
    // validate signup form on keyup and submit
    const icon = "<i class='fa fa-times-circle'></i> ";

    return $("#bookForm").validate({
        rules: {
            orderBookName: {
                required:true,
                minlength:1
            },
            orderBookAuthor: {
                required:true,
                minlength:2
            },
            orderBookNumber: {
                required: true,
                minlength: 1
            },
            orderBookType: {
                required: true
            }
        },
        messages: {
            purchaseName:{
                required :icon+ "请输入书籍名称!",
                minlength: icon + "书籍最少为一个字!"
            } ,
            principalName:{
                required :icon+ "请输入作者姓名!",
                minlength: icon + "姓名最少为两个字!"
            } ,
            purchaseDescription: {
                required: icon + "请输入购书数量!",
                minlength: icon + "购书数量最少为1!"
            },
            principalPhone: {
                required:icon + "请选择部类!"
            }
        }
    });
}
/**
 * 初始化采购计划验证规则！
 * @returns {*|jQuery}
 */
initValidatePurchase = function(){
    // validate signup form on keyup and submit
    const icon = "<i class='fa fa-times-circle'></i> ";

    return $("#purchaseForm").validate({
        rules: {
            purchaseName: {
                required:true,
                minlength:2
            },
            principalName: {
                required:true,
                minlength:2
            },
            purchaseDescription: {
                required: true,
                minlength: 10
            },
            principalPhone: {
                required: true
            }
        },
        messages: {
            purchaseName:{
                required :icon+ "请输入您的采购计划名称",
                minlength: icon + "采购计划最少为两个字!"
            } ,
            principalName:{
                required :icon+ "请输入您的姓名",
                minlength: icon + "姓名最少为两个字!"
            } ,
            purchaseDescription: {
                required: icon + "请输入您的计划描述",
                minlength: icon + "计划描述最少十个字"
            },
            principalPhone: {
                required:icon + "请输入您的联系电话"
            }
        }
    });
}

/**
 * 根据btn自身找到第四层父类div 删除
 * @param btn
 */
function removeBookInfo(btn){

   var bookInfoDiv =  $(btn).parent().parent().parent().parent();

   bookInfoDiv.remove();
}


/**
 * 生成书籍信息框
 * @param number    生成数量
 */
function generateBookInfo(number) {
    var html = "";
    for (var i=0;i<number;i++)
    {
        html+="<div class=\"form-group\" name=\"bookInfo\">\n" +
            "     <label class=\"col-sm-2 control-label\">书籍信息</label>\n" +
            "     <div class=\"col-sm-10\">\n" +
            "         <div class=\"row\">\n" +
            "             <div class=\"col-md-2\">\n" +
            "                 <input type=\"text\" placeholder=\"书籍名称\" name=\"orderBookName\" class=\"form-control\">\n" +
            "             </div>\n" +
            "             <div class=\"col-md-2\">\n" +
            "                 <input type=\"text\" placeholder=\"作者名称\" name=\"orderBookAuthor\" class=\"form-control\">\n" +
            "             </div>\n" +
            "             <div class=\"col-md-2\">\n" +
            "                 <input type=\"text\" placeholder=\"订购数量\" name=\"orderBookNumber\" class=\"form-control\">\n" +
            "             </div>\n" +
            "             <div class=\"col-md-2\">\n" +
            "                <select class=\"form-control m-b\" name=\"orderBookType\">" +
            "                    <option value=\"MLZY_MZDSX\">马列主义毛泽东思想</option>" +
            "                    <option value=\"ZX\">哲学</option>" +
            "                    <option value=\"SHKX\">社会科学</option>" +
            "                    <option value=\"ZRKX\">自然科学</option>" +
            "                    <option value=\"ZHXTS\">综合性图书</option>" +
            "                </select>" +
            "             </div>\n" +
            "             <div class=\"col-md-2\">\n" +
            "                 <a class=\"btn btn-primary btn-danger\" href=\"#\" onclick=\"removeBookInfo(this)\">\n" +
            "                     <i class=\"fa fa-remove\"></i>\n" +
            "                 </a>\n" +
            "             </div>\n" +
            "         </div>\n" +
            "     </div>\n" +
            " </div>"
    }
    $("#bookForm").append(html);

    //每次动态生成 重新绑定校验
    validateOrderBook = initValidateOrderBook();
}


