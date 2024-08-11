![header](https://capsule-render.vercel.app/api?type=waving&color=0:4682B4,100:AFEEEE&height=200&text=실시간%20채팅%20사이트&fontColor=000000&fontSize=40&width=700&fontAlignY=35)

### 팀원 소개 <br/>  
**방성영** : 프로젝트 총괄, 기능 구현 <br/>
**한상지** : Restful API, DB, Javascript, 데이터 송수신 <br/>
**김예은** : 사용자 UI/UX 기능 구현 <br/>

<br>

<div align="center">
    <img src="https://img.shields.io/badge/HTML5-0000CD?style=for-the-badge&logo=html5&logoColor=white&color=F16529" alt="HTML5 badge">
    <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white&color=264DE4" alt="CSS3 badge">
    <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=000000&color=323330" alt="JavaScript badge">
    <img src="https://img.shields.io/badge/JSP-F7E03C?style=for-the-badge&logo=java&logoColor=white&color=323330" alt="JSP badge">
    <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white&color=005B96" alt="Java badge"> <br>
    <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white&color=000000" alt="Oracle badge">
    <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white&color=E1E1E1" alt="Maven badge">
    <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white&color=000000" alt="Git badge">
    <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white&color=4A5B4D" alt="Spring Boot badge">
    <img src="https://img.shields.io/badge/JPA-007396?style=for-the-badge&logo=java&logoColor=white&color=F8C300" alt="JPA badge">
    <img src="https://img.shields.io/badge/WebSocket-000000?style=for-the-badge&logo=WebSocket&logoColor=white&color=4A4A4A" alt="WebSocket badge">
</div>

### 기술스택 <br/> 
**개발 환경**
- **IDE**: STS4, Visual Studio Code

**개발 언어**
- HTML5
- CSS3
- JavaScript
- Java
- JSP

**데이터베이스**
- Oracle

**빌드 도구**
- Maven

**형상 관리**
- Git

**프레임워크**
- Spring Boot
- JPA
- WebSocket



<br>

 



### 프로젝트 기간 : 2024.05 ~ 2024.06 <br/><br/><br/>  

 # 🏃:프로젝트 목표  
  실시간 채팅 서비스 구현  <br/>
일대일, 다인원 채팅방 생성  <br/>
    이미지, 동영상 전송 가능  <br/>
  <br/><br/><br/> 

 # :umbrella:프로젝트 설명  
 #### 사용자가 선택한 지역의 날씨 정보를 제공 <br/>
 #### openweathermap 사이트의 api를 활용하여, JSON 데이터 사용 <br/>   
 :heavy_check_mark:  지역별 날씨 정보 : 현재 우리나라 지역별 날씨를 지도에 표시  
 :heavy_check_mark:  상세 날씨 정보 : 지역을 클릭하면 해당 지역의 상세 날씨, 시간별 날씨, 날짜별 날씨 정보 제공  
 :heavy_check_mark:  다른 지역과 날씨 비교 : 여러 지역과의 날씨 비교 <br/><br/><br/> 
 
 # :cloud:사용 기술
 :small_blue_diamond:  HTML : 웹 페에지의 구조 정의 <br/>
 :small_blue_diamond:  CSS : 사용자 인터페이스 구성 <br/>
 :small_blue_diamond:  Javascript: 웹 페이지의 동적 기능 구현, API와의 상호작용 <br/>
 :small_blue_diamond:  JSON : weather API 데이터 처리, 화면에 표시 <br/>




### 파일 설치 가이드 : 
파일을 다운로드 받으시고 vs code에서 열어주세요. <br/>
jdk17을 다운 받으시고 환경변수 편집으로 path를 설정해주세요. <br/>

### 설정파일 생성 가이드 : 
charservice/src/resources/ 폴더에 application.properties라는 이름으로 파일을 생성해주세요. <br/>
아래는 application.properties 안에 작성해야할 내용입니다. 중괄호 안에 내용은 환경설정에 맞게 작성해주세요. <br/>
spring.application.name=chatservice server.port={톰켓 포트번호} <br/>
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XE <br/>
spring.datasource.username={db 사용자} <br/>
spring.datasource.password={db 비밀번호} <br/>
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver <br/>

spring.jpa.hibernate.ddl-auto=update <br/>

media.filepath=chatservice\src\main\resources\static\media <br/>
spring.servlet.multipart.max-file-size=-1 spring.servlet.multipart.max-request-size=-1 <br/>

size.serverpfp=480 size.reaction=90 size.messagemedia=900 size.userpfp=480 message.number=25 <br/>
