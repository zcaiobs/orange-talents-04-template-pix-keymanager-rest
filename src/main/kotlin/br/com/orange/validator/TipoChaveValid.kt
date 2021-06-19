package br.com.orange.validator

import javax.inject.Singleton
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Singleton
class TipoChaveValid : ConstraintValidator<KeyType, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return when (value) {
            "CPF", "EMAIL", "PHONE", "RANDOM" -> true
            else -> false
        }
    }
}

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [TipoChaveValid::class])
annotation class KeyType(
    val message: String = "Invalid Value.",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)