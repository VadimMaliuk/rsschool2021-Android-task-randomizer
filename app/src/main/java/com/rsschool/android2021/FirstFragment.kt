package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var fragmentSendDataListener: OnFragmentSendDataListener? = null

    interface OnFragmentSendDataListener {
        fun onSendData(min: Int, max: Int)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentSendDataListener = context as  OnFragmentSendDataListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"



        generateButton?.setOnClickListener {
            val editTextViewMin = view.findViewById<EditText>(R.id.min_value)
            val editTextViewMax = view.findViewById<EditText>(R.id.max_value)
            var min: Int = -1;
            var max: Int = -1;

            //Getting min max values
            if (!(editTextViewMax.text.toString() == "") && !(editTextViewMin.text.toString() == "")) {
                min = editTextViewMin.text.toString().toInt()
                max = editTextViewMax.text.toString().toInt()
            }

            //if input is valid, then do stuff(open fragment and pass data), else show info in toast.
            if(checkValidData(min, max))
            {
                fragmentSendDataListener?.onSendData(min, max);
            }
            else{
                Toast.makeText(context, "Invalid input, dude. Try again!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun checkValidData(min: Int, max: Int) : Boolean{
        var valid = true;
        if((min < 0) || (max < 0)){
            valid = false;
        }
        if(max < min){
            valid = false;
        }
        return valid
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}