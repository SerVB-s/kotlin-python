// TODO: muted for Python because it was muted for JS. Once Python doesn't piggy-back on JS, investigate if it can be re-enabled for Python.
// IGNORE_BACKEND: PYTHON
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: IGNORED_IN_JS
// IGNORE_BACKEND: JS_IR
// IGNORE_BACKEND: JS_IR_ES6
// TODO: muted automatically, investigate should it be ran for JS or not
// DONT_RUN_GENERATED_CODE: JS
// IGNORE_BACKEND: JS
// IGNORE_FIR_DIAGNOSTICS_DIFF

tailrec fun test(x : Int) : Int {
    if (x == 0) {
        return 0
    } else if (x == 10) {
        <!NON_TAIL_RECURSIVE_CALL!>test<!>(0)
        return 1 + <!NON_TAIL_RECURSIVE_CALL!>test<!>(x - 1)
    } else {
        return test(x - 1)
    }
}

fun box() : String = if (test(1000000) == 1) "OK" else "FAIL"
