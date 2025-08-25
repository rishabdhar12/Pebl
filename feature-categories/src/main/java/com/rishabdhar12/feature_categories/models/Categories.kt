package com.rishabdhar12.feature_categories.models


import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("Education")
    val education: List<String>,
    @SerializedName("Entertainment")
    val entertainment: List<String>,
    @SerializedName("Financial")
    val financial: List<String>,
    @SerializedName("Food")
    val food: List<String>,
    @SerializedName("Gifts Donations")
    val giftsDonations: List<String>,
    @SerializedName("Health")
    val health: List<String>,
    @SerializedName("Housing")
    val housing: List<String>,
    @SerializedName("Miscellaneous")
    val miscellaneous: List<String>,
    @SerializedName("Personal Care")
    val personalCare: List<String>,
    @SerializedName("Transportation")
    val transportation: List<String>
)