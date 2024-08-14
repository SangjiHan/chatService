![header](https://capsule-render.vercel.app/api?type=Wave&color=0:FF007F,100:00FFFF&height=200&text=실시간%20웹%20채팅&fontColor=FFFFFF&fontSize=40&width=700&fontAlignY=50)


<br />

# 👋 소개

> 실시간 웹 채팅 사이트입니다.  <br />
> 친구를 추가하고 다양한 채팅 방을 생성하여 소통할 수 있습니다. <br />
> 회원 가입 후에는 친구와 직접 대화를 나누거나, 주제에 맞는 서버를 생성해 다양한 대화를 즐길 수 있습니다. <br />
> 동영상과 사진을 첨부하여 공유하고 재생할 수 있는 기능도 지원합니다. <br />
> 지금 가입하여 친구와 소통해보세요!<br />

<br />

# 🧑‍🤝‍🧑 팀원 소개

- **방성영(팀장)** : 백엔드 개발 및 기능 구현
- **김예은** : 사용자 페이지 UI/UX 디자인
- **[한상지](https://github.com/SangjiHan)** : 프론트엔드 개발, 정적 페이지를 동적 페이지로 변환, RESTful API 설계 및 구현, 데이터베이스 설계 및 데이터 입력
  
<br />

# 프로젝트 기간
> 2024.05 ~ 2024.06

<br />

# 개발 도구
- 개발 언어 <img src="https://img.shields.io/badge/HTML5-F16529?style=for-the-badge&logo=html5&logoColor=white" alt="HTML5 badge"> <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" alt="CSS3 badge"> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=000000" alt="JavaScript badge"> <img src="https://img.shields.io/badge/JAVA-F7E03C?style=for-the-badge&logo=java&logoColor=000000" alt="JAVA badge"> <img src="https://img.shields.io/badge/JSP-red?style=for-the-badge&logo=jsp&logoColor=gray" alt="JSP badge"> <br />
- 개발 환경 <img src="https://img.shields.io/badge/VSCode-007ACC?style=for-the-badge&logo=visual-studio-code&logoColor=white" alt="VSCode badge"> <br />
- 데이터베이스 <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white" alt="Oracle badge"> <br />
- 빌드 <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven badge"> <br />
- 형상 관리 <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white&color=F05032" alt="Git badge"> <br />
- 프레임워크 <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot badge"> <img src="https://img.shields.io/badge/JPA-0074CC?style=for-the-badge&logo=java&logoColor=white" alt="JPA badge"> <img src="https://img.shields.io/badge/MyBatis-00205B?style=for-the-badge&logo=mybatis&logoColor=white" alt="MyBatis badge"> <br />
- 기타  <img src="https://img.shields.io/badge/WebSocket-6B6B6B?style=for-the-badge&logo=websocket&logoColor=white" alt="WebSocket badge">  <br />
 <br />


# 파일 설치 가이드
- 프로젝트의 모든 파일을 다운로드하여 로컬 환경에 저장합니다. <br />
- [VSCode 다운로드 페이지](https://code.visualstudio.com/)에서 VSCode를 다운로드하여 설치합니다. <br />
- [JDK 17 다운로드 페이지](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)에서 JDK 17을 다운로드하여 설치합니다. <br />
- JDK 설치 후, 환경변수 JAVA_HOME을 JDK가 설치된 경로로 설정합니다. <br />
- [Oracle Database 다운로드 페이지](https://www.oracle.com/database/technologies/)에서 Oracle Database를 다운로드하여 설치합니다. <br />
- 설치가 완료된 vscode를 실행합니다. <br />
- 웹 브라우저를 열고 http://localhost:{톰켓 포트번호}를 입력하여 애플리케이션이 정상적으로 실행되는지 확인합니다. <br />
<br />

# 설정파일 생성 가이드  <br/>
- charservice/src/resources/ 폴더에 application.properties라는 이름으로 파일을 생성해주세요. <br/>
- 아래는 application.properties 안에 작성해야할 내용입니다. 중괄호 안에 내용은 환경설정에 맞게 작성해주세요. <br/>
- spring.application.name=chatservice server.port={톰켓 포트번호} <br/>
- spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XE <br/>
- spring.datasource.username={db 사용자} <br/>
- spring.datasource.password={db 비밀번호} <br/>
- spring.datasource.driver-class-name=oracle.jdbc.OracleDriver <br/>
- spring.jpa.hibernate.ddl-auto=update <br/>
- media.filepath=chatservice\src\main\resources\static\media <br/>
- spring.servlet.multipart.max-file-size=-1 spring.servlet.multipart.max-request-size=-1 <br/>
- size.serverpfp=480 size.reaction=90 size.messagemedia=900 size.userpfp=480 message.number=25 <br/>
<br/>

# 프로젝트 소개

## 시스템 구조도
> ![image](https://github.com/user-attachments/assets/c2506d78-4b02-48dc-903b-b1ec9972c783) <br />
> - 친구 리스트: 사용자는 자신의 친구 목록을 보고, 친구의 상태를 확인하며, 친구를 추가하거나 삭제할 수 있습니다. <br />
> - 채팅방 생성: 사용자는 새로운 채팅방을 생성하고 일대일 채팅, 다인원 채팅을 할 수 있습니다. <br />
> - 서버 생성: 사용자는 다양한 주제를 위한 서버를 생성하여 그룹 대화를 나눌 수 있습니다. <br />
> - 사용자 정보 관리: 사용자는 자신의 프로필 정보를 수정하고, 비밀번호를 변경하거나 개인정보 설정을 조정할 수 있습니다. <br />


<br />

## 데이터 베이스
> ![image](https://github.com/user-attachments/assets/4909be04-4f50-4eb1-87d9-9bdfb0937391)<br />
> - CUID 형식: 데이터베이스는 CUID(Compact Unique Identifier) 형식을 사용하여 고유한 식별자를 생성합니다. <br />
> - 기본키 및 외래키: 테이블 간의 관계를 정의하고 데이터의 무결성을 유지하기 위해 기본키와 외래키를 설정했습니다. <br />
> - 연동과 무결성: 테이블 간의 연동이 용이하며, 데이터 무결성을 효과적으로 유지하도록 설계되었습니다. <br />

<br />

## 주요 기능 살펴보기 
### 1. 로그인 및 회원가입
> ![그림2](https://github.com/user-attachments/assets/dbe426a4-aa8f-4677-b561-7c7b797ecd97) ![그림3](https://github.com/user-attachments/assets/d6cd09ea-0fd5-40b0-9259-e00b53262b7e) <br />
> - 유효성 검사: 회원가입 시 사용자가 입력한 정보에 대해 유효성 검사를 수행하여, 올바른 형식과 범위 내의 값만을 허용합니다. 예를 들어, 이메일 주소의 형식이 올바른지 확인하고 비밀번호가 지정된 복잡성 요구 사항을 충족하는지 검토합니다. <br />
> - 중복 확인: 사용자가 입력한 정보(예: 이메일 주소, 사용자 이름 등)의 중복 여부를 체크하여, 이미 등록된 정보와의 충돌을 방지합니다. 이를 통해 사용자에게 고유한 계정을 보장하고, 기존 사용자와의 정보 충돌을 예방합니다. <br />

<br />

### 2. 채팅 화면
>  ![그림1](https://github.com/user-attachments/assets/8054519d-3c69-45bc-a456-4f334e088c27) <br />
> - 실시간 채팅: 텍스트 메시지를 실시간으로 주고받을 수 있어 즉각적인 소통이 가능합니다. <br />
> - 미디어 전송: 이미지와 동영상을 채팅창에 첨부하여 공유할 수 있습니다. <br />
> - 메시지 수정 및 삭제: 전송한 메시지를 수정하거나 삭제할 수 있는 기능을 제공합니다. 이를 통해 사용자는 실수로 전송한 메시지를 수정하거나 삭제할 수 있습니다. <br />

<br />


### 3. 친구 찾기 및 친구 리스트
> ![그림4](https://github.com/user-attachments/assets/0da1c07e-1599-42a3-a4bd-870cfd15daa6) ![그림5](https://github.com/user-attachments/assets/1bcef62f-f365-4198-85bb-b0c0937c87d1) <br/>
> - 친구 찾기: 사용자는 이메일 주소나 이름을 입력하여 친구를 검색하고, 해당 친구에게 메시지를 보낼 수 있습니다. <br />
> - 친구 리스트: 자신의 친구 목록을 확인하고, 친구의 상태(온라인/오프라인)를 확인할 수 있습니다. <br />
> - 친구 추가/삭제: 친구 요청을 보내거나, 기존 친구를 삭제하는 기능을 제공합니다. <br />

<br />

### 4. 서버 생성
>  ![image](https://github.com/user-attachments/assets/cf86499d-f004-4121-8125-2423bbc26c24) <br />
> - 서버 생성: 사용자는 다양한 용도에 맞춰 새로운 서버를 생성할 수 있습니다. 각 서버는 특정 주제나 목적에 따라 설정할 수 있습니다. <br />
> - 다양한 용도: 생성된 서버는 여러 가지 용도로 활용될 수 있으며, 주제에 맞는 대화를 나누거나 그룹 프로젝트, 이벤트 등을 위해 사용할 수 있습니다. <br />
> - 다수 인원 지원: 서버 내에서 다수의 사용자와 동시에 채팅할 수 있어, 대규모의 그룹 대화나 협업이 가능합니다. <br />

<br />


