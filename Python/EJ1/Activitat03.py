import hashlib

nombre = ["adrian", "Pedro", "Ruben", "Jose", "Amelia"]
contraseña = [hashlib.new("sha1", b"peperoni"), hashlib.new("sha1", b"canino1avion4"), hashlib.new("sha1", b"perroarbol"), hashlib.new("sha1", b"cabutops"), hashlib.new("sha1", b"molinillos")]

#Creamos una lista que nos guardara una lista de dos elementos, numbre y usuario
llistaFinal = []

def almacenar(x, y):
    
    i = 0

    for i in range(len(nombre)):
        llista = [x[i]], y[i].hexdigest()

        llistaFinal.append(llista)

        i+1

    

almacenar(nombre, contraseña)
print(llistaFinal)
print("-----------------------------------------")

#Crearemos un diccionario que tendra de clave el nombre y de contenido la contraseña
diccionarioFinal = {}


def almacenar2(x, y):

    i = 0

    for i in range(len(nombre)):
        diccionarioFinal[x[i]] = y[i].hexdigest()

        i + 1

    

almacenar2(nombre, contraseña)
print(diccionarioFinal)
