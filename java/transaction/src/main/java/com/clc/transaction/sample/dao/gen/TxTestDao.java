package com.clc.transaction.sample.dao.gen;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.clc.transaction.sample.dao.po.gen.TxExample;

@Service 
//@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TxTestDao implements ITxTestDao {
    protected static final Logger logger = LoggerFactory.getLogger(TxTestDao.class);
    
    @Resource
    private TxDAO TxDAO;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void readCommited() throws SQLException {
       new Thread(new Runnable(){
          @Override
          public void run() {
             int n = 0;
             while(n==0) {
                try {
                   logger.info("==select readCommited begin: {}", n);
                   TxExample example = new TxExample();
                   n = TxDAO.countByExample(example);
                   logger.info("==select readCommited end: {}", n);
                } catch (Exception e) {
                   logger.error("", e);
                }
             }
          }}).start();
    }
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void readRepeated() throws SQLException {
       new Thread(new Runnable(){
          @Override
          public void run() {
             int n = 0;
             while(n==0) {
                try {
                   logger.info("==select readRepeated begin: {}", n);
                   TxExample example = new TxExample();
                   n = TxDAO.countByExample(example);
                   logger.info("==selectreadRepeated  end: {}", n);
                } catch (Exception e) {
                   logger.error("", e);
                }
             }
          }}).start();
    }

}