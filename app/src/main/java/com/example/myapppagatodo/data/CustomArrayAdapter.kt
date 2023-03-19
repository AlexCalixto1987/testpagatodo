package com.example.myapppagatodo.data

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

class CustomArrayAdapter<String>(context: Context, resuorce: Int, objets: List<String>) : ArrayAdapter<String>(context, resuorce, objets){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        if (position % 4 == 0)
            view.setBackgroundColor(Color.LTGRAY)
        else
            view.setBackgroundColor(Color.WHITE)
        return view
    }

}