<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>添加问卷</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
    </script>
    <script>


        $(document).ready(
            function () {
                var questionNumber = -1;
                var questions = new Array();//存
                var contents = new Array();

                function changeNumberToChar(number) {
                    if (number == 1) return "A";
                    else if (number == 2) return "B";
                    else if (number == 3) return "C";
                    else if (number == 4) return "D";
                    else if (number == 5) return "E";
                    else if (number == 6) return "F";
                    else if (number == 7) return "G";
                    else if (number == 8) return "H";
                    else if (number == 9) return "I";
                    else if (number == 10) return "J";
                    else if (number == 11) return "K";
                    else if (number == 12) return "L";
                    else if (number == 13) return "M";
                    else if (number == 14) return "N";
                    else if (number == 15) return "O";
                    else if (number == 16) return "P";
                    else if (number == 17) return "Q";
                    else if (number == 18) return "R";
                    else return "";

                }


                //点击添加一个单选题
                $("#addSingleSelection").click(function () {

                    questionNumber++;

                    //存数据
                    questions[questionNumber] = new Object();


                    questions[questionNumber].type = 1;
                    questions[questionNumber].paperId = $("#paperId").val();
                    questions[questionNumber].orderNumber = questionNumber + 1;


                    //这个问题的div
                    var question = $("<div/>", {
                        id: "question" + questionNumber,
                        order: questionNumber,
                        css: {
                            border: "2px solid #000"
                        }
                    });


                    //标题
                    var title = document.createElement("p");
                    title.id = "title" + questionNumber;
                    while (true) {
                        var titleContent = prompt("请输入问题");
                        if (!(titleContent == null || titleContent == "")) {
                            title.innerText = (questionNumber + 1) + ". " + titleContent + "(单选题)";

                            //存
                            contents[questionNumber] = "{" + "\"" + titleContent + "\":[";

                            break;
                        }
                    }

                    //添加选项按钮
                    var selectNumber = 0;
                    var addSubSelect = $("<input>", {
                        type: "button",
                        id: "addSubSelect" + questionNumber,
                        value: "添加选项",
                        click: function () {
                            question.append($("<br>"));
                            selectNumber++;

                            var select;
                            while (true) {
                                select = prompt("请输入选项");
                                if (!(select == null || select == "")) {
                                    break;
                                }
                            }

                            var num = changeNumberToChar(selectNumber);

                            //存
                            if (num == "A") {

                                contents[question.attr("order")] = contents[question.attr("order")] + "{\"" + num + "\"" + ":" + "\"" + select + "\"}";

                            } else {

                                contents[question.attr("order")] = contents[question.attr("order")] + "," + "{\"" + num + "\"" + ":" + "\"" + select + "\"}";

                            }


                            var selectItemRadio = $("<input>", {
                                type: "radio",
                                value: select,
                                name: titleContent,
                                style: "display:inline"
                            });


                            var selectValueText = $("<p>", {
                                text: num + ". " + select,
                                style: "display:inline"
                            });
                            selectItemRadio.appendTo(question);
                            selectValueText.appendTo(question);

                        }
                    });


                    //删除这个问题
                    var deleteQuestion = $("<input>", {
                        type: "button",
                        value: "删除该题",
                        click: function () {
                            if (questionNumber == question.attr("order")) {
                                question.remove();

                                for (var key in questions[questionNumber]) {
                                    delete questions[questionNumber][key];
                                }
                                questionNumber--;
                            } else {
                                alert("当前仅支持删除最后一题");
                                console.log("questionNumber: " + questionNumber + ",question.order:" + question.attr("order"));
                            }
                        }
                    });

                    //是否必答
                    var mustAnswer = $("<input>", {
                        type: "radio",
                        name: "mustAnswer" + questionNumber,
                        value: "true",

                        click: function () {
                            questions[question.attr("order")].mustAnswer = true;
                        }
                    });

                    var mustAnswerText = $("<label>", {
                        text: "必答"
                    });
                    var notMustAnswer = $("<input>", {
                        type: "radio",
                        name: "mustAnswer" + questionNumber,
                        value: "false",
                        click: function () {
                            questions[question.attr("order")].mustAnswer = false;
                        }

                    });
                    var notMustAnswerText = $("<label>", {
                        text: "非必答"
                    });


                    mustAnswer.appendTo(question);
                    mustAnswerText.appendTo(question);
                    notMustAnswer.appendTo(question);
                    notMustAnswerText.appendTo(question);

                    question.append(title);
                    addSubSelect.appendTo(question);
                    deleteQuestion.appendTo(question);
                    $("#paperMain").append(question);
                    question.append("<hr>");


                });

                //点击添加一个多选题
                $("#addMultipleSelection").click(function () {


                    questionNumber++;

                    //存数据
                    questions[questionNumber] = new Object();


                    questions[questionNumber].type = 2;
                    questions[questionNumber].paperId = $("#paperId").val();
                    questions[questionNumber].orderNumber = questionNumber + 1;


                    //这个问题的div
                    var question = $("<div/>", {
                        id: "question" + questionNumber,
                        order: questionNumber,
                        css: {
                            border: "2px solid #000"
                        }
                    });


                    //标题
                    var title = document.createElement("p");
                    title.id = "title" + questionNumber;
                    while (true) {
                        var titleContent = prompt("请输入问题");
                        if (!(titleContent == null || titleContent == "")) {
                            title.innerText = (questionNumber + 1) + ". " + titleContent + "(多选题)";

                            //存
                            contents[questionNumber] = "{" + "\"" + titleContent + "\":[";
                            // contents[questionNumber] = "{" + "" + titleContent + ":[";
                            break;
                        }
                    }

                    //添加选项按钮
                    var selectNumber = 0;
                    var addSubSelect = $("<input>", {
                        type: "button",
                        id: "addSubSelect" + questionNumber,
                        value: "添加选项",
                        click: function () {
                            question.append($("<br>"));
                            selectNumber++;

                            var select;
                            while (true) {
                                select = prompt("请输入选项");
                                if (!(select == null || select == "")) {
                                    break;
                                }
                            }

                            var num = changeNumberToChar(selectNumber);

                            //存
                            if (num == "A") {

                                contents[question.attr("order")] = contents[question.attr("order")] + "{\"" + num + "\"" + ":" + "\"" + select + "\"}";

                            } else {

                                contents[question.attr("order")] = contents[question.attr("order")] + "," + "{\"" + num + "\"" + ":" + "\"" + select + "\"}";

                            }


                            var selectItemRadio = $("<input>", {
                                type: "checkbox",
                                value: select,
                                name: titleContent,
                                style: "display:inline"
                            });


                            var selectValueText = $("<p>", {
                                text: num + ". " + select,
                                style: "display:inline"
                            });
                            selectItemRadio.appendTo(question);
                            selectValueText.appendTo(question);

                        }
                    });


                    //删除这个问题
                    var deleteQuestion = $("<input>", {
                        type: "button",
                        value: "删除该题",
                        click: function () {
                            if (questionNumber == question.attr("order")) {
                                question.remove();

                                for (var key in questions[questionNumber]) {
                                    delete questions[questionNumber][key];
                                }
                                questionNumber--;
                            } else {
                                alert("当前仅支持删除最后一题");
                                console.log("questionNumber: " + questionNumber + ",question.order:" + question.attr("order"));
                            }
                        }
                    });

                    //是否必答
                    var mustAnswer = $("<input>", {
                        type: "radio",
                        name: "mustAnswer" + questionNumber,
                        value: "true",

                        click: function () {
                            questions[question.attr("order")].mustAnswer = true;
                        }
                    });

                    var mustAnswerText = $("<label>", {
                        text: "必答"
                    });
                    var notMustAnswer = $("<input>", {
                        type: "radio",
                        name: "mustAnswer" + questionNumber,
                        value: "false",
                        click: function () {
                            questions[question.attr("order")].mustAnswer = false;
                        }

                    });
                    var notMustAnswerText = $("<label>", {
                        text: "非必答"
                    });


                    mustAnswer.appendTo(question);
                    mustAnswerText.appendTo(question);
                    notMustAnswer.appendTo(question);
                    notMustAnswerText.appendTo(question);

                    question.append(title);
                    addSubSelect.appendTo(question);
                    deleteQuestion.appendTo(question);
                    $("#paperMain").append(question);
                    question.append("<hr>");


                });


                //添加一个问答题
                $("#addQA").click(function () {

                    questionNumber++;
                    //存数据
                    questions[questionNumber] = new Object();


                    questions[questionNumber].type = 3;
                    questions[questionNumber].paperId = $("#paperId").val();
                    questions[questionNumber].orderNumber = questionNumber + 1;

                    //这个问题的div
                    var question = $("<div/>", {
                        id: "question" + questionNumber,
                        order: questionNumber,
                        css: {
                            border: "2px solid #000"
                        }
                    });

                    //标题
                    var title = document.createElement("p");
                    title.id = "title" + questionNumber;
                    while (true) {
                        var titleContent = prompt("请输入问题");
                        if (!(titleContent == null || titleContent == "")) {
                            title.innerText = (questionNumber + 1) + ". " + titleContent + "(问答题)";
                            //存
                            questions[questionNumber].content = titleContent;
                            break;
                        }
                    }


                    //删除这个问题
                    var deleteQuestion = $("<input>", {
                        type: "button",
                        value: "删除该题",
                        click: function () {
                            if (questionNumber == question.attr("order")) {
                                question.remove();

                                for (var key in questions[questionNumber]) {
                                    delete questions[questionNumber][key];
                                }
                                questionNumber--;
                            } else {
                                alert("当前仅支持删除最后一题");
                                console.log("questionNumber: " + questionNumber + ",question.order:" + question.attr("order"));
                            }
                        }
                    });

                    //是否必答
                    var mustAnswer = $("<input>", {
                        type: "radio",
                        name: "mustAnswer" + questionNumber,
                        value: "true",

                        click: function () {
                            questions[question.attr("order")].mustAnswer = true;
                        }
                    });

                    var mustAnswerText = $("<label>", {
                        text: "必答"
                    });
                    var notMustAnswer = $("<input>", {
                        type: "radio",
                        name: "mustAnswer" + questionNumber,
                        value: "false",
                        click: function () {
                            questions[question.attr("order")].mustAnswer = false;
                        }

                    });
                    var notMustAnswerText = $("<label>", {
                        text: "非必答"
                    });


                    mustAnswer.appendTo(question);
                    mustAnswerText.appendTo(question);
                    notMustAnswer.appendTo(question);
                    notMustAnswerText.appendTo(question);

                    question.append(title);
                    deleteQuestion.appendTo(question);
                    $("#paperMain").append(question);


                });

                //提交问卷
                $("#submit").click(function () {

                    console.log("问卷内容为：");
                    for (var i = 0; i < (questionNumber + 1); i++) {

                        if (questions[i].type != 3) {

                            questions[i].content = contents[i] + "]}";
                        }

                        console.log(JSON.stringify(questions[i]));

                    }

                    //提交
                    $.ajax({
                        //请求方式
                        type: "POST",
                        //请求的媒体类型
                        contentType: "application/json;charset=UTF-8",
                        //请求地址
                        url: "http://localhost:8090/user/addQuestion",
                        //数据，json字符串
                        data: JSON.stringify(questions),
                        //请求成功
                        success: function (result) {
                            console.log(result);
                        },
                        //请求失败，包含具体的错误信息
                        error: function (e) {
                            console.log(e.status);
                            console.log(e.responseText);
                        }
                    });
                    alert("问卷发布成功!");
                    window.location.href = "http://localhost:8090/user";
                });


            }
        )

    </script>
</head>
<body style="width: 60%;margin: auto;padding: auto; border:solid 2px">
<br>
<h1>添加问卷</h1>
<br><br>
<button id="addSingleSelection">添加一个单选题</button>
<br><br>
<button id="addMultipleSelection">添加一个多选题</button>
<br><br>
<button id="addQA">添加一个问答题</button>
<br>
<br>
<div id="paperMain">
</div>

<br>
<br>
<button id="submit">发布问卷</button>


<input id="paperId" type="hidden" value="${paperId}">
</body>
</html>
