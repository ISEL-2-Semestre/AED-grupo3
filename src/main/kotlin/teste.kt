fun multiply(M:Int,m:Int){
    var m = m
    var M = M
    var p = 0
    var temp = 0
    while(m>0){
        p = p + M
        m = m - 1
    }
    println(p)
}

fun main() {
    multiply(2,4)
}