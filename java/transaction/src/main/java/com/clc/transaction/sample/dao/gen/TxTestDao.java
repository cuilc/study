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

@Service 
public class TxTestDao implements ITxTestDao {
    protected static final Logger logger = LoggerFactory.getLogger(TxTestDao.class);
    
    @Resource
    private TxDAO TxDAO;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void readCommited() throws SQLException {
             int n = 0;
               while(true){ 
                try {
                   logger.info("==select readCommited begin: current size: {}", n);
                   TxExample example = new TxExample();
                   n = TxDAO.countByExample(example);
                   List<Tx> list = TxDAO.selectByExample(example);
                   if(list != null && list.size() >0) {
                      logger.info("==select readCommited  end: {}", list.get(0).getUsername());
                   } else {
                      logger.info("==select readCommited  end: {}", n);
                   }
                   Thread.sleep(1000);
                } catch (Exception e) {
                   logger.error("", e);
                }
             }
    }
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void readRepeated() throws SQLException {
             int n = 0;
             while(true){
                try {
                   logger.info("==select readRepeated begin: current size: {}", n);
                   TxExample example = new TxExample();
                   n = TxDAO.countByExample(example);
                   List<Tx> list = TxDAO.selectByExample(example);
                   if(list != null && list.size() >0) {
                      logger.info("==select readRepeated  end: {}", list.get(0).getUsername());
                   } else {
                      logger.info("==select readRepeated  end: {}", n);
                   }
                   Thread.sleep(1000);
                } catch (Exception e) {
                   logger.error("", e);
                }
             }
    }

}