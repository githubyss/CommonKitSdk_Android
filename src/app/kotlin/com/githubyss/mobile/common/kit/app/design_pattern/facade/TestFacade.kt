package com.githubyss.mobile.common.kit.app.design_pattern.facade

import com.githubyss.mobile.common.kit.app.design_pattern.entity.stock.Fund
import com.githubyss.mobile.common.kit.app.design_pattern.entity.stock.IStock


/**
 * TestFacade
 *
 * 使用场景：
 *
 * 1）设计初期，将不同的层分离，层与层之间建立外观 Facade，这样可以为复杂的子系统创建一个简单的接口，提供给其他层使用，大大降低耦合性。
 *
 * 2）开发阶段，子系统会随着重构演化而变得越来越复杂，设计模式的使用也会产生许多细小的类，给外部调用它们的用户程序带来使用上的困难和复杂，此时增加外观 Facade，提供一个简单的接口，减少子系统与用户程序之间的依赖。
 *
 * 3）维护一个遗留大系统，此时这个系统已经难以维护和扩展，但是必须使用它的功能，此时增加外观 Facade，提供一个简单的接口，让新系统和旧系统的 Facade 对接，通过 Facade 与旧系统交互所有复杂的工作。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/07 11:06:14
 */
fun facade() {
    val fund: IStock = Fund()
    fund.buy()
    fund.sell()
    println()
}