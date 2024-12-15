package com.library.management;

public class Book extends Item {

        private String author;

        public Book(int id, String title, String author) {
            super(id, title);
            this.author = author;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        @Override
        public String toString() {
            return super.toString() + ", Author: " + author;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Book with ID: " + getId() + " is being garbage collected.");
        }
}
