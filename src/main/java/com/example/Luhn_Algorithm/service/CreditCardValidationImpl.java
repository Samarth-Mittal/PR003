package com.example.Luhn_Algorithm.service;

import com.example.Luhn_Algorithm.model.CreditCard;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CreditCardValidationImpl implements  CreditCardValidation{

    @Override
    public boolean isCardValid(String cardNo, String name, String CVV, String expiry_month_year) {

        CreditCard creditCard=new CreditCard();

        creditCard.setCardNo(cardNo);
        creditCard.setHolder_name(name);
        creditCard.setCVV(CVV);
        creditCard.setExpiry_month(Integer.parseInt(expiry_month_year.split("/")[0]));
        creditCard.setExpiry_year(Integer.parseInt(expiry_month_year.split("/")[1]));

        if(!hasExpiryDatePassed(creditCard.getExpiry_month(), creditCard.getExpiry_year()) &&
                luhnAlgorithm(creditCard.getCardNo()) &&
                isNameValid(creditCard.getHolder_name()) &&
                isCVV_Valid(creditCard.getCVV())){
            return true;
        }
        return false;

    }

    private boolean luhnAlgorithm(String cardno){

        boolean isAtEvenPosition=false;
        int no_of_digits=cardno.length();
        int sum=0;
        for(int index=no_of_digits-1;index>=0;--index){
            int digit=cardno.charAt(index)-'0';
            if(isAtEvenPosition){
                digit*=2;
                if(digit>9){
                    sum+=digit%10+1;
                }
            }else{
                sum+=digit;
            }
            isAtEvenPosition=!isAtEvenPosition;
        }
        if(sum%10==0)
            return true;
        return false;
    }

    private boolean isNameValid(String name){
        String regex="^[\\p{L} .'-]+$";
        /*
         *   \\p{L} is a unicode property that matches any kind of letter from any language.
         *   This is used as names can have letters other than the English lexicon.
         *   Examples- Peter Müller, François Hollande, Patrick O'Brian, Silvana Koch-Mehrin
         *
         * */

        Pattern pattern=Pattern.compile(regex);
        if(name.isEmpty()){
            return false;
        }
        Matcher matcher=pattern.matcher(name);
        return matcher.matches();
    }

    private boolean isCVV_Valid(String CVV){
        String regex="^[0-9]{3,4}$";
        Pattern pattern=Pattern.compile(regex);
        if(CVV==null){
            return false;
        }
        Matcher matcher=pattern.matcher(CVV);

        return matcher.matches();
    }

    private boolean hasExpiryDatePassed(int month, int year){
        LocalDate localDate=LocalDate.now();
        int curr_year=localDate.getYear()%100;
        int curr_month=localDate.getMonthValue();
        if((year < curr_year) || ((year == curr_year) && (month < curr_month))) {
            return true;
        }
        return false;
    }

}
