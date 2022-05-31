package bimbetov.com.example.healthjournal.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bimbetov.com.example.healthjournal.databinding.FundRvItemBinding
import bimbetov.com.example.healthjournal.model.Fund

class FundAdapter(
    private val c: Context,
    private val item: List<Fund>
) : RecyclerView.Adapter<FundViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FundViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = FundRvItemBinding.inflate(inflater, parent,false)

        return FundViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FundViewHolder, position: Int) {
        holder.setInfo(item[position], c)

        holder.binding.root.setOnClickListener {
            val intent = Intent(c, FundInfoActivity::class.java)

            intent.putExtra("COLLECTION_AVAILABILITY", item[position].getCollectionAvailability())

            intent.putExtra("ID", item[position].getId())
            intent.putExtra("FUND_NAME", item[position].getFundName())
            intent.putExtra("FUND_IMG", item[position].getFundImg())
            intent.putExtra("CITY", item[position].getCity())
            intent.putExtra("IMAGE_INFO", item[position].imageList.toTypedArray())
            //intent.putExtra("IMAGE_INFO", item[position].getNeedsImg())
            intent.putExtra("NEEDED_AMOUNT", item[position].getNeedAmount())
            intent.putExtra("COLLECTED_AMOUNT", item[position].getCollectedAmount())
            intent.putExtra("TITLE_PROBLEM", item[position].getTitleProblem())
            intent.putExtra("DESCRIPTION_PROBLEM", item[position].getDescriptionProblem())

            c.startActivity(intent)
        }
    }

    override fun getItemCount() = item.size

}