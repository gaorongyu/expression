package com.fngry.expression.execute;

import com.fngry.expression.ExpressionRunner;
import com.fngry.expression.common.OrderInfo;
import com.fngry.expression.execute.IExecutor;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ExpressionTest {

    IExecutor executor = ExpressionRunner.getDefaultExecutor();

    @Test
    public void testExpressionMatchTrue() throws Exception {
        Map<String, Object> context = new HashMap<String, Object>();
        context.put("orderId", "123");
        context.put("orderType", "1");

        String expression = "orderId == '123' && orderType == '1'";

        for (int i = 0; i < 2; i++) {
            long start = System.nanoTime();
            Object obj = executor.execute(expression, context);
            long end = System.nanoTime();
            System.out.println(" match use time: " + (end - start));

            System.out.println(" match result is: " + obj);
            Assert.assertEquals(true, obj);
        }
    }

    @Test
    public void testExpressionMatchFalse() throws Exception {
        Map<String, Object> context = new HashMap<String, Object>();
        context.put("orderType", "1");

        String expression = " orderType == 'abc'";

        Object obj = executor.execute(expression, context);
        System.out.println(" match result is: " + obj);
        Assert.assertEquals(false, obj);
    }

    @Test
    public void testExpressionInvokeMethod() throws Exception {
        Map<String, Object> context = new HashMap<String, Object>();
        OrderInfo orderInfo = new OrderInfo();
        String orderSn = "17121600001";
        orderInfo.setOrderSn(orderSn);

        context.put("order", orderInfo);

        Object obj = executor.execute("order.getOrderSn()", context);
        System.out.println(" execute getOrderSn(): " + obj);
        Assert.assertEquals(orderSn, obj);
    }

    @Test
    public void testExpressionNoContext() throws Exception {
        Object obj = executor.execute(" 1 + 3 *2");
        System.out.println(" match result is: " + obj);
        Assert.assertEquals(7, obj);
    }

}
