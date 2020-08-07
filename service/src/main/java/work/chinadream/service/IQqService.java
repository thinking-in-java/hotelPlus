package work.chinadream.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.chinadream.entity.admin.QqAccount;


public interface IQqService  extends IService<QqAccount> {

	public QqAccount selectQq(String qqId);
	public Integer insertQq(QqAccount qq);
	public Integer updateQq(QqAccount qq);
}
