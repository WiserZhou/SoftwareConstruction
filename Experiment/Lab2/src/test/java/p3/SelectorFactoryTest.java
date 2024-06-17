package p3;

import org.junit.Test;
import p3.Factory.SelectorFactory;
import p3.util.CondimentSelector;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * SelectorFactory 的单元测试类。
 * 测试策略：
 * - 测试类加载的正确性。
 * - 测试饮料及其调料的成本计算。
 * - 测试输入无效饮料或调料名时的异常抛出。
 */
public class SelectorFactoryTest {

    private static final double DELTA = 0.0001;

    /**
     * 测试选择方法。
     * 测试策略：
     * - 测试 SelectorFactory 类中的 select 方法是否能正确计算饮料及其调料的成本。
     */
    @Test
    public void testSelect() {
        SelectorFactory factory = new SelectorFactory();

        // 测试 DarkRost 配 2 份 Mocha
        CondimentSelector mochaSelector = new CondimentSelector("Mocha", 2);
        double cost = factory.select("DarkRost", List.of(mochaSelector));
        assertEquals("DarkRost + 2 Mocha 的成本应该是 1.39", 1.39, cost, DELTA);

        // 测试 Decaf 配 3 份 Soy
        CondimentSelector soySelector = new CondimentSelector("Soy", 3);
        cost = factory.select("Decaf", List.of(soySelector));
        assertEquals("Decaf + 3 Soy 的成本应该是 1.35", 1.35, cost, DELTA);

        // 测试 Espresso 配 1 份 SteamedMilk
        CondimentSelector steamedMilkSelector = new CondimentSelector("SteamedMilk", 1);
        cost = factory.select("Espresso", List.of(steamedMilkSelector));
        assertEquals("Espresso + 1 SteamedMilk 的成本应该是 2.11", 2.11, cost, DELTA);

        // 测试 HouseBlend 配 2 份 Whip
        CondimentSelector whipSelector = new CondimentSelector("Whip", 2);
        cost = factory.select("HouseBlend", List.of(whipSelector));
        assertEquals("HouseBlend + 2 Whip 的成本应该是 1.09", 1.09, cost, DELTA);

        // 测试 Espresso 配多种调料
        CondimentSelector mochaSelector1 = new CondimentSelector("Mocha", 1);
        CondimentSelector soySelector1 = new CondimentSelector("Soy", 1);
        CondimentSelector whipSelector1 = new CondimentSelector("Whip", 1);
        cost = factory.select("Espresso", Arrays.asList(mochaSelector1, soySelector1, whipSelector1));
        assertEquals("Espresso + 1 Mocha + 1 Soy + 1 Whip 的成本应该是 2.39", 2.39, cost, DELTA);
    }

    /**
     * 测试无效饮料名时是否抛出异常。
     * 测试策略：
     * - 测试当输入无效的饮料名时是否抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBeverage() {
        SelectorFactory factory = new SelectorFactory();
        factory.select("InvalidBeverage", List.of(new CondimentSelector("Mocha", 1)));
    }

    /**
     * 测试无效调料名时是否抛出异常。
     * 测试策略：
     * - 测试当输入无效的调料名时是否抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCondiment() {
        SelectorFactory factory = new SelectorFactory();
        factory.select("Espresso", List.of(new CondimentSelector("InvalidCondiment", 1)));
    }

    /**
     * 测试空饮料名时是否抛出异常。
     * 测试策略：
     * - 测试当输入空的饮料名时是否抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyBeverageName() {
        SelectorFactory factory = new SelectorFactory();
        factory.select("", List.of(new CondimentSelector("Mocha", 1)));
    }

    /**
     * 测试空调料名时是否抛出异常。
     * 测试策略：
     * - 测试当输入空的调料名时是否抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyCondimentName() {
        SelectorFactory factory = new SelectorFactory();
        factory.select("Espresso", List.of(new CondimentSelector("", 1)));
    }
}
