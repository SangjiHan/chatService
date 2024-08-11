![header](https://capsule-render.vercel.app/api?type=waving&color=0:4682B4,100:AFEEEE&height=200&text=실시간%20채팅%20사이트&fontColor=000000&fontSize=40&width=700&fontAlignY=35)

<div align = "center">
방성영 : 프로젝트 총괄, 전체적인 기능 구현
한상지 : Restful API, DB, Javascript, 데이터 송수신
김예은 : 사용자 UI/UX

</div>
<br>


 <div align="center">
    <div>
        <strong>개발 언어:</strong> 
        <img src="https://img.shields.io/badge/HTML5-0000CD?style=for-the-badge&logo=html5&logoColor=white&color=F16529" alt="HTML5 badge">
        <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white&color=264DE4" alt="CSS3 badge">
        <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=000000&color=323330" alt="JavaScript badge">
        <img src="https://img.shields.io/badge/JSP-F7E03C?style=for-the-badge&logo=java&logoColor=white&color=323330" alt="JSP badge">
        <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white&color=005B96" alt="Java badge">
    </div>
    
    <div>
        <strong>데이터베이스:</strong> 
        <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white&color=000000" alt="Oracle badge">
    </div>
    
    <div>
        <strong>빌드:</strong> 
        <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white&color=E1E1E1" alt="Maven badge">
    </div>
    
    <div>
        <strong>형상 관리:</strong> 
        <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white&color=000000" alt="Git badge">
    </div>
    
    <div>
        <strong>프레임워크:</strong> 
        <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white&color=4A5B4D" alt="Spring Boot badge">
        <img src="https://img.shields.io/badge/JPA-007396?style=for-the-badge&logo=java&logoColor=white&color=F8C300" alt="JPA badge">
        <img src="https://img.shields.io/badge/WebSocket-000000?style=for-the-badge&logo=WebSocket&logoColor=white&color=4A4A4A" alt="WebSocket badge">
    </div>
</div>

<br>

 ![날씨](https://github.com/SangjiHan/weather/assets/133099077/31ddbb8c-3107-44c0-ba9a-be8929283d04)



### 프로젝트 기간 : 2024.05 ~ 2024.06 <br/><br/><br/>  

 # :sunny:프로젝트 목표  
 사용자가 빠르고 간편하게 날씨 정보를 얻기 위함  <br/><br/><br/> 

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





설정파일 생성 가이드: 
charservice/src/resources/ 폴더에 application.properties라는 이름으로 파일을 생성해주세요
application.properties 안에 작성해야할 내용(중괄호 안은 자기가 채움)
spring.application.name=chatservice server.port={톰켓 포트번호}
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XE 
spring.datasource.username={db 사용자} 
spring.datasource.password={db 비밀번호} 
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=update

media.filepath=chatservice\src\main\resources\static\media 
spring.servlet.multipart.max-file-size=-1 spring.servlet.multipart.max-request-size=-1

size.serverpfp=480 size.reaction=90 size.messagemedia=900 size.userpfp=480 message.number=25
