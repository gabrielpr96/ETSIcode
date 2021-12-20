clear all
clc
%Crea el robot utilizando la conexión wiFi
%Connect the Host Computer to an EV3 Brick Over a Wireless Network
%IPaddress='192.168.0.101';
IPaddress='192.168.0.132';
%Robot_ID='00165814f48';
Robot_ID='00165380547c';
mi_Robot  = legoev3('wifi',IPaddress,Robot_ID)
%---------------------------------------