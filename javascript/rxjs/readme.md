### RxJS

[공식 Guide](https://rxjs.dev/guide/overview)를 참고하였습니다. 


- 비동기, 이벤트 기반 프로그래밍을 `Observable sequences`를 사용

- `Observable` : 하나의 핵심 유형 

- `Satellite` : `Observer`, `Scheduler`, `Subjects` & Array 메소드(map, filter, reduce, every 등)에서 영감을 받은 연산자를 제공 
  - 비동기 이벤트를 컬렉션으로 처리할 수 있도록 한다

- 이벤트 용 lodash?

- ReactiveX : Observer Pattern + Iterator Pattern과 결합 & 함수형 프로그래밍을 컬렉션과 결합

  - ==> 이벤트 시퀀스를 관리하는 이상적인 방법?

### 비동기 이벤트 관리를 해결하는 RxJS의 필수 개념

- `Observable` : 미래 값 또는 이벤트의 호출 가능한 컬렉션에 대한 아이디어를 나타냅니다. 

- `Observer` : Observable이 전달하는 값을 수신하는 방법을 알고 있는 callback의 모음 

- `Subscription` : Observable의 실행을 나타내며 주로 실행을 취소하는 데 유용합니다. 

- `Operators` : map, filter, concat, reduce 등과 같은 작업으로 컬렉션을 처리하는 기능적 프로그래밍 스타일을 가능하게 하는 순수 함수 

- `Subject` : EventEmitter와 동일하며 값 또는 이벤트를 여러 Observers들에게 멀티캐스팅하는 유일한 방법

- `Schedulers` : 동시성을 제어하는 중앙 집중식 dispatcher, 예를 들어 => setTimeout, requestAnimationFrame, 기타 연산이 발생하면 조정할 수 있습니다. 

### Purity = 순수

- RxJS를 강력하게 만드는 것 => 순수함수를 사용해 값을 생성하는 기능 

- 코드에서 오류가 발생하기 쉽습니다

- 일반적으로 우리는 코드의 다른 부분이 상태를 엉망으로 만들 수 있는 불순한 함수를 만들기 때문입니다. 

```javascript
let count = 0;
document.addEventListener('click', () => console.log(`Clicked ${++count} times`));
```

- `scan` 연산자는 Array의 `reduce`와 동일하게 작동합니다. 

- callback에 노출되는 값을 사용합니다. 

- callback의 반환 값은 다음에 callback이 실행될 때 노출되는 다음 값이 됩니다. 

### Flow - 흐름 

RxJS에는 이벤트가 Observable을 통해 흐르는 방식을 제어하는 데 도움이 되는 연산자가 있습니다. 

- `filter`, `delay`, `debounceTime`, `take`, `takeUntil`, `distinct`, `distinctUntilChanged` 등등 흐름 제어 연산자가 존재합니다. 

### 값 

Observable을 통해 전달된 값을 변환할 수 있습니다. 

- 값 생성 연산자로는 `pluck`, `pairwise`, `sample` 등이 있습니다. 

---

### Collection Pipeline 

[컬렉션 파이프라인 참고](https://martinfowler.com/articles/collection-pipeline/#NestedOperatorExpressions)

- 컬렉션 파이프라인이란 무엇인가?

  - 한 작업의 출력으로 컬렉션을 가져오고 다음 작업에 공급해 구성하는 일련의 작업으로 일부 계산을 구성하는 프로그래밍 패턴 

  - 이 패턴은 함수형 프로그래밍, 람다가 있는 객체 지향 언어에서도 일반적입니다. 

- Unix를 시작할 때 이 패턴을 접할 수 있다???

```shell

grep -l 'nosql' bliki/entries





```






