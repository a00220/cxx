package cx.controller;


import com.lly835.bestpay.model.PayResponse;
import cx.dto.OrderDTO;
import cx.enums.RResultEnum;
import cx.exception.SellException;
import cx.service.OrderService;
import cx.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/pay/")
public class PayController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private PayService payService;

	@GetMapping("create")
	public ModelAndView pay(@RequestParam("orderId") String orderId,
							@RequestParam("returnUrl") String returnUrl,
							Map<String, Object> map) {

		OrderDTO orderDTO = orderService.findOne(orderId);
		if (orderDTO == null) {
			throw new SellException(RResultEnum.ORDER_NOT_EXIST);
		}

		PayResponse payResponse = payService.create(orderDTO);
		map.put("payResponse", payResponse);
		map.put("returnUrl", returnUrl);
		return new ModelAndView("pay/create", map);
	}

	@PostMapping("notify")
	public ModelAndView notify(@RequestBody String notifyData) {
		payService.notify(notifyData);

		//返回给微信处理结果
		return new ModelAndView("pay/success");
	}
}
