package p3.Condiment;

import p3.Interface.Beverage;
import p3.Interface.CondimentDecorator;

/**
 * Soy 是 CondimentDecorator 的一个具体实现类，表示一种豆奶调料。
 * <p>
 * 抽象函数 (AF):
 * 表示一种豆奶调料，可以添加到饮料中，并根据添加的数量增加饮料的成本。
 * <p>
 * 表示不变性 (RI):
 * - `beverage` 必须是一个有效的 Beverage 实例。
 * - `num` 必须是一个非负整数。
 * <p>
 * 防止表示暴露 (Safety from Rep Exposure):
 * - `beverage` 通过构造函数传递，并且没有提供修改其引用的方法。
 * - `num` 的值只能通过类的内部逻辑修改。
 */
public class Soy extends CondimentDecorator {

    @Override
    public double cost() {
        return 0.1 * num + beverage.cost();
    }

}
