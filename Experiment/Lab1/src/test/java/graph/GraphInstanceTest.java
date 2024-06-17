/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 *
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    /**
     * Overridden by implementation-specific test classes.
     *
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    // 测试策略

    // 1. 添加顶点
    // - 测试添加单个顶点成功
    // - 测试添加重复顶点失败
    // - 测试添加多个顶点成功
    // - 测试添加空顶点异常
    // - 测试添加空字符串顶点成功

    // 2. 添加边
    // - 测试添加新边成功
    // - 测试更新现有边权重
    // - 测试移除现有边成功
    // - 测试自环边成功
    // - 测试添加多条边成功

    // 3. 移除顶点
    // - 测试移除存在顶点成功
    // - 测试移除不存在顶点失败
    // - 测试移除带有边顶点成功
    // - 测试移除带有自环顶点成功
    // - 测试从空图移除顶点失败

    // 4. 获取顶点
    // - 测试添加单个顶点后获取顶点
    // - 测试添加多个顶点后获取顶点
    // - 测试移除顶点后获取顶点
    // - 测试添加和移除顶点后获取顶点
    // - 测试从空图获取顶点成功

    // 5. 获取源顶点
    // - 测试从空图获取源顶点成功
    // - 测试无入边顶点源顶点
    // - 测试一条入边顶点源顶点
    // - 测试多条入边顶点源顶点
    // - 测试移除边后源顶点
    // - 测试有自环边顶点源顶点

    // 6. 获取目标顶点
    // - 测试从空图获取目标顶点成功
    // - 测试无出边顶点目标顶点
    // - 测试一条出边顶点目标顶点
    // - 测试多条出边顶点目标顶点
    // - 测试移除边后目标顶点
    // - 测试有自环边顶点目标顶点

    /**
     * 测试添加单个顶点。
     * 如果成功添加到图中，断言将返回True
     */
    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue("预期成功添加顶点", graph.add("A"));
        assertTrue("预期图中包含添加的顶点", graph.vertices().contains("A"));
    }

    /**
     * 测试添加重复顶点。
     * 将向图中添加重复顶点，如果不添加，则预期返回1。
     */
    @Test
    public void testAddDuplicateVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertFalse("预期不成功添加重复顶点", graph.add("A"));
        assertEquals("预期图中只有一个顶点", 1, graph.vertices().size());
    }

    /**
     * 测试添加多个顶点。
     */
    @Test
    public void testAddMultipleVertices() {
        Graph<String> graph = emptyInstance();
        assertTrue("预期成功添加顶点A", graph.add("A"));
        assertTrue("预期成功添加顶点B", graph.add("B"));
        assertTrue("预期成功添加顶点C", graph.add("C"));
        assertTrue("预期图中包含所有添加的顶点", graph.vertices().containsAll(Arrays.asList("A", "B", "C")));
        assertEquals("预期图中有三个顶点", 3, graph.vertices().size());
    }

    /**
     * 测试添加空顶点（如果规范允许空，则可能不同）。
     */
    @Test(expected = NullPointerException.class)
    public void testAddNullVertex() {
        Graph<String> graph = emptyInstance();
        graph.add(null); // 假设不允许为空，应该抛出异常
    }

    /**
     * 测试添加空字符串顶点（假设空字符串作为有效顶点被允许）。
     */
    @Test
    public void testAddEmptyStringVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue("预期成功添加空字符串顶点", graph.add(""));
        assertTrue("预期图中包含添加的空字符串顶点", graph.vertices().contains(""));
    }


    /**
     * 测试添加新边。
     */
    @Test
    public void testAddNewEdge() {
        Graph<String> graph = emptyInstance();
        int prevWeight = graph.set("A", "B", 5);
        assertEquals("预期无前一边权重", 0, prevWeight);
        assertTrue("预期图中包含源顶点", graph.vertices().contains("A"));
        assertEquals("预期边的权重", 5, graph.set("A", "B", 0)); // 验证边的权重
    }

    /**
     * 测试更新现有边的权重。
     */
    @Test
    public void testUpdateEdgeWeight() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "B", 5);
        int prevWeight = graph.set("A", "B", 10);
        assertEquals("预期先前边权重为5", 5, prevWeight);
        assertEquals("预期更新的边权重", 10, graph.set("A", "B", 0)); // 验证边的权重
    }

    /**
     * 测试移除现有边。
     */
    @Test
    public void testRemoveEdge() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "B", 5);
        int prevWeight = graph.set("A", "B", 0);
        assertEquals("预期先前边权重为5", 5, prevWeight);
        assertEquals("预期移除边的权重", 0, graph.set("A", "B", 0)); // 验证边被移除
    }

    /**
     * 测试自环边。
     */
    @Test
    public void testSelfLoopEdge() {
        Graph<String> graph = emptyInstance();
        int prevWeight = graph.set("A", "A", 3);
        assertEquals("预期无前一边权重", 0, prevWeight);
        assertEquals("预期自环边的权重", 3, graph.set("A", "A", 0)); // 验证自环边的权重
    }

    /**
     * 测试添加多条边。
     */
    @Test
    public void testAddMultipleEdges() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "B", 5);
        graph.set("A", "C", 10);
        graph.set("B", "C", 15);
        assertEquals("预期边A->B的权重", 5, graph.set("A", "B", 0)); // 验证边A->B的权重
        assertEquals("预期边A->C的权重", 10, graph.set("A", "C", 0)); // 验证边A->C的权重
        assertEquals("预期边B->C的权重", 15, graph.set("B", "C", 0)); // 验证边B->C的权重
    }

    /**
     * 测试移除存在的顶点。
     */
    @Test
    public void testRemoveVertexExists() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        assertTrue("预期成功移除顶点A", graph.remove("A"));
        assertFalse("预期图中不再包含顶点A", graph.vertices().contains("A"));
        assertTrue("预期图中仍包含顶点B", graph.vertices().contains("B"));
        assertEquals("预期与顶点A无连接的边", 0, graph.set("A", "B", 0));
    }

    /**
     * 测试移除不存在的顶点。
     */
    @Test
    public void testRemoveVertexNotExists() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertFalse("预期不成功移除不存在的顶点B", graph.remove("B"));
        assertTrue("预期图中仍包含顶点A", graph.vertices().contains("A"));
    }

    /**
     * 测试移除有边的顶点。
     */
    @Test
    public void testRemoveVertexWithEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.set("A", "B", 5);
        graph.set("B", "C", 10);
        assertTrue("预期成功移除顶点B", graph.remove("B"));
        assertFalse("预期图中不再包含顶点B", graph.vertices().contains("B"));
        assertTrue("预期图中包含顶点A", graph.vertices().contains("A"));
        assertTrue("预期图中包含顶点C", graph.vertices().contains("C"));
        assertEquals("预期无从A到B的边", 0, graph.set("A", "B", 0));
        assertEquals("预期无从B到C的边", 0, graph.set("B", "C", 0));
    }

    /**
     * 测试移除带有自环的顶点。
     */
    @Test
    public void testRemoveVertexWithSelfLoop() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.set("A", "A", 3);
        assertTrue("预期成功移除顶点A", graph.remove("A"));
        assertFalse("预期图中不再包含顶点A", graph.vertices().contains("A"));
        assertEquals("预期无自环在A上", 0, graph.set("A", "A", 0));
    }


    /**
     * 测试从空图中移除顶点。
     */
    @Test
    public void testRemoveVertexFromEmptyGraph() {
        Graph<String> graph = emptyInstance();
        assertFalse("期望从空图中移除顶点不成功", graph.remove("A"));
    }

    /**
     * 测试添加单个顶点后的顶点获取。
     */
    @Test
    public void testVerticesAfterAddVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        Set<String> vertices = graph.vertices();
        assertEquals("期望图有一个顶点", 1, vertices.size());
        assertTrue("期望图包含添加的顶点", vertices.contains("A"));
    }

    /**
     * 测试添加多个顶点后的顶点获取。
     */
    @Test
    public void testVerticesAfterAddMultipleVertices() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.add("C");
        Set<String> expectedVertices = new HashSet<>(Arrays.asList("A", "B", "C"));
        assertEquals("期望图包含所有添加的顶点", expectedVertices, graph.vertices());
    }

    /**
     * 测试移除顶点后的顶点获取。
     */
    @Test
    public void testVerticesAfterRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.remove("A");
        Set<String> vertices = graph.vertices();
        assertEquals("期望图有一个顶点", 1, vertices.size());
        assertFalse("期望图不含已移除的顶点", vertices.contains("A"));
        assertTrue("期望图包含剩余的顶点", vertices.contains("B"));
    }

    /**
     * 测试添加和移除顶点后的顶点获取。
     */
    @Test
    public void testVerticesAfterAddAndRemoveVertices() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.remove("B");
        Set<String> expectedVertices = new HashSet<>(Arrays.asList("A", "C"));
        assertEquals("期望图包含剩余顶点", expectedVertices, graph.vertices());
    }

    /**
     * 测试从空图获取顶点。
     */
    @Test
    public void testVerticesFromEmptyGraph() {
        Graph<String> graph = emptyInstance();
        assertTrue("期望空图无顶点", graph.vertices().isEmpty());
    }

    /**
     * 测试从空图获取源顶点。
     */
    @Test
    public void testSourcesEmptyGraph() {
        Graph<String> graph = emptyInstance();
        Map<String, Integer> sources = graph.sources("A");
        assertTrue("期望空图无源顶点", sources.isEmpty());
    }

    /**
     * 测试无入边顶点的源顶点获取。
     */
    @Test
    public void testSourcesNoIncomingEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        Map<String, Integer> sources = graph.sources("A");
        assertTrue("期望无入边顶点无源顶点", sources.isEmpty());
    }

    /**
     * 测试有一条入边顶点的源顶点获取。
     */
    @Test
    public void testSourcesOneIncomingEdge() {
        Graph<String> graph = emptyInstance();
        graph.set("B", "A", 5);
        Map<String, Integer> sources = graph.sources("A");
        assertEquals("期望有一个源顶点", 1, sources.size());
        assertTrue("期望源顶点是B", sources.containsKey("B"));
        assertEquals("期望权重是5", Integer.valueOf(5), sources.get("B"));
    }

    /**
     * 测试有多条入边顶点的源顶点获取。
     */
    @Test
    public void testSourcesMultipleIncomingEdges() {
        Graph<String> graph = emptyInstance();
        graph.set("B", "A", 5);
        graph.set("C", "A", 10);
        Map<String, Integer> sources = graph.sources("A");
        assertEquals("期望有两个源顶点", 2, sources.size());
        assertTrue("期望源顶点是B", sources.containsKey("B"));
        assertTrue("期望源顶点是C", sources.containsKey("C"));
        assertEquals("期望权重是5来自B", Integer.valueOf(5), sources.get("B"));
        assertEquals("期望权重是10来自C", Integer.valueOf(10), sources.get("C"));
    }

    /**
     * 测试移除边后的源顶点获取。
     */
    @Test
    public void testSourcesAfterRemovingEdge() {
        Graph<String> graph = emptyInstance();
        graph.set("B", "A", 5);
        graph.set("C", "A", 10);
        graph.set("B", "A", 0); // 移除边B->A
        Map<String, Integer> sources = graph.sources("A");
        assertEquals("期望移除边后有一个源顶点", 1, sources.size());
        assertFalse("期望源顶点不是B", sources.containsKey("B"));
        assertTrue("期望源顶点是C", sources.containsKey("C"));
        assertEquals("期望权重是10来自C", Integer.valueOf(10), sources.get("C"));
    }

    /**
     * 测试有自环边顶点的源顶点获取。
     */
    @Test
    public void testSourcesSelfLoop() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "A", 3);
        Map<String, Integer> sources = graph.sources("A");
        assertEquals("期望自环边有一个源顶点", 1, sources.size());
        assertTrue("期望自环边的源顶点是A", sources.containsKey("A"));
        assertEquals("期望自环边的权重是3", Integer.valueOf(3), sources.get("A"));
    }

    /**
     * 测试从空图获取目标顶点。
     */
    @Test
    public void testTargetsEmptyGraph() {
        Graph<String> graph = emptyInstance();
        Map<String, Integer> targets = graph.targets("A");
        assertTrue("期望空图无目标顶点", targets.isEmpty());
    }

    /**
     * 测试无出边顶点的目标顶点获取。
     */
    @Test
    public void testTargetsNoOutgoingEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        Map<String, Integer> targets = graph.targets("A");
        assertTrue("期望无出边顶点无目标顶点", targets.isEmpty());
    }

    /**
     * 测试有一条出边顶点的目标顶点获取。
     */
    @Test
    public void testTargetsOneOutgoingEdge() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "B", 5);
        Map<String, Integer> targets = graph.targets("A");
        assertEquals("期望有一个目标顶点", 1, targets.size());
        assertTrue("期望目标顶点是B", targets.containsKey("B"));
        assertEquals("期望权重是5", Integer.valueOf(5), targets.get("B"));
    }

    /**
     * 测试有多条出边顶点的目标顶点获取。
     */
    @Test
    public void testTargetsMultipleOutgoingEdges() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "B", 5);
        graph.set("A", "C", 10);
        Map<String, Integer> targets = graph.targets("A");
        assertEquals("期望有两个目标顶点", 2, targets.size());
        assertTrue("期望目标顶点是B", targets.containsKey("B"));
        assertTrue("期望目标顶点是C", targets.containsKey("C"));
        assertEquals("期望权重是5到B", Integer.valueOf(5), targets.get("B"));
        assertEquals("期望权重是10到C", Integer.valueOf(10), targets.get("C"));
    }

    /**
     * 测试移除边后的目标顶点获取。
     */
    @Test
    public void testTargetsAfterRemovingEdge() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "B", 5);
        graph.set("A", "C", 10);
        graph.set("A", "B", 0); // 移除边A->B
        Map<String, Integer> targets = graph.targets("A");
        assertEquals("期望移除边后有一个目标顶点", 1, targets.size());
        assertFalse("期望目标顶点不是B", targets.containsKey("B"));
        assertTrue("期望目标顶点是C", targets.containsKey("C"));
        assertEquals("期望权重是10到C", Integer.valueOf(10), targets.get("C"));
    }

    /**
     * 测试有自环边顶点的目标顶点获取。
     */
    @Test
    public void testTargetsSelfLoop() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "A", 3);
        Map<String, Integer> targets = graph.targets("A");
        assertEquals("期望自环边有一个目标顶点", 1, targets.size());
        assertTrue("期望自环边的目标顶点是A", targets.containsKey("A"));
        assertEquals("期望自环边的权重是3", Integer.valueOf(3), targets.get("A"));
    }
}
