import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        System.out.println(calc((new Scanner(System.in)).nextLine()));
    }

    public static String calc(String input){
        String result = "", operation = ".";
        String[] operations = {"+", "-", "*", "/"};
        int a = 0, b = 0;

        // Проверка на наличие +, -, *, /;
        boolean flag = false;
        for (String symbol : input.split("")){
            for (String chr : operations){
                if (symbol.equals(chr)){
                    flag = true;
                    operation = chr;
                }
            }
        }

        if (!flag){
            return "Error";
        }
        //-----------------------------------
        if (operation.equals("+") || operation.equals("*")){
            operation = "\\" + operation;
        }

        String[] arr = input.split(operation);
        boolean rim = false;
        try {
            if (!(arr.length > 2)){
                a = Integer.parseInt(arr[0]);
                b = Integer.parseInt(arr[1]);
            } else{
                return "Error";
            }
        } catch (NumberFormatException e) {
            a = translator(arr[0]);
            b = translator(arr[1]);
            if (a == -1 || b == -1){
                return "Error";
            }
            rim = true;
        }

        if ((a > 10 || a < 1) || (b > 10 || b < 1)){
            return "Error";
        }

        switch (operation){
            case "\\+":
                result = Integer.toString(a+b);
                break;
            case "-":
                if ((rim) && (a - b < 0)){
                    return "Error";
                }
                result = Integer.toString(a-b);
                break;
            case "/":
                result = Integer.toString((int) a/b);
                break;
            case "\\*":
                result = Integer.toString(a*b);
                break;
        }

        if (!rim){
        return result;
        } else{
            return reversedTranslator(result);
        }
    }
    
    public static int translator(String num){
        if (num.length() > 3){
            return -1;
        }
        String[] a;

        //-----------------------------------------------
        boolean rimFlag = false;
        String[] rimCheck = num.split("");
        for (String sym : rimCheck){
            if (!(sym.equals("V") || sym.equals("X") || sym.equals("I"))){
                return -1;
            }
        }
        //-----------------------------------------------

        num = num.replace("V", "5").replace("I", "1").replace("X", "9");
        a = num.split("");


        int mx;
        boolean right = false, left = false;

        if (Integer.parseInt(a[0]) < Integer.parseInt(a[a.length - 1])){
            mx = Integer.parseInt(a[a.length - 1]);
            right = true;
        } else {
            mx = Integer.parseInt(a[0]);
            left = true;
        }

        if (mx == 9){
            mx = 10;
        }

        if (left){
            for (int i = 1; a.length > i; i++){
                mx += Integer.parseInt(a[i]);
            }
        } else {
            for (int i = 0; a.length - 1 > i; i++){
                mx -= Integer.parseInt(a[i]);
            }
        }

        return mx;
    }

    public static String reversedTranslator(String str){
        String result = "";
        int x = Integer.parseInt(str);

        if (x == 100){
            return "C";
        }

        while (x > 0){
            if (x - 50 >= 0){
                result = result + "L";
                x -= 50;
            } else if (x - 10 >= 0){
                result = result + "X";
                x -= 10;
            } else if (x - 5 >= 0){
                result = result + "V";
                x -= 5;
            } else{
                result = result + "I";
                x--;
            }
        }

        return result;
    }
}