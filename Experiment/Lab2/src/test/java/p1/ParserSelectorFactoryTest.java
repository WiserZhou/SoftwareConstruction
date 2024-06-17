package p1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import p1.Factory.ParserSelectorFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class ParserSelectorFactoryTest {

    @TempDir
    Path tempDir;

    /**
     * 设置临时目录，并创建模拟的解析器类文件。
     *
     * @throws IOException 如果创建文件时出错
     */
    @BeforeEach
    void setUp() throws IOException {
        // 创建模拟的 TxtTypeParser 和 XmlTypeParser 类文件
        String txtParserContent = """
                package p1.Parser;
                import p1.Interface.Parser;
                public class TxtTypeParser implements Parser {
                    public void parse(String filePath) {
                        System.out.println("Parsing TXT file: " + filePath);
                    }
                }
                """;
        String xmlParserContent = """
                package p1.Parser;
                import p1.Interface.Parser;
                public class XmlTypeParser implements Parser {
                    public void parse(String filePath) {
                        System.out.println("Parsing XML file: " + filePath);
                    }
                }
                """;

        Files.write(tempDir.resolve("TxtTypeParser.java"), txtParserContent.getBytes());
        Files.write(tempDir.resolve("XmlTypeParser.java"), xmlParserContent.getBytes());

        // 设置 DIRECTORY_PATH 为临时目录
        System.setProperty("DIRECTORY_PATH", tempDir.toString());
    }

    /**
     * 测试策略：
     * 测试 parseFile 方法是否能正确解析受支持的文件类型。
     * <p>
     * 测试点：
     * 1. 解析 TXT 文件是否输出正确的信息。
     * 2. 解析 XML 文件是否输出正确的信息。
     */
    @Test
    public void testParseFileWithSupportedType() {
        ParserSelectorFactory factory = new ParserSelectorFactory();

        // 验证解析器的输出内容
        ByteArrayOutputStream xmlOutContent = new ByteArrayOutputStream();
        ByteArrayOutputStream jsonOutContent = new ByteArrayOutputStream();
        ByteArrayOutputStream yamlOutContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(xmlOutContent));
        factory.parseFile("src/main/resource/test/testfile.xml");
        Assert.assertFalse("应该正确解析 XML 文件", xmlOutContent.toString().isEmpty());

        System.setOut(new PrintStream(jsonOutContent));
        factory.parseFile("src/main/resource/test/testfile.json");
        Assert.assertFalse("应该正确解析 JSON 文件", jsonOutContent.toString().isEmpty());

        System.setOut(new PrintStream(yamlOutContent));
        factory.parseFile("src/main/resource/test/testfile.yml");
        Assert.assertFalse("应该正确解析 YAML 文件", yamlOutContent.toString().isEmpty());

        System.setOut(System.out);
    }

    /**
     * 测试策略：
     * 测试 parseFile 方法在文件类型不受支持时是否抛出异常。
     * <p>
     * 测试点：
     * 1. 解析不支持的文件类型时是否抛出 IllegalArgumentException 异常。
     */
    @Test
    public void testParseFileWithUnsupportedType() {
        ParserSelectorFactory factory = new ParserSelectorFactory();

        // 预期抛出 IllegalArgumentException 异常
        assertThrows(IllegalArgumentException.class, () -> factory.parseFile("src/main/resource/test/testfile.txt"));
    }

}
