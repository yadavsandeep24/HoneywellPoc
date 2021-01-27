package com.sandeep.newsapp.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import java.util.*

data class Hit(

    @Expose
    var _highlightResult: HighlightResult? = null,

    @Expose
    var _tags: ArrayList<String>? = null,

    @Expose
    var author: String? = null,

    @Expose
    var comment_text: String?=  null,

    @Expose
    var created_at: String?=  null,

    @Expose
    var created_at_i: Int =0,

    @Expose
    var num_comments: Int =0,

    @Expose
    var objectID: String?=  null,

    @Expose
    var parent_id: String?=  null,

    @Expose
    var points: Int =0,

    @Expose
    var relevancy_score: Int =0,

    @Expose
    var story_id: String?=  null,

    @Expose
    var story_text: String?=  null,

    @Expose
    var story_title: String?=  null,

    @Expose
    var story_url: String?=  null,

    @Expose
    var title: String?=  null,

    @Expose
    var url: String? =  null

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(HighlightResult::class.java.classLoader),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(_highlightResult, flags)
        parcel.writeStringList(_tags)
        parcel.writeString(author)
        parcel.writeString(comment_text)
        parcel.writeString(created_at)
        parcel.writeInt(created_at_i)
        parcel.writeInt(num_comments)
        parcel.writeString(objectID)
        parcel.writeString(parent_id)
        parcel.writeInt(points)
        parcel.writeInt(relevancy_score)
        parcel.writeString(story_id)
        parcel.writeString(story_text)
        parcel.writeString(story_title)
        parcel.writeString(story_url)
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hit> {
        override fun createFromParcel(parcel: Parcel): Hit {
            return Hit(parcel)
        }

        override fun newArray(size: Int): Array<Hit?> {
            return arrayOfNulls(size)
        }
    }
}