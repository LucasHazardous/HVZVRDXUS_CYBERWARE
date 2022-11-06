package lucas.hazardous.hvzvrdxus_cyberware

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ServerApi {
    companion object {
        const val BASE_URL = "http://192.168.1.251:8080/api/"
    }

    @GET("users")
    fun getUsers(): Call<List<User>>
}

class ApiRequests {
    companion object {
        private val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        private val retrofit = Retrofit.Builder()
            .baseUrl(ServerApi.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val api: ServerApi = retrofit.create(ServerApi::class.java)

        fun getData() {
            api.getUsers().enqueue(object: Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                        userList = response.body()!!
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.d("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }
    }
}