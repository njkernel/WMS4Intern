package com.connext.wms.service;

import com.connext.wms.entity.InRepertory;
import com.connext.wms.util.Page;
import org.springframework.ui.Model;

import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2019/1/7 10:02
 * @Version 1.0
 */
public interface InRepertoryService {

    /**
     * find all InRepertory
     *
     * @return list of InRepertory
     */
    List<InRepertory> findAll();

    /**
     * find some InRepertory
     *
     * @param value like
     * @return list of InRepertory
     */
    List<InRepertory> findAllLike(String value);


    /**
     * find some InRepertory
     *
     * @param start page num
     * @param size page size
     * @return page
     */
    List<InRepertory> findPage(Integer start,Integer size);

    /**
     * find one type of InRepertory
     *
     * @param start page page
     * @param size page size
     * @param status status
     * @return page
     */
    List<InRepertory> findPageBy(String status,Integer start,Integer size);

    /**
     * find Detail for one InRepertory
     *
     * @param id id of InRepertory
     * @return the InRepertoryDetail contain Detail
     */
    InRepertory findOne(Integer id);

    /**
     * Receive a InRepertory and init it
     *
     * @param inRepertory the InRepertory from api
     */
    void initInRepertory(InRepertory inRepertory);

    /**
     * checkRepertoryState
     *
     * @param inRepertories some InRepertory
     * @return list of InRepertory which over 15 days and sync_status is false
     */
    List<InRepertory> checkInRepertoryExpired(List<InRepertory> inRepertories);

    /**
     * change the  Status of InRepertory which not complete
     *
     * @param id     InRepertory id
     * @param status InRepertory status
     * @return true or false
     */
    boolean changeInRepertoryStatus(Integer id, String status);

    /**
     * pushInRepertoryState
     *
     * @param inRepertory the inRepertory
     * @return true or false
     */
    boolean pushInRepertoryState(InRepertory inRepertory);

    /**
     * get a page
     * @param page page number
     * @param inRepertoryList the list want to display
     * @param status inRepertory status
     * @return a template
     */
    Page getPageInfo(Integer page, List<InRepertory> inRepertoryList, String status);
}
