### 테스트 및 실행방법

#### 애플리케이션 실행

    - InvestmentApplication 실행

#### 기능 테스트
    - Mock Data 설정 
      H2 DB에 테스트용 상품 등록(com.investment.config.DataInitConfig)

    - rest-client/local 폴더에 파일 실행
      1) 투자 : invest.http
      2) 상품조회 : product_sales.http
      3) 나의 투자상품 조회 : member_invested_details.http


#### 단위 테스트
    - test/java 디렉토리 참조

### 설계
    1. 패키지 구성 
       도메인 분리를 감안하여 investment 메인 패키지내 'invest' 도메인 과 'lookup' 도메인을 분리함

    2. 투자 상품 동시성 제어
       상품 판매 오픈될때 동시성 처리 및 CAS 처리를 위해 Redis (Lua Script)를 사용해 , 최대 금액 제약사항 
       처리를 수행하도록 설계 했습니다.

       총투자 금액 및 투자자수 관리는 DB 병목 문제 와 응답 실시간성을 감안하여 Spring Event를 이용해 
       비동기 처리하도록 설계 했습니다.

### 개선 사항
    1) 상품 및 투자내역 조회 서비스 와 상품투자 API는 별도 분리한다.
    2) kakfa을 이용해 비동기 Task를 분리한다.
    3) 단위 테스트를 추가고 , 리팩토링한다.

