fun test(a: Int) {
    run @f{
      if (a > 0) return@f
      else return@f 1
    }
}

fun run<T>(f: () -> T): T { return f() }