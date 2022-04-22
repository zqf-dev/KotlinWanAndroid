package com.zqf.kotlinwanandroid.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author: zqf
 * Date: 2021/12/24
 * 体系Bean
 */
public class SysTabTreeEntity implements Parcelable {

    private List<ChildrenBean> children;
    private int courseId;
    private int id;
    private String name;
    private int order;
    private int parentChapterId;
    private boolean userControlSetTop;
    private int visible;

    protected SysTabTreeEntity(Parcel in) {
        courseId = in.readInt();
        id = in.readInt();
        name = in.readString();
        order = in.readInt();
        parentChapterId = in.readInt();
        userControlSetTop = in.readByte() != 0;
        visible = in.readInt();
    }

    public static final Creator<SysTabTreeEntity> CREATOR = new Creator<SysTabTreeEntity>() {
        @Override
        public SysTabTreeEntity createFromParcel(Parcel in) {
            return new SysTabTreeEntity(in);
        }

        @Override
        public SysTabTreeEntity[] newArray(int size) {
            return new SysTabTreeEntity[size];
        }
    };

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(courseId);
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(order);
        dest.writeInt(parentChapterId);
        dest.writeByte((byte) (userControlSetTop ? 1 : 0));
        dest.writeInt(visible);
    }

    public static class ChildrenBean implements Parcelable {
        private List<?> children;
        private int courseId;
        private int id;
        private String name;
        private int order;
        private int parentChapterId;
        private boolean userControlSetTop;
        private int visible;

        protected ChildrenBean(Parcel in) {
            courseId = in.readInt();
            id = in.readInt();
            name = in.readString();
            order = in.readInt();
            parentChapterId = in.readInt();
            userControlSetTop = in.readByte() != 0;
            visible = in.readInt();
        }

        public static final Creator<ChildrenBean> CREATOR = new Creator<ChildrenBean>() {
            @Override
            public ChildrenBean createFromParcel(Parcel in) {
                return new ChildrenBean(in);
            }

            @Override
            public ChildrenBean[] newArray(int size) {
                return new ChildrenBean[size];
            }
        };

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getParentChapterId() {
            return parentChapterId;
        }

        public void setParentChapterId(int parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public boolean isUserControlSetTop() {
            return userControlSetTop;
        }

        public void setUserControlSetTop(boolean userControlSetTop) {
            this.userControlSetTop = userControlSetTop;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(courseId);
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeInt(order);
            dest.writeInt(parentChapterId);
            dest.writeByte((byte) (userControlSetTop ? 1 : 0));
            dest.writeInt(visible);
        }
    }
}
