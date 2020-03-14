package cx.service.impl;

import cx.dto.OrderDTO;
import cx.entity.OrderDetail;
import cx.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)

public class OrderServiceImplTest {


    @Autowired
    private OrderService orderService;

    public static final  String BUYER_OPENID="12222";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("心心新");
        orderDTO.setBuyerAddress("柏埔");
        orderDTO.setBuyerPhone("1313797979");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("");
        orderDetail.setProductQuantity(2);
        orderDetailList.add(orderDetail);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("");
        orderDetail2.setProductQuantity(2);
        orderDetailList.add(orderDetail);
    }
}