package com.githubyss.mobile.common.kit.util


/**
 * ListUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/17 14:37:01
 */

/** ****************************** Properties ****************************** */

/**  */
private const val TAG: String = "ListUtils"


/** ****************************** Functions ****************************** */

/** ******************** Checker ******************** */

/**
 * Return whether the list is null or 0-length.
 *
 * @param list The list.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("isEmptyOrNull_")
fun isEmptyOrNull(list: List<*>?) = list.isEmptyOrNull()
fun List<*>?.isEmptyOrNull() = this.isNullOrEmpty()
