package com.lookballs.app.mvvm.ui.bean;

import androidx.databinding.Bindable;

import com.lookballs.app.mvvm.BR;
import com.lookballs.mvvm.core.adapter.BaseBindingMultiItemBean;

import java.util.List;

public class ArticleJsonBean extends BaseBean {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<DatasBean> datas;

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean extends BaseBindingMultiItemBean {
            private int id;
            private String link;

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

            @Override
            public int getItemType() {
                return 1;
            }
        }
    }
}
