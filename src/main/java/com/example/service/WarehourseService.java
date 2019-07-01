package com.example.service;

import com.example.common.CalendarUtil;
import com.example.common.DateFormat;
import com.example.common.DateUtil;
import com.example.common.Util;
import com.example.dao.WarehourseDao;
import com.example.entity.Warehourse;
import com.sun.org.apache.bcel.internal.generic.Select;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;


/**
 * Company: www.leadmap.net
 * Description:
 *
 * @author: 49022
 * @Date: 2019/1/16 14:22
 */
@Transactional
@Service
public class WarehourseService {
    //@Autowired是用在JavaBean中的注解，通过byType形式，用来给指定的字段或方法注入所需的外部资源。
    @Autowired
    private WarehourseDao warehourseDao;

    /**
     * 存入数据
     *
     * @param request
     */
    public void save(HttpServletRequest request) {
        Warehourse warehourse = new Warehourse();
//        warehourse.setId(Integer.parseInt(request.getParameter("id")));
        warehourse.setName(request.getParameter("name"));
        warehourse.setType(request.getParameter("type"));
        warehourse.setUnitprice(Float.parseFloat(request.getParameter("unitprice")));
        warehourse.setTotalprice(Float.parseFloat(request.getParameter("totalprice")));
        warehourse.setVender(request.getParameter("vender"));
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            warehourse.setProducedata(format.parse(request.getParameter("producedata")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        warehourse.setBuyperson(request.getParameter("buyperson"));
        warehourse.setColor(request.getParameter("color"));
        warehourse.setNumber(request.getParameter("number"));
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(request.getParameter("enterhoursetime"));
            warehourse.setEnterhoursetime(format.parse(request.getParameter("enterhoursetime")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        warehourse.setEnterhourseperson(request.getParameter("enterhourseperson"));
        warehourse.setHoursenumber(Integer.parseInt(request.getParameter("hoursenumber")));
        warehourseDao.save(warehourse);
    }

    /**
     * 删除数据
     *
     * @param request
     */
    public void delete(HttpServletRequest request) {
        int number;
        String inputid = request.getParameter("id");
        if (inputid.isEmpty()) {
            String inputname = request.getParameter("name");
            List<Warehourse> list = warehourseDao.findByName(inputname);
            for (Warehourse w : list) {
                Float unitprice = w.getUnitprice();
                number = Integer.parseInt(w.getNumber());
                int inputnumber = Integer.parseInt(request.getParameter("number"));
                if (inputnumber <= number) {
                    String displaynumber = String.valueOf(number - inputnumber);
                    Float displaytotalprice = unitprice * (number - inputnumber);
                    w.setTotalprice(displaytotalprice);
                    w.setNumber(displaynumber);
                    warehourseDao.save(w);
                }
            }
        } else {
            Optional<Warehourse> warehourse = warehourseDao.findById(Integer.parseInt(inputid));
            Float unitprice = warehourse.get().getUnitprice();
            number = Integer.parseInt(warehourse.get().getNumber());
            int inputnumber = Integer.parseInt(request.getParameter("number"));
            if (inputnumber <= number) {
                String displaynumber = String.valueOf(number - inputnumber);
                Float displaytotalprice = unitprice * (number - inputnumber);
                warehourse.get().setTotalprice(displaytotalprice);
                warehourse.get().setNumber(displaynumber);
                warehourseDao.save(warehourse.get());
            }
        }
    }

    /**
     * 通过id修改数据
     */
    public void update(HttpServletRequest request) {
        int inputid = Integer.parseInt(request.getParameter("id"));
        String inputname = request.getParameter("name");
        String inputtype = request.getParameter("type");
        String unitprice = request.getParameter("unitprice");
        String totalprice = request.getParameter("totalprice");
        String inputvender = request.getParameter("vender");
        String producedata = request.getParameter("producedata");
        String inputbuyperson = request.getParameter("buyperson");
        String inputcolor = request.getParameter("color");
        String inputnumber = request.getParameter("number");
        String enterhoursetime = request.getParameter("enterhoursetime");
        String inputenterhourseperson = request.getParameter("enterhourseperson");
        String hoursenumber = request.getParameter("hoursenumber");
        if (!inputname.isEmpty()) {
            warehourseDao.updatename(inputname, inputid);
        }
        if (!inputtype.isEmpty()) {
            warehourseDao.updatetype(inputtype, inputid);
        }
        if (!unitprice.isEmpty()) {
            Float inputunitprice = Float.parseFloat(unitprice);
            warehourseDao.updateunitprice(inputunitprice, inputid);
        }
        if (!totalprice.isEmpty()) {
            Float inputtotalprice = Float.parseFloat(totalprice);
            warehourseDao.updatetotalprice(inputtotalprice, inputid);
        }
        if (!inputvender.isEmpty()) {
            warehourseDao.updatevender(inputvender, inputid);
        }
        if (!producedata.isEmpty()) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date inputproducedata = format.parse(producedata);
                warehourseDao.updateproducedata(inputproducedata, inputid);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!inputbuyperson.isEmpty()) {
            warehourseDao.updatebuyperson(inputbuyperson, inputid);
        }
        if (!inputcolor.isEmpty()) {
            warehourseDao.updatecolor(inputcolor, inputid);
        }
        if (!inputnumber.isEmpty()) {
            warehourseDao.updatenumber(inputnumber, inputid);
        }
        if (!enterhoursetime.isEmpty()) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date inputenterhoursetime = format.parse(enterhoursetime);
                warehourseDao.updateenterhoursetime(inputenterhoursetime, inputid);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!inputenterhourseperson.isEmpty()) {
            warehourseDao.updateenterhourseperson(inputenterhourseperson, inputid);
        }
        if (!hoursenumber.isEmpty()) {
            int inputhoursenumber = Integer.parseInt(hoursenumber);
            warehourseDao.updatehoursenumber(inputhoursenumber, inputid);
        }
    }

    /**
     * 查询数据
     */

    public List<Warehourse> getWarehoure(String defaultselect, String enterhoursetime, String type, String vender, String enterhoursebegintime, String enterhourseendtime, String name, String hoursenumber) {
        List<Warehourse> warehourseList = new ArrayList<>();
        Specification querySpecifi = new Specification<Warehourse>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (defaultselect.isEmpty()) {
                    CalendarUtil calendarUtil = new CalendarUtil();
                    String firstdayofnowmonth = calendarUtil.getFirstDayOfMonth();
                    String lastdayofnowmonth = calendarUtil.getDefaultDay();
                    Date nowmonthfirstdate = Util.StrToDate(firstdayofnowmonth + " 00:00:00");
                    Date nowmonthlastdate = Util.StrToDate(lastdayofnowmonth + " 23:59:59");
                    predicateList.add(criteriaBuilder.greaterThan(root.get("enterhoursetime"), nowmonthfirstdate));
                    predicateList.add(criteriaBuilder.lessThan(root.get("enterhoursetime"), nowmonthlastdate));
                }
                if (!enterhoursetime.isEmpty()) {
                    CalendarUtil calendarUtil = new CalendarUtil();
                    if ("今天".equals(enterhoursetime)) {
                        String nowtime = CalendarUtil.getNowTime();
                        Date nowbegindate = Util.StrToDate(nowtime + " 00:00:00");
                        Date nowenddate = Util.StrToDate(nowtime + " 23:59:59");
                        predicateList.add(criteriaBuilder.greaterThan(root.get("enterhoursetime"), nowbegindate));
                        predicateList.add(criteriaBuilder.lessThan(root.get("enterhoursetime"), nowenddate));
                    }
                    if ("本周".equals(enterhoursetime)) {
                        String preMonday = calendarUtil.getMondayOFWeek();
                        String preSunday = calendarUtil.getCurrentWeekday();
                        Date nowweekmondaydate = Util.StrToDate(preMonday + " 00:00:00");
                        Date nowweeksundaydate = Util.StrToDate(preSunday + " 23:59:59");
                        predicateList.add(criteriaBuilder.greaterThan(root.get("enterhoursetime"), nowweekmondaydate));
                        predicateList.add(criteriaBuilder.lessThan(root.get("enterhoursetime"), nowweeksundaydate));
                    }
                    if ("本月".equals(enterhoursetime)) {
                        String firstdayofnowmonth = calendarUtil.getFirstDayOfMonth();
                        String lastdayofnowmonth = calendarUtil.getDefaultDay();
                        Date nowmonthfirstdate = Util.StrToDate(firstdayofnowmonth + " 00:00:00");
                        Date nowmonthlastdate = Util.StrToDate(lastdayofnowmonth + " 23:59:59");
                        predicateList.add(criteriaBuilder.between(root.get("enterhoursetime"), nowmonthfirstdate, nowmonthlastdate));
                    }
                    if ("上月".equals(enterhoursetime)) {
                        String firstdayofprecedingmonth = calendarUtil.getPreviousMonthFirst();
                        String lastdayofprecedingmonth = calendarUtil.getPreviousMonthEnd();
                        Date pmonthfirstdate = Util.StrToDate(firstdayofprecedingmonth + " 00:00:00");
                        Date pmonthlastdate = Util.StrToDate(lastdayofprecedingmonth + " 23:59:59");
                        predicateList.add(criteriaBuilder.between(root.get("enterhoursetime"), pmonthfirstdate, pmonthlastdate));
                    }
                    if ("本年".equals(enterhoursetime)) {
                        String firstdayofnowyear = calendarUtil.getCurrentYearFirst();
                        String lastdayofnowyear = calendarUtil.getCurrentYearEnd();
                        Date nowyearfirstdate = Util.StrToDate(firstdayofnowyear + " 00:00:00");
                        Date nowyearlastdate = Util.StrToDate(lastdayofnowyear + " 23:59:59");
                        predicateList.add(criteriaBuilder.between(root.get("enterhoursetime"), nowyearfirstdate, nowyearlastdate));
                    }
                    if ("上年".equals(enterhoursetime)) {
                        String firstdayofpyear = calendarUtil.getPreviousYearFirst();
                        String lastdayofpyear = calendarUtil.getPreviousYearEnd();
                        Date pyearfirstdate = Util.StrToDate(firstdayofpyear + " 00:00:00");
                        Date pyearlastdate = Util.StrToDate(lastdayofpyear + " 23:59:59");
                        predicateList.add(criteriaBuilder.between(root.get("enterhoursetime"), pyearfirstdate, pyearlastdate));
                    }
                }
                if (!type.isEmpty()) {
                    predicateList.add(criteriaBuilder.like(root.get("type"), "%" + type + "%"));
                }
                if (!vender.isEmpty()) {
                    predicateList.add(criteriaBuilder.like(root.get("vender"), "%" + vender + "%"));
                }
                if (!enterhoursebegintime.isEmpty()) {
                    Date beginDate = Util.StrToDate(enterhoursebegintime + " 00:00:00");
                    predicateList.add(criteriaBuilder.greaterThan(root.get("enterhoursetime"), beginDate));
                }
                if (!enterhourseendtime.isEmpty()) {
                    Date endDate = Util.StrToDate(enterhourseendtime + " 00:00:00");
                    predicateList.add(criteriaBuilder.lessThan(root.get("enterhoursetime"), endDate));
                }
                if (!name.isEmpty()) {
                    predicateList.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                }
                if (!hoursenumber.isEmpty()) {
                    int inthoursenumber = Integer.parseInt(hoursenumber);
                    predicateList.add(criteriaBuilder.equal(root.get("hoursenumber"), inthoursenumber));
                }
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
                criteriaQuery.where(criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()])));
                return criteriaQuery.getRestriction();
            }
        };
        warehourseList = warehourseDao.findAll(querySpecifi);
        return warehourseList;
    }

//    public List<Warehourse> defaultselect(){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        String nowdate = simpleDateFormat.format(date);
//        String monthtstartime=nowdate.substring(0,7)+'-'+"01";
//        String monthendtime=nowdate.substring(0,7)+'-'+"31";
//        Date begintime=DateUtil.stringDate(monthtstartime+" 00:00:00",DateFormat.DateFull);
//        Date endtime=DateUtil.stringDate(monthendtime+" 23:59:59",DateFormat.DateFull);
//        List<Warehourse> list=warehourseDao.getBydefaultselect(begintime,endtime);
//        return list;
//    }

}


/**
 * 查询数据
 * public List<Warehourse> select() {
 * SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 * Date date = new Date();
 * String nowdate = simpleDateFormat.format(date);
 * System.out.println(nowdate);
 * List<Warehourse> iterable = (List<Warehourse>) warehourseDao.findAll();
 * List<Warehourse> list = new ArrayList<>();
 * for (int i = 0; i < iterable.size(); i++) {
 * String enterhoursetime = iterable.get(i).getEnterhoursetime().toString();
 * System.out.println(enterhoursetime);
 * System.out.println(enterhoursetime.substring(0, 7));
 * System.out.println(nowdate.substring(0, 7));
 * if (enterhoursetime.substring(0, 7).equals(nowdate.substring(0, 7))) {
 * Integer id = iterable.get(i).getId();
 * Warehourse warehourse = warehourseDao.findById(id).get();
 * list.add(warehourse);
 * }
 * }
 * return list;
 * }
 */
//        String starttime="2019-01-01 00:00:00";
//        String endtime="2019-01-31 23:59:59";
//        warehourseDao.selectenterhoursetime(starttime,endtime);



