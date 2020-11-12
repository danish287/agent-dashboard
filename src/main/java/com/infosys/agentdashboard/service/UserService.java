package com.infosys.agentdashboard.service;


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

    public List <UserEntity> addUser(User user) {
        UserEntity newUser = new UserEntity();
        
        if((user.getName() == null) | (user.getSkill() == null)) {
        	log.info("INVALID USER");
        } else {
        	newUser.setName(user.getName());
        	newUser.setSkill(user.getSkill());
        	newUser.setUUID(UUID.randomUUID().toString());
        	userRepository.saveAndFlush(newUser);
        }
        return userRepository.findAll();
    }

    public List<UserLst> getAll() {
        List <UserEntity> lst = userRepository.findAll();
        TreeMap <String, ArrayList <String>> userMap = new TreeMap < String, ArrayList < String >> ();
        List <UserLst> orderedLst = new ArrayList <UserLst>();


        for (UserEntity usr: lst) {
        	if((usr.getName() == null) | (usr.getSkill() == null)){
                continue;
            }
            if (userMap.get(usr.getName()) == null) {
                ArrayList <String> skills = new ArrayList <String> ();
                skills.add(usr.getSkill());
                userMap.put(usr.getName(), skills);
            } else {
                ArrayList <String> usrLst = userMap.get(usr.getName());
                usrLst.add(usr.getSkill());
            }
        }

        for (HashMap.Entry <String, ArrayList <String>> entry: userMap.entrySet()) {
            UserLst newUser = new UserLst();
            newUser.setName(entry.getKey());
            newUser.setSkill(entry.getValue());
            orderedLst.add(newUser);
        }
        return orderedLst;
    }

    public List <UserEntity> deleteUser(String name) {
    	
        List <UserEntity> lst = userRepository.findAll();
        for (UserEntity usr: lst) {
            String tempName = usr.getName();
            if (tempName != null) {
            	if (tempName.equals(name)) {
            		String usrID = usr.getUUID();
            		userRepository.deleteById(usrID);
            	} 
            }
        }
        return userRepository.findAll();
    }

    public List <UserEntity> deleteSkill(String name, String skill) {

        List <UserEntity> lst = userRepository.findAll();
        for (UserEntity usr: lst) {
            String tempName = usr.getName();
            String tempSkill = usr.getSkill();
            if (tempName != null) {
            	if (tempName.equals(name) && tempSkill.equals(skill)) {
            		String usrID = usr.getUUID();
            		userRepository.deleteById(usrID);
            	} 
            }
        }

        return userRepository.findAll();
    }

    
    public HashMap <String, ArrayList <String>> getSkillsByID(String name) {

        List <UserEntity> lst = userRepository.findAll();
        HashMap <String, ArrayList <String>> userMap = new HashMap <String, ArrayList <String>> ();

        for (UserEntity usr: lst) {
            String tempName = usr.getName();
            
            if (tempName.equals(name)) {
                if (userMap.get(name) == null) {
                    ArrayList <String> skills = new ArrayList <String> ();
                    skills.add(usr.getSkill());
                    userMap.put(usr.getName(), skills);
                } else {
                    ArrayList < String > usrLst = userMap.get(name);
                    usrLst.add(usr.getSkill());
                }

            } 
        }
     
        return userMap;
    }
}