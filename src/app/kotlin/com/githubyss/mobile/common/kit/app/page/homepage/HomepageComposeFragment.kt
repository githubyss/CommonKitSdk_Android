package com.githubyss.mobile.common.kit.app.page.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.R
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
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseActivity
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.kit.util.runOnUiThread
import com.githubyss.mobile.common.res.button_click.compose.CommonButtonClickBlue


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
        Toolbar(homepageVm.title)
    }

    @Composable
    override fun Content() {
        Buttons()
    }


    /** ****************************** Functions ****************************** */

    @Composable
    private fun Buttons() {
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_mvvm)) {
            runOnUiThread {
                FragmentUtils.switchFragmentByAddHideShow(MvvmFragment(), MvvmFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_mvi)) {
            runOnUiThread {
                ActivityUtils.startActivity(activity, MviActivity::class.java)
            }
        }
        Column() {
            CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_compose)) {
                runOnUiThread {
                    ActivityUtils.startActivity(activity, ComposeActivity::class.java)
                    // FragmentUtils.switchFragmentByAddHideShow(ComposeFragment(), ComposeFragment.TAG, this, parentFragmentManager, true)
                }
            }
            CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_compose_toolbar)) {
                runOnUiThread {
                    ActivityUtils.startActivity(activity, ComposeToolbarActivity::class.java)
                    // FragmentUtils.switchFragmentByAddHideShow(ComposeToolbarFragment(), ComposeToolbarFragment.TAG, this, parentFragmentManager, true)
                }
            }
        }
        Column {
            CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_view_binding_reflect)) {
                runOnUiThread {
                    ActivityUtils.startActivity(activity, ReflectActivity::class.java)
                }
            }
            CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_view_binding_reflect_toolbar)) {
                runOnUiThread {
                    ActivityUtils.startActivity(activity, ReflectToolbarActivity::class.java)
                }
            }
        }
        Column {
            CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_view_binding_inline)) {
                runOnUiThread {
                    ActivityUtils.startActivity(activity, InlineActivity::class.java)
                }
            }
            CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_view_binding_inline_toolbar)) {
                runOnUiThread {
                    ActivityUtils.startActivity(activity, InlineToolbarActivity::class.java)
                }
            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_log)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_json_utils)) {
            runOnUiThread {
                FragmentUtils.switchFragmentByAddHideShow(JsonUtilsFragment(), JsonUtilsFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_glide)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_lifecycle)) {
            runOnUiThread {
                ActivityUtils.startActivity(activity, LifecycleActivity::class.java)
            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_audio_player)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_contacts_fetch)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_font)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_permission)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_screenshot_detect)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_speech_recognition)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_activity_utils)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_adapt_screen_utils)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_app_utils)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_array_utils)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_bar_utils)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_brightness_utils)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_convert_utils)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_datetime_utils)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_device_utils)) {
            runOnUiThread {

            }
        }
        CommonButtonClickBlue(getStringFromRes(R.string.comkit_homepage_button_encrypt_utils)) {
            runOnUiThread {

            }
        }
    }
}
