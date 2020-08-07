package work.chinadream.web.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/5/26 22:15
 */
public class AppPath implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        //将路径保存到Application范围中
        ServletContext context = sce.getServletContext();
        String contextPath = context.getContextPath();
        context.setAttribute("App_Path",contextPath);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
