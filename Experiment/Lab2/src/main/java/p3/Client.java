package p3;

import p3.util.CondimentSelector;
import p3.Factory.SelectorFactory;

import java.io.IOException;
import java.util.List;

/**
 * Client 是一个用于测试 SelectorFactory 类的客户端类。
 */
public class Client {

    /**
     * 主方法，用于运行测试。
     *
     * @param args 命令行参数（未使用）。
     * @throws IOException 如果发生 I/O 错误。
     */
    public static void main(String[] args) throws IOException {
        SelectorFactory selectorFactory = new SelectorFactory();

        // 模拟用户选择的饮料和调料
        String beverageName = "Decaf";
        List<CondimentSelector> condimentList = List.of(
                new CondimentSelector("Mocha", 1),
                new CondimentSelector("Soy", 2),
                new CondimentSelector("Whip", 3)
        );

        // 通过 SelectorFactory 选择并计算价格
        Double price = selectorFactory.select(beverageName, condimentList);
        System.out.println(" $" + price);
    }
}
