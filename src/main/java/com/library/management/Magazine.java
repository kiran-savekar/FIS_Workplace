package com.library.management;

public class Magazine  extends Item{

        private int issueNumber;

        public Magazine(int id, String title, int issueNumber) {
            super(id, title);
            this.issueNumber = issueNumber;
        }

        public int getIssueNumber() {
            return issueNumber;
        }

        public void setIssueNumber(int issueNumber) {
            this.issueNumber = issueNumber;
        }

        @Override
        public String toString() {
            return super.toString() + ", Issue Number: " + issueNumber;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Magazine with ID: " + getId() + " is being garbage collected.");
        }

}
