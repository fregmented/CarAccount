package tech.hanwool.caraccount.api.opinet

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import tech.hanwool.caraccount.api.opinet.model.*

interface OpinetApi {
    /**
     * 전국 평균 가격들을 불러옵니다.
     */
    @GET("avgAllPrice.do")
    fun getAvrgPriceAll(): Observable<OpinetResponse<List<AveragePriceAll>>>

    /**
     * 지정된 시도 내의 지정된 유종의 평균 가격을 불러옵니다.
     * @param metropolitanCode 시도 코드
     * @param fuelTypeCode 유종
     */
    @GET("avgSidoPrice.do")
    fun getAvrgPriceInMetropolitan(@Query("sido") metropolitanCode:String? = null,
                                   @Query("prodcd") fuelTypeCode: FuelTypeCode
    ): Single<OpinetResponse<AveragePriceInMetropolitan>>

    /**
     * 지정된 시군구 내의 지정된 유종의 평균 가격을 불러옵니다.
     * @param metropolitanCode 시도 코드
     * @param districtCode 시군구 코드
     * @param fuelTypeCode 유종
     */
    @GET("avgSigunPrice.do")
    fun getAvrgPriceInDistrict(@Query("sido") metropolitanCode: String? = null,
                               @Query("sigun") districtCode: String? = null,
                               @Query("prodcd") fuelTypeCode: FuelTypeCode? = null): Single<OpinetResponse<AveragePriceInDistrict>>

    /**
     * 지정된 지역의 지정된 유종 최저가격 주유소 목록을 불러옵니다.
     * @param fuelType 유종
     * @param areaCode 시도 코드 또는 시군구 코드 null일경우 전국
     * @param count 가져올 갯수
     */
    @GET("lowTop10.do")
    fun getLowestInDistrict(@Query("prodcd") fuelType: FuelTypeCode,
                            @Query("area") areaCode: String?,
                            @Query("cnt") count: Int = 10): Observable<OpinetResponse<List<StationInfo>>>

    /**
     * 지정된 죄표 주변의 지정된 유종을 판매하는 주유소 목록을 불러옵니다.
     * @param fuelType 유종
     * @param katecX KATEC X 죄표
     * @param katecY KATEC Y 좌표
     * @param radius 반경 m단위
     * @param sort 소팅 방법
     */
    @GET("aroundAll.do")
    fun getAroundStations(@Query("prodcd") fuelType: FuelTypeCode,
                          @Query("x") katecX: Float,
                          @Query("y") katecY: Float,
                          @Query("radius") radius: Int = 1000,
                          @Query("sort") sort: AroundStationsSort
    ): Observable<OpinetResponse<List<StationInfo>>>

    @GET("detailById.do")
    fun getStationDetail(@Query("id") stationCode: String): Single<OpinetResponse<StationDetail>>
}