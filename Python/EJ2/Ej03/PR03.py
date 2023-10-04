""""
Programa que le una serie de caracteres pasados por
pantalla y te muestra cuantas veces se an repetido estos
patrones
"""

text = input("Pasame una cadena de texto: ")

def numeroPatrones(text):

    #Lista de patrones
    patrons = ["00","101","ABC","HO"]

    #Valor que guarda cuantos patrones se repite
    numPatrones = 0

    #Lo pasamos a mayusculas para que no distinga entre machus o minus
    textMax = text.upper()

    #Por cada uno de los patrones
    for i in range(len(patrons)):

        #Longitud del patron
        patronLen = len(patrons[i])
        
        #Por cada letra del texto comprueba
        for x in range(len(text)):
            
            #cadena de texto que va desde x asta la longitud del patron
            textComp = textMax[x: x+patronLen]

            #Si el patron i esta dentro, suma uno a numPatrones
            if patrons[i] == textComp:
                numPatrones = numPatrones + 1
    
    print(numPatrones)


numeroPatrones(text)