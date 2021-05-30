package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null
    var fragmentBackDataListener: OnFragmentBackDataListener? = null
    var randomValue = -1

    interface OnFragmentBackDataListener {
        fun onBackData(random: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentBackDataListener = context as OnFragmentBackDataListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        var min : Int = 0
        var max : Int = 0

        arguments?.let {
            min = it.getInt(MIN_VALUE_KEY)
            max = it.getInt(MAX_VALUE_KEY)
        }

        result?.text = generate(min, max).toString()

        backButton?.setOnClickListener {
            fragmentBackDataListener?.onBackData(randomValue)
        }
    }

    private fun generate(min: Int, max: Int): Int {
        randomValue = rand(min, max);
        return randomValue
    }

    fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }



    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putInt(MIN_VALUE_KEY, min)
            args.putInt(MAX_VALUE_KEY, max)
            fragment.arguments = args

            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}