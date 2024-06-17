package p1;

import org.junit.jupiter.api.Test;
import p1.Parser.YmlTypeParser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * YmlTypeParser 的单元测试类。
 * 测试策略：
 * - 测试 parse 方法是否能够正确解析 YAML 文件并打印内容。
 */
public class YmlTypeParserTest {

    private final YmlTypeParser parser = new YmlTypeParser();

    /**
     * 测试 parse 方法使用有效的文件路径。
     * 验证解析 YAML 文件时是否能够正常执行而不抛出异常。
     */
    @Test
    void testValidFilePath() {
        assertDoesNotThrow(() -> parser.parse("src/main/resource/test/testfile.yml"));
    }

    /**
     * 测试 parse 方法使用空的文件路径。
     * 验证解析 YAML 文件时是否会抛出 IllegalArgumentException 异常。
     */
    @Test
    void testEmptyFilePath() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(""));
    }

    /**
     * 测试 parse 方法。
     * 验证解析 YAML 文件的输出是否符合预期。
     */
    @Test
    public void parse() {
        // 准备测试数据
        String filePath = "src/main/resource/example.yml";

        // 重定向 System.out，捕获输出内容
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // 调用 parse 方法解析 YAML 文件
        parser.parse(filePath);

        // 恢复原始的 System.out
        System.setOut(System.out);

        // 验证解析输出是否符合预期
        String expectedOutput = """
                解析 YAML 文件：src/main/resource/example.yml\r
                root:\r
                  child1:\r
                    subchild1:\r
                      value1\r
                    subchild2:\r
                      value2\r
                  child2:\r
                    - \r
                      listitem1\r
                    - \r
                      listitem2\r
                  child3:\r
                    value3\r
                """;

        assertEquals(expectedOutput, outContent.toString());
    }

}
