
#Introducimos el texto
from typing import cast


sudoku = open("/home/2dam/Python/U05EJ02/Ej02/sudoku.in")
sudoku = sudoku.read()

#Funcion que comprueba todas las posibilidades y te dice si el sudoku esta bien
def esSudokuCorrecto(miArrayBi):

    #Creamos una lista con el parametro que se nos a pasado
    listaSudoku = miArrayBi.split()

    #Controlador que cambia si una de las funciones es incorrecta
    controlador = False

    #Comprobamos las lineas
    controlador = lineas(listaSudoku)

    #Comprobamos las columnas
    controlador = columnas(listaSudoku)

    #Comprobamos en Vertical
    controlador = cuadrado(listaSudoku)

    #Si el controlador es true "ESTA MAL", si es false "ESTA BIEN"
    if(controlador):
        print("El sudoku esta mal")
    else:
        print("El sudoku esta bien")


#Buscamos en la linea si son correctas
def lineas(x):

    #Creamos una lista nueva para no trabajar con la de afuera
    sudokuLista = list(x)

    #Creamos un controlador, sera el resultado de nuestra función
    controlador = False
    
    #Por las nueve lineas me compruevas
    for i in range(9):
        
        #Numero en el que se guarda la suma de todo
        num = 0

        #Por cada uno de los 9 numeros de la linea
        for z in range(9):
            #Suma el numero al numero siguiente del sudoku
            num = num + int(sudokuLista[0])
            #tiralo para que no vuelba a sumarlo
            sudokuLista.pop(0)

        #Si el num es diferente de 45, el sudoku esta mal
        if num != 45:
            controlador = True
            return controlador

        


#Divide el texto en columnas y te dice si no son iguales        
def columnas(x):

    #Creamos una lista nueva para no trabajar con la de afuera
    sudokuLista = list(x)

    #Creamos un controlador, sera el resultado de nuestra función
    controlador = False

    control = 0

    #Por cada 3 de los 9 numeros, me coges 3 numeros y los comparas
    for i in range(9):
        
        #numFinal guardara la suma de todos los numeros
        numFinal = 0

        num = [sudokuLista[0+control], sudokuLista[9+control], sudokuLista[18+control],sudokuLista[27+control], sudokuLista[36+control], sudokuLista[45+control],sudokuLista[54+control],sudokuLista[63+control], sudokuLista[72+control]]
        
        control= control + 1

        for i in range (9):
            numFinal = numFinal + int(num[0])
            num.pop(0)

        #Si el num es diferente de 45, el sudoku esta mal
        if numFinal != 45:
            controlador = True
            return controlador


#Comprueva si el cuadrado de 3*3 es diferente      
def cuadrado(x):

    #Creamos una lista nueva para no trabajar con la de afuera
    sudokuLista = list(x)

    #Creamos un controlador, sera el resultado de nuestra función
    controlador = False

    control = 0

    #Por cada 3 de los 9 numeros, me coges 3 numeros y los comparas
    for i in range(9):

        #numFinal guardara la suma de todos los numeros
        numFinal = 0

        #separamos el cuadrado que vamos a hacer
        num = [sudokuLista[0+control], sudokuLista[1+control], sudokuLista[2+control], sudokuLista[9+control], sudokuLista[10+control], sudokuLista[11+control], sudokuLista[18+control], sudokuLista[19+control], sudokuLista[20+control]]
        
        control = control+3

        #comprobamos que en el cuadrado ningun numero se repite
        for i in range(9):
            numFinal = numFinal + int(num[0])
            num.pop(0)

        #si es diferente a 45 el sudoku esta mal
        if numFinal != 45:
            controlador = True
            return controlador


esSudokuCorrecto(sudoku)
