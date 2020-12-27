<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<style>
*{margin:0;padding:0; color:#666}
img{border:0 none; vertical-align:top;}
li{list-style:none;}

.bannerArea{width:100%; padding:10px 50px 10px 90px; border:1px solid #000; margin:30px auto; position:relative;}

.bannerArea .banner{width:300px; height:200px; overflow:hidden; }
.bannerArea .banner ul:after{display:block; clear:both; content:"";}
.bannerArea .banner ul{width:5000px; *zoom:1;}
.bannerArea .banner ul li{width:235px; float:left; position: relative;}

.bannerArea .btns a{position:absolute; top:30px;}
.bannerArea .prev{left:15px;}
.bannerArea .next{right:15px;}
p {text-align: center;}
</style>

</head>
<body>
<!-- 태준 -->
		<div class="container" style="width: 100%;">
		<hr>
			${goods.g_m_id }님이 판매하는 모든 상품
			
				
			<div class="bannerArea">
			  <div class="banner">
			    <ul>
			     <c:forEach var="List" items="${List }" >
			      <li>
			      <a href="detail?gno=${List.gno}"><img src="<c:url value="/imgUpload/${List.f_name}"/>" width=220px; height=200px;></a>
			      </li>
			    </c:forEach>
			    </ul>
			  </div>
			  <div class="btns">
			    <a href="#" class="prev"><img src="../resources/goods/goods_img/previous.png" width="30px"></a>
			    <a href="#" class="next"><img src="../resources/goods/goods_img/next.png" width="30px"></a>
			  </div>
			</div>
			<p>
			  <a href="#none" class="play"><img src="../resources/goods/goods_img/play.png" width="30px"></a> &nbsp;
			   <a href="#none" class="stop"><img src="../resources/goods/goods_img/stop.png" width="30px"></a>
			</p>
		</div><br>
		<!-- 태준 -->
<script>
var wrap = $(".banner");
var banner = $(".banner ul");
var slideList = $(".banner ul li");
var totalNum = slideList.size();
var listWidth = slideList.outerWidth();
var prevBtn = $(".prev");
var nextBtn = $(".next");
var playBtn = $(".play");
var stopBtn = $(".stop");
var cnt = 0;
var viewNum = 4;
var interval = 1000;
var copyItem = $(".banner ul li:lt(" + viewNum + ")").clone(); //창수씨 소스 참조 ㅎㅎ
wrap.css({"width" : slideList.width() * viewNum})
var timer;

var slider = {
  
  init : function ( set ) {
    prevBtn.each(function () { 
      $(this).click(function () {
        slider.prevMove();
        stopTimer();
        return false;
      });
    });
    
    nextBtn.each(function () {
      $(this).click( function () {
        slider.nextMove();
        stopTimer();
        return false;
      });
    }); 
    
    if (set == "auto") {
     startTimer()
    } else {
      stopTimer();
    }
  },
  prevMove : function () {
    banner.append(copyItem);
    if ( cnt == 0) {
      cnt = totalNum;
      banner.css("margin-left", -listWidth * cnt )
    }
    cnt--;
    banner.stop().animate({"margin-left" : -listWidth * cnt },"fast"); 
  },
  nextMove : function () {
    banner.append(copyItem);
    if ( cnt == totalNum) {
      cnt = 0;
      banner.css("margin-left", cnt)
    }
    cnt++;
    banner.stop().animate({"margin-left" : -listWidth * cnt },"fast"); 
  } 
}

function startTimer () {
   clearInterval(timer)
    timer = setInterval(function () { slider.nextMove() }, interval);
    playBtn.css({"color": "red"});
    stopBtn.css({"color": "#666"})
    console.log("start");
}

function stopTimer() {
  clearInterval(timer);
  playBtn.css({"color": "#666"})
  stopBtn.css({"color": "red"})
  console.log("stop");
}

$(document).ready(function () {
   slider.init("auto");
   playBtn.on("click" , function () {startTimer()})
   stopBtn.on("click" , function () {stopTimer()})
  })

</script>
</body>