package com.githubyss.mobile.common.kit.app.page.homepage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.fragment.app.viewModels
import com.githubyss.common.base.activity_fragment.classical.BaseActivity
import com.githubyss.common.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.compose_ui.comui.ButtonClickBlueWeightHorizontal
import com.githubyss.mobile.common.kit.app.compose_ui.comui.LayoutWeightHorizontal
import com.githubyss.mobile.common.kit.app.compose_ui.comui.PageSidePadding
import com.githubyss.mobile.common.kit.app.compose_ui.comui.TopNavigationBar
import com.githubyss.mobile.common.kit.app.page.json_utils.JsonUtilsFragment
import com.githubyss.mobile.common.kit.app.page.web_view.WebViewActivity
import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.kit.util.startActivityExt
import com.githubyss.mobile.common.kit.util.switchFragmentByAddHideShow
import com.githubyss.mobile.common.res.common.dimen.SpaceNormal


/**
 * HomepageComposeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/30 17:50:30
 */
class HomepageComposeFragment : BaseComposeToolbarFragment() {

    /** ****************************** Properties ****************************** */

    /**  */
    companion object {
        val TAG: String = HomepageComposeFragment::class.java.simpleName
    }

    /**  */
    private val homepageVm: HomepageComposeViewModel by viewModels()


    /** ****************************** Override ****************************** */

    /**  */
    @Composable
    override fun Toolbar() {
        TopNavigationBar(homepageVm.title)
    }

    /**  */
    @Composable
    override fun Content() {
        PageSidePadding(
            paddingVertical = Dp.SpaceNormal,
        ) {
            Buttons()
        }
    }


    /** ****************************** Functions ****************************** */

    /**  */
    @Composable
    private fun Buttons() {
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_web_view)) {
            activity.startActivityExt<WebViewActivity>()
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(
                text = getStringFromRes(R.string.comkit_homepage_button_log),
                modifier = Modifier.weight(1F)
            ) {
            }
            ButtonClickBlueWeightHorizontal(
                text = getStringFromRes(R.string.comkit_homepage_button_json_utils),
                modifier = Modifier.weight(1F)
            ) {
                switchFragmentByAddHideShow(JsonUtilsFragment(), JsonUtilsFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
            }
            ButtonClickBlueWeightHorizontal(
                text = getStringFromRes(R.string.comkit_homepage_button_glide),
                modifier = Modifier.weight(1F)
            ) {
            }
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_audio_player)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_contacts_fetch)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_font)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_permission)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_screenshot_detect)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_speech_recognition)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_activity_utils)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_adapt_screen_utils)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_app_utils)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_array_utils)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_bar_utils)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_brightness_utils)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_convert_utils)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_datetime_utils)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_device_utils)) {
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_encrypt_utils)) {
        }
    }
}