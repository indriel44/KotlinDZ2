package com.example.dz2.objects


import com.google.gson.annotations.SerializedName

class Beer {
    @SerializedName("image_url")
    var id = ""

    fun imageId() = id
}