package com.robertrussell.miguel.mobilepracticaltest.domain.repository

import android.content.res.AssetManager
import android.util.Log
import com.robertrussell.miguel.mobilepracticaltest.domain.model.CarInformation
import com.robertrussell.miguel.mobilepracticaltest.domain.model.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class CarInformationRepository(private val assetManager: AssetManager) {
    private var jsonString: String? = null

    suspend fun getAllCarInfo(): Flow<Response<MutableList<CarInformation>>> = flow {
        delay(1000)

        try {
            emit(Response.Loading)

            val response = readJSONFromAssets()
            val array = jsonToArray(response)
            val cars = mutableListOf<CarInformation>()

            for ((index, item) in array.withIndex()) {
                val jsonObject = JSONObject(item)
                cars.add(
                    CarInformation(
                        id = index.toLong(),
                        model = jsonObject.get("model").toString(),
                        maker = jsonObject.get("make").toString(),
                        marketPrice = jsonObject.get("marketPrice").toString().toDouble(),
                        customerPrice = jsonObject.get("customerPrice").toString().toDouble(),
                        rating = jsonObject.get("rating").toString().toInt(),
                        cons = jsonToArray(jsonObject.get("consList").toString()).toList(),
                        pros = jsonToArray(jsonObject.get("prosList").toString()).toList()
                    )
                )
            }
            emit(Response.Success(data = cars))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    private fun readJSONFromAssets(): String? {
        try {
            val stream = assetManager.open("car_list.json")
            jsonString = stream.bufferedReader().use {
                it.readText()
            }
            stream.close()

        } catch (e: IOException) {
            Log.e("IOException", "readJSONFromAssets Error: $e")
        }
        return jsonString
    }

    private fun jsonToArray(jsonString: String?): Array<String> {
        val jsonArray = JSONArray(jsonString)
        val array = Array(jsonArray.length()) { index ->
            jsonArray.getString(index)
        }
        return array
    }
}
