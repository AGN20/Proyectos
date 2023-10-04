import doctest
from os import PathLike, closerange
from typing import Container

#Pedim el ficher d'entrada
fEntrada = "/home/adrian/Documentos/Python/U05EJ02/Ej07/entrada.txt"
#fEntrada = input("Indique el ficher d'entrada: ")

#Pedim el ficher d'eixida
fEixida = "salida.txt"
#fEixida = input("Indique el ficher d'eixida: ")

#Funciones del Programa

#Numeros que se lean igual del derecho que del rebes
def esPalindromo(x):
    #Tenemos que quitar primero el \n o si no nos dara problemas al comparar
    listNumero = list(x.split("\n"))
    lon = len(listNumero)
    #Si la longitud es 1 no lo hagas, porque no tiene el \n
    if lon != 1:
        listNumero.pop(lon-1)

    #lo pasamos otra vez a str
    numero = listNumero[0]

    #numero leida al rebes
    num = numero[::-1]

    #si el numero es Palindromo devuelbe
    if numero == num:
        return numero
    
    #Devolbemos el numero
    




def esPrimo(x):
    num = int(x)

    if num == 2 or num == 3 or num == 5:
        return str(num)
    else:
        if num % 2 == 0 or num % 3 == 0 or num % 5 == 0:
            return None
        else:
            return str(num)
        


palindromo = []
primo = []
losDos = []



#leemos el archivo
with open(fEntrada) as archivo:

    #Por cada numero ejecutamos las funciones
    for numero in archivo:
        if esPalindromo(numero) != None:
            palindromo.append(esPalindromo(numero))
        if esPrimo(numero) != None:
            primo.append(esPrimo(numero))
        
    for i in palindromo:
        for x in primo:
            if i == x:
                losDos.append(i)

lonPalindromos = len(palindromo)
lonPrimos = len(primo)

result = "Hi han "+ str(lonPalindromos) +" números palíndromos \nHi han "+ str(lonPrimos) +" números cosins"
for i in range(len(losDos)):
    result = result + "\n" + losDos[i]



print(result)



doctest.testfile(fEixida)