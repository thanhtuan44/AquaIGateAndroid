package vn.aquavietnam.aquaiget.homepage.listproducts

import vn.aquavietnam.aquaiget.base.BaseVM
import android.content.Context
import android.util.Log
import org.jsoup.Jsoup
import vn.aquavietnam.aquaiget.model.ListProducts
import java.io.IOException
import kotlin.concurrent.thread

class ListProductsVM(context: Context, url: String, product: Int): BaseVM() {
    var listProductsAdapter: ListProductsAdapter
    var listProductsAqua: ((List<ListProducts>) -> Unit)? =  null
    init {
        this.context = context
        listProductsAdapter = ListProductsAdapter(context!!, null, product)
        initRecycler(listProductsAdapter, 2)
        getDataFromUrl(url)
    }

    fun setAdapter(listProduct: List<ListProducts>?) {
        listProductsAdapter!!.arrData = listProduct
        listProductsAdapter!!.notifyDataSetChanged()
    }
    private fun getDataFromUrl(urlParent: String) {
        thread {
            try {
                val prods = ArrayList<ListProducts>()
                var info1: String= ""
                var info2: String = ""
                var info3: String=""
                //Get Document object after parsing the html from given url.
                Jsoup.connect(urlParent).get().run {
                    //2. Parses and scrapes the HTML response
                    val product = getElementsByClass("product-item")
                    for (element in product) {
                        val info = element.getElementsByClass("product-info")
                        if(info.size > 0) {
                            val infoProduct = element.getElementsByTag("p")
                            if(infoProduct.size > 0) {
                                for ((index, value) in infoProduct.withIndex()) {
                                    when (index) {
                                        0 -> info1 = value.text()
                                        1 -> info2 = value.text()
                                        else -> { // Note the block
                                            info3 = value.text()
                                        }
                                    }
                                }
                            }else {
                                val list_unstyled = element.getElementsByClass("list-unstyled")
                                if(list_unstyled.size > 0) {
                                    val infoProduct = element.getElementsByTag("li")
                                    for ((index, value) in infoProduct.withIndex()) {
                                        when (index) {
                                            0 -> info1 = value.text()
                                            1 -> info2 = value.text()
                                            else -> { // Note the block
                                                info3 = value.text()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        val title = element.getElementsByTag("a").attr("title")
                        val url = element.getElementsByTag("a").attr("href")
                        val image = element.getElementsByTag("img").attr("src")
                        prods.add(ListProducts(image,title,info1,info2,info3,url,urlParent))
                    }
                    if(prods.isNotEmpty()) {
                        listProductsAqua?.invoke(prods)
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}