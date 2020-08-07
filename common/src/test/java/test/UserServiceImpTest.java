package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import work.chinadream.common.aop.SystemLogAspect;
import work.chinadream.service.admin.ILogService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-service/applicationContext-service.xml"})
public class UserServiceImpTest {
    @Autowired
    private SystemLogAspect service;

    @Test
    public void findUserById() {
        System.out.println(service);
    }

}