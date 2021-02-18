package sankovskij.api.testappfortfn.devices.model

import com.google.gson.annotations.SerializedName

data class Device(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("icon") val icon: String,
    @field:SerializedName("isOnline") val isOnline: Boolean,
    @field:SerializedName("type") val type: Int,
    @field:SerializedName("status") val status: String,
    @field:SerializedName("lastWorkTime") val lastWorkTime: Int
)