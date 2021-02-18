package sankovskij.api.testappfortfn.devices.model

import com.google.gson.annotations.SerializedName

data class DeviceList (
	@field:SerializedName("data") val devicesList : MutableList<Device>
)