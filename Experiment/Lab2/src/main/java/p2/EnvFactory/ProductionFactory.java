package p2.EnvFactory;

import p2.Interface.EnvironmentFactory;

/**
 * ProductionFactory 是 EnvironmentFactory 的一个具体实现类。
 * <p>
 * 抽象函数 (AF):
 * 表示一个始终返回 true 的环境工厂类，表示所有文件类型都支持。
 * <p>
 * 表示不变性 (RI):
 * - 无特定的不变性要求。
 * <p>
 * 防止表示暴露 (Safety from Rep Exposure):
 * - 没有内部状态，因此不存在表示暴露的问题。
 */
public class ProductionFactory extends EnvironmentFactory {

    @Override
    protected Boolean judgeType(String fileType) {
        return true;
    }
}
