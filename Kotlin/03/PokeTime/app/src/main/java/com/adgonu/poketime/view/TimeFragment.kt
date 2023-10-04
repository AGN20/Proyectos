package com.adgonu.poketime.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.adgonu.poketime.databinding.FragmentTimeBinding
import com.adgonu.poketime.viewmodel.TimeViewModel

class TimeFragment : Fragment() {

    private lateinit var binding:FragmentTimeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentTimeBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trainerViewModel = ViewModelProvider(this)[TimeViewModel::class.java]

        trainerViewModel.weatherLiveData.observe(viewLifecycleOwner){ imageID ->
            binding.ivWeather.setImageResource(imageID)
        }

        trainerViewModel.timeLiveData.observe(viewLifecycleOwner) { repetition ->
            if (repetition.equals("CHANGE")) {
                binding.tvChange.visibility = View.VISIBLE
            } else {
                binding.tvChange.visibility = View.GONE
            }
            binding.tvTime.text = repetition
        }

    }


}