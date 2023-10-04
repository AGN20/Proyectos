from Escola import *


def crearEscola():

    nom = input("Escribe el nombre de la Escuela: ")
    localitat = input("Escribe la localidad: ")
    responsable = input("Escribe el responsable: ")

    professor = crearProfesores()
    alumne = crearAlumnes()

    escola = Escola(nom, localitat, responsable, professor, alumne)

    return escola

def crearProfesores():
    print("Creame una lista de profesores")

    profesor = []

    for i in range(2):
        nom = input("nombre del profesor: ")
        tipus = input("Tipo de docencia: ")
        profesor.append(Professor(nom, tipus))

    return profesor
      

def crearAlumnes():
    print("Creamos una lista de alumnes")

    alumnes = []

    for i in range(2):
        nom = input("Nom del alumne: ")
        curs = input("Curs del alumne: ")
        nomP = input("Profesor a carreg: ")
        tipusP = input("Tipus de docencia del profesor: ")
        proffesor = Professor(nomP, tipusP)
        alumnes.append(Alumne(nom, curs, proffesor))
        
        return alumnes


myEscuela = crearEscola()
print("ESCOLA:________________")
myEscuela.printNom()
print("LOCALITAT:________________")
myEscuela.printLocalitat()
print("RESPONSABLE:________________")
myEscuela.printResponsable()
print("PROFESORS Y TIPUS:________________")
myEscuela.printProfessors()
print("ALUMNES, CURS y PROFESSOR AL CARREC(AMB EL SEU TIPUS:________________")
myEscuela.printAlumnes()
