package bimbetov.com.example.healthjournal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import bimbetov.com.example.healthjournal.R
import bimbetov.com.example.healthjournal.databinding.ActivityFundListBinding
import bimbetov.com.example.healthjournal.model.Fund
import bimbetov.com.example.healthjournal.repository.FundRepository
import bimbetov.com.example.healthjournal.repository.TestRepository
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FundListActivity : AppCompatActivity() {
    lateinit var binding: ActivityFundListBinding
    private val FUND_KEY = "Fund"
    private lateinit var mDatabase: DatabaseReference
    private var fundList = mutableListOf<Fund>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund_list)

        binding = ActivityFundListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mDatabase = Firebase.database.getReference(FUND_KEY)

        getDataFromDB()

        with(binding.idRVFund){
            layoutManager = LinearLayoutManager(context)
            adapter = FundAdapter(context, fundList)
        }
    }

    private fun setDataToDB() {
        var repository : FundRepository = TestRepository()
        var list = repository.getFund()

        for (f in list) {
            mDatabase.child(f.getId().toString()).setValue(f)
        }
    }

    private fun getDataFromDB() {


        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    for (itemSnapshot in snapshot.children){
                        val fund = itemSnapshot.getValue(Fund::class.java)
                        fundList.add(fund!!)
                    }
                    binding.idRVFund.adapter!!.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}