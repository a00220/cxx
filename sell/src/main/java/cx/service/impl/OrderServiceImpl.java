package cx.service.impl;

import com.google.common.collect.Lists;
import cx.VO.ProductVO;
import cx.dao.OrderDetailDao;
import cx.dao.OrderMasterDao;
import cx.dto.CartDTO;
import cx.dto.OrderDTO;
import cx.entity.OrderDetail;
import cx.entity.OrderMaster;
import cx.entity.ProductInfo;
import cx.enums.OrderStatusEnum;
import cx.enums.PayStatusEnum;
import cx.enums.RResultEnum;
import cx.exception.SellException;
import cx.service.OrderService;
import cx.service.ProductService;
import cx.utils.KeyUtil;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        BigDecimal count = new BigDecimal("0");
        List<ProductInfo> productInfoList = new ArrayList<>();
        //查询商品
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getOrderId());
            if (productInfo == null) {
                throw new SellException(RResultEnum.PRODUCT_NOT_EXIST);
            }
            productInfoList.add(productInfo);
            //计算总价
            count = count.add(productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));
            //写入订单数据
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(KeyUtil.genUniqueKey());
            orderDetailDao.save(orderDetail);
        }
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(count);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMasterDao.save(orderMaster);
        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }
}
