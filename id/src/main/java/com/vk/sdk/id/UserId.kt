package com.vk.dto.common.id

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.*
import java.lang.reflect.Type
import kotlin.math.absoluteValue

/**
 * wrapper class to support Long user id
 */
data class UserId(val value: Long): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong()
    )

    override fun toString(): String {
        return value.toString()
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(value)
    }

    class GsonSerializer(
        private val shiftByMaxInt: Boolean = false
    ) : JsonSerializer<UserId?>, JsonDeserializer<UserId?> {
        override fun serialize(
            src: UserId?,
            typeOfSrc: Type?,
            context: JsonSerializationContext?
        ): JsonElement {
            val value = when {
                src == null -> -1
                !shiftByMaxInt -> src.value
                else -> if (src.value < 0) { // is group
                    src.value - Int.MAX_VALUE
                } else { // is user
                    src.value + Int.MAX_VALUE
                }
            }
            return JsonPrimitive(value)
        }

        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): UserId? {
           return if (json != null && !json.isJsonNull) {
               val long = json.asLong
               if (!shiftByMaxInt) return UserId(long)

               val isGroup = long < 0
               val absLong = long.absoluteValue
               // Если не нужно сдвигать на максимальный инт, то возвращаем как есть
               if (absLong < Int.MAX_VALUE) throw IllegalStateException("abs of owner id should be >= MAX_INT")
               val shifted = absLong - Int.MAX_VALUE
               return UserId(if (isGroup) -shifted else shifted)
            } else {
                null
            }
        }
    }

    companion object {
        @JvmField
        val DEFAULT = UserId(0)

        @JvmField
        val CREATOR = object : Parcelable.Creator<UserId> {
            override fun createFromParcel(source: Parcel): UserId = UserId(source)
            override fun newArray(size: Int): Array<UserId?> = arrayOfNulls(size)
        }

        @Deprecated(message = "don't use it in new code",
            replaceWith = ReplaceWith("UserId()", "com.vk.dto.common.id.UserId")
        )
        @JvmStatic
        fun fromLegacyValue(value: Int) = UserId(value.toLong())

        @Deprecated(message = "don't use it in new code",
            replaceWith = ReplaceWith("UserId()", "com.vk.dto.common.id.UserId")
        )
        @JvmStatic
        fun fromLegacyValues(value: Collection<Int>) = value.toList().map { fromLegacyValue(it) }
    }
}

fun UserId.isReal() = value != 0L

fun UserId.abs() = copy(value = value.absoluteValue)

fun UserId.negative() = copy(value = -value)

operator fun UserId.unaryMinus() = negative()

fun UserId.isGroupId() = value < 0

fun UserId.isUserId() = value > 0

@Deprecated(message = "don't use it in new code",
    replaceWith = ReplaceWith("UserId()", "com.vk.dto.common.id.UserId")
)
fun UserId.legacyValue(): Int = value.toInt()

@Deprecated(message = "don't use it in new code",
    replaceWith = ReplaceWith("UserId()", "com.vk.dto.common.id.UserId")
)
fun List<UserId>.mapLegacyValues(): List<Int> = this.map { it.legacyValue() }

fun Long.toUserId(): UserId = UserId(this)

@Deprecated(message = "don't use it in new code",
    replaceWith = ReplaceWith("toUserId()", "com.vk.dto.common.id")
)
fun Int.toLegacyUserId(): UserId = UserId(this.toLong())
