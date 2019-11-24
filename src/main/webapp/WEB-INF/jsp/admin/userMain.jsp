<%--
  Created by IntelliJ IDEA.
  User: 童材
  Date: 2019/11/22
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户管理</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
</head>
<body>
<div class="page-header">
    <h1><small><a href="/admin/adminMain">admin账号管理</a></small>
        <a href="/admin/userMain">用户管理</a>
        <small><a href="/admin/getAllPaper?pageNo=1">审核问卷</a></small>

    </h1>
</div>

<!--add按钮-->
<button type="button" id="btn_add" class="btn btn-primary" data-toggle="modal">add</button>


<!--数据列表-->
<table class="table table-hover">
    <!--搜索框-->
    <div class="col-sm-4">
        <div class="input-group">
            <input type="text" id="searchWord" class="form-control" onkeydown="onKeyDown(event)"/>
            <span class="input-group-addon"><a id="search"><i class="glyphicon glyphicon-search"> <span>搜索   </span></i></a></span>
        </div>
        <div class="col-sm-4">
            <thead>
            <tr>
                <th>id</th>
                <th>username</th>
                <th>email</th>
                <th>冻结</th>
                <%--                <th>删除</th>--%>
                <th>修改</th>
            </tr>
            </thead>
            <tbody id="adminList">
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                        <%--                    <c:choose>--%>
                    <c:if test="${user.isActive == true}">
                        <td><a class="btn btn-primary" href="/admin/isActive?isActive=0&userId=${user.id}">冻结</a></td>
                    </c:if>
                    <c:if test="${user.isActive == false}">
                        <td><a class="btn btn-primary" href="/admin/isActive?isActive=1&userId=${user.id}">解冻</a></td>
                    </c:if>
                        <%--                    </c:choose>--%>
                        <%--                    <td><a class="btn btn-primary" onClick="delcfm('http://localhost:8090/admin/deleteUser?id=${user.id}')">delete</a></td>--%>
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal"
                                data-id="${user.id}" data-username="${user.username}"
                                data-password="${user.password}" data-email="${user.email}">edit
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
</table>

<!--分页-->
<a href="/admin/userMain?pageNumber=1">首页</a>
<a href="/admin/userMain?pageNumber=${pageNo-1}">上一页</a>
<a href="/admin/userMain?pageNumber=${pageNo+1}">下一页</a>
<a href="/admin/userMain?pageNumber=${maxPage}">尾页</a>
<p style='display: inline;'>共${maxPage}页${totalCount}条数据，当前第${pageNo}页</p>

<!--用户账号修改弹出框-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">edit</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="adminId"/>
                <div class="form-group">
                    <label for="txt_departmentname">username</label>
                    <input type="text" name="txt_departmentname" class="form-control" id="txt_departmentname">
                </div>
                <div class="form-group">
                    <label for="txt_parentdepartment">password</label>
                    <input type="text" name="txt_parentdepartment" class="form-control" id="txt_parentdepartment">
                </div>
                <div class="form-group">
                    <label for="txt_departmentlevel">email</label>
                    <input type="text" name="txt_departmentlevel" class="form-control" id="txt_departmentlevel">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span
                        class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                </button>
                <button type="button" id="btn_submit" class="btn btn-primary"><span
                        class="glyphicon glyphicon-floppy-disk"></span>保存
                </button>
            </div>
        </div>
    </div>
</div>

<!--用户账号增加弹出框-->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel1">add</h4>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <label for="txt_departmentname">username</label>
                    <input type="text" name="txt_departmentname" class="form-control" id="add_username">
                </div>
                <div class="form-group">
                    <label for="txt_parentdepartment">password</label>
                    <input type="text" name="txt_parentdepartment" class="form-control" id="add_password">
                </div>
                <div class="form-group">
                    <label for="txt_departmentlevel">email</label>
                    <input type="text" name="txt_departmentlevel" class="form-control" id="add_email">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span
                        class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                </button>
                <button type="button" id="btn-addAdmin" class="btn btn-primary" data-dismiss="modal"><span
                        class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>增加
                </button>
            </div>
        </div>
    </div>
</div>

<input type="hidden" id="searchMsg" value="${msg}"/>
<input type="hidden" id="pageNo" value="${pageNo}"/>
<!-- 管理员账号删除确认 -->
<div class="modal fade" id="delcfmModel">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p>您确认要删除吗？</p>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="url"/>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <a onclick="urlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
    //确认删除提示
    function delcfm(url) {
        $('#url').val(url);//给会话中的隐藏属性URL赋值
        $('#delcfmModel').modal();
    }

    function urlSubmit() {
        var url = $.trim($("#url").val());//获取会话中的隐藏属性URL
        toastr.success("删除成功！");
        window.location.href = url;
    }


    $("#btn_add").click(function () {
        $('#myModal1').modal('show');
    });

    <!--用户账号增加ajax提交-->
    $("#btn-addAdmin").click(function () {

        var username = $('#add_username').val();
        var password = $('#add_password').val();
        var email = $('#add_email').val();

        var i = 0;
        //**用户名验证
        if (username == null || username == "") {
            toastr.warning("用户名不能为空");
            i++;
        }


        if (username.length < 4 || username.length > 10) {
            toastr.warning("用户名长度：4~10");
            i++;
        }

        //密码验证
        if (password == null || password == "") {
            toastr.warning("密码不能为空");
            i++;
        }


        if (password.length < 6 || password.length > 20) {
            toastr.warning("密码长度：6~20");
            i++;
        }
        //正则表达式邮箱格式验证
        var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if (!reg.test(email)) {
            toastr.warning("不是有效的邮箱");
            i++;
        }

        if (i > 0) {
            return false;
        } else {
            // toastr.success("增加成功！")
        }

        var data = {id: null, username: username, password: password, image: null, email: email, isActive: 1};
        console.log(data);

        //提交
        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url: "http://localhost:8090/admin/addUser",
            //数据，json字符串
            data: JSON.stringify(data),
            //请求成功
            success: function (result) {
                console.log(result);
                var pageNo = $("#pageNo").val();
                alert("增加成功!");
                window.location.href = "http://localhost:8090/admin/userMain?pageNumber=" + pageNo;

            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
                toastr.error("当前用户名已经存在");
            }
        });


    });
    //用户账号修改，将用户信息的值赋给模态框显示
    $('#myModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var username = button.data('username');
        var password = button.data('password');
        var email = button.data('email');
        var id = button.data('id');// Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        console.log(username);
        console.log(password);
        console.log(email);
        $('#adminId').val(id);
        $('#txt_departmentname').val(username);
        $('#txt_parentdepartment').val(password);
        $('#txt_departmentlevel').val(email)
    });


    <!--用户账号修改ajax提交-->
    $('#btn_submit').click(function () {

        var id = $('#adminId').val();
        var username = $('#txt_departmentname').val();
        var password = $('#txt_parentdepartment').val();
        var email = $('#txt_departmentlevel').val();
        var i = 0;
        //**用户名验证
        if (username == null || username == "") {
            toastr.warning("用户名不能为空");
            i++;
        }


        if (username.length < 4 || username.length > 10) {
            toastr.warning("用户名长度：4~10");
            i++;
        }

        //密码验证
        if (password == null || password == "") {
            toastr.warning("密码不能为空");
            i++;
        }


        if (password.length < 6 || password.length > 20) {
            toastr.warning("密码长度：6~20");
            i++;
        }
        //正则表达式邮箱格式验证
        var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if (!reg.test(email)) {
            toastr.warning("不是有效的邮箱");
            i++;
        }

        if (i > 0) {
            return false;
        } else {
            // toastr.success("修改成功！")
        }

        var data = {"id": id, "username": username, "password": password, "image": null, "email": email};
        console.log(data);
        //提交
        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url: "http://localhost:8090/admin/editUser",
            //数据，json字符串
            data: JSON.stringify(data),
            //请求成功
            success: function (result) {
                console.log(result);
                var pageNo = $("#pageNo").val();
                alert("修改成功！");
                window.location.href = "http://localhost:8090/admin/userMain?pageNumber=" + pageNo;

            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
                toastr.error("当前用户名已经存在");
            }
        });

        $("#myModal").modal('hide');

    });

    //消息显示样式
    toastr.options = {

        "closeButton": false, //是否显示关闭按钮

        "debug": false, //是否使用debug模式

        "positionClass": "toast-top-center",//弹出窗的位置

        "showDuration": "300",//显示的动画时间

        "hideDuration": "1000",//消失的动画时间

        "timeOut": "5000", //展现时间

        "extendedTimeOut": "1000",//加长展示时间

        "showEasing": "swing",//显示时的动画缓冲方式

        "hideEasing": "linear",//消失时的动画缓冲方式

        "showMethod": "fadeIn",//显示时的动画方式

        "hideMethod": "fadeOut" //消失时的动画方式
    };

    //enter触发搜索事件
    function onKeyDown(event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) { // enter 键
            var keyWord = $('#searchWord').val();
            if (keyWord == "" | keyWord == null) {
                window.location.href = "http://localhost:8090/admin/userMain";
            } else {
                window.location.href = "http://localhost:8090/admin/searchUser?keyWord=" + keyWord;
            }
        }
    }

    $().ready(function () {
        var msg = $('#searchMsg').val();
        console.log(msg);
        if (msg == 1) {
            toastr.info("无搜索结果，请重新输入关键词！");
        }
        if (msg == 2) {
            toastr.success("搜索成功！");
        }


    });
</script>

</body>
</html>
