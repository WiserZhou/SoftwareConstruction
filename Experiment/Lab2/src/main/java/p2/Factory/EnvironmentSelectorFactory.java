package p2.Factory;

import p2.Interface.EnvironmentFactory;
import p2.util.FactoryLoader;

import java.util.Map;

/**
 * EnvironmentSelectorFactory 是一个用于扫描目录并选择合适的环境工厂并解析文件的实用类。
 * <p>
 * 抽象函数 (AF):
 * 该类表示一个从指定目录中读取环境工厂类并选择合适的工厂来创建环境工厂的实用类。
 * <p>
 * 表示不变性 (RI):
 * - 目录路径 DIRECTORY_PATH 必须是一个有效的字符串，不能为 null 或空。
 * <p>
 * 防止表示暴露 (Safety from Rep Exposure):
 * - 该类没有暴露其内部状态，因此不存在表示暴露的问题。
 */
public class EnvironmentSelectorFactory {

    private static final String DIRECTORY_PATH = "src/main/java/p2/EnvFactory";  // 替换为你要扫描的目录路径
    private final Map<String, EnvironmentFactory> factoryMap;

    /**
     * 构造一个 EnvironmentSelectorFactory 实例。
     * 使用 FactoryLoader 扫描指定目录并加载环境工厂类。
     */
    public EnvironmentSelectorFactory() {
        factoryMap = FactoryLoader.scanAndLoadFactories(DIRECTORY_PATH, "Factory", "p2.EnvFactory", EnvironmentFactory.class);
    }

    /**
     * 选择并解析文件。
     *
     * @param env      环境名称。
     * @param filePath 文件路径。
     * @throws IllegalArgumentException 如果环境名或文件路径为空，或者指定的环境类型不受支持。
     */
    public void selectEnv(String env, String filePath) {
        if (env == null || env.isEmpty() || filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("环境名或文件路径不能为空");
        }

        EnvironmentFactory environmentFactory = factoryMap.get(env.toLowerCase());
        if (environmentFactory == null) {
            throw new IllegalArgumentException("不支持的环境类型：" + env);
        }
        environmentFactory.parseFile(filePath);
    }
}
