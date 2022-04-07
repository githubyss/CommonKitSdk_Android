package com.githubyss.mobile.common.kit.app.design_pattern.entity.stock


class Fund : IStock {
    private val stock1: IStock = Stock1()
    private val stock2: IStock = Stock2()
    private val stock3: IStock = Stock3()
    private val nationalDebt1: IStock = NationalDebt1()
    private val realty1: IStock = Realty1()

    override fun buy() {
        stock1.buy()
        stock2.buy()
        stock3.buy()
        nationalDebt1.buy()
        realty1.buy()
    }

    override fun sell() {
        stock1.sell()
        stock2.sell()
        stock3.sell()
        nationalDebt1.sell()
        realty1.sell()
    }
}