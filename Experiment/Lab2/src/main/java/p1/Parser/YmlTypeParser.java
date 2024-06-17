package p1.Parser;

import org.yaml.snakeyaml.Yaml;
import p1.Interface.Parser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * YmlTypeParser 是 Parser 的一个具体实现类，用于解析 YAML 文件。
 * <p>
 * 抽象函数 (AF):
 * 表示一个可以解析 YAML 文件并打印其内容的解析器。
 * <p>
 * 表示不变性 (RI):
 * - filePath 必须是一个有效的字符串，不能为 null 或空。
 * - 解析的文件必须是有效的 YAML 文件。
 * <p>
 * 防止表示暴露 (Safety from Rep Exposure):
 * - 没有内部状态，因此不存在表示暴露的问题。
 */
public class YmlTypeParser extends Parser {

    @Override
    public void parse(String filePath) {
        validateFilePath(filePath);
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Yaml yaml = new Yaml();
            Object data = yaml.load(inputStream);
            System.out.println("解析 YAML 文件：" + filePath);
            printYamlData(data, 0);
        } catch (Exception e) {
            handleParsingError(e);
        }
    }

    /**
     * 打印 YAML 数据。
     *
     * @param data  要打印的数据。
     * @param level 缩进级别。
     */
    private void printYamlData(Object data, int level) {
        if (data instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                printIndent(level);
                System.out.println(entry.getKey() + ":");
                printYamlData(entry.getValue(), level + 1);
            }
        } else if (data instanceof Iterable<?> iterable) {
            for (Object element : iterable) {
                printIndent(level);
                System.out.println("- ");
                printYamlData(element, level + 1);
            }
        } else {
            printIndent(level);
            System.out.println(data);
        }
    }
}
