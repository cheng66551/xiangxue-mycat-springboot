package com.xiangxue.jack.test;

import com.alibaba.fastjson.JSONObject;
import com.xiangxue.jack.MycatApplication;
import com.xiangxue.jack.bean.*;
import com.xiangxue.jack.dao.CommonMapper;
import com.xiangxue.jack.util.SnowflakeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MycatApplication.class)
public class MyTest {

    @Autowired
    private CommonMapper commonMapper;

    @Test
    public void test1() {
        ConsultConfigArea area = new ConsultConfigArea();
        area.setAreaCode("jack");
        area.setAreaName("jack");
        area.setState(1);
        commonMapper.addArea(area);
    }

    @Test
    public void test2() {
        List<ConsultConfigArea> areas = commonMapper.qryArea();
        System.out.println(JSONObject.toJSONString(areas));
    }

    @Test
    public void test3() {
        ZgGoods zgGoods = new ZgGoods();
        zgGoods.setGoodCode("Jack");
        zgGoods.setGoodName("Jack");
        zgGoods.setCount(100);
        commonMapper.addGood(zgGoods);
    }

    @Test
    public void test4() {
        List<ZgGoods> zgGoods = commonMapper.queryAll();
        System.out.println(JSONObject.toJSONString(zgGoods));
    }

    @Test
    public void test5() {
        for (int i = 101; i < 1000; i++) {
            Tb_user user = new Tb_user();
            user.setUser_name("Jack" + i);
            commonMapper.addUser(user);
        }
    }

    /*@Test
    public void test6() {
        for (int i = 10000; i < 20000; i++) {
            T_order order = new T_order();
            order.setOrder_content("Jack" + i);
            commonMapper.addTOrder(order);
        }
    }*/

    /*
    * 增加的业务的复杂度
    * */
    @Test
    public void test6() {
        for (int i = 0; i < 1000; i++) {
            long id = SnowflakeUtil.nextId();
            //路由
            long l = id % 3;
            Tb_user user = new Tb_user();
            user.setUser_id(id);
            user.setUser_name("James" + i);
            user.setSeq((int)l + 1);
            commonMapper.addT_user(user);
        }
    }

    @Test
    public void test7() {
        for (int i = 0; i < 1000; i++) {
            Long id = SnowflakeUtil.nextId();
            Tb_user user = new Tb_user();
            user.setUser_id(id);
            user.setUser_name("Peter" + i);
            //有侵入性。。进行路由数据的配置
            RouteBean routeBean = new RouteBean();
            routeBean.setPrimaryId(id);
            routeBean.setTableCount(3);
            user.setRouteBean(routeBean);
//            user.setRoute(true);
            commonMapper.addT_user(user);
        }
    }

    @Test
    public void test8() {

        RouteBean routeBean = new RouteBean();
        routeBean.setTableCount(3);
//        routeBean.setPrimaryId(713155063818752000L);
        Tb_user user = new Tb_user();
//        user.setUser_id(713155063818752000L);
        user.setUser_name("King889");
        user.setRouteBean(routeBean);

        List<Tb_user> user1 = commonMapper.queryUser(user);
        System.out.println(JSONObject.toJSONString(user1));
    }

    /*
    *
    * */
    @Test
    public void test9() {
        for (int i = 0; i <= 1000; i++) {
            Long id = SnowflakeUtil.nextId();
            T_order order = new T_order();
            order.setOrderId(id);
            order.setOrderName("Jack" + i);
            commonMapper.addTt_order_murmur_hash(order);
        }

        for (int i = 1001; i <= 2000; i++) {
            Long id = SnowflakeUtil.nextId();
            T_order order = new T_order();
            order.setOrderId(id);
            order.setOrderName("Deer" + i);
            commonMapper.addTt_order_murmur_hash(order);
        }
    }

    @Test
    public void test10() {
        T_order order = new T_order();
//        order.setOrderId(1011L);
        order.setOrderName("Deer1012");
        JSONObject.toJSONString(commonMapper.queryTOrder(order));
    }

    @Test
    public void test11() {
        T_order order = new T_order();
        Long id = SnowflakeUtil.nextId();
        order.setOrderId(id);
        order.setOrderName("Deer1012");
        order.setCreateTime(new Timestamp(new Date("2020/07/17").getTime()));
        commonMapper.addTt_order_time_day(order);
    }
}
