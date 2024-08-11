# chatService
실시간 웹 채팅 사이트




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
