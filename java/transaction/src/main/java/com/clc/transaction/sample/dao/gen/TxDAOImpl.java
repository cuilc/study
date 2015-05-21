package com.clc.transaction.sample.dao.gen;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.clc.transaction.sample.dao.po.gen.Tx;
import com.clc.transaction.sample.dao.po.gen.TxExample;
import com.ibatis.sqlmap.client.SqlMapClient;

@Service 
public class TxDAOImpl implements TxDAO {
    protected static final Logger LOGGER = LoggerFactory.getLogger(TxDAOImpl.class);
    
    @Resource(name = "clc-sqlMapClient")
    private SqlMapClient sqlMapClient;

    public TxDAOImpl() {
        super();
    }

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }

    public int countByExample(TxExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("tops_clc_tx.countByExample", example);
        return count;
    }

    public int deleteByExample(TxExample example) throws SQLException {
        int rows = sqlMapClient.delete("tops_clc_tx.deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(String id) throws SQLException {
        Tx key = new Tx();
        key.setId(id);
        int rows = sqlMapClient.delete("tops_clc_tx.deleteByPrimaryKey", key);
        return rows;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void insert(Tx record) throws SQLException {
        sqlMapClient.insert("tops_clc_tx.insert", record);
    }

    public void insertSelective(Tx record) throws SQLException {
        sqlMapClient.insert("tops_clc_tx.insertSelective", record);
    }

    @SuppressWarnings("unchecked")
    public List<Tx> selectByExample(TxExample example) throws SQLException {
        List<Tx> list = sqlMapClient.queryForList("tops_clc_tx.selectByExample", example);
        return list;
    }

    public Tx selectByPrimaryKey(String id) throws SQLException {
        Tx key = new Tx();
        key.setId(id);
        Tx record = (Tx) sqlMapClient.queryForObject("tops_clc_tx.selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(Tx record, TxExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("tops_clc_tx.updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(Tx record, TxExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("tops_clc_tx.updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(Tx record) throws SQLException {
        int rows = sqlMapClient.update("tops_clc_tx.updateByPrimaryKeySelective", record);
        return rows;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public int updateByPrimaryKey(Tx record) throws SQLException {
        int rows = sqlMapClient.update("tops_clc_tx.updateByPrimaryKey", record);
        return rows;
    }

    protected static class UpdateByExampleParms extends TxExample {
        private Object record;

        public UpdateByExampleParms(Object record, TxExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}