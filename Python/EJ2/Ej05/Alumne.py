from Professor import Professor


class Alumne:

    def __init__(self, nom, curs, professor) -> None:
        self.nom = nom
        self.curs = curs
        self.professor = professor

    #Funciones que devuelben los datos 
    def getNom(self):
        return self.nom
    def getCurs(self):
        return self.curs
    def getProfessor(self):
        return self.professor
    
    #Funciones que insertan datos
    def setNom(self, x):
        self.nom = x
    def setCurs(self, x):
        self.curs = x
    def setProfessor(self, x):
        self.professor = Professor.setNomb(x)

    #Funciones que imprimen los datos
    def printNom(self):
        print(self.nom)
    def printCurs(self):
        print(self.curs)
    def printProfesor(self):
        profesor = self.professor
        profesor.printNom()
        profesor.printTipus()
    
    #Funciones que modifique el contingut
    def modNom(self):
        mod = "Modificant nom, perfavor escriu el nou nom: "
        self.nom = mod
    def modCurs(self,):
        mod = "Modificant curs, perfavor escriu el nou curs: "
        self.curs = mod
    def modProfesor(self, x):
        elec = input("que vols modificar 'nom' o 'tipus'? ")
        if elec == "nom":
            self.professor.modNom()
        if elec == "tipus":
            self.professor.modTipus()