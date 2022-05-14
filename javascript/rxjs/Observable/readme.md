### Observable

[공식 문서](https://rxjs.dev/guide/observable)

- 여러 값의 지연 Push 컬렉션입니다. 

|   | Single | Multiple |
|---|---|---|
| Pull | Function | Iterator |
| Push | Promise | Observable |

- Observable를 호출하고 값을 보기 위해서는, subscribe 작업이 필요합니다. 

```javascript

import { Observable } from 'rxjs';

const observable = new Observable(
    subscriber => {
        subscriber.next(1);
        subscriber.next(2);
        subscriber.next(3);
        setTimeout(()=>{
            subscriber.next(4);
            subscriber.complete();
        }, 1000);
    }
);

console.log(`before subscribe`);

observable.subscribe({
    next(x) {
        console.log(`got value ${x}`)
    },

    error(err) {
        console.error(`something wrong occurred: ${err}`)
    },

    complete() {
        console.log(`done`)
    }
})

console.log(`after subscribe`);
```

- Pull vs Push 

  - pull & push는 데이터 Producer와 데이터 Consumer가 통신하는 방법을 설명하는 두 가지 다른 프로토콜입니다. 

  - 1) pull 

    - pull 시스템에서, consumer는 데이터 producer로부터 데이터를 받는 시기를 결정합니다. 

    - producer 자체는 데이터가 consumer에게 언제 전달되는지 알지 못합니다. 

    - 모든 Javascript 함수는 pull 시스템입니다. 
    
    - 함수는 데이터 생성자이며 함수를 호출하는 코드는 호출에서 단일 반환 값을 pulling(가져오기)하여 데이터를 소비합니다. 

    - ES2015에서는 또 다른 유형의 Pull 시스템인 generator 함수와 반복자를 도입했습니다. (function* )

      - iterator.next()를 호출하는 코드는 반복자(producer)에서 여러 값을 pulling(가져오는) consumer입니다.


|   | Producer | Consumer |
|---|---|---|
| Pull | `Passive` : 요청 시에 데이터를 생성합니다. | `Active` : 데이터가 요청되는 시점을 결정합니다. |
| Push | `Active` : 자체 속도로 데이터를 생성합니다. | `Passive` : 수신된 데이터에 반응합니다. |

  - 2) push 

    - push 시스템에서, producer는 consumer에게 데이터를 보낼 시기를 결정합니다. consumer는 언제 해당 데이터를 받을지 모릅니다. 

    - Promise가 오늘날 Javascript에서 가장 일반적인 유형의 push 시스템입니다. 

      - Promise(Producer)는 확인된 값을 등록된 콜백(Consumer)에 전달하지만, 함수와 달리 해당 값이 콜백에 push되는 시점을 정확하게 결정하는 것은 Promise입니다. 

    - RxJS는 JS용 새로운 push 시스템인 Observable을 도입했습니다. 

      - Observable은 여러 값의 producer로서, 이를 Observer(Consumer)에게 push합니다.

        - 함수는 호출 시 단일 값을 동기적으로 반환하는 느리게 평가되는 연산입니다. 

        - generator는 반복에서 무한 값으로 0을 동기적으로 반환하는 느리게 평가된 연산입니다. 

        - Promise는 결국 단일 값을 반환할 수도 있고, 반환하지 않을 수도 있는 연산입니다. 

        - Observable은 호츨된 시간부터 무한 값에 대해 동기 or 비동기적으로 0을 반환할 수 있는 느리게 평가된 연산입니다.

- 함수의 일반화로서의 Observable

  - Observable은 EventEmitter와 같지 않으며 여러 값에 대한 Promise와도 다릅니다.

  - Observable은 RxJS Subject를 사용해 multicast되는 경우처럼, EventEmitter처럼 작동할 수 있지만, 일반적으로 EventEmitter처럼 작동하지 않습니다. 

  - `Observable은 인수가 0인 함수와 비슷하지만, 여러 값을 허용하도록 일반화합니다.`

  - 함수와 Observable이 모두 지연 연산이기 때문에 발생합니다. 

    - 함수를 호출하지 않으면 console.log('Hello')가 발생하지 않습니다. 

    - 또한, Observable을 사용하여 subscription을 통해 호출하지 않아도 console.log('Hello')가 발생하지 않습니다. 

    - 호출 또는 subscription은 격리된 작업입니다. 

      - 두 개의 함수 호출은 두 개의 개별 부작용을 가져오고, 두 개의 Observable subscription은 두 개의 개별 부작용을 가져옵니다. 

      - 부작용을 공유하고 구독자의 존재여부와 관계없이 즉시 실행되는 EventEmitter와 달리, Observable은 공유 실행이 없고 지연됩니다. 

  - `Observable을 subscription하는 것은 함수를 호출하는 것과 유사합니다`


  - `Observable은 동기식 또는 비동기식으로 값을 전달할 수 있습니다`

    - Observable과 함수의 차이점 : Observable은 시간이 지남에 따라 여러 값을 반환할 수 있지만, 함수는 할 수 없습니다. 

    - func.call() : 하나의 값을 동기적으로 제공을 의미 

    - observable.subscribe() : 동기식 or 비동기식으로 원하는 양의 값을 제공합니다를 의미 

---

- Observable을 깊게?

  - Observable은 새로운 Observable 또는 생성 연산자를 사용해 생성되고, Observer에 subscribe되고 Observer에 next, error, complete 알림을 전달하기 위해 실행되며, 실행은 폐기될 수 있습니다. 

  - 4가지 측면은 모두 Observable 인스턴스에 인코딩되어 있지만, 일부는 Observer & Subscription과 같은 다른 유형과 관련되어 있습니다. 

  - 핵심 Observable 문제 

    - 1) Observable 생성

    - 2) Observable subscription

    - 3) Observable 실행 

    - 4) Observable 폐기

- 1) Observables 생성 

  - Observable 생성자는 하나의 인수인 subscribe 함수를 사용합니다. 

  - 새로운 Observable로 Observable을 생성할 수 있습니다. (of, from, interval 등과 같은 생성 함수를 사용하여 생성됩니다.)

  - subscribe 함수는 Observable을 설명하는 가장 중요한 부분 

- 2) Observables을 subscribe

  - `new Observable(function subscribe(subscriber){})`에서 `observable.subscribe`와 `subscribe`가 같은 이름을 갖는다는 것은 우연이 아닙니다 

  - 라이브러리에서는 서로 다르지만 실용적인 목적을 위해 개념적으로 동일한 것으로 간주할 수 있습니다. 

  ```javascript
  observable.subscribe(x => console.log(x))
  ```

  - 동일한 Observable의 여러 Observer 간에 subscribe 호출이 공유되지 않는 방법을 보여줍니다. 

  - Observer로 observable.subscribe를 호출할 때 new Observable(function subscribe(subscriber){})의 subscribe 함수는 해당 subscription을 하고 있는 대상에 대해 실행됩니다. 

  - `observable.subscribe`에 대한 각 호출은 해당 subscription을 하고 있는 대상에 대한 자체 독립 설정을 트리거합니다. 

  - Observable을 subscription하는 것은 데이터가 전달될 콜백을 제공하는 함수를 호출하는 것과 같습니다. 

    - addEventListener / removeEventListener와 같은 이벤트 핸들러 API와 크게 다릅니다?

    - `observable.subscribe`를 사용하면 주어진 Observer가 Observable에 리스너로 등록되지 않습니다. 

    - Observable은 연결된 Observers 목록을 유지 & 관리하지도 않습니다. 

    - subscription 호출은 단순히 Observable execution을 시작하고 해당 실행의 Observer에게 값이나 이벤트를 전달하는 방법입니다. 

- 3) Observable 실행

  - `new Observable(function subscribe(subscriber){})` 내부의 코드는 구독하는 각 Observer에 대해서만 발생하는 지연 계산입니다. 

  - 실행은 시간이 지남에 따라 동기식 또는 비동기식으로 여러 값을 생성합니다. 

  - Observable 실행이 전달할 수 있는 값에는 세 가지 유형이 존재합니다. 

    - 1) Next 알림 : 숫자, 문자열, 객체 등과 같은 값을 보냅니다. 

    - 2) Error 알림 : Javascript 오류 또는 예외를 보냅니다.

    - 3) Complete 알림 : 값을 보내지 않습니다. 

  - Next 알림은 가장 중요하고 가장 일반적인 유형

    - subscriber에게 전달되는 실제 데이터를 나타냅니다. 

  - Error 및 Complete 알림 : Observable 실행 중에 한 번만 발생할 수 있으며 둘 중 하나만 존재할 수 있습니다. 

  - 이러한 제약 조건은 정규 표현식으로 작성된 Observable 문법 or 계약에서 가장 잘 나타납니다.

  - Observable 실행에서 0에서 무한한 Next 알림이 전달될 수 있습니다. 

    ```text
    next*(error|complete)?
    ```

    - Error 또는 Complete 알림이 전달되면 이후에 다른 어떤 것도 전달될 수 없습니다. 







