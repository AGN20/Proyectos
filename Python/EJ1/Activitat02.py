import copy

#Definimos la funcion a la que pasaremos 2 numeros y los suma
def suma(x, y):
    return x + y

print(suma(10, 5))
print("---------------------------------")

#Llista a duplicar
llista = [1,2,3,4,5,6]

#Funcion que duplica la llista
def duplicar( x ):
    
    for i in range(len(x)):
        x[i] = x[i] * 2

duplicar(llista)
print(llista)
print("---------------------------------")

#Funcion que duplica la llista
def duplicar2( x ):
    
    y = copy.deepcopy(x)

    for i in range(len(x)):
        y[i] = x[i] * 2

    return y

print(duplicar2(llista))
print(llista)
print("---------------------------------")