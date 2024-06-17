package p1.Parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import p1.Interface.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * JsonTypeParser 是 Parser 的一个具体实现类，用于解析 JSON 文件。
 * <p>
 * 抽象函数 (AF):
 * 表示一个可以解析 JSON 文件并打印其内容的解析器。
 * <p>
 * 表示不变性 (RI):
 * - filePath 必须是一个有效的字符串，不能为 null 或空。
 * - 解析的文件必须是有效的 JSON 文件。
 * <p>
 * 防止表示暴露 (Safety from Rep Exposure):
 * - 没有内部状态，因此不存在表示暴露的问题。
 */
public class JsonTypeParser extends Parser {

    @Override
    public void parse(String filePath) {
        validateFilePath(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            System.out.println("解析 JSON 文件：" + filePath);
            System.out.println("键值对:");
            printJsonNode(rootNode, 0);
        } catch (IOException e) {
            handleParsingError(e);
        }
    }

    /**
     * 递归打印 JsonNode 的键值对方法。
     *
     * @param node  要打印的 JsonNode 节点。
     * @param level 缩进级别。
     */
    private void printJsonNode(JsonNode node, int level) {
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                printIndent(level);
                System.out.println(entry.getKey() + ":");
                printJsonNode(entry.getValue(), level + 1);
            }
        } else if (node.isArray()) {
            for (JsonNode element : node) {
                printIndent(level);
                System.out.println("-");
                printJsonNode(element, level + 1);
            }
        } else {
            printIndent(level);
            System.out.println(node.asText());
        }
    }
}
