package com.book.controller.business;

import com.book.enums.business.ReadersStateEnum;
import com.book.model.business.ReadersDO;
import com.book.service.business.ReadersService;
import com.book.utlis.Message;
import com.book.utlis.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读者卡管理控制器
 * 用于读者的新增、修改、查询、删除操作！
 * @author:YaoShuLi
 * @Date:2019/4/1 0001
 * @Time:18:47
 */
@Api(description ="读者卡信息管理控制器")
@RestController
@RequestMapping("/readers")
public class ReadersController {

    @Autowired
    private ReadersService readersService;


    @ApiOperation("新增读者卡信息")
    @PostMapping("/addReaders")
    public Message addReaders(ReadersDO readersDO){

        boolean isSuccess = readersService.addReaders(readersDO);
        if (isSuccess) {
            return  new Message("200","办理成功");
        }
        return  new Message("500","办理失败");
    }


    @ApiOperation("分页查询可用读者卡名单")
    @GetMapping("/findReaders")
    public PageResult<ReadersDO> findReaders(ReadersDO readersDO, Integer rows, Integer page){
        //只查询可用读者卡
        readersDO.setReadersState(ReadersStateEnum.AVAILABLE);
        return readersService.findReaders(readersDO,rows,page);
    }

    @ApiOperation("分页查询读者黑名单")
    @GetMapping("/findReadersBlackList")
    public PageResult<ReadersDO> findReadersBlackList(ReadersDO readersDO, Integer rows, Integer page){
        //只查询读者卡黑名单
        readersDO.setReadersState(ReadersStateEnum.BLACKLIST);
        return readersService.findReaders(readersDO,rows,page);
    }

    @ApiOperation("根据id删除对应的读者卡信息")
    @PostMapping("/removeReaders")
    public Message removeReaders(String id){
        boolean isSuccess = readersService.removeReaders(id);
        if (isSuccess) {
            return  new Message("200","删除成功！");
        }
        return  new Message("500","删除失败！");
    }

    @ApiOperation("根据id将读者加入黑名单")
    @PostMapping("/addBlackList")
    public Message addBlackList(ReadersDO readersDO){
        boolean isSuccess = readersService.addBlackList(readersDO);
        if (isSuccess) {
            return  new Message("200","拉黑成功！");
        }
        return  new Message("500","拉黑失败！");
    }

    @ApiOperation("根据id将读者移出黑名单")
    @PostMapping("/moveBlackList")
    public Message moveBlackList(String id){
        boolean isSuccess = readersService.moveBlackList(id);
        if (isSuccess) {
            return  new Message("200","移出成功！");
        }
        return  new Message("500","移出失败！");
    }

}
