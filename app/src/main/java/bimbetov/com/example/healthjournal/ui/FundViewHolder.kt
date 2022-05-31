package bimbetov.com.example.healthjournal.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import bimbetov.com.example.healthjournal.databinding.FundRvItemBinding
import bimbetov.com.example.healthjournal.model.Fund
import com.bumptech.glide.Glide

class FundViewHolder(val binding: FundRvItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun setInfo(fund: Fund, c: Context){

        binding.fundNameTextView.text = fund.getFundName()
        binding.cityTextView.text = fund.getCity()
        binding.needCountTextView.text = fund.getNeedAmount().toString()
        binding.collectedCountTextView.text = fund.getCollectedAmount().toString()
        binding.titleTextView.text = fund.getTitleProblem()
        binding.helpButton.text = fund.getCollectionAvailability().toString()

        Glide
            .with(c)
            .load(fund.getFundImg())
            .centerCrop()
            .into(binding.fundImageView)

        Glide
            .with(c)
            //.load(fund.getNeedsImg())
            .load(fund.getImgList()[0])
            .centerCrop()
            .into(binding.imagesInfo)

        /*binding.helpButton.setOnClickListener {

        }*/
    }


}