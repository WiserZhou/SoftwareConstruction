package graph;

import java.util.*;

/**
 * An implementation of Graph.
 *
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {

    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();

    // Abstraction function:
    // 表示一个有向图，其中顶点存储在'vertices'集合中，边则存储在'edges'列表里。
    // 所以AF是从这两种抽象数据类型，到有向图的映射

    // Representation invariant:
    // edges 应该有起始节点，长度应该是大于0的实数
    // 每个vertex必须在vertices集合中
    // 每两点之间，最多只能有一条边，其源顶点和目标顶点都必须存在于'vertices'集合内。

    // Safety from rep exposure:
    //所有字段都是私有的并且是最终的(final)。
    //'vertices'和'edges'作为不可变集合返回，形式为不可修改的视图。
    // 在有必要的时候使用防御性拷贝

    /**
     * 检查表示不变性
     */
    private void checkRep() {
        // 用于检查每两点之间是否有多于一条边
        Set<String> edgeSet = new HashSet<>();

        for (Edge<L> edge : edges) {
            L source = edge.getSource();
            L target = edge.getTarget();
            double weight = edge.getWeight();

            // 检查源和目标顶点是否在顶点集合中
            assert vertices.contains(source) : "源顶点不在顶点集合中";
            assert vertices.contains(target) : "目标顶点不在顶点集合中";

            // 检查边的权重是否大于0
            assert weight > 0 : "边的权重必须是大于0的实数";

            // 检查每两点之间是否有多于一条边
            String edgeIdentifier = source.toString() + "->" + target.toString();
            assert !edgeSet.contains(edgeIdentifier) : "每两点之间最多只能有一条边";
            edgeSet.add(edgeIdentifier);
        }
    }


    @Override
    public boolean add(L vertex) {
        if (vertex == null) throw new NullPointerException("顶点不能为null");
        if (vertices.contains(vertex)) {
            return false; // 顶点已存在
        }
        vertices.add(vertex);
        checkRep();
        return true;
    }

    @Override
    public int set(L source, L target, int weight) {
        if (source == null || target == null) throw new NullPointerException("源和目标不能为null");
        if (!vertices.contains(source)) add(source);
        if (!vertices.contains(target)) add(target);

        // 查找现有的边
        for (Edge<L> e : edges) {
            if (e.getSource().equals(source) && e.getTarget().equals(target)) {
                int oldWeight = e.getWeight();
                edges.remove(e); // 移除现有的边
                if (weight > 0) {
                    edges.add(new Edge<>(source, target, weight)); // 添加新边
                }
                checkRep();
                return oldWeight; // 返回旧权重
            }
        }

        // 如果没有找到现有的边
        if (weight > 0) {
            edges.add(new Edge<>(source, target, weight)); // 添加新边
        }
        checkRep();
        return 0; // 返回0表示没有旧边
    }


    @Override
    public boolean remove(L vertex) {
        if (!vertices.contains(vertex)) {
            return false; // 顶点不存在
        }
        // 移除与顶点相关的边
        edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
        // 移除顶点本身
        vertices.remove(vertex);
        checkRep();
        return true;
    }

    @Override
    public Set<L> vertices() {
        return Collections.unmodifiableSet(vertices);
    }

    @Override
    public Map<L, Integer> sources(L target) {
        Map<L, Integer> sourceMap = new HashMap<>();
        for (Edge<L> edge : edges) {
            if (edge.getTarget().equals(target)) {
                sourceMap.put(edge.getSource(), edge.getWeight());
            }
        }
        return Collections.unmodifiableMap(sourceMap);
    }


    @Override
    public Map<L, Integer> targets(L source) {
        Map<L, Integer> targetMap = new HashMap<>();
        for (Edge<L> edge : edges) {
            if (edge.getSource().equals(source)) {
                targetMap.put(edge.getTarget(), edge.getWeight());
            }
        }
        return Collections.unmodifiableMap(targetMap);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("图: [");

        // 添加顶点
        boolean isFirstVertex = true;
        for (L vertex : vertices) {
            if (!isFirstVertex) {
                sb.append(", ");
            }
            sb.append(vertex.toString());
            isFirstVertex = false;
        }
        sb.append("]");
        // 添加边
        for (Edge<L> edge : edges) {
            sb.append(", (").append(edge.getSource().toString()).append("->").append(edge.getTarget().toString())
                    .append(", 权重: ").append(edge.getWeight()).append(")");
        }

        return sb.toString();
    }


}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 *
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {

    private final L source;
    private final L target;
    private final int weight;

    // Abstraction function:
    // 以source，target，weight组成的抽象数据型，到有向边集合的映射

    // Representation invariant:
    // 源和目标不能为null。
    // 权重必须是非负数。

    // Safety from rep exposure:
    // 所有字段都是私有和最终的(final)。
    // 使用不可变类型作为字段。

    /**
     * 构建Edge对象。
     *
     * @param source 源顶点。
     * @param target 目标顶点。
     * @param weight 边的权重。
     */
    public Edge(L source, L target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }

    /**
     * 检查表示不变性是否保持。
     */
    private void checkRep() {
        assert source != null : "源顶点不能为null";
        assert target != null : "目标顶点不能为null";
        assert weight >= 0 : "权重必须是非负数";
    }

    /**
     * 获取边的源顶点。
     *
     * @return 源顶点。
     */
    public L getSource() {
        return source;
    }

    /**
     * 获取边的目标顶点。
     *
     * @return 目标顶点。
     */
    public L getTarget() {
        return target;
    }

    /**
     * 获取边的权重。
     *
     * @return 边的权重。
     */
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(" + source.toString() + "->" + target.toString() + ", 权重: " + weight + ")";
    }

}