package lucas.hazardous.hvzvrdxus_cyberware.api

import lucas.hazardous.hvzvrdxus_cyberware.*
import retrofit2.Call
import retrofit2.http.*

interface ServerApi {
    companion object {
        const val BASE_URL = "http://192.168.1.251:8080/api/"
    }

    @GET("users")
    fun getUsers(): Call<List<User>>

    @POST("users")
    fun addUser(@Body user: UserToAdd): Call<Int>

    @PATCH("users/{id}")
    fun patchUser(@Path("id") id: Int, @Body user: User): Call<Int>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Int>

    @GET("products")
    fun getProducts(): Call<List<Product>>

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id: Int): Call<Int>

    @POST("products")
    fun addProduct(@Body product: ProductToAdd): Call<Int>

    @PATCH("products/{id}")
    fun patchProduct(@Path("id") id: Int, @Body product: Product): Call<Int>

    @POST("orders")
    fun addCartOrder(@Body cartOrder: CartOrder): Call<Int>

    @GET("orders")
    fun getOrders(): Call<List<Order>>
}