package poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import graph.ConcreteEdgesGraph;
import graph.Graph;

/**
 * A graph-based poetry generator.
 *
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 *
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 *
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 *
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 *
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY sengthen the specifications
 *  * and you MAY add additional methods.tr
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    private final Graph<String> graph = new ConcreteEdgesGraph<>();

    // Abstraction function:
    // 是一个利用加权有向图寻找两点中间有无通路的方法，到实际给一句话添加中间词的问题的映射

    // Representation invariant:
    // 新加入的词必须是两点中间的bridge，weight必须大于0

    // 必要时使用防御性拷贝
    // Safety from rep exposure:
    // 设置graph为private final

    /**
     * 使用给定的语料库文本文件创建一个新的诗人，并生成诗人的关联图。
     *
     * @param corpus 用于构建诗人亲和图的文本文件
     * @throws IOException 如果无法找到或读取语料库文件
     */
    public GraphPoet(File corpus) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(corpus));
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        String string;
        // 读取语料库文件并处理
        while ((string = in.readLine()) != null) {
            list.addAll(Arrays.asList(string.split(" ")));
        }
        in.close();
        // 构建关联图
        for (int i = 0; i < list.size() - 1; i++) {
            String source = list.get(i).toLowerCase();
            String target = list.get(i + 1).toLowerCase();
            int preweight = 0;
            if (map.containsKey(source + target)) {
                preweight = map.get(source + target);
            }
            map.put(source + target, preweight + 1);
            graph.set(source, target, preweight + 1);
        }
        checkRep();
    }

    /**
     * 检查诗人的亲和图是否符合表示不变式。
     */
    private void checkRep() {
        for (String source : graph.vertices()) {
            for (String target : graph.targets(source).keySet()) {
                assert graph.targets(source).get(target) > 0 : "边的权重必须大于0";
            }
        }
    }

    /**
     * 生成一首诗。
     *
     * @param input 用于创建诗的字符串
     * @return 诗（如上所述）
     */
    public String poem(String input) {
        StringBuilder SB = new StringBuilder();
        List<String> list = new ArrayList<>(Arrays.asList(input.split(" ")));
        Map<String, Integer> sourceMap;
        Map<String, Integer> targetMap;
        // 从输入字符串生成诗
        for (int i = 0; i < list.size() - 1; i++) {
            SB.append(list.get(i)).append(" ");
            String sourceString = list.get(i).toLowerCase();
            String targetString = list.get(i + 1).toLowerCase();
            targetMap = graph.targets(sourceString);
            sourceMap = graph.sources(targetString);
            int maxWeight = 0;
            String bridgeWord = "";
            // 寻找连接词
            for (String string : targetMap.keySet()) {
                if (sourceMap.containsKey(string) && sourceMap.get(string) + targetMap.get(string) > maxWeight) {
                    maxWeight = sourceMap.get(string) + targetMap.get(string);
                    bridgeWord = string;
                }
            }
            if (maxWeight > 0) {
                SB.append(bridgeWord).append(" ");
            }
        }
        SB.append(list.getLast());
        return SB.toString();
    }

    @Override
    public String toString() {
        return graph.toString();
    }

}
