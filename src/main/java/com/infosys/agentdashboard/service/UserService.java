package com.infosys.agentdashboard.service;

import com.infosys.agentdashboard.entity.UserEntity;
import com.infosys.agentdashboard.models.User;
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

    public HashMap getSkillsByID(String name){

        List<UserEntity> lst = userRepository.findAll();
        HashMap<String, ArrayList<String>> userMap = new HashMap<String, ArrayList<String>>();
        ArrayList<String> scores = new ArrayList<String> ();
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

    public String addUser(User user){
        UserEntity newUser = new UserEntity();
        newUser.setName(user.getName());
        newUser.setSkill(user.getSkill());
        newUser.setUUID(UUID.randomUUID().toString());
        userRepository.saveAndFlush(newUser);
        return "Added successfully";
    }

    public List<UserEntity> getAll(){
        return userRepository.findAll();
    }

}
