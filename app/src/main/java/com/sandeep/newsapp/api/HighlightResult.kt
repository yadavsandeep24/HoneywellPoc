package com.sandeep.newsapp.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

data class HighlightResult(

    @Expose
    val author: Author?,

    @Expose
    val story_text: StoryText?,

    @Expose
    val title: Title?,

    @Expose
    val url: Url?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Author::class.java.classLoader),
        parcel.readParcelable(StoryText::class.java.classLoader),
        parcel.readParcelable(Title::class.java.classLoader),
        parcel.readParcelable(Url::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(author, flags)
        parcel.writeParcelable(story_text, flags)
        parcel.writeParcelable(title, flags)
        parcel.writeParcelable(url, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HighlightResult> {
        override fun createFromParcel(parcel: Parcel): HighlightResult {
            return HighlightResult(parcel)
        }

        override fun newArray(size: Int): Array<HighlightResult?> {
            return arrayOfNulls(size)
        }
    }
}