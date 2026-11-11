package com.example.animalloverapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Method 1: navigate by destination ID
        val btnCat = view.findViewById<ImageButton>(R.id.BtnCat)
        val oclCat = View.OnClickListener {
            Navigation.findNavController(view).navigate(R.id.DestCat)
        }
        btnCat.setOnClickListener(oclCat)

        // Method 2: navigate by action
        val btnDog = view.findViewById<ImageButton>(R.id.BtnDog)
        val oclDog = View.OnClickListener {
            Navigation.findNavController(view).navigate(R.id.NextToDog)
        }
        btnDog.setOnClickListener(oclDog)
    }
}