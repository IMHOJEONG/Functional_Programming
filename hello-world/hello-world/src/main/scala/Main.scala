import exercise2_1.fib
import Array._
object Hello {
    def main(args: Array[String]) = {
        var arr = range(0,10)
        arr = arr.map(value =>{
          print(value)
          fib(value)
        })
        println()
        for(x<-arr){
          println(x)
        }
    }
}
// class Cafe {
//   def buyCoffee(cc: CreditCard, p: Payments): (Coffee, Charge) = {

//     val cup = new Coffee()
    
//     (cup, Charge(cc, cup.price))
//   }

//   def buyCoffees(cc: CreditCard, n : Int): (List[Coffee], Charge) = {
  
//     val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))

//     val (coffees, charges) = purchase.unzip(coffees, charges.reduce((c1,c2) => c1.combine(c2)))

//   }

//   def coalesce(charges: List[Charge]): List[Charge] = charges.groupBy(_.cc).values.map(_.reduce(_ combine _)).toList
// }

// case class Charge(cc: CreditCard, amount: Double) {

//   def combine(other: Charge): Charge = 

//     if (cc == other.cc) 
      
//       Charge(cc, amount + other.amount)

//     else 

//       throw new Exception("Can't combine charges to different cards")

// }