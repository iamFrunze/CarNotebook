package com.byfrunze.carnotebook.activities

import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.byfrunze.carnotebook.R
import com.byfrunze.carnotebook.adapters.BrandAdapter
import com.byfrunze.carnotebook.models.Brand
import com.byfrunze.carnotebook.presenters.ChooseAutoPresenter
import com.byfrunze.carnotebook.views.ChooseAutoView
import kotlinx.android.synthetic.main.activity_choose_auto.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class ChooseAutoActivity : MvpAppCompatActivity(), ChooseAutoView {

    @InjectPresenter
    lateinit var presenter: ChooseAutoPresenter
    lateinit var brandsAdapter: BrandAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_auto)
        presenter.loadAuto()
        brandsAdapter = BrandAdapter()
        rv_model.adapter = brandsAdapter
        rv_model.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_model.setHasFixedSize(true)
    }

    override fun loadInfo() {
    }

    override fun completeLoading(arrayOfBrands: ArrayList<Brand>) {
        brandsAdapter.setupArrayOfBrands(arrayOfBrands)
    }

    override fun errorLoading(textError: String?) {
    }
}