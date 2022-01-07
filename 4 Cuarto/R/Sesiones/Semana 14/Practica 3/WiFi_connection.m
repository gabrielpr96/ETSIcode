clear all
clc

IPaddress='192.168.0.132';
Robot_ID='00165380547c';

robot  = legoev3('wifi',IPaddress,Robot_ID)

Detecta_colision = touchSensor(robot, 1);
Pulsador = touchSensor(robot, 2);

motor_izquierdo = motor(robot, 'B');
motor_derecho = motor(robot, 'C');
