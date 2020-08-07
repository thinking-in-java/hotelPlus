package work.chinadream.service.admin.imp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import work.chinadream.service.admin.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-service/applicationContext-service.xml"})
public class UserServiceImpTest {
    @Autowired
    private ICheckinService service;

    @Test
    public void findUserById() {

        System.out.println(service.findCheckin(13L));
    }

}