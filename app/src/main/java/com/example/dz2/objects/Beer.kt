package com.example.dz2.objects


import com.google.gson.annotations.SerializedName

class Beer: java.io.Serializable {
    @SerializedName("image_url")
    var url = ""
    fun imageId() = url

    @SerializedName("id") var id=""
    fun Id()= id
}