#ifndef _EXCEPCION_H_
#define _EXCEPCION_H_

#include <string>
#include <stdexcept>

using namespace std;

class Excepcion : public runtime_error
{
	public:
		Excepcion(const string& err) : runtime_error(err) { }
  		string Mensaje() const { return what(); }
};

class NoHaySiguienteMayor : public Excepcion
{
	public:
		NoHaySiguienteMayor(const string& err) : Excepcion(err) { }
  		string Mensaje() const { return what(); }
};

#endif

