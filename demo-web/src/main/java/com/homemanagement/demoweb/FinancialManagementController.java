package com.homemanagement.demoweb;

import com.homemanagement.demoentity.entity.SeasUser;
import com.homemanagement.demoentity.entity.UserExpenditure;
import com.homemanagement.demoentity.entity.UserIncome;
import com.homemanagement.demoentity.entity.UserMoney;
import com.homemanagement.demoservice.service.ISeasUserService;
import com.homemanagement.demoservice.service.IUserExpenditureService;
import com.homemanagement.demoservice.service.IUserIncomeService;
import com.homemanagement.demoservice.service.IUserMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;

@RestController()
@RequestMapping("/userMoney")
public class FinancialManagementController {

    @Autowired
    ISeasUserService seasUserService;

    @Autowired
    IUserMoneyService userMoneyService;

    @Autowired
    IUserIncomeService userIncomeService;

    @Autowired
    IUserExpenditureService userExpenditureService;
    //
    @CrossOrigin(origins = "*")
    @RequestMapping("/financialManagementTab")
    public UserMoney queryMoney(@RequestParam(value = "tab",required = false) String tab, HttpSession session){
        System.out.println("进来了");
        SeasUser seasUser = (SeasUser)session.getAttribute("seasUser");
        System.out.println(seasUser.getUserId());
        UserMoney userMoney = userMoneyService.getUserMoneyByUserId(seasUser.getUserId());
        session.setAttribute("userMoney",userMoney);
        return userMoney;
    }


    @CrossOrigin(origins = "*")
    @RequestMapping("/addInComeMoney")
    public void addInComeMoney(@RequestParam(value = "incomeAmount",required = false) String incomeAmount,@RequestParam(value = "incomeType",required = false) String incomeType,@RequestParam(value = "incomeDate",required = false) String incomeDate,@RequestParam(value = "remarks",required = false) String remarks,HttpSession session){
        SeasUser seasUser = (SeasUser)session.getAttribute("seasUser");//从session中获取用户
        UserMoney userMoney = userMoneyService.getUserMoneyByUserId(seasUser.getUserId());//根据用户id查询出总金额
        UserIncome userIncome = new UserIncome();
        userIncome.setUserId(seasUser.getUserId());
        userIncome.setUserName(seasUser.getUserName());
        userIncome.setIncomeAmount(incomeAmount);
        userIncome.setIncomeType(incomeType);
        incomeDate = incomeDate.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.sql.Date date1 = null;
        try{
            java.util.Date date = format.parse(incomeDate);
            date1 = new Date(date.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        userIncome.setIncomeDate(date1);
        userIncome.setRemarks(remarks);
        userIncomeService.addIncomeRecord(userIncome);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/addExpenditureRecords")
    public String addExpenditureRecords(@RequestParam(value = "expenditureAmount",required = false) String expenditureAmount,@RequestParam(value = "expenditureType",required = false) String expenditureType,@RequestParam(value = "expenditureDate",required = false) String expenditureDate,@RequestParam(value = "expenditureExplain",required = false) String expenditureExplain,HttpSession session){
        SeasUser seasUser = (SeasUser)session.getAttribute("seasUser");//从session中获取用户
        UserExpenditure userExpenditure = new UserExpenditure();
        userExpenditure.setUserId(seasUser.getUserId());
        userExpenditure.setUserName(seasUser.getUserName());
        userExpenditure.setExpenditureAmount(expenditureAmount);
        userExpenditure.setExpenditureType(expenditureType);
        userExpenditure.setExpenditureExplain(expenditureExplain);
        expenditureDate = expenditureDate.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.sql.Date date1 = null;
        try{
            java.util.Date date = format.parse(expenditureDate);
            date1 = new Date(date.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        userExpenditure.setExpenditureDate(date1);

        try {
            userExpenditureService.addExpenditureRecords(userExpenditure);
        }catch (Exception  e){
            return "0";
        }
        return "1";
    }
}
