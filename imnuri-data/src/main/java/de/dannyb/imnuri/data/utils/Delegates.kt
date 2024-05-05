package de.dannyb.imnuri.data.utils

import org.apache.commons.lang3.ClassUtils
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * TODO: Move to a `core` module
 */
abstract class BaseDelegate<T>(
    private val expectedType: Class<*>,
    protected val defaultValue: T? = null
) {
    private var _value: T? = null

    var value: T?
        get() = _value ?: fetchValue()?.also { value ->
            enforceType(value)
            _value = value
        } ?: defaultValue
        set(value) {
            enforceType(value)
            if (_value != value) {
                storeValue(value).also { _value = value }
            }
        }

    protected abstract fun fetchValue(): T?

    protected abstract fun storeValue(value: T?)

    private fun enforceType(value: T?) = value?.let {
        val actualType = it::class.java
        require(
            ClassUtils.isAssignable(actualType, expectedType)
        ) { "Type mismatch: $actualType is not a parent of $expectedType" }
    }

    @Suppress("UNCHECKED_CAST")
    operator fun provideDelegate(
        thisRef: Any?,
        property: KProperty<*>
    ): ReadWriteProperty<Any?, T> {
        enforceType(defaultValue)

        return if (defaultValue != null) {
            NonNullPropertyDelegate(this)
        } else {
            NullablePropertyDelegate(this) as ReadWriteProperty<Any?, T>
        }
    }
}

class NonNullPropertyDelegate<T>(private val baseDelegate: BaseDelegate<T>) :
    ReadWriteProperty<Any?, T> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return checkNotNull(baseDelegate.value)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        baseDelegate.value = value
    }
}

class NullablePropertyDelegate<T>(private val baseDelegate: BaseDelegate<T>) :
    ReadWriteProperty<Any?, T?> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? = baseDelegate.value

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        baseDelegate.value = value
    }
}