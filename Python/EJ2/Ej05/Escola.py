
import copy
from Alumne import Alumne
from Professor import Professor


class Escola:

    def __init__(self, nom, localitat, responsable, professors, alumnes) -> None:
        self.nom = nom
        self.localitat = localitat
        self.responsable = responsable
        self.professors = copy.copy(professors)
        self.alumnes = copy.copy(alumnes)

    #Funcion que añade alumnos
    def setAlumne(self, x):
        self.nom = x
    
    #Funcion que añade profesores
    def setProfessors(self, x):
        self.professors.append(x)

    #Funcion que elimina alumnos
    def popAlumne(self, x):
        self.alumnes.pop(x)

    #Funcion que elimine profesores
    def popProfessors(self, x):
        self.professors.pop(x)

    #Funciones que devuelben los datos
    def getNom(self):
        return self.nom
    def getLocalitat(self):
        return self.localitat
    def getResponsable(self):
        return self.responsable
    def getProfessors(self):
        for i in range(len(self.professors)):
            return self.professors[i]
    def getAlumnes(self):
        for i in range(len(self.alumnes)):
            return self.alumnes[i]

    #Funcion que modifican los datos
    def modNom(self, x):
        self.nom = x
    def modLocalitat(self, x):
        self.localitat = x
    def modResponsable(self, x):
        self.responsable = x
    def modProfessors(self, num):
        num = "Dime la identificacion del profesor(su numero en la lista): "
        profesor = self.professors[num]
        selec = input("Que vols cambiar, nom o tipus: ")
        if selec == "nom":
            profesor.modNom()
        if selec == "tipus":
            profesor.modTipus()
    def modAlumnes(self, posicion):
        num = "Dime la identificacion del Alumno(su numero en la lista): "
        alumne = self.alumnes[num]
        selec = input("Que vols cambiar, nom, curs o Professor")
        if selec == "nom":
            alumne.modNom()
        if selec == "curs":
            alumne.modCurs()
        if selec == "Professors":
            alumne.modProfessor()

    #Funciones que insertan datos(solo modifico aquellos datos que pueden ser insertados)
    def setProfessors(self, x):
        self.professors.append(x)
    def setAlumnes(self, x):
        self.alumnes.append(x)

    #Funcion que borran datos(solo de los que se pueden borra)
    def delProfessores(self, x):
        self.professors.pop(x)
    def delAlumnes(self, x):
        self.alumnes.pop(x)

    #Funciones que imprimen los datos
    def printNom(self):
        print(self.nom)
    def printLocalitat(self):
        print(self.localitat)
    def printResponsable(self):
        print(self.responsable)
    def printProfessors(self):
        for i in range(len(self.professors)):
            profe = self.professors[i]
            profe.printNom()
            profe.printTipus()

    def printAlumnes(self):
        for i in range(len(self.alumnes)):
            alumne = self.alumnes[i]
            alumne.printNom()
            alumne.printCurs()
            alumne.printProfesor()