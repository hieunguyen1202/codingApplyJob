package com.swp.server.services.auth;

import com.swp.server.dto.*;
import com.swp.server.entities.Profile;
import com.swp.server.entities.Account;
import com.swp.server.repository.AccountRepo;
import com.swp.server.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Override
    public ResponseEntity<?> createProfile(ProfileDTO profileDTO) {
        try {
            Optional<Profile> checkPhoneNumberExist = profileRepo.findFirstByPhoneNumber(profileDTO.getPhoneNumber());
            if (checkPhoneNumberExist.isPresent()) {
                Map<String, String> error = new HashMap<String, String>();
                error.put("error", "Phone number is existed  !!!");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            String phoneNumberRegex = "^(\\+84|0)(3[2-9]|5[2689]|7[06-9]|8[1-689]|9[0-9])\\d{7}$";
            Pattern patternPhone = Pattern.compile(phoneNumberRegex);
            Matcher matcherPhone = patternPhone.matcher(profileDTO.getPhoneNumber());
            if(matcherPhone.matches() == false){
                Map<String, String> error = new HashMap<>();
                error.put("error", "Invalid phone number!");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            if(!(profileDTO.getFirstName().trim().matches("^[A-Za-z ]+$")) || !(profileDTO.getLastName().trim().matches("^[A-Za-z ]+$"))){
                Map<String, String> error = new HashMap<>();
                error.put("error", "Invalid name!");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            if(!(profileDTO.getAddress().trim().matches("^[a-zA-Z_ÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\ ]+$"))){
                Map<String, String> error = new HashMap<>();
                error.put("error", "Invalid address!");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            Optional<Account> findAccountByEmail = accountRepo.findFirstByEmail(profileDTO.getEmail());
            if (findAccountByEmail.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Account not found!");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }

            Account account = findAccountByEmail.get();

            Profile newProfileEntity = new Profile();


            newProfileEntity.setAccount(account);
            newProfileEntity.setAddress(profileDTO.getAddress());
            newProfileEntity.setFirstName(profileDTO.getFirstName());
            newProfileEntity.setLastName(profileDTO.getLastName());
            newProfileEntity.setGender(profileDTO.isGender());
            newProfileEntity.setPhoneNumber(profileDTO.getPhoneNumber());
            newProfileEntity.setAvatar(profileDTO.getAvatar().getBytes());
            newProfileEntity.setCV(profileDTO.getCV().getBytes());
            Profile newProfile = profileRepo.save(newProfileEntity);

            Map<String, String> success = new HashMap<String, String>();
            success.put("success", "Create profile successfully !!!");
            return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "INTERNAL SERVER ERROR !!!");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
