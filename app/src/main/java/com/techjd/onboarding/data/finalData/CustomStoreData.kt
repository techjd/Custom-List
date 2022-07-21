package com.techjd.onboarding.data.finalData

import java.util.ArrayList

data class CustomStoreData(
    val priority: Int,
    val title: String,
    val data: Array<ArrayList<SocialStoreItem>?>
)
