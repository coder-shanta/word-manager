package com.shanto.miah.wm.app.data.entitys

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
@Entity()
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val word: String,
    val meaning: String
): Parcelable