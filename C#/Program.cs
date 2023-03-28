using System;
using System.IO;

// создаём класс для клиентов
class Client
{
	public string name;
	public string pol;
	public int year;
	public string sity;
	public string diagnosis;

	public void Print() {
        Console.WriteLine("Name: " + name + "\nPol: " + pol + "\nYear: " + year + "\nSity: " + sity + "\nDiagnosis: " + diagnosis);
	}

};
class HelloWorld {
    // функция изменения диагноза
    public static void remake(List<Client> people){
        string fio, diagnosisNew;
        fio = Console.ReadLine();
		diagnosisNew = Console.ReadLine();

		for (int i = 0; i < people.Count; i++)
		{
			if (people[i].name == fio) {
				people[i].diagnosis = diagnosisNew;
			}
		}
    }
  static void Main() {
    StreamReader fin = new StreamReader("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/C#/test.txt");
    StreamWriter fout = new StreamWriter("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/C#/res.txt");

    // check под все строки из исходного файла
    List<string> check = new List<string>();
    // people массив под клиентов
    List<Client> people = new List<Client>();

    // извлекаем из файла посточно
    string N;
	while ((N = fin.ReadLine()) != null){
        check.Add(N);
    }
    fin.Close();

    // заполняем массив клиентов
	for (int i = 0; i < check.Count; i += 6)
	{
		Client first = new Client();
		first.name = check[i];
		first.pol = check[i+1];
		first.year = Convert.ToInt32(check[i+2]);
		first.sity = check[i+3];
		first.diagnosis = check[i+4];
		people.Add(first);
	}

    // выводим, что заполнили
	for (int i = 0; i < people.Count; i++)
	{
		people[i].Print();
		Console.WriteLine("---------");
	}

    // Проверка на инногородних и выводим их
	for (int i = 0; i < people.Count; i++)
	{
		if (people[i].sity != "Barnaul") {
            Console.WriteLine("FIO: " + people[i].name + "\tSity: " + people[i].sity);
		}
	}

    // запрашиваем возраст
    Console.Write(">= age - ");
    int age = Convert.ToInt32(Console.ReadLine());

    // записываем в другой файл >= age
	for (int i = 0; i < people.Count; i++)
	{
		if (people[i].year >= age) {
            fout.WriteLine(people[i].name + "\n" + people[i].year + "\n" + people[i].diagnosis);
		}
	}
    fout.Close();

    StreamReader printFout = new StreamReader("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/C#/res.txt");
    StreamWriter inputFin = new StreamWriter("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/C#/test.txt");
	string M;

    // добавляем >= age в исходный файл
	Console.Write("\n");
	while ((M = printFout.ReadLine()) != null){
		Console.WriteLine(M);
        inputFin.WriteLine(M);
	}
    inputFin.Close();

    // удаляем записи инногородних
    StreamWriter inputFinalFin = new StreamWriter("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/C#/test.txt");
	for (int i = 0; i < people.Count; i++)
	{
		if (people[i].sity == "Barnaul") {
            inputFinalFin.WriteLine(people[i].name + "\n" + people[i].pol + "\n" + people[i].year + "\n" + people[i].sity + "\n" + people[i].diagnosis);
		}
	}
    inputFinalFin.Close();

    // Отдельный файл для результатов
    StreamWriter last = new StreamWriter("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/C#/last.txt");
	
    // надо ли перезаписывать диагноз
    Console.WriteLine("Edit diagnosis? y/n");
    char test = char.Parse(Console.ReadLine());
	while (test == 'y') {
        // перезаписываем диагноз
		remake(people);
        Console.WriteLine("Edit diagnosis? y/n");
        test = char.Parse(Console.ReadLine());
	}

    // для удобства выводим результат
	for (int i = 0; i < people.Count; i++)
	{
		people[i].Print();
        Console.WriteLine("---------");
	}

    // записывем результат в последний файл
	for (int i = 0; i < people.Count; i++)
	{
        last.WriteLine(people[i].name + "\n" + people[i].pol + "\n" + people[i].year + "\n" + people[i].sity + "\n" + people[i].diagnosis + "\n");
	}
    last.Close();
  }
}