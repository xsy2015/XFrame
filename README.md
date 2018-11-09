#### 1.retrofit+okhttp+rxjava封装网络请求
```java
GankApi gankApi = DrakeetFactory.getGankIOSingleton();
        gankApi.getNewsList2("1", "10")
                .compose(RxUtil.<JsonDataResponse<List<NewsBean.DataBean>>>normalSchedulers())
                .subscribe(new WebSuccessAction<JsonDataResponse<List<NewsBean.DataBean>>>() {
                    @Override
                    public void onSuccess(JsonDataResponse<List<NewsBean.DataBean>> response) {
                        LogUtils.i(TAG,"data="+response.getData());
                        updateUI(response);
                    }
                }, new WebFailAction());
```
#### 2.集成qmui改变整个App色调
CoordinatorLayout+QMUIAppBarLayout</br>
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181107173543950.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3Nhbnlhbmc3MzA=,size_16,color_FFFFFF,t_70)
#### 3.图片卡片式左右滑动
图片展示，左右滑动</br>
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181107164721684.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3Nhbnlhbmc3MzA=,size_16,color_FFFFFF,t_70)
#### 4.扫一扫功能实现
作为一个lib组件</br>
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181107164727959.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3Nhbnlhbmc3MzA=,size_16,color_FFFFFF,t_70)</br>
![](https://github.com/xsy2015/XFrame/blob/master/app/src/main/res/assets/xsy.gif)</br>
