package com.xsy.xframe.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @Description描述:
 * @Author作者: xuesanyang
 * @Date日期: 2018/5/8
 */

public class NewsBean {

    private String ResultMessage;
    private int ResultCode;
    private int PagesCount;
    private int RecordsCount;
    private int PageIndex;
    private int PageSize;
    private boolean AllowPaging;
    private List<DataBean> Data;

    public String getResultMessage() {
        return ResultMessage;
    }

    public int getResultCode() {
        return ResultCode;
    }

    public int getPagesCount() {
        return PagesCount;
    }

    public int getRecordsCount() {
        return RecordsCount;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public boolean isAllowPaging() {
        return AllowPaging;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "ID=" + ID +
                    ", Title='" + Title + '\'' +
                    ", Body='" + Body + '\'' +
                    ", LastModifiedTime='" + LastModifiedTime + '\'' +
                    ", Category='" + Category + '\'' +
                    ", MainImage='" + MainImage + '\'' +
                    ", NewLableList=" + NewLableList +
                    ", IsCollectLink=" + IsCollectLink +
                    ", ReadingQuantity=" + ReadingQuantity +
                    ", ReleaseTime='" + ReleaseTime + '\'' +
                    ", HelpfulCount=" + HelpfulCount +
                    ", Author=" + Author +
                    ", SourceName=" + SourceName +
                    ", Abstract=" + Abstract +
                    ", Sort=" + Sort +
                    '}';
        }

        /**
         * ID : 9993
         * Title : 老公喝醉了，老婆应该这样做
         * Body : 周末啦！忙碌一周，呼朋唤友，大家欢聚一起，聊聊工作谈谈生活。说到聚会，餐桌上少不了的就是酒，在我们尽兴之余，难免会多饮几杯。我们都知道过度饮酒不好，可是欢聚时分，不忍拒绝亲友的美意，那么很多人就会问，怎么样解酒，怎么样避免过度饮酒出现一些其他的不适情况。今天我们就来共同认识一下怎样减少酒精对我们人体的伤害。理想解酒速度，即不超过肝脏处理能力的饮酒速度。一般肝脏分解酒精的速度是每小时约10毫升，酒中所含的纯酒精(乙醇)的量，可以通过酒瓶标签上标示的度数计算出来。建议在饮酒前注意计算，同时结合自身的实际情况来合理选择低度的白酒或者其他酒精性的饮品，当然也存在一些个体差异，有的朋友酒精代谢速度较快，酒量会大一些。一、饮酒前，可以吃一些蛋白含量高的食物。可以喝一杯牛奶、酸奶、甜汤或者吃一些甜食、面粥等食物，是可以保护胃黏膜，也是可以稍稍提高饮酒量，避免出现醉酒的症状。二、饮酒后建议可以适当喝一些饮料、果汁或者茶等来帮助解酒。其中被乾隆称为“御茶”的解酒护肝茶是居于首位的。主要由葛花、决明子、陈皮、黄山贡菊等组成的，是能有效减轻因饮酒导致的头痛、头晕、胃痛、恶心、精神不佳等症状。另外淡盐水、含糖高的食物或者是运动型饮料也都是有一定的效果。一些男性朋友，在生活中往往不管妻子在家如何交待，外出依旧可劲的喝，然后醉酒回家。这个时候，另一半就需要特别注意，如果丈夫有呕吐的情况，先注意观察呕吐物的颜色和性状，如果有正常的食物残渣，是要在呕吐后是将口腔清洗干净，并给于淡盐水漱口，适当的补充一些含糖高的水分，避免出现大量胃内容物丢失导致的脱水和电解质紊乱等；如果在饮水后仍有频繁呕吐的情况，是需要禁食4-6小时左右并进一步观察，采取去枕平卧，将头偏向一侧，预防出现呕吐物阻塞气道导致窒息，从而引发生命危险；如果出现打呼、嗜睡、不能唤醒，呕吐物为咖啡色或暗红色等情况，是需要尽快送医院就诊，采取药物帮助醒酒，减少酒精对我们的肝脏、胃以及其他内脏的损伤，另外有必要的话还需要排除一些酒精诱发导致的其他严重疾病的可能。作者：张小红  指导医生：王滨  简介：王滨，内科住院医师, 中国平安全职专家。
         * LastModifiedTime : 2016-09-19 00:00:00
         * Category : jk.cn/中医
         * MainImage : http://upload.jkbat.com/image-workflow/images/3bb1470cb4bd889080456ceb2d88921eb0529894664d719c8bc3bab86414e5e1.jpg
         * NewLableList : null
         * IsCollectLink : 0
         * ReadingQuantity : 99229
         * ReleaseTime : 2016-09-19 00:00:00
         * HelpfulCount : 0
         * Author : null
         * SourceName : null
         * Abstract : null
         * Sort : 0
         */

        private int ID;
        private String Title;
        private String Body;
        private String LastModifiedTime;
        private String Category;
        private String MainImage;
        private Object NewLableList;
        private int IsCollectLink;
        private int ReadingQuantity;
        private String ReleaseTime;
        private int HelpfulCount;
        private Object Author;
        private Object SourceName;
        private Object Abstract;
        private int Sort;

        public int getID() {
            return ID;
        }

        public String getTitle() {
            return Title;
        }

        public String getBody() {
            return Body;
        }

        public String getLastModifiedTime() {
            return LastModifiedTime;
        }

        public String getCategory() {
            return Category;
        }

        public String getMainImage() {
            return MainImage;
        }

        public Object getNewLableList() {
            return NewLableList;
        }

        public int getIsCollectLink() {
            return IsCollectLink;
        }

        public int getReadingQuantity() {
            return ReadingQuantity;
        }

        public String getReleaseTime() {
            return ReleaseTime;
        }

        public int getHelpfulCount() {
            return HelpfulCount;
        }

        public Object getAuthor() {
            return Author;
        }

        public Object getSourceName() {
            return SourceName;
        }

        public Object getAbstract() {
            return Abstract;
        }

        public int getSort() {
            return Sort;
        }
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "ResultMessage=" + ResultMessage +
                ", ResultCode=" + ResultCode +
                ", PagesCount=" + PagesCount +
                ", RecordsCount=" + RecordsCount +
                ", PageIndex=" + PageIndex +
                ", PageSize=" + PageSize +
                ", AllowPaging=" + AllowPaging +
                ", Data=" + Data +
                '}';
    }
}
