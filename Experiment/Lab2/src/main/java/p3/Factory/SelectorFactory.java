package p3.Factory;

import p3.util.CondimentSelector;
import p3.Interface.Beverage;
import p3.Interface.CondimentDecorator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SelectorFactory 类用于扫描指定目录中的 Beverage 和 Condiment 类并实例化它们，
 * 同时提供计算饮料及其调料总价格的方法。
 * <p>
 * 抽象函数 (AF):
 * 该类表示一个用于扫描指定目录中的饮料和调料类并实例化它们的工厂类，以及提供计算饮料及其调料总价格的方法。
 * <p>
 * 表示不变性 (RI):
 * - 目录路径 CONDIMENT_DIRECTORY_PATH 和 BEVERAGE_DIRECTORY_PATH 必须是有效的字符串，不能为 null 或空。
 * <p>
 * 防止表示暴露 (Safety from Rep Exposure):
 * - 没有内部状态暴露给外部，因此不存在表示暴露的问题。
 */
public class SelectorFactory {
    private static final String CONDIMENT_DIRECTORY_PATH = "src/main/java/p3/Condiment";  // 使用资源路径常量
    private static final String BEVERAGE_DIRECTORY_PATH = "src/main/java/p3/Beverage";  // 使用资源路径常量

    private final Map<String, CondimentDecorator> selectCondimentMap;
    private final Map<String, Beverage> selectBeverageMap;

    /**
     * 构造一个 SelectorFactory 实例。
     * 扫描并加载饮料和调料类。
     */
    public SelectorFactory() {
        selectCondimentMap = new HashMap<>();
        selectBeverageMap = new HashMap<>();
        scanAndLoadClasses(CONDIMENT_DIRECTORY_PATH, selectCondimentMap, CondimentDecorator.class);
        scanAndLoadClasses(BEVERAGE_DIRECTORY_PATH, selectBeverageMap, Beverage.class);
    }

    /**
     * 扫描指定目录并加载类实例到相应的映射中。
     *
     * @param directoryPath 目录路径
     * @param map           存放实例的映射
     * @param expectedClass 期望的类类型
     * @param <T>           类类型
     */
    private <T> void scanAndLoadClasses(String directoryPath, Map<String, T> map, Class<T> expectedClass) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("目录路径无效: " + directoryPath);
        }

        String[] fileNames = directory.list((dir, name) -> name.endsWith(".java"));
        if (fileNames == null || fileNames.length == 0) {
            throw new IllegalArgumentException("指定目录中没有找到 " + expectedClass.getSimpleName() + " 类");
        }

        for (String fileName : fileNames) {
            try {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                String fullClassName = directoryPath.replace("src/main/java/", "").replace("/", ".") + "." + className;
                Class<?> clazz = Class.forName(fullClassName);
                T instance = expectedClass.cast(clazz.getDeclaredConstructor().newInstance());
                map.put(className.toLowerCase(), instance);
            } catch (Exception e) {
                System.err.println("无法实例化 " + expectedClass.getSimpleName() + " 类：" + fileName + "。错误信息：" + e.getMessage());
            }
        }
    }

    /**
     * 计算指定饮料及其调料的总价格。
     *
     * @param beverageName          饮料名称
     * @param condimentSelectorList 调料选择列表
     * @return 饮料及其调料的总价格
     * @throws IllegalArgumentException 如果未找到指定的饮料或调料
     */
    public Double select(String beverageName, List<CondimentSelector> condimentSelectorList) {
        Beverage beverage = selectBeverageMap.get(beverageName.toLowerCase());
        if (beverage == null) {
            throw new IllegalArgumentException("未找到指定的饮料：" + beverageName);
        }

        for (CondimentSelector condimentSelector : condimentSelectorList) {
            CondimentDecorator condimentDecorator = selectCondimentMap.get(condimentSelector.condimentName().toLowerCase());
            if (condimentDecorator == null) {
                throw new IllegalArgumentException("未找到指定的调料：" + condimentSelector.condimentName());
            }
            condimentDecorator.setCondiment(beverage, condimentSelector.num());
            beverage = condimentDecorator;
        }

        return beverage.cost();
    }
}
