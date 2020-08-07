package work.chinadream.service.admin.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.chinadream.dao.admin.BookOrderMapper;
import work.chinadream.entity.admin.BookOrder;
import work.chinadream.service.admin.IBookOrderService;

import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 21:28
 */
@Service
public class BookOrderServiceImp extends ServiceImpl<BookOrderMapper, BookOrder> implements IBookOrderService {
    @Autowired
    private BookOrderMapper bookOrderMapper;

    /**
     * 添加
     * @param bookOrder
     * @return
     */
    @Override
    public int addBookOrder(BookOrder bookOrder) {
        return bookOrderMapper.insert(bookOrder);
    }

    /**
     * 修改
     * @param bookOrder
     * @return
     */
    @Override
    public int editBookOrder(BookOrder bookOrder) {

        return bookOrderMapper.updateById(bookOrder);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteBookOrder(Long id)
    {
        return bookOrderMapper.deleteById(id);
    }

    /**
     * 待条件查询
     * @param bookOrder
     * @return
     */
    @Override
    public List<BookOrder> findBookOrderList(BookOrder bookOrder) {
        QueryWrapper<BookOrder> queryWrapper = new QueryWrapper<>();
        if (bookOrder.getName()!=null){
            queryWrapper.like("name",bookOrder.getName());
        }
        if(bookOrder.getStatus()!=null){
            queryWrapper.eq("status",bookOrder.getStatus());
        }
        if(bookOrder.getPayStatus()!=null){
            queryWrapper.eq("pay_status",bookOrder.getPayStatus());
        }
        if(bookOrder.getAccountId()!=null){
            queryWrapper.eq("account_id",bookOrder.getAccountId());
        }
        if(bookOrder.getRoomTypeId()!=null){
            queryWrapper.eq("room_type_id",bookOrder.getRoomTypeId());
        }
        if(bookOrder.getIdCard()!=null){
            queryWrapper.like("id_card",bookOrder.getIdCard());
        }
        if(bookOrder.getMobile()!=null){
            queryWrapper.like("mobile",bookOrder.getMobile());
        }
        return bookOrderMapper.selectList(queryWrapper);
    }



    /**
     * 根据id查询预定
     * @param id
     * @return
     */
    @Override
    public BookOrder findBookOrder(Long id) {
        return bookOrderMapper.selectById(id);
    }
}
