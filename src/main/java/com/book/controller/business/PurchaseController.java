package com.book.controller.business;

import com.book.model.business.OrderBookDO;
import com.book.model.business.PurchaseDO;
import com.book.model.business.ReadersDO;
import com.book.service.business.PurchaseService;
import com.book.utlis.Message;
import com.book.utlis.PageResult;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 采购计划管理控制器
 *
 * @author:YaoShuLi
 * @Date:2019/4/10 0010
 * @Time:14:33
 */
@Api(description = "采购计划管理")
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;


    @ApiOperation("查询采购计划")
    @GetMapping("/findPurchase")
    public PageResult<PurchaseDO> findPurchase(PurchaseDO purchaseDO, Integer rows, Integer page){
        return  purchaseService.findPurchase(purchaseDO,rows,page);
    }

    @ApiOperation("新增采购计划")
    @PostMapping("/addPurchase")
    public Message addPurchase(PurchaseDO purchaseDO, String orderBookStr) throws Exception {

        //格式化Json数据将其转换为对象
        ObjectMapper objectMapper = new ObjectMapper();
        //获取集合类型
        JavaType collectionType = getCollectionType(objectMapper, List.class, OrderBookDO.class);
        //根据json字符串将其转换为集合
        List<OrderBookDO> orderBookDOList =  objectMapper.readValue(orderBookStr, collectionType);

        //执行新增方法
        boolean isSuccess = purchaseService.addPurchase(purchaseDO,orderBookDOList);
        if (isSuccess) {
            return  new Message("200","办理成功");
        }
        return  new Message("500","新增失败!");
    }


    /**
     * 获取泛型的Collection Type
     * @param collectionClass 泛型的Collection
     * @param elementClasses 实体bean
     * @return JavaType Java类型
     */
    private static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);

    }
}
