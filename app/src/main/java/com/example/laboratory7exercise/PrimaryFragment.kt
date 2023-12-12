package com.example.laboratory7exercise

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PrimaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PrimaryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_primary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadPreferences(view)
    }

    private fun getPreferences(): SharedPreferences {
        return requireActivity().getSharedPreferences("tab1_settings", Context.MODE_PRIVATE)
    }
    private fun loadPreferences(view:View){
        val data = getPreferences()
//        var name = data.getString("name", "")
//        val surname = data.getString("surname", "")
        val image = data.getInt("profile_image", R.drawable.pineapple)

//        getNameField(view).text = name
//        getSurnameField(view).text = surname
        getProfileImage(view).setImageResource(image)
    }
//    private fun getNameField(view:View):TextView{
//        return view.findViewById(R.id.primary_fragment_name)
//    }
//    private fun getSurnameField(view:View):TextView{
//        return view.findViewById(R.id.primary_fragment_surname)
//    }
    private fun getProfileImage(view:View):ImageView{
        return view.findViewById(R.id.profile_fragment_image)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PrimaryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PrimaryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}