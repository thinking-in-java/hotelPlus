package work.chinadream.web.controller.home;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import work.chinadream.entity.admin.BookOrder;
import work.chinadream.service.admin.IBookOrderService;
import work.chinadream.util.AlipayConfig;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Author: James Ben
 * Email: thinking_in_java@126.com
 * ToDo: Sail against the current
 * Create Time:2020/7/22 17:26
 */
@Controller
@RequestMapping("/orderPay")
public class PayController {
  @Autowired
  private IBookOrderService bookOrderService;
  /*跳转支付页面*/
  @RequestMapping(value = "/index",method = RequestMethod.GET)
  public ModelAndView index(Long  book_orderId, String book_orderName, String order_Prices) {
    //通过id查询入住了几天计算价格
    BookOrder bookOrder = bookOrderService.findBookOrder(book_orderId);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
    Date ArriveDate = null;
    Date LeaveDate = null;
    try {
      ArriveDate = simpleDateFormat.parse(bookOrder.getArriveDate());
      LeaveDate = simpleDateFormat.parse(bookOrder.getLeaveDate());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    //居住天数
    Long stayDay=(LeaveDate.getTime()-ArriveDate.getTime())/(1000*60*60*24);
    ModelAndView model = new ModelAndView();
    Map<String, Object> mod = model.getModel();
    mod.put("book_orderName",book_orderName);
    mod.put("order_Prices",Double.parseDouble(order_Prices)*stayDay);
    mod.put("book_orderId",book_orderId);
    model.setViewName("ALipay/index");
    return model;
  }
  /*跳转支付页面*/
  @RequestMapping(value = "/pay",method = RequestMethod.POST)
  public ModelAndView pay(String WIDout_trade_no, String WIDsubject, String WIDtotal_amount, String WIDbody, ModelAndView model) {
    ModelMap modelMap = model.getModelMap();
    modelMap.put("WIDout_trade_no",WIDout_trade_no);
    modelMap.put("WIDsubject",WIDsubject);
    modelMap.put("WIDtotal_amount",WIDtotal_amount);
    modelMap.put("WIDbody",WIDbody);
    model.setViewName("ALipay/alipay.trade.page.pay");
    return model;
  }

  @RequestMapping(value = "/success",method = RequestMethod.POST)
  @ResponseBody
  public String success(HttpServletRequest request) throws AlipayApiException {
    /* *
     * 功能：支付宝服务器异步通知页面
     * 日期：2017-03-30
     * 说明：
     * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
     * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。


     *************************页面功能说明*************************
     * 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
     * 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
     * 如果没有收到该页面返回的 success
     * 建议该页面只做支付成功的业务逻辑处理，退款的处理请以调用退款查询接口的结果为准。
     */

    //获取支付宝POST过来反馈信息
    Map<String,String> params = new HashMap<String,String>();
    Map<String,String[]> requestParams = request.getParameterMap();
    for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
      String name = (String) iter.next();
      String[] values = (String[]) requestParams.get(name);
      String valueStr = "";
      for (int i = 0; i < values.length; i++) {
        valueStr = (i == values.length - 1) ? valueStr + values[i]
                : valueStr + values[i] + ",";
      }
      params.put(name, valueStr);
    }

    boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

    //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
    if(signVerified) {//验证成功
      //商户订单号
      String out_trade_no = request.getParameter("out_trade_no");

      //支付宝交易号
      String trade_no = request.getParameter("trade_no");

      //交易状态
      String trade_status = request.getParameter("trade_status");

      if(trade_status.equals("TRADE_FINISHED")){
        //判断该笔订单是否在商户网站中已经做过处理
        //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
        //如果有做过处理，不执行商户的业务程序

        //注意：
        //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
      }else if (trade_status.equals("TRADE_SUCCESS")){
        //判断该笔订单是否在商户网站中已经做过处理
        //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
        //如果有做过处理，不执行商户的业务程序

        //注意：
        //付款完成后，支付宝系统发送该交易状态通知
      }
      String id = params.get("out_trade_no");
      System.out.println("id:"+id);
      BookOrder bookOrder = bookOrderService.findBookOrder(Long.parseLong(id));
      System.out.println("book"+bookOrder);
      bookOrder.setPayStatus(1);//支付成功
      bookOrderService.editBookOrder(bookOrder);
    }else {//验证失败
      System.out.println("验证失败");
      //调试用，写文本函数记录程序运行情况是否正常
      //String sWord = AlipaySignature.getSignCheckContentV1(params);
      //AlipayConfig.logResult(sWord);
    }

    //——请在这里编写您的程序（以上代码仅作参考）——
return "ok";
  }

}
