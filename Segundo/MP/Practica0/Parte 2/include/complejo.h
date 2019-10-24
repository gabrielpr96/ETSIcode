#ifndef COMPLEJO_H
#define COMPLEJO_H


class complejo
{
    public:
        complejo(int r, int i = 0);
        virtual ~complejo();

        void setr(int val) { real = val; }
        void seti(int val) { imaginario = val; }
        int getr() const { return real; }
        int geti() const { return imaginario; }

        void set(int r, int i);
        void set();
        void ver() const;

        complejo operator+(const complejo &b) const;
        complejo operator+(const int &b) const;
        complejo operator-() const;

        complejo operator++();
        complejo operator++(int flag);

        bool operator==(const complejo &b) const;
        bool operator==(const int &b) const;

        operator int() const;

    protected:

    private:
        int real;
        int imaginario;
};

complejo operator+(int a, const complejo &b);
std::ostream& operator<<(std::ostream& s, const complejo &o);

#endif // COMPLEJO_H
