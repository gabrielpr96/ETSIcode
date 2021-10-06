----------------------------------------------------------------------------------
-- Enginer:        Borja L�pez Pineda
-- Create Date:    17:10:03 16/12/2018
-- Module Name:    Practica5 - Arquitectura
-- Project Name:   Practica 5.vme
-- Description:    Practica 5 de FC, ETSI UHU.
--
-- Created with VHDL ME. Parser by B0vE, powered by Temis (De mesa)
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

Entity Practica5 is
	Port( C0 : In STD_LOGIC;
		   C1 : In STD_LOGIC;
		   C2 : In STD_LOGIC;
		   C3 : In STD_LOGIC;
		   A : Out STD_LOGIC;
		   I : Out STD_LOGIC;
		   J : Out STD_LOGIC);
End Practica5;

Architecture Arquitectura of Practica5 is
	Signal entrada :  STD_LOGIC_VECTOR(3 downto 0);
	Signal entrada_int :  INTEGER range 0 to 15;
Begin
	A <= ((Not C2) And (Not C3)) Or (C2 And C3) Or (C0 And C1 And (Not C2)) Or ((Not C0) And (Not C1) And C2);
	entrada <= C3&C2&C1&C0;

	I <= '1' When entrada = "0001" else
		  '1' When entrada = "0010" else
		  '1' When entrada = "0100" else
		  '1' When entrada = "0111" else
		  '1' When entrada = "1000" else
		  '1' When entrada = "1011" else
		  '1' When entrada = "1101" else
		  '1' When entrada = "1110" else
		  '0';

	entrada_int <= CONV_INTEGER(entrada);

	With entrada_int select
	J <= '1' When 0|1|3|7|8|12|14|15,
		  '0' When others;
End Arquitectura;

--#Implementation, copy this into constrictions file and remove '--' coments.
--NET "C0" LOC = "L13"  | IOSTANDARD = LVTTL | PULLUP ;
--NET "C1" LOC = "L14"  | IOSTANDARD = LVTTL | PULLUP ;
--NET "C2" LOC = "H18"  | IOSTANDARD = LVTTL | PULLUP ;
--NET "C3" LOC = "N17"  | IOSTANDARD = LVTTL | PULLUP ;
--NET "A" LOC = "F9"  | IOSTANDARD = LVTTL | SLEW = SLOW | DRIVE = 8 ;
--NET "I" LOC = "E9"  | IOSTANDARD = LVTTL | SLEW = SLOW | DRIVE = 8 ;
--NET "J" LOC = "D11"  | IOSTANDARD = LVTTL | SLEW = SLOW | DRIVE = 8 ;
--#Implementation finished
