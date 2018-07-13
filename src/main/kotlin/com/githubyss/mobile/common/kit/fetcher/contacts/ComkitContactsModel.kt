package com.githubyss.mobile.common.kit.fetcher.contacts

/**
 * ComkitContactsModel
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
data class ComkitContactsModel constructor(var rawContactsId: String?, var displayName: String?, var cellphoneList: List<String>?) {
    constructor(displayName: String?, cellphoneNumberList: List<String>?) : this(null, displayName, cellphoneNumberList)

    override fun toString(): String
            = "$displayName|${cellphoneList.toString().replace("[", "").replace("]", "").replace(" ", "")}"
}
