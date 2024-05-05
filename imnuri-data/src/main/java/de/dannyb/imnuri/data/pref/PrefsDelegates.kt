package de.dannyb.imnuri.data.pref

import androidx.preference.PreferenceDataStore
import com.google.gson.reflect.TypeToken
import de.dannyb.imnuri.data.pref.datastore.InMemoryDataStore
import de.dannyb.imnuri.data.pref.datastore.SharedPreferencesDataStore
import de.dannyb.imnuri.data.utils.BaseDelegate
import de.dannyb.imnuri.data.utils.DataModuleInjector
import java.lang.reflect.Type

abstract class PrefsDelegate<T>(
    protected val key: String,
    private val inMemory: Boolean = false,
    expectedType: Class<*>,
    defaultValue: T? = null
) : BaseDelegate<T>(expectedType, defaultValue) {

    protected val prefs: PreferenceDataStore by lazy {
        if (inMemory) inMemoryDataStore else sharedPreferencesDataStore
    }

    private val inMemoryDataStore: InMemoryDataStore by lazy {
        DataModuleInjector.getInMemoryDataStore()
    }

    private val sharedPreferencesDataStore: SharedPreferencesDataStore by lazy {
        DataModuleInjector.getSharedPreferencesDataStore()
    }
}

open class StringPrefsDelegate(
    key: String,
    inMemory: Boolean = false,
    defaultValue: String? = null
) : PrefsDelegate<String>(key, inMemory, String::class.java, defaultValue) {

    constructor(keyDefaultPair: Pair<String, String?>, inMemory: Boolean = false) : this(
        key = keyDefaultPair.first,
        inMemory = inMemory,
        defaultValue = keyDefaultPair.second
    )

    override fun fetchValue(): String? = prefs.getString(key, defaultValue)

    override fun storeValue(value: String?) {
        value?.let { prefs.putString(key, it) }
    }
}

open class StringSetPrefsDelegate(
    key: String,
    inMemory: Boolean = false,
    defaultValue: Set<String>? = null
) : PrefsDelegate<Set<String>>(key, inMemory, Set::class.java, defaultValue) {

    constructor(keyDefaultPair: Pair<String, Set<String>?>, inMemory: Boolean = false) : this(
        key = keyDefaultPair.first,
        inMemory = inMemory,
        defaultValue = keyDefaultPair.second
    )

    override fun fetchValue(): Set<String>? = prefs.getStringSet(key, defaultValue)

    override fun storeValue(value: Set<String>?) {
        value?.let { prefs.putStringSet(key, it) }
    }
}

open class IntPrefsDelegate(
    key: String,
    inMemory: Boolean = false,
    defaultValue: Int? = 0
) : PrefsDelegate<Int>(key, inMemory, Int::class.java, defaultValue) {

    constructor(keyDefaultPair: Pair<String, Int?>, inMemory: Boolean = false) : this(
        key = keyDefaultPair.first,
        inMemory = inMemory,
        defaultValue = keyDefaultPair.second
    )

    override fun fetchValue(): Int = prefs.getInt(key, checkNotNull(defaultValue))

    override fun storeValue(value: Int?) {
        value?.let { prefs.putInt(key, it) }
    }
}

open class LongPrefsDelegate(
    key: String,
    inMemory: Boolean = false,
    defaultValue: Long? = 0L
) : PrefsDelegate<Long>(key, inMemory, Long::class.java, defaultValue) {

    constructor(keyDefaultPair: Pair<String, Long?>, inMemory: Boolean = false) : this(
        key = keyDefaultPair.first,
        inMemory = inMemory,
        defaultValue = keyDefaultPair.second
    )

    override fun fetchValue(): Long = prefs.getLong(key, checkNotNull(defaultValue))

    override fun storeValue(value: Long?) {
        value?.let { prefs.putLong(key, it) }
    }
}

open class FloatPrefsDelegate(
    key: String,
    inMemory: Boolean = false,
    defaultValue: Float? = 0f
) : PrefsDelegate<Float>(key, inMemory, Float::class.java, defaultValue) {

    constructor(keyDefaultPair: Pair<String, Float?>, inMemory: Boolean = false) : this(
        key = keyDefaultPair.first,
        inMemory = inMemory,
        defaultValue = keyDefaultPair.second
    )

    override fun fetchValue(): Float = prefs.getFloat(key, checkNotNull(defaultValue))

    override fun storeValue(value: Float?) {
        value?.let { prefs.putFloat(key, it) }
    }
}

open class BooleanPrefsDelegate(
    key: String,
    inMemory: Boolean = false,
    defaultValue: Boolean? = false
) : PrefsDelegate<Boolean>(key, inMemory, Boolean::class.java, defaultValue) {

    constructor(keyDefaultPair: Pair<String, Boolean?>, inMemory: Boolean = false) : this(
        key = keyDefaultPair.first,
        inMemory = inMemory,
        defaultValue = keyDefaultPair.second
    )

    override fun fetchValue(): Boolean = prefs.getBoolean(key, checkNotNull(defaultValue))

    override fun storeValue(value: Boolean?) {
        value?.let { prefs.putBoolean(key, it) }
    }
}

open class ObjectPrefsDelegate<T>(
    key: String,
    private val type: Type,
    private val typeToken: TypeToken<*>? = null,
    inMemory: Boolean = false,
    defaultValue: T? = null
) : PrefsDelegate<T>(key, inMemory, Any::class.java, defaultValue) {

    private val gson by lazy { DataModuleInjector.getGson() }

    constructor(
        keyDefaultPair: Pair<String, T?>,
        type: Type,
        typeToken: TypeToken<*>?,
        inMemory: Boolean = false
    ) : this(
        key = keyDefaultPair.first,
        type = type,
        typeToken = typeToken,
        inMemory = inMemory,
        defaultValue = keyDefaultPair.second
    )

    override fun fetchValue(): T? {
        return prefs.getString(key, null)?.let { gson.fromJson(it, type) }
    }

    override fun storeValue(value: T?) {
        value?.let { prefs.putString(key, gson.toJson(it)) }
    }
}
