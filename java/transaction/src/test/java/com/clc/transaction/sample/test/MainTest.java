package com.clc.transaction.sample.test;

import java.sql.SQLException;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.clc.transaction.sample.dao.gen.ITxTestDao;
import com.clc.transaction.sample.dao.gen.TxDAO;
import com.clc.transaction.sample.dao.po.gen.Tx;
import com.clc.transaction.sample.dao.po.gen.TxExample;

@ContextConfiguration(locations = { "classpath*:spring/clc-min-ctx.xml"})
public class MainTest extends AbstractJUnit4SpringContextTests {
   private static final Logger logger = LoggerFactory.getLogger(MainTest.class);

   @Resource
   private TxDAO TxDAO;
   @Resource
   private ITxTestDao txTestDao;
   
   @Test
   public void testReadCommitTx(){
      try {
         //remove all data firstly
         TxDAO.deleteByExample(new TxExample());
      } catch (SQLException e2) {
         logger.error("", e2);
         return;
      }
      
      new Thread(new Runnable(){
         @Override
         public void run() {
            try {
               txTestDao.readCommited();
            } catch (SQLException e1) {
               logger.error("", e1);
            }
         }
      }).start();
      
      new Thread(new Runnable(){
         @Override
         public void run() {
            try {
               Tx record = new Tx();
               record.setId("1");
               record.setUsername("A");
               TxDAO.insert(record);
               logger.info("===insert=======record:{}", record);
            } catch (Exception e) {
               logger.error("", e);
            }
         }}).start();
      try {
         Thread.currentThread().join();
      } catch (InterruptedException e) {
      }
   }
   
   @Test
   public void testReadRepeatedTx(){
      try {
         //remove all data firstly
         TxDAO.deleteByExample(new TxExample());
      } catch (SQLException e2) {
         logger.error("", e2);
         return;
      }
      
      new Thread(new Runnable(){
         @Override
         public void run() {
            try {
               txTestDao.readRepeated();
            } catch (SQLException e1) {
               logger.error("", e1);
            }
         }
      }).start();
      
      new Thread(new Runnable(){
         @Override
         public void run() {
            try {
               Tx record = new Tx();
               record.setId("1");
               record.setUsername("A");
               TxDAO.insert(record);
               logger.info("===insert=======record:{}", record);
            } catch (Exception e) {
               logger.error("", e);
            }
         }}).start();
      try {
         Thread.currentThread().join();
      } catch (InterruptedException e) {
      }
   }
}