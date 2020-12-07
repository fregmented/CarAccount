/**
 * Initialize OkHTTP, Retrofit, ApiClient
 */
package tech.hanwool.caraccount.api.opinet

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.hanwool.caraccount.BuildConfig

object OpinetApiClient {
    private val baseUrl = "https://www.opinet.co.kr/api/"
    private val apiCode = BuildConfig.OPINET_API_KEY
    private val retrofit: Retrofit
    private val okHttpClient: OkHttpClient
    val client: OpinetApi

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(Interceptor {
                // Intercept and add new Query for authorize api and set output format to json
                val request: Request = it.request()
                val newReq = request.newBuilder().apply {
                    url(request.url.newBuilder().apply {
                        // Add new Query string to request url
                        addQueryParameter("code", apiCode)
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
            addConverterFactory(EnumConverterFactory())
        }.build()

        client = retrofit.create(OpinetApi::class.java)
    }
}