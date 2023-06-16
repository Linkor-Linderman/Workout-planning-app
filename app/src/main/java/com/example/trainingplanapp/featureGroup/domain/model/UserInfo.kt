package com.example.trainingplanapp.featureGroup.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val id: String,
    val imageId: String,
    val login: String,
    val name: String
) : Parcelable