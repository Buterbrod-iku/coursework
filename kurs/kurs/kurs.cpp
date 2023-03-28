#include <iostream>
#include <string>
#include <fstream>
#include <vector>

using namespace std;

// создаём класс для клиентов
class Client
{
public:
	string name;
	string pol;
	int year;
	string sity;
	string diagnosis;

	void Print() {
		cout << "Name: " << name << "\nPol: " << pol << "\nYear: " << year << "\nSity: " << sity << "\nDiagnosis: " << diagnosis << endl;
	}

};

// функция изменения диагноза
vector<Client> remake(vector<Client> people) {
	string fio, diagnosisNew;
	cin.get();
	getline(cin, fio);
	cin >> diagnosisNew;

	for (int i = 0; i < people.size(); i++)
	{
		if (people[i].name == fio) {
			people[i].diagnosis = diagnosisNew;
		}
	}
	return people;
}

int main()
{
	ifstream fin("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/kurs/test.txt");
	ofstream fout("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/kurs/res.txt");

	// check под все строки из исходного файла
	vector <string> check;
	// people массив под клиентов
	vector <Client> people;

	// извлекаем из файла посточно
	for (string N; getline(fin, N); ) {
		check.push_back(N);
	}

	// заполняем массив клиентов
	for (int i = 0; i < check.size(); i += 6)
	{
		Client first;
		first.name = check[i];
		first.pol = check[i+1];
		first.year = atoi(check[i+2].c_str());
		first.sity = check[i+3];
		first.diagnosis = check[i+4];
		people.push_back(first);
	}

	// выводим, что заполнили
	for (int i = 0; i < people.size(); i++)
	{
		people[i].Print();
		cout << "---------" << endl;
	}

	// Проверка на инногородних и выводим их
	for (int i = 0; i < people.size(); i++)
	{
		if (people[i].sity != "Barnaul") {
			cout << "FIO: " << people[i].name << "\tSity: " << people[i].sity << endl;
		}
	}

	// запрашиваем возраст
	int age;
	cout << ">= age - ";
	cin >> age;

	// записываем в другой файл >= age
	for (int i = 0; i < people.size(); i++)
	{
		if (people[i].year >= age) {
			fout << people[i].name << endl << people[i].year << endl << people[i].diagnosis << endl;
		}
	}
	fout.close();

	// добавляем >= age в исходный файл
	ifstream printFout("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/kurs/res.txt");
	ofstream inputFin("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/kurs/test.txt", ios_base::app);
	string M;
	inputFin << endl;
	while (getline(printFout, M))
	{
		cout << M << endl;
		inputFin << M << endl;
	}

	// удаляем записи инногородних
	ofstream inputFinalFin("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/kurs/test.txt");
	for (int i = 0; i < people.size(); i++)
	{
		if (people[i].sity == "Barnaul") {
			inputFinalFin << people[i].name << endl << people[i].pol << endl << people[i].year << endl << people[i].sity << endl << people[i].diagnosis << endl;
		}
	}

	// Отдельный файл для результатов
	ofstream last("C:/Users/Buterbrod/Desktop/C++/semestr_2/Новая папка/kurs/last.txt");
	
	
	// надо ли перезаписывать диагноз
	cout << "Edit diagnosis? y/n" << endl;
	char test;
	cin >> test;
	while (test == 'y') {
		// перезаписываем диагноз
		people = remake(people);
		cout << "Edit diagnosis? y/n" << endl;
		cin >> test;
	}

	// для удобства выводим результат
	for (int i = 0; i < people.size(); i++)
	{
		people[i].Print();
		cout << "---------" << endl;
	}

	// записывем результат в последний файл
	for (int i = 0; i < people.size(); i++)
	{
		last << people[i].name << endl << people[i].pol << endl << people[i].year << endl << people[i].sity << endl << people[i].diagnosis << "\n\n";
	}
	return 0;
}