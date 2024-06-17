package p3;

import org.junit.Test;
import p3.Beverage.DarkRost;
import p3.Beverage.Decaf;
import p3.Beverage.Espresso;
import p3.Beverage.HouseBlend;
import p3.Interface.Beverage;

import static org.junit.Assert.assertEquals;

/**
 * 测试具体饮料类的功能。
 * 测试策略：
 * - 测试每种饮料类的 cost 方法是否返回正确的成本值。
 */
public class BeverageTest {

    /**
     * 测试 DarkRost 饮料的成本。
     * 测试策略：
     * - 调用 cost 方法并检查返回值是否等于 0.99。
     */
    @Test
    public void testDarkRostCost() {
        // 创建 DarkRost 实例
        Beverage darkRost = new DarkRost();

        // 预期成本应该是 0.99
        double expectedCost = 0.99;

        // 实际成本
        double actualCost = darkRost.cost();

        // 断言预期结果和实际结果是否相等
        assertEquals("DarkRost 的成本应该是 0.99", expectedCost, actualCost, 0.0);
    }

    /**
     * 测试 Decaf 饮料的成本。
     * 测试策略：
     * - 调用 cost 方法并检查返回值是否等于 1.05。
     */
    @Test
    public void testDecafCost() {
        // 创建 Decaf 实例
        Beverage decaf = new Decaf();

        // 预期成本应该是 1.05
        double expectedCost = 1.05;

        // 实际成本
        double actualCost = decaf.cost();

        // 断言预期结果和实际结果是否相等
        assertEquals("Decaf 的成本应该是 1.05", expectedCost, actualCost, 0.0);
    }

    /**
     * 测试 Espresso 饮料的成本。
     * 测试策略：
     * - 调用 cost 方法并检查返回值是否等于 1.99。
     */
    @Test
    public void testEspressoCost() {
        // 创建 Espresso 实例
        Beverage espresso = new Espresso();

        // 预期成本应该是 1.99
        double expectedCost = 1.99;

        // 实际成本
        double actualCost = espresso.cost();

        // 断言预期结果和实际结果是否相等
        assertEquals("Espresso 的成本应该是 1.99", expectedCost, actualCost, 0.0);
    }

    /**
     * 测试 HouseBlend 饮料的成本。
     * 测试策略：
     * - 调用 cost 方法并检查返回值是否等于 0.89。
     */
    @Test
    public void testHouseBlendCost() {
        // 创建 HouseBlend 实例
        Beverage houseBlend = new HouseBlend();

        // 预期成本应该是 0.89
        double expectedCost = 0.89;

        // 实际成本
        double actualCost = houseBlend.cost();

        // 断言预期结果和实际结果是否相等
        assertEquals("HouseBlend 的成本应该是 0.89", expectedCost, actualCost, 0.0);
    }
}
