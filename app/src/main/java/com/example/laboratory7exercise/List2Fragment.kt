package com.example.laboratory7exercise

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratory7exercise.databinding.ListRowBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [List2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class List2Fragment : Fragment() {
    lateinit var dataRepo: MyRepository
    private lateinit var adapter: MyAdapter

    inner class MyAdapter(var data: MutableList<DBItem>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
        inner class MyViewHolder(viewBinding: ListRowBinding): RecyclerView.ViewHolder(viewBinding.root){
            val mainName: TextView = viewBinding.lrowMainName
            val latinName: TextView = viewBinding.lrowLatinName
            val img: ImageView = viewBinding.lrowImage
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val viewBinding = ListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MyViewHolder(viewBinding)
        }

        override fun getItemCount(): Int {
             MyRepository.getInstance(requireContext()).getData()?.let {
                return it.size;
            }
            return 0;
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.mainName.text =data[position].mainName
            holder.latinName.text = data[position].latinName
//            holder.itemView.setOnClickListener{
//                Toast.makeText(requireContext(), "You clicked "+(position+1), Toast.LENGTH_SHORT).show()
//            }
            holder.itemView.setOnClickListener {
                openItemDetails(data[position])
                true
            }
            holder.itemView.setOnLongClickListener {
                if(dataRepo.deleteItem(data[position])){
                    data = dataRepo.getData()!!
                    notifyDataSetChanged()
                }
                true
            }

            when (data[position].isFruit){
                false -> holder.img.setImageResource(R.drawable.onion)
                true -> holder.img.setImageResource(R.drawable.apple)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataRepo = MyRepository.getInstance(requireContext())
        adapter = MyAdapter(dataRepo.getData()!!)
        parentFragmentManager.setFragmentResultListener("item_added", this) {
                requestKey, _ ->
            adapter.data = dataRepo.getData()!!
            adapter.notifyDataSetChanged()
        }
    }

    private fun openItemDetails(data: DBItem){
        val intent = Intent(requireContext(), ItemDetails::class.java)
        intent.putExtra("id", data.id);
        intent.putExtra("main_name", data.mainName)
        intent.putExtra("latin_name", data.latinName)
        intent.putExtra("price", data.price)
        intent.putExtra("quality", data.quality)
        intent.putExtra("is_fruit", data.isFruit)
        startActivity(intent)
    }
    private fun setFloatingButton(){
        requireActivity().findViewById<FloatingActionButton>(R.id.add_item_floating_button).setOnClickListener{
            val intent = Intent(requireContext(), AddItem::class.java)
            startActivity(intent)

        }
    }

    override fun onResume() {
        super.onResume()
        adapter.data = dataRepo.getData()!!
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val dataRepo = DataRepo.getInstance()
//        adapter = MyAdapter(dataRepo.getData())
        val recView = (requireActivity().findViewById<View>(R.id.my_rec_view)) as RecyclerView
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireContext())
        setFloatingButton()
//        recView.layoutManager = RecyclerView.L(requireContext()) as RecyclerView.LayoutManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list2, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment List2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            List2Fragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}