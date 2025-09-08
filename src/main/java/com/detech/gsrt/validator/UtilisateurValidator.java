package com.detech.gsrt.validator;

import com.detech.gsrt.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Autor luca
 */
public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto dto){
        List<String> errors = new ArrayList<>();

        if(dto == null){
            errors.add("Veuillez renseigner le nom de l'utilisateur");
            errors.add("Veuillez renseigner l'adress mail de l'utilisateur");
            errors.add("Veuillez renseigner le role de l'utilisateur");
            return errors;
        }

        if(!StringUtils.hasLength(dto.getNom())){
            errors.add("Veuillez renseigner le nom de l'utilisateur");
        }

        if(!StringUtils.hasLength(dto.getEmail())){
            errors.add("Veuillez renseigner l'adress mail de l'utilisateur");
        }

        if(dto.getRole() == null){
            errors.add("Veuillez renseigner le role de l'utilisateur");
        }

        return errors;
    }
}
