package p1.Factory;

import p1.Interface.Parser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * ParserSelectorFactory 是一个用于扫描目录并选择合适的解析器工厂并解析文件的实用类。
 * <p>
 * 抽象函数 (AF):
 * 表示一个从指定目录中读取解析器类并选择合适的工厂来创建解析器的类。
 * <p>
 * 表示不变性 (RI):
 * - 目录路径 DIRECTORY_PATH 必须是一个有效的字符串，不能为 null 或空。
 * <p>
 * 防止表示暴露 (Safety from Rep Exposure):
 * - 没有内部状态，因此不存在表示暴露的问题。
 */
public class ParserSelectorFactory {

    private static final String DIRECTORY_PATH = "src/main/java/p1/Parser";  // 替换为你要扫描的目录路径
    private final Map<String, Parser> parserFactoryMap;

    /**
     * 构造一个 ParserSelectorFactory 实例。
     *
     * @spec.effects: 初始化解析器工厂映射，从指定目录中扫描解析器类。
     * 如果无法读取目录或无法实例化工厂类，则输出错误信息。
     */
    public ParserSelectorFactory() {
        parserFactoryMap = new HashMap<>();
        scanAndLoadParsers();
    }

    /**
     * 解析文件。
     *
     * @param filePath 文件路径。
     * @throws IllegalArgumentException 如果文件类型不受支持。
     * @spec.requires: filePath 不是 null 或空字符串。
     * @spec.effects: 解析给定文件路径的文件，使用相应的解析器工厂创建解析器进行解析。
     * 如果文件类型不受支持，则抛出 IllegalArgumentException 异常。
     */
    public void parseFile(String filePath) {
        String fileType = getFileType(filePath);
        Parser parser = parserFactoryMap.get(fileType);
        if (parser == null) {
            throw new IllegalArgumentException("不支持的文件类型：" + fileType);
        }
        parser.parse(filePath);
    }

    /**
     * 从文件路径中提取文件类型（扩展名）。
     *
     * @param filePath 文件路径。
     * @return 文件类型（扩展名）或空字符串（如果没有扩展名）。
     */
    private static String getFileType(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex != -1 && dotIndex < filePath.length() - 1) {
            return filePath.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }


    /**
     * 扫描指定目录并加载解析器类。
     * <p>
     * 此方法扫描指定目录中的文件，并尝试加载以 "TypeParser.java" 结尾的文件作为解析器类。
     * 加载成功后，将实例化解析器类并将其存储在解析器工厂映射中。
     * 如果无法加载解析器类或实例化类的过程中出现错误，则输出错误信息。
     *
     * @throws IllegalArgumentException 如果目录路径无效或找不到解析器类文件。
     * @spec.modifies: parserFactoryMap。
     * @spec.effects: 扫描指定目录中的文件，加载解析器类，并将实例化的解析器类存储在 parserFactoryMap 中。
     */
    private void scanAndLoadParsers() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("目录路径无效: " + DIRECTORY_PATH);
        }

        String[] fileNames = directory.list((dir, name) -> name.endsWith("TypeParser.java"));
        if (fileNames == null || fileNames.length == 0) {
            throw new IllegalArgumentException("指定目录中没有找到解析器类");
        }

        for (String fileName : fileNames) {
            try {
                String className = fileName.substring(0, fileName.indexOf("TypeParser"));
                String parserClassName = "p1.Parser." + className + "TypeParser";
                Class<?> parserClass = Class.forName(parserClassName);
                Parser parserInstance = (Parser) parserClass.getDeclaredConstructor().newInstance();
                parserFactoryMap.put(className.toLowerCase(), parserInstance);
            } catch (Exception e) {
                System.err.println("无法实例化解析器类：" + fileName + "。错误信息：" + e.getMessage());
            }
        }
    }

}
