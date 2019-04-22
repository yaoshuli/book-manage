package com.book.utlis;

import lombok.Data;

import java.util.List;

/**
 * 用于返回分页数据格式
 *
 * @author:YaoShuLi
 * @Date:2019/4/2 0002
 * @Time:12:00
 */
@Data
public class PageResult<T>{

    //总条数
    private int total;

    //结果集
    private List<T> rows;

    public PageResult(int total,List<T> list){
        this.total =total;
        this.rows = list;
    }

    public PageResult(){

    }

}
