import random

#llamamos a pantalla para que el usuario nos de un numero
n = input("Ponme el numero de coches que quieres: ")
n = int(n)
colores = ["red", "white", "black", "pink", "blue"]
class Car: #clase coches
    matricula = 0
    color = ""

    def __init__(self):
        self.matricula = i
        self.color = self.nuevoColor()

    def imprimir(self): #Funcion imprimir, imprime 10 si son 10 o mas, si no te imprime los que son
        print("La matricula es ", self.matricula, " y es de color ", self.color)

    def nuevoColor(self): #Metodo que introduce a coche un color aleatorio
        ran = random.randint(0, 4)
        self.color = colores[ran]

    def matriculaNueva( self, numero ): #Metodo que permite cambiar la matricula del coche
        self.matricula = numero
        


for i in range(n): #for que crea coches y los guarda en una lista
    
    coche = Car() #Iniciamos la clase coche

    coche.matriculaNueva(i) #le instroducimos la matricula
    coche.nuevoColor()  # le decimos que le ponga un color aleatorio

    if i < 10: #si es menor de 10 imprimelos
            coche.imprimir()
    