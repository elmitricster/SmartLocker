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
	MYSQL_RES* result;  //쿼리성공시 결과를 담는 구조체 포인터
	MYSQL_ROW row;      //쿼리성공시 결과로 나온 행의 정보를 담는 구조체
	int Stat;           //쿼리요청 후 결과(성공,실패)
	int s_day;          //s_day를 int로 바꾼 최종값 저장용
	int l_day;          //l_day를 int로 바꾼 최종값 저장용
	

	/* 여기서부터 현재시간 구하려고 짠 소스

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




	/* 여기서부터 DB에서 s_day, l_day를 int로 얻어오는 소스
	
	mysql_init(&Conn);
	ConnPtr = mysql_real_connect(&Conn, "DB서버주소", "root", "비밀번호", "접속할DB", 3306, (char*)NULL, 0);

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

	while ((row = mysql_fetch_row(result)) != NULL)   //row[0], row[1], ... 형식으로 저장됨
	{
		
		s_day = atoi(row[0]);
		l_day = atoi(row[1]);

	}
	   	  
	mysql_free_result(result);

	mysql_close(ConnPtr);
	
	*/
	
	return 0;
}