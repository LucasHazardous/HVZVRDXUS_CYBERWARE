package lucas.hazardous.hvzvrdxus_cyberware

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ServerApi {
    companion object {
        const val BASE_URL = "http://192.168.1.251:8080/api/"
    }

    @GET("users")
    fun getUsers(): Call<List<User>>

    @POST("users")
    fun addUser(@Body user: UserToAdd): Call<Int>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Int>

    @GET("products")
    fun getProducts(): Call<List<Product>>

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id: Int): Call<Int>
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

        fun loadUserList() {
            api.getUsers().enqueue(object: Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                        userList.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.d("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun addUser(user: UserToAdd) {
            api.addUser(user).enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun deleteUser(id: Int) {
            api.deleteUser(id).enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun loadProductList() {
            api.getProducts().enqueue(object: Callback<List<Product>> {
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                        productList.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    Log.d("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }

        fun deleteProduct(id: Int) {
            api.deleteProduct(id).enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }
    }
}