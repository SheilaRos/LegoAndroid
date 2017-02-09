package com.example.dam.lego;
import android.media.Image;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//Resultado de las cajas que tienen un tema
public class CajaList {
        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("next")
        @Expose
        private String next;
        @SerializedName("previous")
        @Expose
        private Object previous;
        @SerializedName("results")
        @Expose
        private List<Caja> results = null;
        public Integer getCount() {
            return count;
        }
        public void setCount(Integer count) {
            this.count = count;
        }
        public String getNext() {
            return next;
        }
        public void setNext(String next) {
            this.next = next;
        }
        public Object getPrevious() {
            return previous;
        }
        public void setPrevious(Object previous) {
            this.previous = previous;
        }
        public List<Caja> getResults() {
            return results;
        }
        public void setResults(List<Caja> results) {
            this.results = results;
        }

    @Override
    public String toString() {
        return "CajaList{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous=" + previous +
                ", results=" + results +
                '}';
    }
}
