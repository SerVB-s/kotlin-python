// TODO: muted for Python because it was muted for JS. Once Python doesn't piggy-back on JS, investigate if it can be re-enabled for Python.
// IGNORE_BACKEND: PYTHON
// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: IGNORED_IN_JS
// IGNORE_BACKEND: JS_IR
// IGNORE_BACKEND: JS_IR_ES6
// IGNORE_BACKEND: JS

// Exclamation marks are not valid in names in the dex file format.
// Therefore, do not attempt to dex this file as it will fail.
// See: https://source.android.com/devices/tech/dalvik/dex-format#simplename
// IGNORE_DEXING
// IGNORE_BACKEND: ANDROID

class `A!u00A0`() {
    val ok = "OK"
}

fun box(): String {
    return `A!u00A0`().ok
}
