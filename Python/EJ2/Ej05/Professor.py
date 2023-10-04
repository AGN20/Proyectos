class Professor:

    def __init__(self, nom, tipus) -> None:
        self.nom = nom
        #Controlamos que es de alguno de estos 3 tipos, si no es pintamelo
        if tipus == "ciencies":
            self.tipus = tipus
        elif tipus == "lletres":
            self.tipus = tipus
        elif tipus == "mixt":
            self.tipus = tipus
        else:
            print("no es un tipo de docencia")
            self.tipus = ""
    

    #Funciones qie devuelben los de datos
    def getNom(self):
        return self.nom
    def getTipus(self):
        return self.tipus

    #Insercion de datos
    def setNomb(self, x):
        self.nom = x
    def setTipus(self, x):
        #Controlamos que es de alguno de estos 3 tipos, si no es pintamelo
        if x == "ciencies":
            self.tipus = x
        elif x == "lletres":
            self.tipus = x
        elif x == "mixt":
            self.tipus = x
        else:
            print("no es un tipo de docencia")

    #Funciones que imprimen el contenido
    def printNom(self):
        print(self.nom)
    def printTipus(self):
        print(self.tipus)

    #Modificacio de dades
    def modNom(self, x):
        self.nom = x
    def modTipus(self, x):
        self.tipus = x