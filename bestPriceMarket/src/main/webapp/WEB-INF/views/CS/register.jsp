<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <title>문의하기</title>

    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">

    <!-- Main CSS-->
    <link href="${pageContext.request.contextPath}/resources/cs/cs_css/main.css" rel="stylesheet" media="all">
    
    
    <script type="text/javascript">
		function register(){
			var cs_subject = document.fr.cs_subject.value;
			var cs_content = document.fr.cs_content.value;

		if(cs_subject == ""){
			alert("문의 제목을 입력해 주세요.");
			document.fr.cs_subject.focus();
			return false;
			}

		if(cs_content == ""){
			alert("문의 내용을 입력해 주세요");
			document.fr.cs_content.focus();
			return false;
			}
		}
    </script>
     <style type="text/css">  
   .btn {
       border-radius: 4px !important;
       background: #212529 !important;
       color: #fff !important;
       padding: 7px 45px !important;
       display: inline-block !important;
       margin-top: 20px !important;
       border: solid 2px #212529 !important; 
       transition: all 0.5s ease-in-out 0s !important;
   }
   .btn:hover,
   .btn:focus {
       background: transparent !important;
       color: #212529 !important;
       text-decoration: none !important;
   }
 .btn-danger {
       border-radius: 4px !important;
       background: #DC3545 !important;
       color: #fff !important;
       padding: 7px 45px !important;
       display: inline-block !important;
       margin-top: 20px !important;
       border: solid 2px #DC3545 !important; 
       transition: all 0.5s ease-in-out 0s !important;
   }
   .btn-danger:hover,
   .btn-danger:focus {
       background: transparent !important;
       color: #DC3545 !important;
       text-decoration: none !important;
}

</style>
</head>

<body>
    <div class="page-wrapper bg-dark p-t-100 p-b-50">
        <div class="wrapper wrapper--w900">
            <div class="card card-6">
                
                <div class="card-heading">
                    <h2 class="title">문의하기</h2>
                </div>
              
                <div class="card-body">
                    <form action="/CS/register" method="POST" accept-charset="UTF-8" name="fr">
                        <div class="form-row">
                            <div class="name">문의 제목</div>
                            <div class="value">
                                <input class="input--style-6" type="text" name="cs_subject">
                            </div>
                        </div>
                       
                        <div class="form-row">
                            <div class="name">글 작성자</div>
                            <div class="value">
                                <div class="input-group">
                                <%String id=(String)session.getAttribute("id"); %>
                                    <input class="input--style-6" type="text" name="cs_m_id" readonly="readonly" value="<%=id%>">
                                </div>
                            </div>
                        </div>
                      
                        <div class="form-row">
                            <div class="name">문의 내용</div>
                            <div class="value">
                                <div class="input-group">
                                    <textarea class="textarea--style-6" name="cs_content" placeholder="궁금하신 사항을 상세히 작성해 주세요."></textarea>
                                </div>
                            </div>
                        </div>
		               
		                <div class="card-footer">
		                   <div class="row">
								<div class="col-lg-6">
									<button type="submit" onclick="return register();" class="btn btn-secondary btn-block" style="font-size: 20px">문의하기</button>
								</div>
								<div class="col-lg-6">
									<a class="cancle" href="/CS/CSBoardListPage?num=1"><button type="button" class="btn btn-danger btn-block" style="font-size: 20px">취소</button></a>
								</div>
							</div><br>
<!-- 		                   	<button type="submit" class="btn btn-secondary btn-block" style="font-size: 20px">작성하기</button> -->
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