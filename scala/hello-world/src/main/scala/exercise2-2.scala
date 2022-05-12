package exercise2_2

def isSorted[A](as: Array[A], ordered: (A,A)=>Boolean): Boolean = {
    @annotation.tailrec
    def loop(n: Int, check: Boolean): Boolean = 
        if(n >= as.length-1) check
        else if (ordered(as(n), as(n+1))) {
            loop(n+1, true)
        }
        else {
            loop(n+1, false)
        }
    loop(0, false)
}
