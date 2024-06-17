package p2;

import p2.Factory.EnvironmentSelectorFactory;

import java.io.IOException;

/**
 * Client 是一个用于测试 EnvironmentSelectorFactory 类的客户端类。
 */
public class Client {

    /**
     * 主方法，用于运行测试。
     *
     * @param args 命令行参数（未使用）。
     * @throws IOException 如果无法读取配置文件。
     */
    public static void main(String[] args) throws IOException {

        EnvironmentSelectorFactory environmentSelectorFactory = new EnvironmentSelectorFactory();

        // 模拟解析 YAML 文件
        environmentSelectorFactory.selectEnv("test", "src/main/resource/example.yml");

        // 模拟解析 XML 文件
        environmentSelectorFactory.selectEnv("production", "src/main/resource/example.xml");

    }
}
