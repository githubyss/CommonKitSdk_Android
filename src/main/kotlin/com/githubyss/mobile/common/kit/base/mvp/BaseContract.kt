package com.githubyss.mobile.common.kit.base.mvp


/**
 * BaseContract
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 14:35:34
 */
interface BaseContract {
    interface BaseIView<in P> {
        fun setPresenter(iPresenter: P)
    }

    interface BaseIPresenter {
        fun onStandby()
    }
}
