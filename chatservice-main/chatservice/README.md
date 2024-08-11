# chatservice
설정파일 생성 가이드:
  charservice/src/resources/ 폴더에 application.properties라는 이름으로 파일 생성

  application.properties 안에 작성해야할 내용(중괄호 안은 자기가 채움):

  spring.application.name=chatservice
  server.port={톰켓 포트번호}

  spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XE
  spring.datasource.username={db 사용자}
  spring.datasource.password={db 비밀번호}
  spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

  spring.jpa.hibernate.ddl-auto=update


  media.filepath=chatservice\\src\\main\\resources\\static\\media
  spring.servlet.multipart.max-file-size=-1
  spring.servlet.multipart.max-request-size=-1


  size.serverpfp=480
  size.reaction=90
  size.messagemedia=900
  size.userpfp=480
  message.number=25

플로우:

  회원가입:

    Request:
    index.html(로그인 겸용) -> join.html ->
    JoinController -> JoinService -> AppuserRepository

    Response(성공시):
    AppuserRepository -> JoinService -> JoinController -> index.html(로그인 여부의 프롬트)
      예 -> mainapp으로 리다이렉트
      아니요 -> index.html
    Response(실패시)
      index.html로 리다이렉트
  
  유효성:
    Requst:
      join.html(이메일('.' 이후에) 혹은 고유이름(5자 부터) 입력시 실시간으로 fetch api를 사용해서 request 보냄) -> JoinCheckController -> JoinService -> AppuserRepository
    Response:
      AppuserRepository -> JoinCheckController ->
      (중복여부에 대한 정보가 들어간 json)을 join.html 로 리턴

회원가입 폼 가이드:
  이메일 (appuserEmail) : email
  비밀번호 (appuserPassword): password
  비밀번호 확인: password
  고유 이름(appuserUkName) : text
  디스플레이 이름(appuserDisplayName): text

회원가입 유효성 가이드:
  이메일과 고유이름은 JoinCheckController에서 비동기적으로 체크
  비밀번호와 디스플레이 이름은 자바스크립트로

  이메일 최대 35자
  고유이름 5~20자
  디스플레이 이름 5~20자
  비밀번호 10~35자 (알파벳과 숫자 혼용, 특수문자 최소 1개)



