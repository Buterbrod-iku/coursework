import java.util.*;
import java.io.*;

// создаём класс для клиентов
class Client
{
    String name;
    String pol;
    int year;
    String sity;
    String diagnosis;

    void Print() {
        System.out.printf("Name: " + name + "\nPol: " + pol + "\nYear: " + year + "\nSity: " + sity + "\nDiagnosis: " + diagnosis + "\n");
    }

};

public class App {
    public static void remake(ArrayList<Client> people){
        Scanner resetDiagnosis = new Scanner(System.in);
        String fio = resetDiagnosis.nextLine();
        String diagnosisNew = resetDiagnosis.nextLine();

        for (int i = 0; i < people.size(); i++)
        {
            if (fio.equals(people.get(i).name)) {
                people.get(i).diagnosis = diagnosisNew;
            }
        }
    }
    public static void main(String[] args) throws Exception {
            File fin = new File("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/Java/kurs/src/test.txt");
            FileWriter fout = new FileWriter("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/Java/kurs/src/res.txt");

            // check под все строки из исходного файла
            ArrayList<String> check = new ArrayList<String>();
            // people массив под клиентов
            ArrayList<Client> people = new ArrayList<Client>();

            // извлекаем из файла посточно
            Scanner Reader = new Scanner(fin);
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                check.add(data);
            }

            // заполняем массив клиентов
            for (int i = 0; i < check.size(); i += 6)
            {
                Client first = new Client();
                first.name = check.get(i);
                first.pol = check.get(i+1);
                first.year = Integer.parseInt(check.get(i+2));
                first.sity = check.get(i+3);
                first.diagnosis = check.get(i+4);
                people.add(first);
            }

            // выводим, что заполнили
            for (int i = 0; i < people.size(); i++)
            {
                people.get(i).Print();
                System.out.println("---------");
            }

            // Проверка на инногородних и выводим их
            for (int i = 0; i < people.size(); i++)
            {
                if (!people.get(i).sity.equals("Barnaul")) {
                    System.out.printf("FIO: " + people.get(i).name + "\tSity: " + people.get(i).sity + "\n");
                }
            }

            // запрашиваем возраст
            System.out.print(">= age - ");
            Scanner ageNum = new Scanner(System.in);
            String age = ageNum.nextLine();
            int ageNew = Integer.valueOf(age);

            // записываем в другой файл >= age
            for (int i = 0; i < people.size(); i++)
            {
                if (people.get(i).year >= ageNew) {
                    fout.write(people.get(i).name + "\n" + people.get(i).year + "\n" + people.get(i).diagnosis + "\n");
                }
            }
            fout.flush();
            fout.close();

            File printFout = new File("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/Java/kurs/src/res.txt");
            FileWriter inputFin = new FileWriter("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/Java/kurs/src/test.txt");

            Scanner Reader1 = new Scanner(printFout);

            // добавляем >= age в исходный файл
            while (Reader1.hasNextLine()) {
                String data = Reader1.nextLine();
                System.out.printf(data + "\n");
                inputFin.write(data + "\n");
            }
            inputFin.flush();

            FileWriter inputFinalFin = new FileWriter("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/Java/kurs/src/test.txt");

            // отчищаем файл
            PrintWriter clearFinalFin = new PrintWriter(inputFinalFin);
            clearFinalFin.print("");
            clearFinalFin.flush();

            // удаляем записи инногородних
            for (int i = 0; i < people.size(); i++)
            {
                if (people.get(i).sity.equals("Barnaul")) {
                    inputFinalFin.append(people.get(i).name + "\n" + people.get(i).pol + "\n" + people.get(i).year + "\n" + people.get(i).sity + "\n" + people.get(i).diagnosis + "\n");
                }
            }
            inputFinalFin.flush();
            inputFinalFin.close();

            // надо ли перезаписывать диагноз
            System.out.printf("Edit diagnosis? y/n" + "\n");
            Scanner testNum = new Scanner(System.in);
            char test = testNum.nextLine().charAt(0);
            while (test == 'y') {
                // перезаписываем диагноз
                remake(people);
                System.out.printf("Edit diagnosis? y/n" + "\n");
                test = testNum.nextLine().charAt(0);
            }
            
            // для удобства выводим результат
            for (int i = 0; i < people.size(); i++)
            {
                people.get(i).Print();
                System.out.println("---------");
            }

            // записывем результат в последний файл
            FileWriter last = new FileWriter("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/Java/kurs/src/last.txt");
            for (int i = 0; i < people.size(); i++)
            {
                last.append(people.get(i).name + "\n" + people.get(i).pol + "\n" + people.get(i).year + "\n" + people.get(i).sity + "\n" + people.get(i).diagnosis + "\n\n");
            }
            last.flush();
            last.close();
    }
}
