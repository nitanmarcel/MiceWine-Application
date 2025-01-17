package com.micewine.emu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.micewine.emu.R
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_AVX_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_DYNAREC_ALIGNED_ATOMICS_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_DYNAREC_BIGBLOCK_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_DYNAREC_BLEEDING_EDGE_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_DYNAREC_CALLRET_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_DYNAREC_FASTNAN_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_DYNAREC_FASTROUND_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_DYNAREC_NATIVEFLAGS_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_DYNAREC_PAUSE_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_DYNAREC_SAFEFLAGS_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_DYNAREC_STRONGMEM_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_DYNAREC_WAIT_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_DYNAREC_WEAKBARRIER_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_DYNAREC_X87DOUBLE_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_LOG_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_NOSIGILL_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_NOSIGSEGV_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_SHOWBT_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.BOX64_SHOWSEGV_KEY
import com.micewine.emu.activities.GeneralSettings.Companion.SPINNER
import com.micewine.emu.activities.GeneralSettings.Companion.SWITCH
import com.micewine.emu.adapters.AdapterSettingsPreferences
import com.micewine.emu.adapters.AdapterSettingsPreferences.SettingsListSpinner

class Box64SettingsFragment : Fragment() {
    private val settingsList: MutableList<SettingsListSpinner> = ArrayList()
    private var rootView: View? = null
    private var recyclerView: RecyclerView? = null
    private var layoutManager: GridLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_settings_model, container, false)
        recyclerView = rootView?.findViewById(R.id.recyclerViewSettingsModel)

        layoutManager = recyclerView?.layoutManager as GridLayoutManager?
        layoutManager?.spanCount = 1

        setAdapter()

        return rootView
    }

    private fun setAdapter() {
        val adapterSettingsPreferences = AdapterSettingsPreferences(settingsList, requireActivity())

        recyclerView?.setAdapter(adapterSettingsPreferences)

        settingsList.clear()

        addToAdapter(R.string.box64_bigblock_title, R.string.box64_bigblock_description, arrayOf("0", "1", "2", "3"), SPINNER, "1", BOX64_DYNAREC_BIGBLOCK_KEY)
        addToAdapter(R.string.box64_strongmem_title, R.string.box64_strongmem_description, arrayOf("0", "1", "2", "3"), SPINNER, "0", BOX64_DYNAREC_STRONGMEM_KEY)
        addToAdapter(R.string.box64_weakbarrier_title, R.string.box64_weakbarrier_description, arrayOf("0", "1", "2"), SPINNER, "0", BOX64_DYNAREC_WEAKBARRIER_KEY)
        addToAdapter(R.string.box64_pause_title, R.string.box64_pause_description, arrayOf("0", "1", "2", "3"), SPINNER, "0", BOX64_DYNAREC_PAUSE_KEY)
        addToAdapter(R.string.box64_x87double_title, R.string.box64_x87double_description, null, SWITCH, "false", BOX64_DYNAREC_X87DOUBLE_KEY)
        addToAdapter(R.string.box64_fastnan_title, R.string.box64_fastnan_description, null, SWITCH, "true", BOX64_DYNAREC_FASTNAN_KEY)
        addToAdapter(R.string.box64_fastround_title, R.string.box64_fastround_description, null, SWITCH, "true", BOX64_DYNAREC_FASTROUND_KEY)
        addToAdapter(R.string.box64_safeflags_title, R.string.box64_safeflags_description, arrayOf("0", "1", "2"), SPINNER, "1", BOX64_DYNAREC_SAFEFLAGS_KEY)
        addToAdapter(R.string.box64_callret_title, R.string.box64_callret_description, null, SWITCH, "true", BOX64_DYNAREC_CALLRET_KEY)
        addToAdapter(R.string.box64_aligned_atomics_title, R.string.box64_aligned_atomics_description, null, SWITCH, "false", BOX64_DYNAREC_ALIGNED_ATOMICS_KEY)
        addToAdapter(R.string.box64_nativeflags_title, R.string.box64_nativeflags_description, null, SWITCH, "true", BOX64_DYNAREC_NATIVEFLAGS_KEY)
        addToAdapter(R.string.box64_bleeding_edge_title, R.string.box64_bleeding_edge_description, null, SWITCH, "true", BOX64_DYNAREC_BLEEDING_EDGE_KEY)
        addToAdapter(R.string.box64_dynarec_wait_title, R.string.box64_dynarec_wait_description, null, SWITCH, "true", BOX64_DYNAREC_WAIT_KEY)
        addToAdapter(R.string.box64_log_title, R.string.box64_log_description, arrayOf("0", "1"), SPINNER, "1", BOX64_LOG_KEY)
        addToAdapter(R.string.box64_avx_title, R.string.box64_avx_description, arrayOf("0", "1", "2"), SPINNER, "2", BOX64_AVX_KEY)

        // Debugging Options

        addToAdapter(R.string.box64_show_segv_title, R.string.box64_show_segv_description, null, SWITCH, "false", BOX64_SHOWSEGV_KEY)
        addToAdapter(R.string.box64_no_sigsegv_title, R.string.box64_no_sigsegv_description, null, SWITCH, "false", BOX64_NOSIGSEGV_KEY)
        addToAdapter(R.string.box64_show_bt_title, R.string.box64_show_bt_description, null, SWITCH, "false", BOX64_SHOWBT_KEY)
        addToAdapter(R.string.box64_no_sigill_title, R.string.box64_no_sigill_description, null, SWITCH, "false", BOX64_NOSIGILL_KEY)
    }

    private fun addToAdapter(titleId: Int, descriptionId: Int, valuesArray: Array<String>?, type: Int, defaultValue: String, keyId: String) {
        settingsList.add(SettingsListSpinner(titleId, descriptionId, valuesArray, type, defaultValue, keyId))
    }
}
