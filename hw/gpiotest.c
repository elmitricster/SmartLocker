#include <wiringPi.h>
#include <stdio.h>

#define A1 23 
#define B1 24
#define C1 25
#define D1 0 
#define E1 2
#define F1 3
#define G1 21
#define H1 22
#define I1 1
#define SEN0 27
#define SEN1 28
#define SEN2 29

int main(void)
{
	if(wiringPiSetup()==-1)
		return 1;

	pinMode(A1,OUTPUT);
	pinMode(B1,OUTPUT);
	pinMode(C1,OUTPUT);
	pinMode(D1,OUTPUT);
	pinMode(E1,OUTPUT);
	pinMode(F1,OUTPUT);
	pinMode(G1,OUTPUT);
	pinMode(H1,OUTPUT);
	pinMode(I1,OUTPUT);
	pinMode(SEN0,INPUT);
	pinMode(SEN1,INPUT);
	pinMode(SEN2,INPUT);

	while(1)
	{
                if(digitalRead(SEN0) == HIGH){ //digitalRead(SEN0)이 센싱값이에요.
                digitalWrite(A1,1);
                digitalWrite(D1,0);
                }
                else{
                digitalWrite(A1,0);
                digitalWrite(D1,1);
                }
                if(digitalRead(SEN1) == HIGH){
                digitalWrite(B1,1);
                digitalWrite(E1,0);
                }
                else{
                digitalWrite(B1,0);
                digitalWrite(E1,1);
                }
                if(digitalRead(SEN2) == HIGH){
                digitalWrite(C1,1);
                digitalWrite(F1,0);
                }
                else{
                digitalWrite(C1,0);
                digitalWrite(F1,1);
                }

		delay(1000);
	}
	return 0;
}
