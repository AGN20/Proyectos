import functools

"""
Lambda nos permite asignar diferentes variables
dependiendo de cuando se introduzca el valor, en este caso
asignamos 5 cuando llamamos a la funcion y este guardara a
despues al imprimir por pantalla le damos el numero 10,
este sera el numero que se guarde en n
"""

def funtionLamb(n):
    return lambda a,: a * n 

result = funtionLamb(5)

print(result(10))

texto = input("Indica una serie de numeros separados por espacios:")

try:

    """
    Filtramos la cadena de texto que nos da con 'split()' 
    separandolas por espacios, despues
    castemos los caracteres a numeros con 'map()' y por ultimo
    creamos una lista con 'list()'
    """

    lista = list(map(int, texto.split(" ")))

    """
    Con 'filter()' y con la alluda de una funcion lambda quitaremos
    de la lista todos los numeros menores de 10, posteriormente lo 
    pasaremos a lista con 'list()'
    """

    lista = list(filter(lambda x: x >= 10, lista ))

    """
    Es importante antes de utilizar el 'reduce()' importar 'functools' para que funcione correctamente,
    en este caso le pasaremos al reduce una funcion lambda que cogera dos numeros y los multiplicara, 
    cogiendo como porimer parametro el resultado de la anterior multiplicación, el segundo parametro
    sera nuestra lista, para que saque los numeros de esta.
    """

    result = functools.reduce((lambda x, y: x * y), lista )

    print(result)

except ValueError:
    print("Esto no son numeros")