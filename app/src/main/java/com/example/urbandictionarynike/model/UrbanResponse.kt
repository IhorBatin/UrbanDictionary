package com.example.urbandictionarynike.model

import android.os.Parcelable
import com.example.urbandictionarynike.model.Definition
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UrbanResponse(

    @field:Json(name = "list")
    val list: List<Definition>?
) : Parcelable