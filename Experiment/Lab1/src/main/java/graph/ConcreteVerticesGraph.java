package graph;

import java.util.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of Graph.
 *
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {

    private final List<Vertex<L>> vertices = new ArrayList<>();

    // Abstraction function:
    // 表示一个有向图，其中顶点存储在'vertices'列表中，每个顶点包含它的出边。
    // 所以AF是从顶点列表到有向图的映射

    // Representation invariant:
    // 顶点列表中的每个顶点应该是唯一的。
    // 顶点的名称不能为空。
    // 每个顶点的边的目标顶点应该存在于顶点列表中。
    // 边的权重应该是大于0的实数。

    // Safety from rep exposure:
    // 所有字段都是私有的并且是最终的(final)。
    // 在有必要的时候使用防御性拷贝，确保外部无法直接修改内部数据结构。

    /**
     * 检查表示不变性
     */
    private void checkRep() {
        Set<L> existingVertexNames = new HashSet<>(vertices.size());
        for (Vertex<L> vertex : vertices) {
            assert vertex.getName() != null : "顶点名称不能为空";

            // 在检查之前，先收集所有顶点的名称
            if (!existingVertexNames.add(vertex.getName())) {
                throw new IllegalStateException("顶点名称必须唯一");
            }

            Map<L, Integer> edges = vertex.getEdges();
            for (Map.Entry<L, Integer> entry : edges.entrySet()) {
                assert entry.getValue() > 0 : "边的权重必须是正数";
            }
        }
    }

    @Override
    public boolean add(L vertex) {
        if (vertex == null) throw new NullPointerException("顶点不能为null");
        for (Vertex<L> v : vertices) {
            if (v.getName().equals(vertex)) {
                return false; // 顶点已存在
            }
        }
        vertices.add(new Vertex<>(vertex));
        checkRep();
        return true;
    }

    @Override
    public int set(L source, L target, int weight) {
        if (source == null || target == null) throw new NullPointerException("源和目标不能为null");

        Vertex<L> sourceVertex = findVertex(source);
        int oldWeight = 0;

        if (sourceVertex != null) {
            if (weight == 0) {
                oldWeight = sourceVertex.removeEdge(target);
            } else {
                oldWeight = sourceVertex.setEdge(target, weight);
            }
        } else {
            if (weight != 0) {
                sourceVertex = new Vertex<>(source);
                sourceVertex.setEdge(target, weight);
                vertices.add(sourceVertex);
            }
        }

        checkRep();
        return oldWeight;
    }


    /**
     * 在顶点列表中查找指定名称的顶点。
     *
     * @param name 要查找的顶点名称。
     * @return 如果找到，则返回该顶点；否则返回null。
     */
    private Vertex<L> findVertex(L name) {
        for (Vertex<L> vertex : vertices) {
            if (vertex.getName().equals(name)) {
                return vertex;
            }
        }
        return null;
    }

    @Override
    public boolean remove(L vertex) {
        Vertex<L> vertexToRemove = null;
        for (Vertex<L> v : vertices) {
            if (v.getName().equals(vertex)) {
                vertexToRemove = v;
                break;
            }
        }

        if (vertexToRemove == null) {
            return false; // 顶点不存在
        }

        vertices.remove(vertexToRemove);
        for (Vertex<L> v : vertices) {
            v.removeEdge(vertex);
        }

        checkRep();
        return true;
    }

    @Override
    public Set<L> vertices() {
        Set<L> vertexNames = new HashSet<>();
        for (Vertex<L> v : vertices) {
            vertexNames.add(v.getName());
        }
        return Collections.unmodifiableSet(vertexNames);
    }

    @Override
    public Map<L, Integer> sources(L target) {
        // 创建一个存储源顶点及其权重的映射
        Map<L, Integer> sourceMap = new HashMap<>();

        // 标记是否存在自环边的布尔变量，默认为false
        boolean hasSelfLoop = false;

        // 遍历图中的每个顶点
        for (Vertex<L> v : vertices) {
            // 如果当前顶点的名称等于目标顶点的名称，并且当前顶点的边集合中包含目标顶点的名称
            if (v.getName().equals(target) && v.getEdges().containsKey(target)) {
                // 将目标顶点及其权重添加到源顶点映射中
                sourceMap.put(target, v.getEdges().get(target));
                // 标记存在自环边
                hasSelfLoop = true;
            }
            // 如果当前顶点的边集合中包含目标顶点的名称，但当前顶点的名称不等于目标顶点的名称
            else if (v.getEdges().containsKey(target)) {
                // 将当前顶点及其与目标顶点之间的权重添加到源顶点映射中
                sourceMap.put(v.getName(), v.getEdges().get(target));
            }
        }

        // 处理自环边的情况
        if (!hasSelfLoop) {
            // 如果不存在自环边，从源顶点映射中移除目标顶点
            sourceMap.remove(target);
        }

        // 返回不可修改的源顶点映射
        return Collections.unmodifiableMap(sourceMap);
    }


    @Override
    public Map<L, Integer> targets(L source) {
        for (Vertex<L> v : vertices) {
            if (v.getName().equals(source)) {
                return Collections.unmodifiableMap(v.getEdges());
            }
        }
        return Collections.unmodifiableMap(new HashMap<>());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("图: [");

        boolean isFirstVertex = true;
        for (Vertex<L> vertex : vertices) {
            if (!isFirstVertex) {
                sb.append(", ");
            }
            sb.append(vertex.getName().toString());
            isFirstVertex = false;
        }
        sb.append("]");
        for (Vertex<L> vertex : vertices) {
            for (Map.Entry<L, Integer> edge : vertex.getEdges().entrySet()) {
                sb.append(", (").append(vertex.getName().toString()).append("->").append(edge.getKey().toString())
                        .append(", 权重: ").append(edge.getValue().toString()).append(")");
            }
        }


        return sb.toString();
    }
}


/**
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 */
class Vertex<L> {

    private final L name;
    private final Map<L, Integer> edges = new HashMap<>();

    // Abstraction function:
    // 表示一个顶点以及它指向的有向边的集合。
    // 顶点名称存储在'name'字段中，边存储在'edges'映射中，其中键是目标顶点，值是边的权重。

    // Representation invariant:
    // 顶点名称不能为null。
    // 边的权重必须是非负数。

    // Safety from rep exposure:
    // 所有字段都是私有和最终的。
    // 使用不可变类型作为字段。


    /**
     * 构建Vertex对象。
     *
     * @param name 顶点名称。
     */
    public Vertex(L name) {
        this.name = name;
        checkRep();
    }

    /**
     * 检查表示不变性是否保持。
     */
    private void checkRep() {
        assert name != null : "顶点名称不能为null";
        for (int weight : edges.values()) {
            assert weight >= 0 : "边的权重必须是非负数";
        }
    }

    /**
     * 获取顶点名称。
     *
     * @return 顶点名称。
     */
    public L getName() {
        return name;
    }

    /**
     * 获取顶点的边集合。
     *
     * @return 边集合，不可变视图。
     */
    public Map<L, Integer> getEdges() {
        return Collections.unmodifiableMap(edges);
    }

    /**
     * 设置边的权重。如果边已经存在，更新其权重；否则，添加新的边。
     *
     * @param target 目标顶点。
     * @param weight 边的权重。
     * @return 旧的权重，如果没有旧边则返回0。
     */
    public int setEdge(L target, int weight) {
        Integer oldWeight = edges.put(target, weight);
        return oldWeight != null ? oldWeight : 0;
    }

    /**
     * 移除与目标顶点的边。
     *
     * @param target 目标顶点。
     * @return 旧的权重值，如果没有与目标顶点相关联的边则返回0。
     */
    public int removeEdge(L target) {
        Integer oldWeight = edges.remove(target);
        return oldWeight != null ? oldWeight : 0;
    }


    @Override
    public String toString() {
        return "顶点: " + name.toString() + ", 边: " + edges;
    }
}

