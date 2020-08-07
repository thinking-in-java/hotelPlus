package work.chinadream.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.chinadream.dao.QqMapper;
import work.chinadream.dao.admin.AccountMapper;
import work.chinadream.entity.admin.Account;
import work.chinadream.entity.admin.QqAccount;
import work.chinadream.service.IQqService;


@Service
public class QqServiceImpl extends ServiceImpl<QqMapper, QqAccount> implements IQqService {
	@Autowired
	private QqMapper userDao;

	@Override
	public QqAccount selectQq(String qqId) {
		return userDao.selectOne(new QueryWrapper<QqAccount>().eq("openid",qqId));
	}

	@Override
	public Integer insertQq(QqAccount qq) {
		return userDao.insert(qq);
	}

	@Override
	public Integer updateQq(QqAccount qq) {
		return userDao.updateById(qq);
	}
}
