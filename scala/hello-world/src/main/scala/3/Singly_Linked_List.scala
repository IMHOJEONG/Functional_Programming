package datastructure

// 형식 A에 대한 매개변수화된 List 자료 형식 
sealed trait List[+A]

// 빈 목록을 나타내는 List 자료 생성자
case object Nil extends List[Nothing]

// 비지 않은 목록을 나타내는 또 다른 자료 생성자 
// tail은 또 다른 List[A]로, Nil일 수도 있고
// 다른 Cons일 수도 있다. 
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
    def sum(ints: List[Int]): Int = ints match {
        // 빈 목록의 합은 0
        case Nil => 0

        // x로 시작하는 목록의 합은 x 더하기 목록 나머지 부분의 합  
        case Cons(x,xs) => x + sum(xs)
    }

    def product(ds: List[Double]): Double = ds match {
        case Nil => 1.0
        case Cons(0.0, _) => 0.0
        case Cons(x, xs) => x * product(xs)
    }

    def apply[A](as: A*): List[A] = 
        if (as.isEmpty) Nil
        else Cons(as.head, apply(as.tail: _*))

}