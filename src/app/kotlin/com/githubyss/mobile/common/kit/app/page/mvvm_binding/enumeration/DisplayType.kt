package com.githubyss.mobile.common.kit.app.page.mvvm_binding.enumeration

import androidx.annotation.StringDef


/**
 * DisplayType
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/21 16:01:26
 */
// sealed class DisplayType {
//     object TEXT : DisplayType()
//     object IMAGE : DisplayType()
//     object EDITTEXT : DisplayType()
// }


/**
 * DisplayType
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/27 09:35:00
 */
@StringDef(DisplayType.TEXT, DisplayType.IMAGE, DisplayType.EDITTEXT)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class DisplayType {
    companion object {
        const val TEXT = "0"
        const val IMAGE = "1"
        const val EDITTEXT = "2"
    }
}
