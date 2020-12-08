package tech.hanwool.caraccount.api.navermap

/**
 * Initialize OkHTTP, Retrofit, ApiClient
 */

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.hanwool.caraccount.BuildConfig

object NaverMapApiClient {
    private val baseUrl = "https://naveropenapi.apigw.ntruss.com/"
    private val apiId = BuildConfig.NAVER_MAP_API_ID
    private val apiKey = BuildConfig.NAVER_MAP_API_SECRET
    private val retrofit: Retrofit
    private val okHttpClient: OkHttpClient
    val client: NaverMapApi

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        okHttpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(Interceptor {
                // Intercept and add new Query for authorize api and set output format to json
                val request: Request = it.request()
                val newReq = request.newBuilder().apply {
                    url(request.url.newBuilder().apply {
                        // Add new Query string to request url
                        addHeader("X-NCP-APIGW-API-KEY-ID", apiId)
                        addHeader("X-NCP-APIGW-API-KEY", apiKey)
                        addHeader("Accept",  "application/json")
                        addQueryParameter("out", "json")
                    }.build())
                }.build()
                return@Interceptor it.proceed(newReq)
            })
        }.build()



        retrofit = Retrofit.Builder().apply {
            baseUrl(baseUrl)
            client(okHttpClient)
            addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
        }.build()

        client = retrofit.create(NaverMapApi::class.java)
    }
}