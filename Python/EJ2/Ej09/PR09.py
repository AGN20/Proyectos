from os import name
import sys
import csv
import barcode
import random
from barcode import EAN13
from barcode.writer import ImageWriter

#Comprobamos que solo nos pasan un parametro
if (len(sys.argv)>2):
    print("Solo se necesita un parametro")
    exit()
elif (len(sys.argv)<=1):
    print("Se necesita al menos un parametro")
    exit()

#variable con el csv que se nos pasa
csvFile = sys.argv[1]

#Creamos la case estudiante
class Estudiante:
    
    def __init__(self, nombre, id):
        self.nomalumne = nombre
        self.id = id

    def __str__(self):
        return "Nombre: " + self.nomalumne + ", ID: " + self.id

    def getNom(self):
        return self.nomalumne
    def getId(self):
        return self.id

#Lista de estudiantes
estudiantes = []

#Leemos el archivo .csv y creamos los studiantes
with open(csvFile) as csv_file:
    leer = csv.reader(csv_file, delimiter=",")
    cont = 0
    for row in leer:
        #Nos saltamos la columna 0, ya que es el nombre de las columnas
        if cont != 0:
            linea = row

            alumno = Estudiante(linea[0], linea[1])
            estudiantes.append(alumno)

        cont = cont + 1

#EAN 13 esta compuesto por 13 digitos, de izquierda a derecha, 3 = pais, 4 o 5 = propietario, 
# 4 o 5 de codigo de producto y 1 de digito de control
for i in range(len(estudiantes)):
    #Despues de meditarlo hemos decidido meter la longitud del nombre como propietario y el id como codigo del producto
    #el codigo de control sera un numero aleatorio
    nomEstudiante = estudiantes[i].getNom()
    idEstudiante = estudiantes[i].getId()

    numNom = str(len(nomEstudiante))

    numeroControl = str(random.randint(0,9))

    numCodigo = "000000" + numNom + "0000" + idEstudiante + numeroControl

    codigo = barcode.get("ean13",numCodigo,writer=ImageWriter())

    fillero = codigo.save(nomEstudiante)




