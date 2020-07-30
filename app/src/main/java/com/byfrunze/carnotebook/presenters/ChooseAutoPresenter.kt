package com.byfrunze.carnotebook.presenters

import com.byfrunze.carnotebook.models.Brand
import com.byfrunze.carnotebook.providers.ChooseAutoProvider
import com.byfrunze.carnotebook.views.ChooseAutoView
import moxy.MvpPresenter
import java.util.ArrayList

class ChooseAutoPresenter : MvpPresenter<ChooseAutoView>() {
    fun loadAuto() {
        viewState.loadInfo()
        ChooseAutoProvider(this).loadBrands()
    }

    fun errorLoad(localizedMessage: String?) {
        viewState.errorLoading(localizedMessage)
    }

    fun completeLoading(arrayOfBrands: ArrayList<Brand>) {
        viewState.completeLoading(arrayOfBrands)
    }

}