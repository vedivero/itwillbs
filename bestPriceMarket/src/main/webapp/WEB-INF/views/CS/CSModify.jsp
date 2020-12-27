<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <!-- Title Page-->
    <title>내 문의</title>

    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">

    <!-- Main CSS-->
    <link href="${pageContext.request.contextPath}/resources/cs/cs_css/main.css" rel="stylesheet" media="all">
</head>

<body>
    <div class="page-wrapper bg-dark p-t-100 p-b-50">
        <div class="wrapper wrapper--w900">
            <div class="card card-6">
                <div class="card-heading">
                    <h2 class="title">내 문의</h2>
                </div>
                <div class="card-body">
                    <form method="POST">
                        <div class="form-row">
                            <div class="name">문의 제목</div>
                            <div class="value">
                                <input class="input--style-6" type="text" name="cs_subject" value="${vo.cs_subject }">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">글 작성자</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-6" type="text" name="cs_m_id" readonly="readonly" value="${vo.cs_m_id}">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">문의 내용</div>
                            <div class="value">
                                <div class="input-group">
                                    <textarea class="textarea--style-6" name="cs_content">${vo.cs_content }</textarea>
                                </div>
                            </div>
                        </div>
                    <div class="card-footer">
                    	<div class="row">
								<div class="col-lg-6">
									<button type="submit" class="btn btn-secondary btn-block" style="font-size: 20px">글 수정하기</button>
								</div>
								<div class="col-lg-6">
									<a class="cancle" href="/CS/list"><button type="button" class="btn btn-danger btn-block" style="font-size: 20px">글 삭제하기</button></a>
								</div>
							</div><br>
							
		                   	<button type="submit" class="btn btn-secondary btn-block" style="font-size: 20px">목록으로</button>		        
		            </div>
                    </form>
                </div>
                </div>
            </div>
        </div>

    <!-- Jquery JS-->
    <script src="${pageContext.request.contextPath}/resources/cs/cs_vendor/jquery/jquery.min.js"></script>


    <!-- Main JS-->
    <script src="${pageContext.request.contextPath}/resources/cs/cs_js/global.js"></script>

</body>


</html>