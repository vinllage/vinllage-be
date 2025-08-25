package xyz.vinllage.global.validators;

public interface PasswordValidator {
    /**
     * 알파벳 복잡성 체크
     *  - 1) 대소문자 각각 1개 이상 있는 경우
     *  - 2) 대소문자 구분없이 알파벳 1자 이상
     * @param  caseInsensitive : true 2), false - 1)
     * @return
     */
    default boolean checkAlpha(String password, boolean caseInsensitive) {
        if (caseInsensitive) { // 대소문자 구분없이 알파벳 1자 이상
            // .* : 0이상 아무 문자  [a-zA-Z]+ : 알파벳 대소문자 상관없이 1자 이상
            return password.matches(".*[a-zA-Z]+.*");
        }

        // 대문자 1개 이상, 소문자 1개 이상
        return password.matches(".*[a-z]+.*") && password.matches(".*[A-Z]+.*");
    }

    /**
     * 숫자 복잡성 체크
     *
     * [0-9]  -> \d
     *
     * @param password
     * @return
     */
    default boolean checkNumber(String password) {
        return password.matches(".*\\d+.*");
    }

    /**
     * 특수문자 복잡성 체크
     * [^문자..]   문자는 제외
     * [^\d] -> 숫자를 제외한 문자
     *
     * @param password
     * @return
     */
    default boolean checkSpecialChars(String password) {
        String pattern = ".*[^0-9a-zA-Zㄱ-ㅎ가-힣]+.*";  // 숫자, 알파벳, 한글을 제외한 모든 문자(특수문자)

        return password.matches(pattern);
    }
    default int passwordCheck(String password){
        int score = 0;
        // 길이 점수
        int length = password.length();
        if(length >= 17) score +=3;
        else if(length >= 13) score +=2;
        else if(length  >= 8) score +=1;
        // 알파벳 점수
        if(password.matches(".*[a-z].*") && password.matches(".*[A-Z].*")) score +=2;
        else if(password.matches(".*[a-zA-Z].*")) score += 1;
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

    default  String sPassword(String password) {
        int score = passwordCheck(password);
        if(score <=2) return "매우 약함";
        else if(score <=4) return "약함";
        else if(score <=6) return "보통";
        else if(score <=8) return "강함";
        else  return "매우 강함";

    }

}
