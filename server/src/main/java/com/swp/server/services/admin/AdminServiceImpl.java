package com.swp.server.services.admin;

import com.swp.server.dto.BlockAccountDTO;
import com.swp.server.dto.CreateAccountDTO;
import com.swp.server.dto.ProfileDTO;
import com.swp.server.entities.Account;
import com.swp.server.entities.Profile;
import com.swp.server.entities.Role;
import com.swp.server.repository.AccountRepo;
import com.swp.server.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Override
    public ResponseEntity<?> viewListAccounts() {
        try {
            List<Account> accounts = accountRepo.findBy();
            if (accounts.isEmpty()) {
                Map<String, String> error = new HashMap<String, String>();
                error.put("error", "There's nothing to display");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            List<ProfileDTO> responseProfileDTO = new ArrayList<>();
            for(Account a: accounts){
                Profile profileEntity = a.getProfile();
                if (profileEntity == null) {
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "Profile not found!");
                    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
                }
                ProfileDTO responseDTO = new ProfileDTO();
                responseDTO.setFirstName(profileEntity.getFirstName());
                responseDTO.setLastName(profileEntity.getLastName());
                responseDTO.setPhoneNumber(profileEntity.getPhoneNumber());
                responseDTO.setAddress(profileEntity.getAddress());
                responseDTO.setGender(profileEntity.isGender());
                responseProfileDTO.add(responseDTO);

            }
            return ResponseEntity.ok(responseProfileDTO);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "INTERNAL SERVER ERROR !!!");
            return new ResponseEntity<>(error, HttpStatus.ACCEPTED);
        }
    }

    @Override
    public ResponseEntity<?> createAccount(CreateAccountDTO createAccountDTO) {
        try {
            Optional<Account> checkUsernameExist = accountRepo.findFirstByUsername(createAccountDTO.getUsername());
            if (checkUsernameExist.isPresent()) {
                Map<String, String> error = new HashMap<String, String>();
                error.put("error", "Username is existed  !!!");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            Optional<Account> checkEmailExist = accountRepo.findFirstByEmail(createAccountDTO.getEmail());
            if (checkEmailExist.isPresent()) {
                Map<String, String> error = new HashMap<String, String>();
                error.put("error", "Email is existed  !!!");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            String passwordRegex = "^(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
            Pattern patternPassword = Pattern.compile(passwordRegex);
            Matcher matcherPassword = patternPassword.matcher(createAccountDTO.getPassword());
            if (!matcherPassword.matches()) {
                Map<String, String> error = new HashMap<String, String>();
                error.put("error",
                        "Password at least 8 characrter , 1 uppercase , 1 special character and should not contain any whitespace characters.  !!!");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            String role = createAccountDTO.getRole();
            if(role.isEmpty()){
                Map<String, String> error = new HashMap<String, String>();
                error.put("error", "Role is empty !!!");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            Role roleEntity = roleRepo.findByName(role.toUpperCase());
            if(roleEntity == null){
                Map<String, String> error = new HashMap<String, String>();
                error.put("error", "Role is not found !!!");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }

            Account newAccountEntity = new Account();
            newAccountEntity.setUsername(createAccountDTO.getUsername());
            newAccountEntity.setEmail(createAccountDTO.getEmail());
            newAccountEntity.setPassword(new BCryptPasswordEncoder().encode(createAccountDTO.getPassword()));
            newAccountEntity.setVerify(true);
            newAccountEntity.setEnabled(true);
            newAccountEntity.setRole(roleEntity);

            Account newAccount = accountRepo.save(newAccountEntity);

            Map<String, String> success = new HashMap<String, String>();
            success.put("success", "Sign up successfully !!!");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }catch (Exception e){
            Map<String, Object> error = new HashMap<>();
            error.put("error", "INTERNAL SERVER ERROR !!!");
            return new ResponseEntity<>(error, HttpStatus.ACCEPTED);
        }
    }

    @Override
    public ResponseEntity<?> blockAccount(BlockAccountDTO blockAccountDTO) {
        try{
            Optional<Account> checkEmailExisted = accountRepo.findFirstByEmail(blockAccountDTO.getEmail());
            if (!checkEmailExisted.isPresent()) {
                Map<String, String> error = new HashMap<String, String>();
                error.put("error", "Email is not existed  !!!");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            Account blockAccount = checkEmailExisted.get();
            if(!blockAccountDTO.isEnabled()){
                blockAccount.setEnabled(false);
                accountRepo.save(blockAccount);
                Map<String, Object> msg = new HashMap<>();
                msg.put("msg", "Block account "+blockAccount.getEmail() +" successfully !!!");
                return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
            }else{
                blockAccount.setEnabled(true);
                accountRepo.save(blockAccount);
                Map<String, Object> msg = new HashMap<>();
                msg.put("msg", "Unblock account "+blockAccount.getEmail()+" successfully !!!");
                return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
            }

        }catch (Exception e){
            Map<String, Object> error = new HashMap<>();
            error.put("error", "INTERNAL SERVER ERROR !!!");
            return new ResponseEntity<>(error, HttpStatus.ACCEPTED);
        }
    }
}
