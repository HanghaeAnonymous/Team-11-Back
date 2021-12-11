# 익명의 멘탈케어

<p align="center"><img width="500"  alt="스크린샷 2021-12-09 오후 2 32 42" src="https://user-images.githubusercontent.com/90129613/145664148-ff6951db-040e-4b32-9a59-1e4a10b36c12.png">
 </p>

</br>

## 🤷 프로젝트 소개 
 <p> 가끔, 아는 사람들에게는 말하기 어려운 고민도 있습니다. </p>
 <p> 그럴 땐 이름도, 얼굴도 모르는 사람에게 자신의 고민을 털어 놓으세요. </p>
 <p> '익명의 멘탈케어'는 익명 고민 상담 플랫폼 입니다. </p>
 <p> 모르는 사람에게 자신의 고민을 들려주세요, 그리고 들어주세요. 분명히 위로가 될 겁니다.</p>
 <p> 중요 : 한 번 지나친 다른 사람의 고민은 다시는 볼 수 없습니다. 명심하세요! </p>
  
  Web Site : http://anonymousmentalcare.s3-website.ap-northeast-2.amazonaws.com/
</br>

## 🧾 와이어 프레임
![image](https://user-images.githubusercontent.com/90129613/145663373-a13d593f-6817-4ed9-b2da-07efe0b3f7a4.png)


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
 
 ![image](https://user-images.githubusercontent.com/90129613/145416387-0d1c54aa-7110-4f2b-b1a4-0dbb90b07e62.png)

<!-- |기능|Method|URL|Request|Response|
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
 -->
 
</details>



</br>


## 🔨사용한 기술 스택

<img width="866" alt="스크린샷 2021-12-09 오후 2 32 42" src="https://user-images.githubusercontent.com/90129613/145340150-0241eb6f-4f6f-4688-9083-11e60ef709c0.png">


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
 * Swagger

<code> Front-end </code>
* [Front-end 개발Page](https://github.com/HanghaeAnonymous/Team-11-Front)

</br>

<code>DevOps</code>
* AWS EC2
* AWS RDS(MySQL)
* FileZilla

<code>Tool</code>
* Git
* GitHub

## ⚒️ ERD 설계

<img src="https://user-images.githubusercontent.com/90129613/145414719-0a6e457f-a0eb-4610-8f4d-9f963b5eb932.png" width="600" height="500"/>


## ✌🏻 개인 역할 및 트러블 슈팅 해결과정

<code>정영빈</code> 게시글 작성&수정&삭제 ,사진 업로드 
</br>


<code>김용빈</code> 아이디 중복 검사, 랜덤 & 내가 작성한 게시글 조회, 댓글 작성, 피드 페이지
</br>


<code>백정수</code> 로그인, 회원가입, 내가 작성한 댓글 조회 

<code>Trouble Shooting</code>  [트러블 슈팅 해결과정](https://chrome-armadillo-b80.notion.site/0c1dc754030248d88bf14c7d8570ddab)

</br>



## 📝 후기 및 팀 노션 페이지

<code>정영빈</code> 
</br>

<code>김용빈</code> 
</br>


<code>백정수</code>  
</br>


<code>팀 노션 페이지</code>  [익명의 멘탈케어](https://chrome-armadillo-b80.notion.site/c96fcf057d404cb98d18c01cb404aaa7)
