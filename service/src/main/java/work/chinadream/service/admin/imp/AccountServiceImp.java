package work.chinadream.service.admin.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.chinadream.dao.admin.AccountMapper;
import work.chinadream.entity.admin.Account;
import work.chinadream.service.admin.IAccountService;

import java.util.List;
import java.util.Map;

/**
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:59
 */
@Service
public class AccountServiceImp extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 添加账户
     *
     * @param account
     * @return
     */
    @Override
    public int addAccount(Account account) {
        return accountMapper.insert(account);
    }

    /**
     * 修改账户
     *
     * @param account
     * @return
     */
    @Override
    public int editAccount(Account account) {

        return accountMapper.updateById(account);
    }

    /**
     * 删除账户
     *
     * @param id
     * @return
     */
    @Override
    public int deleteAccount(Long id) {
        return accountMapper.deleteById(id);
    }

    /**
     * 带条件查询所有
     *
     * @param account
     * @return
     */
    @Override
    public List<Account> findListAccount(Account account) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        if (account.getName() != null) {
            queryWrapper.like("name", account.getName());
        }
        if (account.getStatus() != null) {
            queryWrapper.eq("status", account.getStatus());

        }
        if (account.getRealName() != null) {
            queryWrapper.like("real_name", account.getRealName());
        }
        if (account.getIdCard() != null) {
            queryWrapper.like("id_card", account.getIdCard());
        }
        if (account.getMobile() != null) {
            queryWrapper.like("mobile", account.getMobile());
        }
        return accountMapper.selectList(queryWrapper);
    }


    /**
     * 查询某个账户
     *
     * @param id
     * @return
     */
    @Override
    public Account findAccount(Long id) {


        return accountMapper.selectById(id);
    }

    /**
     * 通过名称查询某个账户
     *
     * @param name
     * @return
     */
    @Override
    public Account findAccountByName(String name) {
        return accountMapper.selectOne(new QueryWrapper<Account>().eq("name", name));
    }

    /**
     * 查询所有账户
     *
     * @return
     */
    @Override
    public List<Account> findAllAccount() {
        return accountMapper.selectList(new QueryWrapper<Account>());
    }


    @Override
    public Account findAccountByQq(Integer id) {
        return accountMapper.selectOne(new QueryWrapper<Account>().eq("qid",id));
    }
}
