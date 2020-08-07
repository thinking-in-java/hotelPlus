package work.chinadream.service.admin;


import com.baomidou.mybatisplus.extension.service.IService;
import work.chinadream.entity.admin.Account;

import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:58
 */
public interface IAccountService extends IService<Account> {
    /**
     * 添加
     * @param account
     * @return
     */
    public int addAccount(Account account);

    /**
     * 修改
     * @param account
     * @return
     */
    public int editAccount(Account account);

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteAccount(Long id);

    /**
     * 待条件查询
     * @param account
     * @return
     */
    public List<Account> findListAccount(Account account);


    /**
     * 查询某个
     * @param id
     * @return
     */
    public Account findAccount(Long id);

    /**
     * 通过名称查询
     * @param name
     * @return
     */
    public Account findAccountByName(String name);

    /**
     * 查询所有
     * @return
     */
    public List<Account> findAllAccount();

    /**
     * 通过qqid查询有无该用户
     * @param id
     * @return
     */
    public Account findAccountByQq(Integer id);
}
