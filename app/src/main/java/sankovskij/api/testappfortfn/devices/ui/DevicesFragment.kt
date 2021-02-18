package sankovskij.api.testappfortfn.devices.ui

import android.graphics.Color
import sankovskij.api.testappfortfn.devices.model.DeviceList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_device.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import sankovskij.api.testappfortfn.App
import sankovskij.api.testappfortfn.R
import sankovskij.api.testappfortfn.devices.dialog.DeleteDialogFragment
import sankovskij.api.testappfortfn.devices.model.Device
import sankovskij.api.testappfortfn.devices.view.DevicesView
import sankovskij.api.testappfortfn.devices.presenter.DevicesPresenter

class DevicesFragment : MvpAppCompatFragment(), DevicesView {

    lateinit var adapter: DevicesAdapter

    val presenter: DevicesPresenter by moxyPresenter { DevicesPresenter().apply {
        App.instance.appComponent.inject(this)
    }}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.instance.appComponent.inject(this)
        return View.inflate(context, R.layout.fragment_device, null)

    }

    override fun init() {
        rv_devices.layoutManager = GridLayoutManager(context, 1)
        adapter = DevicesAdapter({
            var deleteDialogFragment = DeleteDialogFragment(it.name) {
                presenter.deleteDeviceFromServer(it)
               }
            deleteDialogFragment.setStyle(DialogFragment.STYLE_NO_FRAME, 0)
            deleteDialogFragment.show(childFragmentManager, "deleteDialog")
        },requireContext())
        rv_devices.adapter = adapter
        fabProgress.isVisible = true
        extended_fab_button.isVisible = false
        fab_button.isVisible = true
        error_textview.setTextColor(Color.parseColor("#3F4956"))
        extended_fab_button.setOnClickListener {
            presenter.resetDeviceFromServer()
        }
    }

    override fun updateRV(deviceList: DeviceList) {
        adapter.data = deviceList
        fabProgress.isVisible = false
        extended_fab_button.isVisible = true
        fab_button.isVisible = false
        error_textview.setTextColor(Color.parseColor("#885FF8"))
    }

    override fun notifyDataChanged() {
        adapter.notifyDataSetChanged()
    }

    override fun navigateToErrorScreen() {
        findNavController().navigate(R.id.action_fragment_with_devices_to_fragment_error)
    }

    override fun removeDeviceFromAdapter(device: Device) {
        adapter.data.devicesList.remove(device)
    }

}