package work.chinadream.service.admin;


import com.baomidou.mybatisplus.extension.service.IService;
import work.chinadream.entity.admin.BookOrder;

import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 21:28
 */
public interface IBookOrderService extends IService<BookOrder> {
    /**
     * 添加预定
     * @param bookOrder
     * @return
     */
    public int addBookOrder(BookOrder bookOrder);

    /**
     * 修改预定
     * @param bookOrder
     * @return
     */
    public int editBookOrder(BookOrder bookOrder);

    /**
     * 删除预定
     * @param id
     * @return
     */
    public int deleteBookOrder(Long id);

    /**
     * 查询所有预定
     * @param queryMap
     * @return
     */
    public List<BookOrder> findBookOrderList(BookOrder bookOrder);

    /**
     * 查询某个预定
     * @param id
     * @return
     */
    public BookOrder findBookOrder(Long id);
}
