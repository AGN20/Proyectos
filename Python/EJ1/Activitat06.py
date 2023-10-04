from operator import itemgetter, attrgetter

mida = [1.30, 0.96, 0.60, 1.30, 1.02]
pes = [78, 57, 60, 86, 78]

#Creamos una lista que nos guardara una lista de dos elementos
llistaFinal = []

#creamos la funcion que creara una lista de listas con dos elementos
def almacenar(x, y):
    
    i = 0

    #Por cada elemento de "mida" se crara una lista que se añadira en la listaFinal
    for i in range(len(mida)):
        llista = [x[i], y[i]]

        llistaFinal.append(llista)

        i+1

#Iniciamos la función
almacenar(mida, pes)

#Creamos una lista ordenada por el "mida" de manera descendente
s = sorted(llistaFinal, key=itemgetter(0), reverse=True)

#lo ordenamos por la "pes" (esto probocara que si hay medidas iguales cogera la de menor peso primero)
sorted(s, key=itemgetter(1))

#imprimimos la lista
print(s)

"""
"Key Functions" es el campo dentro de un "sort" o "sorted" que indica
el campo por el que se va a ordenar la lista, suelen tener una clave
"itemgetter()", "attrgetter()" o "lambda", los primeros dos necesitan
que se les designe un campo de la lista, para poder ordenar por ese campo 
"""
