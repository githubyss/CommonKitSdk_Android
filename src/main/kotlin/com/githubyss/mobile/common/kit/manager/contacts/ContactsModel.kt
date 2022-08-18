package com.githubyss.mobile.common.kit.manager.contacts

/**
 * ContactsModel
 * <Description>
 *
 * @author Ace Yan
 * @github githubyss
 */
data class ContactsModel constructor(var rawContactsId: String?, var displayName: String?, var cellphoneList: List<String>?) {
    constructor(displayName: String?, cellphoneNumberList: List<String>?) : this(null, displayName, cellphoneNumberList)

    override fun toString(): String
            = "$displayName|${cellphoneList.toString().replace("[", "").replace("]", "").replace(" ", "")}"
}
