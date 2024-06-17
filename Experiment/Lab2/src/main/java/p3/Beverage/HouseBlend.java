package p3.Beverage;

import p3.Interface.Beverage;

/**
 * HouseBlend 是 Beverage 的一个具体实现类，表示一种家用混合咖啡饮料。
 * <p>
 * 抽象函数 (AF):
 * 表示一种名为 HouseBlend 的饮料，其固定价格为 0.89。
 * <p>
 * 表示不变性 (RI):
 * - 价格必须始终为 0.89。
 * <p>
 * 防止表示暴露 (Safety from Rep Exposure):
 * - 没有可变的内部状态，因此不存在表示暴露的问题。
 */
public class HouseBlend extends Beverage {

    @Override
    public double cost() {
        return 0.89;
    }
}
