//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
$.validator.setDefaults({
    highlight: function (element) {
        $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
    },
    success: function (element) {
        element.closest('.form-group').removeClass('has-error').addClass('has-success');
    },
    errorElement: "span",
    errorPlacement: function (error, element) {
        if (element.is(":radio") || element.is(":checkbox")) {
            error.appendTo(element.parent().parent().parent());
        } else {
            error.appendTo(element.parent());
        }
    },
    errorClass: "help-block m-b-none",
    validClass: "help-block m-b-none"


});
var validate;
//以下为官方示例
$().ready(function () {

    //当点击提交时，校验表单！
    $("#readers_add_submit").click(function (event) {
        console.info("组织！");
        event.preventDefault();
        formValidate();
    });

    //初始化表单验证规则
    validate=initValidate();
});

//初始化表单验证规则！
initValidate = function(){
    // validate signup form on keyup and submit
    const icon = "<i class='fa fa-times-circle'></i> ";

    return $("#signupForm").validate({
        rules: {
            readersName: {
                required:true,
                minlength:2
            },
            idCard: {
                required:true,
                minlength:18,
                maxlength:18
            },
            nativePlace: {
                required: true,
                minlength: 2
            },
            password: {
                required: true,
                minlength: 8
            },
            confirm_password: {
                required: true,
                minlength: 8,
                equalTo: "#password"
            },
            readersPhone: {
                required: true
            },
            topic: {
                required: "#newsletter:checked",
                minlength: 2
            },
            agree: "required"
        },
        messages: {
            readersName:{
                required :icon+ "请输入您的姓名",
                minlength: icon + "姓名最少为两个字!"
            } ,
            idCard:{
                required :icon+ "请输入您的身份证号",
                minlength: icon + "身份证号过短请检查!",
                maxlength: icon+"身份证号过长请检查!"
            } ,
            nativePlace: {
                required: icon + "请输入您的用户名",
                minlength: icon + "用户名必须两个字符以上"
            },
            password: {
                required: icon + "请输入您的密码",
                minlength: icon + "密码必须8个字符以上"
            },
            confirm_password: {
                required: icon + "请再次输入密码",
                minlength: icon + "密码必须8个字符以上",
                equalTo: icon + "两次输入的密码不一致"
            },
            readersPhone: {
                required:icon + "请输入您的联系电话"
            },
            agree: {
                required: icon + "必须同意协议后才能注册",
                element: '#agree-error'
            }
        }
    });
}



//校验form表单！
formValidate = function () {

    if(validate.form()){
        $.ajax({
            url: "http://127.0.0.1:9091/readers/addReaders",
            type:"POST",
            data:$("#signupForm").serialize(),
            dataType:"json",
            success: function(data){

                //成功后提示框
                swal({
                    title: "成功",
                    text: data.message,
                    type: "success"
                });

                $(':input','#signupForm').val("");

            },
            error:function (XMLHttpRequest) {
                console.info(XMLHttpRequest);
            }
        });
    }



}