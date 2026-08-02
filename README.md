# 🥗 얌얌코치 (YAMYAM COACH)

> **건강한 식습관 형성을 위한 Java Console 기반 식단 관리 프로그램**

---

# 📌 프로젝트 소개

얌얌코치는 사용자의 건강 프로필을 기반으로 식단을 기록하고 관리할 수 있는 콘솔 프로그램입니다.

회원은 자신의 건강 정보를 등록하고 식단을 기록하여 영양 성분을 분석할 수 있으며,
관리자는 회원 정보를 조회 및 관리할 수 있습니다.

음식 데이터는 식품 DB를 활용하여 제공되며, 회원 정보와 식단 기록은 JSON 파일로 저장됩니다.

---

# 👨‍💻 개발 환경

| 항목 | 내용 |
|------|------|
| Language | Java |
| IDE | Eclipse |
| Library | Gson 2.10.1 |
| Data | CSV, JSON |
| Version Control | Git / GitHub |

---

# 📁 프로젝트 구조

```
Java_P_PJT
├── DTO
│   ├── Food.java
│   ├── HealthProfile.java
│   ├── MealItem.java
│   ├── MealRecord.java
│   └── User.java
│
├── Manager
│   ├── FoodManager.java
│   └── UserManager.java
│
├── Repository
│   ├── FoodRepository.java
│   └── UserRepository.java
│
├── Util
│   ├── FileUtil.java
│   └── JsonUtil.java
│
└── Menu
    └── MainMenu.java
```

---

# 🏗️ 클래스 다이어그램

> 클래스 다이어그램 이미지 삽입


---

# ⚙️ 주요 기능

## 👤 회원 기능

- 회원가입
- 로그인 / 로그아웃
- 내 정보 조회
- 이름 수정
- 비밀번호 수정
- 건강 프로필 등록
- 건강 프로필 수정
- 회원 탈퇴

---

## 🍽️ 음식 및 식단 기능

### 음식 검색

- 음식명 검색
- 검색 결과 최대 5개 출력
- 음식 선택

### 식단 관리

- 식단 등록
- 음식 여러 개 추가 가능
- 식단 수정
- 식단 삭제
- 식단 목록 조회
- 식단 상세 조회

### 영양 분석

- 총 칼로리 계산
- 탄수화물 계산
- 단백질 계산
- 지방 계산
- 당류 계산
- 나트륨 계산

### 데이터 관리

- CSV 음식 DB 로드
- 식단 기록 JSON 저장
- 식단 기록 JSON 불러오기

---

## 👑 관리자 기능

- 아이디로 회원 조회
- 회원번호로 회원 조회
- 이름으로 회원 조회
- 전체 회원 조회
- 회원 삭제

---

# 📂 데이터 관리

## 회원 정보

회원 정보는 JSON 파일에 저장됩니다.

```
users.json
```

프로그램 실행 시

```
users.json
        │
        ▼
ArrayList<User>
```

회원가입, 수정, 삭제 시

```
ArrayList<User>
        │
        ▼
users.json
```

으로 자동 저장됩니다.

---

## 음식 데이터

음식 정보는 공공 식품 데이터를 CSV 파일에서 불러옵니다.

```
Food.csv
        │
        ▼
List<Food>
```

---

## 식단 데이터

식단 기록은 JSON 파일로 저장됩니다.

```
mealRecords.json
```

프로그램 실행 시

```
mealRecords.json
        │
        ▼
List<MealRecord>
```

으로 불러오며,

식단 등록/수정/삭제 시 다시 JSON으로 저장됩니다.

---

# 🔄 프로그램 구조

```
사용자
   │
   ▼
MainMenu
   │
   ▼
Manager
   │
   ▼
Repository
   │
   ▼
Util
   │
   ▼
CSV / JSON File
```

---

# 📦 패키지별 역할

## Menu

- 콘솔 메뉴 출력
- 사용자 입력 처리
- Manager 호출

---

## DTO

프로그램에서 사용하는 데이터 객체

- User
- HealthProfile
- Food
- MealItem
- MealRecord

---

## Manager

프로그램의 핵심 비즈니스 로직 처리

### UserManager

- 회원가입
- 로그인
- 로그아웃
- 회원 조회
- 회원 수정
- 회원 삭제
- 건강 프로필 관리

### FoodManager

- 음식 검색
- 식단 생성
- 식단 조회
- 식단 수정
- 식단 삭제
- 식단 분석

---

## Repository

데이터 관리 계층

### UserRepository

- 회원 저장
- 회원 불러오기

### FoodRepository

- CSV 음식 DB 로드
- 음식 검색
- 식단 저장
- 식단 조회
- 식단 삭제
- JSON 저장 및 불러오기

---

## Util

### JsonUtil

- 객체 ↔ JSON 변환

### FileUtil

- 파일 읽기
- 파일 쓰기

---

# 🧩 주요 DTO

## User

| 필드 | 설명 |
|------|------|
| id | 회원 고유번호 |
| userId | 로그인 아이디 |
| password | 비밀번호 |
| name | 이름 |
| profile | 건강 프로필 |

---

## HealthProfile

| 필드 | 설명 |
|------|------|
| height | 키 |
| weight | 몸무게 |
| goal | 건강 목표 |

---

## Food

| 필드 | 설명 |
|------|------|
| foodCode | 식품 코드 |
| foodName | 식품명 |
| servingSize | 1회 제공량 |
| calorie | 칼로리 |
| protein | 단백질 |
| carbohydrate | 탄수화물 |
| fat | 지방 |
| sugar | 당류 |
| sodium | 나트륨 |

---

## MealItem

식단에 포함되는 음식 1개를 의미합니다.

| 필드 | 설명 |
|------|------|
| food | 음식 정보 |
| amountGram | 섭취량(g) |

---

## MealRecord

식단 기록 1개를 의미합니다.

| 필드 | 설명 |
|------|------|
| mealId | 식단 번호 |
| userId | 회원 번호 |
| date | 날짜 |
| mealType | 아침 / 점심 / 저녁 / 간식 |
| items | 섭취 음식 목록 |

---

# 👥 담당 기능

## 👤 User

- User DTO
- HealthProfile DTO
- UserManager
- UserRepository
- JsonUtil
- FileUtil
- 회원가입
- 로그인 / 로그아웃
- 회원 조회
- 회원 수정
- 회원 삭제
- 건강 프로필 관리
- 회원 데이터 JSON 저장 및 불러오기

---

## 🍽️ Food

- Food DTO
- MealItem DTO
- MealRecord DTO
- FoodManager
- FoodRepository
- CSV 음식 DB 로드
- 음식 검색
- 식단 생성
- 식단 조회
- 식단 수정
- 식단 삭제
- 영양 성분 분석
- 식단 JSON 저장 및 불러오기

---

# 🚀 향후 개선 사항

- BMI 자동 계산
- 목표 기반 식단 추천
- 날짜별 식단 조회
- 음식 즐겨찾기 기능
- 입력 예외 처리 강화
- 비밀번호 암호화
- 관리자 권한 세분화
- 콘솔 UI 개선

---

# 💬 프로젝트 소감

> 이번 프로젝트를 통해 Java의 객체지향 설계와 클래스 간의 역할 분리에 대해 깊이 이해할 수 있었습니다.
특히 Manager, Repository, Util 구조를 적용하며 데이터 관리의 흐름을 직접 구현해 본 것이 가장 큰 경험이었습니다.
또한 Git을 이용한 협업과 역할 분담을 경험하면서 팀 프로젝트의 중요성을 배울 수 있었습니다.
앞으로는 예외 처리와 사용자 편의성을 더욱 고려하여 완성도 높은 프로그램을 개발해 보고 싶습니다.

---

# 👨‍👩‍👧‍👦 Team

| 이름 | 담당 |
|------|------|
| 최경환 | Food 기능 개발 |
| 서주영 | User 기능 개발 |
