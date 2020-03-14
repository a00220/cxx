package cx.controller;

import cx.VO.ResultVO;
import cx.enums.RResultEnum;
import cx.exception.SellException;
import cx.from.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RequestMapping
@RestController("/buyer/order/")
@Slf4j
public class BuyerOrderController {


    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确,orderForm={}", orderForm);
            throw new SellException(RResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }




    }


}
