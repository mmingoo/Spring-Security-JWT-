# Spring Security와 JWT를 이용한 인증 시스템
- 해당실습은 유튜브 '개발자 유미' 강의를 따라서 진행한 실습입니다.

  
## 프로젝트 개요
이 프로젝트는 Spring Boot, Spring Security, JWT를 활용하여 사용자 인증 시스템을 구현한 예제입니다.

## 주요 기능
- 회원가입 및 로그인
- JWT 기반 인증
- 역할 기반 접근 제어(RBAC)
- CORS 설정

## 기술 스택
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- MySQL

## 프로젝트 구조

### 설정 (Configuration)
- **CorsMvcConfig**: CORS 설정을 관리
- **SecurityConfig**: Spring Security 설정

### 컨트롤러 (Controller)
- **AdminController**: 관리자 전용 페이지 접근
- **JoinController**: 회원가입 기능 처리
- **MainController**: 메인 페이지 및 인증 정보 출력

### 도메인 (Domain)
- **UserEntity**: 사용자 정보를 표현하는 엔티티

### DTO (Data Transfer Object)
- **CustomUserDetails**: UserDetails 인터페이스 구현체
- **JoinDto**: 회원가입 요청 정보

### JWT (JSON Web Token)
- **JWTFilter**: JWT 검증 및 인증 처리 필터
- **JWTUtil**: JWT 생성 및 검증 유틸리티
- **LoginFilter**: 로그인 요청 처리 필터

### 저장소 (Repository)
- **UserRepository**: 사용자 데이터 접근

## 인증 흐름
1. 사용자 회원가입: `/join` 엔드포인트를 통해 회원가입
2. 사용자 로그인: `/login` 엔드포인트로 로그인 요청
3. JWT 토큰 발급: 로그인 성공 시 JWT 토큰 발급
4. 인증 요청: 발급받은 토큰을 Authorization 헤더에 포함하여 요청
5. 토큰 검증: JWTFilter에서 토큰 유효성 검증
6. 권한 확인: 인증된 사용자의 권한에 따라 리소스 접근 제어

## API 엔드포인트
- **POST /join**: 회원가입
- **POST /login**: 로그인 및 JWT 토큰 발급
- **GET /main**: 인증된 사용자만 접근 가능
- **GET /admin**: ADMIN 역할을 가진 사용자만 접근 가능

## 보안 기능
- 비밀번호 암호화: BCryptPasswordEncoder 사용
- CSRF 보호: JWT 사용으로 비활성화
- 세션 관리: STATELESS 방식으로 설정
- CORS 설정: 특정 출처(localhost:3000)에서의 요청만 허용

## JWT 구성
- 사용자 식별자(username)
- 사용자 역할(role)
- 토큰 만료 시간: 10시간
