package com.ms_login.logic;

import com.ms_login.constant.Constants;
import com.ms_login.dto.LoginRequest;
import com.ms_login.dto.UserCreateRequest;
import com.ms_login.exception.*;
import com.ms_login.repository.UserRepository;
import com.ms_login.services.UserService;
import com.ms_login.entity.User;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.time.LocalDate;
import java.util.*;

@Service
 class UserLogic implements UserService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    public UserLogic(UserRepository userRepository, JavaMailSender mailSender,
                     PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }




    @Override
    public ResponseEntity<String> saveUser(UserCreateRequest userCreateRequest) throws PasswordNotUppercase, PasswordShortException, UserExistException {
        Optional<User> temp = userRepository.findByEmial(userCreateRequest.getEmail());
        try {
            if (temp.isPresent()) {
                throw new UserExistException(userCreateRequest.getEmail());
            }else{
                validationPassword(userCreateRequest);
                validationEmail(userCreateRequest);
                validationUsername(userCreateRequest);

                User newUser = new User(userCreateRequest.getUserName(),
                        passwordEncoder.encode(userCreateRequest.getPassword()),
                        userCreateRequest.getEmail(),
                        1,
                        LocalDate.now(),
                        LocalDate.now());
                userRepository.save(newUser);
                return new ResponseEntity<String>("User saved successfully", HttpStatusCode.valueOf(200));
            }

        }catch (UserExistException ex){
            ex.printStackTrace();
            return new ResponseEntity<String>("User already exist", HttpStatusCode.valueOf(400));
        } catch (PasswordShortException ex) {
            ex.printStackTrace();
            return  new ResponseEntity<String>("Invalid password because is too short", HttpStatusCode.valueOf(400));
        }catch (PasswordNotContentSpecialCharacterException ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>("Invalid password because it doesn't contain special character", HttpStatusCode.valueOf(400));
        }catch (PasswordNotUppercase ex){
            ex.printStackTrace();
            return new ResponseEntity<String>("Invalid password because it doesn't contain Uppercase", HttpStatusCode.valueOf(400));
        }catch (DomainNoExistException ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>(ex.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    @Override
    public ResponseEntity<String> loginUser(LoginRequest loginRequest) {
         Optional<User> temp = userRepository.findByEmial(loginRequest.getEmail());
         try{
             if(temp.isPresent()){
                if( passwordEncoder.matches(loginRequest.getPassword(),temp.get().getPassword())){

                    return new ResponseEntity<String>(" login successful", HttpStatusCode.valueOf(200));
                }else {

                    return new ResponseEntity<String >("Invalid password", HttpStatusCode.valueOf(400));
                }

             }else{
                 throw new UserNotExistException(Constants.userNotExist);
             }
         }catch (UserNotExistException ex){
             ex.printStackTrace();
             return  new ResponseEntity<String>(Constants.userNotExist, HttpStatusCode.valueOf(400));
         }

    }

    @Override
    public void validationPassword(UserCreateRequest userCreateRequest) {
        if(userCreateRequest.getPassword().length() < 6){
            throw new PasswordShortException("Password too short");
        }

        for (int i = 0; i < userCreateRequest.getPassword().length(); i++) {
            if(Character.isLetter(userCreateRequest.getPassword().charAt(i))){
                if(Character.isUpperCase(userCreateRequest.getPassword().charAt(i))){
                    break;
                }
            }
            if(i == userCreateRequest.getPassword().length() - 1){
                throw new PasswordNotUppercase("Password doesn't contain uppercase character");
            }
        }

        List<Character> x= new ArrayList<Character>();

        for (int i = 0; i < userCreateRequest.getPassword().length(); i++) {
            if(!Character.isLetter(userCreateRequest.getPassword().charAt(i)) || !Character.isDigit(userCreateRequest.getPassword().charAt(i)) ){
                break;
            }
            if(i == userCreateRequest.getPassword().length() - 1){
                throw  new PasswordNotContentSpecialCharacterException("Password doesn't contain special character");
            }
        }

    }

    @Override
    public void validationEmail(UserCreateRequest userCreateRequest) {
        //send email to the verification
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(userCreateRequest.getEmail());
//        message.setSubject("Verifica tu cuenta");
//        message.setText("Haz clic en el siguiente enlace...");
//
//        mailSender.send(message);
        try {
            String domain = userCreateRequest.getEmail().substring(userCreateRequest.getEmail().indexOf("@")+1);

            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial",
                    "com.sun.jndi.dns.DnsContextFactory");

            DirContext dirContext = new InitialDirContext(env);

            Attributes attrs = dirContext.getAttributes(domain,
                    new String[]{"MX"});

            Attribute attr = attrs.get("MX");

            if (attrs.get("MX") == null) {
                throw new DomainNoExistException("Domain Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void validationUsername(UserCreateRequest userCreateRequest) {
        if(userCreateRequest.getUserName().length() < 6){
            throw new UserExistException("Username too short");
        }
    }

}
