package p2.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * FactoryLoader 是一个实用类，用于加载工厂类。
 * <p>
 * 抽象函数 (AF):
 * 该类用于加载工厂类并返回一个包含工厂实例的映射，键为工厂类名，值为工厂实例。
 * <p>
 * 表示不变性 (RI):
 * - 无特定的不变性要求。
 * <p>
 * 防止表示暴露 (Safety from Rep Exposure):
 * - 没有内部状态，因此不存在表示暴露的问题。
 */
public class FactoryLoader {

    /**
     * 扫描指定目录并加载工厂类。
     *
     * @param directoryPath    要扫描的目录路径。
     * @param suffix           工厂类文件名后缀。
     * @param packageName      工厂类所在包名。
     * @param factoryClassType 工厂类的类型。
     * @param <T>              工厂类的类型。
     * @return 一个包含工厂实例的映射，键为工厂类名，值为工厂实例。
     * @throws IllegalArgumentException 如果目录路径无效或无法实例化工厂类。
     */
    public static <T> Map<String, T> scanAndLoadFactories(String directoryPath, String suffix, String packageName, Class<T> factoryClassType) {
        Map<String, T> factoryMap = new HashMap<>();
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("目录路径无效: " + directoryPath);
        }

        String[] fileNames = directory.list((dir, name) -> name.endsWith(suffix + ".java"));
        if (fileNames == null || fileNames.length == 0) {
            throw new IllegalArgumentException("指定目录中没有找到工厂类");
        }

        for (String fileName : fileNames) {
            try {
                String className = fileName.substring(0, fileName.indexOf(suffix));
                String factoryClassName = packageName + "." + className + suffix;
                Class<?> factoryClass = Class.forName(factoryClassName);
                T factoryInstance = factoryClassType.cast(factoryClass.getDeclaredConstructor().newInstance());
                factoryMap.put(className.toLowerCase(), factoryInstance);
            } catch (Exception e) {
                throw new IllegalArgumentException("无法实例化工厂类：" + fileName + "。错误信息：" + e.getMessage(), e);
            }
        }
        return factoryMap;
    }
}
