package cx.dao;

import cx.entity.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoDaoTest {

    @Autowired
    ProductInfoDao productInfoDao;

    @Test
    public void find() {
        List<ProductInfo> productInfoList = productInfoDao.findByProductStatus(0);
        System.out.println(productInfoList);
    }
}