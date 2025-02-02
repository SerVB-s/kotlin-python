// TODO: muted for Python because it was muted for JS. Once Python doesn't piggy-back on JS, investigate if it can be re-enabled for Python.
// IGNORE_BACKEND: PYTHON
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: IGNORED_IN_JS
// IGNORE_BACKEND: JS_IR
// IGNORE_BACKEND: JS_IR_ES6
// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS, NATIVE
// SAM_CONVERSIONS: CLASS

fun box(): String {
    val f = { }
    val class1 = (Runnable(f) as Object).getClass()
    val class2 = (Runnable(f) as Object).getClass()

    return if (class1 == class2) "OK" else "$class1 $class2"
}
