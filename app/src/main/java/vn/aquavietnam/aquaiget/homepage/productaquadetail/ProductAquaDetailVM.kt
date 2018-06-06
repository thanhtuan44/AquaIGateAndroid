package vn.aquavietnam.aquaiget.homepage.productaquadetail

import android.content.Context
import android.util.Log
import org.jsoup.Jsoup
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.InfoDetail
import vn.aquavietnam.aquaiget.model.ProductInfoDetail
import java.io.IOException
import kotlin.concurrent.thread

class ProductAquaDetailVM(context: Context, url: String): BaseVM() {
    var productAquaDetailAdapter: ProductAquaDetailAdapter
    var productDetail: ((ProductInfoDetail) -> Unit)? =  null
    init {
        this.context = context
        productAquaDetailAdapter = ProductAquaDetailAdapter(context!!, null)
        initRecycler(productAquaDetailAdapter, 1)
        getDataFromUrl(url)
    }

    fun setAdapter(infoDetail: List<InfoDetail>?) {
        productAquaDetailAdapter!!.arrData = infoDetail
        productAquaDetailAdapter!!.notifyDataSetChanged()
    }
    private fun getDataFromUrl(url: String) {
        var prod : ProductInfoDetail? = null
        thread {
            try {
                var image: String = ""
                var model: String = ""
                var title: String = ""
                val infos = ArrayList<InfoDetail>()
                //Get Document object after parsing the html from given url.
                Jsoup.connect(url).get().run {
                    //2. Parses and scrapes the HTML response
                    val product = getElementsByClass("product-featured")
                    Log.d("PRODUCT",product.toString())
                    for (element in product) {
                        val gallery = element.getElementsByClass("product-gallery")
                        val desc = element.getElementsByClass("product-desc")
                        if(gallery.size > 0) {
                            val items = gallery[0].getElementsByClass("item")
                            for(item in items){
                                image = item.getElementsByTag("img").attr("src").toString()
                                break
                            }
                        }
                        if(desc.size > 0){
                            val productInfos = desc[0].getElementsByClass("product-info")
                            for(productInfo in productInfos){
                                val modelElement = productInfo.getElementsByClass("entry-title")
                                if(modelElement.size > 0){
                                    model = modelElement[0].text()
                                }
                                val titleElement = productInfo.getElementsByClass("short-desc")
                                if(titleElement.size > 0){
                                    val titleEl = titleElement[0].getElementsByTag("strong")
                                    if(titleEl.size > 0){
                                        title = titleEl[0].text()
                                    }
                                }

                                val infoParentElements = productInfo.getElementsByClass("short-desc")
                                if(infoParentElements.size > 0){
                                    val infoElements = infoParentElements[0].getElementsByTag("li")
                                    for (item in infoElements) {
                                        infos.add(InfoDetail(item.text()))
                                    }
                                }
                            }
                        }
                        prod = ProductInfoDetail(image,model,title,infos)
                    }
                    if(prod != null) {
                        productDetail?.invoke(prod!!)
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}