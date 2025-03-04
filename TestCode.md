# Test Code

## 자바 단위 테스팅 프레임워크
- https://junit.org/junit5/docs/current/user-guide/#writing-tests
- https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-writing-parameterized-tests/

## AssertJ
- 테스트 코드 가독성을 높여주는 자바 라이브러리
- https://assertj.github.io/doc/#assertj-core-assertions-guide

### 작성하는 이유
1. 문서화 역할
2. 코드에 결함을 발견하기 위함
3. 리팩토링 시 안정성 확보
4. 테스트하기 쉬운 코드를 작성하다보면 더 낮은 결합도를 가진 설계를 얻을 수 있음

## TDD (Test Driven Development)
- 프로덕션 코드보다 테스트 코드를 먼저 작성하는 개발 방법
- TFD (Test First Development) + 리팩토링
- 기능 동작을 검증 (메소드 단위)

## BDD (Behavior Driven Development) 행위 주도 개발
- 시나리오 기반으로 테스트 코드를 작성하는 개발 방법
- 하나의 시나리오는 Given, When, Then 구조를 가짐