package com.clc.transaction.sample.test;

import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.clc.transaction.sample.dao.gen.ITxTestDao;
import com.clc.transaction.sample.dao.gen.TxDAO;
import com.clc.transaction.sample.dao.po.gen.Tx;
import com.clc.transaction.sample.dao.po.gen.TxExample;

@ContextConfiguration(locations = { "classpath*:spring/tx-min-ctx.xml"})
public class MainTest extends AbstractJUnit4SpringContextTests {
   private static final Logger logger = LoggerFactory.getLogger(MainTest.class);

   @Resource
   private TxDAO TxDAO;
   @Resource
   private ITxTestDao txTestDao;
   
   @Before
   public void testInit(){
      try {
       //remove all data firstly
         TxExample example = new TxExample();
         int total = TxDAO.countByExample(example);
         logger.info("remove old test data, size: {}", total);
         TxDAO.deleteByExample(example);
         total = TxDAO.countByExample(example);
         logger.info("remove old test data completed, current: {}", total);
         Tx record = new Tx();
         record.setId("1");
         record.setUsername("A");
         record.setLastLogonTime(new Date());
         logger.info("===insert=======record begin:{}", record);
         TxDAO.insert(record);
         logger.info("===insert=======record end:{}", record);
      } catch (SQLException e2) {
         logger.error("", e2);
         return;
      }
   }
   
   @Test
   public void testTransaction(){
      //可重复读事务
      Thread readRepeatedThread = new Thread(new Runnable(){
         @Override
         public void run() {
            try {
               txTestDao.readRepeated();
            } catch (SQLException e1) {
               logger.error("", e1);
            }
         }
      });
      
    //读已提交事务
      Thread readCommitedThread = new Thread(new Runnable(){
         @Override
         public void run() {
            try {
               txTestDao.readCommited();
            } catch (SQLException e1) {
               logger.error("", e1);
            }
         }
      });
      
    //更新事务
      Thread updateThread = new Thread(new Runnable(){
         @Override
         public void run() {
            try {
               Tx record = new Tx();
               record.setId("1");
               record.setUsername("F");
               record.setLastLogonTime(new Date());
               logger.info("===insert=======record begin:{}", record);
               TxDAO.updateByPrimaryKey(record);
               logger.info("===insert=======record end:{}", record);
            } catch (Exception e) {
               logger.error("", e);
            }
         }});
      
      try {
         readRepeatedThread.start();
         readCommitedThread.start();
         updateThread.start();
         readRepeatedThread.join();
         readCommitedThread.join();
         updateThread.join();
      } catch (InterruptedException e) {
      }
   }
}