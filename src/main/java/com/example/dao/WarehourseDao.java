package com.example.dao;

import com.example.entity.Warehourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * Company: www.leadmap.net
 * Description:
 *
 * @author: 49022
 * @Date: 2019/1/15 10:08
 */
@Repository
public interface WarehourseDao extends PagingAndSortingRepository<Warehourse, Integer>, JpaSpecificationExecutor<Warehourse> {

    @Query(value = "delete from warehourse where name = :inputname", nativeQuery = true)
    @Modifying
    void deleteByName(@Param("inputname") String inputname);

    @Modifying
    @Query(value = "select * from warehourse where name=?1", nativeQuery = true)
    List<Warehourse> findByName(@Param("inputname") String inputname);


    @Modifying
    @Query(value = "update warehourse w set w.NAME =:inputname where w.id=:inputid", nativeQuery = true)
    void updatename(@Param("inputname") String inputname, @Param("inputid") Integer id);

    @Modifying
    @Query(value = "update warehourse w set w.type =:inputtype where w.id=:inputid", nativeQuery = true)
    void updatetype(@Param("inputtype") String inputtype, @Param("inputid") Integer id);

    @Modifying
    @Query(value = "update warehourse w set w.unitprice =:inputunitprice where w.id=:inputid", nativeQuery = true)
    void updateunitprice(@Param("inputunitprice") Float inputunitprice, @Param("inputid") Integer id);

    @Modifying
    @Query(value = "update warehourse w set w.totalprice =:inputtotalprice where w.id=:inputid", nativeQuery = true)
    void updatetotalprice(@Param("inputtotalprice") Float inputtotalprice, @Param("inputid") Integer id);

    @Modifying
    @Query(value = "update warehourse w set w.vender =:inputvender where w.id=:inputid", nativeQuery = true)
    void updatevender(@Param("inputvender") String inputvender, @Param("inputid") Integer id);

    @Modifying
    @Query(value = "update warehourse w set w.producedata =:inputproducedata where w.id=:inputid", nativeQuery = true)
    void updateproducedata(@Param("inputproducedata") Date inputproducedata, @Param("inputid") Integer id);

    @Modifying
    @Query(value = "update warehourse w set w.buyperson =:inputbuyperson where w.id=:inputid", nativeQuery = true)
    void updatebuyperson(@Param("inputbuyperson") String inputbuyperson, @Param("inputid") Integer id);

    @Modifying
    @Query(value = "update warehourse w set w.color =:inputcolor where w.id=:inputid", nativeQuery = true)
    void updatecolor(@Param("inputcolor") String inputcolor, @Param("inputid") Integer id);

    @Modifying
    @Query(value = "update warehourse w set w.number =:inputnumber where w.id=:inputid", nativeQuery = true)
    void updatenumber(@Param("inputnumber") String inputnumber, @Param("inputid") Integer id);

    @Modifying
    @Query(value = "update warehourse w set w.enterhoursetime =:inputenterhoursetime where w.id=:inputid", nativeQuery = true)
    void updateenterhoursetime(@Param("inputenterhoursetime") Date inputenterhoursetime, @Param("inputid") Integer id);

    @Modifying
    @Query(value = "update warehourse w set w.enterhourseperson =:inputenterhourseperson where w.id=:inputid", nativeQuery = true)
    void updateenterhourseperson(@Param("inputenterhourseperson") String inputenterhourseperson, @Param("inputid") Integer id);

    @Modifying
    @Query(value = "update warehourse w set w.hoursenumber=:inputhoursenumber where w.id=:inputid", nativeQuery = true)
    void updatehoursenumber(@Param("inputhoursenumber") Integer inputhoursenumber, @Param("inputid") Integer id);


//    @Modifying
//    @Query(value = "select * from warehourse where enterhoursetime between ?1 and ?2",nativeQuery = true)
//    List<Warehourse> getBydefaultselect(@Param("begintime") Date begintime,@Param("endtime") Date endtime);
}


/**
 * @Transactional public interface WarehourseDao extends CrudRepository<Warehourse,Integer>{
 * public List<Warehourse> findById(int id);
 * public List<Warehourse> findByName(String name);
 * public List<Warehourse> findByType(String type);
 * public List<Warehourse> findByUnitprice(Float unitprice);
 * public List<Warehourse> findByTotalprice(Float totalprice);
 * public List<Warehourse> findByVender(String verder);
 * public List<Warehourse> findByProducedata(Date producedata);
 * public List<Warehourse> findByBuyperson(String buyperson);
 * public List<Warehourse> findByColor(String color);
 * public List<Warehourse> findByNumber(String number);
 * public List<Warehourse> findByEnterhoursetime(Date enterhoursetime);
 * public List<Warehourse> findByEnterhourseperson(String enterhourseperson);
 * public List<Warehourse> findByHoursenumber(Integer hoursenumber);
 * }
 */

