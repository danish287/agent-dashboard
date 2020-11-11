package com.infosys.agentdashboard.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.infosys.agentdashboard.entity.UserEntity;
import com.infosys.agentdashboard.models.User;
import com.infosys.agentdashboard.models.UserLst;

import com.infosys.agentdashboard.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    private static Log log = (Log) LogFactory.getLog(UserService.class);

    public List<UserEntity> addUser(User user){
        UserEntity newUser = new UserEntity();
        newUser.setName(user.getName());
        newUser.setSkill(user.getSkill());
        newUser.setUUID(UUID.randomUUID().toString());
        userRepository.saveAndFlush(newUser);
        return userRepository.findAll();
    }

    public List<UserLst> getAll(){
        List<UserEntity> lst = userRepository.findAll();
        HashMap<String, ArrayList<String>> userMap = new HashMap<String, ArrayList<String>>();
        Gson json = new Gson();


        for (UserEntity usr : lst){

            if(userMap.get(usr.getName()) == null){
                ArrayList<String> skills = new ArrayList<String>();
                skills.add(usr.getSkill());
                userMap.put(usr.getName(), skills);
            } else{
                ArrayList<String>  usrLst = userMap.get(usr.getName());
                usrLst.add(usr.getSkill());
            }
        }
        List<UserLst> myLst = new ArrayList<UserLst>();
        
        for (HashMap.Entry<String,ArrayList<String>> entry : userMap.entrySet())
        {
            UserLst newLst = new UserLst();
        	newLst.setName(entry.getKey());
        	newLst.setSkill(entry.getValue());
        	myLst.add(newLst);
        
            log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
        }

//        return json.toJson(userMap);
        return myLst;

    }

    public List<UserEntity> deleteUser(String name){

        List<UserEntity> lst = userRepository.findAll();
        for (UserEntity usr : lst){
            String tempName = usr.getName();
            if(tempName.equals(name)){
                String usrID = usr.getUUID();
                userRepository.deleteById(usrID);
            } else{
                log.info("NOTHING TO DELETE");
            }
        }

        return userRepository.findAll();
    }

    public List<UserEntity> deleteSkill(String name, String skill){

        List<UserEntity> lst = userRepository.findAll();
        for (UserEntity usr : lst){
            String tempName = usr.getName();
            String tempSkill = usr.getSkill();
            if(tempName.equals(name) && tempSkill.equals(skill)){
                String usrID = usr.getUUID();
                userRepository.deleteById(usrID);
            } else{
                log.info("NOTHING TO DELETE");
            }
        }

        return userRepository.findAll();
    }

    public  HashMap<String,ArrayList<String>> getSkillsByID(String name){

        List<UserEntity> lst = userRepository.findAll();
        HashMap<String, ArrayList<String>> userMap = new HashMap<String, ArrayList<String>>();

        for (UserEntity usr : lst){
            String tempName = usr.getName();
            if(tempName.equals(name)){
                log.info("YESSSSS" + usr.getSkill());
                if(userMap.get(name) == null){
                    ArrayList<String> skills = new ArrayList<String>();
                    skills.add(usr.getSkill());
                    userMap.put(usr.getName(), skills);
                    log.info("CREATed " + usr.getName()  + " "+ usr.getSkill());

                } else{
                    log.info("ADDED " + usr.getName()  + " "+ usr.getSkill());

                    ArrayList usrLst = userMap.get(name);
                    usrLst.add(usr.getSkill());
                }


            } else{
                log.info("NOOOOO" + usr.getSkill());

            }
        }

        return userMap;
    }



}
