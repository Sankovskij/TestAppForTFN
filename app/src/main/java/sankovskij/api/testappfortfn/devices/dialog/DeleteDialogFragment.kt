package sankovskij.api.testappfortfn.devices.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import sankovskij.api.testappfortfn.R

class DeleteDialogFragment(val name : String, private val onItemClick: (() -> Unit)? = null) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_dialog, container, false)
        val deleteDevice = """${getString(R.string.do_you_want_to_delete)}$name?"""
        rootView.dialog_device_name.text = deleteDevice

        rootView.cancel.setOnClickListener {
            dismiss()
        }
        rootView.delete.setOnClickListener {
            onItemClick?.invoke()
            dismiss()
        }
        return rootView

    }
}