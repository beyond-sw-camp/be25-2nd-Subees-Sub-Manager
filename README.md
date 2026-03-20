<div align="center">

<!-- ✅ 상단 배너(Front와 동일) -->
<img src="./이미지/배너/배너.svg" alt="Subees 배너" width="900" />

<br/>

<!-- ✅ 로고(Back-end는 로고 사용, 포스터 섹션은 제외) -->
<img src="./이미지/로고/Subees_Logo.png" alt="Subees Logo" width="320" />

<!-- ✅ 로고(Back-end는 로고 사용, 포스터 섹션은 제외) -->
<img src="./이미지/로고/포스터.png" alt="Subees Phoster" width="720" />

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
- **서비스 소개:** Subees는 여러 플랫폼에 흩어져 있는 구독 정보를 한곳에서 통합 관리하고, 결제일·구독 금액·주기 등을 한눈에 확인할 수 있도록 돕는 통합 구독관리 서비스입니다.
- **프로젝트 목적:** 사용자가 구독 서비스로 인해 발생하는 지출 누락, 중복 결제, 무료체험 종료 후 자동 결제와 같은 문제를 줄이고, 소비 패턴 분석과 알림 기능을 통해 더 합리적인 구독 생활을 할 수 있도록 지원하는 것을 목표로 합니다.
- **주요 제공 가치:** 구독 등록 및 관리, 결제 예정 알림, 월별/카테고리별 소비 분석, 사용자 맞춤형 추천 기능을 통해 구독 피로도를 낮추고 지출 최적화를 돕습니다.

---

<a id="서비스-목표"></a>
## 🎯 서비스 목표
- 사용자 구독 정보와 결제 데이터를 통합 관리할 수 있는 백엔드 시스템을 구축합니다.
- 결제일, 금액, 주기 등 핵심 데이터를 안정적으로 관리하여 구독 현황 조회와 소비 분석 기능을 지원합니다.
- 알림, 분석, 추천 기능에 필요한 데이터를 일관성 있게 제공하여 서비스의 확장성과 활용도를 높이는 것을 목표로 합니다.

---

<a id="프로젝트-시나리오"></a>
## 📖 프로젝트 시나리오
<div align="center">
<img src="./이미지/작업이미지/프로젝트 시나리오.png" alt="api" width="640" />
</div>

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
