package com.sandeep.newsapp.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import java.util.*

data class Title(

    @Expose
    val matchLevel: String?,

    @Expose
    val matchedWords: ArrayList<String>?,

    @Expose
    val value: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(matchLevel)
        parcel.writeStringList(matchedWords)
        parcel.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Title> {
        override fun createFromParcel(parcel: Parcel): Title {
            return Title(parcel)
        }

        override fun newArray(size: Int): Array<Title?> {
            return arrayOfNulls(size)
        }
    }
}