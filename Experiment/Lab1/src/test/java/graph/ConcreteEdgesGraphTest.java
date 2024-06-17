/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * <p>
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * <p>
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<>();
    }

    /*
     * Testing ConcreteEdgesGraph...
     */
    // ConcreteEdgesGraph 的测试策略
    //   testToStringEmptyGraph:
    //     - 测试当图为空时，toString()方法返回的字符串是否正确。
    //   testToStringOnlyVertices:
    //     - 测试当图只包含顶点而没有边时，toString()方法返回的字符串是否正确。
    //   testToStringWithEdges:
    //     - 测试当图包含顶点和边时，toString()方法返回的字符串是否正确。
    //   testToStringMultipleEdgesBetweenVertices:
    //     - 测试当图包含多个顶点之间有多条边时，toString()方法返回的字符串是否正确。
    @Test
    public void testToStringEmptyGraph() {
        ConcreteEdgesGraph<String> graph = new ConcreteEdgesGraph<>();
        assertEquals("图: []", graph.toString());
    }

    @Test
    public void testToStringOnlyVertices() {
        ConcreteEdgesGraph<String> graph = new ConcreteEdgesGraph<>();
        graph.add("A");
        graph.add("B");
        assertEquals("图: [A, B]", graph.toString());
    }

    @Test
    public void testToStringWithEdges() {
        ConcreteEdgesGraph<String> graph = new ConcreteEdgesGraph<>();
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.set("A", "B", 1);
        graph.set("A", "C", 2);
        assertEquals("图: [A, B, C], (A->B, 权重: 1), (A->C, 权重: 2)", graph.toString());
    }

    @Test
    public void testToStringMultipleEdgesBetweenVertices() {
        ConcreteEdgesGraph<String> graph = new ConcreteEdgesGraph<>();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 1);
        graph.set("A", "B", 2); // Adding another edge between A and B to override
        assertEquals("图: [A, B], (A->B, 权重: 2)", graph.toString());
    }

    /*
     * Testing Edge...
     */
    // Edge 的测试策略
    //   构造函数：
    //     - 创建一个具有非空源顶点、非空目标顶点和正权重的有效边。
    //     - 创建一个源顶点为null的边（期望抛出 AssertionError）。
    //     - 创建一个目标顶点为null的边（期望抛出 AssertionError）。
    //     - 创建一个权重为负数的边（期望抛出 AssertionError）。

    @Test
    public void testEdgeCreation() {
        Edge<String> edge = new Edge<>("A", "B", 5);
        assertEquals("A", edge.getSource());
        assertEquals("B", edge.getTarget());
        assertEquals(5, edge.getWeight());
    }

    @Test(expected = AssertionError.class)
    public void testEdgeCreationWithNullSource() {
        new Edge<>(null, "B", 5);
    }

    @Test(expected = AssertionError.class)
    public void testEdgeCreationWithNullTarget() {
        new Edge<>("A", null, 5);
    }

    @Test(expected = AssertionError.class)
    public void testEdgeCreationWithNegativeWeight() {
        new Edge<>("A", "B", -5);
    }

}