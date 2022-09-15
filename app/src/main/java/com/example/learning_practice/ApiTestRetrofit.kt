package com.example.learning_practice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_practice.databinding.ActivityApiTestRetrofitBinding
import com.example.learning_practice.databinding.TestApiViewItemBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class Valuees(
    val product: String,
    val init: String,
    val dataseries: List<Dataseris>
)

data class Dataseris(
    val timepoint: Int,
    val cloudcover: Int,
    val seeing: Int,
    val transparency: Int,
    val lifted_index: Int,
    val rh2m: Int,
    val temp2m: Int,
    val wind10m: Wind10m,
    val prec_type: String
)

data class Wind10m(
    val direction: String,
    val speed: Int
)

class ApiTestRetrofit : AppCompatActivity() {

    lateinit var binding: ActivityApiTestRetrofitBinding
    var value = "No Data "
    val dat = MutableLiveData<Valuees>()

    interface ApiService {
        @GET("astro.php")
        fun getProperties(
            @Query("lon") lon: String,
            @Query("lat") lat: String,
            @Query("ac") ac: String,
            @Query("unit") unit: String,
            @Query("output") output: String,
            @Query("tzshift") tzshift: String
        ):
                Call<String>
    }

    object Api {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val retrifit: ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_api_test_retrofit)

        binding.testRecycle.layoutManager = LinearLayoutManager(this)

        Api.retrifit.getProperties(
            lon = "113.2",
            lat = "23.1",
            ac = "0",
            unit = "metric",
            output = "json",
            tzshift = "0"
        ).enqueue(
            object : Callback<String> {
                override fun onResponse(
                    call: Call<String>?,
                    response: Response<String>?
                ) {
                    if (response != null) {
                        value = response.body()
                        val test = Gson().fromJson(response.body(), Valuees::class.java)
                        val adapter = TestAdapter()
                        adapter.submitList(test.dataseries)
                        binding.testRecycle.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<String>?, t: Throwable?) {
                    if (t != null) {
                        Log.i("error", "${t.message}")
                        value = "failure " + t.message
                    }
                }

            }
        )
        binding.setLifecycleOwner(this)
    }
}

const val BASE_URL = "https://www.7timer.info/bin/"

class TestAdapter :
    androidx.recyclerview.widget.ListAdapter<Dataseris, TestAdapter.ViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Dataseris>() {
        override fun areItemsTheSame(oldItem: Dataseris, newItem: Dataseris): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Dataseris, newItem: Dataseris): Boolean {
            return oldItem.cloudcover == newItem.cloudcover && oldItem.lifted_index == newItem.lifted_index && oldItem.transparency == newItem.transparency && oldItem.temp2m == newItem.temp2m
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TestApiViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private var binding: TestApiViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataserisa: Dataseris) {
            binding.item = dataserisa
        }
    }
}
