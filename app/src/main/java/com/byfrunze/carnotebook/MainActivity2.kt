package com.byfrunze.carnotebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.byfrunze.carnotebook.models.Brand
import com.byfrunze.carnotebook.models.Model
import io.realm.Realm

class MainActivity2 : AppCompatActivity() {

    val mRealm: Realm

    init {
        mRealm = Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Log.i("REALM", "${mRealm.where(Brand::class.java).findAllAsync()}")
        Log.i("REALM", "1 ${mRealm.where(Model::class.java).findAllAsync()}")

    }
}