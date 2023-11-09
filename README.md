# 프로젝트 요약
1. Spring Security, Google OAuth2, JWT를 활용하여 소셜 인증 기능 구현
2. Docker 환경에서 MariaDB 환경 구축
3. Vue와 Vue 라이브러리(vuetify, pinia 등)를 활용하여 화면단 구현

# 기술스택
- Spring Boot 2
- Spring Data JPA
- Spring Security 5
- Docker 24
- MariaDB 11
- Vue 3

# 프로젝트 구현내용
### 1. Google CLoud Console에서 서비스 등록
1. OAuth2 Client ID 생성
2. 로그인 성공시 이동할 Redirect URI 등록
### 2. Spring Boot 프로젝트 설정
1. application.properties 설정 파일 생성
   - DB 관련 설정
   - Google OAUTH 관련 설정(Client ID, Client Secret, Scope)
2. CORS 관련 설정
### 3. Maria DB 환경 구축
1. MariaDB Docker Image Pull
2. Docker Volume 생성
3. MariaDB Image Docker Container 실행(볼륨지정, 포트지정, root 패스워드 지정)
4. MariaDB Image Docker Container 접속
5. MariaDB DB 생성
### 4. JWT 토큰 유효성 검사를 하는 Security Filter 생성
1. HTTP 요청 헤더에서 액세스 토큰을 조회
2. 액세스 토큰이 유효한 경우, 인증객체를 생성하고 SecurityContext에 저장
3. 액세스 토큰이 유효하지 않은경우, SecurityContext를 비움
### 5. OAuth2 API를 호출할 수 있는 DefaultOAuth2UserService를 상속받는 클래스 구현
1. OAuth2 API를 호출하여 유저 정보 조회
2. 조회한 유저 정보를 DB에 저장
### 6. OAuth2 로그인 성공 이후 처리를 위한 OAuth2SuccessHandler 구현
1. 리프레시 토큰 생성
2. 생성한 리프레시 토큰을 DB와 쿠키에 저장
### 7. Spring Security 설정 클래스 생성
- JWT 토큰 유효성 검사를 하는 Security Filter 등록
- OAuth2 로그인 성공 이후 처리를 위한 OAuth2SuccessHandler 등록
- OAuth2 API를 호출할 수 있는 DefaultOAuth2UserService를 상속받는 클래스 등록
- CORS 관련 헤더를 응답에 추가
- CSRF 토큰을 사용하지 않음
- OAuth2 인증이 필요한 경로 설정

# 로그인 과정
### 1. 로그인 버튼 클릭
![](https://velog.velcdn.com/images/topmedia/post/760477a2-1de9-4bbb-b4f5-9f39107cb516/image.png)
1. 구글 로그인을 할 수 있는 새창이 띄워짐
2. 새창은 구글 Oauth 로그인 URL로 이동됨
### 2. 로그인 성공 후, 리프레스 토큰 발급
![](https://velog.velcdn.com/images/topmedia/post/d6bdb6a0-0c24-4186-9848-9f9e0999acf2/image.png)
1. 로그인 시도
2. 로그인 성공시, 스프링 시큐리티에서 설정한 Authentication Success Handler가 호출됨
   - 리프레시 토큰 생성
   - 생성한 리프레시 토큰을 DB와 쿠키에 저장
3. 로그인 새창이 꺼지고, 원래 페이지가 새로고침됨
### 3. 액세스 토큰 발급
![](https://velog.velcdn.com/images/topmedia/post/d641140c-0db5-4f33-bd55-92c481c987f8/image.png)
1. 현재 클라이언트 상태: 리프레시 토큰을 가지고 있고 액세스 토큰은 가지고 있지 않음
2. 클라이언트는 리프레시 토큰을 가지고 서버에 액세스 토큰을 요청
3. 서버는 리프레시 토큰을 검증하고, 유효한 경우 액세스 토큰을 클라언트에게 발급
4. 클라이언트는 액세스 토큰을 Vue Pinia를 통해 상태관리
5. 액세스 토큰에 담긴 페이로드를 추출하여 로그인한 유저 정보를 보여줌
### 4. 로그인 성공
![](https://velog.velcdn.com/images/topmedia/post/69165798-3260-4170-805e-c5677f3201c3/image.png)

# 로그아웃 과정
### 1. 로그아웃 버튼 클릭
![](https://velog.velcdn.com/images/topmedia/post/69064cc3-dc12-4788-b783-29ebc067a689/image.png)
1. 리프레시 토큰을 제거하는 백엔드 API 호출
   - DB에 저장된 리프레시 토큰 제거
   - 쿠키에 저장된 리프레시 토큰 제거
2. Vue Pinia를 통해 관리하던 액세스 토큰 제거
