### Functional Programming

- 불순한 요소? 

: 변이 가능 변수, 예외, 전통적인 입출력 

: 불순한 요소를 제거한다?

### Using the Scala installer or sbt? 

- build tool for Scala

- 오류가 발생하고 있어서 skip

### VSCode with Metals

1. Marketplace에서 `Metals` extension을 다운받습니다. 

2. Metal 플러그인을 이용해서 VS-code에서 사용할 수 있습니다. 

###

```scala
findFirst(Array(7,9,13), (x: Int) => x == 9)
res2: Int = 1
```

- new 같은 키워드 없이 배열을 생성할 수 있습니다 


### 함수적 자료구조?

- 순수함수는 자료를 그 자리에서 변경 or 기타 부수 효과를 수행하는 일이 없어야 합니다. 

- 정의에 의해 불변(immutable) 

- 자료 형식을 도입할 때는 `trait` 키워드를 사용합니다. 

  - trait : 특질 -> 하나의 추상 인터페이스 
    
    - 필요하다면 일부 메서드의 구현을 담을 수도 있습니다. 

- trait 앞에 sealed를 붙이는 것 => 이 특질의 모든 구현이 반드시 이 파일 안에 선언되어 있어야 함을 의미 

---

### reduce : 환원 