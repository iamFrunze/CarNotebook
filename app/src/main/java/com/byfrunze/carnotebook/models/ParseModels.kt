package com.byfrunze.carnotebook.models

import io.realm.RealmObject

data class Brand(
    var name: String = "",
    var logo: String = "",
    var site: String = ""
)

data class Model(
    var name: String = "",
    var properties: String = ""
)