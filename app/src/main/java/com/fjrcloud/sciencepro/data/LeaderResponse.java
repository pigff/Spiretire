package com.fjrcloud.sciencepro.data;

import java.util.List;

/**
 * Created by lin on 2017/3/9.
 */

public class LeaderResponse extends BaseBean<LeaderResponse.LeaderEntity> {


    private LeaderEntity data;

    public LeaderEntity getData() {
        return data;
    }

    public void setData(LeaderEntity data) {
        this.data = data;
    }

    public static class LeaderEntity {
        private int totalPages;
        private int totalElements;
        private boolean last;
        private int numberOfElements;
        private Object sort;
        private boolean first;
        private int size;
        private int number;

        private List<Leader> content;

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<Leader> getContent() {
            return content;
        }

        public void setContent(List<Leader> content) {
            this.content = content;
        }

        public static class Leader {
            private int id;
            private String name;
            private String photo;
            private String duty;
            private String dutyExplain;
            private String phone;
            private String email;
            private long createTime;

            public Leader(String name, String photo, String duty, String dutyExplain, String phone, String email) {
                this.name = name;
                this.photo = photo;
                this.duty = duty;
                this.dutyExplain = dutyExplain;
                this.phone = phone;
                this.email = email;
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

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getDuty() {
                return duty;
            }

            public void setDuty(String duty) {
                this.duty = duty;
            }

            public String getDutyExplain() {
                return dutyExplain;
            }

            public void setDutyExplain(String dutyExplain) {
                this.dutyExplain = dutyExplain;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }
    }
}
