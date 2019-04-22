package com.book.service.business;

import com.book.model.business.ReadersDO;
import com.book.utlis.PageResult;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 读者信息管理服务接口
 *
 * @author:YaoShuLi
 * @Date:2019/4/1 0001
 * @Time:19:00
 */
public interface ReadersService {

    /**
     * 创建新的读者卡信息
     * @param readersDO 读者信息
     * @return  办理结果
     */
    boolean addReaders(ReadersDO readersDO);

    /**
     * 分页查询所有读者卡信息
     * @param readersDO 查询参数及分页参数
     * @return  结果
     */
    PageResult<ReadersDO> findReaders(ReadersDO readersDO, Integer rows, Integer page);

    /**
     * 根据id删除读者卡信息
     * @param id    读者卡id
     * @return  结果
     */
    boolean removeReaders(String id);

    /**
     * 根据id将读者卡移出黑名单
     * @param id    读者卡id
     * @return  结果
     */
    boolean moveBlackList(String id);

    /**
     * 根据id将读者卡加入黑名单
     * @param readersDO    新增黑名单对象
     * @return  结果
     */
    boolean addBlackList(ReadersDO readersDO);
}
