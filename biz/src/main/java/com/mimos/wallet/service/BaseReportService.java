package com.mimos.wallet.service;

import java.util.List;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/21 11:53 AM
 */
public interface BaseReportService <T> {

    /**
     *
     * @param item
     * @return savedID
     */
    void onReprot(T item) throws Exception;

    /**
     *
     * @param items
     * @return effectCount
     */
    void onReportList(List<T> items) throws Exception;

}