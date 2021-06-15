package com.lookballs.app.mvvm.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.lookballs.app.mvvm.BR;

import java.util.List;

public class ArticleJsonBean extends BaseBean {

    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18587,"link":"https://mp.weixin.qq.com/s/HVA9vTN5n2QQwE4B6oyuuQ","niceDate":"1天前","niceShareDate":"19小时前","origin":"","prefix":"","projectLink":"","publishTime":1623254400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1623336655000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"让你迷惑的 Kotlin 代码","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18575,"link":"https://mp.weixin.qq.com/s/E8v-8mVhc_REXCftNOZ7cg","niceDate":"2天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1623168000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1623250842000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"新技术| ViewBinding 与 Kotlin 委托双剑合璧","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18558,"link":"https://mp.weixin.qq.com/s/PxrW7LSDuAAeqlXFxnIInA","niceDate":"2021-06-08 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1623081600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1623163980000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android 混淆，新引入的D8、R8改变了什么？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18557,"link":"https://mp.weixin.qq.com/s/xGD68ia_9VgL-qgbZM4m3g","niceDate":"2021-06-07 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1622995200000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1623163965000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"直面底层 | ImageView scaleType是如何实现的？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18535,"link":"https://mp.weixin.qq.com/s/lN4nIeYoSg06fMaAoi6W-A","niceDate":"2021-06-04 00:00","niceShareDate":"2021-06-06 22:15","origin":"","prefix":"","projectLink":"","publishTime":1622736000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622988955000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android中竟然包含这么多设计模式，一起来学一波！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18517,"link":"https://mp.weixin.qq.com/s/nEvBqMPrdmirADo-Y6ihYg","niceDate":"2021-06-03 00:00","niceShareDate":"2021-06-03 22:31","origin":"","prefix":"","projectLink":"","publishTime":1622649600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622730709000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"遇到个难题，怎么修改子View绘制顺序？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18506,"link":"https://mp.weixin.qq.com/s/6OaE2SZHFQQfiE8vadQOaw","niceDate":"2021-06-02 00:00","niceShareDate":"2021-06-02 23:18","origin":"","prefix":"","projectLink":"","publishTime":1622563200000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622647112000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"RecyclerView 中的秘密探索 | 滚动","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18483,"link":"https://mp.weixin.qq.com/s/z3FkJHTSkH_TFis_VOdkSw","niceDate":"2021-06-01 00:00","niceShareDate":"2021-06-01 22:10","origin":"","prefix":"","projectLink":"","publishTime":1622476800000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622556624000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Google 还发布了这个库？ 告别shape、各种 drawable...","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18476,"link":"https://mp.weixin.qq.com/s/MbYlH0gB2EGx-FSmeZh7zA","niceDate":"2021-05-31 00:00","niceShareDate":"2021-05-31 21:41","origin":"","prefix":"","projectLink":"","publishTime":1622390400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622468499000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Compose 1.0 即将发布，你准备好了吗？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18469,"link":"https://mp.weixin.qq.com/s/AguM2NYfflEKin6Nr75XPQ","niceDate":"2021-05-30 00:00","niceShareDate":"2021-05-30 22:27","origin":"","prefix":"","projectLink":"","publishTime":1622304000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622384875000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"一个困惑很久的问题，Android中有子窗口吗？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18451,"link":"https://mp.weixin.qq.com/s/7vWg3epsXIH3hFeohkNUag","niceDate":"2021-05-27 00:00","niceShareDate":"2021-05-27 22:23","origin":"","prefix":"","projectLink":"","publishTime":1622044800000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622125420000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android 事件分发中你可能忽略掉的点！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18440,"link":"https://mp.weixin.qq.com/s/ukFOCvAlMdTYE4t7vTHAsA","niceDate":"2021-05-26 00:00","niceShareDate":"2021-05-26 21:59","origin":"","prefix":"","projectLink":"","publishTime":1621958400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622037596000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"开启B站少女心，探究APP换肤","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18427,"link":"https://mp.weixin.qq.com/s/UQIC8-SjASHUZkDI97RiWQ","niceDate":"2021-05-25 00:00","niceShareDate":"2021-05-25 22:37","origin":"","prefix":"","projectLink":"","publishTime":1621872000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621953470000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"不需要权限的悬浮窗方案了解一下~","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18412,"link":"https://mp.weixin.qq.com/s/iy5sU9NnqgzJweUFxxvrAg","niceDate":"2021-05-24 00:00","niceShareDate":"2021-05-24 23:00","origin":"","prefix":"","projectLink":"","publishTime":1621785600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621868452000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"微信团队自研的APM利器，Matrix性能监控日志探索","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18411,"link":"https://mp.weixin.qq.com/s/mPrVnQXQSdXPsaRxhY0ETg","niceDate":"2021-05-22 00:00","niceShareDate":"2021-05-24 23:00","origin":"","prefix":"","projectLink":"","publishTime":1621612800000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621868433000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"聊点面试真相 ，如何提高简历通过率？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18410,"link":"https://mp.weixin.qq.com/s/zW6X1CTnjdb3NX-d7nr6cw","niceDate":"2021-05-21 00:00","niceShareDate":"2021-05-24 23:00","origin":"","prefix":"","projectLink":"","publishTime":1621526400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621868414000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Jetpack 这么讲就懂了，LiveData原理、粘性事件掌握！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18372,"link":"https://mp.weixin.qq.com/s/6Uema8qL5H_uoVh2y9lS5A","niceDate":"2021-05-20 00:00","niceShareDate":"2021-05-20 22:11","origin":"","prefix":"","projectLink":"","publishTime":1621440000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621519901000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"程序员该如何写好自己的简历，一位 5 年中大厂老哥跟你聊聊","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18363,"link":"https://mp.weixin.qq.com/s/QTaz45aLucX9mivVMbCZPQ","niceDate":"2021-05-19 00:00","niceShareDate":"2021-05-19 23:00","origin":"","prefix":"","projectLink":"","publishTime":1621353600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621436434000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"新技术系列：RecyclerView 的新伙伴 ConcatAdapter","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18341,"link":"https://mp.weixin.qq.com/s/kyG2sfhuVFHB85SQuxF_VQ","niceDate":"2021-05-18 00:00","niceShareDate":"2021-05-18 21:48","origin":"","prefix":"","projectLink":"","publishTime":1621267200000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621345726000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"两年Android研发，大厂面试问什么？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18340,"link":"https://mp.weixin.qq.com/s/3iZOIy5-F5qfXS22buFxrA","niceDate":"2021-05-17 00:00","niceShareDate":"2021-05-18 21:48","origin":"","prefix":"","projectLink":"","publishTime":1621180800000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621345708000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"androidx来袭，Fragment如何更简单的实现懒加载？","type":0,"userId":-1,"visible":1,"zan":0}],"offset":0,"over":false,"pageCount":71,"size":20,"total":1404}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18587,"link":"https://mp.weixin.qq.com/s/HVA9vTN5n2QQwE4B6oyuuQ","niceDate":"1天前","niceShareDate":"19小时前","origin":"","prefix":"","projectLink":"","publishTime":1623254400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1623336655000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"让你迷惑的 Kotlin 代码","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18575,"link":"https://mp.weixin.qq.com/s/E8v-8mVhc_REXCftNOZ7cg","niceDate":"2天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1623168000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1623250842000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"新技术| ViewBinding 与 Kotlin 委托双剑合璧","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18558,"link":"https://mp.weixin.qq.com/s/PxrW7LSDuAAeqlXFxnIInA","niceDate":"2021-06-08 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1623081600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1623163980000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android 混淆，新引入的D8、R8改变了什么？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18557,"link":"https://mp.weixin.qq.com/s/xGD68ia_9VgL-qgbZM4m3g","niceDate":"2021-06-07 00:00","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1622995200000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1623163965000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"直面底层 | ImageView scaleType是如何实现的？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18535,"link":"https://mp.weixin.qq.com/s/lN4nIeYoSg06fMaAoi6W-A","niceDate":"2021-06-04 00:00","niceShareDate":"2021-06-06 22:15","origin":"","prefix":"","projectLink":"","publishTime":1622736000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622988955000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android中竟然包含这么多设计模式，一起来学一波！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18517,"link":"https://mp.weixin.qq.com/s/nEvBqMPrdmirADo-Y6ihYg","niceDate":"2021-06-03 00:00","niceShareDate":"2021-06-03 22:31","origin":"","prefix":"","projectLink":"","publishTime":1622649600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622730709000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"遇到个难题，怎么修改子View绘制顺序？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18506,"link":"https://mp.weixin.qq.com/s/6OaE2SZHFQQfiE8vadQOaw","niceDate":"2021-06-02 00:00","niceShareDate":"2021-06-02 23:18","origin":"","prefix":"","projectLink":"","publishTime":1622563200000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622647112000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"RecyclerView 中的秘密探索 | 滚动","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18483,"link":"https://mp.weixin.qq.com/s/z3FkJHTSkH_TFis_VOdkSw","niceDate":"2021-06-01 00:00","niceShareDate":"2021-06-01 22:10","origin":"","prefix":"","projectLink":"","publishTime":1622476800000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622556624000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Google 还发布了这个库？ 告别shape、各种 drawable...","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18476,"link":"https://mp.weixin.qq.com/s/MbYlH0gB2EGx-FSmeZh7zA","niceDate":"2021-05-31 00:00","niceShareDate":"2021-05-31 21:41","origin":"","prefix":"","projectLink":"","publishTime":1622390400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622468499000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Compose 1.0 即将发布，你准备好了吗？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18469,"link":"https://mp.weixin.qq.com/s/AguM2NYfflEKin6Nr75XPQ","niceDate":"2021-05-30 00:00","niceShareDate":"2021-05-30 22:27","origin":"","prefix":"","projectLink":"","publishTime":1622304000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622384875000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"一个困惑很久的问题，Android中有子窗口吗？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18451,"link":"https://mp.weixin.qq.com/s/7vWg3epsXIH3hFeohkNUag","niceDate":"2021-05-27 00:00","niceShareDate":"2021-05-27 22:23","origin":"","prefix":"","projectLink":"","publishTime":1622044800000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622125420000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android 事件分发中你可能忽略掉的点！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18440,"link":"https://mp.weixin.qq.com/s/ukFOCvAlMdTYE4t7vTHAsA","niceDate":"2021-05-26 00:00","niceShareDate":"2021-05-26 21:59","origin":"","prefix":"","projectLink":"","publishTime":1621958400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1622037596000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"开启B站少女心，探究APP换肤","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18427,"link":"https://mp.weixin.qq.com/s/UQIC8-SjASHUZkDI97RiWQ","niceDate":"2021-05-25 00:00","niceShareDate":"2021-05-25 22:37","origin":"","prefix":"","projectLink":"","publishTime":1621872000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621953470000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"不需要权限的悬浮窗方案了解一下~","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18412,"link":"https://mp.weixin.qq.com/s/iy5sU9NnqgzJweUFxxvrAg","niceDate":"2021-05-24 00:00","niceShareDate":"2021-05-24 23:00","origin":"","prefix":"","projectLink":"","publishTime":1621785600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621868452000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"微信团队自研的APM利器，Matrix性能监控日志探索","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18411,"link":"https://mp.weixin.qq.com/s/mPrVnQXQSdXPsaRxhY0ETg","niceDate":"2021-05-22 00:00","niceShareDate":"2021-05-24 23:00","origin":"","prefix":"","projectLink":"","publishTime":1621612800000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621868433000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"聊点面试真相 ，如何提高简历通过率？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18410,"link":"https://mp.weixin.qq.com/s/zW6X1CTnjdb3NX-d7nr6cw","niceDate":"2021-05-21 00:00","niceShareDate":"2021-05-24 23:00","origin":"","prefix":"","projectLink":"","publishTime":1621526400000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621868414000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Jetpack 这么讲就懂了，LiveData原理、粘性事件掌握！","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18372,"link":"https://mp.weixin.qq.com/s/6Uema8qL5H_uoVh2y9lS5A","niceDate":"2021-05-20 00:00","niceShareDate":"2021-05-20 22:11","origin":"","prefix":"","projectLink":"","publishTime":1621440000000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621519901000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"程序员该如何写好自己的简历，一位 5 年中大厂老哥跟你聊聊","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18363,"link":"https://mp.weixin.qq.com/s/QTaz45aLucX9mivVMbCZPQ","niceDate":"2021-05-19 00:00","niceShareDate":"2021-05-19 23:00","origin":"","prefix":"","projectLink":"","publishTime":1621353600000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621436434000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"新技术系列：RecyclerView 的新伙伴 ConcatAdapter","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18341,"link":"https://mp.weixin.qq.com/s/kyG2sfhuVFHB85SQuxF_VQ","niceDate":"2021-05-18 00:00","niceShareDate":"2021-05-18 21:48","origin":"","prefix":"","projectLink":"","publishTime":1621267200000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621345726000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"两年Android研发，大厂面试问什么？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"host":"","id":18340,"link":"https://mp.weixin.qq.com/s/3iZOIy5-F5qfXS22buFxrA","niceDate":"2021-05-17 00:00","niceShareDate":"2021-05-18 21:48","origin":"","prefix":"","projectLink":"","publishTime":1621180800000,"realSuperChapterId":407,"selfVisible":0,"shareDate":1621345708000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"androidx来袭，Fragment如何更简单的实现懒加载？","type":0,"userId":-1,"visible":1,"zan":0}]
         * offset : 0
         * over : false
         * pageCount : 71
         * size : 20
         * total : 1404
         */

        private int curPage;
        private List<DatasBean> datas;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public static class DatasBean extends BaseObservable {
            /**
             * apkLink :
             * audit : 1
             * author : 鸿洋
             * canEdit : false
             * chapterId : 408
             * chapterName : 鸿洋
             * collect : false
             * courseId : 13
             * desc :
             * descMd :
             * envelopePic :
             * fresh : false
             * host :
             * id : 18587
             * link : https://mp.weixin.qq.com/s/HVA9vTN5n2QQwE4B6oyuuQ
             * niceDate : 1天前
             * niceShareDate : 19小时前
             * origin :
             * prefix :
             * projectLink :
             * publishTime : 1623254400000
             * realSuperChapterId : 407
             * selfVisible : 0
             * shareDate : 1623336655000
             * shareUser :
             * superChapterId : 408
             * superChapterName : 公众号
             * tags : [{"name":"公众号","url":"/wxarticle/list/408/1"}]
             * title : 让你迷惑的 Kotlin 代码
             * type : 0
             * userId : -1
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private int audit;
            private String author;
            private boolean canEdit;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String descMd;
            private String envelopePic;
            private boolean fresh;
            private String host;
            private int id;
            private String link;
            private String niceDate;
            private String niceShareDate;
            private String origin;
            private String prefix;
            private String projectLink;
            private long publishTime;
            private int realSuperChapterId;
            private int selfVisible;
            private long shareDate;
            private String shareUser;
            private int superChapterId;
            private String superChapterName;
            private List<TagsBean> tags;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public boolean isCanEdit() {
                return canEdit;
            }

            public void setCanEdit(boolean canEdit) {
                this.canEdit = canEdit;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getDescMd() {
                return descMd;
            }

            public void setDescMd(String descMd) {
                this.descMd = descMd;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            @Bindable
            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
                notifyPropertyChanged(BR.link);
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getNiceShareDate() {
                return niceShareDate;
            }

            public void setNiceShareDate(String niceShareDate) {
                this.niceShareDate = niceShareDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getRealSuperChapterId() {
                return realSuperChapterId;
            }

            public void setRealSuperChapterId(int realSuperChapterId) {
                this.realSuperChapterId = realSuperChapterId;
            }

            public int getSelfVisible() {
                return selfVisible;
            }

            public void setSelfVisible(int selfVisible) {
                this.selfVisible = selfVisible;
            }

            public long getShareDate() {
                return shareDate;
            }

            public void setShareDate(long shareDate) {
                this.shareDate = shareDate;
            }

            public String getShareUser() {
                return shareUser;
            }

            public void setShareUser(String shareUser) {
                this.shareUser = shareUser;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public static class TagsBean {
                /**
                 * name : 公众号
                 * url : /wxarticle/list/408/1
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
