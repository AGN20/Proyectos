str1 = "Pikachu"
str2 = "MSmain"
str3 = "wobufet"

llistaPokimones = ["wobufet", "charmander", "bulbasur", "oddis"]


"""
EL parametro "is" ens compara els dos objectes i ens diu si són iguals
en canse de ser-ho retorna "True", si no ens retorna "False"
"""
print("Que pokemon es: ", str1 is str1)
print("Que pokemon es: ", str2 is str3)

"""
EL parametro "is not" ens compara els dos objectes i ens diu si no són iguals
en canse de ser-ho retorna "False", si no ens retorna "True"
"""
print("Que pokemon no es: ", str1 is not str1)
print("Que pokemon no es: ", str2 is not str3)

"""
EL parametro "in" ens diu si un objecte es troba dins d'una llista
en cas d'estar retorna "True", si no ens retorna "False"
"""
print("Que pokemon tienes: ", str1 in llistaPokimones)
print("Que pokemon tienes: ", str3 in llistaPokimones)

"""
EL parametro "not in" ens diu si un objecte es troba dins d'una llista
en cas d'estar retorna "False", si no ens retorna "True"
"""
#El paramtero "not in" nos dice si un parametro no esta incluido dentro de una lista
print("Que pokemon no tienes: ", str1 not in llistaPokimones)
print("Que pokemon no tienes: ", str3 not in llistaPokimones)