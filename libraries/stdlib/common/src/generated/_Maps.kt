/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:kotlin.jvm.JvmMultifileClass
@file:kotlin.jvm.JvmName("MapsKt")

package kotlin.collections

//
// NOTE: THIS FILE IS AUTO-GENERATED by the GenerateStandardLib.kt
// See: https://github.com/JetBrains/kotlin/tree/master/libraries/stdlib
//

import kotlin.random.*
import kotlin.ranges.contains
import kotlin.ranges.reversed

/**
 * Returns a [List] containing all key-value pairs.
 */
public fun <K, V> Map<out K, V>.toList(): List<Pair<K, V>> {
    if (size == 0)
        return emptyList()
    val iterator = entries.iterator()
    if (!iterator.hasNext())
        return emptyList()
    val first = iterator.next()
    if (!iterator.hasNext())
        return listOf(first.toPair())
    val result = ArrayList<Pair<K, V>>(size)
    result.add(first.toPair())
    do {
        result.add(iterator.next().toPair())
    } while (iterator.hasNext())
    return result
}

/**
 * Returns a single list of all elements yielded from results of [transform] function being invoked on each entry of original map.
 * 
 * @sample samples.collections.Maps.Transformations.flatMap
 */
public inline fun <K, V, R> Map<out K, V>.flatMap(transform: (Map.Entry<K, V>) -> Iterable<R>): List<R> {
    return flatMapTo(ArrayList<R>(), transform)
}

/**
 * Returns a single list of all elements yielded from results of [transform] function being invoked on each entry of original map.
 * 
 * @sample samples.collections.Collections.Transformations.flatMap
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.jvm.JvmName("flatMapSequence")
public inline fun <K, V, R> Map<out K, V>.flatMap(transform: (Map.Entry<K, V>) -> Sequence<R>): List<R> {
    return flatMapTo(ArrayList<R>(), transform)
}

/**
 * Appends all elements yielded from results of [transform] function being invoked on each entry of original map, to the given [destination].
 */
public inline fun <K, V, R, C : MutableCollection<in R>> Map<out K, V>.flatMapTo(destination: C, transform: (Map.Entry<K, V>) -> Iterable<R>): C {
    for (element in this) {
        val list = transform(element)
        destination.addAll(list)
    }
    return destination
}

/**
 * Appends all elements yielded from results of [transform] function being invoked on each entry of original map, to the given [destination].
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.jvm.JvmName("flatMapSequenceTo")
public inline fun <K, V, R, C : MutableCollection<in R>> Map<out K, V>.flatMapTo(destination: C, transform: (Map.Entry<K, V>) -> Sequence<R>): C {
    for (element in this) {
        val list = transform(element)
        destination.addAll(list)
    }
    return destination
}

/**
 * Returns a list containing the results of applying the given [transform] function
 * to each entry in the original map.
 * 
 * @sample samples.collections.Maps.Transformations.mapToList
 */
public inline fun <K, V, R> Map<out K, V>.map(transform: (Map.Entry<K, V>) -> R): List<R> {
    return mapTo(ArrayList<R>(size), transform)
}

/**
 * Returns a list containing only the non-null results of applying the given [transform] function
 * to each entry in the original map.
 * 
 * @sample samples.collections.Maps.Transformations.mapNotNull
 */
public inline fun <K, V, R : Any> Map<out K, V>.mapNotNull(transform: (Map.Entry<K, V>) -> R?): List<R> {
    return mapNotNullTo(ArrayList<R>(), transform)
}

/**
 * Applies the given [transform] function to each entry in the original map
 * and appends only the non-null results to the given [destination].
 */
public inline fun <K, V, R : Any, C : MutableCollection<in R>> Map<out K, V>.mapNotNullTo(destination: C, transform: (Map.Entry<K, V>) -> R?): C {
    forEach { element -> transform(element)?.let { destination.add(it) } }
    return destination
}

/**
 * Applies the given [transform] function to each entry of the original map
 * and appends the results to the given [destination].
 */
public inline fun <K, V, R, C : MutableCollection<in R>> Map<out K, V>.mapTo(destination: C, transform: (Map.Entry<K, V>) -> R): C {
    for (item in this)
        destination.add(transform(item))
    return destination
}

/**
 * Returns `true` if all entries match the given [predicate].
 * 
 * @sample samples.collections.Collections.Aggregates.all
 */
public inline fun <K, V> Map<out K, V>.all(predicate: (Map.Entry<K, V>) -> Boolean): Boolean {
    if (isEmpty()) return true
    for (element in this) if (!predicate(element)) return false
    return true
}

/**
 * Returns `true` if map has at least one entry.
 * 
 * @sample samples.collections.Collections.Aggregates.any
 */
public fun <K, V> Map<out K, V>.any(): Boolean {
    return !isEmpty()
}

/**
 * Returns `true` if at least one entry matches the given [predicate].
 * 
 * @sample samples.collections.Collections.Aggregates.anyWithPredicate
 */
public inline fun <K, V> Map<out K, V>.any(predicate: (Map.Entry<K, V>) -> Boolean): Boolean {
    if (isEmpty()) return false
    for (element in this) if (predicate(element)) return true
    return false
}

/**
 * Returns the number of entries in this map.
 */
@kotlin.internal.InlineOnly
public inline fun <K, V> Map<out K, V>.count(): Int {
    return size
}

/**
 * Returns the number of entries matching the given [predicate].
 */
public inline fun <K, V> Map<out K, V>.count(predicate: (Map.Entry<K, V>) -> Boolean): Int {
    if (isEmpty()) return 0
    var count = 0
    for (element in this) if (predicate(element)) ++count
    return count
}

/**
 * Performs the given [action] on each entry.
 */
@kotlin.internal.HidesMembers
public inline fun <K, V> Map<out K, V>.forEach(action: (Map.Entry<K, V>) -> Unit): Unit {
    for (element in this) action(element)
}

@Deprecated("Use maxByOrNull instead.", ReplaceWith("this.maxByOrNull(selector)"))
@DeprecatedSinceKotlin(warningSince = "1.4")
@kotlin.internal.InlineOnly
public inline fun <K, V, R : Comparable<R>> Map<out K, V>.maxBy(selector: (Map.Entry<K, V>) -> R): Map.Entry<K, V>? {
    return maxByOrNull(selector)
}

/**
 * Returns the first entry yielding the largest value of the given function or `null` if there are no entries.
 * 
 * @sample samples.collections.Collections.Aggregates.maxByOrNull
 */
@SinceKotlin("1.4")
@kotlin.internal.InlineOnly
public inline fun <K, V, R : Comparable<R>> Map<out K, V>.maxByOrNull(selector: (Map.Entry<K, V>) -> R): Map.Entry<K, V>? {
    return entries.maxByOrNull(selector)
}

/**
 * Returns the largest value among all values produced by [selector] function
 * applied to each entry in the map.
 * 
 * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
 * 
 * @throws NoSuchElementException if the map is empty.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V> Map<out K, V>.maxOf(selector: (Map.Entry<K, V>) -> Double): Double {
    return entries.maxOf(selector)
}

/**
 * Returns the largest value among all values produced by [selector] function
 * applied to each entry in the map.
 * 
 * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
 * 
 * @throws NoSuchElementException if the map is empty.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V> Map<out K, V>.maxOf(selector: (Map.Entry<K, V>) -> Float): Float {
    return entries.maxOf(selector)
}

/**
 * Returns the largest value among all values produced by [selector] function
 * applied to each entry in the map.
 * 
 * @throws NoSuchElementException if the map is empty.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V, R : Comparable<R>> Map<out K, V>.maxOf(selector: (Map.Entry<K, V>) -> R): R {
    return entries.maxOf(selector)
}

/**
 * Returns the largest value among all values produced by [selector] function
 * applied to each entry in the map or `null` if there are no entries.
 * 
 * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V> Map<out K, V>.maxOfOrNull(selector: (Map.Entry<K, V>) -> Double): Double? {
    return entries.maxOfOrNull(selector)
}

/**
 * Returns the largest value among all values produced by [selector] function
 * applied to each entry in the map or `null` if there are no entries.
 * 
 * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V> Map<out K, V>.maxOfOrNull(selector: (Map.Entry<K, V>) -> Float): Float? {
    return entries.maxOfOrNull(selector)
}

/**
 * Returns the largest value among all values produced by [selector] function
 * applied to each entry in the map or `null` if there are no entries.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V, R : Comparable<R>> Map<out K, V>.maxOfOrNull(selector: (Map.Entry<K, V>) -> R): R? {
    return entries.maxOfOrNull(selector)
}

/**
 * Returns the largest value according to the provided [comparator]
 * among all values produced by [selector] function applied to each entry in the map.
 * 
 * @throws NoSuchElementException if the map is empty.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V, R> Map<out K, V>.maxOfWith(comparator: Comparator<in R>, selector: (Map.Entry<K, V>) -> R): R {
    return entries.maxOfWith(comparator, selector)
}

/**
 * Returns the largest value according to the provided [comparator]
 * among all values produced by [selector] function applied to each entry in the map or `null` if there are no entries.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V, R> Map<out K, V>.maxOfWithOrNull(comparator: Comparator<in R>, selector: (Map.Entry<K, V>) -> R): R? {
    return entries.maxOfWithOrNull(comparator, selector)
}

@Deprecated("Use maxWithOrNull instead.", ReplaceWith("this.maxWithOrNull(comparator)"))
@DeprecatedSinceKotlin(warningSince = "1.4")
@kotlin.internal.InlineOnly
public inline fun <K, V> Map<out K, V>.maxWith(comparator: Comparator<in Map.Entry<K, V>>): Map.Entry<K, V>? {
    return maxWithOrNull(comparator)
}

/**
 * Returns the first entry having the largest value according to the provided [comparator] or `null` if there are no entries.
 */
@SinceKotlin("1.4")
@kotlin.internal.InlineOnly
public inline fun <K, V> Map<out K, V>.maxWithOrNull(comparator: Comparator<in Map.Entry<K, V>>): Map.Entry<K, V>? {
    return entries.maxWithOrNull(comparator)
}

@Deprecated("Use minByOrNull instead.", ReplaceWith("this.minByOrNull(selector)"))
@DeprecatedSinceKotlin(warningSince = "1.4")
public inline fun <K, V, R : Comparable<R>> Map<out K, V>.minBy(selector: (Map.Entry<K, V>) -> R): Map.Entry<K, V>? {
    return minByOrNull(selector)
}

/**
 * Returns the first entry yielding the smallest value of the given function or `null` if there are no entries.
 * 
 * @sample samples.collections.Collections.Aggregates.minByOrNull
 */
@SinceKotlin("1.4")
@kotlin.internal.InlineOnly
public inline fun <K, V, R : Comparable<R>> Map<out K, V>.minByOrNull(selector: (Map.Entry<K, V>) -> R): Map.Entry<K, V>? {
    return entries.minByOrNull(selector)
}

/**
 * Returns the smallest value among all values produced by [selector] function
 * applied to each entry in the map.
 * 
 * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
 * 
 * @throws NoSuchElementException if the map is empty.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V> Map<out K, V>.minOf(selector: (Map.Entry<K, V>) -> Double): Double {
    return entries.minOf(selector)
}

/**
 * Returns the smallest value among all values produced by [selector] function
 * applied to each entry in the map.
 * 
 * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
 * 
 * @throws NoSuchElementException if the map is empty.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V> Map<out K, V>.minOf(selector: (Map.Entry<K, V>) -> Float): Float {
    return entries.minOf(selector)
}

/**
 * Returns the smallest value among all values produced by [selector] function
 * applied to each entry in the map.
 * 
 * @throws NoSuchElementException if the map is empty.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V, R : Comparable<R>> Map<out K, V>.minOf(selector: (Map.Entry<K, V>) -> R): R {
    return entries.minOf(selector)
}

/**
 * Returns the smallest value among all values produced by [selector] function
 * applied to each entry in the map or `null` if there are no entries.
 * 
 * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V> Map<out K, V>.minOfOrNull(selector: (Map.Entry<K, V>) -> Double): Double? {
    return entries.minOfOrNull(selector)
}

/**
 * Returns the smallest value among all values produced by [selector] function
 * applied to each entry in the map or `null` if there are no entries.
 * 
 * If any of values produced by [selector] function is `NaN`, the returned result is `NaN`.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V> Map<out K, V>.minOfOrNull(selector: (Map.Entry<K, V>) -> Float): Float? {
    return entries.minOfOrNull(selector)
}

/**
 * Returns the smallest value among all values produced by [selector] function
 * applied to each entry in the map or `null` if there are no entries.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V, R : Comparable<R>> Map<out K, V>.minOfOrNull(selector: (Map.Entry<K, V>) -> R): R? {
    return entries.minOfOrNull(selector)
}

/**
 * Returns the smallest value according to the provided [comparator]
 * among all values produced by [selector] function applied to each entry in the map.
 * 
 * @throws NoSuchElementException if the map is empty.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V, R> Map<out K, V>.minOfWith(comparator: Comparator<in R>, selector: (Map.Entry<K, V>) -> R): R {
    return entries.minOfWith(comparator, selector)
}

/**
 * Returns the smallest value according to the provided [comparator]
 * among all values produced by [selector] function applied to each entry in the map or `null` if there are no entries.
 */
@SinceKotlin("1.4")
@OptIn(kotlin.experimental.ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
@kotlin.internal.InlineOnly
public inline fun <K, V, R> Map<out K, V>.minOfWithOrNull(comparator: Comparator<in R>, selector: (Map.Entry<K, V>) -> R): R? {
    return entries.minOfWithOrNull(comparator, selector)
}

@Deprecated("Use minWithOrNull instead.", ReplaceWith("this.minWithOrNull(comparator)"))
@DeprecatedSinceKotlin(warningSince = "1.4")
public fun <K, V> Map<out K, V>.minWith(comparator: Comparator<in Map.Entry<K, V>>): Map.Entry<K, V>? {
    return minWithOrNull(comparator)
}

/**
 * Returns the first entry having the smallest value according to the provided [comparator] or `null` if there are no entries.
 */
@SinceKotlin("1.4")
@kotlin.internal.InlineOnly
public inline fun <K, V> Map<out K, V>.minWithOrNull(comparator: Comparator<in Map.Entry<K, V>>): Map.Entry<K, V>? {
    return entries.minWithOrNull(comparator)
}

/**
 * Returns `true` if the map has no entries.
 * 
 * @sample samples.collections.Collections.Aggregates.none
 */
public fun <K, V> Map<out K, V>.none(): Boolean {
    return isEmpty()
}

/**
 * Returns `true` if no entries match the given [predicate].
 * 
 * @sample samples.collections.Collections.Aggregates.noneWithPredicate
 */
public inline fun <K, V> Map<out K, V>.none(predicate: (Map.Entry<K, V>) -> Boolean): Boolean {
    if (isEmpty()) return true
    for (element in this) if (predicate(element)) return false
    return true
}

/**
 * Performs the given [action] on each entry and returns the map itself afterwards.
 */
@SinceKotlin("1.1")
public inline fun <K, V, M : Map<out K, V>> M.onEach(action: (Map.Entry<K, V>) -> Unit): M {
    return apply { for (element in this) action(element) }
}

/**
 * Performs the given [action] on each entry, providing sequential index with the entry,
 * and returns the map itself afterwards.
 * @param [action] function that takes the index of an entry and the entry itself
 * and performs the action on the entry.
 */
@SinceKotlin("1.4")
public inline fun <K, V, M : Map<out K, V>> M.onEachIndexed(action: (index: Int, Map.Entry<K, V>) -> Unit): M {
    return apply { entries.forEachIndexed(action) }
}

/**
 * Creates an [Iterable] instance that wraps the original map returning its entries when being iterated.
 */
@kotlin.internal.InlineOnly
public inline fun <K, V> Map<out K, V>.asIterable(): Iterable<Map.Entry<K, V>> {
    return entries
}

/**
 * Creates a [Sequence] instance that wraps the original map returning its entries when being iterated.
 */
public fun <K, V> Map<out K, V>.asSequence(): Sequence<Map.Entry<K, V>> {
    return entries.asSequence()
}

