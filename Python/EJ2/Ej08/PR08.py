from genericpath import isfile
import os
from posix import listdir
import shutil

path = os.path.dirname(os.path.realpath(__file__))


fileExtension = []
filePath = []
fileName = os.listdir(path)
fileName2 = []

#Recorremos todos los ficheros y sacamos sus extensiones y sus rutas
for name in fileName:
    pathDir = path +"/"+ name

    if os.path.isfile(pathDir):
        extension = name.split(".")[-1]
        filePath.append(pathDir)
        fileExtension.append(extension)
    else:
        fileName2 = os.listdir(pathDir)
        for name in fileName2:
            pathN = pathDir +"/"+ name

            if os.path.isfile(pathN):
                extension = name.split(".")[-1]
                filePath.append(pathN)
                fileExtension.append(extension)
            else:
                fileName2 = os.listdir(pathN)

                for name in fileName2:
                    pathF = pathN +"/"+ name
                    if os.path.isfile(pathF):
                        extension = name.split(".")[-1]
                        filePath.append(pathF)
                        fileExtension.append(extension)

#Con esto quitamos las extensiones repetidas
extensionesStr = ""
for i in range(len(fileExtension)):
    if i != 0:
        extensionesStr = extensionesStr + "," + fileExtension[i]
    else:
        extensionesStr = fileExtension[i]
fileExtension = list(set(extensionesStr.split(",")))

#Por cada extenson creamos una carpeta con su nombre
for extension in fileExtension:
    try:
        os.mkdir(os.path.join(path,extension))
    except OSError as error:
        print("El directorio " + path+"/"+extension + " ya existe.")

#Por cada extension
for i in range(len(fileExtension)):
    #Recorremos todos los archivos
    for x in range(len(filePath)):

        #le sacamos el nombre al archivo
        nameP = filePath[x].split("/")[-1]

        #Si la extension concuerda
        if filePath[x].split(".")[-1] == fileExtension[i]:
            try:
                #Lo metemos dentro de la carpeta correspondiente
                shutil.move(filePath[x],path+"/"+fileExtension[i]+"/"+ nameP)
            except OSError as error:
                print(" No se a podido mover el archivo.")
