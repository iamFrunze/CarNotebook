package com.byfrunze.carnotebook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.byfrunze.carnotebook.models.Brand
import com.byfrunze.carnotebook.models.Model
import com.byfrunze.carnotebook.parser.Parser
import io.reactivex.rxjava3.disposables.Disposable
import io.realm.Realm
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 10000.toLong()
    private var mRealm: Realm

    init {
        Realm.init(this)
        mRealm = Realm.getDefaultInstance()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        GlobalScope.launch {
            mRealm = Realm.getDefaultInstance()
            val brands = Parser.getAllBrands()
            mRealm.executeTransaction {
                mRealm.createObject(Brand::class.java)
                mRealm.copyToRealm(brands)
                Log.i("REALM", "3 ${brands}")

            }
            mRealm.executeTransaction {
                mRealm.createObject(Model::class.java)
                for (brand in brands) {
                    mRealm.copyToRealm(Parser.getAllModel(brand.site))
                    Log.i("REALM", "2 ${Parser.getAllModel(brand.site)[0].name}")
                }
            }
        }

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, MainActivity2::class.java))
            finish()
        }, SPLASH_TIME_OUT)

    }
}