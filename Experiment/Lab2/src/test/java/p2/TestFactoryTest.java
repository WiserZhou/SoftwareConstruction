package p2;

import org.junit.Test;
import p2.Interface.EnvironmentFactory;
import p2.util.FactoryLoader;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * TestFactoryTest 的单元测试类。
 * 测试策略：
 * - 测试 scanAndLoadFactories 方法在不同情况下的表现，包括正常加载工厂类和处理各种异常情况。
 */
public class TestFactoryTest {

    /**
     * 测试策略：
     * 测试 scanAndLoadFactories 方法在正常情况下是否能够正确加载工厂类。
     * <p>
     * 测试点：
     * 1. 工厂类映射不为空。
     * 2. 工厂类映射包含 TestFactory。
     * 3. 加载的类应为 EnvironmentFactory 的子类。
     */
    @Test
    public void testFactoryLoading_NormalCase() {
        String directoryPath = "src/main/java/p2/EnvFactory";
        String suffix = "Factory";
        String packageName = "p2.EnvFactory";

        Map<String, EnvironmentFactory> factories = FactoryLoader.scanAndLoadFactories(directoryPath, suffix, packageName, EnvironmentFactory.class);

        assertNotNull("工厂类映射不能为空", factories);
        assertFalse("工厂类映射不能为空", factories.isEmpty());
        assertTrue("工厂类映射应包含 TestFactory", factories.containsKey("test"));
        assertNotNull("加载的类应为 EnvironmentFactory 的子类", factories.get("test"));
    }

    /**
     * 测试策略：
     * 测试 scanAndLoadFactories 方法在目录路径不存在的情况下是否抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFactoryLoading_NonExistingDirectory() {
        String directoryPath = "non_existing_directory";
        String suffix = "Factory";
        String packageName = "p2.EnvFactory";

        FactoryLoader.scanAndLoadFactories(directoryPath, suffix, packageName, EnvironmentFactory.class);
    }

    /**
     * 测试策略：
     * 测试 scanAndLoadFactories 方法在目录路径为文件的情况下是否抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFactoryLoading_FileAsDirectory() {
        String directoryPath = "src/main/java/p2/EnvFactory/TestFactory.java";
        String suffix = "Factory";
        String packageName = "p2.EnvFactory";

        FactoryLoader.scanAndLoadFactories(directoryPath, suffix, packageName, EnvironmentFactory.class);
    }

    /**
     * 测试策略：
     * 测试 scanAndLoadFactories 方法在无法实例化工厂类的情况下是否抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFactoryLoading_FailedInstantiation() {
        String directoryPath = "src/main/java/p2/EnvFactory";
        String suffix = "InvalidFactory";
        String packageName = "p2.EnvFactory";

        FactoryLoader.scanAndLoadFactories(directoryPath, suffix, packageName, EnvironmentFactory.class);
    }

    /**
     * 测试策略：
     * 测试 scanAndLoadFactories 方法在工厂类不是指定类型的情况下是否抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFactoryLoading_WrongType() {
        String directoryPath = "src/main/java/p2/EnvFactory";
        String suffix = "Factory";
        String packageName = "p2.EnvFactory";

        FactoryLoader.scanAndLoadFactories(directoryPath, suffix, packageName, String.class);
    }
}
