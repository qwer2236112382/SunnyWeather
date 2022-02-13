package com.example.sunnyweather.ui.place

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnyweather.logic.model.Place
import com.example.sunnyweather.ui.weather.WeatherActivity
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.coroutines.internal.artificialFrame

class PlaceAdapter(private val fragment: PlaceFragment, private val placeList: List<Place>):
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val placeName: TextView = view.findViewById(com.example.sunnyweather.R.id.placeName)
            val placeAddress: TextView = view.findViewById(com.example.sunnyweather.R.id.placeAddress)
            val placeLng :TextView = view.findViewById(com.example.sunnyweather.R.id.placeLng)
            val placeLat : TextView =view.findViewById(com.example.sunnyweather.R.id.placeLat)
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.sunnyweather.R.layout.place_item,parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val place = placeList[position]
            val activity = fragment.activity
            if (activity is WeatherActivity) {
                activity.drawerLayout.closeDrawers()
                activity.viewModel.locationLng = place.location.lng
                activity.viewModel.locationLat = place.location.lat
                activity.viewModel.placeName = place.name
                activity.refreshWeather()
            } else {
                val intent = Intent(parent.context, WeatherActivity::class.java).apply {
                    putExtra("location_lng", place.location.lng)
                    putExtra("location_lat", place.location.lat)
                    putExtra("place_name", place.name)
                }
                fragment.startActivity(intent)
                fragment.activity?.finish()
            }
            fragment.viewModel.savePlace(place)
            Log.d("Activity","${fragment.viewModel.getSavedPlace()} is saved")
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.name
        holder.placeAddress.text = place.address
        holder.placeLng.text = "lon: " + place.location.lng
        holder.placeLat.text = "lat: " + place.location.lat
    }

    override fun getItemCount() = placeList.size

    }
