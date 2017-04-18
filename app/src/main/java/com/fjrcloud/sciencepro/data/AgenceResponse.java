package com.fjrcloud.sciencepro.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lin on 2017/2/25.
 */

public class AgenceResponse extends BaseBean<AgenceResponse.AgenceEntity>{


    private AgenceEntity data;

    public AgenceEntity getData() {
        return data;
    }

    public void setData(AgenceEntity data) {
        this.data = data;
    }

    public static class AgenceEntity implements Serializable{
        private int totalPages;
        private int totalElements;
        private boolean last;
        private int numberOfElements;
        private Object sort;
        private boolean first;
        private int size;
        private int number;

        private List<Agence> content;

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

        public List<Agence> getContent() {
            return content;
        }

        public void setContent(List<Agence> content) {
            this.content = content;
        }

        public static class Agence implements Serializable{
            private int id;
            private String name;
            private String phone;
            private String email;
            private String head;
            private String duty;
            private long createTime;

            public Agence(String name, String phone, String email, String head, String duty) {
                this.name = name;
                this.phone = phone;
                this.email = email;
                this.head = head;
                this.duty = duty;
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

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public String getDuty() {
                return duty;
            }

            public void setDuty(String duty) {
                this.duty = duty;
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
