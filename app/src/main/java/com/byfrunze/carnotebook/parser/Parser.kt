package com.byfrunze.carnotebook.parser

import android.util.Log
import com.byfrunze.carnotebook.models.Brand
import com.byfrunze.carnotebook.models.Model
import io.realm.Realm
import org.jsoup.Jsoup
import org.jsoup.nodes.Element


object Parser {

    private val URL = "https://www.avtovzglyad.ru/brand/"

    private val parserBrandConfig = Jsoup.connect("https://carobka.ru/cars/")
        .userAgent("Chrome/4.0.249.0 Safari/532.5")
        .referrer("http://www.google.com")
        .get()

    fun getAllBrands(): ArrayList<Brand> {


        val arrayOfBrand = ArrayList<Brand>()
        val parseBrands = parserBrandConfig.getElementsByClass("item-mark")

        Log.i("SITE", "$parseBrands")
        for (brand: Element in parseBrands) {
            val logo = brand.select("img").attr("src")

            val name = brand.select("span").text()

            if (name.toLowerCase() == "камаз" ||
                name.toLowerCase() == "htm" ||
                name.toLowerCase() == "man"

            ) continue
            val site = when ("$URL${name.toLowerCase()}") {
                "${URL}geely" -> "${URL}gelly"
                "${URL}газ" -> "${URL}gaz"
                "${URL}lada" -> "${URL}vaz"
                "${URL}land rover" -> "${URL}land_rover"
                "${URL}mercedes" -> "${URL}mercedes-benz"
                else -> "${URL}${name.toLowerCase()}"
            }

            Log.i("BRAND", "$site")
            arrayOfBrand.add(
                Brand(
                    logo = logo,
                    name = name,
                    site = site
                )
            )
        }
        return arrayOfBrand
    }

    fun getAllModel(site: String): ArrayList<Model> {
        val ModelURL = "https://www.avtovzglyad.ru"
        val parserModelConfig = Jsoup.connect(site)
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .referrer("http://www.google.com")
            .get()
        val arrayOfModels = ArrayList<Model>()

        val parseModels = parserModelConfig.getElementsByClass("item")
        for (brand: Element in parseModels) {
            val logo = brand.select("div").attr("style").removePrefix("background-image: url('")
                .removeSuffix("')")
            val name = brand.getElementsByClass("name").text()
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