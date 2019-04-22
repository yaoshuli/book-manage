package com.book.service.business.impl;

import com.book.enums.base.SexEnum;
import com.book.enums.business.ReadersStateEnum;
import com.book.mapper.business.ReadersMapper;
import com.book.model.business.ReadersDO;
import com.book.service.business.ReadersService;
import com.book.utlis.IdcardUtils;
import com.book.utlis.PageResult;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 读者信息管理服务接口实现类
 *
 * @author:YaoShuLi
 * @Date:2019/4/1 0001
 * @Time:19:01
 */
@Service
public class ReadersServiceImpl implements ReadersService {

    @Autowired(required = false)
    private ReadersMapper readersMapper;

    @Override
    public boolean addReaders(ReadersDO readersDO) {

        //设置数据创建时间
        readersDO.setGmtCreate(LocalDate.now());

        //设置读者卡状态默认可用
        readersDO.setReadersState(ReadersStateEnum.AVAILABLE);

        //设置读者卡性别，根据身份证计算出
        String gender = IdcardUtils.getGenderByIdCard(readersDO.getIdCard());
        if(gender.equals("M")){
            //男
            readersDO.setReadersSex(SexEnum.MALE);
        }else if(gender.equals("F")){
            //女
            readersDO.setReadersSex(SexEnum.FEMALE);
        }else{
            //未知
            readersDO.setReadersSex(SexEnum.UNKNOWN);
        }

        return readersMapper.insert(readersDO) >0 ? true:false;
    }

    @Override
    public PageResult<ReadersDO> findReaders(ReadersDO readersDO, Integer rows, Integer page) {
        //获取总数
       int count =  readersMapper.selectCount(readersDO);
        //使用PageHelper进行分页处理
        Page<ReadersDO> pages= PageHelper.startPage(page, rows);
        List<ReadersDO> readersDOS = pages.doSelectPage(new ISelect() {
            @Override
            public void doSelect() {
                readersMapper.select(readersDO);
            }
        });
        return new PageResult<ReadersDO>(count,readersDOS);
    }

    @Override
    public boolean removeReaders(String id) {
        return readersMapper.deleteByPrimaryKey(id)>0?true:false;
    }

    @Override
    public boolean moveBlackList(String id) {
        //定义修改对象
        ReadersDO readersDO = new ReadersDO();
        //设置id 根据id修改
        readersDO.setId(id);
        //设置状态为可用
        readersDO.setReadersState(ReadersStateEnum.AVAILABLE);

        return readersMapper.updateByPrimaryKeySelective(readersDO) >0 ? true : false;
    }

    @Override
    public boolean addBlackList(ReadersDO readersDO) {

        //设置状态为可用
        readersDO.setReadersState(ReadersStateEnum.BLACKLIST);

        return readersMapper.updateByPrimaryKeySelective(readersDO) >0 ? true : false;
    }
}
