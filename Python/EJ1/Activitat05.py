import sys

print("Tu nombre es ", sys.argv[1], " y tu apellido es ", sys.argv[2])

def multiplicar(num):

    return num * 10

def multiplicar(num1, num2):

    return num1 * num2

def multiplicar( *args ):
    
    x = 1

    for i in range(len(args)):
        x = x * args[i]

    return x


print(multiplicar(10))
print(multiplicar(10,20))
print(multiplicar(10, 4, 2, 6, 5))