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




