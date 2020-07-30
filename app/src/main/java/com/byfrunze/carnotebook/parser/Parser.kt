package com.byfrunze.carnotebook.parser

import android.util.Log
import com.byfrunze.carnotebook.models.Brand
import com.byfrunze.carnotebook.models.Model
import com.google.firebase.firestore.FirebaseFirestore
import io.realm.Realm
import kotlinx.coroutines.delay
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.select.Elements


object Parser {
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    /*
    private val URL = "https://www.avtovzglyad.ru/brand/"
    private val parserBrandConfig = Jsoup.connect("https://carobka.ru/cars/")
        .userAgent("Chrome/4.0.249.0 Safari/532.5")
        .referrer("http://www.google.com")
        .get()

    fun getAllBrands(): ArrayList<Brand> {


        val arrayOfBrand = ArrayList<Brand>()
        val parseBrands = parserBrandConfig.getElementsByClass("item-mark")

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

            db.collection("brands")
                .document(name)
                .set(
                    mapOf(
                        "name" to name,
                        "site" to site,
                        "logo" to logo
                    )
                ).addOnSuccessListener {
                    Log.i("FIRE", "SUCCESS")
                }
                .addOnFailureListener {
                    Log.i("FIRE", "Error")
                }
            arrayOfBrand.add(
                Brand(name = name, logo = logo, site = site)
            )

        }
        return arrayOfBrand
    }

    private val mDB = db.collection("models")
    fun getAllModel(site: String, brands: String): Boolean {

        val ModelURL = "https://www.avtovzglyad.ru"
        val parserModelConfig = Jsoup.connect(site)
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .referrer("http://www.google.com")
            .get()
        val parseModels = parserModelConfig.getElementsByClass("catalog-model-list").first()

        var isCompleted = false

        for (brand: Element in parseModels.children()) {
            val logo = brand.select("div").attr("style").removePrefix("background-image: url('")
                .removeSuffix("')")

            val name = brand.select("h4").text()

            mDB.document(brands)
                .collection(name).document("properties")
                .set(
                    mapOf(
                        "name" to name,
                        "logo" to "$ModelURL$logo"
                    )
                ).addOnSuccessListener {
                    Log.i("FIRE", "SUCCESS")
                    isCompleted = true
                }
                .addOnFailureListener {
                    isCompleted = false
                }
        }
        return isCompleted

    }
*/
    private val mDB = db.collection("models")
    private val newURLCatalog = "https://auto.mail.ru/catalog/"
    private val newURL = "https://auto.mail.ru"

    val newParserBrandConfig = Jsoup.connect(newURLCatalog)
        .userAgent("Chrome/4.0.249.0 Safari/532.5")
        .referrer("http://www.google.com")
        .get()

    var il = 0
    fun newBrands(): HashSet<Brand> {
        val parseBrands = newParserBrandConfig.getElementsByClass("p-firm")
        val hash = HashSet<Brand>()

        for (brand: Element in parseBrands) {
            val logo = brand.getElementsByClass("p-firm__logo").attr("src")
            val site = "$newURL${brand.getElementsByClass("p-firm__text").attr("href")}"
            val name = brand.getElementsByClass("p-firm__text").text()

            val currentBrand = Brand(
                logo = logo,
                name = name,
                site = site
            )
            hash.add(currentBrand)
        }
        for (brand: Brand in hash) {
            Thread.sleep(2000L)
            db.collection("brands")
                .document(brand.name)
                .set(
                    mapOf(
                        "name" to brand.name,
                        "site" to brand.site,
                        "logo" to brand.logo
                    )
                ).addOnSuccessListener {
                    Log.i("FIRE", "${il++} SUCCESS")
                }
                .addOnFailureListener {
                    Log.i("FIRE", "Error")
                }
        }
        return hash
    }

    var i = 0
    fun newModels(site: String, brand: String): Boolean {
        val parserModelConfig = Jsoup.connect(site)
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .referrer("http://www.google.com")
            .get()
        val parserModel = parserModelConfig.getElementsByAttribute("data-module").first()
        val parserCurrentModel = parserModelConfig.getElementsByClass("p-car")
        var isCompleted = false

        for (model: Element in parserCurrentModel) {
            val name = model.select("a").text().replace('/','-')
            val properties = model.select("div>div").text()
            mDB.document(brand)
                .collection(name).document("properties")
                .set(
                    mapOf(
                        "name" to name,
                        "properties" to properties
                    )
                ).addOnSuccessListener {
                    isCompleted = true
                }
                .addOnFailureListener {
                    isCompleted = false
                }
        }
        Log.i("FIRE", "${i++} $isCompleted $brand")

        return isCompleted
    }


}