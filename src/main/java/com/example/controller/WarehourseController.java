package com.example.controller;

import com.example.common.CalendarUtil;
import com.example.common.DateFormat;
import com.example.common.DateUtil;
import com.example.dao.WarehourseDao;
import com.example.dto.ResultInfo;
import com.example.entity.Warehourse;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.example.service.WarehourseService;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.sql.DataTruncation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Company: www.leadmap.net
 * Description:
 *
 * @author: 49022
 * @Date: 2019/1/15 10:40
 */
@Controller
public class WarehourseController {

    @Autowired
    private WarehourseService warehourseService;

    @Autowired
    private WarehourseDao warehourseDao;

    @RequestMapping("main")
    public String main() {
        return "main";
    }

    @RequestMapping("insert")
    public String insert() {
        return "insert";
    }

    @RequestMapping("delete")
    public String delete() {
        return "delete";
    }

    @RequestMapping("update")
    public String update() {
        return "update";
    }

    @RequestMapping("select")
    public String select() {
        return "select";
    }

    /**
     * 存入数据
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/saveData", method = RequestMethod.POST)
    @ResponseBody
    public String saveData(HttpServletRequest request) {
        warehourseService.save(request);
        return "存入数据";
    }

    /**
     * 删除数据
     */
    @RequestMapping(value = "deleteData", method = RequestMethod.GET)
    @ResponseBody
    public String deleteData(HttpServletRequest request) {
        warehourseService.delete(request);
        return "删除数据";
    }

    /**
     * 通过id修改数据
     */
    @RequestMapping(value = "updateData", method = RequestMethod.GET)
    @ResponseBody
    public String updateData(HttpServletRequest request) {
        warehourseService.update(request);
        return "修改数据";
    }

    /**
     * 查询数据
     */
    @RequestMapping(value = "selectData", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo<List<Warehourse>> selectData(HttpServletRequest request) {
        ResultInfo<List<Warehourse>> resultInfo = new ResultInfo<>();
        String defaultselect = request.getParameter("defaultselect");
        String enterhoursetime = request.getParameter("enterhoursetime");
        String type = request.getParameter("type");
        String vender = request.getParameter("vender");
        String enterhoursebegintime = request.getParameter("enterhoursebegintime");
        String enterhourseendtime = request.getParameter("enterhourseendtime");
        String name = request.getParameter("name");
        String hoursenumber = request.getParameter("hoursenumber");
        List<Warehourse> list = warehourseService.getWarehoure(defaultselect, enterhoursetime, type, vender, enterhoursebegintime, enterhourseendtime, name, hoursenumber);
        resultInfo.setCode("200");
        resultInfo.setMsg("success");
        resultInfo.setData(list);
        return resultInfo;
    }

    @RequestMapping(value = "/autoGetTotalprice", method = RequestMethod.POST)
    @ResponseBody
    public String autoGetTotalprice(HttpServletRequest request) {
        String unitprice = request.getParameter("unitprice");
        String number = request.getParameter("number");
        String totalprice = String.valueOf(Float.parseFloat(unitprice) * Integer.parseInt(number));
        return totalprice;
    }

    @RequestMapping(value = "/dnumberjudge", method = RequestMethod.POST)
    @ResponseBody
    public String dnumberjudge(HttpServletRequest request) {
        String id = request.getParameter("id");
        Optional<Warehourse> warehourse = warehourseDao.findById(Integer.parseInt(id));
        String number = warehourse.get().getNumber();
        return number;
    }

    @RequestMapping(value = "/autoGetEnterhoursetime", method = RequestMethod.POST)
    @ResponseBody
    public String autoGetEnterhoursetime() {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowtime = sf.format(date);
        return nowtime;
    }
}


/**
 * //查询数据库列表
 *
 * @RequestMapping("/getId")
 * @ResponseBody public List<Warehourse> getById(int id) {
 * List<Warehourse> warehourseList = warehourseDao.findById(id);
 * //        if (warehourseList != null && warehourseList.size() != 0) {
 * //            return warehourseList;
 * //        }
 * return warehourseList;
 * }
 * @RequestMapping("/getEnterhoursetime")
 * @ResponseBody public String fingByEnterhoursetime(String enterhoursetime) {
 * System.out.println("enterhoursetime:" + enterhoursetime);
 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
 * List<Warehourse> warehourseList = null;
 * try {
 * warehourseList = warehourseDao.findByEnterhoursetime(format.parse(enterhoursetime));
 * } catch (ParseException e) {
 * e.printStackTrace();
 * }
 * if (warehourseList != null && warehourseList.size() != 0) {
 * return "length is:" + warehourseList.size();
 * }
 * return "warehourse" + enterhoursetime + "is not exist.";
 * }
 * <p>
 * //增加一个对象数据
 * @GetMapping(value = "/addWarehourse")
 * public boolean addWarehourse(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam("type") String type, @RequestParam("unitprice") Float unitprice,
 * @RequestParam("totalprice") Float totalprice, @RequestParam("vender") String verder, @RequestParam("producedata") Date producedata, @RequestParam("buyperson") String buyperson,
 * @RequestParam("color") String color, @RequestParam("number") String number, @RequestParam("enterhoursetime") Date enterhoursetime, @RequestParam("enterhourseperson") String enterhourseperson,
 * @RequestParam("hoursenumber") Integer hoursenumber)
 * {
 * try {
 * Warehourse warehourse = new Warehourse();
 * warehourse.setId(id);
 * warehourse.setName(name);
 * warehourse.setType(type);
 * warehourse.setUnitprice(unitprice);
 * warehourse.setTotalprice(totalprice);
 * warehourse.setVender(verder);
 * warehourse.setProducedata(producedata);
 * warehourse.setBuyperson(buyperson);
 * warehourse.setColor(color);
 * warehourse.setNumber(number);
 * warehourse.setEnterhoursetime(enterhoursetime);
 * warehourse.setEnterhourseperson(enterhourseperson);
 * warehourse.setHoursenumber(hoursenumber);
 * warehourseDao.save(warehourse);
 * return true;
 * } catch (Exception e) {
 * return false;
 * }
 * }
 * <p>
 * public static void main(String[] args) {
 * WarehourseController warehourseController=new WarehourseController();
 * warehourseController.addWarehourse(1002,"iphone XR","手机",6199.00f,619900.00f,"雅达电子",null,"张三","珊瑚色","100",
 * null,"王五",2);
 * }
 */

