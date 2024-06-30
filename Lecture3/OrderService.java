package Lecture3;

import java.util.HashMap;
import java.util.Map;

public class OrderService {
    public double discount(Order order) {
        OrderType type = order.getType();
        DiscountStrategy discountStrategy = DiscountStrategyFactory.getDiscountStrategy(type);
        return discountStrategy.calDiscount(order);
    }
}

class DiscountStrategyFactory {
    private static final Map<OrderType, DiscountStrategy> strategies = new HashMap<>();

//    static {
//        strategies.put(OrderType.NORMAL, new NormalDiscountStrategy());
//        strategies.put(OrderType.GROUPON, new GrouponDiscountStrategy());
//        strategies.put(OrderType.PROMOTION, new PromotionDiscountStrategy());
//    }

    public static DiscountStrategy getDiscountStrategy(OrderType type) {
        return strategies.get(type);
    }
}

class GrouponDiscountStrategy implements DiscountStrategy {
    @Override
    public double calDiscount(Order order) {


        // 打折算法，或者直接从规则引擎中获取
        return 0.7;
    }
}

interface DiscountStrategy {
//    DiscountStrategyFactory discountStrategyFactory = null;

    double calDiscount(Order order);
}

class NormalDiscountStrategy implements DiscountStrategy {
    @Override
    public double calDiscount(Order order) {
        // 打折算法，或者直接从规则引擎中获取
        return 0.9;
    }
}

class PromotionDiscountStrategy implements DiscountStrategy {
    @Override
    public double calDiscount(Order order) {
        // 打折算法，或者直接从规则引擎中获取
        return 0.5;
    }
}


enum OrderType {
    NORMAL,
    GROUPON,
    PROMOTION
}

class Order {
    private OrderType type;

    public Order(OrderType type) {
        this.type = type;
    }

    public OrderType getType() {
        return type;
    }
}

