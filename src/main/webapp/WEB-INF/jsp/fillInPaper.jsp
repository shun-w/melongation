<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<html>
<head>
    <title>问卷</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>

        window.onload = function (ev) {
            setTimeout(function () {
                console.log("开始");

                var elements = document.getElementsByName("aa");
                for (var index = 0; index < elements.length; index++) {
                    var obj = JSON.parse(elements[index].innerHTML);
                    var content = "";
                    for (var i in obj) {
                        content = content + i + " <br>";

                        for (var j = 0; j < obj[i].length; j++) {

                            for (var k in obj[i][j]) {
                                content = content + "<br>" + "<input name=\"answer\" type=\"radio\" value=" + k + ">" + k + ". " + obj[i][j][k] + "<br>";

                            }
                        }

                    }
                    // console.log(index + "为" + content);
                    elements[index].innerHTML = content;
                }

                // /
                var elements = document.getElementsByName("cc");
                for (var index = 0; index < elements.length; index++) {
                    var obj = JSON.parse(elements[index].innerHTML);
                    var content = "";
                    for (var i in obj) {
                        content = content + i + " <br>";

                        for (var j = 0; j < obj[i].length; j++) {

                            for (var k in obj[i][j]) {
                                content = content + "<br>" + "<input name=\"answer\" type=\"checkbox\" value=" + k + ">" + k + ". " + obj[i][j][k] + "<br>";

                            }
                        }

                    }
                    // console.log(index + "为" + content);
                    elements[index].innerHTML = content;
                }

            }, 2000)


        };


    </script>

</head>

<body>

<div style="width:70%;margin: auto;padding: auto">

    <h3 style="display: inline">问卷标题： </h3><c:out value="${paper.title}"/>

    <br/>
    <br>
    <h3 style="display: inline">问卷描述： </h3>
    <br><c:out value="${paper.description}"/><br/><br>
    <hr/>
    <br/>
    <h4>问卷填写:</h4>
    <table id="table">
        <c:forEach items="${paper.questionList}" var="question">
            <tr>
                <td>${question.orderNumber}.
                    <c:if test="${question.type == 1}">
                        单选题
                    </c:if>
                    <c:if test="${question.type == 2}">
                        多选题
                    </c:if>
                    <c:if test="${question.type == 3}">
                        问答题
                    </c:if>
                    (
                    <c:if test="${question.mustAnswer == true}">必答题</c:if>
                    <c:if test="${question.mustAnswer == false}">非必答题目</c:if>)
                </td>

            </tr>

            <tr>
                <td>


                    <c:if test="${question.type == 1}">

                        <form action="/${paper.id}" method="post">

                            <div name="aa">${question.content}</div>
                            <input type="hidden" name="questionId" value="${question.id}">

                        </form>
                    </c:if>

                    <c:if test="${question.type == 2}">
                        <form action="/${paper.id}" method="post">

                            <div name="cc">${question.content}</div>
                            <input type="hidden" name="questionId" value="${question.id}">

                        </form>
                    </c:if>

                    <c:if test="${question.type == 3}">
                        <form action="/${paper.id}" method="post">

                            <div name="bb" style="">${question.content}</div>
                            <textarea class="form-control" rows="10" name="answer"></textarea>
                            <input type="hidden" name="questionId" value="${question.id}">

                        </form>
                    </c:if>


                </td>

            </tr>

        </c:forEach>
    </table>
    <br>
    <input type="button" value="提交问卷" onclick="submitForm()">


</div>

<script>
    function submitForm() {

        console.log("开始处理");
        var results = new Array();
        var cnt = 0;
        //处理单选题
        var radioDiv = document.getElementsByName("aa");
        for (var i = 0; i < radioDiv.length; i++) {
            results[cnt++] = new Object();
            var n = radioDiv[i].childNodes;
            var answer1 = "";
            for (var j = 0; j < n.length; j++) {

                if (n[j].name == "answer" && n[j].checked == true) {

                    answer1 = answer1 + n[j].value;
                }
            }
            results[cnt - 1].answer = answer1;
            results[cnt - 1].questionId = radioDiv[i].nextElementSibling.value;
        }

        ///处理多选
        var radioDiv = document.getElementsByName("cc");
        for (var i = 0; i < radioDiv.length; i++) {
            results[cnt++] = new Object();
            var n = radioDiv[i].childNodes;
            var answer1 = "";
            for (var j = 0; j < n.length; j++) {

                if (n[j].name == "answer" && n[j].checked == true) {

                    answer1 = answer1 + n[j].value;
                }
            }
            results[cnt-1].answer = answer1;
            results[cnt-1].questionId = radioDiv[i].nextElementSibling.value;

        }

        //处理问答题
        var radioDiv = document.getElementsByName("bb");
        for (var i = 0; i < radioDiv.length; i++) {
             results[cnt++] = new Object();
            var n = radioDiv[i].childNodes;
            var answer1 = radioDiv[i].nextElementSibling.value;

            results[cnt-1].answer = answer1;
            results[cnt-1].questionId = radioDiv[i].nextElementSibling.nextElementSibling.value;


        }
        console.log("results:"+JSON.stringify(results));
        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url: "http://localhost:8090/fillPaper",
            //数据，json字符串
            data: JSON.stringify(results),
            //请求成功
            success: function (e) {
                alert(e)
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e);
            }
        });

    }

</script>
</body>
</html>
