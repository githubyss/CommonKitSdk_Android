package com.githubyss.mobile.common.kit.base.mvp


/**
 * IBaseView
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 14:35:56
 */
interface IBaseView<in P> {
    fun setPresenter(iPresenter: P)
}
