class Solution {
    public int myAtoi(String s) {
        
        long result = 0;
        int index = 0, sign = 1;
        String trim = s.trim();

        while (index < trim.length() && (Character.isDigit(trim.charAt(index)) || (index == 0 && (trim.charAt(index) == '-' || trim.charAt(index) == '+')))) {

            char c = trim.charAt(index);

            if (c == '-') sign = -1;
            else if(Character.isDigit(trim.charAt(index))) {

                int digit = (c - '0');

                if (result > Integer.MAX_VALUE / 10 ||
                        (result == Integer.MAX_VALUE / 10 && digit > (sign == 1 ? 7 : 8))) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }


                result = result * 10 + digit;
            }

            index++;

        }

        return (int) (result * sign);

    }
}
