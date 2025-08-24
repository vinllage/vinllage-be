package xyz.vinllage.global.validators;

public class Password implements  PasswordValidator{

    @Override
    public int passwordCheck(String password) {
        int score = 0;
        // 길이 점수
        int length = password.length();
        if(length >= 17) score +=3;
        else if(length >= 13) score +=2;
        else if(length  >= 8) score +=1;
        // 알파벳 점수
        if(password.matches(".*[a-z].*") && password.matches(".*[A-Z].*")) score +=2;        else if(password.matches(".*[a-zA-Z].*")) score += 1;
        // 숫자 점수
        int number = password.replaceAll("[^0-9]", "").length();
        if(number >=2) score +=2;
        else if(number == 1) score +=1;
        // 특수문자 점수
        int special = password.replaceAll("[0-9a-zA-Zㄱ-ㅎ가-힣]", "").length();
        if(special >=2) score += 2;
        else if(special ==1) score +=1;

        return score;
    }

    @Override
    public boolean getPassword(String password) {
        return password.length() >=8 &&
                password.matches(".*[a-zA-Z].*") &&
                password.matches("\\d.*") &&
                password.matches(".*[^0-9a-zA-Zㄱ-ㅎ가-힣].*");
    }
}
