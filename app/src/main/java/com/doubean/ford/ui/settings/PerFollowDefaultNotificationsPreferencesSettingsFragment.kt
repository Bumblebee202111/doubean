package com.doubean.ford.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.*
import com.doubean.ford.R
import com.doubean.ford.model.PostSortBy
import com.doubean.ford.util.InjectorUtils
import com.google.android.material.appbar.MaterialToolbar

// PreferenceFragmentCompat + DataStore<Preferences>
// See https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:datastore/datastore-sampleapp/src/main/java/com/example/datastoresampleapp/SettingsFragment.kt
// Also conforms to MVVM
class PerFollowDefaultNotificationsPreferencesSettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: PerFollowDefaultNotificationsPreferencesSettingsViewModel by viewModels {
        InjectorUtils.providePerFollowDefaultNotificationsSettingsViewModelFactory(
            requireContext()
        )
    }

    private var postNotificationsSwitchPreference: SwitchPreferenceCompat? = null
    private var allowDuplicateNotificationsSwitchPreference: SwitchPreferenceCompat? = null
    private var sortRecommendedPostsByListPreference: ListPreference? = null
    private var feedRequestPostCountLimitSeekBarPreference: SeekBarPreference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.per_follow_default_notifications_preferences, rootKey)
        postNotificationsSwitchPreference =
            preferenceManager.findPreference<SwitchPreferenceCompat>("post_notifications")
        allowDuplicateNotificationsSwitchPreference =
            preferenceManager.findPreference<SwitchPreferenceCompat>("allow_duplicate_notifications")
        sortRecommendedPostsByListPreference =
            preferenceManager.findPreference<ListPreference>("sort_recommended_posts_by")
        feedRequestPostCountLimitSeekBarPreference =
            preferenceManager.findPreference<SeekBarPreference>("feed_request_post_count_limit")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val linearLayout =
            super.onCreateView(inflater, container, savedInstanceState) as LinearLayout
        linearLayout.fitsSystemWindows = true
        addToolbar(linearLayout)

        return linearLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.enablePostNotifications.observe(viewLifecycleOwner) {
            postNotificationsSwitchPreference?.isChecked = it
        }
        postNotificationsSwitchPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, _ ->
                viewModel.toggleEnablePostNotifications()
                true
            }

        viewModel.allowDuplicateNotifications.observe(viewLifecycleOwner) {
            allowDuplicateNotificationsSwitchPreference?.isChecked = it
        }
        allowDuplicateNotificationsSwitchPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, _ ->
                viewModel.toggleAllowDuplicateNotifications()
                true
            }

        viewModel.sortRecommendedPostsBy.observe(viewLifecycleOwner) {
            sortRecommendedPostsByListPreference?.value = getSortRecommendedPostsByValue(it)
        }
        sortRecommendedPostsByListPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                viewModel.setSortRecommendedPostsBy(getSortRecommendedPostsBy(newValue as String))
                true
            }

        viewModel.feedRequestPostCountLimit.observe(viewLifecycleOwner) {
            feedRequestPostCountLimitSeekBarPreference?.value = it
        }
        feedRequestPostCountLimitSeekBarPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                viewModel.setFeedRequestPostCountLimit(newValue as Int)
                true
            }
    }

    private fun getSortRecommendedPostsBy(value: String) =
        when (value) {
            getString(R.string.last_updated_value) -> PostSortBy.LAST_UPDATED
            getString(R.string.new_top_value) -> PostSortBy.NEW_TOP
            else -> throw IndexOutOfBoundsException()
        }

    private fun getSortRecommendedPostsByValue(postSortBy: PostSortBy) =
        when (postSortBy) {
            PostSortBy.LAST_UPDATED -> getString(R.string.last_updated_value)
            PostSortBy.NEW_TOP -> getString(R.string.new_top_value)
            else -> throw IndexOutOfBoundsException()
        }

    private fun addToolbar(linearLayout: LinearLayout) {
        // https://blog.csdn.net/m0_46958584/article/details/105663403
        val toolbar = LayoutInflater.from(context)
            .inflate(
                R.layout.toolbar_per_follow_default_notifications_settings,
                linearLayout,
                false
            ) as MaterialToolbar
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        linearLayout.addView(toolbar, 0)
    }
}