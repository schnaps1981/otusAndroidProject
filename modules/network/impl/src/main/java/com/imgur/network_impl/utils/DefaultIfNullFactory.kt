package com.imgur.network_impl.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import timber.log.Timber
import java.lang.reflect.Type

class DefaultIfNullFactory : JsonAdapter.Factory {
    override fun create(
        type: Type, annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*> {
        val delegate = moshi.nextAdapter<Any>(this, type, annotations)
        return object : JsonAdapter<Any>() {
            override fun fromJson(reader: JsonReader): Any? {
                val rawData = try {
                    reader.readJsonValue()
                } catch (e: Throwable) {
                    Timber.e(e)
                    null
                }

                return try {
                    if (type == NullableStatus::class.java) {
                        return delegate.fromJsonValue(rawData)
                    }

                    when (rawData) {
                        is List<*> -> {
                            val result = rawData.map {
                                if (it is Map<*, *>) {
                                    removeNull(it)
                                } else {
                                    it
                                }
                            }
                            delegate.fromJsonValue(result)
                        }
                        is Map<*, *> -> {
                            val result = removeNull(rawData)
                            delegate.fromJsonValue(result)
                        }
                        else -> {
                            delegate.fromJsonValue(rawData)
                        }
                    }
                } catch (e: Throwable) {
                    Timber.e(e)
                    delegate.fromJsonValue(rawData)
                }
            }

            private fun removeNull(rawData: Map<*, *>): Any {
                val mutableMap = rawData.toMutableMap()

                mutableMap.forEach {
                    if (it.value is Map<*, *>) {
                        mutableMap[it.key] = removeNull(it.value as Map<*, *>)
                    }
                }

                return mutableMap.filterValues { it != null }
            }

            override fun toJson(writer: JsonWriter, value: Any?) {
                if (value is NullableStatus)
                    delegate.serializeNulls().toJson(writer, value)
                else
                    delegate.toJson(writer, value)
            }
        }
    }
}
