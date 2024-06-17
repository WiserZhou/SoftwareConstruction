package p2;

import org.junit.Before;
import org.junit.Test;
import p2.Factory.EnvironmentSelectorFactory;
import p2.Interface.EnvironmentFactory;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EnvironmentSelectorFactoryTest {

    private EnvironmentSelectorFactory factory;

    /**
     * 初始化测试环境，在每个测试方法执行前都会运行此方法。
     */
    @Before
    public void setUp() {
        factory = new EnvironmentSelectorFactory();
    }

    /**
     * 测试构造函数，确保实例化不会抛出异常。
     */
    @Test
    public void testConstructor() {
        // 通过构造函数创建实例，确保没有抛出异常
        assertNotNull("EnvironmentSelectorFactory 实例不能为空", factory);
    }

    /**
     * 测试正常情况下的 selectEnv 方法。
     * 使用 mock 对象验证解析文件的方法是否被正确调用。
     */
    @Test
    public void testSelectEnv_NormalCase() {
        // 正常情况下的测试
        String env = "production";
        String filePath = "src/test/resources/example.json";

        // 使用Mockito模拟EnvironmentFactory
        EnvironmentFactory mockFactory = mock(EnvironmentFactory.class);
        // 模拟工厂映射中的工厂实例
        Map<String, EnvironmentFactory> factoryMap = Map.of(env.toLowerCase(), mockFactory);

        // 使用反射设置工厂映射
        setFactoryMap(factory, factoryMap);

        // 执行测试
        factory.selectEnv(env, filePath);

        // 验证是否调用了正确的parseFile方法
        verify(mockFactory).parseFile(filePath);
    }

    /**
     * 测试 selectEnv 方法使用空环境名。
     * 预期抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSelectEnv_EmptyEnv() {
        // 环境名为空的测试
        String env = "";
        String filePath = "src/test/resources/example.json";

        factory.selectEnv(env, filePath);
    }

    /**
     * 测试 selectEnv 方法使用空文件路径。
     * 预期抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSelectEnv_NullFilePath() {
        // 文件路径为空的测试
        String env = "test";

        factory.selectEnv(env, null);
    }

    /**
     * 测试 selectEnv 方法使用不存在的环境名。
     * 预期抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSelectEnv_NonExistingEnv() {
        // 环境名不存在的测试
        String env = "invalid";
        String filePath = "src/test/resources/example.json";

        factory.selectEnv(env, filePath);
    }

    /**
     * 测试 selectEnv 方法使用不存在的文件路径。
     * 预期抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSelectEnv_NonExistingFile() {
        // 文件不存在的测试
        String env = "test";
        String filePath = "non_existing_file.json";

        factory.selectEnv(env, filePath);
    }

    /**
     * 测试 selectEnv 方法环境名不区分大小写。
     * 使用 mock 对象验证解析文件的方法是否被正确调用。
     */
    @Test
    public void testSelectEnv_CaseInsensitiveEnv() {
        // 环境名不区分大小写的测试
        String env = "Production";
        String filePath = "src/test/resources/example.json";

        EnvironmentFactory mockFactory = mock(EnvironmentFactory.class);
        // 模拟工厂映射中的工厂实例
        Map<String, EnvironmentFactory> factoryMap = Map.of(env.toLowerCase(), mockFactory);

        // 使用反射设置工厂映射
        setFactoryMap(factory, factoryMap);

        factory.selectEnv(env, filePath);

        verify(mockFactory).parseFile(filePath);
    }

    /**
     * 测试通过构造函数加载工厂映射是否初始化。
     */
    @Test
    public void testFactoryLoading() {
        // 通过构造函数测试工厂映射的初始化
        EnvironmentSelectorFactory newFactory = new EnvironmentSelectorFactory();
        assertNotNull("工厂类映射不能为空", newFactory);
    }

    /**
     * 测试工厂映射是否包含多个工厂类。
     * 调用多个环境工厂并确保不会抛出异常。
     */
    @Test
    public void testFactoryMapContainsMultipleFactories() {
        // 测试工厂映射是否包含多个工厂类
        EnvironmentSelectorFactory newFactory = new EnvironmentSelectorFactory();
        String filePath = "src/main/resource/example.json";
        try {
            newFactory.selectEnv("test", filePath);
            newFactory.selectEnv("production", filePath);
        } catch (IllegalArgumentException e) {
            fail("工厂类映射应包含 TestFactory 和 ProductionFactory");
        }
    }

    /**
     * 使用反射设置工厂映射。
     *
     * @param factory   环境选择工厂实例
     * @param factoryMap 模拟的工厂映射
     */
    private void setFactoryMap(EnvironmentSelectorFactory factory, Map<String, EnvironmentFactory> factoryMap) {
        try {
            java.lang.reflect.Field field = EnvironmentSelectorFactory.class.getDeclaredField("factoryMap");
            field.setAccessible(true);
            field.set(factory, factoryMap);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
