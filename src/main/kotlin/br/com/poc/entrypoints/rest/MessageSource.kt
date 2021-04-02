package br.com.poc.entrypoints.rest

import okhttp3.internal.toImmutableList
import javax.inject.Singleton
import javax.validation.ConstraintViolation
import javax.validation.Path


@Singleton
class MessageSource {
    fun violationsMessages(violations: Set<ConstraintViolation<*>>): List<String> {
        return violations.map(this::violationMessage).toImmutableList()
    }

    private fun violationMessage(violation: ConstraintViolation<*>): String {
        val sb = StringBuilder()
        val lastNode: Path.Node? = lastNode(violation.propertyPath)
        if (lastNode != null) {
            sb.append(lastNode.name)
            sb.append(" ")
        }
        sb.append(violation.message)
        return sb.toString()
    }

    private fun lastNode(path: Path): Path.Node? {
        return path.findLast { p ->
            return@lastNode p
        }
    }
}