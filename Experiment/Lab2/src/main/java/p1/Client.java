package p1;

import p1.Factory.ParserSelectorFactory;

import java.io.IOException;

/**
 * Client 是一个用于测试 ParserSelectorFactory 类的客户端类。
 */
public class Client {

    /**
     * 主方法，用于运行测试。
     *
     * @param args 命令行参数（未使用）。
     * @throws IOException 如果无法读取配置文件。
     */
    public static void main(String[] args) throws IOException {
        // 创建 ParserSelectorFactory 实例
        ParserSelectorFactory parserSelectorFactory = new ParserSelectorFactory();

        // 模拟解析 JSON 文件
        parserSelectorFactory.parseFile("src/main/resource/example.json");

        // 模拟解析 XML 文件
        parserSelectorFactory.parseFile("src/main/resource/example.xml");

        // 模拟解析 YAML 文件
        // parserSelectorFactory.parseFile("src/main/resource/example.yaml");
    }
}
