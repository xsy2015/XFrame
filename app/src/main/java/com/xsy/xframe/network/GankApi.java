/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.xsy.xframe.network;

import com.xsy.base.ui.network.JsonDataResponse;
import com.xsy.xframe.bean.NewsBean;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

// @formatter:off

/**
 * Created by drakeet on 8/9/15.
 */
public interface GankApi {

    /*@GET("data/福利/" + DrakeetFactory.meizhiSize + "/{page}")
    Observable<MeizhiData> getMeizhiData(@Path("page") int page);

    @GET("day/{year}/{month}/{day}") Observable<GankData> getGankData(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);

    @GET("data/休息视频/" + DrakeetFactory.meizhiSize + "/{page}")
    Observable<休息视频Data> get休息视频Data(@Path("page") int page);*/

    @GET("/api/News/Search")
    Observable<NewsBean> getNewsList(
            @Query("pageIndex") String pageIndex,
            @Query("pageSize") String pageSize
            );
    @GET("/api/News/Search")
    Observable<JsonDataResponse<List<NewsBean.DataBean>>> getNewsList2(
            @Query("pageIndex") String pageIndex,
            @Query("pageSize") String pageSize
    );

}
