<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改模板</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">

        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>修改模板</small>
                    </h1>
                </div>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/template/updateTemplate" method="post"  enctype="multipart/form-data">
            模板名称：<input type="text" name="name" value="${template.getName()}"/>
            模板版本号：<input type="text" name="version" value="${template.getVersion()}"/><br>
            <a href="javascript:;" class="a-upload"><input type="file" name="multipartFile" accept=".xml" display="inline"></a><br>
            <input type="submit" value="提交" align="center"/>
        </form>

    </div>