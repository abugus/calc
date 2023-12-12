import java.util.Scanner;
public class Main {
    static String[] regex = new String[]{"+","-","*","/"};
    static String[]convertedRegex = new String[]{"\\+","-","\\*","/"};
    static int num1;
    static int num2;
    static int result=0;
    static int charIndex = -1;
    static boolean isRoman;

   public static void main(String[] args)throws Exception{
       Scanner scanner = new Scanner(System.in);
       System.out.println("Введите операцию: ");
       String inputExpr = scanner.nextLine();
       System.out.println("Результат операции:"+"\n"+calc(inputExpr));
    }
    public static String calc(String input) throws Exception {
        Converter converter = new Converter();
        for (int i = 0; i < regex.length; i++){
            if(input.contains(regex[i])){
                charIndex = i;
                break;
            }
        }
        if(charIndex==-1){
            throw new Exception("Некорректное выражение"+"\n"+"Не найдена или неправильно введена математическая операция над числами");
        }
        String[]splitExpr = input.split(convertedRegex[charIndex]);
        if(isNumeric(splitExpr[0])&&isNumeric(splitExpr[1])){
            isRoman = false;
            num1 = Integer.parseInt(splitExpr[0]);
            num2 = Integer.parseInt(splitExpr[1]);
        }
        else if(!(isNumeric(splitExpr[0]))&&!(isNumeric(splitExpr[1]))){
            isRoman = true;
            num1 = converter.romanToInt(splitExpr[0]);
            num2 = converter.romanToInt(splitExpr[1]);
        }
        else {
            throw new Exception("Некорректное выражение"+"\n"+"Оба числа должны быть одной системы счисления");
        }
        if((num1>10||num1<0)||num2>10||num2<0){
            throw new Exception("Входные числа должны быть от 1 до 10 включительно(от I до X)");
        }
        switch(regex[charIndex]){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        if(isRoman==true){
            if(result<1){
                throw new Exception("Результатом операции над римскими числами может быть только положительное число");
            }
            return converter.intToRoman(result);
        }
        else{
            return Integer.toString(result);
        }
    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}