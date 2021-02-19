package sankovskij.api.testappfortfn.devices.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.loadAny
import kotlinx.android.synthetic.main.item_device.view.*
import sankovskij.api.testappfortfn.R
import sankovskij.api.testappfortfn.devices.model.Device
import sankovskij.api.testappfortfn.devices.model.DeviceList
import java.text.SimpleDateFormat
import java.util.*

class DevicesAdapter(
    val onItemClick: ((Device) -> Unit)? = null,
    val context: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    var data: DeviceList = DeviceList(
        listOf(
            Device(0, "", "", false, 0, "", 0),
            Device(0, "", "", false, 0, "", 0),
            Device(0, "", "", false, 0, "", 0)
        ) as MutableList<Device>
    )
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_DEVICE) {
            DeviceViewHolder(
                inflater.inflate(R.layout.item_device, parent, false) as View
            )

        } else {
            LoadingViewHolder(
                inflater.inflate(R.layout.item_loading, parent, false) as View
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_DEVICE) {
            holder as DeviceViewHolder
            holder.bind(data.devicesList[position])
        } else {
            holder as LoadingViewHolder
            holder.bind(data.devicesList[position])
        }
    }

    override fun getItemCount(): Int {
        return data.devicesList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data.devicesList[position].name.isNullOrBlank()) TYPE_LOADING else TYPE_DEVICE
    }

    inner class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(device: Device) {
        }
    }


    inner class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(device: Device) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                if (device.isOnline) {
                    itemView.online_offline.text = context.getString(R.string.online)
                    itemView.online_offline.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.green_circle_ic,0,0,0)

                } else {
                    itemView.online_offline.text = context.getString(R.string.offline)
                    itemView.online_offline.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.red_circle_ic,0,0,0)

                }
                itemView.device_name.text = device.name
                when (device.status) {
                    "1" -> {
                        itemView.status.text = context.getString(R.string.no_gas)
                        itemView.status.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_stop,0,0,0)

                    }
                    "2" -> {
                        itemView.status.text = context.getString(R.string.oh_no_this_is_gas)
                        itemView.status.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_warning,0,0,0)

                    }
                    else -> {
                        itemView.status.text = device.status
                        itemView.status.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_rocket,0,0,0)

                    }
                }
                if (device.type == 1) {
                    val sdf = SimpleDateFormat("HH:mm:ss")
                    val date = Date(device.lastWorkTime.toLong() * 1000)
                    itemView.time.text = sdf.format(date)
                } else {
                    itemView.time.isVisible = false

                }


                itemView.device_imageView.loadAny(baseURL + device.icon) {
                    error(R.drawable.ic_stop)
                    placeholder(R.drawable.ic_clock)
                }
                itemView.item_layout.setOnClickListener { onItemClick?.invoke(device) }
            }
        }
    }

    companion object {
        private const val TYPE_LOADING = 0
        private const val TYPE_DEVICE = 1
        private const val baseURL = "https://dev.api.sls.ompr.io"

    }
}
