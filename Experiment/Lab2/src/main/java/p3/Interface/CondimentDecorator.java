package p3.Interface;

/**
 * CondimentDecorator 是一个抽象类，用于装饰 Beverage。
 * 它持有一个 Beverage 对象，并可以添加额外的调料。
 */
public abstract class CondimentDecorator extends Beverage {
    protected int num;
    protected Beverage beverage;

    /**
     * 设置调料的饮料和数量。
     *
     * @param beverage 被装饰的饮料。
     * @param num      调料的数量。
     */
    public void setCondiment(Beverage beverage, int num) {
        this.beverage = beverage;
        this.num = num;
    }
}
