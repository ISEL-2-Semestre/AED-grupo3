fun powerrrrbaby(a:Int,n:Int):Int{
    return if (n==0) 1
    else a*powerrrrbaby(a,n-1)

}

fun power2(a:Int, n:Int):Int{
    if(n==0) return 1
    val two = power2(a,n/2)
    return if (n%2 == 0) two
    else two*two*a

}

//desafio