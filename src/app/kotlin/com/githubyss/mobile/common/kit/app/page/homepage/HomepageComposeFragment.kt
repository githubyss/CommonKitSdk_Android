package com.githubyss.mobile.common.kit.app.page.homepage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.compose_ui.comui.ButtonClickBlueWeightHorizontal
import com.githubyss.mobile.common.kit.app.compose_ui.comui.LayoutWeightHorizontal
import com.githubyss.mobile.common.kit.app.compose_ui.comui.PageSidePadding
import com.githubyss.mobile.common.kit.app.compose_ui.comui.TopNavigationBar
import com.githubyss.mobile.common.kit.app.page.binding_inline.InlineActivity
import com.githubyss.mobile.common.kit.app.page.binding_inline.InlineToolbarActivity
import com.githubyss.mobile.common.kit.app.page.binding_reflect.ReflectActivity
import com.githubyss.mobile.common.kit.app.page.binding_reflect.ReflectToolbarActivity
import com.githubyss.mobile.common.kit.app.page.compose.ComposeActivity
import com.githubyss.mobile.common.kit.app.page.compose.ComposeToolbarActivity
import com.githubyss.mobile.common.kit.app.page.json_utils.JsonUtilsFragment
import com.githubyss.mobile.common.kit.app.page.lifecycle.LifecycleActivity
import com.githubyss.mobile.common.kit.app.page.mvi.MviActivity
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.MvvmFragment
import com.githubyss.mobile.common.kit.app.page.mvvm_compose.MvvmComposeActivity
import com.githubyss.mobile.common.kit.app.page.state_compose.StateComposeActivity
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseActivity
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.kit.util.getStringFromRes
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

    companion object {
        val TAG: String = HomepageComposeFragment::class.java.simpleName
    }

    private val homepageVm: HomepageComposeViewModel by viewModels()


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        TopNavigationBar(homepageVm.title)
    }

    @Composable
    override fun Content() {
        PageSidePadding(
            paddingVertical = Dp.SpaceNormal,
        ) {
            Buttons()
        }
    }


    /** ****************************** Functions ****************************** */

    @Composable
    private fun Buttons() {
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_mvvm)) {
            FragmentUtils.switchFragmentByAddHideShow(MvvmFragment(), MvvmFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }
        ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_mvi)) {
            ActivityUtils.startActivity(activity, MviActivity::class.java)
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(
                text = getStringFromRes(R.string.comkit_homepage_button_mvvm_compose),
                modifier = Modifier.weight(1F)
            ) {
                ActivityUtils.startActivity(activity, MvvmComposeActivity::class.java)
            }
            ButtonClickBlueWeightHorizontal(
                text = getStringFromRes(R.string.comkit_homepage_button_state_compose),
                modifier = Modifier.weight(1F)
            ) {
                ActivityUtils.startActivity(activity, StateComposeActivity::class.java)
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(
                text = getStringFromRes(R.string.comkit_homepage_button_compose),
                modifier = Modifier.weight(1F)
            ) {
                ActivityUtils.startActivity(activity, ComposeActivity::class.java)
                // FragmentUtils.switchFragmentByAddHideShow(ComposeFragment(), ComposeFragment.TAG, this, parentFragmentManager, true)
            }
            ButtonClickBlueWeightHorizontal(
                text = getStringFromRes(R.string.comkit_homepage_button_compose_toolbar),
                modifier = Modifier.weight(1F)
            ) {
                ActivityUtils.startActivity(activity, ComposeToolbarActivity::class.java)
                // FragmentUtils.switchFragmentByAddHideShow(ComposeToolbarFragment(), ComposeToolbarFragment.TAG, this, parentFragmentManager, true)
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(
                text = getStringFromRes(R.string.comkit_homepage_button_view_binding_reflect),
                modifier = Modifier.weight(1F)
            ) {
                ActivityUtils.startActivity(activity, ReflectActivity::class.java)
            }
            ButtonClickBlueWeightHorizontal(
                text = getStringFromRes(R.string.comkit_homepage_button_view_binding_reflect_toolbar),
                modifier = Modifier.weight(1F)
            ) {
                ActivityUtils.startActivity(activity, ReflectToolbarActivity::class.java)
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(
                text = getStringFromRes(R.string.comkit_homepage_button_view_binding_inline),
                modifier = Modifier.weight(1F)
            ) {
                ActivityUtils.startActivity(activity, InlineActivity::class.java)
            }
            ButtonClickBlueWeightHorizontal(
                text = getStringFromRes(R.string.comkit_homepage_button_view_binding_inline_toolbar),
                modifier = Modifier.weight(1F)
            ) {
                ActivityUtils.startActivity(activity, InlineToolbarActivity::class.java)
            }
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
                FragmentUtils.switchFragmentByAddHideShow(JsonUtilsFragment(), JsonUtilsFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
            }
            ButtonClickBlueWeightHorizontal(
                text = getStringFromRes(R.string.comkit_homepage_button_glide),
                modifier = Modifier.weight(1F)
            ) {
            }
            ButtonClickBlueWeightHorizontal(
                text = getStringFromRes(R.string.comkit_homepage_button_lifecycle),
                modifier = Modifier.weight(1F)
            ) {
                ActivityUtils.startActivity(activity, LifecycleActivity::class.java)
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