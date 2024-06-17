package p2.Interface;

import p2.util.FactoryLoader;

import java.util.Map;

/**
 * EnvironmentFactory 是一个用于扫描目录并选择合适的解析器工厂并解析文件的抽象类。
 */
public abstract class EnvironmentFactory {

    private static final String DIRECTORY_PATH = "src/main/java/p2/Parser";  // 替换为你要扫描的目录路径
    private final Map<String, Parser> parserFactoryMap;

    /**
     * 构造一个 EnvironmentFactory 实例。
     * 使用 FactoryLoader 扫描指定目录并加载解析器工厂类。
     */
    public EnvironmentFactory() {
        parserFactoryMap = FactoryLoader.scanAndLoadFactories(DIRECTORY_PATH, "TypeParser", "p2.Parser", Parser.class);
    }

    /**
     * 解析文件。
     *
     * @param filePath 文件路径。
     * @throws IllegalArgumentException 如果文件路径为空或文件类型不受支持。
     */
    public void parseFile(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("文件路径不能为空");
        }

        String fileType = getFileType(filePath);
        if (judgeType(fileType)) {
            Parser parser = parserFactoryMap.get(fileType);
            if (parser == null) {
                throw new IllegalArgumentException("不支持的文件类型：" + fileType);
            }
            parser.parse(filePath);
        } else {
            System.out.println("此环境无法解析此类型文件！");
        }
    }

    /**
     * 判断给定文件类型是否能被当前环境解析。
     *
     * @param fileType 文件类型。
     * @return 如果能解析则返回 true，否则返回 false。
     */
    protected abstract Boolean judgeType(String fileType);

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
}
