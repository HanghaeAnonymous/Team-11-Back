# 익명의 멘탈케어

</br>

## 🤷 프로젝트 소개 
 <p> 익명 고민 상담 플랫폼 입니다. </p>
 <p> 랜덤의 게시글이 나와 익명으로 소통을 할 수 있습니다.</p>
 <p> 댓글을 작성하지 않은 페이지는 이후엔 볼 수 없으니 주의해주세요!  </p>
  
  Web Site : 도메인 이후 작성예정
</br>


## 🎥 시연 영상
 유투브 편집자 구합니다.

## 🧑🏼‍💻 개발기간 및 팀원소개
### 기간: 2021.12.06 ~ 2021.12.11 (5일)  
</br>
### Front-end   
   <p><a href="https://github.com/eundol0519" target="_blank"><img width="150"  src="https://img.shields.io/static/v1?label=React&message=오은희&color=61dafb&style=for-the-badge&>"/></a></p>
   <p><a href="https://github.com/WooMinGy" target="_blank"><img width="150"  src="https://img.shields.io/static/v1?label=React&message=우민기&color=61dafb&style=for-the-badge&>"/></a></p>
   
  
### Back-end
<p><a href="https://github.com/jybin96" target="_blank"><img width="150"  src="https://img.shields.io/static/v1?label=Spring&message=정영빈&color=08CE5D&style=for-the-badge&>"/></a></p>
   <p><a href="https://github.com/Zabee52" target="_blank"><img width="150"  src="https://img.shields.io/static/v1?label=Spring&message=김용빈&color=08CE5D&style=for-the-badge&>"/></a></p>
   <p><a href="https://github.com/HundredCleanWater" target="_blank"><img width="150"  src="https://img.shields.io/static/v1?label=Spring&message=백정수&color=08CE5D&style=for-the-badge&>"/></a></p>
   

  
</br>



## 🏷 API Table
<details>
 <summary>자세히 보기</summary>
 
|기능|Method|URL|Request|Response|
|:-----:|:----:|----|----|----|
|로그인 요청|POST|/user/login|{username: "iamuser",</br>password: "1234"}| |
|회원</br>가입|POST|/api/signup|{username:"iamuser"</br>,"password:"1234"</br>,passwordCheck:"1234"}||
|아이디 중복 검사|POST|/api/idCheck|{username:"iamuser"}|{result:false}|
|로그인 여부</br>확인|GET|/api/islogin||{userInfo:{username:"username"}</br>}|
|로그아웃|GET|/api/logout|||
|사진</br>업로드|POST|/api/images||{imageUrl:"/images/cancle.png"}|
|게시글 작성|POST|/api/posts|{title:"제목입니다",</br>content:"반가워요",</br>imageUrl:"/images/cancle.png"}||
|게시글 수정|PUT|/api/posts/{postId}|{content:"반갑습니다"}||
|게시글 삭제|DELETE||||
|랜덤</br>게시글 조회|GET|/api/posts||{postId:1,</br>title:"제목",</br>content:"글내용",</br>comments:[{</br>commentId:1,</br>comment:"댓글내용",</br>createdAt:LocalDateTime}]</br>}|
|내가</br>작성한 게시글 조회|GET|/api/comments/{postId}||{postId:1,</br>title:"제목",</br>content:"글내용",</br>comments:[{</br>commentId:1,</br>comment:"댓글내용",</br>createdAt:LocalDateTime},</br>{commentId:2,</br>comment:"댓글내용2",</br>createdAt:LocalDateTime}]</br>}|
|내가</br> 댓글을 작성한 게시글 조회|GET|/api/comments/{commentId}||{postId:1,</br>title:"제목",</br>content:"글내용",</br>comments:[{</br>commentId:1,</br>comment:"댓글내용",</br>createdAt:LocalDateTime},</br>{commentId:2,</br>comment:"댓글내용2",</br>createdAt:LocalDateTime}]</br>}|
|댓글</br> 작성|POST|/api/comments/{postId}|{comment:"댓글"}||
|피드</br>페이지|GET|/api/feeds||[myPosts:[{</br>postId:1</br>title:"제목",</br>content:"내용",},</br>{postId:2,</br>title:"제목2",</br>content"내용2"}],</br>myComments:[{</br>commentId:1,</br>comment:"댓글",</br>createdAt:LocalDateTime},</br>{commentId:2,</br>comment:"댓글2",</br>createdAt:LocaldateTime}]</br>]|

</details>



</br>

## 🔨사용한 기술 스택

<code> Back-end </code>
 * Java 8
 * SpringBoot 2.5.3
 * Spring Security
 * Gradle
 * JPA
 * QureyDSL
 * MySQL
 * JWT
 * CORS

<code> Front-end </code>
 * React [Front-end 개발Page](https://github.com/HanghaeAnonymous/Team-11-Front)

</br>

<code>DevOps</code>
* AWS EC2
* AWS RDS(MySQL)
* FileZilla

<code>Tool</code>
* Git
* GitHub

## ⚒️ 아키텍처 및 ERD 설계

곧 올리겠습니다..


## ✌🏻 개인 역할

<code>정영빈</code> 게시글 작성&수정&삭제 ,사진 업로드 

<code>김용빈</code> 아이디 중복 검사, 랜덤 & 내가 작성한 게시글 조회, 댓글 작성, 피드 페이지

<code>백정수</code> 로그인, 회원가입, 내가 작성한 댓글 조회 

</br>



## 📝 후기 및 팀 노션 페이지

<code>정영빈</code> 

<code>김용빈</code> 

<code>백정수</code>  

<code>팀 노션 페이지</code> [익명의 멘탈케어](https://chrome-armadillo-b80.notion.site/c96fcf057d404cb98d18c01cb404aaa7)
