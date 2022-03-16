# java-blackjack

블랙잭 미션 저장소

## 기능 요구 사항

### 전체 진행

`이름 받기 -> 기본 카드 공개 -> 플레이어 카드 추가 여부 -> 딜러 추가 카드 여부 -> 카드 및 점수 출력 -> 최종 승패 출력`

## View

### 입력

- [x] 이름 받기
    - [x] 쉼표 기준으로 받기
    - [x] `[예외]` 플레이어 이름은 공백이 아니어야한다.

- [ ] 플레이어 별 배팅 금액 받기
    - [x] 배팅 금액은 음수가 아니어야한다.
    - [x] 숫자여야한다.

- [x] 플레이어 카드 추가 여부
    - [x] y또는 n으로만 입력 받기
    - [x] y일 경우에는 카드 한장 더 추가 및 추가 여부 물어보기
    - [x] n일 경우에는 카드 그만 받기
    - [x] `[예외]` y또는 n 이외의 값이 들어왔을 경우

### 출력

- [x] 기본 카드 공개
    - [x] 플레이어와 딜러에게 카드 2장 지급
    - [x] 플레이어는 카드 2장 출력
    - [x] 딜러는 1장만 출력

- [x] 카드 및 점수 결과 출력

[//]: # (- [x] 최종 승패 출력)

[//]: # (    - [x] 플레이어들의 승패 여부)

[//]: # (    - [x] 딜러의 전적)

- [ ] 최종 수익 출력
    - [ ] 딜러의 수익
    - [ ] 플레이어의 수익

## Domain

### 끗수 (Denomination)

- [x] 끗수는 A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K로 이루어져 있다.
- [x] 끗수는 각각 고유한 이름을 가지고 있다.
- [x] 끗수는 각각 가중치를 가지고 있다.
- [x] 끗수는 가중치를 조회할 수 있다.
- [x] 점수 합계를 계산해서 반환한다.
    - [x] ACE 가 포함될 경우, `ACE의 점수는 1과 11 중 하나를 사용`해서 블랙잭(21점)에 가까운 점수로 반환한다.

### 무늬 (Suit)

- [x] 무늬는 (스페이드, 하트, 다이아, 클로버) 4가지 종류로 이루어져 있다.
- [x] 무늬 이름을 조회할 수 있다.

### 카드 (Card)

카드는 (A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K) Denomination 과
(스페이드, 하트, 다이아, 클로버)라는 suit 를 가진다.

- [x] 카드 앞자리는 (스페이드, 하트, 다이아, 클로버)라는 suit로 이뤄진다.
- [x] 카드 뒷자리는 (A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K) Denomination 으로만 이뤄진다.
- [x] 카드의 점수를 조회할 수 있다.
- [x] Denomination 과 suit 가 모두 조합된 카드 52장을 만든다.
- [x] `[예외]` 조합된 카드는 유일해야 한다. (Set 활용)

### 카드들 (Cards)

카드 List 를 표현하기 위한 일급 컬렉션이다. 최소 2장을 갖는다.

- [x] 카드를 받아 List에 추가한다.
- [x] 카드들의 총점을 계산해서 반환한다.

### 카드 덱 (Deck)

게임에서 사용되는 카드 52장을 가진 카드 뭉치이다.

- [x] 카드 52장을 가진다.
- [x] 카드를 한장 씩 뽑을 수 있다.
- [x] `[예외]` 덱이 비어있는 경우 에러를 던진다.

### 플레이어 (Player)

블랙잭 게임의 참가자이다.

- [x] 플레이어는 이름을 가질 수 있다.
- [x] 플레이어는 최소 두장의 카드를 가질 수 있다.
- [x] 가진 카드의 총점을 계산하여 반환한다.
- [x] 플레이어의 총 점수가 21점 이하인 경우 hit가 가능하다.
- [x] 플레이어는 카드를 받아 합칠 수 있다.

### 플레이어들 (Players)

- [x] `[예외]` 1명 이상이어야한다.

### 딜러(Dealer)

블랙잭 게임의 진행자이다.

- [x] 딜러라는 이름을 가진다.
- [x] 딜러는 최소 두장의 카드를 가질 수 있다.
- [x] 가진 카드의 총점을 계산하여 반환한다.
- [x] 딜러의 점수가 16점 이하인 경우 카드를 받을 수 있다.
- [x] 딜러는 카드를 받아 합칠 수 있다.

### 최종 승패

- [x]  플레이어들의 승패 여부
    - [x]  플레이어 점수가 21 이하이고, 딜러 점수보다 크면 승리한다.
    - [x]  플레이어 점수가 21 이하이고, 딜러 점수보다 작으면 패배한다.
    - [x]  플레이어와 딜러 모두 Bust 이면 플레이어가 패배한다.
    - [x]  플레이어가 Bust 이면 패배이다.
    - [x]  딜러와 플레이어 점수가 같으면 무승부이다.
- [x]  딜러의 전적
    - [x]  딜러 점수가 21 이하이고, 플레이어 점수보다 크면 승리한다.
    - [x]  딜러 점수가 21 이하이고, 플레이어 점수보다 작으면 패배한다.
    - [x]  플레이어와 딜러 모두 Bust 이면 딜러가 승리한다.
    - [x]  딜러가 Bust 이면 패배이다.
    - [x]  딜러와 플레이어 점수가 같으면 무승부다

### 배팅 금액

- [ ] 딜러
    - [ ]  Bust 이면 Bust 가 아닌 플레이어 배팅 금액 반환
    - [ ]  승리하면 플레이어의 배팅 금액을 얻는다.
    - [ ]  패배하면 플레이어의 배팅 금액 반환
    - [ ]  블랙잭이면 블랙잭이 아닌 플레이어의 모든 배팅 금액을 가져감
    - [ ] 딜러와 플레이어 동시에 블랙잭이면 무승부이기 때문에 배팅 금액을 반환함
- [ ] 플레이어
    - [ ]  Bust 이면 배팅 금액을 잃는다.
    - [ ]  패배하면 배팅 금액을 잃는다.
    - [ ]  승리하면 배팅 금액을 얻는다.
    - [ ]  블랙잭이면 배팅 금액의 1.5배를 받는다.

---

## 흐름
- 플레이어 이름을 입력받음
- 플레이어 별 베팅금액을 입력 받음
- 카드를 2장씩 나눠가짐

- 플레이어 별 카드를 더 받을 건지
- 딜러의 카드 합이 17 미만이면 카드 한장을 더 받음

- 총 점 계산
- 최종 수익 산출

## 논의사항

- 플레이어가 카드를 다 받은 뒤 딜러의 카드 합이 16이하면 한장을 추가로 받는다. (17이상이면 안받음)
    - 논의사항 : 안받을 경우 `안내메시지(딜러는 17이상이라 카드를 받지 않았습니다.)` 메세지를 출력해야하는가?
- A,8,3 일 경우 블랙잭
- A를 어떻게 처리해야 하는가?

### 페어 룰

- 페어 프로그래밍 체인지 타임 (10분)
- 페어와 헤어지는 시점 (목요일 00시)
- [코드 컨벤션](https://github.com/woowacourse/woowacourse-docs/tree/master/styleguide/java)
- 커밋 작성 방법(한글)
- [프로그램 설계 방법](https://whimsical.com/sudaltest-JGN5vZ4gSkYxZGZJPjnnX3@2Ux7TurymNM4tJSA7FqU)
- [매일 저녁 하루 회고](https://necessary-sundial-178.notion.site/47c56e272fb3485d84de5e502d66ff6c)