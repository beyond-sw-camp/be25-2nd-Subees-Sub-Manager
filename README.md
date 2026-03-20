<div align="center">

<!-- ✅ 상단 배너(Front와 동일) -->
<img src="./이미지/배너/배너.svg" alt="Subees 배너" width="900" />

<br/>

<!-- ✅ 로고(Back-end는 로고 사용, 포스터 섹션은 제외) -->
<img src="./이미지/로고/Subees_Logo.png" alt="Subees Logo" width="320" />

<br/><br/>

<img src="https://img.shields.io/badge/Backend-Spring%20Boot-0f172a?style=for-the-badge" alt="Backend" />
<img src="https://img.shields.io/badge/Language-Java-0f172a?style=for-the-badge" alt="Java" />
<img src="https://img.shields.io/badge/DB-MariaDB-0f172a?style=for-the-badge" alt="MariaDB" />
<img src="https://img.shields.io/badge/ORM-JPA%20%2F%20QueryDSL-0f172a?style=for-the-badge" alt="ORM" />
<img src="https://img.shields.io/badge/Docs-Swagger-0f172a?style=for-the-badge" alt="Swagger" />

</div>

---

## 👥 팀원 소개

<table align="center">
  <tr>
    <td align="center" width="180">
      <img src="./이미지/깃허브_사진/cat1.jpg" width="160" height="160" style="object-fit:cover; border-radius:14px; display:block; margin:0 auto;" alt="조장:김가영" />
      <br />
      <b>조장:김가영</b>
      <br />
      <a href="https://github.com/gahyoung920-eng">
        <img src="https://img.shields.io/badge/GITHUB-181717?style=for-the-badge&logo=github&logoColor=white" alt="GITHUB" />
      </a>
    </td>

  <td align="center" width="180">
      <img src="./이미지/깃허브_사진/cat2.jpg" width="160" height="160" style="object-fit:cover; border-radius:14px; display:block; margin:0 auto;" alt="김다솜" />
      <br />
      <b>김다솜</b>
      <br />
      <a href="https://github.com/Myang09">
        <img src="https://img.shields.io/badge/GITHUB-181717?style=for-the-badge&logo=github&logoColor=white" alt="GITHUB" />
      </a>
    </td>

  <td align="center" width="180">
      <img src="./이미지/깃허브_사진/cat3.jpg" width="160" height="160" style="object-fit:cover; border-radius:14px; display:block; margin:0 auto;" alt="김승욱" />
      <br />
      <b>김승욱</b>
      <br />
      <a href="https://github.com/KIM-SEUNG-WOOK">
        <img src="https://img.shields.io/badge/GITHUB-181717?style=for-the-badge&logo=github&logoColor=white" alt="GITHUB" />
      </a>
    </td>

  <td align="center" width="180">
      <img src="./이미지/깃허브_사진/cat4.jpg" width="160" height="160" style="object-fit:cover; border-radius:14px; display:block; margin:0 auto;" alt="김정수" />
      <br />
      <b>김정수</b>
      <br />
      <a href="https://github.com/krystallinekim">
        <img src="https://img.shields.io/badge/GITHUB-181717?style=for-the-badge&logo=github&logoColor=white" alt="GITHUB" />
      </a>
    </td>

  <td align="center" width="180">
      <img src="./이미지/깃허브_사진/cat5.jpg" width="160" height="160" style="object-fit:cover; border-radius:14px; display:block; margin:0 auto;" alt="신민수" />
      <br />
      <b>신민수</b>
      <br />
      <a href="https://github.com/ZonezIpex">
        <img src="https://img.shields.io/badge/GITHUB-181717?style=for-the-badge&logo=github&logoColor=white" alt="GITHUB" />
      </a>
    </td>

  <td align="center" width="180">
      <img src="./이미지/깃허브_사진/cat6.jpg" width="160" height="160" style="object-fit:cover; border-radius:14px; display:block; margin:0 auto;" alt="이서윤" />
      <br />
      <b>이서윤</b>
      <br />
      <a href="https://github.com/leesy744">
        <img src="https://img.shields.io/badge/GITHUB-181717?style=for-the-badge&logo=github&logoColor=white" alt="GITHUB" />
      </a>
    </td>
  </tr>
</table>

---

## 📚 [Back-end] Repository 목차
1. [📌 프로젝트 개요](#프로젝트-개요-project-overview)
2. [🎯 서비스 목표](#서비스-목표)
3. [📖 프로젝트 시나리오](#프로젝트-시나리오)
4. [🧾 요구사항 명세서](#요구사항-명세서)
5. [🗺️ ERD](#erd)
6. [📋 테이블 명세서](#테이블-명세서-및-제약-조건)
7. [🏗️ 시스템 아키텍처](#시스템-구성도)
8. [📑 API 명세서](#api-명세서)
9. [✅ 개발 산출물 및 검증 (Outputs & Validation)](#개발-산출물-및-검증-outputs--validation)
10. [📄 SQL 산출물](#sql-산출물)
11. [🧪 API 단위 테스트 결과서](#api-단위-테스트-결과서)
12. [📝 프로젝트 마무리 회고 및 향후 확장 계획](#프로젝트-마무리-회고-및-향후-확장-계획)

---

<a id="프로젝트-개요-project-overview"></a>
## 📌 프로젝트 개요 (Project Overview)
- **서비스 명칭:** Subees
- **Back-end 한 줄 소개:** <!-- TODO: 예) 데이터 무결성과 상태 전이를 보장하는 통합 구독 관리 API 서버 -->

- **Back-end 담당 범위(예시)**
  - 인증/인가(JWT 등)
  - 구독/결제 도메인 CRUD + 상태 전이
  - 알림 스케줄링(선택)
  - 통계/대시보드용 집계 API(선택)
  - 공통 예외/응답 규격/로깅

---

<a id="서비스-목표"></a>
## 🎯 서비스 목표
<!-- TODO: 백엔드 관점 목표를 명확히 -->
- **데이터 무결성 보장**
  - FK / Unique / Check 제약
  - 트랜잭션 경계 설정
- **상태 역전 방지 정책**
  - 상태 전이 규칙 정의 + 검증(서버에서 강제)
- **확장 가능한 API 설계**
  - 버전 전략 / 공통 응답 포맷 / 에러 코드

---

<a id="프로젝트-시나리오"></a>
## 📖 프로젝트 시나리오
<!-- TODO: 실제 서비스 흐름에 맞는 백엔드 시나리오 작성 -->
- 예시)
  1. 사용자가 회원가입 및 로그인을 진행한다.
  2. 사용자가 구독 서비스를 등록한다.
  3. 서버는 결제 주기, 금액, 결제 예정일을 저장한다.
  4. 사용자는 등록한 구독 정보를 조회/수정/삭제할 수 있다.
  5. 결제 예정일이 가까워지면 알림 기능이 동작한다.

---

<a id="백엔드-핵심-비즈니스-로직-설명"></a>
## 🧠 백엔드 핵심 비즈니스 로직 설명
<!-- TODO: “핵심 도메인” 중심으로 작성 -->
### 핵심 도메인(예시)
- User / Auth
- Subscription
- Payment(또는 Billing)
- Notification(선택)

### 핵심 로직(예시)
- 구독 등록 시: 중복 구독 검증(Unique) → 결제일 정책 검증 → 저장
- 구독 상태 변경 시: 상태 전이 테이블/규칙 기반 검증 후 변경
- 결제일 임박 알림: 스케줄러/배치/이벤트 기반 처리

---

<a id="기술-스택-tech-stack"></a>
## 🧰 기술 스택 (Tech Stack)
| 분류 | 기술 |
|---|---|
| Framework | <!-- TODO: Spring Boot --> |
| Language | <!-- TODO: Java --> |
| DB | <!-- TODO: MariaDB --> |
| ORM | <!-- TODO: JPA / QueryDSL --> |
| Docs | <!-- TODO: Swagger / Postman --> |
| Infra | <!-- TODO: Docker / AWS / GitHub Actions --> |
| Test | <!-- TODO: JUnit5 / RestAssured 등 --> |

---

<a id="framework--language"></a>
## 🧩 Framework & Language
- Spring Boot: <!-- TODO: 버전/채택 이유 -->
- Java: <!-- TODO: 버전 -->

---

<a id="database--orm"></a>
## 🗄️ Database & ORM
- MariaDB: <!-- TODO: 버전/설정 -->
- JPA: <!-- TODO -->
- QueryDSL: <!-- TODO -->

---

<a id="배포-및-인프라"></a>
## 🚀 배포 및 인프라
<!-- TODO: 실제 배포 방식으로 수정 -->
- Docker: <!-- TODO -->
- AWS: <!-- TODO (EC2/RDS/S3 등) -->
- GitHub Actions: <!-- TODO (CI/CD 파이프라인) -->

---

<a id="요구사항-및-데이터-설계-requirements--db-design"></a>
## 🧾 요구사항 및 데이터 설계 (Requirements & DB Design)

<a id="요구사항-명세서"></a>
### 🧩 요구사항 명세서 (기능/비기능)
🔗 <!-- TODO: 요구사항 문서 링크 -->

- 기능 요구사항(예시)
  - 회원가입/로그인
  - 구독 등록/수정/삭제
  - 결제일 알림
- 비기능 요구사항(예시)
  - 인증/인가 정책
  - 동시성/트랜잭션 규칙
  - 로깅/모니터링

<a id="erd"></a>
### 🗺️ ERD (Entity Relationship Diagram)
<!-- TODO: ERD 이미지로 교체 -->
<div align="center">

<img src="./이미지/포스터/포스터예시.jpg" width="720" alt="ERD (placeholder)" />

</div>

<a id="테이블-명세서-및-제약-조건"></a>
### 📋 테이블 명세서 및 제약 조건(Constraints)
🔗 <!-- TODO: 테이블 명세서 링크 -->

- PK/FK/Unique/Index 정책
- 상태 전이 관련 제약(예: 허용 전이만 가능하도록 서버에서 검증)
- 삭제 정책(Soft delete 여부)

---

<a id="시스템-아키텍처-및-api-system-architecture--api"></a>
## 🏗️ 시스템 아키텍처 및 API (System Architecture & API)

<a id="시스템-구성도"></a>
### 🧱 시스템 구성도 (System Architecture)
<!-- TODO: 아키텍처 다이어그램으로 교체 -->
<div align="center">

<img src="./이미지/포스터/포스터예시.jpg" width="720" alt="System Architecture (placeholder)" />

</div>

<a id="api-명세서"></a>
### 📑 API 명세서 (Swagger 또는 Postman)
- Swagger: 🔗 <!-- TODO: Swagger URL -->
- Postman: 🔗 <!-- TODO: Postman 링크 -->

<a id="상태-코드-전이-규칙"></a>
### 🔁 상태 코드 전이 규칙 (Status Code Transition)
<!-- TODO: 실제 상태 전이 규칙으로 수정 -->
| Domain | From | To | Rule |
|---|---|---|---|
| Subscription | ACTIVE | CANCELED | <!-- TODO: 결제 종료일/환불 정책 등 --> |
| Subscription | PENDING | ACTIVE | <!-- TODO --> |

---

<a id="개발-산출물-및-검증-outputs--validation"></a>
## ✅ 개발 산출물 및 검증 (Outputs & Validation)

<a id="sql-산출물"></a>
### 📄 SQL 산출물 (DDL, 핵심 프로시저/트리거)
- DDL: `./sql/ddl.sql` <!-- TODO: 경로/파일명 수정 -->
- Trigger/Procedure: `./sql/triggers.sql` <!-- TODO -->

<a id="api-단위-테스트-결과서"></a>
### 🧪 API 단위 테스트 결과서 (DB 적재 확인 및 조회 쿼리 증빙)
🔗 <!-- TODO: 테스트 결과서 링크 -->
- 케이스: 등록/조회/상태전이/예외 케이스
- DB 적재 증빙: 쿼리 캡처/로그

<a id="성능동시성-테스트-보고서"></a>
### ⚡ 성능/동시성 테스트 보고서 (선택 사항)
🔗 <!-- TODO: 보고서 링크(없으면 생략 가능) -->
- 동시 요청 시나리오
- 트랜잭션/락 정책 결과

---

<a id="회고-및-트러블슈팅-troubleshooting--review"></a>
## 🛠️ 회고 및 트러블슈팅 (Troubleshooting & Review)

<a id="주요-트러블슈팅"></a>
### 🐛 주요 트러블슈팅
| 이슈 | 원인 | 해결 | 참고 |
|---|---|---|---|
| N+1 문제 | <!-- TODO --> | <!-- TODO: fetch join / entity graph / DTO projection --> | <!-- TODO: PR/Issue 링크 --> |
| 참조 무결성 오류 | <!-- TODO --> | <!-- TODO: FK 설계/삭제 정책 변경 --> | <!-- TODO --> |

<a id="프로젝트-마무리-회고-및-향후-확장-계획"></a>
### 📝 프로젝트 마무리 회고 및 향후 확장 계획
- 회고(예시): <!-- TODO -->
- 확장(예시)
  - 캐시/레디스 도입
  - 이벤트 기반 알림 처리
  - CQRS 분리/읽기 성능 최적화

---

## 🧾 License
<!-- TODO: 라이선스 -->
