package com.byfrunze.carnotebook.models

import io.realm.RealmObject

open class Brand(
    var name: String = "",
    var logo: String = "",
    var site: String = ""
) : RealmObject()

open class Model(
    var name: String = "",
    var logo: String = ""
) : RealmObject()