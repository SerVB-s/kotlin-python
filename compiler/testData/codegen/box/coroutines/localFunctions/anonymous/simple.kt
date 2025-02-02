// TODO: muted for Python because it was muted for JS. Once Python doesn't piggy-back on JS, investigate if it can be re-enabled for Python.
// IGNORE_BACKEND: PYTHON
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JVM_IR, JS_IR
// IGNORE_BACKEND: JS_IR_ES6
// IGNORE_BACKEND: JVM, JS, NATIVE
// WITH_RUNTIME
// WITH_COROUTINES
import helpers.*
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

suspend fun callLocal(): String {
    val local = suspend fun() = "OK"
    return local()
}

fun builder(c: suspend () -> Unit) {
    c.startCoroutine(EmptyContinuation)
}

fun box(): String {
    var res = "FAIL"
    builder {
        res = callLocal()
    }
    return res
}
