import { fromEvent, scan, throttleTime, map } from 'rxjs';

fromEvent(document,'click').subscribe(
    ()=> console.log('Clicked using RxJS')
)

fromEvent(document, 'click')
    .pipe(scan((count)=>count+1,0))
    .subscribe((count) => console.log(`Clicked ${count} times`))

fromEvent(document, 'click')
    .pipe(
        throttleTime(1000),
        scan((count) => count + 1, 0)
    )
    .subscribe((count) => console.log(`throttle Clicked ${count}`))

fromEvent(document, 'click')
    .pipe(
        throttleTime(1000),
        map((event)=>event.clientX),
        scan((count, clientX)=> count+clientX, 0)
    )
    .subscribe((count)=>console.log(count))



