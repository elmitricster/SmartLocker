#include <stdio.h>
#include <time.h>
#include <string.h>
#include <stdlib.h>
#include <mysql.h>
//#include <my_global.h>
//#include <wiringPi.h>


#pragma comment(lib, "libmySQL.lib")


int main()
{
	MYSQL Conn;
	MYSQL * ConnPtr = NULL;
	MYSQL_RES* result;  //���������� ����� ��� ����ü ������
	MYSQL_ROW row;      //���������� ����� ���� ���� ������ ��� ����ü
	int Stat;           //������û �� ���(����,����)
	int s_day;          //s_day�� int�� �ٲ� ������ �����
	int l_day;          //l_day�� int�� �ٲ� ������ �����
	

	/* ���⼭���� ����ð� ���Ϸ��� § �ҽ�

	int year, month, day, hour;
	char y[2];
	char m[4];
	char d[6];
	char h[8];
	int now;

	time_t t ;
	struct tm *td;
	t = time(NULL);
	time(&t);
	td = localtime(&t);


	year = td->tm_year - 100;
	month = td->tm_mon + 1;
	day = td->tm_mday;
	hour = td->tm_hour;

	_itoa_s(year, y, 10);
	_itoa_s(month, m, 10);
	_itoa_s(day, d, 10);
	_itoa_s(hour, h, 10);

	strcat_s(y, m);
	strcat_s(m, d);
	strcat_s(d, h);

	now = atoi(h);

	*/




	/* ���⼭���� DB���� s_day, l_day�� int�� ������ �ҽ�
	
	mysql_init(&Conn);
	ConnPtr = mysql_real_connect(&Conn, "DB�����ּ�", "root", "��й�ȣ", "������DB", 3306, (char*)NULL, 0);

	if (ConnPtr == NULL)
	{
		fprintf(stderr, "Mysql connection error : %s\n", mysql_error(&Conn));
		return 1;
	}
 	  
	Stat = mysql_query(ConnPtr, "SELECT s_day, l_day FROM reservation");
	if (Stat != 0)
	{
		fprintf(stderr, "Mysql connection error : %s\n", mysql_error(&Conn));
		return 1;
	}

	result = mysql_store_result(ConnPtr);

	while ((row = mysql_fetch_row(result)) != NULL)   //row[0], row[1], ... �������� �����
	{
		
		s_day = atoi(row[0]);
		l_day = atoi(row[1]);

	}
	   	  
	mysql_free_result(result);

	mysql_close(ConnPtr);
	
	*/
	
	return 0;
}