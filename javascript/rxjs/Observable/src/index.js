import { Observable } from 'rxjs';



(()=>{
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
})();

(()=>{

    function foo() {
        console.log('Hello');
        return 42;
    }
    
    const x = foo.call(); // foo()와 같음
    console.log(x);
    
    const y = foo.call(); // foo()와 같음
    console.log(y);
    
    
    const foo1 = new Observable(
        subscriber => {
            console.log(`Hello`);
            subscriber.next(42);
        }
    );
    
    foo1.subscribe(x=>{
        console.log(x);
    });
    
    foo1.subscribe(y=>{
        console.log(y)
    })
})();


(()=>{
    console.log('before')
    console.log(foo.call())
    console.log('after')
    
    
    console.log('before')
    foo1.subscribe(x=>{
        console.log(x)
    })
    console.log('after')
})();

(()=>{
    function foo() {
        console.log('Hello');
        return 42;
        return 100; // 죽은 코드, 절대 실행되지 않습니다. 
    }

    const foo1 = new Observable(
        subscriber => {
            console.log('Hello');
            subscriber.next(42);
            subscriber.next(100); // 다른 값을 반환
            subscriber.next(200); // 또 다른 값의 반환
            setTimeout(()=>{
                subscriber.next(300); // 비동기적으로 발생합니다. 
            }, 1000);
        }
    )   

       
    console.log('before')
    foo1.subscribe(x=>{
        console.log(x)
    })
    console.log('after')

})();


(()=>{

    const observable = new Observable(
        function subscribe(subscriber) {
            const id = setInterval(()=>{
                subscriber.next(hi)
            }, 1000);
        }
    )


})