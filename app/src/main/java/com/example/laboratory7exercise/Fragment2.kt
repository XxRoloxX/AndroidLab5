package com.example.laboratory7exercise

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }
    private fun getPreferences(): SharedPreferences {
        return requireActivity().getSharedPreferences("tab1_settings", Context.MODE_PRIVATE)
    }
    private fun loadPreferences(view:View){
        val imageId = getImagePreference()
        getProfileImage(view).setImageResource(imageId)
        setRadioButtons(view)
    }
    private fun getImagePreference():Int{
        return getPreferences().getInt("profile_image", R.drawable.strawberry)
    }

    private fun getProfileImage(view:View): ImageView {
        return view.findViewById<ImageView>(R.id.profile_image)
    }
    private fun setRadioButtons(view:View){
        val button1 = view.findViewById<RadioButton>(R.id.profile_button_1)
        val button2 = view.findViewById<RadioButton>(R.id.profile_button_2)
        val button3 = view.findViewById<RadioButton>(R.id.profile_button_3)
        val choosenImage = getImagePreference()

        button1.setOnClickListener(radioButtonListener)
        button2.setOnClickListener(radioButtonListener)
        button3.setOnClickListener(radioButtonListener)

        if(choosenImage == R.drawable.pineapple) {
            button1.isChecked = true
        }else if(choosenImage == R.drawable.strawberry){
            button2.isChecked = true
        }else{
            button3.isChecked = true
        }

    }
    private fun setSaveButton(view:View) {
        view.findViewById<Button>(R.id.profile_save).setOnClickListener(saveButtonListener)
    }
    private fun getSelectedImage(view:View):Int{
        val button1 = view.findViewById<RadioButton>(R.id.profile_button_1)
        val button2 = view.findViewById<RadioButton>(R.id.profile_button_2)
        val button3 = view.findViewById<RadioButton>(R.id.profile_button_3)
        if(button1.isChecked){
            return R.drawable.pineapple
        }else if(button2.isChecked){
            return R.drawable.strawberry
        }else{
            return R.drawable.watermelon
        }
    }

    private val radioButtonListener = View.OnClickListener {
        when (it.id){
            R.id.profile_button_1 -> {
                getProfileImage(requireView()).setImageResource(R.drawable.pineapple)
            }
            R.id.profile_button_2 -> {
                getProfileImage(requireView()).setImageResource(R.drawable.strawberry)
            }
            R.id.profile_button_3 -> {
                getProfileImage(requireView()).setImageResource(R.drawable.watermelon)
            }
        }
    }
    private val saveButtonListener = View.OnClickListener {
        when (it.id){
            R.id.profile_save -> {
                savePreferences(requireView())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSaveButton(view)
        setRadioButtons(view)
        loadPreferences(view)
    }

    private fun savePreferences(view:View){
        val editor = getPreferences().edit()
        editor.putInt("profile_image", getSelectedImage(view))
        editor.apply()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            Fragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}