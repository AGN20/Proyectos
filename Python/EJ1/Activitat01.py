import copy

#Creem la primera llista
listaInicial = [1,2,3,4,5,6,7]

#Per a copiar una llista, es pot fer de dos maneres
lcCop = copy.copy(listaInicial)
lcDeepCop = copy.deepcopy(listaInicial)

"""
Diferencies entre 'Shadow copy' i 'Deep copy'
'Shadow copy' copia la referencia als objectes anidats
'Deep copy' copia els objectes anidats en la llista
"""
#Afegir un nou element a la llista
listaInicial.append(10)

#Llevar un element de la llista
listaInicial.pop(2)

#Crear una llista am els ultims 4 digits de la llista original
listaNew = listaInicial[-4:]

#Creem la cadena de paraules
cadena = "Hola el meu nom n'es Adrian"

#Separem en una llista nova les paraules
listaNom = cadena.split(" ")

#Els comentaris d'una linea se fan posant abans #
#Bon dia, soc un comantari d'una línea

#Els comentaris mutilínea es fan posant """ abans y despres del comentari
""" 
Bon dia
soc un comentari multilínea
"""


