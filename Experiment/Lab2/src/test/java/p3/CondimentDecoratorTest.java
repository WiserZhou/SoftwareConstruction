package p3;

import org.junit.Test;
import p3.Beverage.DarkRost;
import p3.Beverage.Decaf;
import p3.Beverage.Espresso;
import p3.Beverage.HouseBlend;
import p3.Condiment.Mocha;
import p3.Condiment.Soy;
import p3.Condiment.SteamedMilk;
import p3.Condiment.Whip;
import p3.Interface.Beverage;

import static org.junit.Assert.assertEquals;

/**
 * 测试具体调料类的功能。
 * 测试策略：
 * - 测试每种调料的 cost 方法是否能正确计算加料后的成本。
 */
public class CondimentDecoratorTest {

    private static final double DELTA = 0.0001;

    /**
     * 测试 Mocha 调料的成本。
     * 测试策略：
     * - 调用 setCondiment 方法将 Mocha 添加到 DarkRost 中，检查返回的成本是否正确。
     */
    @Test
    public void testMochaCost() {
        Beverage darkRost = new DarkRost();
        Mocha mocha = new Mocha();
        mocha.setCondiment(darkRost, 2);
        double cost = mocha.cost();
        assertEquals("DarkRost + 2 Mocha 的成本应该是 1.39", 1.39, cost, DELTA);
    }

    /**
     * 测试 Soy 调料的成本。
     * 测试策略：
     * - 调用 setCondiment 方法将 Soy 添加到 Decaf 中，检查返回的成本是否正确。
     */
    @Test
    public void testSoyCost() {
        Beverage decaf = new Decaf();
        Soy soy = new Soy();
        soy.setCondiment(decaf, 3);
        double cost = soy.cost();
        assertEquals("Decaf + 3 Soy 的成本应该是 1.35", 1.35, cost, DELTA);
    }

    /**
     * 测试 SteamedMilk 调料的成本。
     * 测试策略：
     * - 调用 setCondiment 方法将 SteamedMilk 添加到 Espresso 中，检查返回的成本是否正确。
     */
    @Test
    public void testSteamedMilkCost() {
        Beverage espresso = new Espresso();
        SteamedMilk steamedMilk = new SteamedMilk();
        steamedMilk.setCondiment(espresso, 1);
        double cost = steamedMilk.cost();
        assertEquals("Espresso + 1 SteamedMilk 的成本应该是 2.11", 2.11, cost, DELTA);
    }

    /**
     * 测试 Whip 调料的成本。
     * 测试策略：
     * - 调用 setCondiment 方法将 Whip 添加到 HouseBlend 中，检查返回的成本是否正确。
     */
    @Test
    public void testWhipCost() {
        Beverage houseBlend = new HouseBlend();
        Whip whip = new Whip();
        whip.setCondiment(houseBlend, 2);
        double cost = whip.cost();
        assertEquals("HouseBlend + 2 Whip 的成本应该是 1.09", 1.09, cost, DELTA);
    }

    /**
     * 测试多种调料组合的成本。
     * 测试策略：
     * - 将多种调料依次添加到 Espresso 中，检查返回的成本是否正确。
     */
    @Test
    public void testMultipleCondiments() {
        Beverage espresso = new Espresso();
        Mocha mocha = new Mocha();
        mocha.setCondiment(espresso, 1);

        Soy soy = new Soy();
        soy.setCondiment(mocha, 1);

        Whip whip = new Whip();
        whip.setCondiment(soy, 1);

        double cost = whip.cost();
        assertEquals("Espresso + 1 Mocha + 1 Soy + 1 Whip 的成本应该是 2.39", 2.39, cost, DELTA);
    }
}
