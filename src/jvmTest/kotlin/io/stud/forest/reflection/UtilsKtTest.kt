package io.stud.forest.reflection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

enum class UsedConstructor { DEFAULT, STRING_INT }

class `Class with (Int, String) and (String, Int) constructors`(
    val number: Int,
    val text: String,
) {
    var usedConstructor = UsedConstructor.DEFAULT

    constructor(text: String, number: Int) : this(number, text) {
        usedConstructor = UsedConstructor.STRING_INT
    }
}

class `Class with no arguments constructor`() {
    var usedConstructor = UsedConstructor.DEFAULT
}

internal class UtilsKtTest {


    @Test
    fun `should create instance using given parameter types in order`() {
        val constructor =
            constructorOf<`Class with (Int, String) and (String, Int) constructors`>(String::class, Int::class)

        val instance = constructor!!.call("text", 8)

        assertThat(instance.usedConstructor).isEqualTo(UsedConstructor.STRING_INT)
    }

    @Test
    fun `correct use correct params values when creating instance`() {
        val constructor =
            constructorOf<`Class with (Int, String) and (String, Int) constructors`>(Int::class, String::class)

        val instance = constructor!!.call(8, "text")

        assertThat(instance.number).isEqualTo(8)
        assertThat(instance.text).isEqualTo("text")
    }

    @Test
    fun `should throws when constructor not found for given parameter types`() {
        val constructor =
            constructorOf<`Class with (Int, String) and (String, Int) constructors`>()

        assertThrows<Exception> {
            constructor!!.call()
        }
    }

    @Test
    fun `should not pass when instance calls with wrong parameters`() {
        val constructor =
            constructorOf<`Class with (Int, String) and (String, Int) constructors`>(Int::class, String::class)

        assertThrows<Exception>{
            constructor!!.call("Not numeric type", 123)
        }
    }

    @Test
    fun `should create instance using default constructor when no parameter types`() {
        val constructor = constructorOf<`Class with no arguments constructor`>()
        val instance = constructor!!.call()
        assertThat(instance.usedConstructor).isEqualTo(UsedConstructor.DEFAULT)
    }
}