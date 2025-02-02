plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    compile(project(":compiler:util"))
    compile(project(":compiler:cli-common"))
    compile(project(":compiler:cli"))
    compile(project(":compiler:frontend"))
    compile(project(":compiler:backend-common"))
    compile(project(":compiler:ir.backend.common"))
    compile(project(":compiler:ir.serialization.js"))
    compile(project(":compiler:ir.tree.impl"))
    compile(project(":compiler:backend.py"))
    compile(project(":compiler:backend.wasm"))
    compile(project(":python:py.translator"))
    compile(project(":js:js.serializer"))
    compile(project(":js:js.dce"))

    compileOnly(intellijCoreDep()) { includeJars("intellij-core") }
}

sourceSets {
    "main" { projectDefault() }
}
