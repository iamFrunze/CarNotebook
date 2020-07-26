package com.byfrunze.carnotebook.parser

import android.util.Log
import com.byfrunze.carnotebook.Mark
import com.byfrunze.carnotebook.Model
import org.jsoup.Jsoup
import org.jsoup.nodes.Element


object Parser {


    //        fun getAllBrands(): ArrayList<String> {
//            val parserConfig = Jsoup.connect("https://www.avtovzglyad.ru/brand/")
//                .userAgent("Chrome/4.0.249.0 Safari/532.5")
//                .referrer("http://www.google.com")
//                .get()
//            val parseBrand = parserConfig.getElementsByClass("item")
//
//            val arrayOfBrand = ArrayList<String>()
//            for (brand: Element in parseBrand) {
//                arrayOfBrand.add(brand.text())
//                Log.i("WEB", brand.select("a").attr("href"))
//            }
//            return arrayOfBrand
//
//        }

    private const val URL = "https://www.avtovzglyad.ru/brand/"

    private val parserBrandConfig = Jsoup.connect("https://carobka.ru/cars/")
        .userAgent("Chrome/4.0.249.0 Safari/532.5")
        .referrer("http://www.google.com")
        .get()


    fun getAllBrands(): ArrayList<Mark> {
        val arrayOfMark = ArrayList<Mark>()
        val parseMarks = parserBrandConfig.getElementsByClass("item-mark")

        for (mark: Element in parseMarks) {
            val logo = mark.select("img").attr("src")
            val name = mark.select("span").text()
            arrayOfMark.add(
                Mark(
                    logo = logo,
                    name = name,
                    site = "$URL${name.toLowerCase()}"
                )
            )
        }

        return arrayOfMark
    }

    fun getAllModel(site: String): ArrayList<Model> {
        val ModelURL = "https://www.avtovzglyad.ru"
        val parserModelConfig = Jsoup.connect(site)
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .referrer("http://www.google.com")
            .get()
        val arrayOfModels = ArrayList<Model>()

        val parseModels = parserModelConfig.getElementsByClass("item")
        for (mark: Element in parseModels) {
            val logo = mark.select("div").attr("style").removePrefix("background-image: url('")
                .removeSuffix("')")
            val name = mark.getElementsByClass("name").text()
            Log.i("WEB", logo)
            arrayOfModels.add(
                Model(
                    logo = "$ModelURL$logo",
                    name = name
                )
            )
        }
        return arrayOfModels

    }


}