package com.connext.wms.service.impl;

import com.connext.wms.dao.ExpressCompanyMapper;
import com.connext.wms.entity.ExpressCompany;
import com.connext.wms.entity.ExpressCompanyExample;
import com.connext.wms.service.ExpressCompanyService;
import com.connext.wms.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Chao.Sun
 * @Date: 2019/1/7 9:37
 * @Version 1.0
 */

@Service
public class ExpressCompanyServiceImpl implements ExpressCompanyService {
    @Autowired
    private ExpressCompanyMapper expressCompanyMapper;

    //手机号正则表达式
    public static final String STR = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$";

    //中文正则表达式
    public static final String REG = "[\\u4e00-\\u9fa5]{4,8}$";
    //判断的状态
    private Integer flag;


    //分页查询所有快递公司信息
    public Page selectByPage(Integer currPage){
        List<ExpressCompany> list = expressCompanyMapper.selectByPage((currPage-1)*Page.PAGE_SIZE, Page.PAGE_SIZE);
        ExpressCompanyExample example = new ExpressCompanyExample();
        return byPage(currPage,list,example);
    }

    //根据关键字查询
    public Page selectByKey(Integer currPage,String key){
        String newKey = "%" + key + "%";
        List<ExpressCompany> list = expressCompanyMapper.selectByKey((currPage-1)*Page.PAGE_SIZE, Page.PAGE_SIZE,newKey);
        ExpressCompanyExample example = new ExpressCompanyExample();
        example.or().andExpressCompanyNameLike(newKey);
        return byPage(currPage,list,example);
    }

    //添加快递公司信息
    public Integer insert(String expressCompanyName,String contactWay){
        //手机号验证
        Pattern p = Pattern.compile(STR);
        Matcher m = p.matcher(contactWay);
        //公司名称验证
        Pattern p1 = Pattern.compile(REG);
        Matcher m1 = p1.matcher(expressCompanyName);
        //按公司名查找是否存在记录
        ExpressCompanyExample example = new ExpressCompanyExample();
        example.or().andExpressCompanyNameEqualTo(expressCompanyName);
        List<ExpressCompany> list = expressCompanyMapper.selectByExample(example);
        //1:该公司已存在；2：该公司不存在，可以添加；3：手机号格式错误;4:公司名称超出范围;5:信息不完整
        if(("").equals(expressCompanyName)||("").equals(contactWay)){
            flag = 5;
        }else {
            if(list.size()==1){
                flag = 1;
            }else{
                if(m1.matches()){
                    if(m.matches()){
                        ExpressCompany expressCompany = new ExpressCompany();
                        expressCompany.setExpressCompanyName(expressCompanyName);
                        expressCompany.setContactWay(contactWay);
                        expressCompanyMapper.insert(expressCompany);
                        flag = 2;
                    }else{
                        flag = 3;
                    }
                }else{
                    flag = 4;
                }
            }
        }

        return flag;
    }

    //删除快递公司信息
    public void deleteByExample(String expressCompanyName){
        ExpressCompanyExample example = new ExpressCompanyExample();
        example.createCriteria().andExpressCompanyNameEqualTo(expressCompanyName);
        expressCompanyMapper.deleteByExample(example);
    }

    //修改快递公司信息
    public Integer updateByExample(String newName,String expressCompanyName,String contactWay){
        //手机号验证
        Pattern p = Pattern.compile(STR);
        Matcher m = p.matcher(contactWay);
        //公司名称验证
        Pattern p1 = Pattern.compile(REG);
        Matcher m1 = p1.matcher(newName);
        //通过新公司名称查找是否已经存在该条记录
        ExpressCompanyExample example = new ExpressCompanyExample();
        example.or().andExpressCompanyNameEqualTo(newName);
        List<ExpressCompany> list1 = expressCompanyMapper.selectByExample(example);
        example.or().andExpressCompanyNameEqualTo(expressCompanyName);
        List<ExpressCompany> list2 = expressCompanyMapper.selectByExample(example);

        //1:新公司已存在;2：新公司不存在，可以添加；3：手机号格式错误;4:公司名称超出范围;5：信息不完整
        //6：原公司不存在
        if(("").equals(expressCompanyName)||("").equals(newName)||("").equals(contactWay)){
            flag = 5;
        }else{
            if(list2.size()==1){
                if(list1.size()==1){
                    flag = 1;
                }else{
                    if(m1.matches()){
                        if(m.matches()){
                            ExpressCompany record = new ExpressCompany();
                            record.setExpressCompanyName(newName);
                            record.setContactWay(contactWay);
                            example.or().andExpressCompanyNameEqualTo(expressCompanyName);
                            expressCompanyMapper.updateByExample(record,example);
                            flag = 2;
                        }else{
                            flag = 3;
                        }
                    }else{
                        flag = 4;
                    }
                }
            }else{
                flag = 6;
            }


        }
        return flag;
    }

    //分页实现方法
    Page byPage(Integer currPage,List<ExpressCompany> list,ExpressCompanyExample example){
        Page page = new Page();
        page.setTotalCount((long)expressCompanyMapper.countByExample(example));
        page.setCurrPage(currPage);
        page.init();
        page.setData(list);
        return page;
    }

}
