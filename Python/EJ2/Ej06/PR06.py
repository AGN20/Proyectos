import sesionDB
from alumne import Alumne

def run():
    #Creamos alumnos de prueba
    adrian = Alumne("Adrian", "2DAM", 25, "zadriangonzalez1996@gmail.com", 564646464)
    ester = Alumne("Ester", "2DAM", 24, "ester@gmail.com", 3424232553)

    #añadimos los dos alumnos de pruebas a la sesion
    sesionDB.session.add(adrian)
    sesionDB.session.add(ester)

    #Tenemos que hacer un comit al final 
    sesionDB.session.commit()

if __name__ == '__main__':
    sesionDB.Base.metadata.create_all(sesionDB.engine)
    run()