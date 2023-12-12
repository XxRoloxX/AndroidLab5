package com.example.laboratory7exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Switch
import android.widget.TextView
import com.example.laboratory7exercise.databinding.ActivityItemDetailsBinding

class ItemDetails : AppCompatActivity() {

    private lateinit var binding: ActivityItemDetailsBinding;
    private var itemId =0 ;
    private lateinit var item: DBItem;
    private val dataRepo = MyRepository.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getItemDataFromRepository()
        loadIntent()
        setButtons()
        title = "Item Details"
    }
    private fun getItemDataFromRepository(){
        itemId = intent.getIntExtra("id",0)
        item = dataRepo.findById(itemId)!!
    }

    private fun loadIntent(){
//        val mainName = intent.getStringExtra("main_name")
//        val latinName = intent.getStringExtra("latin_name")
//        val price = intent.getIntExtra("price", 0)
//        val quality = intent.getFloatExtra("quality",0F);
//        val isFruit = intent.getBooleanExtra("is_fruit", false)


        binding.itemDetailsMainName.text = item.mainName
        binding.itemDetailsLatinName.text = item.latinName
        binding.itemDetailsPrice.text = item.price.toString()
        binding.itemDetailsRating.rating = item.quality
        binding.itemDetailsOrigin.text = item.origin.toString()
        if(item.isFruit){
            binding.itemDetailsImage.setImageResource(R.drawable.apple);
        }else{
            binding.itemDetailsImage.setImageResource(R.drawable.onion);
        }
    }

    override fun onResume() {
        super.onResume()
        getItemDataFromRepository()
        loadIntent()
    }

    private fun setButtons(){
        binding.itemDetailsGoBack.setOnClickListener {
            finish()
        }
        binding.detailsModifyButton.setOnClickListener {
            val modifyIntent = Intent(this, ModifyItemActivity::class.java)
            val id = intent.getIntExtra("id", 0);
            Log.v("SERVICE", id.toString())
            modifyIntent.putExtra("id", id);
            startActivity(modifyIntent);
        }
    }
}