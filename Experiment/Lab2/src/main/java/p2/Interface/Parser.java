package p2.Interface;

import java.io.File;

/**
 * Parser 是一个抽象类，定义了解析器的基本功能。
 * 子类需要实现具体的解析方法。
 */
public abstract class Parser {

    /**
     * 解析给定文件路径所指示的文件。
     *
     * @param filePath 文件路径，表示需要解析的文件，非空。
     * @throws IllegalArgumentException 如果文件路径为空或指定的文件不存在。
     */
    public abstract void parse(String filePath);

    /**
     * 检查文件路径是否有效。
     *
     * @param filePath 文件路径，表示需要解析的文件，非空。
     * @throws IllegalArgumentException 如果文件路径为空或指定的文件不存在。
     */
    protected void validateFilePath(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("文件路径不能为空");
        }

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("无效的文件路径: " + filePath);
        }
    }

    /**
     * 打印缩进。
     *
     * @param level 缩进级别。
     */
    protected void printIndent(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
    }

    /**
     * 处理解析错误。
     *
     * @param e 解析过程中发生的异常。
     */
    protected void handleParsingError(Exception e) {
        System.out.println("解析失败！错误信息：" + e.getMessage());
    }
}
