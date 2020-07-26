package com.byfrunze.carnotebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.byfrunze.carnotebook.parser.Parser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch(Dispatchers.IO){
            val a = Parser.getAllBrands()
           Log.i("WEB", "${Parser.getAllModel(a[0].site)}")
        }
    }
}