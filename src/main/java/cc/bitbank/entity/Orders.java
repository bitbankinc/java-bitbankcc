package cc.bitbank.entity;

/**
 * Created by tanaka on 2017/04/12.
 */
public class Orders extends Data {
    public Order[] orders;

    public Orders() {}
    public Orders(Order[] orders) {
        this.orders = orders;
    }
}
