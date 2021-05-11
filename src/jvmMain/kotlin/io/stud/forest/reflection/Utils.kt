package io.stud.forest.reflection

import java.lang.reflect.Modifier
import kotlin.reflect.KFunction
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.kotlinFunction


/**
 * @sample
 * val startFunctionName = "day${day}_${part}"
 * val clazz = object : Any() {}.javaClass.enclosingClass*
 * val clazz = Class.forName("package.subpackage.FileNameKt")
 * io.stud.forest.reflection.getStaticFunctionForClass(clazz, startFunctionName)?.call()
 */
fun getStaticFunctionForClass(clazz: Class<*>, funcName: String): KFunction<*>? {
    val javaMethod = clazz.methods.find { it.name == funcName && Modifier.isStatic(it.modifiers) }
    return javaMethod?.kotlinFunction
}

fun fileNameContainingClass(clazz: Class<*>): String {
    return clazz.protectionDomain.codeSource.location.path
}

fun <PropertyType> setProperty(instance: Any, propertyName: String, propertyValue: PropertyType) {
    val property = instance::class.memberProperties.find { it.name == propertyName }
    if (property is KMutableProperty<*>) {
        property.setter.call(instance, propertyValue)
    }
}
