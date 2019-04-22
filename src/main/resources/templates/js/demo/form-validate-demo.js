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

        //以下为官方示例
        $().ready(function () {
            // validate the comment form when it is submitted
            $("#commentForm").validate();

            // validate signup form on keyup and submit
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({
                rules: {
                    readersName: {
                        required:true,
                        minlength:2
                    },
                    idCard: {
                        required:true,
                        minlength:18
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
                    phone: {
                        required: true,
                        phone: true
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
                         minlength: icon + "身份证号为18位请检查!"
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
                    phone: icon + "请输入您的联系电话",
                    agree: {
                        required: icon + "必须同意协议后才能注册",
                        element: '#agree-error'
                    }
                }
            });

            // propose username by combining first- and lastname
            $("#username").focus(function () {
                var readers_name = $("#readers_name").val();
                var id_card = $("#id_card").val();
                if (readers_name && id_card && !this.value) {
                    this.value = readers_name + "." + id_card;
                }
            });
        });
