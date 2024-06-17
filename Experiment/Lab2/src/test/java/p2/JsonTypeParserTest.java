package p2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import p2.Parser.JsonTypeParser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JsonTypeParser 的单元测试类。
 * 测试策略：
 * - 测试 parse 方法是否能够正确解析 JSON 文件并打印键值对。
 *   - 使用有效的文件路径测试解析功能是否正常工作。
 *   - 使用空的文件路径测试异常处理是否正确。
 *   - 验证解析 JSON 文件后的输出是否符合预期格式和内容。
 */
class JsonTypeParserTest {
    private final JsonTypeParser parser = new JsonTypeParser();

    /**
     * 测试 parse 方法使用有效的文件路径。
     * 验证解析 JSON 文件时是否能够正常执行而不抛出异常。
     */
    @Test
    void testValidFilePath() {
        assertDoesNotThrow(() -> parser.parse("src/main/resource/test/testfile.json"));
    }

    /**
     * 测试 parse 方法使用空的文件路径。
     * 验证解析 JSON 文件时是否会抛出 IllegalArgumentException 异常。
     */
    @Test
    void testEmptyFilePath() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(""));
    }

    /**
     * 测试 parse 方法。
     * 验证解析 JSON 文件的输出是否符合预期。
     * 具体步骤：
     * - 准备测试数据，包括要解析的 JSON 文件路径。
     * - 重定向 System.out，捕获解析方法的输出内容。
     * - 调用 JsonTypeParser 实例的 parse 方法解析 JSON 文件。
     * - 恢复原始的 System.out。
     * - 验证捕获的输出内容是否符合预期结果。
     */
    @Test
    void parse() {
        // 准备测试数据
        String filePath = "src/main/resource/example.json";

        // 重定向 System.out，捕获输出内容
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // 创建 JsonTypeParser 实例并调用 parse 方法解析 JSON 文件
        JsonTypeParser jsonTypeParser = new JsonTypeParser();
        jsonTypeParser.parse(filePath);

        // 恢复原始的 System.out
        System.setOut(System.out);

        // 验证解析输出是否符合预期
        String expectedOutput = "解析 JSON 文件：" + filePath + "\r\n" +
                "键值对:\r\n" +
                "name:\r\n" +
                "  John Doe\r\n" +
                "age:\r\n" +
                "  30\r\n" +
                "email:\r\n" +
                "  john.doe@example.com\r\n" +
                "address:\r\n" +
                "  street:\r\n" +
                "    123 Main St\r\n" +
                "  city:\r\n" +
                "    Anytown\r\n" +
                "  state:\r\n" +
                "    CA\r\n" +
                "  zip:\r\n" +
                "    12345\r\n" +
                "phoneNumbers:\r\n" +
                "  -\r\n" +
                "    type:\r\n" +
                "      home\r\n" +
                "    number:\r\n" +
                "      123-456-7890\r\n" +
                "  -\r\n" +
                "    type:\r\n" +
                "      work\r\n" +
                "    number:\r\n" +
                "      098-765-4321\r\n";

        Assertions.assertEquals(expectedOutput, outContent.toString());
    }
}