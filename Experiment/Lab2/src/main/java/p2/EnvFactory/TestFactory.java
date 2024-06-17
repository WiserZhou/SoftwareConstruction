package p2.EnvFactory;

import p2.Interface.EnvironmentFactory;

/**
 * TestFactory 是 EnvironmentFactory 的一个具体实现类，用于测试环境工厂。
 * <p>
 * 抽象函数 (AF):
 * 该类实现了 EnvironmentFactory 的抽象方法，根据文件类型判断是否支持。
 * <p>
 * 表示不变性 (RI):
 * - 无特定的不变性要求。
 * <p>
 * 防止表示暴露 (Safety from Rep Exposure):
 * - 没有内部状态，因此不存在表示暴露的问题。
 */
public class TestFactory extends EnvironmentFactory {

    @Override
    protected Boolean judgeType(String fileType) {
        if (fileType.equals("xml")) {
            return true;
        } else return fileType.equals("json");
    }
}
