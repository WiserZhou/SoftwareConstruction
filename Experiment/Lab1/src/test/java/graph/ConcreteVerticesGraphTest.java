/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * <p>
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * <p>
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<>();
    }

    /*
     * Testing ConcreteVerticesGraph...
     */
    // ConcreteVerticesGraph 的测试策略
    //   testToStringEmptyGraph:
    //     - 测试当图为空时，toString()方法返回的字符串是否正确。
    @Test
    public void testToStringEmptyGraph() {
        ConcreteVerticesGraph<String> graph = new ConcreteVerticesGraph<>();
        assertEquals("图: []", graph.toString());
    }

    //   testToStringOnlyVertices:
    //     - 测试当图只包含顶点而没有边时，toString()方法返回的字符串是否正确。
    @Test
    public void testToStringOnlyVertices() {
        ConcreteVerticesGraph<String> graph = new ConcreteVerticesGraph<>();
        graph.add("A");
        graph.add("B");
        assertEquals("图: [A, B]", graph.toString());
    }

    //   testToStringWithEdges:
    //     - 测试当图包含顶点和边时，toString()方法返回的字符串是否正确。
    @Test
    public void testToStringWithEdges() {
        ConcreteVerticesGraph<String> graph = new ConcreteVerticesGraph<>();
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.set("A", "B", 1);
        graph.set("A", "C", 2);
        assertEquals("图: [A, B, C], (A->B, 权重: 1), (A->C, 权重: 2)", graph.toString());
    }

    //   testToStringMultipleEdgesBetweenVertices:
    //     - 测试当图包含多个顶点之间有多条边时，toString()方法返回的字符串是否正确。
    @Test
    public void testToStringMultipleEdgesBetweenVertices() {
        ConcreteVerticesGraph<String> graph = new ConcreteVerticesGraph<>();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 1);
        graph.set("A", "B", 2); // Adding another edge between A and B to override
        assertEquals("图: [A, B], (A->B, 权重: 2)", graph.toString());
    }

    /*
     * Testing Vertex...
     */

    // Vertex 的测试策略
    //   testVertexCreation:
    //     - 测试顶点对象的创建是否正确。
    @Test
    public void testVertexCreation() {
        Vertex<String> vertex = new Vertex<>("A");
        assertEquals("A", vertex.getName());
        assertEquals(0, vertex.getEdges().size());
    }

    //   testSetEdge:
    //     - 测试设置边是否正确，包括边的权重。
    @Test
    public void testSetEdge() {
        Vertex<String> vertex = new Vertex<>("A");
        assertEquals(0, vertex.setEdge("B", 1)); // 添加新的边
        assertEquals(1, vertex.getEdges().size()); // 确保边已添加
        assertEquals(1, vertex.setEdge("B", 2)); // 更新边的权重
        assertEquals(1, vertex.getEdges().size()); // 确保边的数量未改变
    }

    //   testRemoveEdge:
    //     - 测试移除边是否正确。
    @Test
    public void testRemoveEdge() {
        Vertex<String> vertex = new Vertex<>("A");
        vertex.setEdge("B", 1); // 添加新的边
        assertEquals(1, vertex.removeEdge("B")); // 移除边
        assertEquals(0, vertex.removeEdge("B")); // 移除不存在的边
    }


}
