package com.infosys.agentdashboard.models;

import java.util.ArrayList;

public class UserLst {


        private String name;
        private ArrayList<String> skill;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<String> getSkill() {
            return skill;
        }

        public void setSkill(ArrayList<String> arrayList) {
            this.skill = arrayList;
        }


}
