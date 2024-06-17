/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * <p>
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {

    // 测试策略
    //   empty() 方法
    //     没有输入参数，唯一输出是一个空图
    //     通过 vertices() 方法来观察结果

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }

    //测试字符串标签的空图：
    //testEmptyGraphWithStringLabels 方法创建了一个标签为 String 的空图，检查其是否为空。
    @Test
    public void testEmptyGraphWithStringLabels() {
        Graph<String> graph = Graph.empty();
        assertNotNull(graph);
        assertTrue(graph.vertices().isEmpty());
    }

    //测试整数标签的空图：
    //testEmptyGraphWithIntegerLabels 方法创建了一个标签为 Integer 的空图，检查其是否为空。
    @Test
    public void testEmptyGraphWithIntegerLabels() {
        Graph<Integer> graph = Graph.empty();
        assertNotNull(graph);
        assertTrue(graph.vertices().isEmpty());
    }

    //测试自定义标签的空图：
    //testEmptyGraphWithCustomLabels 方法定义了一个简单的不可变标签类 Label，创建了一个标签为 Label 的空图，检查其是否为空。
    @Test
    public void testEmptyGraphWithCustomLabels() {
        class Label {
            private final String name;

            Label(String name) {
                this.name = name;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Label label = (Label) obj;
                return name.equals(label.name);
            }

            @Override
            public int hashCode() {
                return name.hashCode();
            }

            @Override
            public String toString() {
                return name;
            }
        }

        Graph<Label> graph = Graph.empty();
        assertNotNull(graph);
        assertTrue(graph.vertices().isEmpty());
    }
}
