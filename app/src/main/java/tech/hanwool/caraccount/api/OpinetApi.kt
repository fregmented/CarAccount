package tech.hanwool.caraccount.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tech.hanwool.caraccount.api.model.AveragePriceAll
import tech.hanwool.caraccount.api.model.AveragePriceInMetropolitan
import tech.hanwool.caraccount.api.model.ProdCode

interface OpinetApi {
    @GET("avgAllPrice.do")
    fun getAvrgPriceAll(): Observable<List<AveragePriceAll>>
    @GET("avgSidoPrice.do")
    fun getAvrgPriceInMetropolitan(@Query("sido") metropolitanCode:String? = null,
                                   @Query("prodcd") prodCode: ProdCode? = null): Observable<List<AveragePriceInMetropolitan>>
    @GET("avgSigunPrice.do")
    fun getAvrgPriceInDistrict(@Query("sido") metropolitanCode: String? = null,
                               @Query("sigun") districtCode: String? = null,
                               @Query("prodcd") prodCode: ProdCode? = null)

}