using System;
using System.Runtime.Remoting.Lifetime;

namespace Cliente {
    class Esponsor : MarshalByRefObject, ISponsor {
        private readonly double tiempoRenovacion = 10;
        private readonly bool renovar = true;
        public TimeSpan Renewal(ILease lease) {
            if(renovar)
                return TimeSpan.FromSeconds(tiempoRenovacion);
            else
                return TimeSpan.Zero;
        }
    }
}
