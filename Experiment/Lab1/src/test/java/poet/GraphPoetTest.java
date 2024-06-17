/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {

    // 测试策略：
    // 对 GraphPoet(File corpus) 方法：
    //   - 使用具有已知结构的小型语料库进行测试
    //   - 使用空语料库进行测试
    // 对 poem(String input) 方法：
    //   - 使用不需要桥接词的输入进行测试
    //   - 使用需要一个桥接词的输入进行测试
    //   - 使用需要多个桥接词的输入进行测试
    //   - 使用没有可能的桥接词的输入进行测试
    // 对 toString() 方法：
    //   - 测试空语料库
    //   - 测试非空语料库
    // 对 testAssertionsEnabled() 方法：
    //   - 测试断言是否启用

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testEmptyCorpus() throws IOException {
        GraphPoet poet = new GraphPoet(new File("src/main/resources/empty.txt"));
        assertEquals("预期空诗歌", "", poet.poem(""));
    }

    @Test
    public void testSimplePoem() throws IOException {
        GraphPoet poet = new GraphPoet(new File("src/main/resources/simple.txt"));
        assertEquals("预期有一个桥接词的诗歌", "Test of the system.", poet.poem("Test the system."));
    }

    @Test
    public void testNoBridgeWords() throws IOException {
        GraphPoet poet = new GraphPoet(new File("src/main/resources/simple.txt"));
        assertEquals("预期没有桥接词的诗歌", "This is a test.", poet.poem("This is a test."));
    }

    @Test
    public void testMultipleBridgeWords() throws IOException {
        GraphPoet poet = new GraphPoet(new File("src/main/resources/multiple-bridges.txt"));
        assertEquals("预期有多个桥接词的诗歌", "Test of the system.", poet.poem("Test the system."));
    }

    @Test
    public void testNewCorpus() throws IOException {
        GraphPoet poet = new GraphPoet(new File("src/main/resources/corpus.txt"));
        String input = "Seek to explore new and exciting synergies!";
        String expected = "Seek to explore strange new life and exciting synergies!";
        assertEquals("预期具有多行正确桥接词的诗歌", expected, poet.poem(input));
    }

    @Test
    public void testToString() throws IOException {
        GraphPoet poet = new GraphPoet(new File("src/main/resources/corpus.txt"));
        assertTrue(poet.toString().contains("explore->strange"));
        assertTrue(poet.toString().contains("strange->new"));
        assertTrue(poet.toString().contains("new->worlds"));
        assertTrue(poet.toString().contains("seek->out"));
        assertTrue(poet.toString().contains("out->new"));
        assertTrue(poet.toString().contains("life->and"));
        assertTrue(poet.toString().contains("and->new"));
        assertTrue(poet.toString().contains("new->civilizations"));
    }

}