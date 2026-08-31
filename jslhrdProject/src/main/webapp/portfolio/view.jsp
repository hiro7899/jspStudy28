<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="JSL">
<meta name="Keywords"
	content="반응형홈페이지,  JAVA, JSP, PHP, 대전직업전문학교, 대전국비지원, 국비무료">
<meta name="Description" content="응용SW개발자를 위한 반응형 홈페이지">
<title>JSL인재개발원</title>
</head>
<body>
	<!-- sub contents -->
	<div class="sub_title">
		<h2>포트폴리오</h2>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="index.html"><i
							class="fa fa-home btn_plus"></i></a></li>
					<li class="dropdown"><a href="">포트폴리오<i
							class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="../about/gratings.html">기업소개</a> <a
								href="../portfolio/portfolio.html">포트폴리오</a> <a
								href="../notice/notice.html">커뮤니티</a>
						</div></li>
					<li class="dropdown"><a href="">포트폴리오<i
							class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="portfolio.html">포트폴리오</a>
						</div></li>
				</ul>
			</div>
		</div>
		<!-- container end -->
	</div>

	<div class="container">
		<div class="board_view">
			<h2>${dto.title }</h2>
			<p class="info">
				<span class="user">${dto.name }</span> | ${dto.regdate.substring(0, 10) }
				| <i class="fa fa-eye"></i> ${dto.views }
			</p>
			<div class="board_body">
				<p>${dto.content }</p>
				<div style="text-align: center; margin-top: 20px;">
					<img src="/uploads/${dto.imgfile }" alt=""
						style="width: 30%; height: auto;">
				</div>
			</div>
			<div class="prev_next">
				<c:if test="${prevDto != null}">
					<a
						href="${pageContext.request.contextPath}/port/view.do?bno=${prevDto.bno}&type=view"
						class="btn_prev"><i class="fa fa-angle-left"></i> <span
						class="prev_wrap"> <strong>이전글</strong><span>${prevDto.title}</span>
					</span> </a>
				</c:if>
				<div class="btn_3wrap">
					<a href="${pageContext.request.contextPath}/port/list.do">목록</a> <a
						href="${pageContext.request.contextPath}/port/update.do?bno=${dto.bno}&type=update"
						onClick="return updateCheck();">수정</a> <a
						href="${pageContext.request.contextPath}/port/delete.do?bno=${dto.bno}"
						onClick="return deleteCheck();">삭제</a>
				</div>
				<c:if test="${nextDto != null}">
					<a
						href="${pageContext.request.contextPath}/port/view.do?bno=${nextDto.bno}&type=view"
						class="btn_next"> <span class="next_wrap"> <strong>다음글</strong><span>${nextDto.title}</span>
					</span> <i class="fa fa-angle-right"></i></a>
				</c:if>
			</div>
		</div>

		<!-- ============================================ -->
		<!-- 댓글 영역 시작 (view.jsp 하단, 포트폴리오 내용 아래) -->
		<!-- ============================================ -->
		<div id="replyWrap">
		
		    <!-- ① 댓글 개수 표시 영역 -->
		    <h3 id="replyTitle">댓글 <span id="replyCount">0</span>개</h3>
		
		    <!-- ② 댓글 입력 영역: textarea + button을 가로로 나란히 배치 -->
		    <div id="replyInputBox">
		        <textarea id="replyContent" rows="3" placeholder="댓글을 입력하세요"></textarea>
		        <button id="replyBtn" type="button">댓글<br>달기</button>
		    </div>
		
		    <!-- ③ 댓글 목록이 실제로 채워질 자리 -->
		    <div id="replyList">
		    </div>
		
		</div>
		<!-- 댓글 영역 끝 -->
	</div>
<!-- end contents -->
	<script>
		function updateCheck() {
			const userid = "${sessionScope.userid}";
			const writer = "${dto.name}";
			
			if (userid === "") {
				alert("로그인 후 이용 가능합니다.");
				return false;
			} else if (userid !== writer) {
				alert("작성자만 수정할 수 있습니다.");
				return false;
			} else {
				return true;
			}
		}
	</script>
	
	<script>
		function deleteCheck() {
			const userid = "${sessionScope.userid}";
			const writer = "${dto.name}";
			
			if (userid === "") {
				alert("로그인 후 이용 가능합니다.");
				return false;
			} else if (userid !== writer) {
				alert("작성자만 삭제할 수 있습니다.");
				return false;
			} else {
				if(!confirm("정말 삭제하시겠습니까?")) {
					return false;
				}else{
					return true;
				}
			}
		}
	</script>
	<script>
		$(function() {
			$(".location  .dropdown > a").on("click", function(e) {
				e.preventDefault();
				if ($(this).next().is(":visible")) {
					$(".location  .dropdown > a").next().hide();
				} else {
					$(".location  .dropdown > a").next().hide();
					$(this).next().show();
				}
			});
		});
	</script>
	
	<script>
		$(document).ready(function() {
		
		    const port_bno = getParameterByName("bno");
		
		    loadReplyList();
		
		    // ==========================================
		    // ① 댓글 목록 불러오기 함수
		    // ==========================================
		    function loadReplyList() {
		
		        $.ajax({
		            url: "${pageContext.request.contextPath}/reply/list.do",
		            type: "GET",
		            data: { port_bno: port_bno },
		            dataType: "json",
		
		            success: function(res) {
		                $("#replyCount").text(res.count);
		
		                const $replyList = $("#replyList");
		                $replyList.empty();
		
		                if (!res.list || res.list.length === 0) {
		                    $replyList.html("<p style='color:#999; text-align:center;'>첫 댓글을 남겨보세요!</p>");
		                    return;
		                }
		
		                $.each(res.list, function(index, reply) {
		                    // Gson 변환 시 필드명(replyContent, replyDate) 또는 DB컬럼명 호환 처리
		                    const rawContent = reply.replyContent || reply.reply_content || "";
		                    const rawDate = reply.replyDate || reply.reply_date;
		                    const rawUserId = reply.userid || "";

		                    const dateStr = formatDate(rawDate);
		
		                    const html =
		                        "<div class='replyItem'>" +
		                        "  <p class='replyInfo'>" +
		                        "    <span class='replyUserId'>" + escapeHtml(rawUserId) + "</span>" +
		                        "    <span class='replyDate'>" + dateStr + "</span>" +
		                        "  </p>" +
		                        "  <p class='replyContentText'>" + escapeHtml(rawContent) + "</p>" +
		                        "</div>";
		
		                    $replyList.append(html);
		                });
		            },
		
		            error: function(xhr, status, error) {
		                console.log("댓글 목록 조회 실패: " + error);
		            }
		        });
		    }
		
		    // ==========================================
		    // ② 댓글달기 버튼 클릭 이벤트
		    // ==========================================
		    $("#replyBtn").on("click", function() {
		
		        const content = $("#replyContent").val().trim();
		
		        if (content === "") {
		            alert("댓글 내용을 입력해주세요.");
		            return;
		        }
		
		        $.ajax({
		            url: "${pageContext.request.contextPath}/reply/write.do",
		            type: "POST",
		            data: {
		                port_bno: port_bno,
		                reply_content: content
		            },
		            dataType: "json",
		
		            success: function(res) {
		                if (res.result === "login_required") {
		                    alert("로그인 후 댓글을 작성할 수 있습니다.");
		                    return;
		                }
		
		                if (res.result === 1) {
		                    $("#replyContent").val("");
		                    loadReplyList();
		                } else {
		                    alert("댓글 등록에 실패했습니다.");
		                }
		            },
		
		            error: function(xhr, status, error) {
		                console.log("댓글 등록 실패: " + error);
		            }
		        });
		    });
		
		    // ==========================================
		    // 유틸 함수들
		    // ==========================================
		    function getParameterByName(name) {
		        var url = window.location.href;
		        name = name.replace(/[\[\]]/g, "\\$&");
		        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
		            results = regex.exec(url);
		        if (!results) return null;
		        if (!results[2]) return "";
		        return decodeURIComponent(results[2].replace(/\+/g, " "));
		    }
		
		    function formatDate(timestamp) {
		        if (!timestamp) return "";
		        var d = new Date(timestamp);
		        if (isNaN(d.getTime())) return "";
		        
		        var yyyy = d.getFullYear();
		        var mm = String(d.getMonth() + 1).padStart(2, "0");
		        var dd = String(d.getDate()).padStart(2, "0");
		        var hh = String(d.getHours()).padStart(2, "0");
		        var mi = String(d.getMinutes()).padStart(2, "0");
		        return yyyy + "-" + mm + "-" + dd + " " + hh + ":" + mi;
		    }
		
		    // null / undefined 방어 코드가 추가된 escapeHtml
		    function escapeHtml(text) {
		        if (text === null || text === undefined) return "";
		        return String(text)
		            .replace(/&/g, "&amp;")
		            .replace(/</g, "&lt;")
		            .replace(/>/g, "&gt;")
		            .replace(/"/g, "&quot;")
		            .replace(/'/g, "&#039;");
		    }
		
		});
	</script>
</body>
</html>
<%@ include file="/footer.jsp"%>