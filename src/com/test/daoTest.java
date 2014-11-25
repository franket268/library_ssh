package com.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.UserDao;

import junit.framework.TestCase;

public class daoTest extends TestCase {
    public  void test1(){
    	  BeanFactory factory=new ClassPathXmlApplicationContext("applicationContext-*.xml");
          UserDao  userDao=(UserDao) factory.getBean("UserDao");
          userDao.login("asd", "123");
          
    }

}
