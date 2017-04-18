package com.fjrcloud.sciencepro.data;

import java.io.Serializable;

/**
 * Created by lin on 2017/2/24.
 */

public class NewsResponse extends BaseBean {

   public static class News implements Serializable {
       private String title;
       private String imgUrl;
       private String data;
       private String content;

       public News(String title, String imgUrl, String data, String content) {
           this.title = title;
           this.imgUrl = imgUrl;
           this.data = data;
           this.content = content;
       }

       public String getTitle() {
           return title;
       }

       public void setTitle(String title) {
           this.title = title;
       }

       public String getImgUrl() {
           return imgUrl;
       }

       public void setImgUrl(String imgUrl) {
           this.imgUrl = imgUrl;
       }

       public String getData() {
           return data;
       }

       public void setData(String data) {
           this.data = data;
       }

       public String getContent() {
           return content;
       }

       public void setContent(String content) {
           this.content = content;
       }
   }
}
