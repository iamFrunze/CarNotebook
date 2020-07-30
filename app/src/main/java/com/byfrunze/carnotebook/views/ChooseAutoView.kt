package com.byfrunze.carnotebook.views

import com.byfrunze.carnotebook.models.Brand
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ChooseAutoView : MvpView {

    fun loadInfo()
    fun completeLoading(arrayOfBrands: ArrayList<Brand>)
    fun errorLoading(textError: String?)

}