package p2;

import org.junit.Test;
import p2.util.FactoryLoader;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;

/**
 * FactoryLoader 的单元测试类。
 * 测试策略：
 * - 测试 scanAndLoadFactories 方法是否能够正确扫描和加载工厂类。
 */
public class FactoryLoaderTest {
    // Example factory interface
    interface FactoryInterface {
    }

    /**
     * 辅助方法：创建临时目录用于测试。
     *
     * @return 临时目录文件对象
     * @throws Exception 如果创建临时目录失败
     */
    private File createTempDirectory() throws Exception {
        File tempDir = File.createTempFile("temp", Long.toString(System.nanoTime()));
        if (!(tempDir.delete())) {
            throw new RuntimeException("Could not delete temp file: " + tempDir.getAbsolutePath());
        }
        if (!(tempDir.mkdir())) {
            throw new RuntimeException("Could not create temp directory: " + tempDir.getAbsolutePath());
        }
        return tempDir;
    }

    /**
     * 辅助方法：编译 Java 文件。
     *
     * @param javaFile 要编译的 Java 文件
     * @throws Exception 如果编译失败
     */
    private void compileJavaFile(File javaFile) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        compiler.getTask(null, fileManager, null, null, null, fileManager.getJavaFileObjects(javaFile)).call();
        fileManager.close();
    }

    /**
     * 测试 scanAndLoadFactories 方法在给定无效路径时抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPath() {
        FactoryLoader.scanAndLoadFactories("invalid/path", "Factory", "p2.util", FactoryInterface.class);
    }

    /**
     * 测试 scanAndLoadFactories 方法在给定路径不是目录时抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNotADirectory() throws Exception {
        File tempFile = File.createTempFile("temp", "file");
        FactoryLoader.scanAndLoadFactories(tempFile.getAbsolutePath(), "Factory", "p2.util", FactoryInterface.class);
    }

    /**
     * 测试 scanAndLoadFactories 方法在给定空目录时抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyDirectory() throws Exception {
        File tempDir = createTempDirectory();
        FactoryLoader.scanAndLoadFactories(tempDir.getAbsolutePath(), "Factory", "p2.util", FactoryInterface.class);
    }

    /**
     * 测试 scanAndLoadFactories 方法在目录中没有匹配的工厂类文件时抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNoMatchingFiles() throws Exception {
        File tempDir = createTempDirectory();
        File tempFile = new File(tempDir, "SomeOtherClass.java");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("package p2.util; public class SomeOtherClass {}");
        }
        compileJavaFile(tempFile);

        FactoryLoader.scanAndLoadFactories(tempDir.getAbsolutePath(), "Factory", "p2.util", FactoryInterface.class);
    }

    /**
     * 测试 scanAndLoadFactories 方法在匹配的工厂类文件不实现指定接口时抛出 IllegalArgumentException 异常。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFactoryClass() throws Exception {
        File tempDir = createTempDirectory();
        File tempFile = new File(tempDir, "InvalidFactory.java");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("package p2.util; public class InvalidFactory { public InvalidFactory() {} }");
        }
        compileJavaFile(tempFile);

        FactoryLoader.scanAndLoadFactories(tempDir.getAbsolutePath(), "Factory", "p2.util", FactoryInterface.class);
    }
}
