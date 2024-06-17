package p1.Parser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import p1.Interface.Parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * XmlTypeParser 是 Parser 的一个具体实现类，用于解析 XML 文件。
 * <p>
 * 抽象函数 (AF):
 * 表示一个可以解析 XML 文件并打印其内容的解析器。
 * <p>
 * 表示不变性 (RI):
 * - filePath 必须是一个有效的字符串，不能为 null 或空。
 * - 解析的文件必须是有效的 XML 文件。
 * <p>
 * 防止表示暴露 (Safety from Rep Exposure):
 * - 没有内部状态，因此不存在表示暴露的问题。
 */
public class XmlTypeParser extends Parser {

    @Override
    public void parse(String filePath) {
        validateFilePath(filePath);
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(filePath));
            doc.getDocumentElement().normalize();

            System.out.println("解析 XML 文件：" + filePath);
            System.out.println("根元素：" + doc.getDocumentElement().getNodeName());

            parseNode(doc.getDocumentElement(), 0);
        } catch (Exception e) {
            handleParsingError(e);
        }
    }

    /**
     * 递归遍历节点。
     *
     * @param node  要解析的节点。
     * @param level 缩进级别。
     */
    private void parseNode(Node node, int level) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            printIndent(level);
            System.out.println("节点名称：" + node.getNodeName());
            if (node.hasChildNodes()) {
                NodeList childNodes = node.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    parseNode(childNodes.item(i), level + 1);
                }
            }
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            String value = node.getNodeValue().trim();
            if (!value.isEmpty()) {
                printIndent(level);
                System.out.println("节点值：" + value);
            }
        }
    }
}

