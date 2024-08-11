![header](https://capsule-render.vercel.app/api?type=waving&color=0:4682B4,100:AFEEEE&height=200&text=실시간%20채팅%20사이트&fontColor=000000&fontSize=40&width=700&fontAlignY=35)

![그림1](https://github.com/user-attachments/assets/8054519d-3c69-45bc-a456-4f334e088c27)

## 파일 설치 가이드 <br/>
파일을 다운로드 받으시고 vs code에서 열어주세요. <br/>
jdk17을 다운 받으시고 환경변수 편집으로 path를 설정해주세요. <br/>
Oracel를 설치하고 sql 데이터를 참고하여 테이블을 생성해주세요. <br/>
  <br/><br/><br/> 
## 설정파일 생성 가이드  <br/>
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

<br/><br/><br/>

# 프로젝트 소개 <br/> 

### 팀원 소개 <br/>  
**방성영** : 프로젝트 총괄, 기능 구현 <br/>
**한상지** : Restful API, DB, Javascript, 데이터 송수신 <br/>
**김예은** : 사용자 UI/UX 기능 구현 <br/>

<br>

<div>
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


## 프로젝트 기간 : 2024.05 ~ 2024.06 <br/><br/><br/>  

 # 🏃:프로젝트 설명
  실시간 채팅 사이트입니다.  <br/>
  친구를 찾아 소통을 할 수 있습니다. <br/>
일대일, 다인원 채팅이 가능합니다.  <br/>
    텍스트뿐만 아니라 이미지, 동영상 전송 가능이 가능합니다.  <br/>
   서버를 생성하여 기능에 맞게 채팅을 할 수 있습니다.  <br/>


<br/><br/><br/>  
 # 주요 기능

## 로그인 및 회원가입 <br/>
![그림2](https://github.com/user-attachments/assets/dbe426a4-aa8f-4677-b561-7c7b797ecd97)
![그림3](https://github.com/user-attachments/assets/d6cd09ea-0fd5-40b0-9259-e00b53262b7e) <br/>
로그인과 회원가입으로 사이트의 회원이 되어보세요!  <br/>
 <br/><br/>
## 실시간 채팅 <br/>
![그림1](https://github.com/user-attachments/assets/8054519d-3c69-45bc-a456-4f334e088c27) <br/>

텍스트를 보내 실시간 채팅 서비스가 가능합니다. <br/>
이미지와 동영상도 보낼 수 있습니다. <br/>
메세지 수정, 삭제 기능이 가능합니다. <br/>
<br/><br/>
## 친구 찾기 및 친구 리스트 <br/>
![그림4](https://github.com/user-attachments/assets/0da1c07e-1599-42a3-a4bd-870cfd15daa6)
![그림5](https://github.com/user-attachments/assets/1bcef62f-f365-4198-85bb-b0c0937c87d1) <br/>

친구를 찾아 메세지를 보낼 수 있습니다.<br/>
친구의 이메일, 이름을 검색하여 친구를 찾을 수 있습니다.<br/>
<br/><br/>
# 서버 기능 <br/>
![그림6](https://github.com/user-attachments/assets/3fdecd24-0513-466e-b0d7-41651e48d417) <br/>

사용 용도 별로 서버를 생성할 수 있습니다. <br/>
다양한 용도로 서버를 사용할 수 있습니다. <br/>
다수의 인원과 채팅을 할 수 있습니다. <br/>




