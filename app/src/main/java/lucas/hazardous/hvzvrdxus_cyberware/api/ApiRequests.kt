package lucas.hazardous.hvzvrdxus_cyberware.api

import android.util.Log
import lucas.hazardous.hvzvrdxus_cyberware.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
                    Log.e("ApiRequests", "onFailure: ${t.message}")
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

        fun patchUser(id: Int, user: User) {
            api.patchUser(id, user).enqueue(object : Callback<Int> {
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
                    Log.e("ApiRequests", "onFailure: ${t.message}")
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

        fun addProduct(product: ProductToAdd) {
            api.addProduct(product).enqueue(object : Callback<Int> {
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

        fun patchProduct(id: Int, product: Product) {
            api.patchProduct(id, product).enqueue(object : Callback<Int> {
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

        fun addCartOrder(cartOrder: CartOrder) {
            api.addCartOrder(cartOrder).enqueue(object : Callback<Int> {
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

        fun loadOrderList() {
            api.getOrders().enqueue(object: Callback<List<Order>> {
                override fun onResponse(
                    call: Call<List<Order>>,
                    response: Response<List<Order>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("ApiRequests", "onResponse: ${response.body()}")
                        orderList.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                    Log.e("ApiRequests", "onFailure: ${t.message}")
                }
            })
        }
    }
}