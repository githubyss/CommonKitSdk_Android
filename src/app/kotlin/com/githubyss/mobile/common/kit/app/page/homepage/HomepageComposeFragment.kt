package com.githubyss.mobile.common.kit.app.page.homepage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.fragment.app.viewModels
import com.githubyss.common.base.activity_fragment.classical.BaseActivity
import com.githubyss.common.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.z_copy.compose_ui.ButtonClickBlueWeightHorizontal
import com.githubyss.mobile.common.kit.app.z_copy.compose_ui.LayoutWeightHorizontal
import com.githubyss.mobile.common.kit.app.z_copy.compose_ui.PageSidePadding
import com.githubyss.mobile.common.kit.app.z_copy.compose_ui.TopNavigationBar
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
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_log), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_shell), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_stack_trace), modifier = Modifier.weight(1F)) {
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_array), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_list), modifier = Modifier.weight(1F)) {
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_class), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_string), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_number), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_regex), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_json), modifier = Modifier.weight(1F)) {
                switchFragmentByAddHideShow(JsonUtilsFragment(), JsonUtilsFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_encrypt), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_encode), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_datetime), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_convert), modifier = Modifier.weight(1F)) {
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_contacts_fetch), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_screenshot_detect), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_permission), modifier = Modifier.weight(1F)) {
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_image), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_glide), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_audio_player), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_speech_recognition), modifier = Modifier.weight(1F)) {
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_file), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_kv), modifier = Modifier.weight(1F)) {
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_uri), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_bundle), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_intent), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_stream), modifier = Modifier.weight(1F)) {
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_app), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_activity), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_fragment), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_bar), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_web_view), modifier = Modifier.weight(1F)) {
                activity.startActivityExt<WebViewActivity>()
            }
        }
        LayoutWeightHorizontal {
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_font), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_property), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_resource), modifier = Modifier.weight(1F)) {
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_adapt_screen), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_brightness), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_device), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_system), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_network), modifier = Modifier.weight(1F)) {
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_anti_shake), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_snackbar), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_toast), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_typewriter), modifier = Modifier.weight(1F)) {
            }
        }
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_task), modifier = Modifier.weight(1F)) {
            }
            ButtonClickBlueWeightHorizontal(text = getStringFromRes(R.string.comkit_homepage_button_timer), modifier = Modifier.weight(1F)) {
            }
        }
    }
}