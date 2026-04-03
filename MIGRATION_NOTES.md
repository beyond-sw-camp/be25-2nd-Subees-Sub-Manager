# 변경 요약

이 프로젝트는 팀장이 준 원본 `subscription` 구조를 기준으로, 민수님의 작업 내용을 다음 원칙으로 다시 반영한 수정본입니다.

## 반영한 요구사항
- `global` 패키지 제거
- 원본의 `common` 패키지 구조 사용
- `AuthService` / `AuthServiceImpl` 분리
- `JwtCookieService` / `JwtCookieServiceImpl` 추가
- `UserService` / `UserServiceImpl` 분리
- 기존 JWT 로그인 로직 유지 + HttpOnly 쿠키 발급/삭제 추가
- 기존 MyBatis 기반 `UserMapper.xml` 및 컨트롤러 로직 이식
- 원본 프로젝트 루트(`subscription`) 기준 패키지명 `com.subees.subscription`으로 통일

## 주요 패키지
- `common`
  - 공통 예외/응답/보안 설정
- `auth`
  - 로그인/로그아웃, JWT, 쿠키 서비스
- `user`
  - 회원가입, 내 정보, 비밀번호, 탈퇴, 프로필 이미지
- `system`
  - health/info/time 테스트용 API

## 확인 필요 사항
- `src/main/resources/application.yml`의 DB 계정/비밀번호는 민수님 로컬값으로 유지했습니다.
- 팀 브랜치에 올릴 때는 팀 공통 DB 설정으로 바꿔야 할 수 있습니다.
- 현재 컨테이너 환경에서는 Maven 의존성 다운로드가 막혀 있어 실제 `mvn compile`까지는 검증하지 못했습니다.
  다만 파일 간 import/패키지 참조 정합성은 점검했습니다.
