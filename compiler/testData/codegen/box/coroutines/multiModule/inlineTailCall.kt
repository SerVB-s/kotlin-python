// TODO: muted for Python because it was muted for JS. Once Python doesn't piggy-back on JS, investigate if it can be re-enabled for Python.
// IGNORE_BACKEND: PYTHON
// IGNORE_BACKEND: NATIVE
// WITH_RUNTIME
// WITH_COROUTINES
// MODULE: lib
// FILE: lib.kt
suspend inline fun foo(v: String): String = v

suspend inline fun bar(): String = foo("O")

// MODULE: main(lib, support)
// FILE: main.kt
import helpers.*
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

fun builder(c: suspend () -> Unit) {
    c.startCoroutine(EmptyContinuation)
}

fun box(): String {
    var result = ""

    builder {
        result = bar()
        result += foo("K")
    }

    return result
}
