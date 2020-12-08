package tech.hanwool.caraccount.api.navermap

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tech.hanwool.caraccount.api.navermap.model.Coordinate
import tech.hanwool.caraccount.api.navermap.model.GeoCodingResult
import tech.hanwool.caraccount.api.navermap.model.ReverseGeoCodingResult

interface NaverMapApi {
    @GET("map-reversegeocode/v2/gc?output=json&orders=admcode")
    fun reverseGeoCoding(@Query("coords") coordinate: Coordinate): Observable<ReverseGeoCodingResult>
    @GET("map-geocode/v2/geocode")
    fun geoCoding(@Query("query") query: String,
                  @Query("coordinate") coordinate: String? = null,
                  @Query("filter") filter: String? = null,
                  @Query("page") page: Int = 1,
                  @Query("count") count: Int = 1): Observable<GeoCodingResult>
}