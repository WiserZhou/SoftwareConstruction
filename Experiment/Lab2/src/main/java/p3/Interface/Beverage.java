package p3.Interface;

/**
 * Beverage 是一个抽象类，定义了饮料的基本功能。
 * 子类需要实现具体的计算成本的方法。
 */
public abstract class Beverage {

    /**
     * 计算饮料的成本。
     * 每个子类会实现此方法以返回具体饮料的成本。
     *
     * @return 饮料的成本。
     */
    public abstract double cost();
}
