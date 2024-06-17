package p1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import p1.Parser.XmlTypeParser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * XmlTypeParser 的单元测试类。
 * 测试策略：
 * - 测试 parse 方法是否能够正确解析 XML 文件并打印节点信息。
 */
class XmlTypeParserTest {

    private final XmlTypeParser parser = new XmlTypeParser();

    /**
     * 测试 parse 方法使用有效的文件路径。
     * 验证解析 XML 文件时是否能够正常执行而不抛出异常。
     */
    @org.junit.jupiter.api.Test
    void testValidFilePath() {
        assertDoesNotThrow(() -> parser.parse("src/main/resource/test/testfile.xml"));
    }

    /**
     * 测试 parse 方法使用空的文件路径。
     * 验证解析 XML 文件时是否会抛出 IllegalArgumentException 异常。
     */
    @org.junit.jupiter.api.Test
    void testEmptyFilePath() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(""));
    }

    /**
     * 测试 parse 方法。
     * 验证解析 XML 文件的输出是否符合预期。
     */
    @Test
    void parse() {
        // 准备测试数据
        String filePath = "src/main/resource/example.xml";

        // 重定向 System.out，捕获输出内容
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // 调用 parse 方法解析 XML 文件
        parser.parse(filePath);

        // 恢复原始的 System.out
        System.setOut(System.out);

        // 验证解析输出是否符合预期
        String expectedOutput = """
                解析 XML 文件：src/main/resource/example.xml\r
                根元素：person\r
                节点名称：person\r
                  节点名称：name\r
                    节点值：John Doe\r
                  节点名称：age\r
                    节点值：30\r
                  节点名称：email\r
                    节点值：john.doe@example.com\r
                  节点名称：address\r
                    节点名称：street\r
                      节点值：123 Main St\r
                    节点名称：city\r
                      节点值：Anytown\r
                    节点名称：state\r
                      节点值：CA\r
                    节点名称：zip\r
                      节点值：12345\r
                  节点名称：phoneNumbers\r
                    节点名称：phoneNumber\r
                      节点值：123-456-7890\r
                    节点名称：phoneNumber\r
                      节点值：098-765-4321\r
                """;

        Assertions.assertEquals(expectedOutput, outContent.toString());
    }
}
