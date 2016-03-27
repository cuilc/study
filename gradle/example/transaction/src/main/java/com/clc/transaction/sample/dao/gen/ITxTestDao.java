package com.clc.transaction.sample.dao.gen;

import java.sql.SQLException;

public interface ITxTestDao {

   void readCommited() throws SQLException;

   void readRepeated() throws SQLException;

}
