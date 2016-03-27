package com.clc.transaction.sample.dao.gen;

import com.clc.transaction.sample.dao.po.gen.Tx;
import com.clc.transaction.sample.dao.po.gen.TxExample;
import java.sql.SQLException;
import java.util.List;

public interface TxDAO {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(TxExample example) throws SQLException;

    /**
     * 根据条件删除记录
     */
    int deleteByExample(TxExample example) throws SQLException;

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String id) throws SQLException;

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    void insert(Tx record) throws SQLException;

    /**
     * 保存属性不为空的记录
     */
    void insertSelective(Tx record) throws SQLException;

    /**
     * 根据条件查询记录集
     */
    List<Tx> selectByExample(TxExample example) throws SQLException;

    /**
     * 根据主键查询记录
     */
    Tx selectByPrimaryKey(String id) throws SQLException;

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(Tx record, TxExample example) throws SQLException;

    /**
     * 根据条件更新记录
     */
    int updateByExample(Tx record, TxExample example) throws SQLException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Tx record) throws SQLException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Tx record) throws SQLException;
}