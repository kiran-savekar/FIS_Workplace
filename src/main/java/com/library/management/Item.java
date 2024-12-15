package com.library.management;

public class Item {

        private int id;
        private String title;

        public Item(int id, String title) {
            this.id = id;
            this.title = title;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Item [ID: " + id + ", Title: " + title + "]";
        }

}
