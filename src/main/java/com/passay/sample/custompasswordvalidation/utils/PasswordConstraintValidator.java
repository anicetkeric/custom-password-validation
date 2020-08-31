package com.passay.sample.custompasswordvalidation.utils;

import com.passay.sample.custompasswordvalidation.annotation.ValidPassword;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <h2>PasswordConstraintValidator</h2>
 *
 * @author aek
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(final ValidPassword arg0) {

    }


/*   LengthRule(8,30)-impose la longueur du mot de passe entre 8 et 30 caractères.

    CharacterRule(EnglishCharacterData.UpperCase,1)-applique le mot de passe à au moins 1 caractère majuscule.

    CharacterRule(EnglishCharacterData.LowerCase,1)-applique le mot de passe à au moins 1 caractère minuscule.

    CharacterRule(EnglishCharacterData.Digit,1)-applique le mot de passe à au moins 1 caractère numérique.

    CharacterRule(EnglishCharacterData.Special,1)-applique le mot de passe à au moins 1 symbole(caractère spécial).

    WhitespaceRule-applique le mot de passe ne contient pas d'espace.*/
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(

                // at least 8 characters
                new LengthRule(8, 30),

                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special, 1),

                // no whitespace
                new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        List<String> messages = validator.getMessages(result);
        String messageTemplate = messages.stream().collect(Collectors.joining(","));
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }

}
