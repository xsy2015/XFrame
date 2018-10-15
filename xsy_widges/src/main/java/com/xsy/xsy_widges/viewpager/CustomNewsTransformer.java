package com.xsy.xsy_widges.viewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @Description描述:
 * @Author作者: xsy
 * @Date日期: 2017/6/14
 */

public class CustomNewsTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.9F;
    @Override
    public void transformPage(View page, float position) {

        if(position < -1){
            page.setScaleY(MIN_SCALE);
        }else if(position<= 1){
            //
            float scale = Math.max(MIN_SCALE,1 - Math.abs(position));
            page.setScaleY(scale);
            /*page.setScaleX(scale);

            if(position<0){
                page.setTranslationX(width * (x1 - scale) /x2);
            }else{
                page.setTranslationX(-width * (x1 - scale) /x2);
            }*/

        }else{
            page.setScaleY(MIN_SCALE);
        }
    }
}
