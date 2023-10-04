from genericpath import isfile
import os
from posix import listdir
import shutil

path = os.path.dirname(os.path.realpath(__file__))


fileExtension = []
filePath = []
fileName = os.listdir(path)
fileName2 = []

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

extensionesStr = ""


for i in range(len(fileExtension)):
    if i != 0:
        extensionesStr = extensionesStr + "," + fileExtension[i]
    else:
        extensionesStr = fileExtension[i]

fileExtension = list(set(extensionesStr.split(",")))

for extension in fileExtension:
    try:
        os.mkdir(os.path.join(path,extension))
    except OSError as error:
        print("El directorio " + path+"/"+extension + " ya existe.")


for i in range(len(fileExtension)):
    for x in range(len(filePath)):

        nameP = filePath[x].split("/")[-1]

        if filePath[x].split(".")[-1] == fileExtension[i]:
            try:
                shutil.move(filePath[x],path+"/"+fileExtension[i]+"/"+ nameP)
            except OSError as error:
                print(" ERROR: Cannot move a not existing file! Or is already moved.")




