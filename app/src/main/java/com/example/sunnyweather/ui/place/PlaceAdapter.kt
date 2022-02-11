package com.example.sunnyweather.ui.place

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.drawerlayout.R
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnyweather.logic.model.Place

class PlaceAdapter(private val fragment: Fragment, private val placeList: List<Place>):
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val placeName: TextView = view.findViewById()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        
    }
    }
}