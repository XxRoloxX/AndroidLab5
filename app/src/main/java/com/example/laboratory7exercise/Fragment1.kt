package com.example.laboratory7exercise

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private fun getPreferences():SharedPreferences{
        return requireActivity().getSharedPreferences("tab1_settings", Context.MODE_PRIVATE)
    }
    private fun loadPreferences(view:View){
        val data = getPreferences()
        var name = data.getString("name", "")
        val surname = data.getString("surname", "")
        getNameInput(view).setText(name)
        getSurnameInput(view).setText(surname)
    }

    private fun getNameInput(view:View): TextInputEditText{
        return view.findViewById<View>(R.id.swipe_tab1_name_input) as TextInputEditText
    }
    private fun getSurnameInput(view:View): TextInputEditText {
        return view.findViewById<View>(R.id.swipe_tab_1_surname_input) as TextInputEditText
    }


    private fun savePreferences(view:View){
        val nameInput = getNameInput(view)
        val surnameInput = getSurnameInput(view)
        val editor = getPreferences().edit()
        editor.putString("name", nameInput.text.toString())
        editor.putString("surname", surnameInput.text.toString())
        editor.apply()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPreferences(view)
        setButton(view)

    }
    private fun setButton(view:View){
        val button = view.findViewById<View>(R.id.swipe_tab_1_save) as Button
        button.setOnClickListener {
            savePreferences(view)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            Fragment1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}