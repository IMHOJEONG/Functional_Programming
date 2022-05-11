package exercise2_1
def fib(n: Int): Int = {
    @annotation.tailrec
    def go(n1: Int, n2: Int, index: Int): Int = 
        if (index == n) n1
        else go(n2, (n1)+(n2), index+1)
    go(0,1,0)
}